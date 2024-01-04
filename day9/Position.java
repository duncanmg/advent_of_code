import java.io.IOException;
import java.util.*;

class Position {

    public static void main(String[] args) {
           // Position obj = new Position(Forest grid, Integer height, Integer row, Integer col);
     }

    public Position(int r, int c) {
		row = r;
		col = c;
    }

    public Position(Position p) {
		row = p.row;
		col = p.col;;
		headVisits = p.headVisits;
		tailVisits = p.tailVisits;
    }

	public final Integer row;

	public final Integer col;

	public Integer headVisits = 0;

	public Integer tailVisits = 0;

	public Position clone() {
		return new Position(this);
	}

	public String toString() {
		return("row: " + this.row + " col: " + this.col + " headVisits: " + this.headVisits + " tailVisits: " + this.tailVisits);
	}

	public Boolean equals(Position p) {
		if (this.row.intValue() == p.row.intValue() && this.col.intValue() == p.col.intValue()) {
			return true;
		}
		else {
			return false;
		}
	}

	public Boolean touches(Position p) {
		if (this.equals(p)) {
			return true;
		}
	    else if (Math.abs(this.row.intValue() - p.row.intValue()) <= 1 && Math.abs(this.col.intValue() - p.col.intValue()) <= 1) {
			return true;
		}
		else {
			return false;
		}
	}
}
