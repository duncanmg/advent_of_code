 import java.io.IOException;
 import java.util.*;
 import java.math.BigInteger;

 class Selector {

   public static void main(String[] args) throws Exception {
     Selector obj = new Selector();
   }

   // Constructors
   public Selector(Grid g, Position c) throws Exception {

	   grid = g;
	   current = c;
   }
   public Selector() throws Exception {
   }

   // Attributes
   public Boolean visited = false;

   Grid grid;

   Position current;

   Boolean beenUp = false;
   Boolean beenDown = false;
   Boolean beenLeft = false;
   Boolean beenRight = false;

   public String msg;

   public Boolean verbose = true;

   // Methods
   ArrayList<Position> getNeighbours() throws Exception {

	 ArrayList<Position> neighbours = new ArrayList<Position>(0);

	 if (! this.beenUp && this.searchUp()) {
		 neighbours.add(this.nextPositionUp());
	 }
	 if (! this.beenDown && this.searchDown()) {
		 neighbours.add(this.nextPositionDown());
	 }
	 if (! this.beenLeft && this.searchLeft()) {
		 neighbours.add(this.nextPositionLeft());
	 }
	 if (! this.beenRight && this.searchRight()) {
		 neighbours.add(this.nextPositionRight());
	 }
	 System.out.println(current + " has " + neighbours.size() + " neighbours. " + neighbours);
     return neighbours;

   }

   Boolean isTopEdge() {
      if (current.row + 1 >= current.numRows) {
        return true;
      }
	  this.msg = current + " is at the top edge";
      return false;
   }

   Boolean isBottomEdge() {
      if (current.row <= 0) {
        return true;
      }
	  this.msg = current + " is at the bottom edge";
      return false;
   }

   Boolean isLeftEdge() {
      if (current.col <= 0) {
        return true;
      }
	  this.msg = current + " is at the left edge";
      return false;
   }

   Boolean isRightEdge() {
      if (current.col+1 >= current.numCols) {
        return true;
      }
	  this.msg = current + " is at the right edge";
      return false;
   }

   Position nextPositionUp() {
     return this.grid.squares.get(this.current.row + 1).get(this.current.col);
   }

   Position nextPositionDown() {
     return this.grid.squares.get(this.current.row - 1).get(this.current.col);
   }

   Position nextPositionLeft() {
     return this.grid.squares.get(this.current.row).get(this.current.col - 1);
   }

   Position nextPositionRight() {
     return this.grid.squares.get(this.current.row).get(this.current.col + 1);
   }

   Boolean searchPos(Position next) throws Exception {
     if (next.value - current.value > 1) {
	   this.msg = this.current + " cannot go to " + next + " because the gradient is " + (next.value - current.value);
	   this.log(msg);
       return false;
     }
	 this.log("The gradient from " +  this.current + " to " + next + " is " + (next.value - current.value));
     if (next.visited) {
	   this.msg = this.current + " cannot go to " + next + " because it has been visited";
	   this.log(msg);
       return false;
     }
	 return true;
   }

   Boolean searchUp() throws Exception {
	 if (this.isTopEdge()) {
		 this.log(current + " is at the top edge.");
		 return false;
	 }
     return this.searchPos(this.nextPositionUp()); 
   }

   Boolean searchDown() throws Exception {
	 if (this.isBottomEdge()) {
		 this.log(current + " is at the bottom edge.");
		 return false;
	 }
     return this.searchPos(this.nextPositionDown()); 
   }

   Boolean searchLeft() throws Exception {
	 if (this.isLeftEdge()) {
		 this.log(current + " is at the left edge.");
		 return false;
	 }
     return this.searchPos(this.nextPositionLeft()); 
   }

   Boolean searchRight() throws Exception {
	 if (this.isRightEdge()) {
		 this.log(current + " is at the right edge.");
		 return false;
	 }
     return this.searchPos(this.nextPositionRight()); 
   }

   void log(String msg) {
	   if (this.verbose) {
		   System.out.println(msg);
	   }
   }

 }
