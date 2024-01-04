import java.util.*;

class Coords {

    public static void main(String[] args) {
		   Coords obj = new Coords(0,0);
     }

	// Accepts "34,40"
    public Coords(String s) {
		String[] bits = s.split(",");
		xRight = Integer.parseInt(bits[0]);
		yDown = Integer.parseInt(bits[1]);
    }

	// Accepts "34","40"
    public Coords(String x, String y) {
		xRight = Integer.parseInt(x);
		yDown = Integer.parseInt(y);
    }

    public Coords(Integer x, Integer y) {
		xRight = x;
		yDown = y;
    }

	public final int xRight;

	public final int yDown;

	Boolean isEqual(Coords c) {
		if ( this.xRight == c.xRight && this.yDown == c.yDown) {
			return true;
		}
		else {
			return false;
		}
	}

	public String toString() {
		return "(" + this.xRight + "," + this.yDown + ")";
	}
}
