import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Moves {

    public static void main(String[] args) {
        Moves obj = new Moves();
    }

	private Data dataObj = new Data("moves.txt");

    private ArrayList<String> data = dataObj.data;

	private StartingPositions startingPositions = new StartingPositions();

	private ArrayList<ArrayList<String>> stacks = this.startingPositions.stacks;

    public Moves() {
		getMoves();
    }

	void getMoves() {

	  for (int i=0; i<this.data.size(); i++) {

          String[] tokens = this.data.get(i).split(" ");
		  int numToMove = Integer.parseInt(tokens[1]);
		  int from = Integer.parseInt(tokens[3]);
		  int to = Integer.parseInt(tokens[5]);

		  this.move(numToMove, from, to);
		  // this.startingPositions.dump();
	  }

	  String result = "";
	  for (int i=0; i<this.stacks.size(); i++) {
		 ArrayList<String> stack = this.stacks.get(i);
         result = result + stack.get(stack.size()-1);
	  }
	  System.out.println("result: " + result);
    }

	private void move(int numToMove, int from, int to) {
		for (int i=0; i<numToMove; i++) {

			ArrayList<String> stack = this.stacks.get(from-1);
			int top = stack.size()-1;
			String crate = stack.get(top);
            stack.remove(top);

			stack = this.stacks.get(to-1);
			stack.add(crate);
		}
	}

}
