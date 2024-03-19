import java.io.IOException;
import java.util.*;

// ./run.sh RunCalculator --dataFile=data.txt
class RunCalculator {

	public static void main(String[] args) throws Exception {
		RunCalculator obj = new RunCalculator(args);
	}

	Data dataObj = new Data();

	public RunCalculator(String[] args) throws Exception {
		ArgumentProcessor argProcessor = new ArgumentProcessor(args);
		HashMap<String, String> argMap = argProcessor.process();
		String dataFile = argMap.get("dataFile");

		ArrayList<String> data = dataObj.getData(dataFile);

		HashMap<String, Operation> operations = parseData(data);

		double answer = solveOperations(operations);
		System.out.println("root.answer=" + answer);
	}

	Logger logger = new Logger(this, true);

	double solveOperations(HashMap<String, Operation> operations) {
		Operation root = operations.get("root");
		while (!root.solved) {
			for (String name : operations.keySet()){
				Operation operation = operations.get(name);
				if (operation.solved) {
					continue;
				}
				operation.solve();
			}
		}
		return root.answer;
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
