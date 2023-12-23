import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// 2 * 2 square
// ##
// ##

class Horizontal extends Rock {

	public static void main(String[] args) {
		Horizontal obj = new Horizontal();
		System.out.println("Horizontal object created");
	}

	public Horizontal(Chamber chamber) throws Exception {
		chamber.logger.log("startX=" + chamber.startX + " startY=" + chamber.startY);
		this.addCoord(chamber.startX, chamber.startY);
		this.addCoord(chamber.startX+1, chamber.startY);
		this.addCoord(chamber.startX+2, chamber.startY);
		this.addCoord(chamber.startX+3, chamber.startY);
		label = 'h';
	}

	public Horizontal() {
	}

	public Horizontal(ArrayList<Coords> coords) throws Exception {
		for (int i=0; i<coords.size(); i++) {
			Coords current = coords.get(i);
			this.coords.add(new Coords(current.x, current.y));
		}
		label = 'h';
	}


}
