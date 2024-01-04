import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Rucksacks2 {

    public static void main(String[] args) {
        System.out.println("Rucksacks2.java!"); 

		GroupedRucksackList groupedRucksacks = new GroupedRucksackList();

		Priorities priorities = new Priorities();

		int total = 0;
		for (int i=0; i<groupedRucksacks.size(); i++) {

			RucksackList rucksacks = groupedRucksacks.get(i);

			ArrayList<String> first = rucksacks.get(0);
			ArrayList<String> second = rucksacks.get(1);
			ArrayList<String> third = rucksacks.get(2);

		    String badge = "";
			for (int x=0; x<first.size(); x++) {
				if (second.indexOf(first.get(x)) == -1) {
					continue;
				}
				if (third.indexOf(first.get(x)) == -1) {
					continue;
				}
				badge = first.get(x);
				break;
			}
			total += priorities.get(badge);
		}
        System.out.println("Total=" + total);

    }

}
