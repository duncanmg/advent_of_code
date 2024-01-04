import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class GroupedRucksackList {

	private RucksackList rucksacks = new RucksackList();

	private ArrayList<RucksackList> groupedRucksacks = new ArrayList<RucksackList>(0);

	public RucksackList get(int i) {
		return this.groupedRucksacks.get(i);
	}

	public int size() {
		return this.groupedRucksacks.size();
	}

	public GroupedRucksackList() {
        System.out.println("Constructor GroupedRucksackList.java!"); 
		rucksacks.populate();
		while (rucksacks.size()>0){
			addGroup(0);
		}
	}

    public static void main(String[] args) {

		GroupedRucksackList obj = new GroupedRucksackList();

    }

	private void addGroup(int start) {

		this.groupedRucksacks.add(new RucksackList());
		int current = this.groupedRucksacks.size() - 1;
		for (int i=start; i<start+3; i++) {
			if (this.rucksacks.size() > 0) {
			  this.groupedRucksacks.get(current).add(this.rucksacks.get(0));
			  this.rucksacks.remove(0);
			}
		}
	}
}
