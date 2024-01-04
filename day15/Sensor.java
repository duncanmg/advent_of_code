import java.util.*;

class Sensor extends Item {

    public static void main(String[] args) {
		   Sensor obj = new Sensor(0,0);
     }

    public Sensor(Integer x1, Integer y1) {
		super(x1, y1);
    }

    public Sensor(Integer x1, Integer y1, Beacon nearest) {
		super(x1, y1);
		this.nearestBeacon = nearest;
    }

    public Sensor(Coords c) {
		super(c.x, c.y);
    }

	public Beacon nearestBeacon;

	// Manhatten distance. |x1 - x2| + |y1 - y2|
	public int getDistance(Beacon b) {
		return Math.abs(this.x - b.x) + Math.abs(this.y - b.y);
	}

	Coords coords(){
	   	return new Coords(this.x, this.y);
	}

	public String toString() {
		return this.x + "," + this.y;
	}
}
