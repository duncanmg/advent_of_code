import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Cube {

	public static void main(String[] args) {
		Cube obj = new Cube();
	}

	public Cube() {
		this.x = 1;
		this.y = 1;
		this.z = 1;
		this.id = this.setId();
	}

	public Cube(String line) {
		String[] coords = line.split(",");
		this.x = Integer.parseInt(coords[0]);
		this.y = Integer.parseInt(coords[1]);
		this.z = Integer.parseInt(coords[2]);
		this.id = this.setId();}

	public Cube(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = this.setId();
	}

	public Cube(Cube c) {
		this.x = c.x;
		this.y = c.y;
		this.z = c.z;
		this.id = this.setId();
	}

	final public int x;

	final public int y;

	final public int z;

	final String id;

	String setId() {
		return String.format("I%06d%06d%06d", x, y, z);
	}

	private ArrayList<Cube> possibleNeighbours = new ArrayList<Cube>();

	public ArrayList<Cube> getPossibleNeighbours() {
		if (this.possibleNeighbours.size() == 0) {
			this.possibleNeighbours.add(new Cube((this.x + 1), this.y, this.z));
			this.possibleNeighbours.add(new Cube((this.x - 1), this.y, this.z));
			this.possibleNeighbours.add(new Cube(this.x, (this.y + 1), this.z));
			this.possibleNeighbours.add(new Cube(this.x, (this.y - 1), this.z));
			this.possibleNeighbours.add(new Cube(this.x, this.y, (this.z + 1)));
			this.possibleNeighbours.add(new Cube(this.x, this.y, (this.z - 1)));
		}
		return this.possibleNeighbours;
	}

}
