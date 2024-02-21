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
public class TestOptimizerVariousBlueprints {

	public static void main(String[] args) throws Exception {
		TestOptimizerVariousBlueprints obj = new TestOptimizerVariousBlueprints();
	}

	Optimizer optimizer;

	Logger logger = new Logger(this, true);

	// Blueprint 1:
	// Each ore robot costs 4 ore.
	// Each clay robot costs 2 ore.
	// Each obsidian robot costs 3 ore and 14 clay.
	// Each geode robot costs 2 ore and 7 obsidian.

	ArrayList<Blueprint> testBlueprints = new ArrayList<Blueprint>();

	void buildBlueprints() {

		// Simple
		testBlueprints.add(new Blueprint(1, new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1))));

		// Lots of ore needed for geode robot.
		testBlueprints.add(new Blueprint(2, new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 10, 1))));
		logger.log("XX Hi " + testBlueprints.size() + " " + counter);
	}

	int counter = 0;

	@Before
		public void setUp() throws Exception {
		logger.log("01 Hi");
			if (testBlueprints.size() == 0){
		logger.log("02 Hi");
				buildBlueprints();
			}
		logger.log("03 Hi");
			optimizer = new Optimizer();
		logger.log("04 Hi");
			assertEquals(optimizer.getClass().getName(), "Optimizer");
		logger.log("05 Hi");
			ArrayList<Blueprint> blueprints = new ArrayList<Blueprint>();
		logger.log("06 Hi " + testBlueprints.size() + " " + counter);
			blueprints.add(testBlueprints.get(counter));
		logger.log("07 Hi");
			counter++;
		logger.log("08 Hi");
			optimizer.blueprints = blueprints;
		logger.log("09 Hi");

		}

	RobotStrategy bestRobotStrategy = new RobotStrategy();

//	// Request first obsidian robot.
//	@Test public void testBlueprintOne11() throws Exception {
//		optimizer.maxMinutes = 11;
//		logger.log("Bang");
//		bestRobotStrategy = optimizer.optimizeBlueprint(optimizer.blueprints.get(0));
//		// assertEquals(0, maxGeodes);
//	}

//	// This is the first geode allegedly collected!
//	// Building a massive surplus of clay. Only 1 ore robot.
//	@Test public void testBlueprintOne19() throws Exception {
//		optimizer.maxMinutes = 19;
//		int maxGeodes = optimizer.optimize();
//		assertEquals(1, maxGeodes);
//	}

	@Test public void testBlueprintZero() throws Exception {
		logger.log("Hi");
		optimizer.maxMinutes = 7;
		int maxGeodes = optimizer.optimize();
		// testMaterialTotals(4, 25, 7, 2);
		assertEquals(1, maxGeodes);
	}

	public void testNumRobots(int numOreRobots, int numClayRobots, int numObsidianRobots, int numGeodeRobots) {
		logger.log("bestRobotStrategy is not null");
		logger.log("bestRobotStrategy.robots.get(\"ore\").numRobots = " + bestRobotStrategy.robots.get("ore").numRobots);
		assertEquals(numOreRobots, bestRobotStrategy.robots.get("ore").numRobots);
		logger.log("bestRobotStrategy.robots.get(\"clay\").numRobots = " + bestRobotStrategy.robots.get("clay").numRobots);
		assertEquals(numClayRobots, bestRobotStrategy.robots.get("clay").numRobots);
		logger.log("bestRobotStrategy.robots.get(\"obsidian\").numRobots = " + bestRobotStrategy.robots.get("obsidian").numRobots);
		assertEquals(numObsidianRobots, bestRobotStrategy.robots.get("obsidian").numRobots);
		logger.log("bestRobotStrategy.robots.get(\"geode\").numRobots = " + bestRobotStrategy.robots.get("geode").numRobots);
		assertEquals(numGeodeRobots, bestRobotStrategy.robots.get("geode").numRobots);
	}

	public void testMaterialTotals(int oreTotal, int clayTotal, int obsidianTotal, int geodeTotal) {

		ArrayList<Integer>expectedTotals = new ArrayList<Integer>(Arrays.asList(oreTotal, clayTotal, obsidianTotal, geodeTotal));
		ArrayList<String>labels = new ArrayList<String>(Arrays.asList("ore", "clay", "obsidian", "geode"));

		int i = 0;
		for(String label : labels) {
			logger.log("testMaterialTotals " + label);
			assertEquals((long)expectedTotals.get(i), (long)bestRobotStrategy.robots.get("label").total);
		}
	}

	void testNumRobotsRequested(int numOreRobotsRequested, int numClayRobotsRequested, int numObsidianRobotsRequested, int numGeodeRobotsRequested) {
		logger.log("bestRobotStrategy is not null");

		logger.log("bestRobotStrategy.robots.get(\"ore\").numRobotsRequested = " + bestRobotStrategy.robots.get("ore").numRobotsRequested);
		assertEquals(numOreRobotsRequested, bestRobotStrategy.robots.get("ore").numRobotsRequested);

		logger.log("bestRobotStrategy.robots.get(\"clay\").numRobotsRequested = " + bestRobotStrategy.robots.get("clay").numRobotsRequested);
		assertEquals(numClayRobotsRequested, bestRobotStrategy.robots.get("clay").numRobotsRequested);

		logger.log("bestRobotStrategy.robots.get(\"obsidian\").numRobotsRequested = " + bestRobotStrategy.robots.get("obsidian").numRobotsRequested);
		assertEquals(numObsidianRobotsRequested, bestRobotStrategy.robots.get("obsidian").numRobotsRequested);

		logger.log("bestRobotStrategy.robots.get(\"geode\").numRobotsRequested = " + bestRobotStrategy.robots.get("geode").numRobotsRequested);
		assertEquals(numGeodeRobotsRequested, bestRobotStrategy.robots.get("geode").numRobotsRequested);
	}
}
