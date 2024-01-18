import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Air extends Cube {

	public static void main(String[] args) {
		Air obj = new Air();
	}

	public Air() {
		super(1, 1, 1);
	}

	public Air(Cube cube) {
		super(cube);
	}

	public void addLavaNeighbour(Lava lava) {
		lavaNeighbours.add(lava);
		sidesCovered++;
	}

	public void addAirNeighbour(Air air) {
		airNeighbours.add(air);
	}

	ArrayList<Lava> lavaNeighbours = new ArrayList<Lava>();

	ArrayList<Air> airNeighbours = new ArrayList<Air>();

	// Number of sides covered by cubes.
	public int sidesCovered = 0;

	// Are all sides covered by cubes?
	public boolean isCovered() {
		return sidesCovered == 6;
	}

}
