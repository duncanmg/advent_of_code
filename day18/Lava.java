import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Boulder {

	public static void main(String[] args) {
		Boulder obj = new Boulder();
	}

	public Boulder() {
		this.x = 1;
		this.y = 1;
		this.z = 1;
		this.id = this.setId();
	}

	public Boulder(String line) {
		String[] coords = line.split(",");
		this.x = Integer.parseInt(coords[0]);
		this.y = Integer.parseInt(coords[1]);
		this.z = Integer.parseInt(coords[2]);
		this.id = this.setId();}

	public Boulder(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = this.setId();
	}

	final public int x;

	final public int y;

	final public int z;

	final String id;

	String setId() {
		return String.format("I%06d%06d%06d", x, y, z);
	}

	String getIdFromCoords(int x, int y, int z) {
		Boulder b = new Boulder(x, y, z);
		return b.id;
	}
	private ArrayList<Boulder> possibleNeighbours = new ArrayList<Boulder>();

	public ArrayList<Boulder> getPossibleNeighbours() {
		if (this.possibleNeighbours.size() == 0) {
			this.possibleNeighbours.add(new Boulder((this.x + 1), this.y, this.z));
			this.possibleNeighbours.add(new Boulder((this.x - 1), this.y, this.z));
			this.possibleNeighbours.add(new Boulder(this.x, (this.y + 1), this.z));
			this.possibleNeighbours.add(new Boulder(this.x, (this.y - 1), this.z));
			this.possibleNeighbours.add(new Boulder(this.x, this.y, (this.z + 1)));
			this.possibleNeighbours.add(new Boulder(this.x, this.y, (this.z - 1)));
		}
		return this.possibleNeighbours;
	}

}
