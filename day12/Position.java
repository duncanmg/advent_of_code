 import java.io.IOException;
 import java.util.*;
 import java.math.BigInteger;

 class Position {

   public static void main(String[] args) throws Exception {
     Position obj = new Position();
   }

   // Constructors
   public Position(int r, int c, int v, char ch) throws Exception {

     row = r;
     col = c;
     value = v;
     character = ch;

   }

   public Position() throws Exception {
     row = 0;
     col = 0;
     value = 0;
     numRows = 0;
     numCols = 0;
   }

   // Attributes
   public final int value;

   public final int row;

   public final int col;

   public Boolean visited = false;

   public Boolean completed = false;

   int numRows;

   int numCols;

   char character;

   int steps = 0;

   Grid grid;

   public ArrayList<Position> shortestRoute = new ArrayList<Position>(0);

   // Methods
   int getShortestCompleteRoute(Grid g) throws Exception {
	     
         System.out.println("Start. row: " + this.row + " col: " + this.col + " character " + this.character + " value " + value);

		 this.numRows = g.squares.size();
		 this.numCols = g.squares.get(0).size();

	     this.visited = true;

		 if (this.character == 'E') {
			 System.out.println("End found after " + this.steps + " steps");
		     return this.steps;
		 }


		 int queueNo = 0;
		 ArrayList<ArrayList<Position>> queues = new ArrayList<ArrayList<Position>>(0);
		 queues.add(new ArrayList<Position>(0));
		 queues.add(new ArrayList<Position>(0));

	     Selector selector = new Selector(g, this);
	     ArrayList<Position> neighbours = selector.getNeighbours();
		 queues.get(0).addAll(neighbours);
         System.out.println("neighbours: " + queues);
         System.out.println("steps: " + this.steps);
		 while (queues.get(0).size() > 0 || queues.get(1).size() > 0) {

             this.steps++;
			 System.out.println("steps: " + this.steps + " queueNo: " + queueNo + " queues: " + queues);
			 while(queues.get(queueNo).size() > 0){

				 Position current = queues.get(queueNo).get(0);
				 queues.get(queueNo).remove(0);
				 if (current.visited) {
					 continue;
				 }
				 current.numRows = this.numRows;
				 current.numCols = this.numCols;
				 current.visited = true;
                 System.out.println("Start. step: " + this.steps + " current: " + current);


		         if (current.character == 'E') {
			       System.out.println("End found after " + this.steps + " steps");
		           return this.steps;
		         }

				 int nextQueueNo = queueNo == 0 ? 1 : 0;

				 selector = new Selector(g, current);
				 queues.get(nextQueueNo).addAll(selector.getNeighbours());
			 }
			 queueNo = queueNo == 0 ? 1 : 0;

		 }
		 throw new Exception("No routes found");
	 }

   @Override
   public String toString() {
	   return "row: " + this.row + " col: " + this.col + " char: " + this.character;
   }
 }
