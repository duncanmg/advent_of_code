import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class RockPaperScissors2 {

    public static void main(String[] args) {
        System.out.println("RockPaperScissors.java!"); 

		RockPaperScissors obj = new RockPaperScissors();

		ArrayList<ArrayList<String>> moves = obj.getData();

		int total = 0;
		System.out.println("Size=" + moves.size());
		for (int i=0; i<moves.size(); i++) {
		    // System.out.println("i=" + i + " size=" + moves.get(i).size());
			// System.out.println("Opponent: " + moves.get(i).get(0) + " Me: " + moves.get(i).get(1));
			String him = moves.get(i).get(0);

			String me = moves.get(i).get(1);
//			switch(me) {
//				case "X":
//					total += 1;
//					break;
//				case "Y":
//					total += 2;
//					break;
//				case "Z":
//				    total += 3;
//				    break;	
//			}

			String pair = him + me;
			switch (pair) {
				// He plays rock. Play scissors to lose.
				case "AX":
					total += 3 + 0;
					break;
				case "AY":
					total += 1 + 3;
					break;
				case "AZ":
				    total += 2 + 6;
				    break;	

				// He plays paper. Play rock to lose.
				case "BX":
					total += 1 + 0;
					break;
				case "BY":
					total += 2 + 3;
					break;
				case "BZ":
				    total += 3 + 6;
				    break;	

				// He plays scissors. Play paper to lose.
				case "CX":
					total += 2 + 0;
					break;
				case "CY":
					total += 3 + 3;
					break;
				case "CZ":
				    total += 1 + 6;
				    break;	
			}

		}
		System.out.println("Total: " + total);

    }

	ArrayList<ArrayList<String>> getData() {

		ArrayList<ArrayList<String>> moves = new ArrayList<ArrayList<String>>(1);
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("data.txt"));
			String line = reader.readLine();

			while (line != null) {
				String[] move = line.split(" ");
				// System.out.println("move[0]: " + move[0] + " move[1]: " + move[1]);
			    moves.add(new ArrayList<String>(0));
				int len = moves.size();
				moves.get(len-1).add(move[0]);
				moves.get(len-1).add(move[1]);
				// System.out.println(len + " UUUUUUUUUUUUUUUUUUUUUU moves.get(len-1): " + moves.get(len-1).get(0) + " : " + moves.get(len-1).get(1));
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return moves;
	}

}
