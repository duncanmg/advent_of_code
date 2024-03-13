class State {

	public static void main(String[] args) {
		State obj = new State(0, 1);
	}

	public State(int id, int v) {
		this.id = id;
		value = v;
	}

	final int value;

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
