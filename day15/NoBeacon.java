import java.util.*;

class NoBeacon extends Item {

    public static void main(String[] args) {
		   NoBeacon obj = new NoBeacon(0,0);
     }

    public NoBeacon(Integer x1, Integer y1) {
		super(x1, y1);
    }

    public NoBeacon(Coords c) {
		super(c.x, c.y);
    }

	Coords coords(){
	   	return new Coords(this.x, this.y);
	}

	public String toString() {
		return this.x + "," + this.y;
	}
}
