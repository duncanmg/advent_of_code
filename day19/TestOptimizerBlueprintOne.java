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

public class TestOptimizerBlueprintOne {

	public static void main(String[] args) throws Exception {
		TestOptimizerBlueprintOne obj = new TestOptimizerBlueprintOne();
	}

	Optimizer optimizer;

	ArrayList<Blueprint> blueprints;

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
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne01() throws Exception {
		optimizer.maxMinutes = 1;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne02() throws Exception {
		optimizer.maxMinutes = 2;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne03() throws Exception {
		optimizer.maxMinutes = 3;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne04() throws Exception {
		optimizer.maxMinutes = 4;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne05() throws Exception {
		optimizer.maxMinutes = 5;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne06() throws Exception {
		optimizer.maxMinutes = 6;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne07() throws Exception {
		optimizer.maxMinutes = 7;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne08() throws Exception {
		optimizer.maxMinutes = 8;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne09() throws Exception {
		optimizer.maxMinutes = 9;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne10() throws Exception {
		optimizer.maxMinutes = 10;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne11() throws Exception {
		optimizer.maxMinutes = 11;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne12() throws Exception {
		optimizer.maxMinutes = 12;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne13() throws Exception {
		optimizer.maxMinutes = 13;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne14() throws Exception {
		optimizer.maxMinutes = 14;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne15() throws Exception {
		optimizer.maxMinutes = 15;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne16() throws Exception {
		optimizer.maxMinutes = 16;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne17() throws Exception {
		optimizer.maxMinutes = 17;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne18() throws Exception {
		optimizer.maxMinutes = 18;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	// This i the first geode collected!
	@Test public void testBlueprintOne19() throws Exception {
		optimizer.maxMinutes = 19;
		int maxGeodes = optimizer.optimize();
		assertEquals(1, maxGeodes);
	}

	@Test public void testBlueprintOne20() throws Exception {
		optimizer.maxMinutes = 20;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne21() throws Exception {
		optimizer.maxMinutes = 21;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne22() throws Exception {
		optimizer.maxMinutes = 22;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne23() throws Exception {
		optimizer.maxMinutes = 23;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

	@Test public void testBlueprintOne24() throws Exception {
		optimizer.maxMinutes = 24;
		int maxGeodes = optimizer.optimize();
		assertEquals(0, maxGeodes);
	}

}
