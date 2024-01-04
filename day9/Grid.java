import java.io.IOException;
import java.util.*;
import org.junit.Test;

// Implements a sparse list of lists.
class Grid {

  public static void main(String[] args) throws Exception {
    Grid obj = new Grid();
  }

  public Grid() throws Exception {
    Position root = visit(new Position(0,0), "head");
    root = visit(new Position(0,0), "tail");
    head = root.clone();
    tail = head.clone();
  }

  public Grid(int r, int c) throws Exception {
    head = visit(new Position(r, c), "head").clone();
    tail = visit(new Position(r, c), "tail").clone();
  }
  private ArrayList < Position > positions = new ArrayList < Position > (0);

  public Position visit(Position p, String which) throws Exception {
    Boolean found = false;
    Position  newPos = new Position(0,0);
    for (int i = 0; i < this.positions.size(); i++) {
      Position pi = this.positions.get(i);
      if (pi.equals(p)) {
        newPos = pi;
		found = true;
        break;
      }
    }

    if (found == false) {
      this.positions.add(new Position(p));
      newPos = this.positions.get(this.positions.size()-1);
    }
    if (which.equals("head")) {
      newPos.headVisits++;
    }
    else if (which.equals("tail")) {
      newPos.tailVisits++;
    }
    else {
      throw new Exception("Parameter 'which' must be 'head' or 'tail'");
    }
    System.out.println(which + " visited " + newPos.row + ", " + newPos.col + " numVisitedBy Tail " + this.numVisitedByTail());
    // System.out.println("headVisits=" + newPos.headVisits + " tailVisits=" + newPos.tailVisits);
    return newPos.clone();
  }

  public Integer numPositions() {
    return this.positions.size();
  }

  public Position head;

  public Position tail;

  // public ArrayList<Knot> knots = new ArrayList<Knot>(0);

  public void moveRope(Move move) throws Exception {

	  System.out.println(move);
	for (int i = 0; i<move.amount; i++) {
       this.moveHeadOnce(move);
           this.moveTail(move);
       }
	   // System.out.println("(" + i + ") move: " + move + " === head:" + this.head + " tail:" + this.tail);
    // System.out.println("Position of tail: " + this.positions.get(this.tail).row + ", " + this.positions.get(this.tail).col);
  }

  private void moveHeadOnce(Move move) throws Exception {
    Position currentHead = this.head.clone();
    int newRow = 0;
    int newCol = 0;
    String msg = "Moved head from " + currentHead.row + " " + currentHead.col + " to ";

    Position newHead;
    switch (move.type) {
    case "U":
      newRow = currentHead.row + 1;
      newHead = this.visit(new Position(newRow, currentHead.col), "head");
      break;
    case "D":
      newRow = currentHead.row - 1;
      newHead = this.visit(new Position(newRow, currentHead.col), "head");
      break;
    case "R":
      newCol = currentHead.col + 1;
      newHead = this.visit(new Position(currentHead.row, newCol), "head");
      break;
    case "L":
      newCol = currentHead.col - 1;
      newHead = this.visit(new Position(currentHead.row, newCol), "head");
      break;
	default:
	  throw new Exception("Invalid move");
    }
    this.head = newHead;
	System.out.println(msg + newHead.row + " " + newHead.col);
  }

  public void moveTail(Move move) throws Exception {

    Position currentHead = this.head;
    Position currentTail = this.tail;

    String msg = "Moved tail from " + currentTail.row + " " + currentTail.col + " to ";

    int rowChange = 0;
    int colChange = 0;

    Integer newIndex = -1;
	// Simple comparison of Integer objects doesn't always work. Hence .intValue().
	if (!currentHead.touches(currentTail)) {
      if (currentHead.row.intValue() != currentTail.row.intValue()) {
        rowChange = (currentHead.row.intValue() > currentTail.row.intValue()) ? 1 : -1;
      }
      if (currentHead.col.intValue() != currentTail.col.intValue()) {
        colChange = (currentHead.col.intValue() > currentTail.col.intValue()) ? 1 : -1;
      }
      if (rowChange != 0 || colChange != 0) {
        this.tail = this.visit(new Position(currentTail.row + rowChange, currentTail.col + colChange), "tail");
		System.out.println(msg + this.tail.row + " " + this.tail.col);
      }
    }
  }

  public int numVisitedByTail() {
    int total = 0;
    for (int i = 0; i < this.positions.size(); i++) {
      int v = this.positions.get(i).tailVisits;
      if (v > 0) {
        total++;
      }
    }
    return total;
  }

  // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//  public void testVisit() throws Exception {
//    Grid g = new Grid();
//
//    if (g.numPositions() != 1 || g.numVisitedByTail() != 1) {
//      System.out.println("numPositions() and  g.numVisitedByTail() should be 1. " + g.numPositions() + " " + g.numVisitedByTail());
//    }
//
//    g.visit(1, 0, "head");
//    if (g.numPositions() != 2 || g.numVisitedByTail() != 1) {
//      System.out.println("numPositions() and  g.numVisitedByTail() should be 2 and 1. " + g.numPositions() + " " + g.numVisitedByTail());
//    }
//
//    g.visit(1, 0, "head");
//    if (g.numPositions() != 2 || g.numVisitedByTail() != 1) {
//      System.out.println("numPositions() and  g.numVisitedByTail() should still be 2 and 1. " + g.numPositions() + " " + g.numVisitedByTail());
//    }
//
//    g.visit(1, 1, "head");
//    if (g.numPositions() != 3 || g.numVisitedByTail() != 1) {
//      System.out.println("numPositions() and  g.numVisitedByTail() should be 3 and 1. " + g.numPositions() + " " + g.numVisitedByTail());
//    }
//
//    g.visit(1, 1, "head");
//    if (g.numPositions() != 3 || g.numVisitedByTail() != 1) {
//      System.out.println("numPositions() and  g.numVisitedByTail() should still be 3 and 1. " + g.numPositions() + " " + g.numVisitedByTail());
//    }
//
//    g.positions.get(0).tailVisits++;
//    if (g.numPositions() != 3 || g.numVisitedByTail() != 1) {
//      System.out.println("numPositions() and  g.numVisitedByTail() should still be 3 and 1. Root already visited. " + g.numPositions() + " " + g.numVisitedByTail());
//    }
//
//    g.positions.get(g.positions.size() - 1).tailVisits++;
//    if (g.numPositions() != 3 || g.numVisitedByTail() != 2) {
//      System.out.println("numPositions() and  g.numVisitedByTail() should be 3 and 2. " + g.numPositions() + " " + g.numVisitedByTail());
//    }
//  }
//
//  public void testMoveHeadOnce() throws Exception {
//    Grid g = new Grid();
//
//    g.moveHeadOnce(new Move("U 2"));
//    if (g.numPositions() != 2 || g.numVisitedByTail() != 1) {
//      System.out.println("positions,  tail visits should be 2, 1. " + g.numPositions() + " " + g.numVisitedByTail());
//    }
//
//    Position newPos = g.positions.get(g.numPositions() - 1);
//    if (newPos.row != 1 || newPos.col != 0 || newPos.headVisits != 1) {
//      System.out.println("New position should be 1, 0, 1. Got " + newPos.row + " " + newPos.col + " " + newPos.headVisits);
//    }
//
//  }

}

