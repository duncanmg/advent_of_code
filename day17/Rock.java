import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Rock {

	public static void main(String[] args) {
		Rock obj = new Rock();
	}

	public Rock() {
	}

	public Rock(Rock r) throws Exception {
		ArrayList<Coords> coords = r.coords;
		for (int i=0; i<coords.size(); i++) {
			Coords current = coords.get(i);
			this.coords.add(new Coords(current.x, current.y));
		}
		label = r.label;
	}

	char label = '?';

	public int size() {
		return this.coords.size();
	}

	public Logger logger = new Logger(this);

	ArrayList<Coords> coords = new ArrayList<Coords>(0);

	ArrayList<Coords> previousCoords = new ArrayList<Coords>(0);

	void addCoord(int x, int y) throws Exception {
		this.coords.add(new Coords(x, y));
	}

	void removeCoord(int x, int y) throws Exception {
		for (int i=0; i<this.coords.size(); i++) {
			Coords current = this.coords.get(i);
			if (current.isEqual(new Coords(x, y))) {
				this.coords.remove(i);
				break;
			}
		}
	}

	void moveLeft() throws Exception {
		ArrayList<Coords> newCoords = new ArrayList<Coords>();
		for (int i=0; i<this.coords.size(); i++) {
			Coords current = this.coords.get(i);
			newCoords.add(current.moveX(-1));
		}
		this.coords = newCoords;
	}

	void moveRight() throws Exception {
		ArrayList<Coords> newCoords = new ArrayList<Coords>();
		for (int i=0; i<this.coords.size(); i++) {
			Coords current = this.coords.get(i);
			newCoords.add(current.moveX(+1));
		}
		this.coords = newCoords;
	}

	void moveDown() throws Exception {
		ArrayList<Coords> newCoords = new ArrayList<Coords>();
		for (int i=0; i<this.coords.size(); i++) {
			Coords current = this.coords.get(i);
			newCoords.add(current.moveY(-1));
		}
		this.coords = newCoords;
	}

	void moveUp() throws Exception {
		ArrayList<Coords> newCoords = new ArrayList<Coords>();
		for (int i=0; i<this.coords.size(); i++) {
			Coords current = this.coords.get(i);
			newCoords.add(current.moveY(1));
		}
		this.coords = newCoords;
	}

	boolean overlapsWith(Rock rock) {
		for (int i=0; i<rock.coords.size(); i++) {
			for (int j=0; j< this.coords.size(); j++) {
				if (rock.coords.get(i).isEqual(this.coords.get(j))) {
					return true;
				}
			}
		}
		return false;
	}

	boolean covers(Rock rock) {
		for (int i=0; i<rock.coords.size(); i++) {
			for (int j=0; j< this.coords.size(); j++) {
				if (!rock.coords.get(i).isEqual(this.coords.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	boolean hasSameXCoords(Rock r) {
		// System.out.println("hasSameXCoordsxx " + this.label + " " + r.label);
		// System.out.println("hasSameXCoordsxx " + this.toString() + " " + r.toString());
		if (this.label != r.label) {
			// System.out.println("hasSameXCoords different types. Return false");
			return false;
		}
		for (int i=0; i<this.coords.size(); i++) {
		// System.out.println("hasSameXCoords i=" + i);
			if (this.coords.get(i).x != r.coords.get(i).x) {
				// System.out.println("Return false");
				return false;
			}
		}
				// System.out.println("Return true");
		return true;
	}

	public int maxY() {
		int max = 0;
		for (int i=0; i<this.coords.size(); i++) {
			int y = this.coords.get(i).y;
			if (y > max) {
				max = y;
			}
		}
		return max;
	}

	public String toString() {
		return this.getClass().getName() + " " + this.coords;
	}

}
