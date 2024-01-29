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

       @Before
                public void setUp() throws Exception {
                        iterator = new StrategyIterator();
                        assertEquals(iterator.getClass().getName(), "StrategyIterator");

                }

        @Test public void testFirstFewValues() throws Exception {

                boolean[] result = iterator.next();
                testResult(result, false, false, false, false);

                result = iterator.next();
                testResult(result, true, false, false, false);

                result = iterator.next();
                testResult(result, false, true, false, false);

                result = iterator.next();
                testResult(result, false, false, true, false);

                result = iterator.next();
                testResult(result, false, false, false, true);

        }

        @Test public void testKeyValues() throws Exception {

		assertEquals(true, iterator.hasNext());
		boolean[] result = multipleNexts(5);
		testResult(result, false, false, false, true);

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
