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

		run();
	}

	Cube cube = new Cube();

	// The string of instructions eg 10R5L2
	String instructions = "";

	// Pointer used by getNextInstruction().
	// Holds the index of the current position in the instructions string.
	int instructionsIndex = 0;

	// This loads both the dataFile and the layoutFile.
	void loadData(String[] args) throws Exception{

		ArgumentProcessor argProcessor = new ArgumentProcessor(args);
		HashMap<String, String> argMap = argProcessor.process();
		String dataFile = argMap.get("dataFile");
		String layoutFile = argMap.get("layoutFile");

		Data data = new Data();
		ArrayList<String> layoutData = data.getData(layoutFile);
		logger.log(layoutFile + " contains " + layoutData.size() + " lines.");

		CubeFactory cubeFactory = new CubeFactory();
		cube = cubeFactory.buildCube(layoutData);

		data = new Data();
		ArrayList<String> instructionData = data.getData(dataFile);

		instructions = instructionData.get(0);
		logger.log(dataFile + " contains " + instructions.length() + " characters.");

	}

	public void run() throws Exception{

		String currentInstruction = getNextInstruction();
		int distance = 0;
		String direction;

		boolean hasDirection = true;
		boolean hasDistance = false;

		while (!currentInstruction.equals("")) {

			logger.log("currentInstruction=" + currentInstruction);
			if (currentInstruction.matches("^\\d+$")) {
				distance = Integer.parseInt(currentInstruction);
				hasDistance = true;
				logger.log("Set distance to " + distance);
			}
			else {
				logger.log("Set direction to " + currentInstruction);
				cube.setDirection(currentInstruction);
				hasDirection = true;
			}

			if (hasDirection && hasDistance) {
				logger.log("About to call cube.move(" + distance + ")");
				logger.log("Before cube.move(distance). currentColumnPo=" 
						+ cube.getCurrentSide().currentCol + " currentRowPos=" + cube.getCurrentSide().currentRow);
				cube.move(distance);
				logger.log("After cube.move(" + distance + "). currentColumnPos=" 
						+ cube.getCurrentSide().currentCol + " currentRowPos=" + cube.getCurrentSide().currentRow);

				hasDirection = false;
				hasDistance = false;
			}
			currentInstruction = getNextInstruction();
			logger.log("+++");
		}

		System.out.println("Done. What was the answer?");
		Side currentSide = cube.getCurrentSide();
		int absCol = currentSide.colOffset + currentSide.currentCol;
		int absRow = currentSide.rowOffset + currentSide.currentRow;
		System.out.println("currentSideNo=" + currentSide.id + " colOffset=" + currentSide.colOffset + " currentCol=" + currentSide.currentCol);
		System.out.println("currentSideNo=" + currentSide.id + " rowOffset=" + currentSide.rowOffset + " currentRow=" + currentSide.currentRow);
		System.out.println("currentDirection=" + currentSide.currentDirection + " directionScore=" + currentSide.directionScore());
		System.out.println("Answer=" + ( (absCol + 1) * 1000 + (absRow +1) * 4 + currentSide.directionScore()));

		// System.out.println("currentColumnPos=" + cube.getCurrentSide().currentCol + " currentRowPos=" + cube.getCurrentSide().currentRow 
		// + " currentDirection=" + cube.getCurrentSide().currentDirection);
		// System.out.println("Answer=" + ( (cube.getCurrentSide().currentCol + 1) * 1000 + (cube.getCurrentSide().currentRow +1) * 4 +cube.getCurrentSide()layout.directionScore()));
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
