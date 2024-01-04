import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
 import java.util.*;

public class TestDay9Part2 {
	
  public static void main(String[] args) throws Exception {
     TestDay9Part2 obj = new TestDay9Part2();
   }

   @Test
   public void testAdd() {
      String str = "Junit is working fine";
      assertEquals("Junit is working fine",str);
   }
	
   @Test
   public void testGrid() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
	  Grid g = new Grid();
	          g.head = g.visit(new Position(2,2), "head");
			  // Method m = this.getAreAdjacentMethod();
			  Boolean[][] expected = { {false, false, false, false, false }, 
				                       {false, true, true, true, false },
									   { false, true, true, true, false },
									   { false, true, true, true, false },
									   { false, false, false, false, false } 
			                         };
	     int loops = 0;
         for (int i=0; i<5; i++) {
             for (int j=0; j<5; j++) {
                 g.tail = g.visit(new Position(i,j), "tail");
				 Boolean r = g.head.touches(g.tail);
                 if (expected[i][j]) {
                     assertTrue(r);
                 }
                 else {
                     assertFalse(r);
                 }
			     loops++;
             }
         }
         assertEquals(25, loops);
   }

     @Test
     public void testVisit() throws Exception {
     Grid g = new Grid();

	 assertEquals(1, (int) g.numPositions());
	 assertEquals(1, (int) g.numVisitedByTail());

     g.visit(new Position(1, 0), "head");
	 assertEquals(2,(int) g.numPositions());
	 assertEquals(1,(int)  g.numVisitedByTail());

     g.visit(new Position(1, 0), "head");
	 assertEquals(2,(int) g.numPositions());
	 assertEquals(1, (int) g.numVisitedByTail());

     g.visit(new Position(1, 1), "head");
	 assertEquals(3,(int) g.numPositions());
	 assertEquals(1, (int) g.numVisitedByTail());

     g.visit(new Position(1, 1), "head");
	 assertEquals(3,(int) g.numPositions());
	 assertEquals(1, (int) g.numVisitedByTail());

     g.visit(new Position(0, 0), "tail");
	 assertEquals(3,(int) g.numPositions());
	 assertEquals(1, (int) g.numVisitedByTail());

     g.visit(new Position(1, 1), "tail");
	 assertEquals(3,(int) g.numPositions());
	 assertEquals(2, (int) g.numVisitedByTail());
   }

   public Method getMoveHeadOnceMethod() throws NoSuchMethodException, IllegalAccessException{
    Method method = Grid.class.getDeclaredMethod("moveHeadOnce", Move.class);
    method.setAccessible(true);
    return method;
  }

   @Test
   public void testMoveHeadOnce() throws Exception, NoSuchMethodException, IllegalAccessException {

     Grid g = new Grid();
	 assertEquals(1, (int) g.numPositions());
	 assertEquals(1, (int) g.numVisitedByTail());

	 Method m = this.getMoveHeadOnceMethod();

	 Move move = new Move("U 1");
	 m.invoke(g, move);
	 assertEquals(2, (int) g.numPositions());
	 assertEquals(1, (int) g.numVisitedByTail());

     Position newPos = g.head;
	 assertEquals(1, (int)newPos.row);
	 assertEquals(0, (int)newPos.col);
	 assertEquals(1, (int)newPos.headVisits);

	 //+++

	 move = new Move("D 1");
	 m.invoke(g, move);
	 assertEquals(2, (int) g.numPositions());
	 assertEquals(1, (int) g.numVisitedByTail());

     newPos = g.head;
	 assertEquals(0, (int)newPos.row);
	 assertEquals(0, (int)newPos.col);
	 assertEquals(2, (int)newPos.headVisits);

	 //+++
	 move = new Move("L 1");
	 m.invoke(g, move);
	 assertEquals(3, (int) g.numPositions());
	 assertEquals(1, (int) g.numVisitedByTail());

     newPos = g.head;
	 assertEquals(0, (int)newPos.row);
	 assertEquals(-1, (int)newPos.col);
	 assertEquals(1, (int)newPos.headVisits);

	 //+++

	 move = new Move("R 1");
	 m.invoke(g, move);
	 assertEquals(3, (int) g.numPositions());
	 assertEquals(1, (int) g.numVisitedByTail());

     newPos = g.head;
	 assertEquals(0, (int)newPos.row);
	 assertEquals(0, (int)newPos.col);
	 assertEquals(3, (int)newPos.headVisits);

   }

   @Test
   public void testMoveRope() throws Exception, NoSuchMethodException, IllegalAccessException {

     Grid g = new Grid();
	 assertEquals(1, (int) g.numPositions());
	 assertEquals(1, (int) g.numVisitedByTail());

	 g.moveRope(new Move("R 1"));
	 assertEquals(0, (int) g.tail.row);
	 assertEquals(0, (int) g.tail.col);

	 g.moveRope(new Move("R 1"));
	 assertEquals(0, (int) g.tail.row);
	 assertEquals(1, (int) g.tail.col);

	 g.moveRope(new Move("R 1"));
	 assertEquals(0, (int) g.tail.row);
	 assertEquals(2, (int) g.tail.col);

	 g.moveRope(new Move("R 1"));
	 assertEquals(0, (int) g.tail.row);
	 assertEquals(3, (int) g.tail.col);
     assertEquals(4, (int)g.numVisitedByTail());

	 g.moveRope(new Move("L 4"));
	 assertEquals(0, (int) g.tail.row);
	 assertEquals(1, (int) g.tail.col);
     assertEquals(4, (int)g.numVisitedByTail());

	 g.moveRope(new Move("L 1"));
	 assertEquals(0, (int) g.tail.row);
	 assertEquals(0, (int) g.tail.col);
     assertEquals(4, (int)g.numVisitedByTail());

	 g.moveRope(new Move("L 1"));
	 assertEquals(0, (int) g.tail.row);
	 assertEquals(-1, (int) g.tail.col);
     assertEquals(5, (int)g.numVisitedByTail());

	 g.moveRope(new Move("U 1"));
	 assertEquals(0, (int) g.tail.row);
	 assertEquals(-1, (int) g.tail.col);
     assertEquals(5, (int)g.numVisitedByTail());

	 g.moveRope(new Move("U 1"));
	 assertEquals(1, (int) g.tail.row);
	 assertEquals(-2, (int) g.tail.col);
     assertEquals(6, (int)g.numVisitedByTail());

	 g.moveRope(new Move("D 1"));
	 assertEquals(1, (int) g.tail.row);
	 assertEquals(-2, (int) g.tail.col);
     assertEquals(6, (int)g.numVisitedByTail());

	 g.moveRope(new Move("D 1"));
	 assertEquals(1, (int) g.tail.row);
	 assertEquals(-2, (int) g.tail.col);
     assertEquals(6, (int)g.numVisitedByTail());

     this.moveRope(g, "D 1", 0, -2, 7);
   }

   void moveRope(Grid g, String m, int row, int col, int visits) throws Exception {
	 g.moveRope(new Move(m));
	 assertEquals(row, (int) g.tail.row);
	 assertEquals(col, (int) g.tail.col);
     assertEquals(visits, (int)g.numVisitedByTail());
   }

   @Test
   public void testPrototype() throws Exception, NoSuchMethodException, IllegalAccessException {

     Grid g = new Grid();
	 assertEquals(1, (int) g.numPositions());
	 assertEquals(1, (int) g.numVisitedByTail());

	 // R 4
     this.moveRope(g, "R 1", 0, 0, 1);
     this.moveRope(g, "R 1", 0, 1, 2);
     this.moveRope(g, "R 1", 0, 2, 3);
     this.moveRope(g, "R 1", 0, 3, 4);

	 // U 4
     this.moveRope(g, "U 1", 0, 3, 4);
     this.moveRope(g, "U 1", 1, 4, 5);
     this.moveRope(g, "U 1", 2, 4, 6);
     this.moveRope(g, "U 1", 3, 4, 7);

	 // L 3
     this.moveRope(g, "L 1", 3, 4, 7);
     this.moveRope(g, "L 1", 4, 3, 8);
     this.moveRope(g, "L 1", 4, 2, 9);

	 // D 1
     this.moveRope(g, "D 1", 4, 2, 9);

	 // R 4
     this.moveRope(g, "R 1", 4, 2, 9);
     this.moveRope(g, "R 1", 4, 2, 9);
     this.moveRope(g, "R 1", 3, 3, 10);
     this.moveRope(g, "R 1", 3, 4, 10);

	 // D 1
     this.moveRope(g, "D 1", 3, 4, 10);

	 // L 5
     this.moveRope(g, "L 1", 3, 4, 10);
     this.moveRope(g, "L 1", 3, 4, 10);
     this.moveRope(g, "L 1", 2, 3, 11);
     this.moveRope(g, "L 1", 2, 2, 12);
     this.moveRope(g, "L 1", 2, 1, 13);

	 // R 2
     this.moveRope(g, "R 1", 2, 1, 13);
     this.moveRope(g, "R 1", 2, 1, 13);

   }

   @Test
   public void testDirector() throws Exception, NoSuchMethodException, IllegalAccessException {

	 // R 4
	 
	 Director director = new Director("test_data01.txt", 10);
     assertEquals(10, director.grids.size());

	 assertEquals(0, (int) director.grids.get(0).tail.row);
	 assertEquals(3, (int) director.grids.get(0).tail.col);

	 assertEquals(0, (int) director.grids.get(1).tail.row);
	 assertEquals(2, (int) director.grids.get(1).tail.col);

	 assertEquals(0, (int) director.grids.get(2).tail.row);
	 assertEquals(1, (int) director.grids.get(2).tail.col);

	 for (int i=3; i<10; i++) {
		 assertEquals(0, (int) director.grids.get(i).tail.row);
		 assertEquals(0, (int) director.grids.get(i).tail.col);
	 }

	 // R 11
	 
	 director = new Director("test_data02.txt", 10);
     assertEquals(10, director.grids.size());
	 ArrayList<Grid> grids = director.grids;

	 for (int i=0; i<10; i++) {
	   int v = 10 - i;
	   assertEquals(0, (int) grids.get(i).tail.row);
	   assertEquals(v, (int) grids.get(i).tail.col);
	 }

   }

   private void testLocation(ArrayList<Grid> grids, int gridNo, int row, int col) {
	 //System.out.println("row) Grid no " + gridNo);
	 assertEquals(row, (int) grids.get(gridNo).tail.row);
	 //System.out.println("col) Grid no " + gridNo);
	 assertEquals(col, (int) grids.get(gridNo).tail.col);
   }

   @Test
   public void testDirector2() throws Exception, NoSuchMethodException, IllegalAccessException {

	 // R 5, U 8
	 
	 Director director = new Director("test_data04.txt", 9);
     assertEquals(9, director.grids.size());
	 ArrayList<Grid> grids = director.grids;

	 this.testLocation(grids, 0, 7, 5);
	 this.testLocation(grids, 1, 6, 5);
	 this.testLocation(grids, 2, 5, 5);
	 this.testLocation(grids, 3, 4, 5);
	 this.testLocation(grids, 4, 4, 4);
	 this.testLocation(grids, 5, 3, 3);
	 this.testLocation(grids, 6, 2, 2);
	 this.testLocation(grids, 7, 1, 1);
	 this.testLocation(grids, 8, 0, 0);

   }
}
