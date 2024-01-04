import java.util.*;
import java.util.regex.*;

// A Cave object contains the parsed version of one line of input.
// eg "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB"
// It's attributes are:
//
// String 				name			- The name of the cave eg "AA"
// int 					flow			- The instanstaneous flow rate eg 10
// String[]			 	pathsFromHere	- List of caves which can be reached directly.

class Cave {

    public static void main(String[] args) {
		   Cave obj = new Cave("Valve AA has flow rate=0; tunnels lead to valves DD, II, BB");
     }

    public Cave() {
		this("Valve AA has flow rate=0; tunnels lead to valves DD, II, BB");
    }

    public Cave(String l) {
		String[] bits = l.split(";");
		if (l.length() < 40) {
			System.out.println("Cave constructor. Got " + l);
			System.out.println("Cave constructor. Expected something like \"Valve AA has flow rate=0; tunnels lead to valves DD, II, BB\"");
		}
		line = l;

		String[] left = bits[0].split("=");
		name = left[0].substring(6,8);
		flow = Integer.parseInt(left[1]);

		Pattern p = Pattern.compile("^.*valves* ");
		Matcher m = p.matcher(bits[1]);
		String right = m.replaceAll("");

		pathsFromHere = right.split(", ");

		// Open defaults to false.
		// Total flow defaults to 0.
    }

	public Cave(Cave c) {
		name = c.name;
		flow = c.flow;
		totalFlow = c.totalFlow;
		pathsFromHere = c.pathsFromHere;
		isOpen = c.isOpen;
		isOpening = c.isOpening;
		line = c.line;
		// Open retains its value.
		// Total flow retains its value.
	}

	public final String line;

	public final String name;

	public final int flow;

	public String[] pathsFromHere;

	public String toString() {
		return this.name;
	}

	public int totalFlow = 0;

	public Boolean isOpen = false;

	public Boolean isOpening = false;

	public Cave open(int minutesLeft) throws Exception {
		// System.out.println("1 open called for " + this.name + " minutesLeft=" + minutesLeft + ". isOpening: " + this.isOpening);
		if (this.isOpen) {
			throw new Exception("Valve " + this.name + " is already open");
		}	
		// System.out.println("2 open called for " + this.name);
		Cave c = new Cave(this);
		if (!this.isOpening) {
			c.isOpening = true;
			return c;
		}
		// System.out.println("3 open called for " + this.name);
		c.isOpen = true;
		c.isOpening = false;
		if ( minutesLeft > 0) {
			c.totalFlow = this.flow * (minutesLeft);
			// System.out.println(this.name + " set totalFlow to " + c.totalFlow);
		}	
			// System.out.println("2 " + this.name + " set totalFlow to " + c.totalFlow);
		return c;
	}

	@Override
	public boolean equals(Object o) {
		if (this.name.equals(((Cave) o).name)) {
			return true;
		}
		else {
			return false;
		}
	}

	// Not called when .equals is called. Have I done this right? Why was it necessary
	// to override this?
	public int hashCode() {
		// Unique integer based on name.
		return this.name.hashCode();
	}
}
