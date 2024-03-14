import java.io.IOException;
import java.util.*;

// mixer = new Mixer("data.txt");
// mixer.mix();
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

		int distance = calculateDistance(state);

		State newNeighbour = getStateByDistance(state, distance);

		if (distance > 0) {
			moveState(state, newNeighbour);
		}
	}

	void logStates() {
		for (State state : states) {
			logger.log("logStates: " + state.toString());
		}
	}

	int calculateDistance(State state) {

		// Don't count the one which will be moved.
		int circumference = states.size() - 1;

		int distance = Math.abs(state.value);

		// Don't go round and round!
		while (distance > circumference) {
			distance -= circumference;
		}

		if (distance == 0) {
			return 0;
		}

		if (state.value < 0) {
			// Keep it simple by always going right.
			distance = circumference - distance;
		}

		logger.log("distance=" + distance);

		return distance;

	}

	// Move "a" to be immediately right of "b".
	void moveState(State a, State b) {

		if (a.id == b.id) {
			return;
		}

		logger.log("01 moveState a.value=" + a.value + " b.value=" + b.value);
		logStates();

		unlinkState(a);

		logger.log("02 moveState. After unlinking a");
		logStates();

		linkState(b, a);
		logger.log("04 moveState. After linking a. " + a.toString());
		logStates();

		logger.log("06 End moveState.");

	}

	void unlinkState(State state) {
		State unlinked = new State(-1, -1);
		state.leftState.rightState = state.rightState;
		state.rightState.leftState = state.leftState;
		state.leftState = unlinked;
		state.rightState = unlinked;
	}

	void linkState(State destinationState, State state) {
		State tmp;

			logger.log("linkState Put " + state + " to the right of " + destinationState);
			tmp = destinationState.rightState;
			destinationState.rightState.leftState = state;
			destinationState.rightState = state;
			state.rightState = tmp;
			state.leftState = destinationState;
	}

	State getStateByDistance(State state, int distance) {
		State candidate = state;
		for (int i=0; i<Math.abs(distance); i++) {
				candidate = candidate.rightState;
		}
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
