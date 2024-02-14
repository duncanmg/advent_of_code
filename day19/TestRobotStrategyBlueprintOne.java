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

public class TestRobotStrategyBlueprintOne {

	public static void main(String[] args) throws Exception {
		TestRobotStrategyBlueprintOne obj = new TestRobotStrategyBlueprintOne();
	}

	RobotStrategy strategy;

	Logger logger = new Logger(this, true);

	@Before
	public void setUp() throws Exception {
		if (strategy == null) {
			strategy = new RobotStrategy();
			assertEquals(strategy.getClass().getName(), "RobotStrategy");

			// These numbers are taken directly from blueprint one in the example data.
			ArrayList<Integer> values = new ArrayList<Integer>(Arrays.asList(4, 2, 3, 14, 2, 7));
			strategy.blueprint = new Blueprint(1, values);
		}
	}

	@Test public void TestBlueprintOne() throws Exception {

		assertEquals(0, strategy.minute);

		testMaterialTotals(0, 0, 0, 0);
		testNumRobots(1, 0, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		assertEquals(26.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.01);
		assertEquals("none", strategy.recommendBestStrategy());

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(1, strategy.minute);

		testMaterialTotals(1, 0, 0, 0);
		testNumRobots(1, 0, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		assertEquals(25.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.01);
		assertEquals("none", strategy.recommendBestStrategy());

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(2, strategy.minute);

		testMaterialTotals(2, 0, 0, 0);
		testNumRobots(1, 0, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		assertEquals(24.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.01);
		assertEquals("clay", strategy.recommendBestStrategy());

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(3, strategy.minute);

		// The time has gone up because resources have been spent on requesting a clay robot
		// which is not producing.
		strategy.requestClayRobot();
		assertEquals(25.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(1, 0, 0, 0);
		testNumRobots(1, 0, 0, 0);
		testNumRobotsRequested(0, 1, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(4, strategy.minute);

		assertEquals(22.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("clay", strategy.recommendBestStrategy());

		testMaterialTotals(2, 1, 0, 0);
		testNumRobots(1, 1, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(5, strategy.minute);

		strategy.requestClayRobot();
		assertEquals(21.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(1, 2, 0, 0);
		testNumRobots(1, 1, 0, 0);
		testNumRobotsRequested(0, 1, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(6, strategy.minute);

		assertEquals(14.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("clay", strategy.recommendBestStrategy());

		testMaterialTotals(2, 4, 0, 0);
		testNumRobots(1, 2, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(7, strategy.minute);

		strategy.requestClayRobot();
		assertEquals(13.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(1, 6, 0, 0);
		testNumRobots(1, 2, 0, 0);
		testNumRobotsRequested(0, 1, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(8, strategy.minute);

		assertEquals(10.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(2, 9, 0, 0);
		testNumRobots(1, 3, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(9, strategy.minute);

		assertEquals(9.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(3, 12, 0, 0);
		testNumRobots(1, 3, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(10, strategy.minute);

		assertEquals(9.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("obsidian", strategy.recommendBestStrategy());

		testMaterialTotals(4, 15, 0, 0);
		testNumRobots(1, 3, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(11, strategy.minute);

		strategy.requestObsidianRobot();
		assertEquals(12.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("clay", strategy.recommendBestStrategy());

		testMaterialTotals(2, 4, 0, 0);
		testNumRobots(1, 3, 0, 0);
		testNumRobotsRequested(0, 0, 1, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(12, strategy.minute);

		strategy.requestClayRobot();
		assertEquals(7.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(1, 7, 1, 0);
		testNumRobots(1, 3, 1, 0);
		testNumRobotsRequested(0, 1, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(13, strategy.minute);

		assertEquals(6.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(2, 11, 2, 0);
		testNumRobots(1, 4, 1, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(14, strategy.minute);

		assertEquals(5.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("obsidian", strategy.recommendBestStrategy());

		testMaterialTotals(3, 15, 3, 0);
		testNumRobots(1, 4, 1, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(15, strategy.minute);

		strategy.requestObsidianRobot();
		assertEquals(4.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(1, 5, 4, 0);
		testNumRobots(1, 4, 1, 0);
		testNumRobotsRequested(0, 0, 1, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(16, strategy.minute);

		assertEquals(1.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(2, 9, 6, 0);
		testNumRobots(1, 4, 2, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(17, strategy.minute);

		// Time down to 1 which is time to create geode robot. Request it in next minute.
		assertEquals(1.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(3, 13, 8, 0);
		testNumRobots(1, 4, 2, 0);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(18, strategy.minute);

		strategy.requestGeodeRobot();
		assertEquals(3.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(2, 17, 3, 0);
		testNumRobots(1, 4, 2, 0);
		testNumRobotsRequested(0, 0, 0, 1);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(19, strategy.minute);

		assertEquals(2.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("obsidian", strategy.recommendBestStrategy());

		testMaterialTotals(3, 21, 5, 1);
		testNumRobots(1, 4, 2, 1);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(20, strategy.minute);

		// Time down to 1 which is time to create geode robot. Request it in next minute.
		// Ignore recommendation.
		assertEquals(1.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(4, 25, 7, 2);
		testNumRobots(1, 4, 2, 1);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(21, strategy.minute);

		strategy.requestGeodeRobot();
		assertEquals(3.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("obsidian", strategy.recommendBestStrategy());

		testMaterialTotals(3, 29, 2, 3);
		testNumRobots(1, 4, 2, 1);
		testNumRobotsRequested(0, 0, 0, 1);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(22, strategy.minute);

		assertEquals(2.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(4, 33, 4, 5);
		testNumRobots(1, 4, 2, 2);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(23, strategy.minute);

		assertEquals(1.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(5, 37, 6, 7);
		testNumRobots(1, 4, 2, 2);
		testNumRobotsRequested(0, 0, 0, 0);

		logger.log("End minute " + strategy.minute + " ----------------------------------------");
		strategy.nextMinute();
		strategy.collectResources();
		assertEquals(24, strategy.minute);

		// Interesting. Don't request geode robot in last minute. Would it matter?
		assertEquals(1.0, strategy.calcTimeToGeodeRobot(false, false, false), 0.001);
		assertEquals("none", strategy.recommendBestStrategy());

		testMaterialTotals(6, 41, 8, 9);
		testNumRobots(1, 4, 2, 2);
		testNumRobotsRequested(0, 0, 0, 0);
	}

	void testMaterialTotals(int oreTotal, int clayTotal, int obsidianTotal, int geodeTotal) {
		assertEquals(oreTotal, strategy.oreTotal);
		assertEquals(clayTotal, strategy.clayTotal);
		assertEquals(obsidianTotal, strategy.obsidianTotal);
		assertEquals(geodeTotal, strategy.geodeTotal);
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

