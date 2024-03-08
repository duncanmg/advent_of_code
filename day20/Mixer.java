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
			State state = new State(i, nums.get(i));
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

	void mix() {
		for (State state : states) {
			move(state);
		}
		if (states.size() >= 1000) {
			System.out.println("Position 999: " + states.get(999));
			System.out.println("Position 1999: " + states.get(1999));
			System.out.println("Position 2999: " + states.get(2999));
		}
	}

	void move(State state) {

		int pos = state.currentPos;
		int newPos = pos + state.value;
		int step = newPos >= pos ? 1 : -1;

		while (pos != newPos) {
			logger.log("move pos=" + pos + " newPos=" + newPos + " state.currentPos=" + state.currentPos);
			moveOne(state, step);
			pos += step;
		}
	}

	void logStates() {
		for (State state : states) {
			logger.log(state.toString());
		}
	}

	void moveOne(State state, int step) {

		logger.log("moveOne step=" + step);
		logStates();

		State oldRightState = state.rightState;
		State oldLeftState = state.leftState;
		// logStates();

		if (step > 0) {

			// Move right

			state.rightState = oldRightState.rightState;
			oldRightState.rightState = state;
			logStates();

			state.leftState = oldRightState;
			oldRightState.leftState = oldLeftState;
			logStates();

			oldLeftState.rightState = oldRightState;
			logStates();

			state.rightState.leftState = state;

			int tmp = state.currentPos;
			state.currentPos = oldRightState.currentPos;
			oldRightState.currentPos = tmp;
			logStates();

		}
		else {

			// Move left

			state.leftState = oldLeftState.leftState;
			oldLeftState.leftState = state;
			logStates();

			state.rightState = oldLeftState;
			oldLeftState.rightState = oldRightState;
			logStates();

			oldRightState.leftState = oldLeftState;
			logStates();

			state.leftState.rightState = state;

			int tmp = state.currentPos;
			state.currentPos = oldLeftState.currentPos;
			oldLeftState.currentPos = tmp;
			logStates();

		}
	}

}
