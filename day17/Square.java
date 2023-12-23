import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// 2 * 2 square
// ##
// ##

class Square extends Rock {

	public static void main(String[] args) {
		Square obj = new Square();
		System.out.println("Square object created");
	}

	public Square(Chamber chamber) throws Exception {
		this.addCoord(chamber.startX, chamber.startY);
		this.addCoord(chamber.startX, chamber.startY+1);
		this.addCoord(chamber.startX+1, chamber.startY);
		this.addCoord(chamber.startX+1, chamber.startY+1);
		label = 'q';
	}

	public Square() {
	}

	public Square(ArrayList<Coords> coords) throws Exception {
		for (int i=0; i<coords.size(); i++) {
			Coords current = coords.get(i);
			this.coords.add(new Coords(current.x, current.y));
		}
		label = 'q';
	}


}
