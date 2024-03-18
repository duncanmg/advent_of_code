class State {

	public static void main(String[] args) {
		State obj = new State(0, 1);
	}

	public State(int id, long v) {
		this.id = id;
		value = v;
	}

	final long value;

	final int id;

	State leftState;

	State rightState;

	@Override
	public String toString() {
		return "leftV: " + leftState.value 
			+ " v: " + value 
			+ " rightV: " + rightState.value;
	}
}
