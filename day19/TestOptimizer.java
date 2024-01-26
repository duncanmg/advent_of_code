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

public class TestOptimizer {

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

	@Test public void testBlueprint1() throws Exception {

		int maxGeodes = runSimpleBlueprint(0);
		assertEquals(0, maxGeodes);
	}

	@Test public void testSimple1min() throws Exception {

		int maxGeodes = runSimpleBlueprint(1);
		assertEquals(0, maxGeodes);
	}

	@Test public void testSimple6min() throws Exception {

		int maxGeodes = runSimpleBlueprint(6);
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

	// Blueprint 1:
	// Each ore robot costs 4 ore.
	// Each clay robot costs 2 ore.
	// Each obsidian robot costs 3 ore and 14 clay.
	// Each geode robot costs 2 ore and 7 obsidian.

	@Test public void testSimple() throws Exception {

		Blueprint bp = new Blueprint(1, new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1)));
		blueprints.add(bp);
		optimizer.blueprints = blueprints;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	// Simple Blueprint:
	// Each ore robot costs 1 ore.
	// Each clay robot costs 1 ore.
	// Each obsidian robot costs 1 ore and 1 clay.
	// Each geode robot costs 1 ore and 1 obsidian.

	// The simple strategy produces one geode after7 minutes.
	public int runSimpleBlueprint(int minutes) {
		Blueprint bp = new Blueprint(1, new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1)));
		blueprints.add(bp);
		optimizer.blueprints = blueprints;
		optimizer.maxMinutes = minutes;
		return optimizer.optimize();
	}

}
