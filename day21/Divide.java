class Divide extends Operation{

	public static void main(String[] args) {
		Divide obj = new Divide(new Constant(1), new Constant(2));
	}

	public Divide(Operation l, Operation r) {
		super(l, r);
	}

	void solve() {
		if (lhs.solved && rhs.solved) {
			answer = lhs.answer / rhs.answer;
			solved = true;
		}
	}

}
