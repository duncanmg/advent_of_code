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

public class TestSimulator01Basic {

	public static void main(String[] args) throws Exception {
		TestSimulator01Basic obj = new TestSimulator01Basic();
	}

	@Test public void TestNextRock() throws Exception {

		System.out.println("01 XXXX p");
		ArrayList<String> expected = new ArrayList<String>(0);
		ArrayList<String> got = new ArrayList<String>(0);

		expected.add("Horizontal");
		expected.add("Cross");
		expected.add("Step");
		expected.add("Vertical");
		expected.add("Square");
		expected.add("Horizontal");

		System.out.println("02 XXXX simulator");
		Simulator simulator = new Simulator(new String[] {});

		System.out.println("Created simulator");
		for (int i=0; i<6; i++) {
			System.out.println(i);
			got.add(simulator.rockGenerator.next().getClass().getName());
		}

		assertEquals(expected, got);
		System.out.println("Finished TestNextRock");
	}

	@Test public void TestNextJet() throws Exception {

		ArrayList<String> expected = new ArrayList<String>(0);
		ArrayList<String> got = new ArrayList<String>(0);

		Simulator simulator = new Simulator(new String[] {"--dataFile=test_data.txt"});
		String data_str = simulator.data.get(0);
		int s = data_str.length();

		// Step through the jets to the end
		for (int i=0; i<s; i++) {
			String str = simulator.jetGenerator.next();
		}

		// It should have cycled back to the beginning
		// so these should be the same.
		for (int i=0; i<5; i++) {
			got.add(simulator.jetGenerator.next());
			expected.add(data_str.substring(i, i+1));
		}

		assertEquals(expected, got);
	}

	@Test
		public void TestConstructor() throws Exception {

			Simulator p = new Simulator(new String[] {});
			assertEquals(p.getClass(), Simulator.class);

			p = new Simulator(new String[] {});
			assertEquals(p.getClass(), Simulator.class);
		}

	@Test
		public void TestSimulation() throws Exception {

			Simulator p = new Simulator(new String[] {"--dataFile=test_data.txt"});
			p.maxRocks = 1;
			p.debug = false;
			int out = p.runSimulation();
			System.out.println("TestSimulation out = " + out);
//			p.chamber.logger.debug = true;
//			p.chamber.show();
			assertEquals(1, out);

		}

	@Test
		public void TestSimulationOneRock() throws Exception {

			System.out.println("Start TestSimulationOneRock()");
			Simulator p = new Simulator(new String[] {"--dataFile=test_data.txt"});
			p.maxRocks = 1;
			p.debug = true;
			int out = p.runSimulation();
			p.chamber.logger.debug = true;
			p.chamber.show();
			assertEquals(1, out);

		}

	@Test
		public void TestSimulationTwoRocks() throws Exception {

			System.out.println("Start TestSimulationTwoRocks()");
			Simulator p = new Simulator(new String[] {"--dataFile=test_data.txt"});
			p.maxRocks = 2;
			p.debug = false;
			int out = p.runSimulation();
			p.chamber.logger.debug = false;
			p.chamber.show();
			assertEquals(4, out);

		}

	@Test
		public void TestSimulationThreeRocks() throws Exception {

			Simulator p = new Simulator(new String[] {"--dataFile=test_data.txt"});
			p.maxRocks = 3;
			p.debug = false;
			int out = p.runSimulation();
			// p.chamber.logger.debug = true;
			p.chamber.show();
			assertEquals(6, out);

		}

	@Test
		public void TestSimulationFourRocks() throws Exception {

			Simulator p = new Simulator(new String[] {"--dataFile=test_data.txt"});
			p.maxRocks = 4;
			p.debug = false;
			int out = p.runSimulation();
			p.chamber.logger.debug = true;
			System.out.println("4 Rocks ==================================");
			p.chamber.show();
			System.out.println("4 Rocks ==================================");
			assertEquals(7, out);

		}

	@Test
		public void TestSimulationFiveRocks() throws Exception {

			Simulator p = new Simulator(new String[] {"--dataFile=test_data.txt"});
			p.maxRocks = 5;
			p.debug = false;
			int out = p.runSimulation();
			System.out.println("5 Rocks ==================================");
			p.chamber.logger.debug = false;
			p.chamber.show();
			System.out.println("5 Rocks ==================================");
			assertEquals(9, out);

		}

	@Test
		public void TestSimulation10Rocks() throws Exception {

			Simulator p = new Simulator(new String[] {"--dataFile=test_data.txt"});
			p.maxRocks = 10;
			p.debug = false;
			int out = p.runSimulation();
			p.chamber.logger.debug = false;
			System.out.println("10 Rocks ==================================");
			p.chamber.show();
			System.out.println("End 10 Rocks ==================================");
			assertEquals(17, out);

		}

//	@Test
//		public void TestSimulation10RocksSmallData() throws Exception {
//
//			Simulator p = new Simulator(new String[] {"--dataFile=small_data.txt"});
//			p.maxRocks = 10;
//			System.out.println("SmallData 10 Rocks ==================================");
//			p.debug = false;
//			int out = p.runSimulation();
//			p.chamber.logger.debug = false;
//			p.chamber.show();
//			System.out.println("SmallData End 10 Rocks ==================================");
//			assertEquals(17, out);
//
//		}

}
