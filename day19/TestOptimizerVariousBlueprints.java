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

		testBlueprints.add(new Blueprint(2, new ArrayList<Integer>(Arrays.asList(100, 100, 100, 100, 100, 100))));

		testBlueprints.add(new Blueprint(3, new ArrayList<Integer>(Arrays.asList(100, 2, 100, 100, 100, 100))));

		testBlueprints.add(new Blueprint(4, new ArrayList<Integer>(Arrays.asList(100, 2, 1, 1, 100, 100))));

		// Lots of ore needed for geode robot.
		// Minute 1: 1 0 0 0
		// Minute 2: 1 0 0 0 Ore robot requested
		// Minute 3: 2 0 0 0 Ore robot requested
		// Minute 4: 4 0 0 0 Clay Robot Requested
		// Minute 5: 6 0 0 0 Obsidian Robot Requested
		// Minute 6: 9 1 1 0
		// Minute 7: 12 1 1 0 Geode Robot Requested
		// Minute 8: 5 2 2 1 Geode Collected.
		testBlueprints.add(new Blueprint(1, new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 10, 1))));
	}

		public void setUp(int blueprintIndex) throws Exception {
			if (testBlueprints.size() == 0){
				buildBlueprints();
			}

			optimizer = new Optimizer();
			optimizer.returnOneBestStrategy = false;
			assertEquals(optimizer.getClass().getName(), "Optimizer");

			ArrayList<Blueprint> blueprints = new ArrayList<Blueprint>();
			blueprints.add(testBlueprints.get(blueprintIndex));

			optimizer.blueprints = blueprints;

			logger.log("End setUp " + blueprintIndex);

		}

	RobotStrategy bestRobotStrategy = new  RobotStrategy();

	@Test public void testBlueprintId1() throws Exception {
		setUp(0);
		optimizer.maxMinutes = 7;
		int totalQuality = optimizer.optimize();
		assertEquals(1, totalQuality);
	}

//	@Test public void testBlueprintId2() throws Exception {
//		setUp(1);
//		optimizer.maxMinutes = 11;
//		int totalQuality = optimizer.optimize();
//		bestRobotStrategy = optimizer.bestRobotStrategies.get(0);
//
//		testNumRobots(1, 0, 0, 0);
//		testMaterialTotals(11, 0, 0, 0);
//		assertEquals(0, totalQuality);
//	}

//	@Test public void testBlueprintId3() throws Exception {
//		setUp(2);
//		optimizer.maxMinutes = 4;
//		int totalQuality = optimizer.optimize();
//		bestRobotStrategy = optimizer.bestRobotStrategies.get(0);
//
//		testNumRobots(1, 1, 0, 0);
//		testMaterialTotals(2, 1, 0, 0);
//		testNumRobotsRequested(0, 0, 0, 0);
//		assertEquals(0, totalQuality);
//	}
//
//	@Test public void testBlueprintId4() throws Exception {
//		setUp(3);
//		optimizer.maxMinutes = 5;
//		int totalQuality = optimizer.optimize();
//		bestRobotStrategy = optimizer.bestRobotStrategies.get(0);
//
//		assertEquals(5, optimizer.bestRobotStrategies.size());
//
//		assertEquals(true, anyRobotStrategiesHaveNumRobotsRequested(optimizer.bestRobotStrategies, 0, 0, 1, 0));
//		assertEquals(true, noRobotStrategiesHaveNumRobots(optimizer.bestRobotStrategies, -1, -1, 1, -1));
//		assertEquals(true, noRobotStrategiesHaveNumRobots(optimizer.bestRobotStrategies, -1, -1, -1, 1));
//		assertEquals(0, totalQuality);
//	}
//
//	@Test public void testBlueprintId4b() throws Exception {
//		setUp(3);
//		optimizer.maxMinutes = 6;
//		int totalQuality = optimizer.optimize();
//		bestRobotStrategy = optimizer.bestRobotStrategies.get(0);
//
//		assertEquals(9, optimizer.bestRobotStrategies.size());
//
//		assertEquals(true, anyRobotStrategiesHaveNumRobots(optimizer.bestRobotStrategies, -1, -1, 1, 0));
//		assertEquals(true, noRobotStrategiesHaveNumRobots(optimizer.bestRobotStrategies, -1, -1, -1, 1));
//		assertEquals(0, totalQuality);
//	}

	public void testNumRobots(int numOreRobots, int numClayRobots, int numObsidianRobots, int numGeodeRobots) {
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
			assertEquals((long)expectedTotals.get(i), (long)bestRobotStrategy.robots.get(label).total);
			i++;
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

	boolean anyRobotStrategiesHaveNumRobots(ArrayList<RobotStrategy> robotStrategies, int numOreRobots, int numClayRobots, int numObsidianRobots, int numGeodeRobots) {
		ArrayList<Integer>expectedNumRobots = new ArrayList<Integer>(Arrays.asList(numOreRobots, numClayRobots,  numObsidianRobots, numGeodeRobots));
		ArrayList<String>labels = new ArrayList<String>(Arrays.asList("ore", "clay", "obsidian", "geode"));

		boolean ok = false;
		for (RobotStrategy robotStrategy : robotStrategies) {
			int i = 0;
			int correct = 0;
			for(String label : labels) {
				if (expectedNumRobots.get(i) < 0) {
					correct++;
				}
				else if ((long)expectedNumRobots.get(i) == (long)bestRobotStrategy.robots.get(label).numRobots) {
					correct++;
				}
				if (correct == 4) {
					logger.log("anyRobotStrategiesHaveNumRobots Correct: " + robotStrategy);
					ok = true;
				}
				i++;
			}
		}
		return ok;
	}

	boolean noRobotStrategiesHaveNumRobots(ArrayList<RobotStrategy> robotStrategies, int numOreRobots, int numClayRobots, int numObsidianRobots, int numGeodeRobots) {
		ArrayList<Integer>expectedNumRobots = new ArrayList<Integer>(Arrays.asList(numOreRobots, numClayRobots,  numObsidianRobots, numGeodeRobots));
		ArrayList<String>labels = new ArrayList<String>(Arrays.asList("ore", "clay", "obsidian", "geode"));

		boolean ok = true;
		for (RobotStrategy robotStrategy : robotStrategies) {
			int i = 0;
			int correct = 0;
			for(String label : labels) {
				if ((long)expectedNumRobots.get(i) < 0) {
					continue;
				}
				if ((long)expectedNumRobots.get(i) == (long)bestRobotStrategy.robots.get(label).numRobots) {
					logger.log("anyRobotStrategiesHaveNumRobots Correct: " + robotStrategy);
					ok = false;
				}
				i++;
			}
		}
		return ok;
	}

	boolean anyRobotStrategiesHaveNumRobotsRequested(ArrayList<RobotStrategy> robotStrategies, int numOreRobotsRequested, int numClayRobotsRequested, int numObsidianRobotsRequested, int numGeodeRobotsRequested) {
		ArrayList<Integer>expectedNumRobotsRequested = new ArrayList<Integer>(Arrays.asList(numOreRobotsRequested, numClayRobotsRequested,  numObsidianRobotsRequested, numGeodeRobotsRequested));
		ArrayList<String>labels = new ArrayList<String>(Arrays.asList("ore", "clay", "obsidian", "geode"));

		boolean ok = false;
		for (RobotStrategy robotStrategy : robotStrategies) {
			int i = 0;
			int correct = 0;
			for(String label : labels) {
				if ((long)expectedNumRobotsRequested.get(i) == (long)bestRobotStrategy.robots.get(label).numRobotsRequested) {
					correct++;
				}
				if (correct == 4) {
					logger.log("anyRobotStrategiesHaveNumRobotsRequested Correct: " + robotStrategy);
					ok = true;
				}
				i++;
			}
		}
		return ok;
	}
}
