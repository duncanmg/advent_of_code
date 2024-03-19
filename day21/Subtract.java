class Subtract extends Operation{

	public static void main(String[] args) {
		Subtract obj = new Subtract(new Constant(1), new Constant(2));
	}

	public Subtract(Operation l, Operation r) {
		super(l, r);
	}

	void solve() {
		if (lhs.solved && rhs.solved) {
			answer = lhs.answer - rhs.answer;
			solved = true;
		}
	}

}
