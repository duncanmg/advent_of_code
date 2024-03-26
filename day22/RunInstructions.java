import java.io.IOException;
import java.util.*;

class RunInstructions {

	public static void main(String[] args) throws Exception{
		RunInstructions obj = new RunInstructions(args);
	}

	public RunInstructions() {
	}

	public RunInstructions(String[] args) throws Exception{
		if (args.length != 2) {
			return;
		}
		loadData(args);

		layout.moveToOrigin();

		run();
	}

	// This is the Layout object. The layout data can be accessed as layout.layout.
	Layout layout = new Layout();

	// The string of instructions eg 10R5L2
	String instructions = "";

	// Pointer used by getNextInstruction().
	// Holds the index of the current position in the instructions string.
	int instructionsIndex = 0;

	// This loads both the dataFile and the layoutFile.
	void loadData(String[] args) {

		ArgumentProcessor argProcessor = new ArgumentProcessor(args);
		HashMap<String, String> argMap = argProcessor.process();
		String dataFile = argMap.get("dataFile");
		String layoutFile = argMap.get("layoutFile");

		Data data = new Data();
		ArrayList<String> layoutData = data.getData(layoutFile);
		logger.log(layoutFile + " contains " + layoutData.size() + " lines.");

		layout = new Layout();
		layout.parseData(layoutData);

		data = new Data();
		ArrayList<String> instructionData = data.getData(dataFile);

		instructions = instructionData.get(0);
		logger.log(dataFile + " contains " + instructions.length() + " characters.");

	}

	public void run() throws Exception{

		String currentInstruction = getNextInstruction();
		int distance = 0;
		String direction = layout.currentDirection;

		boolean hasDirection = true;
		boolean hasDistance = false;

		while (!currentInstruction.equals("")) {

			logger.log("currentInstruction=" + currentInstruction + " distance=" + distance);
			if (currentInstruction.matches("^\\d+$")) {
				distance = Integer.parseInt(currentInstruction);
				hasDistance = true;
				logger.log("Set distance to " + distance + " hasDistance=" + hasDistance);
			}
			else {
				logger.log("About to set direction. Current direction is " + layout.currentDirection);
				layout.setDirection(currentInstruction);
				hasDirection = true;
				logger.log("Set direction is now " + layout.currentDirection + " hasDirection=" + hasDirection);
			}

			if (hasDirection && hasDistance) {
				logger.log("hasDirection and hasDistance are both true.");
				layout.move(distance);

				hasDirection = false;
				hasDistance = false;
			}
			currentInstruction = getNextInstruction();
			logger.log("currentColumnPos=" + layout.currentColumnPos + " currentRowPos=" + layout.currentRowPos);
			logger.log("+++");
		}

		System.out.println("currentColumnPos=" + layout.currentColumnPos + " currentRowPos=" + layout.currentRowPos + " currentDirection=" + layout.currentDirection);
		System.out.println("Answer=" + ( (layout.currentColumnPos + 1) * 1000 + (layout.currentRowPos +1) * 4 + layout.directionScore()));
	}

	// If the string "instructions" is "U123D", this will return "U", "123", "D", ""
	// on successive calls.
	String getNextInstruction() {
		if (instructionsIndex >= instructions.length()) {
			return "";
		}
		Character nextChar = instructions.charAt(instructionsIndex);
		if (Character.isDigit(nextChar)) {
			String out = "";
			while (Character.isDigit(nextChar)) {
				out += nextChar;
				instructionsIndex++;
				if (instructionsIndex >= instructions.length()) {
					return out;
				}
				nextChar = instructions.charAt(instructionsIndex);
			}
			return out;
		}
		else {
			instructionsIndex++;
			return String.valueOf(nextChar);
		}
	}

	Logger logger = new Logger(this, true);

}
