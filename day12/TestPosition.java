//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;

public class TestPosition {

   public static void main(String[] args) throws Exception {
       TestPosition obj = new TestPosition();
   }

   Grid grid;

   Grid smallGrid;

   void testPos(Grid g, int expected, String expectedRoute) throws Exception {
	 Position p = g.squares.get(0).get(0);
     int steps = p.getShortestCompleteRoute(g);
	 assertEquals(expected, steps);
   }

   void testNoRoutes(Grid g) throws Exception {
	   Boolean ok = false;
	   try {
		   Position p = g.squares.get(0).get(0);
		   int steps = p.getShortestCompleteRoute(g);
	   }
	   catch (Exception e) {
		   ok = true;
	   }
	   assertTrue(ok);
   }

   @BeforeEach
   	void setUp() throws Exception {
   	  // this.grid = new Grid("test_data.txt");
   	  this.smallGrid = new Grid("test_data_small.txt");
   	}

   @Test
   public void TestConstructor() throws Exception {
     Position pos = new Position(1, 2, 3, 'a');
     assertEquals(pos.getClass(), Position.class);
   }

   @Test
   public void TestSmallGrid1() throws Exception {
	 this.setUp();
     Grid g = this.smallGrid;
	 assertEquals(3,g.squares.size());
	 assertEquals(3,g.squares.get(0).size());

   }

   @Test
   public void TestSmallGrid2() throws Exception {
	 this.setUp();
     Grid g = this.smallGrid;

	 Position p = g.squares.get(0).get(0);
	 assertEquals(0,p.row);
	 assertEquals(0,p.col);
	 assertEquals('S',p.character);
   }

   @Test
   public void TestZeroGrid() throws Exception {
   	 Grid g = new Grid("test_data_zero.txt");
	 Position p = g.squares.get(0).get(0);
     int steps = p.getShortestCompleteRoute(g);
	 assertEquals(0, steps);
   }

   @Test
   public void TestTouchingGrid() throws Exception {
   	 Grid g = new Grid("test_data_touching.txt");
	 Position p = g.squares.get(0).get(0);
	 testNoRoutes(g);
   }

   @Test
   public void TestVerticalGrid() throws Exception {
   	 Grid g = new Grid("test_data_vertical.txt");
	 Position p = g.squares.get(0).get(0);
     int steps = p.getShortestCompleteRoute(g);
	 assertEquals(25, steps);
   }

   @Test
   public void TestBendGrid() throws Exception {
   	 Grid g = new Grid("test_data_bend.txt");
	 this.testPos(g, 28, "");
   }

   @Test
   public void TestSteepGrid() throws Exception {
   	 Grid g = new Grid("test_data_steep.txt");
	 this.testPos(g,29, "");
   }

   @Test
   public void Test2Routes() throws Exception {
	   System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
   	 Grid g = new Grid("test_data_2routes.txt");
	 // [row: 0 col: 0, row: 0 col: 1, row: 1 col: 1] is also valid, but we
	 // get this because it always searches in the same order.
	 this.testPos(g,27, "");
   }

   // No routes found because the gradients are too steep in such a small grid.
   @Test
   public void TestSmallGrid() throws Exception {
   	 Grid g = new Grid("test_data_small.txt");
	 this.testNoRoutes(g);
   }

   @Test
   public void TestGridWithLoopData() throws Exception {
   	 Grid g = new Grid("test_data_loop.txt");
   // No routes found because the gradients are too steep in such a small grid.
	 this.testNoRoutes(g);
   }

   @Test
   public void TestGridWithBacktrackData() throws Exception {
   	 Grid g = new Grid("test_data_backtrack.txt");
   // No routes found because the gradients are too steep in such a small grid.
	 this.testNoRoutes(g);
   }

   @Test
   public void TestGridWithTestData() throws Exception {
   	 Grid g = new Grid("test_data.txt");
	  this.testPos(g,31, "");
   }

   @Test
   public void TestGridWithDifferentOriginData() throws Exception {
   	 Grid g = new Grid("test_data_origin.txt");
	 Position p = g.squares.get(g.startRow).get(g.startCol);
     int steps = p.getShortestCompleteRoute(g);
     assertEquals(27, steps);
   }

   @Test
   public void TestRealThing() throws Exception {
   	 Grid g = new Grid("data.txt");
	 Position p = g.squares.get(g.startRow).get(g.startCol);

     int steps = p.getShortestCompleteRoute(g);

	 assertEquals(391, steps);
 }

   Boolean compare(String s1, String s2) {
	Boolean ok = true;
	if (s1.length() != s2.length()) {
		System.out.println("The strings are not the same length " + s1.length() + " : " + s2.length());
		ok = false;
	}
	for (int i=0; i<s1.length();i++) {
		if (s1.charAt(i) != s2.charAt(i)) {
			System.out.println("The strings differ at character " + i + ". " + s1.charAt(i) + " : " + s2.charAt(i));
			ok = false;
			break;
		}
	}
	return ok;
   }

   Boolean noDuplicates(ArrayList<Position> route) {
	   Boolean ok = true;
	   for (int i=0; i<route.size();i++){
		   for (int j=i+1; j<route.size();j++) {
			   if (route.get(i).row == route.get(j).row && route.get(i).col == route.get(j).col) {
				   System.out.println(route.get(i) + " exists twice " + route.get(j));
				   ok = false;
			   }
		   }
	   }
     return ok;
   }
 }
