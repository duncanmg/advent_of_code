import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// 2 * 2 square
// ##
// ##

class Step extends Rock {

	public static void main(String[] args) {
		Step obj = new Step();
		System.out.println("Step object created");
	}

	public Step(Chamber chamber) throws Exception {
		this.addCoord(chamber.startX, chamber.startY);
		this.addCoord(chamber.startX+1, chamber.startY);
		this.addCoord(chamber.startX+2, chamber.startY);
		this.addCoord(chamber.startX+2, chamber.startY+1);
		this.addCoord(chamber.startX+2, chamber.startY+2);
		label = 's';
	}

	public Step() {
	}

	public Step(ArrayList<Coords> coords) throws Exception {
		for (int i=0; i<coords.size(); i++) {
			Coords current = coords.get(i);
			this.coords.add(new Coords(current.x, current.y));
		}
		label = 's';
	}
	


}
