import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// 2 * 2 square
// ##
// ##

class Vertical extends Rock {

	public static void main(String[] args) {
		Vertical obj = new Vertical();
		System.out.println("Vertical object created");
	}

	public Vertical(Chamber chamber) throws Exception {
		this.addCoord(chamber.startX, chamber.startY);
		this.addCoord(chamber.startX, chamber.startY+1);
		this.addCoord(chamber.startX, chamber.startY+2);
		this.addCoord(chamber.startX, chamber.startY+3);
		label = 'v';
	}

	public Vertical() {
	}

	public Vertical(ArrayList<Coords> coords) throws Exception {
		for (int i=0; i<coords.size(); i++) {
			Coords current = coords.get(i);
			this.coords.add(new Coords(current.x, current.y));
		}
		label = 'v';
	}

}
