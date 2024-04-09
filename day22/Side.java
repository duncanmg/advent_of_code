import java.io.IOException;
import java.util.*;

// Row no increases --------------->> Column no increases
//        ...#                                  |
//        .#..                                  |
//        #...                                  |
//        ....                                  |
//...#.......#                                  |
//........#...                                  |
//..#....#....                                  v
//..........#.                                  v
//        ...#....
//        .....#..
//        .#......
//        ......#.
//
// The columns and rows are padded to be equal lengths. So in the above example
// the range of the column index is 0-11 and that of the row index is 0-15.

class Side {

	public static void main(String[] args) {
		Side obj = new Side(1, 1);
	}

	public Side(int id, int len) {
		this.id = id;
		this.sideLength = len;
	}

	final int id;

	final int sideLength;

	Logger logger = new Logger(this, true);

	ArrayList<ArrayList<Character>> layout = new ArrayList<ArrayList<Character>>();

	final char WALL = '#';

	final char TILE = '.';

	final char NOTHING = '_';

	final int RIGHT = 0;

	final int DOWN = 1;

	final int LEFT = 2;

	final int UP = 3;

	char currentDirection = 'R';

	int currentCol = 0;

	int currentRow = 0;

	int colOffset = 0;

	int rowOffset = 0;

	ArrayList<ArrayList<Character>> contents = new ArrayList<ArrayList<Character>>();

	int directionScore() {
		switch(currentDirection) {
			case 'R':
				return RIGHT;
			case 'D':
				return DOWN;
			case 'L':
				return LEFT;
			case 'U':
				return UP;
		}
		return -1;
	}

	void addContents(ArrayList<ArrayList<Character>> layout, int startCol, int startRow) {

		colOffset = startCol;
		rowOffset = startRow;

		// logger.log("addContents() 01 id=" + this.id + " startCol=" + startCol + " startRow=" + startRow + " sideLength=" + sideLength);
		for (int i = startCol; i < startCol + sideLength; i++) {
			// logger.log("addContents() 02 i=" + i);
			ArrayList<Character> currentRow = layout.get(i);
			// logger.log("addContents() 03");
			ArrayList<Character> newRow = new ArrayList<Character>();
			// logger.log("addContents() 04");
			newRow.addAll(currentRow.subList(startRow, startRow + sideLength));
			// logger.log("addContents() 05");
			contents.add(newRow);
			// logger.log("addContents() 06");
		}
		// logger.log("addContents() End 07");

	}

	// Start traversing this side. "direction" is the direction of travel.
	// 'R' means travelling right. ie Enter from far left and travel right.
	void enterSide(char direction, int col, int row) {
		switch(direction) {
			case 'R':
				currentCol = col;
				currentRow = row;
				break;
			case 'D':
				currentCol = col;
				currentRow = row;
				break;
			case 'L':
				currentCol = col;
				currentRow = contents.get(0).size() - 1;
				break;
			case 'U':
				currentCol = contents.size() - 1;
				currentRow = row;
				break;
		}
		currentDirection = direction;
	}

	// Move one position in the current direction if possible.
	// Three possible return values:
	// TILE - Successful move.
	// WALL - No move. Obstructed by wall.
	// NOTHING - No move. Would be out of bounds.
	char move() {
		int nextCol = currentCol;
		int nextRow = currentRow;

		logger.log("move() 01 nextCol=" + nextCol + " nextRow=" + nextRow + " currentDirection=" + currentDirection);
		logSide();
		switch(currentDirection) {
			case 'R':
				nextRow++;
				break;
			case 'D':
				nextCol++;
				break;
			case 'L':
				nextRow--;
				break;
			case 'U':
				nextCol--;
				break;
		}

		logger.log("move() 02 nextCol=" + nextCol + " nextRow=" + nextRow);
		if (!withinBounds(nextCol, nextRow)) {
			return NOTHING;
		}

		logger.log("move() 03 nextCol=" + nextCol + " nextRow=" + nextRow);
		char nextContent = getContent(nextCol, nextRow);

		logger.log("move() 04 nextCol=" + nextCol + " nextRow=" + nextRow + " nextContent=" + nextContent);
		if (nextContent == WALL) {
			return WALL;
		}

		currentCol = nextCol;
		currentRow = nextRow;

		logger.log("move() 05 nextCol=" + nextCol + " nextRow=" + nextRow);
		return nextContent;

	}

	boolean withinBounds(int col, int row) {
		//logger.log("withinBounds() col=" + col + " row " + row);
		if (row > contents.get(0).size() - 1) {
			//logger.log("withinBounds() 01");
			return false;
		}
		if (col > contents.size() - 1) {
			//logger.log("withinBounds() 02");
			return false;
		}
		if (row < 0) {
			//logger.log("withinBounds() 03");
			return false;
		}
		if (col < 0) {
			//logger.log("withinBounds() 04");
			return false;
		}
		//logger.log("withinBounds() 05");
		return true;
	}

	// Get the content of the current position (given by currentCol, currentRow).
	char getContent() {
		return contents.get(currentCol).get(currentRow);
	}

	char getContent(int col, int row) {
		return contents.get(col).get(row);
	}

	void logSide() {
		logger.log("logSide() id=" + this.id);
		for (int col = 0; col < sideLength; col++) {
			String out = "";
			for (int row = 0; row < sideLength; row++) {
				if (col == currentCol && row == currentRow) {
					out += '*';
				}
				else {
					out += getContent(col, row);
				}
			}
			logger.log(out);
		}
	}
}
