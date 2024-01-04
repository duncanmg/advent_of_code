import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Priorities {

	private ArrayList<String> priorities;

	public int get(String s) {
		return this.priorities.indexOf(s);
	}

    public Priorities() {
        System.out.println("Priorities.java!"); 

        ArrayList<String> priorities2 =  new ArrayList<String>(0);
        String[] p = { "-", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                           "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                           "u", "v", "w", "x", "y", "z",
                           "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                           "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                           "U", "V", "W", "X", "Y", "Z" };
        Collections.addAll(priorities2, p);
 
        priorities = priorities2;
    }

}
