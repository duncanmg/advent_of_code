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

public class TestStrategyIterator {

	public static void main(String[] args) throws Exception {
		TestStrategyIterator obj = new TestStrategyIterator();
	}

	StrategyIterator iterator;

	Logger logger = new Logger(this, true);

       @Before
                public void setUp() throws Exception {
			RobotStrategy robotStrategy = new RobotStrategy();
			Blueprint blueprint = new Blueprint(100, new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6)));
			robotStrategy.blueprint = blueprint;
                        iterator = new StrategyIterator(robotStrategy);
                        assertEquals(iterator.getClass().getName(), "StrategyIterator");

                }

        @Test public void testFirstFewValues() throws Exception {

		// Always "geode" first.
                boolean[] result = iterator.next();
		assertEquals(iterator.label, "geode");
                testResult(result, false, false, false, true);
		logger.log("01 testFirstFewValues()");

		// Then the best choice, which is "obsidian" in this case.
                result = iterator.next();
		logger.log("02a testFirstFewValues() " + iterator.label);
		assertEquals("obsidian", iterator.label);
                testResult(result, false, false, true, false);
		logger.log("02 testFirstFewValues()");

                result = iterator.next();
		assertEquals(iterator.label, "clay");
                testResult(result, false, true, false, false);
		logger.log("03 testFirstFewValues()");

                result = iterator.next();
		assertEquals(iterator.label, "ore");
                testResult(result, true, false, false, false);
		logger.log("04 testFirstFewValues()");

                result = iterator.next();
		assertEquals(iterator.label, "none");
                testResult(result, false, false, false, false);
		logger.log("05 testFirstFewValues()");

        }

        @Test public void testKeyValues() throws Exception {

		assertEquals(true, iterator.hasNext());
		boolean[] result = multipleNexts(5);
		testResult(result, false, false, false, false);

        }

	public boolean[] multipleNexts(int num) {
		boolean[] result = new boolean[4];
		for (int i = 0; i < num; i++) {
			result = iterator.next();
		}
		return result;
	}

	public void testResult(boolean[] result, boolean ore, boolean clay, boolean obsidian, boolean geode) {
		assertEquals(ore, result[0]);
		assertEquals(clay, result[1]);
		assertEquals(obsidian, result[2]);
		assertEquals(geode, result[3]);
		
	}
}
