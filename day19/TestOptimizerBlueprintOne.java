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
public class TestOptimizerBlueprintOne {

	public static void main(String[] args) throws Exception {
		TestOptimizerBlueprintOne obj = new TestOptimizerBlueprintOne();
	}

	Optimizer optimizer;

	ArrayList<Blueprint> blueprints;

	Logger logger = new Logger(this);

	// Blueprint 1:
	// Each ore robot costs 4 ore.
	// Each clay robot costs 2 ore.
	// Each obsidian robot costs 3 ore and 14 clay.
	// Each geode robot costs 2 ore and 7 obsidian.

	@Before
		public void setUp() throws Exception {
			optimizer = new Optimizer();
			assertEquals(optimizer.getClass().getName(), "Optimizer");
			blueprints = new ArrayList<Blueprint>();
			Blueprint bp = new Blueprint(1, new ArrayList<Integer>(Arrays.asList(4, 2, 3, 14, 2, 7)));
			blueprints.add(bp);
			optimizer.blueprints = blueprints;

		}

	@Test public void testBlueprintOne00() throws Exception {
		optimizer.maxMinutes = 0;
		int maxGeodes = optimizer.optimize();
		testNumRobots(1, 0, 0, 0);
		testMaterialTotals(0, 0, 0, 0);
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne01() throws Exception {
		optimizer.maxMinutes = 1;
		int maxGeodes = optimizer.optimize();
		testMaterialTotals(1, 0, 0, 0);
		testNumRobots(1, 0, 0, 0);
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne02() throws Exception {
		optimizer.maxMinutes = 2;
		int maxGeodes = optimizer.optimize();
		testMaterialTotals(2, 0, 0, 0);
		testNumRobots(1, 0, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne03() throws Exception {
		optimizer.maxMinutes = 3;
		int maxGeodes = optimizer.optimize();
		testMaterialTotals(1, 0, 0, 0);
		testNumRobots(1, 0, 0, 0);
		testNumRobotsRequested(0, 1, 0, 0);
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne04() throws Exception {
		optimizer.maxMinutes = 4;
		int maxGeodes = optimizer.optimize();
		testMaterialTotals(2, 1, 0, 0);
		testNumRobots(1, 1, 0, 0);
		testNumRobotsRequested(0, 0, 0, 0);
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne05() throws Exception {
		optimizer.maxMinutes = 5;
		int maxGeodes = optimizer.optimize();
		testNumRobots(1, 1, 0, 0);
		testNumRobotsRequested(0, 1, 0, 0);
		assertEquals(0, maxGeodes);
	}

	//	@Test public void testBlueprintOne06() throws Exception {
	//		optimizer.maxMinutes = 6;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne07() throws Exception {
	//		optimizer.maxMinutes = 7;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne08() throws Exception {
	//		optimizer.maxMinutes = 8;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne09() throws Exception {
	//		optimizer.maxMinutes = 9;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne10() throws Exception {
	//		optimizer.maxMinutes = 10;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne11() throws Exception {
	//		optimizer.maxMinutes = 11;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne12() throws Exception {
	//		optimizer.maxMinutes = 12;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne13() throws Exception {
	//		optimizer.maxMinutes = 13;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne14() throws Exception {
	//		optimizer.maxMinutes = 14;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne15() throws Exception {
	//		optimizer.maxMinutes = 15;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}

	//	@Test public void testBlueprintOne16() throws Exception {
	//		optimizer.maxMinutes = 16;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}

	//	@Test public void testBlueprintOne17() throws Exception {
	//		optimizer.maxMinutes = 17;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}
	//
	//	@Test public void testBlueprintOne18() throws Exception {
	//		optimizer.maxMinutes = 18;
	//		int maxGeodes = optimizer.optimize();
	//		assertEquals(0, maxGeodes);
	//	}

		// This is the first geode collected!
		@Test public void testBlueprintOne19() throws Exception {
			optimizer.maxMinutes = 19;
			int maxGeodes = optimizer.optimize();
			assertEquals(1, maxGeodes);
		}
	
		@Test public void testBlueprintOne20() throws Exception {
			optimizer.maxMinutes = 20;
			int maxGeodes = optimizer.optimize();
			assertEquals(2, maxGeodes);
		}
	
		@Test public void testBlueprintOne21() throws Exception {
			optimizer.maxMinutes = 21;
			int maxGeodes = optimizer.optimize();
			assertEquals(3, maxGeodes);
		}
	
		@Test public void testBlueprintOne22() throws Exception {
			optimizer.maxMinutes = 22;
			int maxGeodes = optimizer.optimize();
			assertEquals(5, maxGeodes);
		}

		@Test public void testBlueprintOne23() throws Exception {
			optimizer.maxMinutes = 23;
			int maxGeodes = optimizer.optimize();
			assertEquals(7, maxGeodes);
		}

		@Test public void testBlueprintOne24() throws Exception {
			optimizer.maxMinutes = 24;
			int maxGeodes = optimizer.optimize();
			assertEquals(9, maxGeodes);
		}

	public void testNumRobots(int numOreRobots, int numClayRobots, int numObsidianRobots, int numGeodeRobots) {
		if (optimizer.topRobotStrategy == null) {
		} else {
			logger.log("optimizer.topRobotStrategy is not null");
			RobotStrategy topRobotStrategy = optimizer.topRobotStrategy;
			logger.log("topRobotStrategy.numOreRobots = " + topRobotStrategy.numOreRobots);
			assertEquals(numOreRobots, topRobotStrategy.numOreRobots);
			logger.log("topRobotStrategy.numClayRobots = " + topRobotStrategy.numClayRobots);
			assertEquals(numClayRobots, topRobotStrategy.numClayRobots);
			logger.log("topRobotStrategy.numObsidianRobots = " + topRobotStrategy.numObsidianRobots);
			assertEquals(numObsidianRobots, topRobotStrategy.numObsidianRobots);
			logger.log("topRobotStrategy.numGeodeRobots = " + topRobotStrategy.numGeodeRobots);
			assertEquals(numGeodeRobots, topRobotStrategy.numGeodeRobots);
		}
	}

	public void testMaterialTotals(int oreTotal, int clayTotal, int obsidianTotal, int geodeTotal) {
		if (optimizer.topRobotStrategy == null) {
		} else {
			logger.log("optimizer.topRobotStrategy is not null");
			RobotStrategy topRobotStrategy = optimizer.topRobotStrategy;
			logger.log("topRobotStrategy.oreTotal = " + topRobotStrategy.oreTotal);
			assertEquals(oreTotal, topRobotStrategy.oreTotal);
			logger.log("topRobotStrategy.clayTotal = " + topRobotStrategy.clayTotal);
			assertEquals(clayTotal, topRobotStrategy.clayTotal);
			logger.log("topRobotStrategy.obsidianTotal = " + topRobotStrategy.obsidianTotal);
			assertEquals(obsidianTotal, topRobotStrategy.obsidianTotal);
			logger.log("topRobotStrategy.geodeTotal = " + topRobotStrategy.geodeTotal);
			assertEquals(geodeTotal, topRobotStrategy.geodeTotal);
		}
	}

	void testNumRobotsRequested(int numOreRobotsRequested, int numClayRobotsRequested, int numObsidianRobotsRequested, int numGeodeRobotsRequested) {
		if (optimizer.topRobotStrategy == null) {
		} else {
			logger.log("optimizer.topRobotStrategy is not null");
			RobotStrategy topRobotStrategy = optimizer.topRobotStrategy;
			logger.log("topRobotStrategy.numOreRobotsRequested = " + topRobotStrategy.numOreRobotsRequested);
			assertEquals(numOreRobotsRequested, topRobotStrategy.numOreRobotsRequested);
			logger.log("topRobotStrategy.numClayRobotsRequested = " + topRobotStrategy.numClayRobotsRequested);
			assertEquals(numClayRobotsRequested, topRobotStrategy.numClayRobotsRequested);
			logger.log("topRobotStrategy.numObsidianRobotsRequested = " + topRobotStrategy.numObsidianRobotsRequested);
			assertEquals(numObsidianRobotsRequested, topRobotStrategy.numObsidianRobotsRequested);
			logger.log("topRobotStrategy.numGeodeRobotsRequested = " + topRobotStrategy.numGeodeRobotsRequested);
			assertEquals(numGeodeRobotsRequested, topRobotStrategy.numGeodeRobotsRequested);
		}
	}
}
