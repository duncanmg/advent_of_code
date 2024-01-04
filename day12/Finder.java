import java.io.IOException;
import java.util.*;
import java.math.BigInteger;

 class Finder {

   private String fileName;

   public static void main(String[] args) throws Exception {
	   if (args.length > 0) {
         Finder obj = new Finder(args[0]);
	   } 
	   else {
		   Finder obj = new Finder("data.txt");
	   }
   }

   public Finder(String fName) throws Exception {
	 fileName = fName;
     run();
   }

   public Finder() throws Exception {
     run();
   }

   public ArrayList<ArrayList<Position>> squares = new ArrayList<ArrayList<Position>>(0);

   Grid grid;

   void run() throws Exception {
	   Grid grid = new Grid(this.fileName);
	   this.grid = grid;
	   this.squares = grid.squares;
	   int minSteps = 1000000;
	   for (int i=0; i<grid.squares.size(); i++) {
		   ArrayList<Position> row = grid.squares.get(i);
		   for (int j=0; j<row.size(); j++) {
			   Position p = row.get(j);
			   if ( p.character == 'a') {
				   Grid g = new Grid(this.fileName);
				   Position startPos = g.squares.get(i).get(j);
				   int steps;
				   try {
				    steps = startPos.getShortestCompleteRoute(g);
				   }
			       catch (Exception e) {
					   continue;
				   }	   
				   if (steps < minSteps) {
					   minSteps = steps;
				   }
			   }
		   }	  
	   }
	   System.out.println("Shortest Route " + minSteps);
   }

   void search(Position startPos) throws Exception {
     
   }

 }
