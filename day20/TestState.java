import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.Before;

public class TestState {

	public static void main(String[] args) throws Exception {
		TestState obj = new TestState();
	}

	Logger logger = new Logger(this, true);

	@Before
		public void setUp() throws Exception {
			logger.log("Start setUp");
		}

	@Test public void TestConstructor01() {
		State state = new State(1,2);
		assertEquals("State", state.getClass().getName());
	}

}
