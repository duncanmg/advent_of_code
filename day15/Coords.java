import java.util.*;

class Coords {

    public static void main(String[] args) {
		   Coords obj = new Coords(0,0);
     }

	// Accepts "34,40"
    public Coords(String s) {
		String[] bits = s.split(",");
		x = Integer.parseInt(bits[0]);
		y = Integer.parseInt(bits[1]);
    }

	// Accepts "34","40"
    public Coords(String x, String y) {
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
    }

    public Coords(Integer x, Integer y) {
		this.x = x;
		this.y = y;
    }

	public final int x;

	public final int y;

	Boolean isEqual(Coords c) {
		if ( this.x == c.x && this.y == c.y) {
			return true;
		}
		else {
			return false;
		}
	}

	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
}
