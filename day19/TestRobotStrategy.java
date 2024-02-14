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

	Logger logger = new Logger(this, true);

	@Before
		public void setUp() throws Exception {
			logger.log("Start setUp");
			strategy = new RobotStrategy();
			assertEquals(strategy.getClass().getName(), "RobotStrategy");

			ArrayList<Integer> values = new ArrayList<Integer>(Arrays.asList(4, 2, 3, 14, 2, 7));
			strategy.blueprint = new Blueprint(1, values);
			logger.log("End setUp");
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
		strategy.collectResources();
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

		logger.log("Start TestRobotCreation()");
		logger.log("01 ------ minute: " + strategy.minute + " blueprint id " + strategy.blueprint.id);
		runNextMinutes(6);
		logger.log("02 ------ minute: " + strategy.minute);

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
		logger.log("03 z------ minute: " + strategy.minute + " blueprint id " + strategy.blueprint.id + " " + strategy.blueprint.clayRobotCost);

		testMaterialTotals(2, 1, 0, 0);
		testDecisions(false, true, false, false);
		testNumRobots(2, 1, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		runNextMinutes(1);
		logger.log("04 ------ minute: " + strategy.minute);

		testMaterialTotals(4, 2, 0, 0);
		testDecisions(true, true, false, false);
		testNumRobots(2, 1, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		runNextMinutes(13);
		logger.log("05 ------ minute: " + strategy.minute);
		testMaterialTotals(30, 15, 0, 0);
		testDecisions(true, true, true, false);

		// Request obsidian robot.
		strategy.requestObsidianRobot();
		testMaterialTotals(27, 1, 0, 0);
		testDecisions(true, true, false, false);
		testNumRobots(2, 1, 0, 0);
		testNumRobotsRequested(0, 0, 1, 0);

		runNextMinutes(1);
		logger.log("06 a------ minute: " + strategy.minute);
		testNumRobots(2, 1, 1, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		runNextMinutes(1);
		testMaterialTotals(31, 3, 2, 0);

		runNextMinutes(6);
		logger.log("07 ------ minute: " + strategy.minute);
		testMaterialTotals(43, 9, 8, 0);
		testDecisions(true, true, false, true);

		// Request geode robot.
		strategy.requestGeodeRobot();
		testMaterialTotals(41, 9, 1, 0);
		testDecisions(true, true, false, false);
		testNumRobots(2, 1, 1, 0);
		testNumRobotsRequested(0, 0, 0, 1);

		runNextMinutes(1);
		logger.log("08 ------ minute: " + strategy.minute);
		testMaterialTotals(43, 10, 2, 1);
		testNumRobots(2, 1, 1, 1);
		testNumRobotsRequested(0, 0, 0, 0);

		runNextMinutes(1);
		logger.log("09 q------ minute: " + strategy.minute);
		testMaterialTotals(45, 11, 3, 2);
		testNumRobots(2, 1, 1, 1);
		testNumRobotsRequested(0, 0, 0, 0);

		// Test the cloning.
		RobotStrategy clonedRobotStategy = (RobotStrategy) strategy.clone();
		assertEquals(clonedRobotStategy.getClass().getName(), "RobotStrategy");

		assertEquals(clonedRobotStategy.minute, strategy.minute);
		assertEquals(clonedRobotStategy.oreTotal, strategy.oreTotal);
		assertEquals(clonedRobotStategy.clayTotal, strategy.clayTotal);
		assertEquals(clonedRobotStategy.obsidianTotal, strategy.obsidianTotal);
		assertEquals(clonedRobotStategy.geodeTotal, strategy.geodeTotal);
		assertEquals(clonedRobotStategy.numOreRobots, strategy.numOreRobots);	
		assertEquals(clonedRobotStategy.numClayRobots, strategy.numClayRobots);

		assertEquals(clonedRobotStategy.numObsidianRobots, strategy.numObsidianRobots);
		assertEquals(clonedRobotStategy.numGeodeRobots, strategy.numGeodeRobots);
		assertEquals(clonedRobotStategy.numOreRobotsRequested, strategy.numOreRobotsRequested);
		assertEquals(clonedRobotStategy.numClayRobotsRequested, strategy.numClayRobotsRequested);
		assertEquals(clonedRobotStategy.numObsidianRobotsRequested, strategy.numObsidianRobotsRequested);
		assertEquals(clonedRobotStategy.numGeodeRobotsRequested, strategy.numGeodeRobotsRequested);

		// Shallow copy of blueprint.
		assertEquals(clonedRobotStategy.blueprint, strategy.blueprint);

		assertEquals(clonedRobotStategy.blueprint.oreRobotCost, strategy.blueprint.oreRobotCost);
		assertEquals(clonedRobotStategy.blueprint.clayRobotCost, strategy.blueprint.clayRobotCost);
		assertEquals(clonedRobotStategy.blueprint.obsidianRobotOreCost, strategy.blueprint.obsidianRobotOreCost);
		assertEquals(clonedRobotStategy.blueprint.obsidianRobotClayCost, strategy.blueprint.obsidianRobotClayCost);
		assertEquals(clonedRobotStategy.blueprint.geodeRobotOreCost, strategy.blueprint.geodeRobotOreCost);
		assertEquals(clonedRobotStategy.blueprint.geodeRobotObsidianCost, strategy.blueprint.geodeRobotObsidianCost);

		logger.log("End TestRobotCreation()");
	}

	void runNextMinutes(int num) {
		for (int i = 0; i < num; i++) {
			strategy.nextMinute();
			strategy.collectResources();
		}
	}

	void testMaterialTotals(int oreTotal, int clayTotal, int obsidianTotal, int geodeTotal) {
		assertEquals(oreTotal, strategy.oreTotal);
		assertEquals(clayTotal, strategy.clayTotal);
		assertEquals(obsidianTotal, strategy.obsidianTotal);
		assertEquals(geodeTotal, strategy.geodeTotal);
	}

	void testDecisions(boolean canBuildOreRobot, boolean canBuildClayRobot, boolean canBuildObsidianRobot, boolean canBuildGeodeRobot) {
		logger.log("01 testDecisions");
		assertEquals(canBuildOreRobot, strategy.canBuildOreRobot());
		logger.log("02 testDecisions");
		assertEquals(canBuildClayRobot, strategy.canBuildClayRobot());
		logger.log("03 testDecisions");
		assertEquals(canBuildObsidianRobot, strategy.canBuildObsidianRobot());
		logger.log("04 testDecisions");
		assertEquals(canBuildGeodeRobot, strategy.canBuildGeodeRobot());
		logger.log("05 testDecisions");
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

	@Test public void TestRobotCreationWithSimpleBlueprint() throws Exception {

		// Override the default strategy created by setUp().
		strategy = getSimpleStrategy();

		assertEquals(false, strategy.canBuildClayRobot());
		runNextMinutes(1);
		assertEquals(true, strategy.canBuildClayRobot());

		strategy.requestClayRobot();

		runNextMinutes(5);
		testNumRobots(1, 1, 0, 0);
		testMaterialTotals(5, 5, 0, 0);

		assertEquals(true, strategy.canBuildObsidianRobot());
		strategy.requestObsidianRobot();
		runNextMinutes(2);
		testNumRobots(1, 1, 1, 0);
		testMaterialTotals(6, 6, 2, 0);

		assertEquals(true, strategy.canBuildGeodeRobot());
		strategy.requestGeodeRobot();
		runNextMinutes(2);
		testNumRobots(1, 1, 1, 1);
		testMaterialTotals(7, 8, 3, 2);
	}

	@Test public void  TestCanBuildTheseRobots() {
		strategy = getSimpleStrategy();

		assertEquals(true, strategy.canBuildTheseRobots(false, false, false, false));

		logger.log("00");
		setMaterialTotals(1, 0, 0, 0);
		assertEquals(true, strategy.canBuildTheseRobots(true, false, false, false));
		assertEquals(true, strategy.canBuildTheseRobots(false, true, false, false));
		assertEquals(false, strategy.canBuildTheseRobots(true, true, false, false));

		logger.log("01");
		setMaterialTotals(1, 1, 0, 0);
		assertEquals(true, strategy.canBuildTheseRobots(true, false, false, false));
		assertEquals(true, strategy.canBuildTheseRobots(false, false, true, false));
		assertEquals(false, strategy.canBuildTheseRobots(true, false, true, false));
		assertEquals(false, strategy.canBuildTheseRobots(false, false, false, true));

		logger.log("02");
		setMaterialTotals(1, 0, 1, 0);
		assertEquals(true, strategy.canBuildTheseRobots(true, false, false, false));
		assertEquals(true, strategy.canBuildTheseRobots(false, false, false, true));
		assertEquals(false, strategy.canBuildTheseRobots(true, false, false, true));
	}

	@Test
	public void  TestWhenCollectingGeodesStarts() {

		strategy = getSimpleStrategy();
		while (strategy.minute < 1000 && strategy.geodeTotal == 0) {
			strategy.nextMinute();
			strategy.collectResources();

			if (strategy.canBuildClayRobot() && strategy.numClayRobots == 0) {
				strategy.requestClayRobot();
			}
			if (strategy.canBuildObsidianRobot() && strategy.numObsidianRobots == 0) {
				strategy.requestObsidianRobot();
			}
			if (strategy.canBuildGeodeRobot()) {
				break;

			}
		}
		assertEquals(3, strategy.minute);
		assertEquals(0, strategy.geodeTotal);

		strategy.requestGeodeRobot();
		while (strategy.minute < 1000 && strategy.geodeTotal == 0) {
			strategy.nextMinute();
			strategy.collectResources();
		}
		assertEquals(4, strategy.minute);
		assertEquals(1, strategy.geodeTotal);
	}

	public void setMaterialTotals(int oreTotal, int clayTotal, int obsidianTotal, int geodeTotal) {
		strategy.oreTotal = oreTotal;
		strategy.clayTotal = clayTotal;
		strategy.obsidianTotal = obsidianTotal;
		strategy.geodeTotal = geodeTotal;
	}

	public RobotStrategy getSimpleStrategy() {
		RobotStrategy strategy = new RobotStrategy();
		ArrayList<Integer> values = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1));
		strategy.blueprint = new Blueprint(10, values);
		return strategy;
	}
}
