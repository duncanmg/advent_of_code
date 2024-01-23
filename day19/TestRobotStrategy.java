//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.Before;

public class TestRobotStrategy {

	public static void main(String[] args) throws Exception {
		TestRobotStrategy obj = new TestRobotStrategy();
	}

	RobotStrategy strategy;

	@Before
		public void setUp() throws Exception {
			strategy = new RobotStrategy();
			assertEquals(strategy.getClass().getName(), "RobotStrategy");

			ArrayList<Integer> values = new ArrayList<Integer>(Arrays.asList(4, 2, 3, 14, 2, 7));
			strategy.blueprint = new Blueprint(1, values);
		}

	@Test public void TestRawMaterialTotals() throws Exception {

		assertEquals(0, strategy.minute);

		testMaterialTotals(0, 0, 0, 0);
	}

	@Test public void TestRobotTotals() throws Exception {

		assertEquals(0, strategy.minute);

		testNumRobots(1, 0, 0, 0);
	}

	@Test public void TestRobotsRequested() throws Exception {

		assertEquals(0, strategy.minute);

		testNumRobotsRequested(0, 0, 0, 0);
	}

	@Test public void TestRobotsAvailable() throws Exception {

		assertEquals(0, strategy.minute);

		testDecisions(false, false, false, false);
	}

	@Test public void TestRobotsMinute1() throws Exception {

		strategy.nextMinute();
		assertEquals(1, strategy.minute);

		testMaterialTotals(1, 0, 0, 0);

		testDecisions(false, false, false, false);
	}

	@Test public void TestRobotsMinute2() throws Exception {

		runNextMinutes(2);

		assertEquals(2, strategy.minute);

		testMaterialTotals(2, 0, 0, 0);

		testDecisions(false, true, false, false);
	}

	@Test public void TestRobotsMinute4() throws Exception {

		runNextMinutes(4);

		assertEquals(4, strategy.minute);

		testMaterialTotals(4, 0, 0, 0);

		testDecisions(true, true, false, false);
	}


	@Test public void TestRobotsRequestOreRobot() throws Exception {

		runNextMinutes(4);

		assertEquals(4, strategy.minute);

		testMaterialTotals(4, 0, 0, 0);

		testDecisions(true, true, false, false);

		strategy.requestOreRobot();

		testMaterialTotals(0, 0, 0, 0);

		testDecisions(false, false, false, false);

		testNumRobots(1, 0, 0, 0);

		testNumRobotsRequested(1, 0, 0, 0);
	}

	@Test public void TestRobotsRequestClayRobot() throws Exception {

		runNextMinutes(4);

		testDecisions(true, true, false, false);

		strategy.requestClayRobot();

		testDecisions(false, true, false, false);

		testNumRobots(1, 0, 0, 0);

		testNumRobotsRequested(0, 1, 0, 0);

		strategy.requestClayRobot();

		testDecisions(false, false, false, false);

		testNumRobots(1, 0, 0, 0);

		testNumRobotsRequested(0, 2, 0, 0);
	}

	@Test public void TestRobotCreation() throws Exception {

		runNextMinutes(6);

		testMaterialTotals(6, 0, 0, 0);
		testNumRobots(1, 0, 0, 0);
		testDecisions(true, true, false, false);

		// Request ore and clay robots.
		strategy.requestOreRobot();
		strategy.requestClayRobot();

		testMaterialTotals(0, 0, 0, 0);
		testDecisions(false, false, false, false);
		testNumRobots(1, 0, 0, 0);
		testNumRobotsRequested(1, 1, 0, 0);

		runNextMinutes(1);

		testMaterialTotals(1, 0, 0, 0);
		testDecisions(false, false, false, false);
		testNumRobots(2, 1, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		runNextMinutes(1);

		testMaterialTotals(3, 1, 0, 0);
		testDecisions(false, true, false, false);
		testNumRobots(2, 1, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		runNextMinutes(13);
		testMaterialTotals(29, 14, 0, 0);
		testDecisions(true, true, true, false);

		// Request obsidian robot.
		strategy.requestObsidianRobot();
		testMaterialTotals(26, 0, 0, 0);
		testDecisions(true, true, false, false);
		testNumRobots(2, 1, 0, 0);
		testNumRobotsRequested(0, 0, 1, 0);

		runNextMinutes(1);
		testNumRobots(2, 1, 1, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		runNextMinutes(1);
		testMaterialTotals(30, 2, 1, 0);

		runNextMinutes(6);
		testMaterialTotals(42, 8, 7, 0);
		testDecisions(true, true, false, true);

		// Request geode robot.
		strategy.requestGeodeRobot();
		testMaterialTotals(40, 8, 0, 0);
		testDecisions(true, true, false, false);
		testNumRobots(2, 1, 1, 0);
		testNumRobotsRequested(0, 0, 0, 1);

		runNextMinutes(1);
		testMaterialTotals(42, 9, 1, 0);
		testNumRobots(2, 1, 1, 1);
		testNumRobotsRequested(0, 0, 0, 0);

		runNextMinutes(1);
		testMaterialTotals(44, 10, 2, 1);
		testNumRobots(2, 1, 1, 1);
		testNumRobotsRequested(0, 0, 0, 0);
	}

	void runNextMinutes(int num) {
		for (int i = 0; i < num; i++) {
			strategy.nextMinute();
		}
	}

	void testMaterialTotals(int oreTotal, int clayTotal, int obsidianTotal, int geodeTotal) {
		assertEquals(oreTotal, strategy.oreTotal);
		assertEquals(clayTotal, strategy.clayTotal);
		assertEquals(obsidianTotal, strategy.obsidianTotal);
		assertEquals(geodeTotal, strategy.geodeTotal);
	}

	void testDecisions(boolean canBuildOreRobot, boolean canBuildClayRobot, boolean canBuildObsidianRobot, boolean canBuildGeodeRobot) {
		assertEquals(canBuildOreRobot, strategy.canBuildOreRobot());
		assertEquals(canBuildClayRobot, strategy.canBuildClayRobot());
		assertEquals(canBuildObsidianRobot, strategy.canBuildObsidianRobot());
		assertEquals(canBuildGeodeRobot, strategy.canBuildGeodeRobot());
	}

	void testNumRobots(int numOreRobots, int numClayRobots, int numObsidianRobots, int numGeodeRobots) {
		assertEquals(numOreRobots, strategy.numOreRobots);
		assertEquals(numClayRobots, strategy.numClayRobots);
		assertEquals(numObsidianRobots, strategy.numObsidianRobots);
		assertEquals(numGeodeRobots, strategy.numGeodeRobots);
	}

	void testNumRobotsRequested(int numOreRobotsRequested, int numClayRobotsRequested, int numObsidianRobotsRequested, int numGeodeRobotsRequested) {
		assertEquals(numOreRobotsRequested, strategy.numOreRobotsRequested);
		assertEquals(numClayRobotsRequested, strategy.numClayRobotsRequested);
		assertEquals(numObsidianRobotsRequested, strategy.numObsidianRobotsRequested);
		assertEquals(numGeodeRobotsRequested, strategy.numGeodeRobotsRequested);
	}
}
