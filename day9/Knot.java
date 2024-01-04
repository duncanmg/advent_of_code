import java.io.IOException;
import java.util.*;

class Knot {

    public static void main(String[] args) {
           // Knot obj = new Knot(Forest grid, Integer height, Integer row, Integer col);
     }

    public Knot() {
    }

	private ArrayList<Position> history = new ArrayList<Position>(0);

	public void addPosition(Position p) {
		this.history.add(p);
	}

	public Integer numPositionsVisited() {
		Grid g = new Grid();
		for (int i=0; i<this.history.size();i++){
			g.visit(this.history.get(i), "tail");
		}
		return g.numVisitedByTail();
	}
}
