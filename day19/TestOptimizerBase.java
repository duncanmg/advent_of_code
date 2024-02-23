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
public class TestOptimizerBase {

	public static void main(String[] args) throws Exception {
		TestOptimizerBase obj = new TestOptimizerBase();
	}

	Logger logger = new Logger(this, true);

	public void testNumRobots(RobotStrategy robotStrategy, int numOreRobots, int numClayRobots, int numObsidianRobots, int numGeodeRobots) {
		logger.log("robotStrategy.robots.get(\"ore\").numRobots = " + robotStrategy.robots.get("ore").numRobots);
		assertEquals(numOreRobots, robotStrategy.robots.get("ore").numRobots);
		logger.log("robotStrategy.robots.get(\"clay\").numRobots = " + robotStrategy.robots.get("clay").numRobots);
		assertEquals(numClayRobots, robotStrategy.robots.get("clay").numRobots);
		logger.log("robotStrategy.robots.get(\"obsidian\").numRobots = " + robotStrategy.robots.get("obsidian").numRobots);
		assertEquals(numObsidianRobots, robotStrategy.robots.get("obsidian").numRobots);
		logger.log("robotStrategy.robots.get(\"geode\").numRobots = " + robotStrategy.robots.get("geode").numRobots);
		assertEquals(numGeodeRobots, robotStrategy.robots.get("geode").numRobots);
	}

	public void testMaterialTotals(RobotStrategy robotStrategy, int oreTotal, int clayTotal, int obsidianTotal, int geodeTotal) {

		ArrayList<Integer>expectedTotals = new ArrayList<Integer>(Arrays.asList(oreTotal, clayTotal, obsidianTotal, geodeTotal));
		ArrayList<String>labels = new ArrayList<String>(Arrays.asList("ore", "clay", "obsidian", "geode"));

		int i = 0;
		for(String label : labels) {
			logger.log("testMaterialTotals " + label);
			assertEquals((long)expectedTotals.get(i), (long)robotStrategy.robots.get(label).total);
			i++;
		}
	}

	void testNumRobotsRequested(RobotStrategy robotStrategy, int numOreRobotsRequested, int numClayRobotsRequested, int numObsidianRobotsRequested, int numGeodeRobotsRequested) {
		logger.log("robotStrategy is not null");

		logger.log("robotStrategy.robots.get(\"ore\").numRobotsRequested = " + robotStrategy.robots.get("ore").numRobotsRequested);
		assertEquals(numOreRobotsRequested, robotStrategy.robots.get("ore").numRobotsRequested);

		logger.log("robotStrategy.robots.get(\"clay\").numRobotsRequested = " + robotStrategy.robots.get("clay").numRobotsRequested);
		assertEquals(numClayRobotsRequested, robotStrategy.robots.get("clay").numRobotsRequested);

		logger.log("robotStrategy.robots.get(\"obsidian\").numRobotsRequested = " + robotStrategy.robots.get("obsidian").numRobotsRequested);
		assertEquals(numObsidianRobotsRequested, robotStrategy.robots.get("obsidian").numRobotsRequested);

		logger.log("robotStrategy.robots.get(\"geode\").numRobotsRequested = " + robotStrategy.robots.get("geode").numRobotsRequested);
		assertEquals(numGeodeRobotsRequested, robotStrategy.robots.get("geode").numRobotsRequested);
	}

	// This accepts an ArrayList of RobotStrategy objects and a set of expected values. It iterates
	// through the ArrayList and looks for a RobotStrategy with the expected values. Returns true if
	// at least one exists.
	// The expected value -1 is treated as a wildcard.
	boolean anyRobotStrategiesHaveNumRobots(ArrayList<RobotStrategy> robotStrategies, 
			int numOreRobots, int numClayRobots, int numObsidianRobots, int numGeodeRobots) {

		ArrayList<Integer>expectedNumRobots = new ArrayList<Integer>(Arrays.asList(numOreRobots, 
			numClayRobots,  numObsidianRobots, numGeodeRobots));

		ArrayList<String>labels = new ArrayList<String>(Arrays.asList("ore", "clay", "obsidian", "geode"));

		boolean ok = false;
		for (RobotStrategy robotStrategy : robotStrategies) {
			int i = 0;
			int correct = 0;
			for(String label : labels) {
				if (expectedNumRobots.get(i) < 0) {
					correct++;
				}
				else if ((long)expectedNumRobots.get(i) == (long)robotStrategy.robots.get(label).numRobots) {
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

	// This accepts an ArrayList of RobotStrategy objects and a set of expected values. It iterates
	// through the ArrayList and looks for a RobotStrategy with none of the expected values. Returns true if
	// at least one exists.
	// The expected value -1 is treated as a wildcard.
	boolean noRobotStrategiesHaveNumRobots(ArrayList<RobotStrategy> robotStrategies, 
			int numOreRobots, int numClayRobots, int numObsidianRobots, int numGeodeRobots) {

		ArrayList<Integer>expectedNumRobots = new ArrayList<Integer>(Arrays.asList(numOreRobots, numClayRobots,  
			numObsidianRobots, numGeodeRobots));

		ArrayList<String>labels = new ArrayList<String>(Arrays.asList("ore", "clay", "obsidian", "geode"));

		boolean ok = true;
		for (RobotStrategy robotStrategy : robotStrategies) {
			int i = 0;
			int correct = 0;
			for(String label : labels) {
				if ((long)expectedNumRobots.get(i) < 0) {
					continue;
				}
				if ((long)expectedNumRobots.get(i) == (long)robotStrategy.robots.get(label).numRobots) {
					logger.log("anyRobotStrategiesHaveNumRobots Correct: " + robotStrategy);
					ok = false;
				}
				i++;
			}
		}
		return ok;
	}

	// This accepts an ArrayList of RobotStrategy objects and a set of expected values. It iterates
	// through the ArrayList and looks for a RobotStrategy with the expected values. Returns true if
	// at least one exists.
	// The expected value -1 is treated as a wildcard.
	boolean anyRobotStrategiesHaveNumRobotsRequested(ArrayList<RobotStrategy> robotStrategies, 
			int numOreRobotsRequested, int numClayRobotsRequested, int numObsidianRobotsRequested, int numGeodeRobotsRequested) {

		ArrayList<Integer>expectedNumRobotsRequested = new ArrayList<Integer>(Arrays.asList(numOreRobotsRequested, 
			numClayRobotsRequested,  numObsidianRobotsRequested, numGeodeRobotsRequested));

		ArrayList<String>labels = new ArrayList<String>(Arrays.asList("ore", "clay", "obsidian", "geode"));

		boolean ok = false;
		for (RobotStrategy robotStrategy : robotStrategies) {
			int i = 0;
			int correct = 0;
			for(String label : labels) {
				if ((long)expectedNumRobotsRequested.get(i) == (long)robotStrategy.robots.get(label).numRobotsRequested) {
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
