import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

class MovesPart2 {

    public static void main(String[] args) {
        MovesPart2 obj = new MovesPart2();
    }

	private Data dataObj = new Data("moves.txt");

    private ArrayList<String> data = dataObj.data;

	private StartingPositions startingPositions = new StartingPositions();

	private ArrayList<ArrayList<String>> stacks = this.startingPositions.stacks;

    public MovesPart2() {
		getMovesPart2();
    }

	void getMovesPart2() {

	  for (int i=0; i<this.data.size(); i++) {

          String[] tokens = this.data.get(i).split(" ");
		  int numToMove = Integer.parseInt(tokens[1]);
		  int from = Integer.parseInt(tokens[3]);
		  int to = Integer.parseInt(tokens[5]);

		  this.move(numToMove, from, to);
		  // this.startingPositions.dump();
		  // break;
	  }

	  String result = "";
	  for (int i=0; i<this.stacks.size(); i++) {
		 ArrayList<String> stack = this.stacks.get(i);
         result = result + stack.get(stack.size()-1);
	  }
	  System.out.println("result: " + result);
    }

	private void move(int numToMove, int from, int to) {

			ArrayList<String> stack = this.stacks.get(from-1);
			int endRange = stack.size();
			int startRange = endRange - numToMove;
			List<String> crates = stack.subList(startRange, endRange);
			List<String> cratesToAdd = crates.stream().collect(Collectors.toList());
			for (int i=endRange-1; i>=startRange; i--) {
				stack.remove(i);
			}
            // stack.removeAll(crates) can't be used because it removes duplicates!

			stack = this.stacks.get(to-1);
			stack.addAll(cratesToAdd);
	}

}
