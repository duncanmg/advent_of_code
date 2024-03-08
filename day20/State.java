class State {

	public static void main(String[] args) {
		State obj = new State(0, 0);
	}

	public State(int cp, int v) {
		currentPos = cp;
		value = v;
	}

	final int value;

	int currentPos;

	State leftState;

	State rightState;

	@Override
	public String toString() {
		return "leftCp: " + leftState.currentPos + " leftV: " + leftState.value 
			+ " cP: " + currentPos + " v: " + value 
			+ " rightCp: " + rightState.currentPos + " rightV: " + rightState.value;
	}
}
