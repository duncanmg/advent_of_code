import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.Before;

public class TestRunInstructions {

	public static void main(String[] args) throws Exception {
		TestRunInstructions obj = new TestRunInstructions();
	}

	Logger logger = new Logger(this, true);

	RunInstructions instructions = new RunInstructions();

	@Before
		public void setUp() throws Exception {
			logger.log("Start setUp");

			instructions = new RunInstructions();

			Data dataObj = new Data();
			ArrayList<String> data = dataObj.getData("test_layout.txt");

			CubeFactory cubeFactory = new CubeFactory();
			Cube cube = cubeFactory.buildCube(data);
			assertEquals(12, cube.layout.size());

			instructions.cube = cube;
			logger.log("End setUp");
		}

	@Test public void TestConstructor() {
		RunInstructions instructions = new RunInstructions();
		assertEquals("RunInstructions", instructions.getClass().getName());
	}

	@Test public void TestGetNextInstruction() {
		RunInstructions instructions = new RunInstructions();
		instructions.instructions = "U123D";

		assertEquals("U", instructions.getNextInstruction());
		assertEquals("123", instructions.getNextInstruction());
		assertEquals("D", instructions.getNextInstruction());
		assertEquals("", instructions.getNextInstruction());
	}

	@Test public void TestGetNextInstruction02() {
		RunInstructions instructions = new RunInstructions();

		// Start with a number this time.
		instructions.instructions = "1U123D";

		assertEquals("1", instructions.getNextInstruction());
		assertEquals("U", instructions.getNextInstruction());
		assertEquals("123", instructions.getNextInstruction());
		assertEquals("D", instructions.getNextInstruction());
		assertEquals("", instructions.getNextInstruction());
	}

	@Test public void TestRun01() throws Exception{

		logger.log("Start TestRun01");
		instructions.instructions = "10";
		assertEquals(12, instructions.cube.layout.size());
		logger.log("02 TestRun01");
		instructions.run();		
		logger.log("03 TestRun01");
		testPosition(0,2);
	}

	@Test public void TestRun02() throws Exception{

		logger.log("Start TestRun02");
		instructions.instructions = "10R5";
		instructions.run();		
		testPosition(4,1,2, "D");
		logger.log("End TestRun02");
	}

	@Test public void TestRun03() throws Exception{

		logger.log("Start TestRun03");
		instructions.instructions = "10R5L5";
		instructions.run();		
		testPosition(6,2,2,"D");
		logger.log("End TestRun03");
	}

	// Facing down, turn right, end up facing left.
	@Test public void TestRun04() throws Exception{

		logger.log("Prepare TestRun04");
		setPosition(6,2,2,"D");
		testPosition(6,2,2,"D");

		logger.log("Start TestRun04");
		instructions.instructions = "R10";
		instructions.instructionsIndex = 0;

		instructions.run();		
		testPosition(5,2,2,"L");
		logger.log("End TestRun04");
	}

	// Was left. Turn left which means move down.
	@Test public void TestRun05() throws Exception{

		logger.log("Prepare TestRun05");
		setPosition(5,2,2,"L");
		testPosition(5,2,2,"L");

		logger.log("Start TestRun05");
		instructions.instructions = "L4";
		instructions.run();		
		testPosition(2,1,1,"U");
		logger.log("End TestRun05");
	}

	// Was up. Turn right which means move right.
	@Test public void TestRun06() throws Exception{

		logger.log("Prepare TestRun06");
		setPosition(2,1,1,"U");
		testPosition(2,1,1,"U");

		logger.log("Start TestRun06");
		instructions.instructions = "R5";
		instructions.run();		
		testPosition(3,1,2, "R");
		logger.log("End TestRun06");
	}

	// Was right. Turn left which means move up.
	@Test public void TestRun07() throws Exception{

		logger.log("Prepare TestRun07");
		setPosition(3,1,2, "R");
		testPosition(3,1,2, "R");

		logger.log("Start TestRun07");
		instructions.instructions = "L5";
		instructions.run();		
		testPosition(3,0,2,"U");
		logger.log("End TestRun07");
	}

	//	@Test public void TestRunExtra01() throws Exception{
	//
	//		logger.log("Start TestRunExtra01");
	//		instructions.instructions = "2R6R1L3L4L3";
	//		instructions.run();		
	//		testPosition(5,7);
	//		logger.log("End TestRunExtra01");
	//	}

	void testPosition(int expectedSideNo, int expectedCol, int expectedRow, String expectedDirection) {
		testPosition(expectedCol, expectedRow, expectedDirection);
		assertEquals(expectedSideNo, instructions.cube.getCurrentSideNo());
		logger.log("05 testPosition sideNo ok");
	}

	void testPosition(int expectedCol, int expectedRow, String expectedDirection) {
		testPosition(expectedCol, expectedRow);
		assertEquals(expectedDirection, Character.toString(instructions.cube.getCurrentSide().currentDirection));
		logger.log("04 testPosition direction ok");
	}

	void testPosition(int expectedCol, int expectedRow) {
		logger.log("01 testPosition " + expectedCol + ", " + expectedRow);
		assertEquals(expectedCol, instructions.cube.getCurrentSide().currentCol);
		logger.log("02 testPosition currentCol ok");
		assertEquals(expectedRow, instructions.cube.getCurrentSide().currentRow);
		logger.log("03 testPosition currentRow ok");
	}

	void setPosition(int newSideNo, int newCol, int newRow, String newDirection) throws Exception {
		// Chosen a random mapping.
		instructions.cube.changeCurrentSideTo(newSideNo, newDirection.charAt(0), "BOTTOMLEFT->BOTTOMLEFT:BOTTOMRIGHT->TOPLEFT");
		instructions.cube.getCurrentSide().currentCol = newCol;
		instructions.cube.getCurrentSide().currentRow = newRow;
	}

}
