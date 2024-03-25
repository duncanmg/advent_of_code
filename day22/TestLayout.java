import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.Before;

// test_layout.txt
//        ...#
//        .#..
//        #...
//        ....
//...#.......#
//........#...
//..#....#....
//..........#.
//        ...#....
//        .....#..
//        .#......
//        ......#.

public class TestLayout {

	public static void main(String[] args) throws Exception {
		TestLayout obj = new TestLayout();
	}

	Logger logger = new Logger(this, true);

	Layout layout = new Layout();

	@Before
		public void setUp() throws Exception {
			logger.log("Start setUp");

			Data dataObj = new Data();
			ArrayList<String> data = dataObj.getData("test_layout.txt");

			layout = new Layout();
			layout.parseData(data);
			assertEquals(12, layout.layout.size());
		}

	@Test public void TestConstructor() {
		layout = new Layout();
		assertEquals("Layout", layout.getClass().getName());
	}

	@Test public void TestGetNextRight() throws Exception {
		assertEquals(0, layout.currentColumnPos);
		assertEquals(0, layout.currentRowPos);

		assertEquals(1, layout.getNextRight());
	}

	@Test public void TestMoveRight() throws Exception {
		logger.log("Start TestMoveRight()");
		assertEquals(0, layout.currentColumnPos);
		assertEquals(0, layout.currentRowPos);

		layout.moveRight(5);

		assertEquals(0, layout.currentColumnPos);
		// ________...# That's 8 NOTHING, 3 TILE, 1 WALL
		// Pointer should be at 11th position, index 10.
		assertEquals(10, layout.currentRowPos);
		logger.log("End TestMoveRight()");
	}

	@Test public void TestMoveDown() throws Exception {
		assertEquals(0, layout.currentColumnPos);
		assertEquals(0, layout.currentRowPos);

		layout.moveRight(1);
		layout.moveDown(3);

		// Moving 3 down halts because there is a wall in column 2.
		assertEquals(1, layout.currentColumnPos);
		// Moving 1 right goes to the last column with a tile.
		assertEquals(8, layout.currentRowPos);
	}

	@Test public void TestMoveLeft() throws Exception {
		assertEquals(0, layout.currentColumnPos);
		assertEquals(0, layout.currentRowPos);

		layout.currentRowPos = 8;
		layout.currentColumnPos = 4;
		layout.moveLeft(6);

		assertEquals(4, layout.currentColumnPos);
		// Moving 6 left goes to the last row with a tile.
		assertEquals(4, layout.currentRowPos);
	}

	@Test public void TestMoveUp() throws Exception {
		assertEquals(0, layout.currentColumnPos);
		assertEquals(0, layout.currentRowPos);

		layout.currentRowPos = 8;
		layout.currentColumnPos = 11;
		layout.moveUp(9);

		// Moving 9 up goes to the last column with a tile.
		assertEquals(6, layout.currentColumnPos);
		assertEquals(8, layout.currentRowPos);
	}

	@Test public void TestMoveToOrigin() throws Exception {
		assertEquals(0, layout.currentColumnPos);
		assertEquals(0, layout.currentRowPos);

		layout.moveToOrigin();

		assertEquals(0, layout.currentColumnPos);
		assertEquals(8, layout.currentRowPos);
	}

}
