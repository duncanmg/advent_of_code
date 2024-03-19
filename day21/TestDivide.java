import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.Before;

public class TestDivide {

	public static void main(String[] args) throws Exception {
		TestDivide obj = new TestDivide();
	}

	Logger logger = new Logger(this, true);

	@Before
		public void setUp() throws Exception {
			logger.log("Start setUp");
		}

	@Test public void TestConstructor() {
		Divide divide = new Divide(new Constant(1), new Constant(2));
		assertEquals("Divide", divide.getClass().getName());
		divide.solve();
		assertEquals(0.5, divide.answer, 0.01);
		assertEquals(true, divide.solved);
	}

	@Test public void TestSolve() {
		Divide divide = new Divide(new Constant(1), new Add(new Constant(3), new Constant(2)));

		divide.solve();
		assertEquals(false, divide.solved);

		divide.rhs.solve();
		divide.solve();
		assertEquals(true, divide.solved);
		// 1 / 5 = 0.20
		assertEquals(0.20, divide.answer, 0.01);
	}

}
