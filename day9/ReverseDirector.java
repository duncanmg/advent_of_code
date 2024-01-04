import java.io.IOException;
 import java.util.*;

 class ReverseDirector {

   public static void main(String[] args) throws Exception {
     System.out.println(args.length);
     if (args.length == 1) {
       ReverseDirector obj = new ReverseDirector(args[0]);
     } else {
       ReverseDirector obj = new ReverseDirector();
     }
   }

   private Data dataObj;

   private ArrayList < String > data;

   public ReverseDirector() throws Exception {
     dataObj = new Data();
     data = dataObj.data;
     getReverseDirector();
   }

   public ReverseDirector(String file) throws Exception {
     dataObj = new Data(file);
     data = dataObj.data;
     getReverseDirector();
   }

   void getReverseDirector() throws Exception {

     Grid grid = new Grid(-240, 170);

     System.out.println("Lines to process=" + this.data.size());
     for (int i = 0; i < data.size(); i++) {

       String line = this.data.get(i);
       grid.moveRope(new Move(line));
     }

     System.out.println("Number of positions=" + grid.numPositions() + ". Number of positions visited by tail=" + grid.numVisitedByTail());
     System.out.println("Final head: " + grid.getPosition(grid.head) + " Final tail: " + grid.getPosition(grid.tail));

     System.out.println("===============================================");
     // grid.testVisit();
     // grid.testMoveHeadOnce();
   }
 }
