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

			ArrayList<Integer> values = new ArrayList<Integer>(Arrays.asList(4, 2, 3, 14, 2, 7));
			strategy = new RobotStrategy(new Blueprint(1, values));
			assertEquals(strategy.getClass().getName(), "RobotStrategy");

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

		strategy.requestThisRobot("ore");

		testMaterialTotals(0, 0, 0, 0);

		testDecisions(false, false, false, false);

		testNumRobots(1, 0, 0, 0);

		testNumRobotsRequested(1, 0, 0, 0);
	}

	@Test public void TestRobotsRequestClayRobot() throws Exception {

		runNextMinutes(4);

		testDecisions(true, true, false, false);

		strategy.requestThisRobot("clay");

		testDecisions(false, true, false, false);

		testNumRobots(1, 0, 0, 0);

		testNumRobotsRequested(0, 1, 0, 0);

		strategy.requestThisRobot("clay");

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
		strategy.requestThisRobot("ore");
		strategy.requestThisRobot("clay");

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
		strategy.requestThisRobot("obsidian");
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
		strategy.requestThisRobot("geode");
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
		assertEquals(clonedRobotStategy.robots.get("ore").total, strategy.robots.get("ore").total);
		assertEquals(clonedRobotStategy.robots.get("clay").total, strategy.robots.get("clay").total);
		assertEquals(clonedRobotStategy.robots.get("obsidian").total, strategy.robots.get("obsidian").total);
		assertEquals(clonedRobotStategy.robots.get("geode").total, strategy.robots.get("geode").total);

		assertEquals(clonedRobotStategy.robots.get("ore").numRobots, strategy.robots.get("ore").numRobots);	
		assertEquals(clonedRobotStategy.robots.get("clay").numRobots, strategy.robots.get("clay").numRobots);	
		assertEquals(clonedRobotStategy.robots.get("obsidian").numRobots, strategy.robots.get("obsidian").numRobots);	
		assertEquals(clonedRobotStategy.robots.get("geode").numRobots, strategy.robots.get("geode").numRobots);	

		for (String label : strategy.robotList) {
			assertEquals(clonedRobotStategy.robots.get(label).numRobotsRequested, strategy.robots.get(label).numRobotsRequested);
		}

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
		assertEquals(oreTotal, strategy.robots.get("ore").total);
		assertEquals(clayTotal, strategy.robots.get("clay").total);
		assertEquals(obsidianTotal, strategy.robots.get("obsidian").total);
		assertEquals(geodeTotal, strategy.robots.get("geode").total);
	}

	void testDecisions(boolean canBuildOreRobot, boolean canBuildClayRobot, boolean canBuildObsidianRobot, boolean canBuildGeodeRobot) {
		logger.log("01 testDecisions");
		assertEquals(canBuildOreRobot, strategy.canBuildThisRobot("ore"));
		logger.log("02 testDecisions");
		assertEquals(canBuildClayRobot, strategy.canBuildThisRobot("clay"));
		logger.log("03 testDecisions");
		assertEquals(canBuildObsidianRobot, strategy.canBuildThisRobot("obsidian"));
		logger.log("04 testDecisions");
		assertEquals(canBuildGeodeRobot, strategy.canBuildThisRobot("geode"));
		logger.log("05 testDecisions");
	}

	void testNumRobots(int numOreRobots, int numClayRobots, int numObsidianRobots, int numGeodeRobots) {
		assertEquals(numOreRobots, strategy.robots.get("ore").numRobots);
		assertEquals(numClayRobots, strategy.robots.get("clay").numRobots);
		assertEquals(numObsidianRobots, strategy.robots.get("obsidian").numRobots);
		assertEquals(numGeodeRobots, strategy.robots.get("geode").numRobots);
	}

	void testNumRobotsRequested(int numOreRobotsRequested, int numClayRobotsRequested, int numObsidianRobotsRequested, int numGeodeRobotsRequested) {
		assertEquals(numOreRobotsRequested, strategy.robots.get("ore").numRobotsRequested);
		assertEquals(numClayRobotsRequested, strategy.robots.get("clay").numRobotsRequested);
		assertEquals(numObsidianRobotsRequested, strategy.robots.get("obsidian").numRobotsRequested);
		assertEquals(numGeodeRobotsRequested, strategy.robots.get("geode").numRobotsRequested);
	}

	@Test public void TestRobotCreationWithSimpleBlueprint() throws Exception {

		// Override the default strategy created by setUp().
		strategy = getSimpleStrategy();

		assertEquals(false, strategy.canBuildThisRobot("clay"));
//		runNextMinutes(1);
//		assertEquals(true, strategy.canBuildThisRobot("clay"));

//		strategy.requestThisRobot("clay");
//
//		runNextMinutes(5);
//		testNumRobots(1, 1, 0, 0);
//		testMaterialTotals(5, 5, 0, 0);
//
//		assertEquals(true, strategy.canBuildThisRobot("obsidian"));
//		strategy.requestThisRobot("obsidian");
//		runNextMinutes(2);
//		testNumRobots(1, 1, 1, 0);
//		testMaterialTotals(6, 6, 2, 0);
//
//		assertEquals(true, strategy.canBuildThisRobot("geode"));
//		strategy.requestThisRobot("geode");
//		runNextMinutes(2);
//		testNumRobots(1, 1, 1, 1);
//		testMaterialTotals(7, 8, 3, 2);
	}

	@Test public void  TestCanBuildThisRobot() throws Exception {
		strategy = getSimpleStrategy();

		assertEquals(true, strategy.canBuildThisRobot("none"));

		logger.log("00");
		setMaterialTotals(1, 0, 0, 0);
		assertEquals(true, strategy.canBuildThisRobot("ore"));
		assertEquals(true, strategy.canBuildThisRobot("clay"));
		assertEquals(false, strategy.canBuildThisRobot("obsidian"));

		logger.log("01");
		setMaterialTotals(1, 1, 0, 0);
		assertEquals(true, strategy.canBuildThisRobot("ore"));
		assertEquals(true, strategy.canBuildThisRobot("clay"));
		assertEquals(true, strategy.canBuildThisRobot("obsidian"));
		assertEquals(false, strategy.canBuildThisRobot("geode"));

		logger.log("02");
		setMaterialTotals(1, 0, 1, 0);
		assertEquals(true, strategy.canBuildThisRobot("ore"));
		assertEquals(true, strategy.canBuildThisRobot("geode"));
		assertEquals(false, strategy.canBuildThisRobot("obsidian"));
	}

	@Test
	public void  TestWhenCollectingGeodesStarts() {

		strategy = getSimpleStrategy();
		while (strategy.minute < 1000 && strategy.robots.get("geode").numRobots == 0) {
			strategy.nextMinute();
			strategy.collectResources();

			if (strategy.canBuildThisRobot("clay") && strategy.robots.get("clay").numRobots == 0) {
				strategy.requestThisRobot("clay");
			}
			if (strategy.canBuildThisRobot("obsidian") && strategy.robots.get("obsidian").numRobots == 0) {
				strategy.requestThisRobot("obsidian");
			}
			if (strategy.canBuildThisRobot("geode")) {
				break;

			}
		}
		assertEquals(3, strategy.minute);
		assertEquals(0, strategy.robots.get("geode").total);

		strategy.requestThisRobot("geode");
		while (strategy.minute < 1000 && strategy.robots.get("geode").total == 0) {
			strategy.nextMinute();
			strategy.collectResources();
		}
		assertEquals(4, strategy.minute);
		assertEquals(1, strategy.robots.get("geode").total);
	}

	public void setMaterialTotals(int oreTotal, int clayTotal, int obsidianTotal, int geodeTotal) {
		strategy.robots.get("ore").total = oreTotal;
		strategy.robots.get("clay").total = clayTotal;
		strategy.robots.get("obsidian").total = obsidianTotal;
		strategy.robots.get("geode").total = geodeTotal;
	}

	public RobotStrategy getSimpleStrategy() {
		ArrayList<Integer> values = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1));
		RobotStrategy strategy = new RobotStrategy(new Blueprint(10, values));
		return strategy;
	}
}
