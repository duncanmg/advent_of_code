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

// This tests the optimizer using one very simple blueprint.
public class TestOptimizer extends TestOptimizerBase {

	public static void main(String[] args) throws Exception {
		TestOptimizer obj = new TestOptimizer();
	}

	Optimizer optimizer;

	ArrayList<Blueprint> blueprints;

	@Before
		public void setUp() throws Exception {
			optimizer = new Optimizer();
			assertEquals(optimizer.getClass().getName(), "Optimizer");
			blueprints = new ArrayList<Blueprint>();

		}

	Logger logger = new Logger(this, true);

	//	@Test public void testBlueprint1() throws Exception {
	//
	//		int maxGeodes = runSimpleBlueprint(0);
	//		assertEquals(0, maxGeodes);
	//	}
	//
	@Test public void testSimple1min() throws Exception {

		int maxGeodes = runSimpleBlueprint(1);
		testNumRobots(1, 0, 0, 0);
		assertEquals(0, maxGeodes);
	}

	@Test public void testSimple2min() throws Exception {

		int maxGeodes = runSimpleBlueprint(2);
		testNumRobots(1, 0, 0, 0);
		assertEquals(0, maxGeodes);
	}

	@Test public void testSimple3min() throws Exception {

		int maxGeodes = runSimpleBlueprint(3);
		testNumRobots(1, 1, 0, 0);
		assertEquals(0, maxGeodes);
	}

	@Test public void testSimple4min() throws Exception {

		int maxGeodes = runSimpleBlueprint(4);
		testNumRobots(1, 2, 0, 0);
		assertEquals(0, maxGeodes);
	}

	@Test public void testSimple6min() throws Exception {

		int maxGeodes = runSimpleBlueprint(6);
		testNumRobots(1, 2, 2, 0);
		assertEquals(0, maxGeodes);
	}

	@Test public void testSimple7min() throws Exception {

		int maxGeodes = runSimpleBlueprint(7);
		assertEquals(1, maxGeodes);
	}

	@Test public void testSimple8min() throws Exception {

		// This is using a simple blueprint where it only costs 1 obidian and 1 ore to make a geode.
		// So, once we have an obsidian robot and an ore robot we can add another geode robot every minute!
		// Here we have 2 geode robots collecting and one requested.
		int maxGeodes = runSimpleBlueprint(8);
		assertEquals(3, maxGeodes);
	}

	@Test public void testSimple9min() throws Exception {

		// Here we have 3 geode robots collecting and one requested.
		int maxGeodes = runSimpleBlueprint(9);
		assertEquals(6, maxGeodes);
	}

	@Test public void testSimple() throws Exception {

		Blueprint bp = new Blueprint(1, new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1)));
		blueprints.add(bp);
		optimizer.blueprints = blueprints;
		int maxGeodes = optimizer.optimize();
		assertEquals(171, maxGeodes);
	}

	// Simple Blueprint:
	// Each ore robot costs 1 ore.
	// Each clay robot costs 1 ore.
	// Each obsidian robot costs 1 ore and 1 clay.
	// Each geode robot costs 1 ore and 1 obsidian.

	// The simple strategy produces one geode after7 minutes.
	public int runSimpleBlueprint(int minutes) throws Exception {
		Blueprint bp = new Blueprint(1, new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1)));
		blueprints.add(bp);
		optimizer.blueprints = blueprints;
		optimizer.maxMinutes = minutes;
		return optimizer.optimize();
	}

	public void testNumRobots(int numOreRobots, int numClayRobots, int numObsidianRobots, int numGeodeRobots) {
		HashMap<String, Robot> robots = optimizer.bestRobotStrategies.get(0).robots;

		logger.log("robots.get(\"ore\").numRobots = " + robots.get("ore").numRobots);
		assertEquals(numOreRobots, robots.get("ore").numRobots);

		logger.log("robots.get(\"clay\").numRobots = " + robots.get("clay").numRobots);
		assertEquals(numClayRobots, robots.get("clay").numRobots);

		logger.log("robots.get(\"obsidian\").numRobots = " + robots.get("obsidian").numRobots);
		assertEquals(numObsidianRobots, robots.get("obsidian").numRobots);

		logger.log("robots.get(\"geode\").numRobots = " + robots.get("geode").numRobots);
		assertEquals(numGeodeRobots, robots.get("geode").numRobots);
	}
}
