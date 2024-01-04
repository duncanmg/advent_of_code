import java.util.*;

class Beacon extends Item {

    public static void main(String[] args) {
		   Beacon obj = new Beacon(0,0);
     }

    public Beacon(Integer x1, Integer y1) {
		super(x1, y1);
    }

    public Beacon(Integer x1, Integer y1, Sensor nearest) {
		super(x1, y1);
		this.nearestSensor = nearest;
    }

    public Beacon(Coords c) {
		super(c.x, c.y);
    }

	Coords coords(){
	   	return new Coords(this.x, this.y);
	}

	public Sensor nearestSensor;

	public String toString() {
		return this.x + "," + this.y;
	}
}
