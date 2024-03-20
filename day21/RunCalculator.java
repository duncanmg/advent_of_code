import java.io.IOException;
import java.util.*;

// ./run.sh RunCalculator --dataFile=data.txt
class RunCalculator {

	public static void main(String[] args) throws Exception {
		RunCalculator obj = new RunCalculator(args);
	}

	Data dataObj = new Data();

	// The key is the name (eg "humn") the value is an Operation.
	HashMap<String, Operation> operations = new HashMap<String, Operation>();

	// A list of instructions which has been filtered to remove the constants.
	ArrayList<ArrayList<String>> savedInstructionParts = new ArrayList<ArrayList<String>>();

	public RunCalculator(String[] args) throws Exception {
		ArgumentProcessor argProcessor = new ArgumentProcessor(args);
		HashMap<String, String> argMap = argProcessor.process();
		String dataFile = argMap.get("dataFile");

		ArrayList<String> data = dataObj.getData(dataFile);

		operations = parseData(data);

		double answer = solveOperations(operations);
		System.out.println("root.answer=" + answer);

		ArrayList<String> sensitiveOperationNames = findSensitiveOperations("humn", "root");
		logger.log("Found " + sensitiveOperationNames.size() + " sensitiveOperationNames.");

		searchForEquality(sensitiveOperationNames);
	}

	Logger logger = new Logger(this, true);

	void searchForEquality(ArrayList<String> sensitiveOperationNames) {
		logger.log("01 searchForEquality");
		Operation root = operations.get("root");
		double difference = root.lhs.answer - root.rhs.answer;
		double lastDifference = 0;

		Operation humn = operations.get("humn");
		double humnAnswer = humn.answer;
		long change = 1000000000;

		int lastDirection = 1;

		int numDirectionChanges = 0;

		logger.log("02 searchForEquality");
		int maxTries = 100000;
		int numTries = 0;
		while (Math.abs(difference) >= 0.001) {
			logger.log("difference=" + difference);

			for (String name : sensitiveOperationNames) {
				Operation op = operations.get(name);
				if (!op.getClass().getName().equals("Constant")){
					logger.log("Set solved to false for " + name);
					operations.get(name).solved = false;
				}
			}

			humn = operations.get("humn");
			logger.log("humnAnswer=" + humnAnswer + " change= " + change);

			// operations.put("humn", new Constant((long) humnAnswer + change));
			humn.answer = humnAnswer + change;
			humnAnswer = humn.answer;

			logger.log("searchForEquality about to call solveOperations");
			double answer = solveOperations(operations);
			lastDifference = difference;
			root = operations.get("root");
			difference = root.lhs.answer - root.rhs.answer;

			int direction = Math.abs(difference) - Math.abs(lastDifference) > 0 ? -1 : 1;
			logger.log("direction " + direction + " difference " + difference + " lastDifference " + lastDifference);
			if (direction != lastDirection) {
				numDirectionChanges++;
			}
			else {
				numDirectionChanges = 0;
			}
			lastDirection = direction;
			

			logger.log("numDirectionChanges " + numDirectionChanges + " change " + change);
			if (numDirectionChanges > 5 && Math.abs(change) >= 10) {
				change = change / 10;
				numDirectionChanges = 0;
			}

			change *= direction;

			logger.log("End loop difference=" + difference);
			if (numTries > maxTries) {
				System.out.println("Break because numTries exceeded. " + numTries);
				break;
			}
			numTries++;
		}
		System.out.println("root.answer " + root.answer + " root.lhs.answer " + root.lhs.answer + " root.rhs.answer " + root.rhs.answer);
		System.out.println("humn.answer " + humn.answer);
		
	}

	// eg findSensitiveOperations("humn", "root");
	// Traverse the list "savedInstructionParts" and returns a list of
	// the names of operations which are likely to change when "start" changes.
	// The traversal terminates when "end" is reached.
	ArrayList<String> findSensitiveOperations(String start, String end) {

		ArrayList<String> out = new ArrayList<String>();

		// Add to output.
		out.add(start);

		// Return if endpoint reached.
		if (start.equals(end)) {
			logger.log("At end. " + end);
			return out;
		}

		logger.log("Look for " + start);
		// Look for instructions which use "start" as their "lhs" or "rhs".
		for (ArrayList<String> instructionParts : savedInstructionParts) {

			// eg "humn"
			String name = instructionParts.get(0);

			// eg "sllz", "+", "lgvd".
			ArrayList<String> rest = splitRest(instructionParts.get(1));

			String lhs = rest.get(0);
			String rhs = rest.get(2);
			logger.log("lhs: " + lhs + " rhs " + rhs);

			// Recurse
			if (lhs.equals(start)) {
				logger.log("lhs");
				out.addAll(findSensitiveOperations(name, end));
			}
			else if (rhs.equals(start)) {
				logger.log("rhs");
				out.addAll(findSensitiveOperations(name, end));
			}
		}
		return out;
	}

	double solveOperations(HashMap<String, Operation> operations) {
		Operation root = operations.get("root");
		logger.log("Start solveOperations");
		while (!root.solved) {
			for (String name : operations.keySet()){
				Operation operation = operations.get(name);
				if (operation.solved) {
					continue;
				}
				logger.log("solveOperations solve " + name);
				operation.solve();
			}
		}
		return root.answer;
	}

	// Split an instruction into name and the rest
	ArrayList<String> splitNameRest(String instruction) {
		String[] bits = instruction.split(": ");
		ArrayList<String> out = new ArrayList<String>(Arrays.asList(bits));
		return out;
	}

	// Split into lhs, operator, rhs
	ArrayList<String> splitRest(String rest) {
		String[] parts = rest.split(" ");
		return new ArrayList<String>(Arrays.asList(parts));
	}

	// Each line from the data file is an instruction (root: pppw + sjmn) consisting
	// of name, lhs, operator, rhs.
	// An operation is a class of type Add, Subtract, Multiply or Divide.
	HashMap<String, Operation> parseData(ArrayList<String> data) {

		HashMap<String, Operation> out = new HashMap<String, Operation>();
		ArrayList<ArrayList<String>> instructionParts = new ArrayList<ArrayList<String>>();

		// Add the constants to the hashmap "out".
		// Add the others to the arraylist "operations" for further processing.
		for (String instruction : data) {
			// Split the instruction into "name" and the rest.
			String[] bits = instruction.split(": ");
			if (bits[1].matches("^\\d+$")) {
				out.put(bits[0], new Constant(Long.parseLong(bits[1])));
			}
			else {
				ArrayList<String> op = new ArrayList<String>();
				op.add(bits[0]);
				op.add(bits[1]);
				instructionParts.add(op);
			}
		}
		savedInstructionParts.addAll(instructionParts);

		// Eventually everything will be moved to the hashmap "out".
		while (instructionParts.size() > 0) {

			// operations which need further processing.
			ArrayList<ArrayList<String>> newInstructionParts = new  ArrayList<ArrayList<String>>();

			for ( ArrayList<String> instructionPart : instructionParts) {

				String name = instructionPart.get(0);

				String[] insParts = instructionPart.get(1).split(" ");

				String operator = insParts[1];
				String lhs = insParts[0];
				String rhs = insParts[2];

				if (out.containsKey(lhs) && out.containsKey(rhs)) {
					// The components are in the hashmap so we can create the Operation objects.
					switch(operator) {
						case "+":
							Operation add = new Add(out.get(lhs), out.get(rhs));
							out.put(name, add);
							break;
						case "-":
							Operation minus = new Subtract(out.get(lhs), out.get(rhs));
							out.put(name, minus);
							break;
						case "*":
							Operation multiply = new Multiply(out.get(lhs), out.get(rhs));
							out.put(name, multiply);
							break;
						case "/":
							Operation divide = new Divide(out.get(lhs), out.get(rhs));
							out.put(name, divide);
							break;
					}
				}
				else {
					// Haven't got what we need. Store it and try again later.
					newInstructionParts.add(instructionPart);
				}
			}

			instructionParts = newInstructionParts;
		}

		return out;
	}

}
