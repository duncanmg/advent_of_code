import java.io.IOException;
import java.util.*;

class Layout {

	public static void main(String[] args) {
		Layout obj = new Layout();
	}

	public Layout() {
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

	int minCol = 0;

	int maxCol = 0;

	int minRow = 0;

	int maxRow = 0;

	// Missing coordinates within these bounds are treated as NOTHING.
	boolean withinBounds(int col, int row) {
		if (col < minCol || col > maxCol) {
			return false;
		}
		if (row < minRow || row > maxRow) {
			return false;
		}
		return true;
	}

	void parseData(ArrayList<String> data) {

		for (String line : data) {
			logger.log("line: " + line);
			ArrayList<Character> row = new ArrayList<Character>();
			for (int i=0; i<line.length(); i++) {
				switch(line.charAt(i)) {
					case WALL:
						row.add(WALL);
						break;
					case TILE:
						row.add(TILE);
						break;
					case ' ':
						row.add(NOTHING);
						break;
				}
			}
			layout.add(row);
			maxCol++;
			if (row.size() > maxRow) {
				maxRow = row.size();
			}
		}

	}

	int directionScore() {
		switch(currentDirection) {
			case "R":
				return RIGHT;
			case "D":
				return DOWN;
			case "L":
				return LEFT;
			case "U":
				return UP;
		}
		return -1;
	}

	int currentRowPos = 0;

	int currentColumnPos = 0;

	String currentDirection = "R";

	char getContents(int  col, int row) throws Exception{
		try {
		logger.log("getContents col " + col + " row " + row + " direction=" + currentDirection);
		logger.log("col size: " + layout.get(col).size());
		logger.log("row size: " + layout.get(col).size());
		char contents = layout.get(col).get(row);
		logger.log("getContents contents " + String.valueOf(contents));
		return contents;
		}
		catch(IndexOutOfBoundsException e) {
			if (withinBounds(col, row)) {
				return NOTHING;
			}
			throw e;
		}
	}

	int getNextRight() {
		int next = currentRowPos + 1;
		// logger.log("next " + next + " currentColumnPos.size() " + layout.get(currentColumnPos).size());
		return next >= layout.get(currentColumnPos).size() ? 0 : next;
	}

	int getNextDown() {
		int next = currentColumnPos + 1;
		return next >= layout.get(currentRowPos).size() ? 0 : next;
	}

	int getNextLeft() {
		int next = currentRowPos - 1;
		return next < 0 ? layout.get(currentColumnPos).size() - 1 : next;
	}

	int getNextUp() {
		int next = currentColumnPos - 1;
		return next < 0 ? layout.get(currentRowPos).size() - 1 : next;
	}

	void move(int distance) throws Exception {
		switch (currentDirection) {
			case "R":
				moveRight(distance);
				break;
			case "D":
				moveDown(distance);
				break;
			case "L":
				moveLeft(distance);
				break;
			case "U":
				moveUp(distance);
				break;
		}
	}

	// The new direction is always set based on the current direction.
	// When facing left, an instruction to turn left results in a new direction of down.
	// When facing right, an instruction to turn left results in a new direction of up.
	void setDirection(String directionToTurn) {

		if (directionToTurn.equals("R")) {
			switch(currentDirection) {
				case "U":
					currentDirection = "R";
					return;
				case "R":
					currentDirection = "D";
					return;
				case "D":
					currentDirection = "L";
					return;
				case "L":
					currentDirection = "U";
					return;
			}
		}

		if (directionToTurn.equals("L")) {
			switch(currentDirection) {
				case "U":
					currentDirection = "L";
					return;
				case "L":
					currentDirection = "D";
					return;
				case "D":
					currentDirection = "R";
					return;
				case "R":
					currentDirection = "U";
					return;
			}
		}
	}

	void moveRight(int distance) throws Exception {
		int i = 0;
		logger.log("Start moveRight. currentRowPos=" + currentRowPos + " distance=" + distance);
		while (i<distance) {
			int newRowPos = getNextRight();
			char contents = getContents(currentColumnPos, newRowPos);
			logger.log("contents=" + contents);
			switch (contents) {
				case TILE:
					currentRowPos = newRowPos;
					i++;
					// logger.log("TILE. Set currentRowPos=" + currentRowPos + " Set i=" + i);
					break;
				case WALL:
					// logger.log("WALL. Set currentRowPos=" + currentRowPos + " i=" + i);
					if (getContents(currentColumnPos, currentRowPos) == NOTHING) {
						moveLeft(1);
					}
					return;
				case NOTHING:
					currentRowPos = newRowPos;
					// logger.log("NOTHING. Set currentRowPos=" + currentRowPos + " i=" + i);
					break;
				default:
					logger.log("Default!!. currentRowPos=" + currentRowPos + " i=" + i + " contents=" + contents);
			}
		}
		logger.log("End moveRight. currentRowPos=" + currentRowPos + " i=" + i);
	}

	void moveDown(int distance) throws Exception {
		int i = 0;
		while (i<distance) {
			int newColumnPos = getNextDown();
			char contents = getContents(newColumnPos, currentRowPos);
			logger.log("moveDown contents=" + contents + " newColumnPos= " + newColumnPos);
			switch (contents) {
				case TILE:
					currentColumnPos = newColumnPos;
					i++;
					break;
				case WALL:
					if (getContents(currentColumnPos, currentRowPos) == NOTHING) {
						moveUp(1);
					}
					return;
				case NOTHING:
					currentColumnPos = newColumnPos;
					break;
			}
		}
	}

	void moveLeft(int distance) throws Exception {
		int i = 0;
		while (i<distance) {
			int newRowPos = getNextLeft();
			char contents = getContents(currentColumnPos, newRowPos);
			switch (contents) {
				case TILE:
					currentRowPos = newRowPos;
					i++;
					break;
				case WALL:
					if (getContents(currentColumnPos, currentRowPos) == NOTHING) {
						moveRight(1);
					}
					return;
				case NOTHING:
					currentRowPos = newRowPos;
					break;
			}
		}
	}

	void moveUp(int distance) throws Exception {
		int i = 0;
		while (i<distance) {
			int newColumnPos = getNextUp();
			char contents = getContents(newColumnPos, currentRowPos);
			switch (contents) {
				case TILE:
					currentColumnPos = newColumnPos;
					i++;
					break;
				case WALL:
					if (getContents(currentColumnPos, currentRowPos) == NOTHING) {
						moveDown(1);
					}
					return;
				case NOTHING:
					currentColumnPos = newColumnPos;
					break;
			}
		}
	}

	// Assume the origin is in column zero. However, row zero might be NOTHING. Move
	// right until a TILE is found.
	void moveToOrigin() throws Exception {
		currentColumnPos = 0;
		currentRowPos = 0;

		logger.log("01 Start moveToOrigin() currentRowPos=" + currentRowPos);
		while (getContents(currentColumnPos, currentRowPos) != TILE) {
			// logger.log("02 YYYYY " + String.valueOf(getContents(currentColumnPos, currentRowPos)));
			moveRight(1);
		}
		logger.log("03 End moveToOrigin() currentRowPos=" + currentRowPos);
	}
}
