import java.util.*;
import java.util.regex.*;

class Route {

    public static void main(String[] args) {
		   Route obj = new Route();
     }

    public Route() {
    }

    public Route(ArrayList<Cave> cs) {
		for (int i=0; i<cs.size(); i++) {
			this.addCave(cs.get(i));
		}
    }

    public Route(Cave c) {
		this.addCave(c);
    }

    Logger logger = new Logger();

    public Boolean debug = false;

    void log(String msg) {
        this.logger.debug = this.debug;
        this.logger.log(msg);
    }

	public ArrayList<Cave> caves = new ArrayList<Cave>(0);

	public int estimatedTotalFlow() {
		int runningTotal = 0;
		HashMap<String, Cave> done = new HashMap<String, Cave>();
		for (int i=0; i<this.caves.size()-1; i++ ) {
			Cave c = this.caves.get(i);
			this.log("estimatedTotalFlow() " + c + " totalFlow " + c.totalFlow  + " open " + c.isOpen + " flow " + c.flow);
			if (c.totalFlow == 0 || done.containsKey(c.name)) {
				continue;
			}
			runningTotal += c.totalFlow;
			done.put(c.name, c);
			this.log("2 estimatedTotalFlow() " + c);
		}	
		this.log("estimatedTotalFlow() returning " + runningTotal);
		return runningTotal;
	}

	public void addCave(Cave c) {
		this.caves.add(c);
	}

	Route cloneRoute() {
		Route clone = new Route();
		for (int i=0; i<this.caves.size(); i++) {
			clone.addCave(new Cave(this.caves.get(i)));
		}
		return clone;
	}

	public int CompareTo(Route r) {
		if (this.estimatedTotalFlow() > r.estimatedTotalFlow()) {
			return 1;
		}
		else if (this.estimatedTotalFlow() == r.estimatedTotalFlow()) {
			return 0;
		}
		else {
			return -1;
		}
	}

	@Override
	public boolean equals(Object o) {
		Route r = (Route) o;
		this.log("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
		this.log(this.caves.equals(r.caves) ? "Cave lists are equal" : "Cave lists are NOT equal");
		this.log(this.caves.toString());
		this.log(r.caves.toString());
	    if (this.caves.equals(r.caves) && this.estimatedTotalFlow() == r.estimatedTotalFlow()) {
			 this.log("Return true");
	         return true;
	    }
	    else {
	        return false;
	    }
	}

	public String toString() {
		return this.estimatedTotalFlow() + " : " + this.caves;
	}

}
