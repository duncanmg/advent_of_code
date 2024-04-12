import java.io.IOException;
import java.util.*;

class Cube {

	public static void main(String[] args) {
		Cube obj = new Cube();
	}

	// The Cube object needs to be created first because it has some generic methods
	// Cube cube = new Cube();
	// cube.parseData(data);
	// cube.calcSideLength();
	// if (cube.isCubeOne()) {
	// 	cube = new CubeOne();
	// }
	// else {
	// 	cube = new CubeTwo();
	// }
	public Cube() {
	}

	Logger logger = new Logger(this, true);

	ArrayList<ArrayList<Character>> layout = new ArrayList<ArrayList<Character>>();

	static final char WALL = '#';

	static final char TILE = '.';

	static final char NOTHING = '_';

	static final int RIGHT = 0;

	static final int DOWN = 1;

	static final int LEFT = 2;

	static final int UP = 3;

	int sideLength = 4;

	ArrayList<Side> sides = new ArrayList<Side>();

	Side buildSide(int id, int startCol, int startRow) {
		// logger.log("01 buildSide id=" + id + " startCol=" + startCol + " startRow=" + startRow);
		Side side = new Side(id, sideLength);
		// logger.log("02 buildSide id=" + id + " startCol=" + startCol + " startRow=" + startRow);
		side.addContents(layout, startCol * sideLength, startRow * sideLength);
		// logger.log("03 buildSide id=" + id + " startCol=" + startCol + " startRow=" + startRow);
		return side;
	}

	// Zero based. 0-5.
	private int currentSide = 0;

	// One based. 1-6.
	int getCurrentSideNo() {
		return currentSide + 1;
	}

	// newSideNo is one based. 1-6.
	// newDirection can be 'R', 'D', 'L', 'U'.
	//
	// The last coordinates are passed to the new side which will set one of them to 0
	// and use the other. However, the orientation of the two sides may mean the
	// coordinate which is used may need to be reversed.
	//
	// For example, on a 4*4 cube each axis can have values 0-3. The mapping between
	// two axes can be 0-3:0-3 or 0-3:3-0 so 1 can map to 1 or 2. The boolean reverseMapping
	// specifies whether this must be done.
	//
	// A concrete example is moving down from 3,3 on side 6 to side 2. The landing place
	// on side 6 is 0,0 not 3,0.
	void changeCurrentSideTo(int newSideNo, char newDirection, String mapping) throws Exception {
		Side lastSide = getCurrentSide();
		int col = lastSide.currentCol;
		int row = lastSide.currentRow;
		int maxSideNo = sideLength - 1;

		logger.log("changeCurrentSideTo() newSideNo=" + newSideNo + " newDirection=" + newDirection + " mapping=" + mapping);
		switch (mapping) {
			case "BOTTOMLEFT->BOTTOMLEFT:BOTTOMRIGHT->TOPLEFT":
				col = maxSideNo - row;
				row = 0;
				break;
			case "BOTTOMLEFT->BOTTOMRIGHT:BOTTOMRIGHT->BOTTOMLEFT":
				row = maxSideNo - row;
				break;
			case "BOTTOMLEFT->BOTTOMRIGHT:BOTTOMRIGHT->TOPLEFT":
				col = maxSideNo - row;
				row = 0;
				break;
			case "BOTTOMLEFT->TOPLEFT:BOTTOMRIGHT->TOPRIGHT":
				col = maxSideNo - col;
				break;
			case "BOTTOMLEFT->TOPRIGHT:BOTTOMRIGHT->BOTTOMRIGHT":
				col = row;
				row = maxSideNo - row;
				break;
			case "BOTTOMLEFT->TOPRIGHT:BOTTOMRIGHT->TOPLEFT":
				row = maxSideNo - row;
				break;
			case "TOPLEFT->BOTTOMLEFT:TOPRIGHT->BOTTOMRIGHT":
				row = row;
				col = maxSideNo;
				break;
			case "TOPLEFT->BOTTOMLEFT:BOTTOMLEFT->TOPLEFT":
				row = 0;
				col = maxSideNo - col;
				break;
			case "TOPLEFT->BOTTOMRIGHT:BOTTOMLEFT->BOTTOMLEFT":
				row = maxSideNo - col;
				col = maxSideNo;
				break;
			case "TOPLEFT->BOTTOMRIGHT:TOPRIGHT->TOPRIGHT":
				col = maxSideNo - row;
				row = maxSideNo;
				break;
			case "TOPLEFT->TOPLEFT:BOTTOMLEFT->TOPRIGHT":
				row = col;
				col = 0;
				break;
			case "TOPLEFT->TOPLEFT:TOPRIGHT->BOTTOMLEFT":
				col = row;
				row = 0;
				break;
			case "TOPLEFT->TOPRIGHT:BOTTOMLEFT->BOTTOMRIGHT":
				col = col;
				row = maxSideNo - row;
				break;
			case "TOPLEFT->TOPRIGHT:TOPRIGHT->TOPLEFT":
				row = maxSideNo - row;
				break;
			case "TOPRIGHT->TOPRIGHT:BOTTOMRIGHT->TOPLEFT":
				logger.log("TOPRIGHT->TOPRIGHT:BOTTOMRIGHT->TOPLEFT");
				row = maxSideNo - col;
				col = 0;
				break;
			case "TOPRIGHT->BOTTOMRIGHT:BOTTOMRIGHT->TOPRIGHT":
				col = maxSideNo - col;
				break;
			case "TOPRIGHT->BOTTOMLEFT:BOTTOMRIGHT->BOTTOMRIGHT":
				row = col;
				col = maxSideNo;
				break;
			case "TOPRIGHT->TOPLEFT:BOTTOMRIGHT->BOTTOMLEFT":
				row = maxSideNo - row;
				break;
			case "NOMAPPING":
				// Useful when testing.
				break;
			default:
				throw new Exception("changeCurrentSideTo() mapping " + mapping + " not found");
		}

		currentSide = newSideNo - 1;
		logger.log("About to call side no " + newSideNo + " enterSide with newDirection=" + newDirection + " col=" + col + " row=" + row); 
		getCurrentSide().enterSide(newDirection, col, row);
		logger.log("New content " + getCurrentSide().getContent());
		if (getCurrentSide().getContent() == WALL) {
			logger.log("Changing sides would hit a wall straight away. Revert.");
			currentSide = lastSide.id - 1;
		}
		logger.log("End changeCurrentSideTo() current side=" + getCurrentSideNo());
	}

	Side getCurrentSide() {
		// logger.log("getCurrentSide() size=" + sides.size() + " currentSide=" + currentSide);
		return sides.get(currentSide);
	}

	// The new direction is always set based on the current direction.
	// When facing left, an instruction to turn left results in a new direction of down.
	// When facing right, an instruction to turn left results in a new direction of up.
	void setDirection(String directionToTurn) {

		logger.log("setDirection() directionToTurn=" + directionToTurn + " currentDirection=" + getCurrentSide().currentDirection);
		Side currentSide = getCurrentSide();
		char currentDirection = currentSide.currentDirection;

		if (directionToTurn.equals("R")) {
			switch(currentDirection) {
				case 'U':
					currentSide.currentDirection = 'R';
					return;
				case 'R':
					currentSide.currentDirection = 'D';
					return;
				case 'D':
					currentSide.currentDirection = 'L';
					return;
				case 'L':
					currentSide.currentDirection = 'U';
					return;
			}
		}

		if (directionToTurn.equals("L")) {
			switch(currentDirection) {
				case 'U':
					currentSide.currentDirection = 'L';
					return;
				case 'L':
					logger.log("setDirection() Set currentSide.currentDirection =D");
					currentSide.currentDirection = 'D';
					return;
				case 'D':
					currentSide.currentDirection = 'R';
					return;
				case 'R':
					currentSide.currentDirection = 'U';
					return;
			}
		}
	}


	void move(int distance) throws Exception {
		int remaining = distance;
		logger.log("move() Start of move. remaining=" + remaining);
		while (remaining > 0) {
			char content = getCurrentSide().move();
			logger.log("move() remaining=" + remaining + " content=" + content);
			switch (content) {
				case TILE:
					remaining--;
					break;
				case WALL:
					return;
				case NOTHING:
					remaining--;
					logger.log("NOTHING remaining=" + remaining);
					changeSides();
					break;
			}
		}
		logger.log("move() End of move. remaining=" + remaining);
		getCurrentSide().logSide();
	}

	void changeSides() throws Exception {
		logger.log("changeSides() current side=" + getCurrentSideNo() + " currentDirection " + getCurrentSide().currentDirection);
		switch (getCurrentSide().currentDirection) {
			case 'R':
				nextSideRight();
				break;
			case 'D':
				nextSideDown();
				break;
			case 'L':
				nextSideLeft();
				break;
			case 'U':
				nextSideUp();
				break;
		}
	}

	void nextSideRight() throws Exception {
	}	

	void nextSideDown() throws Exception {
	}	

	void nextSideLeft() throws Exception {
	}	

	void nextSideUp() throws Exception {
	}	
}
