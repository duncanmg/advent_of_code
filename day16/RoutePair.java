import java.util.*;
import java.util.regex.*;

class RoutePair {

    public static void main(String[] args) {
		   RoutePair obj = new RoutePair();
     }

    public RoutePair() {
    }

    public RoutePair(Route h, Route e) {
		this.human = h;
		this.elephant = e;
    }

	public Route human = new Route();

	public Route elephant = new Route();

    Logger logger = new Logger();

    public Boolean debug = false;

    void log(String msg) {
        this.logger.debug = this.debug;
        this.logger.log(msg);
    }

	public int estimatedTotalFlow() {
		return this.human.estimatedTotalFlow() + this.elephant.estimatedTotalFlow();
	}

	RoutePair cloneRoutePair() {
		RoutePair clone = new RoutePair( this.human.cloneRoute(), this.elephant.cloneRoute());
		return clone;
	}

	public int CompareTo(RoutePair r) {
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

	Boolean caveInRoutePair(Cave c) {
		int posH = this.human.caves.lastIndexOf(c);
		int posE = this.elephant.caves.lastIndexOf(c);
		return (posH >= 0 || posE >= 0) ? true : false;
	}

	Boolean caveInRoutePairIsOpen(Cave c) {
		int posH = this.human.caves.lastIndexOf(c);
		if (posH >= 0 && this.human.caves.get(posH).isOpen){
			return true;
		}
		int posE = this.elephant.caves.lastIndexOf(c);
		if (posE >= 0 && this.elephant.caves.get(posE).isOpen){
			return true;
		}
		return false;
	}

	Boolean caveInRoutePairIsClosed(Cave c) {
		int posH = this.human.caves.lastIndexOf(c);
		if (posH >= 0 && (this.human.caves.get(posH).isOpen || this.human.caves.get(posH).isOpening)){
			return true;
		}
		int posE = this.elephant.caves.lastIndexOf(c);
		if (posE >= 0 && (this.elephant.caves.get(posE).isOpen || this.elephant.caves.get(posE).isOpening)){
			return true;
		}
		return false;
	}

	Cave getCave(Cave c) throws Exception {
		int posH = this.human.caves.lastIndexOf(c);
		if (posH >= 0){
			return this.human.caves.get(posH);
		}
		int posE = this.elephant.caves.lastIndexOf(c);
		if (posE >= 0){
			return this.elephant.caves.get(posE);
		}
		throw new Exception("getCave " + c + " not in RoutePair.");
	}

	@Override
	public boolean equals(Object o) {
		RoutePair r = (RoutePair) o;
		if (this.human.equals(r.human) && this.elephant.equals(r.elephant)) {
	         return true;
	    }
	    else {
	        return false;
	    }
	}

	public String toString() {
		return "Human: " + this.human.toString() + " Elephant: " + this.elephant.toString();
	}

	public int hashCode() {
	    // Unique integer based on toString().
	   return this.toString().hashCode();
	}

}
