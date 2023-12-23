import java.util.*;

class Coords {

    public static void main(String[] args) throws Exception {
		   Coords obj = new Coords(0,0);
     }

	// Accepts "34,40"
    public Coords(String s) throws Exception {
		String[] bits = s.split(",");
		x = Integer.parseInt(bits[0]);
		y = Integer.parseInt(bits[1]);
		inRange();
    }

	// Accepts "34","40"
    public Coords(String x, String y) throws Exception {
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
		inRange();
    }

    public Coords(Integer x, Integer y) throws Exception {
		this.x = x;
		this.y = y;
		inRange();
    }

	public final int x;

	public final int y;

	final int minY = 0;

	final int minX = 0;

	final int maxX = 6;

	void inRange() throws RockOutOfBoundsException, RockHitFloorException {
		if (x < minX) {
			throw new RockOutOfBoundsException("x is less than the minimum: " + this.toString() + " " + minX);
		}
		if (x > maxX) {
			throw new RockOutOfBoundsException("x is greater than the maximum: " + this.toString() + " " + maxX);
		}
		if (y < minY) {
			throw new RockHitFloorException("y is less than the mninimum: " + this.toString() + " " + minY);
		}
	}

	Boolean isEqual(Coords c) {
		if ( this.x == c.x && this.y == c.y) {
			return true;
		}
		else {
			return false;
		}
	}

	Coords move(int deltaX, int deltaY) throws Exception {
		return new Coords(this.x + deltaX, this.y + deltaY);
	}

	Coords moveX(int deltaX) throws Exception {
		return new Coords(this.x + deltaX, this.y);
	}

	Coords moveY(int deltaY) throws Exception {
		return new Coords(this.x, this.y + deltaY);
	}

	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}
}
