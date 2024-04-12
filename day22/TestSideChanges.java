import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.Before;

public class TestSideChanges {

	public static void main(String[] args) throws Exception {
		TestSideChanges obj = new TestSideChanges();
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

	void setPositionAndVerify(int newSideNo, int newCol, int newRow, String newDirection)throws Exception  {
		setPosition(newSideNo, newCol, newRow, newDirection);
		testPosition(newSideNo, newCol, newRow, newDirection);
	}

	// Side 1 -> 6
	// 	1 -> 4
	//	1 -> 3
	//	1 -> 2

	@Test public void Test01to06() throws Exception{
		String testName = "Test01to06";

		logger.log("Prepare " + testName);
		setPositionAndVerify(1, 1, 3, "R");
		instructions.instructions = "1";

		logger.log("Start " + testName);
		instructions.run();		
		testPosition(6,2,3, "L");

		logger.log("End " + testName);
	}

	@Test public void Test01to04() throws Exception{
		String testName = "Test01to04";

		logger.log("Prepare " + testName);
		setPositionAndVerify(1, 3, 1, "D");
		instructions.instructions = "1";

		logger.log("Start " + testName);
		instructions.run();		
		testPosition(4,0,1, "D");

		logger.log("End " + testName);
	}

	@Test public void Test01to03() throws Exception{
		String testName = "Test01to03";

		logger.log("Prepare " + testName);
		setPositionAndVerify(1, 2, 0, "L");
		instructions.instructions = "1";

		logger.log("Start " + testName);
		instructions.run();		
		testPosition(3,0,2, "D");

		logger.log("End " + testName);
	}

	@Test public void Test01to02() throws Exception{
		String testName = "Test01to02";

		logger.log("Prepare " + testName);
		setPositionAndVerify(1, 0, 1, "U");
		instructions.instructions = "1";

		logger.log("Start " + testName);
		instructions.run();		
		testPosition(2,0,2, "D");

		logger.log("End " + testName);
	}

	// Side	2 -> 3
	@Test public void Test02to03() throws Exception{
		TestHelper helper = new TestHelper("Test02to03", logger, instructions);

		helper.setStartPosition(2, 1, 3, "R");

		helper.run(3,1,0, "R");		
	}

	//	2 -> 5
	@Test public void Test02to05() throws Exception{
		TestHelper helper = new TestHelper("Test02to05", logger, instructions);

		helper.setStartPosition(2, 3, 2, "D");

		helper.run(5,3,1, "U");		
	}

	//	2 -> 6
	@Test public void Test02to06() throws Exception{
		TestHelper helper = new TestHelper("Test02to06", logger, instructions);

		helper.setStartPosition(2, 2, 0, "L");

		helper.run(6,3,1, "U");		
	}

	//	2 -> 1
	@Test public void Test02to01() throws Exception{
		TestHelper helper = new TestHelper("Test02to01", logger, instructions);

		helper.setStartPosition(2, 0, 2, "U");

		helper.run(1,0,1, "D");		
	}

	// Side 3 -> 4
	@Test public void Test03to04() throws Exception{
		TestHelper helper = new TestHelper("Test03to04", logger, instructions);

		helper.setStartPosition(3, 2, 3, "R");

		helper.run(4,2,0, "R");		
	}

	//	3 -> 5
	@Test public void Test03to05() throws Exception{
		TestHelper helper = new TestHelper("Test03to05", logger, instructions);

		helper.setStartPosition(3, 3, 2, "D");

		helper.run(5,1,0, "R");		
	}

	//	3 -> 2
	@Test public void Test03to02() throws Exception{
		TestHelper helper = new TestHelper("Test03to02", logger, instructions);

		helper.setStartPosition(3, 1, 0, "L");

		helper.run(2,1,3, "L");		
	}

	//	3 -> 1
	@Test public void Test03to01() throws Exception{
		TestHelper helper = new TestHelper("Test03to01", logger, instructions);

		helper.setStartPosition(3, 0, 3, "U");

		helper.run(1,3,0, "R");		
	}

	// Side 4 -> 6
	@Test public void Test04to06() throws Exception{
		TestHelper helper = new TestHelper("Test04to06", logger, instructions);

		helper.setStartPosition(4, 0, 3, "R");

		helper.run(6,0,3, "D");		
	}

	//	4 -> 5
	@Test public void Test04to05() throws Exception{
		TestHelper helper = new TestHelper("Test04to05", logger, instructions);

		helper.setStartPosition(4, 3, 1, "D");

		helper.run(5,0,1, "D");		
	}

	//	4 -> 3
	@Test public void Test04to03() throws Exception{
		TestHelper helper = new TestHelper("Test04to03", logger, instructions);

		helper.setStartPosition(4, 1, 0, "L");

		helper.run(3,1,3, "L");		
	}

	//	4 -> 1
	@Test public void Test04to01() throws Exception{
		TestHelper helper = new TestHelper("Test04to01", logger, instructions);

		helper.setStartPosition(4, 0, 1, "U");

		helper.run(1,3,1, "U");		
	}

	// Side 5 -> 6
	@Test public void Test05to06() throws Exception{
		TestHelper helper = new TestHelper("Test05to06", logger, instructions);

		helper.setStartPosition(5, 1, 3, "R");

		helper.run(6,1,0, "R");		
	}

	// 	5 -> 2
	@Test public void Test05to02() throws Exception{
		TestHelper helper = new TestHelper("Test05to02", logger, instructions);

		helper.setStartPosition(5, 3, 2, "D");

		helper.run(2,3,1, "U");		
	}

	//	5 -> 3
	@Test public void Test05to03() throws Exception{
		TestHelper helper = new TestHelper("Test05to03", logger, instructions);

		helper.setStartPosition(5, 2, 0, "L");

		helper.run(3,3,1, "U");		
	}

	//	5 -> 4
	@Test public void Test05to04() throws Exception{
		TestHelper helper = new TestHelper("Test05to04", logger, instructions);

		helper.setStartPosition(5, 0, 3, "U");

		helper.run(4,3,3, "U");		
	}

	// Side 6 -> 1
	@Test public void Test06to01() throws Exception{
		TestHelper helper = new TestHelper("Test06to01", logger, instructions);

		helper.setStartPosition(6, 1, 3, "R");

		helper.run(1,2,3, "L");		
	}

	//	6 -> 2
	@Test public void Test06to02() throws Exception{
		TestHelper helper = new TestHelper("Test06to02", logger, instructions);

		helper.setStartPosition(6, 3, 1, "D");

		helper.run(2,2,0, "R");		
	}

	//	6 -> 5
	@Test public void Test06to05() throws Exception{
		TestHelper helper = new TestHelper("Test06to05", logger, instructions);

		helper.setStartPosition(6, 2, 0, "L");

		helper.run(5,2,3, "L");		
	}

	//	6 -> 4
	@Test public void Test06to04() throws Exception{
		TestHelper helper = new TestHelper("Test06to04", logger, instructions);

		helper.setStartPosition(6, 0, 1, "U");

		helper.run(4,2,3, "L");		
	}

	class TestHelper {
		public TestHelper(String testName, Logger logger, RunInstructions instructions) {
			this.testName = testName;
			this.logger = logger;
			this.instructions = instructions;
		}

		String testName;
		Logger logger;
		RunInstructions instructions;

		void setStartPosition(int newSideNo, int newCol, int newRow, String newDirection) throws Exception {
			logger.log("Prepare " + testName);
			setPositionAndVerify(newSideNo, newCol, newRow, newDirection);
			instructions.instructions = "1";
		}

		void run(int expectedSideNo, int expectedCol, int expectedRow, String expectedDirection) throws Exception {
			logger.log("Start " + testName);
			instructions.run();		
			testPosition(expectedSideNo, expectedCol, expectedRow, expectedDirection);

			logger.log("End " + testName);
		}
	}

	void testPosition(int expectedSideNo, int expectedCol, int expectedRow, String expectedDirection) {

		logger.log("01 testPosition " + expectedCol + ", " + expectedRow);

		assertEquals(expectedSideNo, instructions.cube.getCurrentSideNo());
		logger.log("02 testPosition sideNo ok");

		assertEquals(expectedCol, instructions.cube.getCurrentSide().currentCol);
		logger.log("03 testPosition currentCol ok");

		assertEquals(expectedRow, instructions.cube.getCurrentSide().currentRow);
		logger.log("04 testPosition currentRow ok");

		assertEquals(expectedDirection, Character.toString(instructions.cube.getCurrentSide().currentDirection));
		logger.log("05 testPosition direction ok");
	}

	void setPosition(int newSideNo, int newCol, int newRow, String newDirection) throws Exception {
		// Chosen a random mapping.
		instructions.cube.changeCurrentSideTo(newSideNo, newDirection.charAt(0), "BOTTOMLEFT->BOTTOMLEFT:BOTTOMRIGHT->TOPLEFT");
		instructions.cube.getCurrentSide().currentCol = newCol;
		instructions.cube.getCurrentSide().currentRow = newRow;
	}

}
