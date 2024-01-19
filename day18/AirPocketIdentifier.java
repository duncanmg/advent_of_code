import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// This works on a HashMap of possible Airs and determines which are actually Airs and how many, if any, form air pockets.
class AirPocketIdentifier {

	public static void main(String[] args) {
		AirPocketIdentifier obj = new AirPocketIdentifier(new Context());
	}

	public AirPocketIdentifier(Context c) {
		ctx = c;
	}

	Context ctx;

	boolean changed = true;

	Logger logger = new Logger(this);

	// Traverse the HashMap of potential Airs and call searchNeighbours on each. Repeat until no more changes are made.
	public void find() {

		HashMap<String, Air> workingCopy = new HashMap<String, Air>();
		while (this.changed) {
			this.changed = false;
			workingCopy = new HashMap<String, Air>();
			workingCopy.putAll(ctx.potentialAirs);
			for (Air airPocket : workingCopy.values()) {
				this.searchNeighbours(airPocket);
			}
		}

	}

	// For each Air, check each of its neighbours and use the information to determine whether the the Air is actually part of an air pocket.
	// If it is not, remove it from the HashMap of potential Airs and add it to the HashMap of Outsides.
	// A neighbour which does not exist at all is considered to be an Air and added to the HashMap of potential Airs. It will be checked later.
	private void searchNeighbours(Air airPocket) {

		this.logger.log("searchNeighbours: " + airPocket.id);
		for (Cube neighbour : airPocket.getPossibleNeighbours()) {
			this.logger.log("neighbour: " + neighbour.id);
			if (this.ctx.isLava(neighbour)) {
				continue;
			}
			else if (this.ctx.isOutside(neighbour))	{
				this.logger.log("isOutside: " + neighbour.id);
				this.ctx.outsideMap.put(airPocket.id, new Outside(airPocket));
				this.ctx.potentialAirs.remove(airPocket.id);
				changed = true;
			}
			else if (this.ctx.isPotentialAir(neighbour)) {
			}
			else if (this.ctx.isOutOfRange(neighbour)) {
				this.logger.log("isOutOfRange: " + neighbour.id);
				this.ctx.outsideMap.put(airPocket.id, new Outside(airPocket));
				this.ctx.potentialAirs.remove(airPocket.id);
				changed = true;
			}
			else {
				this.logger.log("Default to isAir: " + neighbour.id);
				changed = true;
				this.ctx.potentialAirs.put(neighbour.id, new Air(neighbour));
			}
		}
	}

}
