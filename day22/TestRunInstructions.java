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

			instructions.layout.parseData(data);
			assertEquals(12, instructions.layout.layout.size());
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

		instructions.instructions = "10";
			assertEquals(12, instructions.layout.layout.size());
		instructions.run();		
		testPosition(0,10);
	}

	@Test public void TestRun02() throws Exception{

		logger.log("Start TestRun02");
		instructions.instructions = "10R5";
		instructions.run();		
		testPosition(5,10);
		logger.log("End TestRun02");
	}

	@Test public void TestRun03() throws Exception{

		logger.log("Start TestRun03");
		instructions.instructions = "10R5L5";
		instructions.run();		
		testPosition(5,3,"R");
		logger.log("End TestRun03");
	}

	// Move down
	@Test public void TestRun04() throws Exception{

		logger.log("Prepare TestRun04");
		setPosition(5,3,"R");
		testPosition(5,3,"R");

		logger.log("Start TestRun04");
		instructions.instructions = "R10";
		instructions.instructionsIndex = 0;

		instructions.run();		
		testPosition(7,3,"D");
		logger.log("End TestRun04");
	}

	// Was down. Turn left which means move right.
	@Test public void TestRun05() throws Exception{

		logger.log("Start TestRun05");
		instructions.instructions = "10R5L5R10L4";
		instructions.run();		
		testPosition(7,7);
		logger.log("End TestRun05");
	}

	// Was right. Turn right which means move down.
	@Test public void TestRun06() throws Exception{

		logger.log("Start TestRun06");
		instructions.instructions = "10R5L5R10L4R5";
		instructions.run();		
		testPosition(5,7);
		logger.log("End TestRun06");
	}

	// Was down. Turn left which means move right.
	// (Can't move at all.)
	@Test public void TestRun07() throws Exception{

		logger.log("Start TestRun07");
		instructions.instructions = "10R5L5R10L4R5L5";
		instructions.run();		
		testPosition(5,7);
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

	void testPosition(int expectedCol, int expectedRow, String expectedDirection) {
		testPosition(expectedCol, expectedRow);
		assertEquals(expectedDirection, instructions.layout.currentDirection);
	}

	void testPosition(int expectedCol, int expectedRow) {
		assertEquals(expectedCol, instructions.layout.currentColumnPos);
		assertEquals(expectedRow, instructions.layout.currentRowPos);
	}

	void setPosition(int newCol, int newRow, String newDirection) {
		instructions.layout.currentColumnPos = newCol;
		instructions.layout.currentRowPos = newRow;
		instructions.layout.currentDirection = newDirection;
	}

}
