import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.Before;

public class TestMultiply {

	public static void main(String[] args) throws Exception {
		TestMultiply obj = new TestMultiply();
	}

	Logger logger = new Logger(this, true);

	@Before
		public void setUp() throws Exception {
			logger.log("Start setUp");
		}

	@Test public void TestConstructor() {
		Multiply multiply = new Multiply(new Constant(1), new Constant(4));
		assertEquals("Multiply", multiply.getClass().getName());
		multiply.solve();
		assertEquals(4, multiply.answer, 0.01);
		assertEquals(true, multiply.solved);
	}

	@Test public void TestSolve() {
		Multiply multiply = new Multiply(new Constant(2), new Add(new Constant(1), new Constant(3)));

		multiply.solve();
		assertEquals(false, multiply.solved);

		multiply.rhs.solve();
		multiply.solve();
		assertEquals(true, multiply.solved);
		assertEquals(8, multiply.answer, 0.01);
	}

}
