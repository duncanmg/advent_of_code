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

	@Test
	public void TestSimulation() throws Exception {

		System.out.println("Hello");
		Pyroclastic p = new Pyroclastic("test_data.txt");
		p.maxRocks = 100;
		p.debug = false;
		p.maxJets = 100;
		int out = p.runSimulation();
		p.chamber.logger.debug = false;
		p.chamber.show();
		assertEquals(157, out);

	}

	//   @Test
	//   public void TestSimulationJets() throws Exception {
	//
	//     Pyroclastic p = new Pyroclastic("test_data.txt");
	//	 p.maxRocks = 1;
	//	 p.debug = true;
	//	 p.maxJets = 3;
	//	 int out = p.runSimulation();
	//	 p.chamber.show();
	//     assertEquals(0, out);
	//
	//   }

}
