import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.Before;

public class TestAdd {

	public static void main(String[] args) throws Exception {
		TestAdd obj = new TestAdd();
	}

	Logger logger = new Logger(this, true);

	@Before
		public void setUp() throws Exception {
			logger.log("Start setUp");
		}

	@Test public void TestConstructor() {
		Add add = new Add(new Constant(1), new Constant(2));
		assertEquals("Add", add.getClass().getName());
//		add.solve();
//		assertEquals(3, add.answer, 0.01);
//		assertEquals(true, add.solved);
	}

//	@Test public void TestSolve() {
//		Add add = new Add(new Constant(1), new Add(new Constant(1), new Constant(2)));
//
//		add.solve();
//		assertEquals(false, add.solved);
//
//		add.rhs.solve();
//		assertEquals(true, add.solved);
//		assertEquals(4, add.answer, 0.01);
//	}

}
