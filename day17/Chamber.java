import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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

	public Rock getFallingRock() {
		System.out.println("In getFallingRock() rocks.size=" + this.rocks.size());
		return this.rocks.get(this.rocks.size()-1);
	}

	// Highest row in the grid which has an occupied slot. Will only return the correct answer if the falling rock is at rest.
	public int getCurrentHeight() throws Exception{
		ArrayList<ArrayList<Character>> grid = this.buildGrid();
		int gridSize = grid.size();
		ArrayList<Character> emptyRow = this.getEmptyRow();

		if (gridSize > 0) {
			this.logger.log("getCurrentHeight() grid.size=" + grid.size() + " row=" + grid.get(gridSize-1) + " emptyRow=" + emptyRow.toString());
		}

		// Is this a valid check? Empty rows are added but immediately populated with the latest rock.
		// If the rock is not at rest then there may be an empty row, but it won't be the top one.
		// Could maybe check the top ten rows for an empty row. Even then, it would just be a guide.
		// The rock is not necessarily at rest because there aren't any empty rows.
		if (gridSize > 0 && grid.get(gridSize-1).equals(emptyRow) == true) {
			throw new Exception("getCurrentHeight() called when rock is not at rest.");
		}

		while (gridSize > 0 && grid.get(gridSize-1).equals(emptyRow) == true) {
			this.logger.log("Decrement gridSize");
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

	void show() throws Exception {
		System.out.println("In show(). numRocks=" + this.rocks.size() + " currentHeight=" + this.getCurrentHeight());

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

	void showRaw(String fileName) throws Exception {
		System.out.println("In showRaw(). numRocks=" + this.rocks.size() + " currentHeight=" + this.getCurrentHeight());

		ArrayList<ArrayList<Character>> grid = buildGrid();

		FileWriter writer = new FileWriter(fileName);
		Collections.reverse(grid);
		int rockNo = grid.size();
		for (int i=0; i<grid.size(); i++) {
			ArrayList<Character> chars = grid.get(i);
			String line = new String();
			for (int j=0; j<chars.size(); j++) {
				line = line + chars.get(j);
			}
			writer.write(i + "\t(" + rockNo + ")\t" + line + "\n");
			rockNo--;
		}
		writer.close();
	}

	ArrayList<ArrayList<Character>> grid = new ArrayList<ArrayList<Character>>();

	int lastNumRocks = 0;

	ArrayList<ArrayList<Character>> buildGrid() {
		int numRocks = this.rocks.size();
		this.logger.log("Start buildGrid() numRocks " + numRocks + " rocks. lastNumRocks " + this.lastNumRocks);
		if (numRocks == lastNumRocks) {
			return this.grid;
		}

		for (int i=this.rocks.size()-1; i>=this.lastNumRocks; i--) {
			Rock current = this.rocks.get(i);
			for (int j=0; j<current.size(); j++) {
				grid = addToGrid(grid, current.coords.get(j), current.label);
			}
		}
		this.lastNumRocks = numRocks;
		return grid;
	}

	// This accepts the coordinates of a rock and sets the appropriate position in the
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
