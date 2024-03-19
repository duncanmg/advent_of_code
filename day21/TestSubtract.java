import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.Before;

public class TestSubtract {

	public static void main(String[] args) throws Exception {
		TestSubtract obj = new TestSubtract();
	}

	Logger logger = new Logger(this, true);

	@Before
		public void setUp() throws Exception {
			logger.log("Start setUp");
		}

	@Test public void TestConstructor() {
		Subtract subtract = new Subtract(new Constant(1), new Constant(2));
		assertEquals("Subtract", subtract.getClass().getName());
		subtract.solve();
		assertEquals(-1, subtract.answer, 0.01);
		assertEquals(true, subtract.solved);
	}

	@Test public void TestSolve() {
		Subtract subtract = new Subtract(new Constant(1), new Subtract(new Constant(1), new Constant(2)));

		subtract.solve();
		assertEquals(false, subtract.solved);

		subtract.rhs.solve();
		subtract.solve();
		assertEquals(true, subtract.solved);
		// 1 - (1 - 2) = 2
		assertEquals(2, subtract.answer, 0.01);
	}

}
