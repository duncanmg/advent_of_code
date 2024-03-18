//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertArrayEquals;
// import static org.junit.Assert.assertTrue;
// import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;

import org.junit.Before;

public class TestExampleData {

	public static void main(String[] args) throws Exception {
		TestExampleData obj = new TestExampleData();
	}

	Logger logger = new Logger(this, true);

	ArrayList<ArrayList<Long>> data = new ArrayList<ArrayList<Long>>();

	// Stes in their original order. Used to determine which state to mix next.
	ArrayList<State> baseStates = new ArrayList<State>();

	void setUpData() {
		long[] nums = {1, 2, -3, 3, -2, 0, 4};
		data.add(arrayListLong(nums));

		nums = new long[] {2, 1, -3, 3, -2, 0, 4};
		data.add(arrayListLong(nums));

		nums = new long[] {1, -3, 2, 3, -2, 0, 4};
		data.add(arrayListLong(nums));

		nums = new long[] {1, 2, 3, -2, -3, 0, 4};
		data.add(arrayListLong(nums));

		nums = new long[] {1, 2, -2, -3, 0, 3, 4};
		data.add(arrayListLong(nums));

		nums = new long[] {1, 2, -3, 0, 3, 4, -2};
		data.add(arrayListLong(nums));

		nums = new long[] {1, 2, -3, 0, 3, 4, -2};
		data.add(arrayListLong(nums));

		nums = new long[] {1, 2, -3, 4, 0, 3, -2};
		data.add(arrayListLong(nums));
		
	}

	@Before
		public void setUp() throws Exception {
			// logger.log("Start setUp");
			Mixer baseMixer = setUpMixer(0);
			baseStates = baseMixer.states;
		}

	@Test public void TestConstructor() {
		Mixer mixer = new Mixer();
		assertEquals("Mixer", mixer.getClass().getName());
		assertEquals(0, mixer.states.size());
	}

	@Test public void TestStep00() throws Exception {
		testSingleStep(0, 2);
	}

	@Test public void TestStep01() throws Exception {
		testSingleStep(1, 1);
	}

	@Test public void TestStep02() throws Exception {
		testSingleStep(2, 1);
	}

	@Test public void TestStep03() throws Exception {
		testSingleStep(3, 1);
	}

	@Test public void TestStep04() throws Exception {
		testSingleStep(4, 1);
	}

	@Test public void TestStep05() throws Exception {
		testSingleStep(5, 1);
	}

	@Test public void TestStep06() throws Exception {
		testSingleStep(6, 1);
	}

	void testSingleStep(int dataIndex, long startValue) throws Exception {

		Mixer mixer = setUpMixer(dataIndex);

		ArrayList<Long> input = data.get(dataIndex);

		// baseStates gives us the State to mix next. value is correct
		// but links may be out of date.
		State s = baseStates.get(dataIndex);
		// Get up to date version of same state.
		int i = mixer.getIndexOfState(s.value);
		State stateToMix = mixer.states.get(i);

		logger.log("About to move/mix: " + stateToMix);
		mixer.move(stateToMix);
		assertEquals("Mixer", mixer.getClass().getName());

		logger.log("Input:\t\t" + input.toString());

		ArrayList<Long> expected = data.get(dataIndex+1);
		logger.log("Expected:\t" +expected.toString());

		ArrayList<Long> got = mixer.getChainAsIntegers(startValue, "");
		logger.log("Got:\t\t" +got.toString());

		assertEquals(expected, got);
	}

	Mixer setUpMixer() {
		return setUpMixer(0);
	}

	Mixer setUpMixer(int index) {

		Mixer mixer = new Mixer(data.get(index));
		assertEquals("Mixer", mixer.getClass().getName());

		return mixer;
	}

        ArrayList<Long> arrayListLong(long[] nums) {
                ArrayList<Long> out = new ArrayList<Long>();
                for (int i=0; i<nums.length; i++) {
                        out.add(nums[i]);
                }
                return out;
        }

}
