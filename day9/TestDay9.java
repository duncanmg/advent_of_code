import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;

public class TestDay9 {
	
  public static void main(String[] args) throws Exception {
     TestDay9 obj = new TestDay9();
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

//   public Method getAreAdjacentMethod() throws NoSuchMethodException, IllegalAccessException{
//    Method method = Grid.class.getDeclaredMethod("areAdjacent");
//    method.setAccessible(true);
//    return method;
//  }

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
}
