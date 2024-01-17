import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class SurfaceAreaCalculator {

	public static void main(String[] args) {
		SurfaceAreaCalculator obj = new SurfaceAreaCalculator(args);
	}

	public SurfaceAreaCalculator(String[] args) {
		ArgumentProcessor argProcessor = new ArgumentProcessor(args);
		argMap = argProcessor.process();
		if (argMap.containsKey("dataFile")) {
			this.logger.log("Data File: " + argMap.get("dataFile"));
			this.data = this.dataObj.getData(argMap.get("dataFile"));
			this.buildBoulderMap();
			this.calculateSurfaceArea();
		}
	}

	Logger logger = new Logger(this, true);

	HashMap<String, String> argMap;

	Data dataObj = new Data();

	ArrayList<String> data = new ArrayList<String>();

	HashMap<String, Boulder> boulderMap = new HashMap<String, Boulder>();

	void buildBoulderMap() {
		this.logger.log("Building Boulder Map. No of lines: " + this.data.size());
		for (String line : this.data) {
			Boulder boulder = new Boulder(line);
			if (boulderMap.containsKey(boulder.id)) {
				this.logger.log("Duplicate Boulder ID: " + boulder.id);
			}
			this.boulderMap.put(boulder.id, boulder);
		}
		this.logger.log("Built Boulder Map. No of boulders: " + this.boulderMap.size());
	}

	void calculateSurfaceArea() {
		int totalSurfaceArea = 0;
		this.logger.log("Calculating Surface Area");
		for (String key : this.boulderMap.keySet()) {
			Boulder boulder = this.boulderMap.get(key);
			int boulderSurfaceArea = 6;
			for (String neighbor : boulder.getPossibleNeighbours()) {
				if (this.boulderMap.containsKey(neighbor)) {
					boulderSurfaceArea--;
				}
			}
			totalSurfaceArea += boulderSurfaceArea;
		}
		logger.log("Total Surface Area: " + totalSurfaceArea);
	}

}
