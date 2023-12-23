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

public class TestPyroclastic01Basic {

	public static void main(String[] args) throws Exception {
		TestPyroclastic obj = new TestPyroclastic();
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

	System.out.println("02 XXXX p");
	Pyroclastic p = new Pyroclastic();

	System.out.println("Created p");
	for (int i=0; i<6; i++) {
	System.out.println(i);
		got.add(p.nextRock().getClass().getName());
	}

	assertEquals(expected, got);
	System.out.println("Finished TestNextRock");
	}

	@Test public void TestNextJet() throws Exception {

	ArrayList<String> expected = new ArrayList<String>(0);
	ArrayList<String> got = new ArrayList<String>(0);

	Pyroclastic p = new Pyroclastic("test_data.txt");
	p.initializeJets();
	String data_str = p.data.get(0);
	int s = data_str.length();

	// Step through the jets to the end
	for (int i=0; i<s; i++) {
		String str = p.nextJet();
	}

	// It should have cycled back to the beginning
	// so these should be the same.
	for (int i=0; i<5; i++) {
		got.add(p.nextJet());
		expected.add(p.jets[i]);
	}

	assertEquals(expected, got);
	}

	@Test
	public void TestConstructor() throws Exception {

		Pyroclastic p = new Pyroclastic();
		assertEquals(p.getClass(), Pyroclastic.class);

		p = new Pyroclastic("test_data.txt");
		assertEquals(p.getClass(), Pyroclastic.class);
	}

	@Test
	public void TestSimulation() throws Exception {

		Pyroclastic p = new Pyroclastic("test_data.txt");
		p.maxRocks = 1;
		p.debug = false;
		int out = p.runSimulation();
		p.chamber.logger.debug = true;
		p.chamber.show();
		assertEquals(1, out);

	}

	@Test
	public void TestSimulationOneRock() throws Exception {

		System.out.println("Start TestSimulationOneRock()");
		Pyroclastic p = new Pyroclastic("test_data.txt");
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
		Pyroclastic p = new Pyroclastic("test_data.txt");
		p.maxRocks = 2;
		p.debug = false;
		int out = p.runSimulation();
		p.chamber.logger.debug = false;
		p.chamber.show();
		assertEquals(4, out);

	}

	@Test
	public void TestSimulationThreeRocks() throws Exception {

		Pyroclastic p = new Pyroclastic("test_data.txt");
		p.maxRocks = 3;
		p.debug = false;
		int out = p.runSimulation();
		// p.chamber.logger.debug = true;
		p.chamber.show();
		assertEquals(6, out);

	}

	@Test
	public void TestSimulationFourRocks() throws Exception {

		Pyroclastic p = new Pyroclastic("test_data.txt");
		p.maxRocks = 4;
		p.debug = false;
		int out = p.runSimulation();
		p.chamber.logger.debug = false;
		System.out.println("4 Rocks ==================================");
		p.chamber.show();
		System.out.println("4 Rocks ==================================");
		assertEquals(7, out);

	}

	@Test
	public void TestSimulationFiveRocks() throws Exception {

		Pyroclastic p = new Pyroclastic("test_data.txt");
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

		Pyroclastic p = new Pyroclastic("test_data.txt");
		p.maxRocks = 10;
		p.debug = false;
		int out = p.runSimulation();
		p.chamber.logger.debug = false;
		System.out.println("10 Rocks ==================================");
		p.chamber.show();
		System.out.println("End 10 Rocks ==================================");
		assertEquals(17, out);

	}

	@Test
	public void TestSimulation10RocksSmallData() throws Exception {

		Pyroclastic p = new Pyroclastic("small_data.txt");
		p.maxRocks = 10;
		System.out.println("SmallData 10 Rocks ==================================");
		p.debug = false;
		int out = p.runSimulation();
		p.chamber.logger.debug = false;
		p.chamber.show();
		System.out.println("SmallData End 10 Rocks ==================================");
		assertEquals(17, out);

	}

}
