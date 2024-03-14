//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
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
		return setUpMixer(10, 20, 30);
	}

	Mixer setUpMixer(int x, int y, int z) {
		ArrayList<Integer> nums = new ArrayList<Integer>();

		nums.add(x);
		nums.add(y);
		nums.add(z);

		Mixer mixer = new Mixer(nums);
		assertEquals("Mixer", mixer.getClass().getName());

		return mixer;
	}

	@Test public void TestMoveOneRight() {

		Mixer mixer = setUpMixer(1, 20, 30);

		mixer.move(mixer.states.get(0));
		ArrayList<State> states = mixer.states;

		// Hasn't moved physically.
		// Has moved logically.

		logger.log(states.get(1).toString());
		assertEquals("leftV: 30 v: 20 rightV: 1", states.get(1).toString());
		assertEquals("leftV: 20 v: 1 rightV: 30", states.get(0).toString());
		assertEquals("leftV: 1 v: 30 rightV: 20", states.get(2).toString());

	}

	@Test public void TestMoveOneLeft() {

		Mixer mixer = setUpMixer(-1, 20, 30);

		mixer.move(mixer.states.get(0));
		ArrayList<State> states = mixer.states;

		// Hasn't moved physically.
		// Has moved logically.

		logger.log(states.get(1).toString());
		assertEquals("leftV: -1 v: 30 rightV: 20", states.get(2).toString());
		assertEquals("leftV: 30 v: 20 rightV: -1", states.get(1).toString());
		assertEquals("leftV: 20 v: -1 rightV: 30", states.get(0).toString());

	}

	// Move one element 2 steps to the right. No wrapping.
	@Test public void TestSuperSwap00() {
		Mixer mixer = new Mixer(new ArrayList<Integer>(Arrays.asList(10, 2, 30, 40, 50)));
		ArrayList<State> states = mixer.states;

		logger.log("Start TestSuperSwap00()");
		logChain(mixer, 10, "Before moveState");

		mixer.moveState(states.get(1), states.get(3));

		logChain(mixer, 10, "After moveState");

		assertEquals("leftV: 50 v: 10 rightV: 30", states.get(0).toString());
		assertEquals("leftV: 40 v: 2 rightV: 50", states.get(1).toString());
		assertEquals("leftV: 10 v: 30 rightV: 40", states.get(2).toString());
		assertEquals("leftV: 30 v: 40 rightV: 2", states.get(3).toString());
		assertEquals("leftV: 2 v: 50 rightV: 10", states.get(4).toString());

		logger.log("End TestSuperSwap00()");
	}

	// Move one element 2 steps to the left. No wrapping.
	@Test public void TestSuperSwap01() {
		Mixer mixer = new Mixer(new ArrayList<Integer>(Arrays.asList(10, 20, 30, -2, 50)));
		ArrayList<State> states = mixer.states;

		logger.log("Start TestSuperSwap01()");
		logChain(mixer, 10, "Before moveState");

		mixer.move(states.get(3));

		logChain(mixer, 10, "After moveState");

		assertEquals("leftV: 50 v: 10 rightV: -2", states.get(0).toString());
		assertEquals("leftV: -2 v: 20 rightV: 30", states.get(1).toString());
		assertEquals("leftV: 20 v: 30 rightV: 50", states.get(2).toString());
		assertEquals("leftV: 10 v: -2 rightV: 20", states.get(3).toString());
		assertEquals("leftV: 30 v: 50 rightV: 10", states.get(4).toString());

		logger.log("End TestSuperSwap01()");
	}

	// Move one element 2 steps to the right. With wrapping.
	@Test public void TestSuperSwap02() {
		Mixer mixer = new Mixer(new ArrayList<Integer>(Arrays.asList(10, 20, 30, 2, 50)));
		ArrayList<State> states = mixer.states;

		logger.log("Start TestSuperSwap02()");
		logChain(mixer, 10, "Before moveState");

		mixer.moveState(states.get(3), states.get(0));

		logChain(mixer, 10, "After moveState");

		assertEquals("leftV: 50 v: 10 rightV: 2", states.get(0).toString());
		assertEquals("leftV: 2 v: 20 rightV: 30", states.get(1).toString());
		assertEquals("leftV: 20 v: 30 rightV: 50", states.get(2).toString());
		assertEquals("leftV: 10 v: 2 rightV: 20", states.get(3).toString());
		assertEquals("leftV: 30 v: 50 rightV: 10", states.get(4).toString());

		logger.log("End TestSuperSwap02()");
	}

	// Move one element 2 steps to the left. With wrapping.
	@Test public void TestSuperSwap03() {
		Mixer mixer = new Mixer(new ArrayList<Integer>(Arrays.asList(10, -2, 30, 40, 50)));
		ArrayList<State> states = mixer.states;

		logger.log("Start TestSuperSwap03()");
		logChain(mixer, 10, "Before moveState");

		mixer.move(states.get(1));

		logChain(mixer, 10, "After moveState");

		assertEquals("leftV: 50 v: 10 rightV: 30", states.get(0).toString());
		assertEquals("leftV: 40 v: -2 rightV: 50", states.get(1).toString());
		assertEquals("leftV: 10 v: 30 rightV: 40", states.get(2).toString());
		assertEquals("leftV: 30 v: 40 rightV: -2", states.get(3).toString());
		assertEquals("leftV: -2 v: 50 rightV: 10", states.get(4).toString());

		logger.log("End TestSuperSwap03()");
	}

	// Same state.
	@Test public void TestSuperSwap04() {
		Mixer mixer = new Mixer(new ArrayList<Integer>(Arrays.asList(10, 2, 30)));
		ArrayList<State> states = mixer.states;

		logger.log("Start TestSuperSwap04()");
		logChain(mixer, 10, "Before moveState");

		mixer.moveState(states.get(0), states.get(0));

		logChain(mixer, 10, "After moveState");

		assertEquals("leftV: 30 v: 10 rightV: 2", states.get(0).toString());
		assertEquals("leftV: 10 v: 2 rightV: 30", states.get(1).toString());
		assertEquals("leftV: 2 v: 30 rightV: 10", states.get(2).toString());

		logger.log("End TestSuperSwap04()");
	}

	// Move one element 2 steps to the right. Short list. With wrapping and overlap.
	@Test public void TestSuperSwap05() {
		Mixer mixer = new Mixer(new ArrayList<Integer>(Arrays.asList(10, 2, 30)));
		ArrayList<State> states = mixer.states;

		logger.log("Start TestSuperSwap05()");
		logChain(mixer, 10, "Before moveState");

		mixer.moveState(states.get(1), states.get(0));

		logChain(mixer, 10, "After moveState");

		assertEquals("leftV: 30 v: 10 rightV: 2", states.get(0).toString());
		assertEquals("leftV: 10 v: 2 rightV: 30", states.get(1).toString());
		assertEquals("leftV: 2 v: 30 rightV: 10", states.get(2).toString());

		logger.log("End TestSuperSwap05()");
	}

	// Move one element 2 steps to the left. Short list. With wrapping and overlap.
	@Test public void TestSuperSwap06() {
		Mixer mixer = new Mixer(new ArrayList<Integer>(Arrays.asList(10, -2, 30)));
		ArrayList<State> states = mixer.states;

		logger.log("Start TestSuperSwap06()");
		logChain(mixer, 10, "Before moveState");

		mixer.move(states.get(1));

		logChain(mixer, 10, "After moveState");

		assertEquals("leftV: 30 v: 10 rightV: -2", states.get(0).toString());
		assertEquals("leftV: 10 v: -2 rightV: 30", states.get(1).toString());
		assertEquals("leftV: -2 v: 30 rightV: 10", states.get(2).toString());

		logger.log("End TestSuperSwap06()");
	}

	// This follows the chain (linked list) thus giving a more readable output.
	void logChain(Mixer mixer, int startValue, String msg) {
		int startIndex = -1;
		for (int i=0; i<mixer.states.size(); i++) {
			State candidate = mixer.states.get(i);
			if (candidate.value == startValue) {
				startIndex = i;
			}
		}
		State state = mixer.states.get(startIndex);
		logger.log("---- " + msg);
		for (int i=0; i<mixer.states.size(); i++) {
			logger.log(state.toString());
			state = state.rightState;
		}
		logger.log("----");
	}

}
