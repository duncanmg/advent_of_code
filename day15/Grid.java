import java.io.IOException;
import java.util.*;

class Grid extends GenericGrid {

    public static void main(String[] args) {
		   Grid obj = new Grid();
     }

    public Grid() {
    }

	public int numMarkedNoBeacon = 0;

	public ArrayList<int[]> allRangesForRow;

	public ArrayList<int[]> allRangesForCol;

	public void doPartTwo(ArrayList<Sensor> allSensors) {
	  int x = 0;
      int y = 0;	
	  int limit = 4000000;
	  try {
	      while (x <= limit) {

		    y = 0;

		    // x = x + this.checkDistances(allSensors, x, y);

		    while (y <= limit) {
		  	  y = y + this.checkDistances(allSensors, x, y);
		    }

			x = x + 1;

	      }
	  }
	  catch(Exception e) {
		  long frequency = ((long)x * (long)4000000) + (long)y;
		  System.out.println("x=" + x + " y=" + y + " frequency=" + frequency + " test=" + ((long)x * (long)4000000));
		  System.out.println("Done. " + e + " frequency=" + frequency);
	  }
	}

	int checkDistances(ArrayList<Sensor> allSensors, int x, int y) throws Exception {

		  Iterator<Sensor> iterator = allSensors.iterator();
		  int incrementBy;
		  while (iterator.hasNext()) {
		      Sensor s = iterator.next();
			  Beacon nearest = s.nearestBeacon;
			  int distance = s.getDistance(nearest);
			  int distanceFromHere = s.getDistance(new Beacon(x,y));
			  if (distance >= distanceFromHere) {
				  // In range
				  incrementBy = Math.abs(distance - distanceFromHere) + 1;
				  return incrementBy;
			  }
		  }
		  throw new Exception("Not within range of any beacons. x=" + x + " y=" + y);
	}

	int[] getBounds(ArrayList<Sensor> allSensors) {

		Sensor first = allSensors.get(0);
		int minX = first.x;
		int maxX = first.x;
		int minY = first.y;
		int maxY = first.y;

		for (int x = 0; x < allSensors.size(); x++) {

			Sensor s = allSensors.get(x);
			Beacon nearest = s.nearestBeacon;
            int distance = s.getDistance(nearest);

			int lowY = s.y - distance;
			int highY = s.y + distance;

			int lowX = s.x - distance;
			int highX = s.x + distance;

			if (lowY < minY) {
				minY = lowY;
			}

			if (highY > maxY) {
				maxY = highY;
			}

			if (lowX < minX) {
				minX = lowX;
			}

			if (highX > maxX) {
				maxX = highX;
			}
		}
		return new int[]{minX, maxX, minY, maxY};
	}

	// getNumNoBeaconsForRow
	public long getNumNoBeaconsForRow(int row, ArrayList<Sensor> allSensors) {
		this.numMarkedNoBeacon = 0;

		System.out.println("getNumNoBeacons " + row);
		ArrayList<int[]> allRanges = new ArrayList<int[]>(0);
        for (int x=0; x<allSensors.size(); x++) {
			int[] range = this.getRangeForRow(allSensors.get(x), row);
			if (range.length > 0) {
				allRanges.add(range);
			}
		}

		// Sort by ascending first element.
		IntArrayComparator comp = new IntArrayComparator();
		Collections.sort(allRanges, comp);

		allRanges = this.removeOverlaps(allRanges);

		this.allRangesForRow = allRanges;

		long total = sumRanges(allRanges);

		total = total - this.numItemsOnRow(allSensors, row);

		return total;
	}

	// getNumNoBeaconsForCol
	public long getNumNoBeaconsForCol(int col, ArrayList<Sensor> allSensors) {
		this.numMarkedNoBeacon = 0;

		System.out.println("getNumNoBeaconsForCol " + col);
		ArrayList<int[]> allRanges = new ArrayList<int[]>(0);
        for (int x=0; x<allSensors.size(); x++) {
			int[] range = this.getRangeForCol(allSensors.get(x), col);
			if (range.length > 0) {
				allRanges.add(range);
			}
		}

		// Sort by ascending second element.
		IntArrayColComparator comp = new IntArrayColComparator();
		Collections.sort(allRanges, comp);

		for (int x=0; x<allRanges.size(); x++) {
			System.out.println(Arrays.toString(allRanges.get(x)));
		}
		System.out.println("\n");

		allRanges = this.removeOverlaps(allRanges);

		for (int x=0; x<allRanges.size(); x++) {
			System.out.println(Arrays.toString(allRanges.get(x)));
		}

		long total = sumRanges(allRanges);

		this.allRangesForCol = allRanges;

		total = total - this.numItemsOnCol(allSensors, col);

		return total;
	}

	long sumRanges(ArrayList<int[]> allRanges) {
		long total = 0;
		for (int x=0; x<allRanges.size(); x++) {
  			int[] range = allRanges.get(x);
			total = total + range[1] - range[0] + 1;
		}
		return total;
	}

	ArrayList<int[]> removeOverlaps(ArrayList<int[]> allRanges) {
		int changes = 1;
		while (changes > 0) {
			changes = 0;
			if (allRanges.size() < 2) {
				return allRanges;
			}
			for (int x=0; x<allRanges.size()-1; x++) {

				int[] current = allRanges.get(x);
				int[] next = allRanges.get(x+1);

				ArrayList<int[]> adjustedRanges = this.removeOverlap(current, next);

				// The first element is always present although the second value may have been adjusted.
				int[] ar = adjustedRanges.get(0);
				allRanges.set(x, new int[]{ar[0], ar[1]});

				// "next" has been absorbed into "current". Remove "next".
				if (adjustedRanges.size() == 1) {
					allRanges.remove(x+1);
					changes++;
				}

			}
		}
		return allRanges;
	}

	public ArrayList<int[]> removeOverlap(int[] a, int[] b) {
		ArrayList<int[]> out = new ArrayList<int[]>(0);
		if (a[0] <= b[0] && a[1] >= b[0] && a[1] <= b[1]) {
			a[1] = b[0]-1;
			out.add(a);
			out.add(b);
		}
		else if (a[0] <= b[0] && a[1] >= b[0] && a[1] > b[1]) {
			out.add(a);
		}
		else {
			out.add(a);
			out.add(b);
		}

		 return out;
	}

	// Return the number of sensors or beacons on the target row.
	int numItemsOnRow(ArrayList<Sensor> allSensors, int row) {
		Set<Integer> itemsOnRow = new HashSet<Integer>();
		Iterator<Sensor> iterator = allSensors.iterator();
		while (iterator.hasNext()) {
			Sensor s = iterator.next();
			Beacon b = s.nearestBeacon;
			if (s.y == row || b.y == row) {
				itemsOnRow.add(s.y == row ? s.x : b.x);
			}
		}
		return itemsOnRow.size();
	}

	// Return the number of sensors or beacons on the target col.
	int numItemsOnCol(ArrayList<Sensor> allSensors, int col) {
		Set<Integer> itemsOnCol = new HashSet<Integer>();
		Iterator<Sensor> iterator = allSensors.iterator();
		while (iterator.hasNext()) {
			Sensor s = iterator.next();
			Beacon b = s.nearestBeacon;
			if (s.x == col || b.x == col) {
				itemsOnCol.add(s.x == col ? s.y : b.y);
			}
		}
		return itemsOnCol.size();
	}

	// Return the range of X (Col) for a given sensor a given Y (row).
	int[] getRangeForRow(Sensor s, int row) {
		Beacon nearest = s.nearestBeacon;
		int distance = s.getDistance(nearest);

		// Min distance is a straight line from s to the row.
		int yDistanceFromRow = Math.abs(row - s.y);
        int searchDistance = Math.abs(distance - yDistanceFromRow);
		
		int minY = s.y - distance;
		int maxY = s.y + distance;

		int minX = s.x - searchDistance;
		int maxX = s.x + searchDistance;

		// No point if Y is out of range.
		if (row < minY || row > maxY) {
			System.out.println(s + ". Y is out of range");
			return new int[]{};
		}

		return new int[]{minX, maxX};
	}

	// Return the range of Y (Row) for a given sensor a given X (col).
	int[] getRangeForCol(Sensor s, int col) {
		Beacon nearest = s.nearestBeacon;
		int distance = s.getDistance(nearest);

		// Min distance is a straight line from s to the col.
		int xDistanceFromCol = Math.abs(col - s.x);
        int searchDistance = Math.abs(distance - xDistanceFromCol);
		
		int minX = s.x - distance;
		int maxX = s.x + distance;

		int minY = s.y - searchDistance;
		int maxY = s.y + searchDistance;

		// No point if X is out of range.
		if (col < minX || col > maxX) {
			System.out.println(s + ". X is out of range");
			return new int[]{};
		}

		return new int[]{minY, maxY};
	}

//	// Mark the positions around this sensor which are on the target
//	// row and cannot contain a beacon.
//	public void markNoBeacon(Sensor s, int row) {
//
//		Beacon nearest = s.nearestBeacon;
//		System.out.println(nearest);
//		int distance = s.getDistance(nearest);
//
//		// Min distance is a straight line from s to the row.
//		int minDistance = row - s.y;
//
//		// double searchDistance = Math.sqrt((distance*distance)-(minDistance*minDistance));
//		int yDistanceFromRow = Math.abs(row - s.y);
//        int searchDistance = Math.abs(distance - yDistanceFromRow);
//		
//		int minY = s.y - distance;
//		int maxY = s.y + distance;
//
//		int minX = s.x - searchDistance;
//		int maxX = s.x + searchDistance;
//
//		System.out.println("Sensor " + s + " minX " + minX + " maxX " + maxX + " range " + (maxX - minX));
//		// No point if Y is out of range.
//		if (row < minY || row > maxY) {
//			System.out.println(s + ". Y is out of range");
//			return;
//		}
//
//		for (int x = minX; x <= maxX; x++) {
//			Beacon current = new Beacon(x,row);
//			if (s.getDistance(current) <= distance) {
//				if (this.isEmpty(x,row)) {
//						this.addItem(new NoBeacon(x,row));
//						this.numMarkedNoBeacon = this.numMarkedNoBeacon + 1;
//				}
//			}
//		}
//	}

	int[] limits;

	// Return an ArrayList of all the sensors.
	ArrayList<Sensor> getAllSensors() {

		int[] limits = this.getLimits();
		this.limits = limits;

		int minX = limits[0];
		int maxX = limits[1];
		int minY = limits[2];
		int maxY = limits[3];

		System.out.println("getAllSensors. Num positions to check: " + ((maxX - minX) * (maxY - minY)));
		ArrayList<Sensor> allSensors = new ArrayList<Sensor>(0);
		for (int x=minX; x<=maxX; x++) {
			for (int y=minY; y<=maxY; y++) {
				Item item = this.getItem(x,y);
				if (item instanceof Sensor) {
					allSensors.add((Sensor)item);
				}
			}
		}

        return allSensors;
	}

//	ArrayList<Item> getRowEmpties(int row) {
//
//		int minX = limits[0];
//		int maxX = limits[1];
//		int minY = limits[2];
//		int maxY = limits[3];
//
//		ArrayList<Item> allItems = new ArrayList<Item>(0);
//		for (int x=minX; x<=maxX; x++) {
//				Item item = this.getItem(x,row);
//				if (!(item instanceof Empty)) {
//					allItems.add(item);
//				}
//		}
//		return allItems;
//	}


}
