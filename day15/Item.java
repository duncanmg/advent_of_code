import java.util.*;

// Super class.
class Item {

    public static void main(String[] args) {
		   Item obj = new Item(0,0);
     }

    public Item(Integer x1, Integer y1) {
		x = x1;
		y = y1;
    }

	final protected int x;
	final protected int y;

    public Item(Coords c) {
		x = c.x;
		y = c.y;
    }

	Coords coords(){
	   	return new Coords(this.x, this.y);
	}

	public String toString() {
		return this.x + "," + this.y;
	}
}
