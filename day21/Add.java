class Add extends Operation{

	public static void main(String[] args) {
		Add obj = new Add(new Constant(1), new Constant(2));
	}

	public Add(Operation l, Operation r) {
		super(l, r);
	}

	void solve() {
		if (lhs.solved && rhs.solved) {
			answer = lhs.answer + rhs.answer;
			solved = true;
		}
	}

}
