import java.io.IOException;
import java.util.*;
import java.util.regex.*;

class BeaconLocator {

    public static void main(String[] args) {
		   	BeaconLocator obj = new BeaconLocator();
		   if (args.length > 0) {
		   		obj.dataFile = args[0];
				if (args.length > 1) {
					obj.targetRow = Integer.parseInt(args[1]);
				}
				if (args.length > 2) {
					obj.targetCol = Integer.parseInt(args[2]);
				}
		        obj.run();
		   }
     }

    public BeaconLocator() {
    }

	String dataFile;

	Grid grid = new Grid();

	int targetRow = 0;

	int targetCol = 0;

	public void run() {

		ArrayList<Sensor> allSensors = new ArrayList<Sensor>(0);
		Data dataObj = new Data(this.dataFile);
		ArrayList<String> data = dataObj.getData(this.dataFile);

		// Load the grid with sensors and beacons.
		for (int i=0; i<data.size(); i++) {
			int[] coords = this.parseLine(data.get(i));
			System.out.println(coords[0]);

			Sensor s = new Sensor(coords[0], coords[1]);
			allSensors.add(s);
			Beacon b = new Beacon(coords[2], coords[3], s);

			s.nearestBeacon = b;
			b.nearestSensor = s;

			this.grid.addItem(s);
			this.grid.addItem(b);
		}

		System.out.println("targetRow=" + this.targetRow + ". " + "getNumNoBeaconsForRow="+this.grid.getNumNoBeaconsForRow(this.targetRow, allSensors));

		System.out.println("targetCol=" + this.targetCol + ". " + "getNumNoBeaconsForCol="+this.grid.getNumNoBeaconsForCol(this.targetCol, allSensors));

		this.grid.doPartTwo(allSensors);
	}

	int[] parseLine(String line) {

		String[] bits = line.split(":");
		String sensor = bits[0];
		String beacon = bits[1];

		Pattern xPattern = Pattern.compile("x=(-*\\d*),");
		Pattern yPattern = Pattern.compile("y=(-*\\d*)");

		int sensorX = this.extractInt(xPattern, sensor);

		int sensorY = this.extractInt(yPattern, sensor);

		int beaconX = this.extractInt(xPattern, beacon);

		int beaconY = this.extractInt(yPattern, beacon);

		return new int[]{ sensorX, sensorY, beaconX,  beaconY };
	}

	int extractInt(Pattern p, String s) {
		Matcher m = p.matcher(s);
		m.find();
		return Integer.parseInt(m.group(1));
	}

}
