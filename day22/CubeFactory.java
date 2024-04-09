import java.io.IOException;
import java.util.*;

class CubeFactory {

	public static void main(String[] args) {
		CubeFactory obj = new CubeFactory();
	}

	// CubeFactory cubeFactory = new CubeFactory();
	// cubeFactory.parseData(data);
	// cube.calcSideLength();
	// if (cube.isCubeOne()) {
	// 	cube = new CubeOne();
	// }
	// else {
	// 	cube = new CubeTwo();
	// }
	public CubeFactory() {
	}

	Logger logger = new Logger(this, true);

	ArrayList<ArrayList<Character>> layout = new ArrayList<ArrayList<Character>>();

	final char WALL = '#';

	final char TILE = '.';

	final char NOTHING = '_';

	final int RIGHT = 0;

	final int DOWN = 1;

	final int LEFT = 2;

	final int UP = 3;

	int sideLength = -1;

	private int numPositions = 0;

	Cube buildCube(ArrayList<String> layoutData) throws Exception {
		parseData(layoutData);
		calcSideLength();
		if (isLayoutOne()) {
			logger.log("isLayoutOne() returned true");
			CubeOne cube =  new CubeOne(sideLength, layout);
			cube.addSides();
			return (Cube) cube;
		}
		else if (isLayoutTwo()) {
			logger.log("isLayoutOne() returned false");
			CubeTwo cube =  new CubeTwo(sideLength, layout);
			cube.addSides();
			return (Cube) cube;
		}
		else {
			throw new Exception("buildCube() Unknown layout");
		}
	}

	// Create the layout.
	void parseData(ArrayList<String> data) {

		for (String line : data) {
			//logger.log("line: " + line);
			ArrayList<Character> row = new ArrayList<Character>();
			for (int i=0; i<line.length(); i++) {
				switch(line.charAt(i)) {
					case WALL:
						row.add(WALL);
						numPositions++;
						break;
					case TILE:
						numPositions++;
						row.add(TILE);
						break;
					case ' ':
						row.add(NOTHING);
						break;
				}
			}
			layout.add(row);
		}

	}

	boolean isLayoutOne() {
		int count = 0;
		try {
			if (layout.get(0).get(0) == NOTHING) {
				count++;
			}
			if (layout.get(0).get(sideLength-1) == NOTHING) {
				count++;
			}
			if (layout.get(2 * sideLength).get(0) == NOTHING) {
				count++;
			}
			if (layout.size() == 12) {
				count++;
			}
			// logger.log("isLayoutOne() layout.size()=" + layout.size());
			// logger.log("isLayoutOne() count=" + count);
			return count == 4 ? true : false;
		}
		catch(IndexOutOfBoundsException e) {
			logger.log("isLayoutOne() IndexOutOfBoundsException, return false. count=" + count);
			return false;
		}
	}

	boolean isLayoutTwo() {
		try {
			int count = 0;
			if (layout.get(0).get(0) == NOTHING) {
				count++;
			}
			if (layout.get(sideLength).get(0) == NOTHING) {
				count++;
			}
			if (layout.get(2 * sideLength).get(0) != NOTHING) {
				count++;
			}
			if (layout.get(3 * sideLength).get(0) != NOTHING) {
				count++;
			}
			return count == 4 ? true : false;
		}
		catch(IndexOutOfBoundsException e) {
			return false;
		}
	}

	// Return length of side. eg If cube has 6 sides of 4 * 4, return 4.
	void calcSideLength() {
		// Num walls plus tiles.
		int validPositions = numPositions;

		int sideArea = validPositions / 6;

		sideLength = (int) Math.sqrt(sideArea);
	}

}
