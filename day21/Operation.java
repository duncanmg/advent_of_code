class Operation {

	public static void main(String[] args) {
		Operation obj = new Operation();
	}

	public Operation() {
	}

	public Operation(Operation l, Operation r) {
		lhs = l;
		rhs = r;
	}

	Logger logger = new Logger(this, true);

	Operation lhs;

	Operation rhs;

	boolean solved = false;

	double answer;

	void solve() {
	}

//	@Override
//	public String toString() {
//		return "leftV: " + leftOperation.value 
//			+ " v: " + value 
//			+ " rightV: " + rightOperation.value;
//	}
}
