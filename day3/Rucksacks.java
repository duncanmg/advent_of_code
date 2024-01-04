import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Rucksacks {

    public static void main(String[] args) {
        System.out.println("Rucksacks.java!"); 

		Rucksacks obj = new Rucksacks();

		ArrayList<String> priorities =  new ArrayList<String>(0);
		String[] p = { "-", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
		                    "u", "v", "w", "x", "y", "z",
		                    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
		                    "U", "V", "W", "X", "Y", "Z" };
        Collections.addAll(priorities, p);

		ArrayList<ArrayList<String>> rucksacks = obj.getData();

		int total = 0;
		for (int i=0; i<rucksacks.size(); i++) {
			ArrayList<String> rucksack = rucksacks.get(i);
			int size = rucksack.size();
			List<String> left = rucksack.subList(0,size/2);
			List<String> right = rucksack.subList(size/2, size);
			// System.out.println('-');
		   	// System.out.println(left);
			// System.out.println(right);

			for (int x=0; x<left.size(); x++){
				if (right.indexOf(left.get(x)) != -1) {
					// System.out.println(left.get(x));
					int priority = priorities.indexOf(left.get(x));
					// System.out.println(priority);
					total += priority;
					break;
				}
			}
		}

		// System.out.println("Z=" + priorities.indexOf("Z"));
		System.out.println("Total=" + total);

    }

	ArrayList<ArrayList<String>> getData() {

		ArrayList<ArrayList<String>> rucksacks = new ArrayList<ArrayList<String>>(1);
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("data.txt"));
			String line = reader.readLine();

			while (line != null) {
				String[] items = line.split("");
				ArrayList<String> all = new ArrayList<String>(0);
				Collections.addAll(all, items);
				// System.out.println("move[0]: " + move[0] + " move[1]: " + move[1]);
			    rucksacks.add(all);
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rucksacks;
	}

}
