import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.Before;

public class TestOperation {

	public static void main(String[] args) throws Exception {
		TestOperation obj = new TestOperation();
	}

	Logger logger = new Logger(this, true);

	@Before
		public void setUp() throws Exception {
			logger.log("Start setUp");
		}

	@Test public void TestConstructor() {
		Operation operation = new Operation(new Constant(1), new Constant(2));
		assertEquals("Operation", operation.getClass().getName());
	}

}
