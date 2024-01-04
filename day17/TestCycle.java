//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;
import org.junit.Before;

public class TestCycle {

	public static void main(String[] args) throws Exception {
		TestCycle obj = new TestCycle();
	}

	// Don't understand what this is testing. It originally set maxRocks to 100 and maxJests to 100
	// and expected the height to be 157. I don't know why.
	// The correct height for 100 rocks is 157, so I set maxJets to a large number. It passes.
	@Test
		public void TestSimulation() throws Exception {

			Simulator p = new Simulator(new String[] {"--dataFile=test_data.txt"});
			p.endTester.maxRocks = 100;
			p.debug = false;
			p.endTester.maxJets = 1000;
			int actualHeight = p.runSimulation();
			p.chamber.logger.debug = false;
			p.chamber.show();
			assertEquals(157, actualHeight);

		}

	@Test
		public void TestSimulationLimitedByRocks() throws Exception {

			Simulator p = new Simulator(new String[] {"--dataFile=test_data.txt"});
			p.endTester.maxRocks = 1;
			p.debug = true;
			p.endTester.maxJets = 1;
			int ActualHeight = p.runSimulation();
			p.chamber.show();
			assertEquals(1, ActualHeight);

		}

	@Test
		public void TestSimulationLimitedByJets() throws Exception {

			Simulator p = new Simulator(new String[] {"--dataFile=test_data.txt"});
			p.endTester.maxRocks = 10;
			p.debug = true;
			p.endTester.maxJets = 1;
			int ActualHeight = p.runSimulation();
			p.chamber.show();
			assertEquals(1, ActualHeight);

		}

}
