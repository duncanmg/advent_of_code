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
                String result = iterator.next();
		assertEquals(result, "geode");
		logger.log("01 testFirstFewValues()");

		// Then the best choice, which is "obsidian" in this case.
                result = iterator.next();
		assertEquals("obsidian", result);
		logger.log("02 testFirstFewValues()");

                result = iterator.next();
		assertEquals(result, "clay");
		logger.log("03 testFirstFewValues()");

                result = iterator.next();
		assertEquals(result, "ore");
		logger.log("04 testFirstFewValues()");

                result = iterator.next();
		assertEquals(result, "none");
		logger.log("05 testFirstFewValues()");

        }

        @Test public void testKeyValues() throws Exception {

		assertEquals(true, iterator.hasNext());
		String result = multipleNexts(5);
		assertEquals(result, "none");

        }

	public String multipleNexts(int num) {
		String result = new String("");
		for (int i = 0; i < num; i++) {
			result = iterator.next();
		}
		return result;
	}

}
