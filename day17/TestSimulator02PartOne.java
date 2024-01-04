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

// This test confirms the correct answers for part one using the test data
// and the real data.

public class TestSimulator02PartOne {

	public static void main(String[] args) throws Exception {
		TestSimulator02PartOne obj = new TestSimulator02PartOne();
	}

	// 3068 is the correct answer for part one using the test data.
	@Test
	public void TestSimulation2022Rocks() throws Exception {

		System.out.println("01 YYYYYYYYYYYYYYY");
		Simulator p = new Simulator(new String[] {"--dataFile=test_data.txt"});
		System.out.println("02 YYYYYYYYYYYYYYY");
		p.endTester.maxRocks = 2022;
		p.debug = false;
		System.out.println("03 YYYYYYYYYYYYYYY");
		int out = p.runSimulation();
		System.out.println("04 YYYYYYYYYYYYYYY");
		p.chamber.logger.debug = false;
		p.chamber.show();
		assertEquals(3068, out);

	}

	// 3085 is the correct answer for part one using the real data.
	@Test
	public void TestSimulation2022RocksRealData() throws Exception {

		Simulator p = new Simulator(new String[] {"--dataFile=data.txt"});
		p.endTester.maxRocks = 2022;
		p.debug = false;
		int out = p.runSimulation();
		p.chamber.logger.debug = false;
		p.chamber.show();
		assertEquals(3085, out);

	}

}
