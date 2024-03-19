class Multiply extends Operation{

	public static void main(String[] args) {
		Multiply obj = new Multiply(new Constant(1), new Constant(2));
	}

	public Multiply(Operation l, Operation r) {
		super(l, r);
	}

	void solve() {
		if (lhs.solved && rhs.solved) {
			answer = lhs.answer * rhs.answer;
			solved = true;
		}
	}

}
