import java.util.*;
import java.util.Arrays;
import java.io.File;
import java.io.FileOutputStream;

class RouteMapper {

    public static void main(String[] args) {
		   RouteMapper obj = new RouteMapper();
     }

    public RouteMapper(Cave c, ArrayList<Cave> cs) {
		cave = c; // The cave the search starts from.
		allCaves = cs; // All possible caves.
    }

    public RouteMapper() {
		this(new Cave(), new ArrayList<Cave>(0));
    }

	Logger logger = new Logger();

	public Boolean debug = false;

	void log(String msg) {
		this.logger.debug = this.debug;
		this.logger.log(msg);
	}

	final Cave cave;

	final  ArrayList<Cave> allCaves;

	private ArrayList<RoutePair>listA = new ArrayList<RoutePair>(0);

	private ArrayList<RoutePair> listB = new ArrayList<RoutePair>(0);

	int maxListSize = 200000;

	boolean filterApplied = false;

	public ArrayList<Cave> humanCavesToKeep = new ArrayList<Cave>(0);

	public ArrayList<Cave> elephantCavesToKeep = new ArrayList<Cave>(0);

	void applyFilter() {
		ArrayList<RoutePair> tmp = new ArrayList<RoutePair>(0);
		System.out.println("Start applyFilter. size listB " + this.listB.size());
		for (int i=0; i < this.listB.size(); i++) {
			Route human = this.listB.get(i).human;
			Route elephant = this.listB.get(i).elephant;
			boolean keep = true;
			for (int j=0; j<humanCavesToKeep.size(); j++) {
				if (j >= elephant.caves.size()) {
					break;
				}
				if (human.caves.get(j).equals(humanCavesToKeep.get(j)) == false) {
					// System.out.println("\n\nDiscard " + human + "\n\n");
					keep = false;
				}
			}
			for (int j=0; j<elephantCavesToKeep.size(); j++) {
				if (j >= elephant.caves.size()) {
					break;
				}
				if (elephant.caves.get(j).equals(elephantCavesToKeep.get(j)) == false) {
					// System.out.println("\n\nDiscard " + elephant + "\n\n");
					keep = false;
				}
			}
			if (keep == true) {
				tmp.add(this.listB.get(i));
			}
		}
		this.listB = tmp;
		System.out.println("End applyFilter. size listB " + this.listB.size());
		this.filterApplied = true;
	}

	private void switchLists() {
			this.log("In switchLists. listB.size: " + this.listB.size());
			if (this.listB.size() > this.maxListSize) {
				this.log("In switchLists. Size >  this.maxListSize");
				this.listA = new ArrayList<RoutePair>(this.listB.subList(0, this.maxListSize));
			}
			else {
				this.log("In switchLists. Size <=  this.maxListSize");
				this.listA = new ArrayList<RoutePair>(this.listB);
			}
			this.log("End switchLists. listA.size: " + this.listA.size() + " listB.size: " + this.listB.size());
			this.listB = new ArrayList<RoutePair>(0);
	}

	void initListA(Cave human, Cave elephant) {

		RoutePair currentRoutePair = new RoutePair();
		currentRoutePair.human.addCave(human);
		currentRoutePair.elephant.addCave(elephant);
		this.listA.add(currentRoutePair);
	}

	ArrayList<RoutePair> map(int minutesLeft) throws Exception {

		this.log("In map minutesLeft=" + minutesLeft + " listA.size()=" + this.listA.size());
		// Initialize list A.
		Cave currentCave = this.cave;

		this.initListA(new Cave(currentCave), new Cave(currentCave));

		this.initListA(new Cave(currentCave), currentCave.open(minutesLeft));

		this.initListA(currentCave.open(minutesLeft), new Cave(currentCave));

		int minuteCounter = minutesLeft;
		// Needs time to travel from start.
		minuteCounter--;
		while (minuteCounter >= 0) {

			this.log("Top minuteCounter=" + minuteCounter + " listA.size " + this.listA.size());

			// Each RoutePair in listA.
			for (int i=0; i<this.listA.size(); i++) {

				RoutePair routePair = this.listA.get(i);
				this.log("i=" + i + " routePair " + routePair);

				Cave humanTail = routePair.human.caves.get(routePair.human.caves.size()-1);
				Cave elephantTail = routePair.elephant.caves.get(routePair.elephant.caves.size()-1);

				// If its opening, add the opened cave. What about the elephant?
				if (humanTail.isOpening) {
					this.log("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ " + humanTail.name + " is opening");
					this.addElephantRoutePairsForPaths(humanTail.open(minuteCounter), elephantTail, routePair, minuteCounter);
		 			continue;
				}

				// Each human path.
				for (int j=0; j<humanTail.pathsFromHere.length; j++) {
					this.log("j=" + j + " pathsFromHere[j] " + humanTail.pathsFromHere[j]);

					Cave nextCave = this.findCave(humanTail.pathsFromHere[j]);
					Route r = routePair.human.cloneRoute();
					int pos = r.caves.lastIndexOf(nextCave);
					if ( pos >= 0){
						// Already in route. Clone it so we know whether it is open.
						nextCave = new Cave(r.caves.get(pos));
					}

					// Add without opening.
					this.log("Add " + nextCave.name + " without opening it.");
					//r.addCave(nextCave);
					// this.listB.add(r);
					this.addElephantRoutePairsForPaths(nextCave, elephantTail, routePair, minuteCounter);

					Cave currentElephantTail = routePair.elephant.caves.get(routePair.elephant.caves.size()-1);
					if (nextCave.equals(elephantTail) && currentElephantTail.isOpening) {
						continue;
					}

					if (nextCave.flow > 0 && !routePair.caveInRoutePairIsOpen(nextCave)) {
							// Add another route with the open cave.
							// Route r2 = route.cloneRoute();
							this.log("Add " + nextCave.name + " and open it.");
							this.addElephantRoutePairsForPaths(nextCave.open(minuteCounter), elephantTail, routePair, minuteCounter);
							// r2.addCave(nextCave.open(minuteCounter));
							// this.listB.add(r2);
					}
				}
				this.log("End of pathsFromHere loop");
			}
			this.log("End of listA loop");

			if (this.listB.size() > this.maxListSize && this.filterApplied == false) {
				System.out.println("applyFilter minuteCounter: " + minuteCounter);
				this.applyFilter();
			}

			// this.log("Middle minuteCounter=" + minuteCounter);
			// Don't need to sort unless we intend to truncate the list
			// or it is the last iteration.
			if (this.listB.size() > this.maxListSize || minuteCounter == 0) {
				// Sort listB;
				System.out.println("Sorting.size listB=" + this.listB.size() + " minuteCounter =" + minuteCounter);
				this.log("Sorting.size listB=" + this.listB.size() + " minuteCounter =" + minuteCounter);
				RoutePairComparator comp = new RoutePairComparator();
				Collections.sort(this.listB, comp);
				Collections.reverse(this.listB);
				System.out.println("Best so far: " + this.listB.get(0));
				this.log("Sorted");
			}

			this.switchLists();
			minuteCounter--;
			// this.log("End loop. minuteCounter=" + minuteCounter);
		}
		this.log("Return " + this.listA);
		return this.listA;

	}

	void addElephantRoutePairsForPaths(Cave humanCave, Cave elephantTail, RoutePair routePair, int minuteCounter) throws Exception {

			if (elephantTail.isOpening) {
				RoutePair r = routePair.cloneRoutePair();
				r.human.caves.add(humanCave);
				r.elephant.caves.add(elephantTail.open(minuteCounter));
				this.listB.add(r);
				return;
			}

			for (int j=0; j<elephantTail.pathsFromHere.length; j++) {
				this.log("j=" + j + " pathsFromHere[j] " + elephantTail.pathsFromHere[j]);

				Cave nextCave = this.findCave(elephantTail.pathsFromHere[j]);
				RoutePair r = routePair.cloneRoutePair();

				int posE = r.elephant.caves.lastIndexOf(nextCave);
				if (posE >= 0) {
					// Already in route. Clone it so we know whether it is open.
					nextCave = new Cave(r.elephant.caves.get(posE));
				}

				// Add without opening.
				this.log("Add " + nextCave.name + " without opening it.");
				r.human.addCave(humanCave);
				r.elephant.addCave(nextCave);
				this.listB.add(r);

				if (humanCave.equals(nextCave) && (humanCave.isOpen || humanCave.isOpening)) {
					continue;
				}

				if (nextCave.flow > 0 && !routePair.caveInRoutePairIsOpen(nextCave)) {
						// Add another route with the open cave.
						RoutePair r2 = routePair.cloneRoutePair();
						this.log("Add " + nextCave.name + " and open it.");
						r2.human.addCave(humanCave);
						r2.elephant.addCave(nextCave.open(minuteCounter));
						this.listB.add(r2);
				}
			}
	}

	ArrayList<Cave> cloneArrayList( ArrayList<Cave> ar) {
		ArrayList<Cave> out = new ArrayList<Cave>(0);
		for (int i=0; i<ar.size(); i++) {
			out.add(ar.get(i));
		}
		return out;
	}

	// Retrieves the cave from the list of all caves.
	Cave findCave(String name) throws Exception {
		for (int i=0; i<this.allCaves.size(); i++) {
			if (this.allCaves.get(i).name.equals(name)) {
				return this.allCaves.get(i);
			}
		}
		throw new Exception("Cave not found");
	}

	int evaluateRoute(Route route) {
		return 999;
	}

}
