import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Chamber {

	public static void main(String[] args) {
		Chamber obj = new Chamber();
	}

	public Chamber() {
		this.logger.log("Chamber object created");
	}

	// ArrayList<ArrayList<Boolean>> positions = new ArrayList<ArrayList<Boolean>>(0);

	public final int startX = 2;

	public final int minX = 0;

	public final int maxX = 6;

	public int startY = 0;

	public int minY = 0;

	public ArrayList<Rock> rocks = new ArrayList<Rock>(0);

	// Highest row in the grid which has an occupied slot.
	public int getCurrentHeight() {
		ArrayList<ArrayList<Character>> grid = this.buildGrid();
		int gridSize = grid.size();
		ArrayList<Character> emptyRow = this.getEmptyRow();
		this.logger.log("01 getCurrentHeight " + grid.toString());
		if (gridSize > 0) {
			this.logger.log("getCurrentHeight() grid.size=" + grid.size() + " row=" + grid.get(gridSize-1) + " emptyRow=" + emptyRow.toString());
		}
		while (gridSize > 0 && grid.get(gridSize-1).equals(emptyRow) == true) {
			this.logger.log("Decrement gridSise");
			gridSize--;
		}
		this.logger.log("02 getCurrentHeight");
		return grid.size();
	}

	public Logger logger = new Logger(this, true);

	// Adds a new rock to the chamber.
	public void addRock(Rock rock) {
		this.rocks.add(rock);
	}

	// Accepts the latest version of the last rock to be added to the chamber
	// (the falling rock) and replaces the old version provided that it won't
	// overlap with another rock.
	public void updateFallingRock(Rock rock) throws RocksOverlapException {

		int indexOfFallingRock = this.rocks.size() - 1;

		// Check all positions are free. Don't include the
		// last rock which is the falling rock.
		for (int i=0; i<indexOfFallingRock; i++){

			// System.out.println("Does " + rock.coords.toString() + " overlap with " + this.rocks.get(i).coords.toString());
			if (this.rocks.get(i).overlapsWith(rock)) {
				throw new RocksOverlapException(rock.toString() + " will overlap with " + this.rocks.get(i).toString());
			}
			// System.out.println("No");
		}

		// System.out.println("Set " + indexOfFallingRock + " to " + rock.coords.toString());
		this.rocks.set(indexOfFallingRock, rock);

	}

	ArrayList<Boolean> createPosition() {
		ArrayList<Boolean> out = new ArrayList<Boolean>(0);
		for (int i=0; i<7; i++) {
			out.add(false);
		}
		return out;
	}

	void show() {
		this.logger.log("In show(). size=" + this.rocks.size() + " currentHeight=" + this.getCurrentHeight());

		ArrayList<ArrayList<Character>> grid = buildGrid();

		Collections.reverse(grid);
		int rockNo = grid.size();
		for (int i=0; i<grid.size(); i++) {
			ArrayList<Character> chars = grid.get(i);
			String line = new String();
			for (int j=0; j<chars.size(); j++) {
				line = line + chars.get(j);
			}
			this.logger.log(i + "\t(" + rockNo + ")\t" + line);
			rockNo--;
		}
	}

	ArrayList<ArrayList<Character>> buildGrid() {
		this.logger.log("Start buildGrid() " + this.rocks.size() + " rocks.");
		ArrayList<ArrayList<Character>> grid = new ArrayList<ArrayList<Character>>();
		for (int i=this.rocks.size()-1; i>=0; i--) {
			Rock current = this.rocks.get(i);
			for (int j=0; j<current.size(); j++) {
				grid = addToGrid(grid, current.coords.get(j), current.label);
			}
			// this.logger.log("In buildGrid() i=" + i + " rocks.size=" + this.rocks.size() + " grid.size=" + grid.size() + " current=" + current);
		}
		return grid;
	}

	// This accepts of of the coordinates of a rock and sets the appropriate position in the
	// grid to the label of the rock eg "v".
	//
	// It accepts three parameters:
	//
	// 	ArrayList<ArrayList<Character>> grid - The grid to which the rock will be added.
	// 	Coords 							coords - One of the coordinates of the rock being added.
	// 	char							label - The label of the rock being added eg "v".
	ArrayList<ArrayList<Character>> addToGrid(ArrayList<ArrayList<Character>> grid, Coords coords, char label) {

		// If the y coordinate is greater than the size of the grid
		// then one or more empty rows must be added.
		for (int i=grid.size()-1; i< coords.y; i++) {
			// Add a row of dots representing an empty line.
			grid.add(this.getEmptyRow());
		}
		// Set the position representing the coordinates to the value of the label eg "v".
		// this.logger.log("addToGrid. Setting " + coords.toString() + " to " + label);
		grid.get(coords.y).set(coords.x,label);
		return grid;
	}

	ArrayList<Character> getEmptyRow() {
		ArrayList<Character> emptyRow = new ArrayList<Character>(
				Arrays.asList('.','.','.','.','.','.','.')
				);
		return emptyRow;
	}
}
