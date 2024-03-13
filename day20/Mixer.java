import java.io.IOException;
import java.util.*;

class Mixer {

	public static void main(String[] args) {
		Mixer obj = new Mixer();
	}

	public Mixer() {
	}

	public Mixer(ArrayList<Integer> nums) {
		initStates(nums);
	}

	public Mixer(String dataFile) {
		dataObj = new Data(dataFile);
		ArrayList<String> dataStrings = dataObj.getData(dataFile);
		ArrayList<Integer> nums = new ArrayList<Integer>(0);

		for (String s : dataStrings) {
			nums.add(Integer.parseInt(s));
		}

		initStates(nums);
	}

	void initStates(ArrayList<Integer> nums) {
		for (int i=0; i < nums.size(); i++) {
			State state = new State(i+1, nums.get(i));
			if (i>0) {
				state.leftState = states.get(i-1);
				states.get(i-1).rightState = state;
			}
			states.add(state);
		}
		int size = states.size();
		states.get(0).leftState = states.get(size - 1);
		states.get(size - 1).rightState = states.get(0);
	}

	ArrayList<State> states = new ArrayList<State>();

	Data dataObj;

	Logger logger = new Logger(this, true);

	void mix() throws Exception{
		for (State state : states) {
			move(state);
			logChain(1, "CURRENT");
		}

		int indexOfValueZero = getIndexOfState(0);

		State oneThousand = getStateByDistance(states.get(indexOfValueZero), 1000);
		State twoThousand = getStateByDistance(states.get(indexOfValueZero), 2000);
		State threeThousand = getStateByDistance(states.get(indexOfValueZero), 3000);

		System.out.println("Position 1000: " + oneThousand.value);
		System.out.println("Position 2000: " + twoThousand.value);
		System.out.println("Position 3000: " + threeThousand.value);
		System.out.println("Total: " + (oneThousand.value 
					+ twoThousand.value + threeThousand.value));
	}

	void move(State state) {
		moveByValue(state);
	}

	void logStates() {
		for (State state : states) {
			logger.log("logStates: " + state.toString());
		}
	}

	void moveByValue(State state) {

		int distance = state.value;

		if (distance == 0) {
			return;
		}

		//		if (Math.abs(distance) == 1) {
		//			moveOne(state);
		//			return;
		//		}

		State stateToBeSwapped = getStateByDistance(state, distance);

		superSwap(state, stateToBeSwapped);

		//		State oldRightState = state.rightState;
		//		State oldLeftState = state.leftState;
		//		// logStates();
		//
		//		if (distance > 0) {
		//
		//			// Move right
		//
		//			logStates();
		//			logger.log("00 UUUUUUUUUUUUUUUUUUUUUUUUUUUUU state=" + state);
		//			logger.log("01 stateToBeSwapped=" + stateToBeSwapped);
		//
		//			state.rightState = stateToBeSwapped.rightState;
		//
		//			logger.log("01.1 oldRightState=" + oldRightState);
		//
		//			stateToBeSwapped.rightState = oldRightState;
		//
		//			logger.log("02");
		//			logStates();
		//
		//			logger.log("03");
		//			state.leftState = stateToBeSwapped.leftState;
		//			stateToBeSwapped.leftState = oldLeftState;
		//			logger.log("04");
		//			logStates();
		//
		//			oldLeftState.rightState = stateToBeSwapped;
		//			logger.log("05");
		//			logStates();
		//
		//			logger.log("06");
		//			state.rightState.leftState = state;
		//
		//			logger.log("07");
		//			logStates();
		//
		//		}
		//		else {
		//
		//			// Move left
		//
		//			state.leftState = stateToBeSwapped.leftState;
		//			stateToBeSwapped.leftState = oldLeftState;
		//			logStates();
		//
		//			state.rightState = stateToBeSwapped.rightState;
		//			stateToBeSwapped.rightState = oldRightState;
		//			logStates();
		//
		//			oldRightState.leftState = stateToBeSwapped;
		//			logStates();
		//
		//			state.leftState.rightState = state;
		//
		//			logStates();
		//
		//		}

	}

	//	// a and b must not be the same.
	//	// a and b must not be adjacent.
	//	void swapStates(State a, State b) {
	//		State tmp;
	//
	//		logger.log("01 swapStates()");
	//		logStates();
	//		tmp = a.leftState;
	//		a.leftState = b.leftState;
	//		b.leftState = tmp;
	//
	//		logger.log("02 swapStates()");
	//		logStates();
	//
	//		tmp = a.rightState;
	//		a.rightState = b.rightState;
	//		b.rightState = tmp;
	//
	//		logStates();
	//		logger.log("03 swapStates()");
	//
	//		tmp = a.leftState.rightState;
	//		a.leftState.rightState = b.leftState.rightState;
	//		b.leftState.rightState = tmp;
	//
	//		logger.log("04 swapStates()");
	//		logStates();
	//
	//		tmp = a.rightState.leftState;
	//		a.rightState.leftState = b.rightState.leftState;
	//		b.rightState.leftState = tmp;
	//
	//		logger.log("05 swapStates()");
	//		logStates();
	//
	//		logger.log("06 swapStates()");
	//	}

	void superSwap(State a, State b) {

		if (a.id == b.id) {
			return;
		}

		State leftStateA = a.leftState;
		if (leftStateA.id == b.id) {
			leftStateA = leftStateA.leftState;
		}

		State leftStateB = b.leftState;
		if (leftStateB.id == a.id) {
			leftStateB = leftStateB.leftState;
		}

		logger.log("01 superSwap a.value=" + a.value + " b.value=" + b.value);
		logStates();
		// Remove both states from linked list.
		unlinkState(a);

		logger.log("02 superSwap. After unlinking a");
		logStates();

		unlinkState(b);
		logger.log("03 superSwap. After unlinking b");
		logStates();

		logger.log("leftStateA " + leftStateA + " leftStateB " + leftStateB);
		linkState(leftStateB, a);
		logger.log("04 superSwap. After linking a. " + a.toString());
		logStates();
		linkState(leftStateA, b);
		logger.log("05 superSwap. After linking b. " + b.toString());
		logStates();
		logger.log("06 End superSwap.");

	}

	void unlinkState(State state) {
		State unlinked = new State(-1, -1);
		state.leftState.rightState = state.rightState;
		state.rightState.leftState = state.leftState;
		state.leftState = unlinked;
		state.rightState = unlinked;
	}

	void linkState(State leftState, State state) {
		State tmp;
		logger.log("linkState Put " + state + " to the right of " + leftState);

		tmp = leftState.rightState;
		leftState.rightState.leftState = state;
		leftState.rightState = state;
		state.rightState = tmp;
		state.leftState = leftState;
	}

	//	void swapAdjacentStates(State a, State b) {
	//		State tmp;
	//
	//			logger.log("01 QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
	//		// Same states
	//		if (a.id == b.id) {
	//			return;
	//		}
	//
	//			logger.log("02 QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
	//		// Exactly two states in list
	//		if (a.rightState.id == b.id && a.leftState.id == b.id) {
	//			b.rightState = a;
	//			a.leftState = b;
	//			b.leftState = a;
	//			a.rightState = b;
	//			return;
	//		}
	//
	//			logger.log("03 QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
	//		// Adjacent on one side
	//		if (a.rightState.id == b.id) {
	//			logger.log("04 QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
	//			tmp = a.leftState;
	//			a.rightState = b.rightState;
	//			b.rightState = a;
	//			a.leftState = b;
	//			b.leftState = tmp;
	//			b.leftState.rightState = b;
	//			return;
	//		}
	//
	//		tmp = a.leftState.rightState;
	//		a.leftState.rightState = b.leftState.rightState;
	//		b.leftState.rightState = tmp;
	//
	//		tmp = a.rightState.leftState;
	//		a.rightState.leftState = b.rightState.leftState;
	//		b.rightState.leftState = tmp;
	//
	//		tmp = a.leftState;
	//		a.leftState = b.leftState;
	//		b.leftState = tmp;
	//
	//		tmp = a.rightState;
	//		a.rightState = b.rightState;
	//		b.rightState = tmp;
	//
	//		logStates();
	//	}

	//	void moveOne(State state) {
	//
	//		logger.log("Start MoveOne");
	//		State oldRightState = state.rightState;
	//		State oldLeftState = state.leftState;
	//		// logStates();
	//
	//		if (state.value > 0) {
	//
	//			// Move right
	//
	//			// logStates();
	//			// logger.log("00 state=" + state);
	//			state.rightState = oldRightState.rightState;
	//			// logger.log("01.1 oldRightState=" + oldRightState);
	//			oldRightState.rightState = state;
	//			// logger.log("02");
	//			// logStates();
	//
	//			logger.log("03");
	//			state.leftState = oldRightState;
	//			oldRightState.leftState = oldLeftState;
	//			// logger.log("04");
	//			// logStates();
	//
	//			oldLeftState.rightState = oldRightState;
	//			////  logger.log("05");
	//			// logStates();
	//
	//			// logger.log("06");
	//			state.rightState.leftState = state;
	//
	//			// logger.log("07");
	//			// logStates();
	//	}
	//	else {
	//			// Move left
	//
	//			state.leftState = oldLeftState.leftState;
	//			oldLeftState.leftState = state;
	//			logStates();
	//
	//			state.rightState = oldLeftState;
	//			oldLeftState.rightState = oldRightState;
	//			logStates();
	//
	//			oldRightState.leftState = oldLeftState;
	//			logStates();
	//
	//			state.leftState.rightState = state;
	//
	//			logStates();
	//	}
	//}
	State getStateByDistance(State state, int distance) {
		State candidate = state;
		boolean goRight = distance > 0;
		// logger.log("getStateByDistance state: " + state.toString() + " distance: " + distance);
		for (int i=0; i<Math.abs(distance); i++) {
			if (goRight) {
				// logger.log("getStateByDistance i=" + i + " candidate=" + candidate.rightState.toString());
				candidate = candidate.rightState;
			}
			else {
				candidate = candidate.leftState;
			}
		}
		// logger.log("getStateByDistance returning " + candidate.toString());
		return candidate;
	}

	// Beware! Only value 0 is guaranteed to be unique.
	int getIndexOfState(int target) throws Exception {
		for (int i=0; i<states.size(); i++) {
			State state = states.get(i);
			if (state.value == target) {
				return i;
			}
		}
		throw new Exception("State with index not found");
	}

	// This follows the chain (linked list) thus giving a more readable output.
	void logChain(int startValue, String msg) {

		ArrayList<State> states = getChain(startValue, msg);

		logger.log("---- " + msg);
		for (State state : states) {
			logger.log(state.toString());
			state = state.rightState;
		}
		logger.log("----");

	}

	ArrayList<State> getChain(int startValue, String msg) {
		ArrayList<State> out = new ArrayList<State>();

		int startIndex = -1;
		for (int i=0; i<states.size(); i++) {
			State candidate = states.get(i);
			if (candidate.value == startValue) {
				startIndex = i;
			}
		}

		State state = states.get(startIndex);
		for (int i=0; i<states.size(); i++) {
			out.add(state);
			state = state.rightState;
		}
		return out;
	}

	ArrayList<Integer> getChainAsIntegers(int startValue, String msg) {

		ArrayList<Integer> out = new ArrayList<Integer>();

		ArrayList<State> states = getChain(startValue, msg);

		for (State state : states) {
			out.add(state.value);
		}

		return out;
	}

}
