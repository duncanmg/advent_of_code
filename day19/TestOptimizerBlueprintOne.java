//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;
import org.junit.Before;

// This test class verifies the behaviour with a single blueprint as set out in https://adventofcode.com/2022/day/19.
// The expected values are taken directly from the example and can be assumed to be correct.
public class TestOptimizerBlueprintOne {

	public static void main(String[] args) throws Exception {
		TestOptimizerBlueprintOne obj = new TestOptimizerBlueprintOne();
	}

	Optimizer optimizer;

	ArrayList<Blueprint> blueprints;

	Logger logger = new Logger(this, true);

	// Blueprint 1:
	// Each ore robot costs 4 ore.
	// Each clay robot costs 2 ore.
	// Each obsidian robot costs 3 ore and 14 clay.
	// Each geode robot costs 2 ore and 7 obsidian.

	@Before
		public void setUp() throws Exception {
			logger.log("01 setUp()");
			optimizer = new Optimizer();
			assertEquals(optimizer.getClass().getName(), "Optimizer");
			blueprints = new ArrayList<Blueprint>();
			Blueprint bp = new Blueprint(1, new ArrayList<Integer>(Arrays.asList(4, 2, 3, 14, 2, 7)));
			blueprints.add(bp);
			optimizer.blueprints = blueprints;

		}

		RobotStrategy bestRobotStrategy = new RobotStrategy();

	//	@Test public void testBlueprintOne00() throws Exception {
	//		optimizer.maxMinutes = 0;
	//		int maxGeodes = optimizer.optimize();
	//		testNumRobots(1, 0, 0, 0);
	//		testMaterialTotals(0, 0, 0, 0);
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne01() throws Exception {
	//		optimizer.maxMinutes = 1;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(1, 0, 0, 0);
	//		testNumRobots(1, 0, 0, 0);
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne02() throws Exception {
	//		optimizer.maxMinutes = 2;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(2, 0, 0, 0);
	//		testNumRobots(1, 0, 0, 0);
	//		testNumRobotsRequested(0, 0, 0, 0);
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	// The new rules means it will always build a robot if it can.
	//	@Test public void testBlueprintOne03() throws Exception {
	//		optimizer.maxMinutes = 3;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(1, 0, 0, 0);
	//		testNumRobots(1, 0, 0, 0);
	//		testNumRobotsRequested(0, 1, 0, 0);
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	// It can't create a robot, so it just collects.
	//	@Test public void testBlueprintOne04() throws Exception {
	//		optimizer.maxMinutes = 4;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(2, 1, 0, 0);
	//		testNumRobots(1, 1, 0, 0);
	//		testNumRobotsRequested(0, 0, 0, 0);
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne05() throws Exception {
	//		optimizer.maxMinutes = 5;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(1, 2, 0, 0);
	//		testNumRobots(1, 1, 0, 0);
	//		testNumRobotsRequested(0, 1, 0, 0);
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne06() throws Exception {
	//		optimizer.maxMinutes = 6;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(2, 4, 0, 0);
	//		testNumRobots(1, 2, 0, 0);
	//		testNumRobotsRequested(0, 0, 0, 0);
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	// Requests yet another clay robot. Yes. These expected values
	//	// are taken from the example.
	//	@Test public void testBlueprintOne07() throws Exception {
	//		optimizer.maxMinutes = 7;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(1, 6, 0, 0);
	//		testNumRobots(1, 2, 0, 0);
	//		testNumRobotsRequested(0, 1, 0, 0);
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne08() throws Exception {
	//		optimizer.maxMinutes = 8;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(2, 9, 0, 0);
	//		testNumRobots(1, 3, 0, 0);
	//		testNumRobotsRequested(0, 0, 0, 0);
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne09() throws Exception {
	//		optimizer.maxMinutes = 9;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(3, 12, 0, 0);
	//		testNumRobots(1, 3, 0, 0);
	//		testNumRobotsRequested(0, 0, 0, 0);
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne10() throws Exception {
	//		optimizer.maxMinutes = 10;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(4, 15, 0, 0);
	//		testNumRobots(1, 3, 0, 0);
	//		testNumRobotsRequested(0, 0, 0, 0);
	//		assertEquals(0, maxGeodes);
	//	}

	// Request first obsidian robot.
	@Test public void testBlueprintOne11() throws Exception {
		optimizer.maxMinutes = 11;
		logger.log("Bang");
		ArrayList<RobotStrategy> bestRobotStrategies = optimizer.optimizeBlueprint(optimizer.blueprints.get(0));
		bestRobotStrategy = bestRobotStrategies.get(0);
		// testMaterialTotals(2, 4, 0, 0);
		// testNumRobots(1, 3, 0, 0);
		// testNumRobotsRequested(0, 0, 1, 0);
		// assertEquals(0, maxGeodes);
	}

//	// Requests an obsidian robot. Still only 1 ore robot.
//	@Test public void testBlueprintOne12() throws Exception {
//		optimizer.maxMinutes = 12;
//		int maxGeodes = optimizer.optimize();
//		// testMaterialTotals(1, 7, 1, 0);
//		testMaterialTotals(1, 7, 20000, 0);
//		testNumRobots(1, 3, 1, 0);
//		testNumRobotsRequested(0, 1, 0, 0);
//		assertEquals(10, maxGeodes);
//	}

	//	//	@Test public void testBlueprintOne13() throws Exception {
	//	//		optimizer.maxMinutes = 13;
	//	//		int maxGeodes = optimizer.optimize();
	//	//		testMaterialTotals(2, 11, 2, 0);
	//	//		assertEquals(0, maxGeodes);
	//	//	}
	//	//
	//	//	@Test public void testBlueprintOne14() throws Exception {
	//	//		optimizer.maxMinutes = 14;
	//	//		int maxGeodes = optimizer.optimize();
	//	//		testMaterialTotals(3, 15, 3, 0);
	//	//		assertEquals(0, maxGeodes);
	//	//	}
	//	//
	//	//	@Test public void testBlueprintOne15() throws Exception {
	//	//		optimizer.maxMinutes = 15;
	//	//		int maxGeodes = optimizer.optimize();
	//	//		testMaterialTotals(1, 5, 4, 0);
	//	//		assertEquals(0, maxGeodes);
	//	//	}
	//
	//	//	@Test public void testBlueprintOne16() throws Exception {
	//	//		optimizer.maxMinutes = 16;
	//	//		int maxGeodes = optimizer.optimize();
	//	//		testMaterialTotals(2, 9, 6, 0);
	//	//		assertEquals(0, maxGeodes);
	//	//	}
	//
	//	//	@Test public void testBlueprintOne17() throws Exception {
	//	//		optimizer.maxMinutes = 17;
	//	//		int maxGeodes = optimizer.optimize();
	//	//		testMaterialTotals(3, 13, 8, 0);
	//	//		assertEquals(0, maxGeodes);
	//	//	}
	//	//
	//	//	@Test public void testBlueprintOne18() throws Exception {
	//	//		optimizer.maxMinutes = 18;
	//	//		int maxGeodes = optimizer.optimize();
	//	//		testMaterialTotals(2, 17, 3, 0);
	//	//		assertEquals(0, maxGeodes);
	//	//	}
	//
		// This is the first geode allegedly collected!
		// Building a massive surplus of clay. Only 1 ore robot.
		@Test public void testBlueprintOne19() throws Exception {
			optimizer.maxMinutes = 19;
//			testMaterialTotals(3, 21, 5, 1);
//			testNumRobots(1, 4, 2, 1);
//			testNumRobotsRequested(0, 0, 0, 0);
			int maxGeodes = optimizer.optimize();
			assertEquals(1, maxGeodes);
		}
	
		@Test public void testBlueprintOne20() throws Exception {
			optimizer.maxMinutes = 20;
			int maxGeodes = optimizer.optimize();
			// testMaterialTotals(4, 25, 7, 2);
			assertEquals(2, maxGeodes);
		}
	
	//	@Test public void testBlueprintOne21() throws Exception {
	//		optimizer.maxMinutes = 21;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(3, 29, 2, 3);
	//		assertEquals(3, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne22() throws Exception {
	//		optimizer.maxMinutes = 22;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(4, 33, 4,5);
	//		assertEquals(5, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne23() throws Exception {
	//		optimizer.maxMinutes = 23;
	//		int maxGeodes = optimizer.optimize();
	//		testMaterialTotals(5, 37, 6, 7);
	//		assertEquals(7, maxGeodes);
	//	}
	//
//		@Test public void testBlueprintOne24() throws Exception {
//			optimizer.maxMinutes = 24;
//			int maxGeodes = optimizer.optimize();
//			testMaterialTotals(6, 41, 8, 9);
//			assertEquals(9, maxGeodes);
//		}

	public void testNumRobots(int numOreRobots, int numClayRobots, int numObsidianRobots, int numGeodeRobots) {
		HashMap<String, Robot> robots = bestRobotStrategy.robots;

		logger.log("robots.get(\"ore\").numRobots = " + robots.get("ore").numRobots);
		assertEquals(numOreRobots, robots.get("ore").numRobots);

		logger.log("robots.get(\"clay\").numRobots = " + robots.get("clay").numRobots);
		assertEquals(numClayRobots, robots.get("clay").numRobots);

		logger.log("robots.get(\"obsidian\").numRobots = " + robots.get("obsidian").numRobots);
		assertEquals(numObsidianRobots, robots.get("obsidian").numRobots);

		logger.log("robots.get(\"geode\").numRobots = " + robots.get("geode").numRobots);
		assertEquals(numGeodeRobots, robots.get("geode").numRobots);
	}

	public void testMaterialTotals(int oreTotal, int clayTotal, int obsidianTotal, int geodeTotal) {
		HashMap<String, Robot> robots = bestRobotStrategy.robots;

		logger.log("robots.get(\"ore\").total = " + robots.get("ore").total);
		assertEquals(oreTotal, robots.get("ore").total);

		logger.log("robots.get(\"clay\").total = " + robots.get("clay").total);
		assertEquals(clayTotal, robots.get("clay").total);

		logger.log("robots.get(\"obsidian\").total = " + robots.get("obsidian").total);
		assertEquals(obsidianTotal, robots.get("obsidian").total);

		logger.log("robots.get(\"geode\").total = " + robots.get("geode").total);
		assertEquals(geodeTotal, robots.get("geode").total);
	}

	void testNumRobotsRequested(int numOreRobotsRequested, int numClayRobotsRequested, int numObsidianRobotsRequested, int numGeodeRobotsRequested) {
		HashMap<String, Robot> robots = bestRobotStrategy.robots;

		logger.log("robots.get(\"ore\").numRobotsRequested = " + robots.get("ore").numRobotsRequested);
		assertEquals(numOreRobotsRequested, robots.get("ore").numRobotsRequested);

		logger.log("robots.get(\"clay\").numRobotsRequested = " + robots.get("clay").numRobotsRequested);
		assertEquals(numClayRobotsRequested, robots.get("clay").numRobotsRequested);

		logger.log("robots.get(\"obsidian\").numRobotsRequested = " + robots.get("obsidian").numRobotsRequested);
		assertEquals(numObsidianRobotsRequested, robots.get("obsidian").numRobotsRequested);

		logger.log("robots.get(\"geode\").numRobotsRequested = " + robots.get("geode").numRobotsRequested);
		assertEquals(numGeodeRobotsRequested, robots.get("geode").numRobotsRequested);
	}
}
