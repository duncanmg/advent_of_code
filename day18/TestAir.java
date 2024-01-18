//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;

public class TestAir {

	public static void main(String[] args) throws Exception {
		TestAir obj = new TestAir();
	}

	@Test public void TestConstructor() throws Exception {

		Air air = new Air();

		assertEquals(air.getClass().getName(), "Air");

		assertEquals(air.id, "I000001000001000001");
	}

}
