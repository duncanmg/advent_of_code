import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class RucksackList {

	private ArrayList<ArrayList<String>> rucksacks = new ArrayList<ArrayList<String>>(0);

	public boolean add(ArrayList<String> s) {
		return this.rucksacks.add(s);
	}

	public ArrayList<String> get(int i) {
		return this.rucksacks.get(i);
	}

	public ArrayList<String> remove(int i) {
		return this.rucksacks.remove(i);
	}

	public int size() {
		return this.rucksacks.size();
	}

	public void populate() {
		this.rucksacks = this.getData();
	}

    public RucksackList() {
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
