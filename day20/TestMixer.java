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

public class TestMixer {

	public static void main(String[] args) throws Exception {
		TestMixer obj = new TestMixer();
	}

	Logger logger = new Logger(this, true);

	@Before
		public void setUp() throws Exception {
			// logger.log("Start setUp");
		}

	@Test public void TestConstructor01() {
		Mixer mixer = new Mixer();
		assertEquals("Mixer", mixer.getClass().getName());
		assertEquals(0, mixer.states.size());
	}

	@Test public void TestConstructor02() {
		Mixer mixer = new Mixer("test_data.txt");
		assertEquals("Mixer", mixer.getClass().getName());
		assertEquals(7, mixer.states.size());
	}

	@Test public void TestConstructor03() {
		ArrayList<Integer> nums = new ArrayList<Integer>();

		nums.add(1);
		nums.add(2);

		Mixer mixer = new Mixer(nums);
		assertEquals("Mixer", mixer.getClass().getName());
		assertEquals(2, mixer.states.size());
	}

	Mixer setUpMixer() {
		ArrayList<Integer> nums = new ArrayList<Integer>();

		nums.add(10);
		nums.add(20);
		nums.add(30);

		Mixer mixer = new Mixer(nums);
		assertEquals("Mixer", mixer.getClass().getName());

		return mixer;
	}

	@Test public void TestMoveOne01() {

		Mixer mixer = setUpMixer();

		assertEquals("leftCp: 2 leftV: 30 cP: 0 v: 10 rightCp: 1 rightV: 20", mixer.states.get(0).toString());
		assertEquals("leftCp: 0 leftV: 10 cP: 1 v: 20 rightCp: 2 rightV: 30", mixer.states.get(1).toString());
		assertEquals("leftCp: 1 leftV: 20 cP: 2 v: 30 rightCp: 0 rightV: 10", mixer.states.get(2).toString());

	}

	@Test public void TestMoveOne02() {

		Mixer mixer = setUpMixer();

		mixer.moveOne(mixer.states.get(0), 1);
		ArrayList<State> states = mixer.states;

		// Hasn't moved physically.
		assertEquals(10, states.get(0).value);
		assertEquals(20, states.get(1).value);
		assertEquals(30, states.get(2).value);

		// Has moved logically.
		assertEquals(1, states.get(0).currentPos);
		assertEquals(0, states.get(1).currentPos);
		assertEquals(2, states.get(2).currentPos);

	}

	@Test public void TestMoveOne03() {

		Mixer mixer = setUpMixer();
		logger.debug = false;

		mixer.moveOne(mixer.states.get(0), 1);
		State zero = mixer.states.get(0);

		logger.log("01 TestMoveOne03");
		assertEquals("leftCp: 0 leftV: 20 cP: 1 v: 10 rightCp: 2 rightV: 30", zero.toString());

		logger.log("02 TestMoveOne03: " + zero.leftState.toString());
		assertEquals("leftCp: 2 leftV: 30 cP: 0 v: 20 rightCp: 1 rightV: 10", zero.leftState.toString());

		logger.log("03 TestMoveOne03 " + zero.leftState.leftState.toString());
		assertEquals("leftCp: 1 leftV: 10 cP: 2 v: 30 rightCp: 0 rightV: 20", zero.leftState.leftState.toString());

		logger.log("04 TestMoveOne03");
		assertEquals("leftCp: 0 leftV: 20 cP: 1 v: 10 rightCp: 2 rightV: 30", zero.leftState.leftState.leftState.toString());

	}

	@Test public void TestMoveOne04() {

		Mixer mixer = setUpMixer();
		logger.debug = false;

		mixer.moveOne(mixer.states.get(0), 1);
		State zero = mixer.states.get(0);

		logger.log("01 TestMoveOne04");
		assertEquals("leftCp: 0 leftV: 20 cP: 1 v: 10 rightCp: 2 rightV: 30", zero.toString());

		logger.log("02 TestMoveOne04: " + zero.rightState.toString());
		assertEquals("leftCp: 1 leftV: 10 cP: 2 v: 30 rightCp: 0 rightV: 20", zero.rightState.toString());

		logger.log("03 TestMoveOne04 " + zero.rightState.rightState.toString());
		assertEquals("leftCp: 2 leftV: 30 cP: 0 v: 20 rightCp: 1 rightV: 10", zero.rightState.rightState.toString());

		logger.log("04 TestMoveOne04");
		assertEquals("leftCp: 0 leftV: 20 cP: 1 v: 10 rightCp: 2 rightV: 30", zero.rightState.rightState.rightState.toString());
	}

	@Test public void TestMove() {
		ArrayList<Integer> nums = new ArrayList<Integer>();

		nums.add(2);
		nums.add(20);
		nums.add(30);

		Mixer mixer = new Mixer(nums);
		assertEquals("Mixer", mixer.getClass().getName());

		mixer.move(mixer.states.get(0));
		State zero = mixer.states.get(0);
		// Hasn't moved physically.
		assertEquals(2, zero.value);
		// Has moved logically.
		assertEquals(2, zero.currentPos);

		assertEquals(1, zero.leftState.currentPos);
		assertEquals(30, zero.leftState.value);

		assertEquals(0, zero.leftState.leftState.currentPos);
		assertEquals(20, zero.leftState.leftState.value);
	}

	@Test public void TestLeftMove() {
		ArrayList<Integer> nums = new ArrayList<Integer>();

		nums.add(-1);
		nums.add(20);
		nums.add(30);

		Mixer mixer = new Mixer(nums);
		assertEquals("Mixer", mixer.getClass().getName());

		mixer.move(mixer.states.get(0));
		State zero = mixer.states.get(0);
		// Hasn't moved physically.
		assertEquals(-1, zero.value);
		// Has moved logically.
		assertEquals(2, zero.currentPos);

		assertEquals(1, zero.leftState.currentPos);
		assertEquals(20, zero.leftState.value);

		assertEquals(0, zero.leftState.leftState.currentPos);
		assertEquals(30, zero.leftState.leftState.value);
	}
}
