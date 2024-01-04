// 22 Dec 2022 v0.02. Still does part 1, but improved by making visit return a Position object.
import java.io.IOException;
 import java.util.*;

 class Director {

   public static void main(String[] args) throws Exception {
     System.out.println(args.length);
     if (args.length == 1) {
       Director obj = new Director(args[0]);
     } else {
       Director obj = new Director();
     }
   }

   private Data dataObj;

   private ArrayList < String > data;

   public Director() throws Exception {
     dataObj = new Data();
     data = dataObj.data;
     getDirector();
   }

   public Director(String file) throws Exception {
     dataObj = new Data(file);
     data = dataObj.data;
     getDirector();
   }

   public Director(String file, int num) throws Exception {
     dataObj = new Data(file);
     data = dataObj.data;
	 numGrids = num;
     getDirector();
   }

   private int numGrids = 9;

   public ArrayList<Grid> grids = new ArrayList<Grid>(0);

   void getDirector() throws Exception {

     ArrayList<Grid> grids = this.grids;
	 for (int i=0; i<this.numGrids; i++) {
		 grids.add(new Grid());
	 }

     System.out.println("Lines to process=" + this.data.size());
     for (int i = 0; i < data.size(); i++) {

       String line = this.data.get(i);
	   Move m = new Move(line);
	   for (int j=0; j<m.expandedAmount.size(); j++) {
		 System.out.println("1 ============================================++");
		 grids.get(0).moveRope(new Move(m.type, m.expandedAmount.get(j)));
		 System.out.println("2 ============================================++");
         for (int k=1; k<grids.size(); k++) {
			 grids.get(k).head = grids.get(k-1).tail.clone();
			 grids.get(k).moveTail(new Move(m.type, 1));
		 }	 
	   }
	   // break;
     }

	 Grid lastGrid = grids.get(grids.size()-1);
     System.out.println("Number of positions=" + lastGrid.numPositions() + ". Number of positions visited by tail=" + lastGrid.numVisitedByTail());
     // System.out.println("Final head: " + grid.getPosition(grid.head) + " Final tail: " + grid.getPosition(grid.tail));

     System.out.println("===============================================");
   }
 }
