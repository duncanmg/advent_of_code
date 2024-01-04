import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class TotalCalories {

    public static void main(String[] args) {
        System.out.println("TotalCalories.java!"); 

		TotalCalories obj = new TotalCalories();

		ArrayList<ArrayList<Integer>> caloriesByElf = caloriesByElf = obj.getData();

		// elfNo, calories
		ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>(1);

		Integer maxCalories = new Integer(0);
		Integer winningElfNo = new Integer(0);
		for (int i=0; i<caloriesByElf.size(); i++ ){
			int count = 0;
			for (int j=0; j<caloriesByElf.get(i).size(); j++) {
				count = count + caloriesByElf.get(i).get(j);
			}
			ArrayList<Integer> a = new ArrayList<Integer>(0);
			a.add(i);
			a.add(count);
			results.add(a);
			if (count > maxCalories) {
				maxCalories = count;
				winningElfNo = i;
			}
		}
		System.out.println("Winning elf: " + winningElfNo.toString() + ". Calories " + maxCalories.toString() + ".");

	Collections.sort(results, new Comparator<ArrayList<Integer>> () {
    @Override
    public int compare(ArrayList<Integer> a,  ArrayList<Integer> b) {
        return a.get(1).compareTo(b.get(1));
    }
});

    int s = results.size()-1;
    System.out.println("1st " + results.get(s).get(0) + " with " + results.get(s).get(1));
    System.out.println("2nd " + results.get(s-1).get(0) + " with " + results.get(s-1).get(1));
    System.out.println("3rd " + results.get(s-2).get(0) + " with " + results.get(s-2).get(1));
	int firstThree = results.get(s).get(1) + results.get(s-1).get(1) + results.get(s-2).get(1);
	System.out.println("Total for first 3: " + firstThree);
    }

	ArrayList<ArrayList<Integer>> getData() {

		ArrayList<ArrayList<Integer>> caloriesByElf = new ArrayList<ArrayList<Integer>>(1);
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("data.txt"));
			String line = reader.readLine();

			caloriesByElf.add(new ArrayList<Integer>(0));
			while (line != null) {
				int len = caloriesByElf.size();
				if (line.length() == 0) {
			         caloriesByElf.add(new ArrayList<Integer>(0));
					 len++;
				}
				else {
				  caloriesByElf.get(len-1).add(new Integer(line));
				  // read next line
				}
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return caloriesByElf;
	}

}
