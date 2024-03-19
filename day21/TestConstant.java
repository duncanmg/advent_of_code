import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.Before;

public class TestConstant {

	public static void main(String[] args) throws Exception {
		TestConstant obj = new TestConstant();
	}

	Logger logger = new Logger(this, true);

	@Before
		public void setUp() throws Exception {
			logger.log("Start setUp");
		}

	@Test public void TestConstructor() {
		Constant constant = new Constant((long)1);
		assertEquals("Constant", constant.getClass().getName());
		constant.solve();
		assertEquals(1, constant.answer, 0.01);
		assertEquals(true, constant.solved);
	}

}
