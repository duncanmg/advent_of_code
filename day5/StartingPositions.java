import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class StartingPositions {

    public static void main(String[] args) {
        StartingPositions obj = new StartingPositions();
    }

    public StartingPositions() {
		getStartingPositions();
    }

	public void dump() {
		for (int i=0; i<this.stacks.size(); i++){
		  System.out.println(this.stacks.get(i));
		}
	}

	private Data dataObject = new Data("starting_positions.txt");

	private ArrayList<String> data = dataObject.data;

	public  ArrayList<ArrayList<String>> stacks = new ArrayList<ArrayList<String>>(0);

	void getStartingPositions() {

		ArrayList<String> lines = this.data;
		Collections.reverse(lines);

		// Remove the stack numbers.
		lines.remove(0);
        
		for (int i=0; i<9; i++){
			this.stacks.add(new ArrayList<String>());
		}

		// Identifiers at positions 1, 5, 9, 13, 17 .. 33. (0 based)
		// Start at 1, steps of 4.
		for (int i=0; i<lines.size(); i++) {
			String l = lines.get(i);
			int stackNo = 0;
			for (int pos=1; pos < l.length(); pos+=4) {
				String v = l.substring(pos, pos + 1);
				if (!v.equals(" ")) {
				  this.stacks.get(stackNo).add(v);
				}  
				stackNo++;
			}
		}
		// this.dump();
	}

}
