//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;

public class TestRock {

   public static void main(String[] args) throws Exception {
       TestRock obj = new TestRock();
   }

   @Test public void TestConstructor() throws Exception {

     Rock r = new Rock();

     assertEquals(r.getClass().getName(), "Rock");
   }

   static Rock r;

   Rock getRock() throws Exception{
   		Rock r = new Rock();
		r.addCoord(1,1);
		r.addCoord(1,2);
		r.addCoord(2,2);
		return r;
   }

   @Test public void TestAddCoord() throws Exception {

   		r = getRock();

		ArrayList<Coords> c = r.coords;
		ArrayList<Coords> expected = new ArrayList<Coords>(0);
		assertEquals(1, c.get(0).x);
		assertEquals("[1, 1]", c.get(0).toString());
		assertEquals("[[1, 1], [1, 2], [2, 2]]", c.toString());
	 
   }

   @Test public void TestRemoveCoord() throws Exception {

   		assertEquals(3, r.coords.size());

		r.removeCoord(1,2);
		assertEquals("[[1, 1], [2, 2]]", r.coords.toString());

		r.removeCoord(1,7);
		assertEquals("[[1, 1], [2, 2]]", r.coords.toString());

		int counter = 0;
		try {
			r.removeCoord(7,1);
		}
		catch(Exception e) {
			counter++;
			assertEquals("x is greater than the maximum: [7, 1] 6", e.getMessage());
		}
		assertEquals(1, counter);

   }

   @Test public void TestMoveLeft() throws Exception {

   		r = getRock();

   		assertEquals(3, r.coords.size());

		r.moveLeft();
		assertEquals("[[0, 1], [0, 2], [1, 2]]", r.coords.toString());

		// Could go in a lambda function.
		int counter = 0;
		try {
			r.moveLeft();
		}
		catch(Exception e) {
			counter++;
			assertEquals("x is less than the minimum: [-1, 1] 0", e.getMessage());
		}
		assertEquals(1, counter);

   }

   @Test public void TestMoveRight() throws Exception {

   		r = getRock();
		assertEquals("[]", r.previousCoords.toString());

		r.moveRight();
		assertEquals("[[2, 1], [2, 2], [3, 2]]", r.coords.toString());

   }

   @Test public void TestMoveDown() throws Exception {

   		r = getRock();

		r.moveDown();
		assertEquals("[[1, 0], [1, 1], [2, 1]]", r.coords.toString());

		int counter = 0;
		try {
			r.moveDown();
		}
		catch(Exception e) {
			counter++;
			assertEquals("y is less than the mninimum: [1, -1] 0", e.getMessage());
		}
		assertEquals(1, counter);

   }

 }
