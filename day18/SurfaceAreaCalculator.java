import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class SurfaceAreaCalculator {

	// ./run.sh SurfaceAreaCalculator --dataFile=data.txt 2>&1
	// ...
	// SurfaceAreaCalculator: Total Surface Area: 4482
	// SurfaceAreaCalculator: Total Surface Area Excluding Air Pockets: 2576
	public static void main(String[] args) {
		SurfaceAreaCalculator obj = new SurfaceAreaCalculator(args);
	}

	public SurfaceAreaCalculator(String[] args) {
		ArgumentProcessor argProcessor = new ArgumentProcessor(args);
		argMap = argProcessor.process();
		if (argMap.containsKey("dataFile")) {
			this.logger.log("Data File: " + argMap.get("dataFile"));
			this.data = this.dataObj.getData(argMap.get("dataFile"));
			this.buildLavaMap();
			this.calculateSurfaceArea();
		}
	}

	Logger logger = new Logger(this, true);

	HashMap<String, String> argMap;

	Data dataObj = new Data();

	ArrayList<String> data = new ArrayList<String>();

	Context c = new Context();

	void buildLavaMap() {
		this.logger.log("Building Lava Map. No of lines: " + this.data.size());
		for (String line : this.data) {
			Lava lava = new Lava(line);
			if (c.lavaMap.containsKey(lava.id)) {
				this.logger.log("Duplicate Lava ID: " + lava.id);
			}
			this.c.lavaMap.put(lava.id, lava);

			if (lava.x < c.minX) {
				c.minX = lava.x;
			}
			if (lava.x > c.maxX) {
				c.maxX = lava.x;
			}
			if (lava.y < c.minY) {
				c.minY = lava.y;
			}
			if (lava.y > c.maxY) {
				c.maxY = lava.y;
			}
			if (lava.z < c.minZ) {
				c.minZ = lava.z;
			}
			if (lava.z > c.maxZ) {
				c.maxZ = lava.z;
			}
		}
		this.logger.log("Built Lava Map. No of lavas: " + this.c.lavaMap.size());
	}

	void calculateSurfaceArea() {
		int totalSurfaceArea = 0;
		this.logger.log("Calculating Surface Area");

		// This calculates the surface area of the lavas including air pockets. It
		// also build a HashMap of objects that could be air pockets.
		for (String lavaId : this.c.lavaMap.keySet()) {
			Lava lava = this.c.lavaMap.get(lavaId);
			int lavaSurfaceArea = 6;
			for (Cube neighbour : lava.getPossibleNeighbours()) {
				if (this.c.lavaMap.containsKey(neighbour.id)) {
					lavaSurfaceArea--;
				}
				else {
					Air air = this.c.potentialAirs.get(neighbour.id);
					if (air == null) {
						air = new Air(neighbour);
						this.c.potentialAirs.put(air.id, air);
					}
				}
			}
			totalSurfaceArea += lavaSurfaceArea;
		}

		logger.log("Number Of Airs: " + c.potentialAirs.size());

		AirPocketIdentifier largeAirPocketFinder = new AirPocketIdentifier(this.c);
		largeAirPocketFinder.find();

		// This calculates the surface area of the air pockets.
		int totalAirSurfaceArea = 0;
		for (String airId : this.c.potentialAirs.keySet()) {
			Air air = this.c.potentialAirs.get(airId);
			int airSurfaceArea = 6;
			for (Cube neighbour : air.getPossibleNeighbours()) {
				if (this.c.potentialAirs.containsKey(neighbour.id)) {
					airSurfaceArea--;
				}
			}
			totalAirSurfaceArea += airSurfaceArea;
		}

		logger.log("Number Of Airs After Running AirPocketIdentifier: " + c.potentialAirs.size());
		logger.log("Total Surface Area: " + totalSurfaceArea);
		logger.log("Total Surface Area Excluding Air Pockets: " + (totalSurfaceArea - totalAirSurfaceArea));
	}

}
