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
                testResult(result, true, true, false, false);

                result = iterator.next();
                testResult(result, false, false, true, false);

        }

        @Test public void testKeyValues() throws Exception {

		// This is correct multipleNexts(1) return all false
		// so it is correct for multipleNexts(16) to return all true.
		boolean[] result = multipleNexts(16);
		testResult(result, true, true, true, true);

		result = multipleNexts(1);
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
