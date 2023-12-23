import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// 2 * 2 square
// ##
// ##

class Cross extends Rock {

	public static void main(String[] args) {
		Cross obj = new Cross();
		System.out.println("Cross object created");
	}

	public Cross(Chamber chamber) throws Exception {
		this.addCoord(chamber.startX+1, chamber.startY);
		this.addCoord(chamber.startX, chamber.startY+1);
		this.addCoord(chamber.startX+1, chamber.startY+1);
		this.addCoord(chamber.startX+2, chamber.startY+1);
		this.addCoord(chamber.startX+1, chamber.startY+2);
		label = 'c';
	}

	public Cross() {
	}


	public Cross(ArrayList<Coords> coords) throws Exception {
		for (int i=0; i<coords.size(); i++) {
			Coords current = coords.get(i);
			this.coords.add(new Coords(current.x, current.y));
		}
		label = 'c';
	}

}
