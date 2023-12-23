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
import java.lang.Math;

public class TestPyroclastic03Repetition {

	public static void main(String[] args) throws Exception {
		TestPyroclastic obj = new TestPyroclastic();
	}

//	@Test
//	public void TestSimulationLCMRocks() throws Exception {
//
//		Pyroclastic p = new Pyroclastic("test_data.txt");
//
//		int numRocks = 5;
//		int numJets =  p.data.get(0).split("").length;
//
//		p.maxRocks = numRocks * numJets;
//		p.debug = false;
//		int out = p.runSimulation();
//		p.chamber.logger.debug = true;
//		System.out.println("TestSimulationLCMRocks() numJets= " + numJets + " LCM=" +  numRocks * numJets);
//		p.chamber.show();
//		assertEquals(324, out);

//	}

//	@Test
//	public void TestSimulationTwoLCMRocks() throws Exception {
//
//		Pyroclastic p = new Pyroclastic("test_data.txt");
//
//		int numRocks = 5;
//		int numJets =  p.data.get(0).split("").length;
//
//		p.maxRocks = numRocks * numJets * 2;
//		p.debug = false;
//		int out = p.runSimulation();
//		p.chamber.logger.debug = true;
//		System.out.println("TestSimulationTwoLCMRocks() numJets= " + numJets + " 2 * LCM=" +  numRocks * numJets * 2);
//		p.chamber.show();
//		assertEquals(648, out);
//
//	}

//	@Test
//	public void TestSimulationFourLCMRocks() throws Exception {
//
//		Pyroclastic p = new Pyroclastic("test_data.txt");
//
//		int numRocks = 5;
//		int numJets =  p.data.get(0).split("").length;
//
//		p.maxRocks = numRocks * numJets * 2;
//		p.debug = false;
//		int out = p.runSimulation();
//		p.chamber.logger.debug = true;
//		System.out.println("TestSimulationTwoLCMRocks() numJets= " + numJets + " 2 * LCM=" +  numRocks * numJets * 2);
//		p.chamber.show();
//		assertEquals(648, out);
//
//	}

	Chamber getChamberLCM(int numLCMs) throws Exception{
		Pyroclastic p = new Pyroclastic("test_data.txt");

		int numRocks = 5;
		int numJets =  p.data.get(0).split("").length;

		System.out.println( "numRock=" + numRocks + " numJets=" + numJets + " LCM=" + (numRocks * numJets));
		p.maxRocks = numRocks * numJets * numLCMs;
		// p.debug = true;
		int out = p.runSimulation();
		return p.chamber;
	}

//	@Test
//	public void TestSimulationCompareChambers() throws Exception {
//
//		Chamber twoLCMs = getChamberLCM(2);
//
//		int counter1 = 0;
//		int lcm = 5 * 40;
//		System.out.println("In TestSimulationCompareChambers.size=" + twoLCMs.rocks.size());
//		for (counter1 = 0; counter1 < lcm; counter1++){
//			Rock first = twoLCMs.rocks.get(counter1);
//			Rock second = twoLCMs.rocks.get(counter1 + lcm);
//			System.out.println("counter1 :" + counter1);
//			System.out.println("first: \t" + first.label);
//			System.out.println("second:\t" + second.label);
//			System.out.println(first.toString() + " -- " + second.toString());
//			assertEquals(first.label, second.label);
//			// assertEquals(first.coords.get(0).x, second.coords.get(0).x);
//		}
//
//	}

//	@Test
//	public void TestSimulationCompareChambers4LCM() throws Exception {
//
//		Chamber twoLCMs = getChamberLCM(4);
//
//		int counter1 = 0;
//		int twoLcm = 5 * 40 * 2;
//		System.out.println("In TestSimulationCompareChambers.size=" + twoLCMs.rocks.size());
//		for (counter1 = 0; counter1 < twoLcm; counter1++){
//			Rock first = twoLCMs.rocks.get(counter1);
//			Rock second = twoLCMs.rocks.get(counter1 + twoLcm);
//			System.out.println("counter1 :" + counter1);
//			System.out.println("first: \t" + first.label);
//			System.out.println("second:\t" + second.label);
//			System.out.println(first.toString() + " -- " + second.toString());
//			assertEquals(first.label, second.label);
//			assertEquals(first.coords.get(0).x, second.coords.get(0).x);
//		}
//
//	}

	public void testSimulation( int maxRocks, int expectedHeight, boolean debug, String filename) throws Exception{
		Pyroclastic p = new Pyroclastic(filename);

		System.out.println("testSimulation maxRocks " + maxRocks + " filename " + filename);
		p.maxRocks = maxRocks;
		int out = p.runSimulation();
		p.chamber.logger.debug = debug;
		p.chamber.show();
		System.out.println( expectedHeight + " : " +  out);
		assertEquals(expectedHeight, out);
		System.out.println("End testSimulation");
	}

//	Pyroclastic getP(int maxRocks) throws Exception{
//		Pyroclastic p = new Pyroclastic("test_data.txt");
//
//		p.maxRocks = maxRocks;
//		int out = p.runSimulation();
//		return p;
//	}
//

	// This confirms the code still produces the correct answer for part one.
	// ie My improvements haven't broken it!
	@Test
	public void TestPartOneTestData() throws Exception{
		testSimulation(2022, 3068, false, "test_data.txt");
	}

	// This confirms the code still produces the correct answer for part one.
	// ie My improvements haven't broken it!
	@Test
	public void TestPartOneRealData() throws Exception{
		testSimulation(2022, 3085, false, "data.txt");
	}

	// There are 5 rocks. There are 40 movements in test_data.txt. Is a set/cycle 5 * 40 * 2?
	// 400
	@Test
	public void TestPartTwoWithTestData() throws Exception{
	     testSimulation(400, 608, false, "test_data.txt");
	}

	@Test
	public void TestPartTwoWithTestData2() throws Exception{
	     testSimulation(400 * 2, 608 * 2, true, "test_data.txt");
	}

	// There are 5 rocks. There are 10091 movements in data.txt. Is a set/cycle 5 * 10091 * 2?
	// 100910
	@Test
	public void TestTheoryOneRep() throws Exception{
		testSimulation(100910, 3085, false, "data.txt");
	}

	@Test
	public void TestTheoryTwoReps() throws Exception{
		testSimulation(100910 * 2, 3085, false, "data.txt");
	}

//	public void runSimulationForRepetitions(int lastIndex, int numRepetitions) throws Exception {
//
//		Pyroclastic p = getP(2022);
//
//		ArrayList<Rock> s = new ArrayList<Rock>(0);
//		s.addAll(p.chamber.rocks.subList(0, lastIndex));
//		System.out.println("About to call isSet()");
//		ArrayList<Integer> result = p.isSet(p.chamber.rocks, s, numRepetitions);
//		System.out.println("After to call isSet() " + result.get(0));
//		assertEquals(1, (int) result.get(0));
//
//		int numRocks = 1 + 5 * (numRepetitions - 1);
//		int height = result.get(2) + 1;
//		System.out.println("RESULT numRocks=" + numRocks + " height=" + height);
//	}
//
//	@Test
//	public void TestSimulationSmallList() throws Exception {
//		runSimulationForRepetitions(1, 1);
//	}
//
//	@Test
//	public void TestSimulationSmallList2() throws Exception {
//		runSimulationForRepetitions(1, 2);
//	}
//
//	@Test
//	public void TestSimulationSmallList3() throws Exception {
//		runSimulationForRepetitions(1, 3);
//	}
//
//	@Test
//	public void TestSimulationSmallList4() throws Exception {
//		runSimulationForRepetitions(1, 4);
//	}
//
//	@Test
//	public void TestSimulationSmallList5() throws Exception {
//		runSimulationForRepetitions(1, 5);
//	}
//
//	@Test
//	public void TestSimulationSmallList6() throws Exception {
//		runSimulationForRepetitions(1, 6);
//	}
//
//	@Test
//	public void TestSimulationSmallList7() throws Exception {
//		runSimulationForRepetitions(1, 7);
//	}
//
//	@Test
//	public void TestSimulationSmallList8() throws Exception {
//		runSimulationForRepetitions(1, 8);
//	}
//
//	@Test
//	public void TestSimulationSmallList9() throws Exception {
//		runSimulationForRepetitions(1, 9);
//	}
//
//	@Test
//	public void TestSimulationSmallList10() throws Exception {
//		runSimulationForRepetitions(1, 10);
//	}
//
//	@Test
//	public void TestSimulationSmallList11() throws Exception {
//		runSimulationForRepetitions(1, 11);
//	}
//
//	@Test
//	public void TestSimulationSmallList12() throws Exception {
//		runSimulationForRepetitions(1, 12);
//	}
//
//	@Test
//	public void TestSimulationSmallList13() throws Exception {
//		runSimulationForRepetitions(1, 13);
//	}
//
//	@Test
//	public void TestSimulationSmallList14() throws Exception {
//		runSimulationForRepetitions(1, 14);
//	}

	// This attempts to calculate the height for a given number of rocks besed 
	// on statistics learned from part one.
	double calcPartTwo(double numRocks) {

		// Cycle through this number of rocks in the same order.
		double typesOfRock = 5;

		// Pattern repeats after number number of cycles of typesOfRock.
		double numCyclesInRepetition = 7;

	    // numRocks = 1000000000000.0;
	    double height = 0.0;

		if (numRocks <= 0) {
			return height;
		}

		//  The first rock is always the horizontal one with height 1.
	    height = 1;
    	numRocks--;

		// Always return 1 if less than typesOfRock. That will be incorrect!
		if (numRocks <  typesOfRock) {
			return height;
		}

		// Very odd. I think it means the height will be 26 after 15 (or 16??) rocks.
	    height = height + 25;
	    numRocks -= 15;

		// Why?
		double heightRepetition = 18 + 23 + 6 + 6;

		double rocksInRepetition = numCyclesInRepetition * typesOfRock;
		System.out.println("00 calcPartTwo numRocks=" + numRocks + " rocksInRepetition=" + rocksInRepetition);

		double numCompleteRepetitions = numRocks / rocksInRepetition;
		numCompleteRepetitions = Math.floor(numCompleteRepetitions);

		System.out.println("01 calcPartTwo numCompleteRepetitions =" + numCompleteRepetitions);

		// Calculate the new height
		height = height + (numCompleteRepetitions * heightRepetition);
		System.out.println("calcPartTwo new height = " + height);

		numRocks = numRocks - (numCompleteRepetitions * rocksInRepetition);

		System.out.println("Rocks remaining=" + numRocks + " height=" + height);
		return height;
	}

	// This compares the output of p.runSimulation() and calcPartTwo(numRocks) as
	// the number of rocks is incremented by a set number.
	// The test ends when the number of rocks reaches a specific number.
	// It uses the full data set (data.txt).
//	@Test
//	public void Compare() throws Exception {
//
//		// Initial number of rocks.
//		double numRocks = 16.0;
//
//		double increaseRocksBy = 35.0;
//
//		double maxNumRocks = 100.0;
//
//		boolean same = true;
//		while (same) {
//			Pyroclastic p = new Pyroclastic("data.txt");
//			p.maxRocks = (int) numRocks;
//			int out = p.runSimulation();
//			p.chamber.logger.debug = true;
//			p.chamber.show();
//
//			double calculated = calcPartTwo(numRocks);
//
//			System.out.println("COMPARE numRocks=" + numRocks + " brute force=" + out + " calculated= " + calculated);
//
//			assertEquals(out, (int) calculated);
//
//			if (numRocks > maxNumRocks) {
//				same = false;
//			}
//
//			numRocks += increaseRocksBy;
//		}
//	}

//	@Test
//	public void GoForIt() throws Exception {
//		double numRocks = 1000000000000.0;
//
//		double calculated = calcPartTwo(numRocks);
//
//		System.out.println("GoForIt numRocks=" + numRocks + " calculated= " + calculated);
//
//	}

//	@Test
//	public void TestRealThing() throws Exception {
//		calcPartTwo(6);
//		calcPartTwo(26);
//		calcPartTwo(46);
//		calcPartTwo(47);
//		calcPartTwo(2022);
//	}

//	@Test
//	public void TestSimulationSomething() throws Exception {
//
//		Pyroclastic p = getP(2022);
//
//		ArrayList<Rock> s = new ArrayList<Rock>(0);
//		s.addAll(p.chamber.rocks.subList(0,5));
//		System.out.println("About to call isSet()");
//		boolean found = p.isSet(p.chamber.rocks, s, 2);
//		System.out.println("After to call isSet() " + found);
//		assertTrue("isSet() found something", found);
//
//	}
}
