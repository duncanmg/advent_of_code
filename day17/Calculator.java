import java.io.IOException;
import java.util.*;
import java.math.BigInteger;

class Calculator {

	public static void main(String[] args) {
		try {
			Calculator obj = new Calculator(args);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	public Calculator(String[] args) throws Exception{
		ArgumentProcessor argProcessor = new ArgumentProcessor(args);
		argMap = argProcessor.process();
		run();
	}

	HashMap<String, String> argMap;

	Logger logger = new Logger(this, true);

	// The number of rocks specified in part 2 of the puzzle.
	BigInteger maxRocks = new BigInteger("1000000000000");

	// These four numbers have been derived using data.txt, Simulator and FindRepetitions.
	BigInteger rocksRepetition = new BigInteger("1705");

	BigInteger rocksOffset = new BigInteger("615");

	BigInteger heightRepetition = new BigInteger("2618");

	BigInteger heightOffset = new BigInteger("929");

	// The cavernMap will consist of a bit at the beginning (the offset) which can't have its height calculated
	// and a bit at the end (the tail) which can't have its calculated. The middle section (the core) can have its height
	// calculated because its a series of repetitions and we know the repetition size.
	// The correct answer is 1535483870924.
	public BigInteger run() throws Exception {

		BigInteger rocksCore = this.maxRocks.subtract(this.rocksOffset);

		BigInteger rocksRepetitions = rocksCore.divide(this.rocksRepetition);

		BigInteger rocksTail = rocksCore.remainder(this.rocksRepetition);

		// Base calculation using repetitions. Excludes offset and tail.
		BigInteger height = rocksRepetitions.multiply(this.heightRepetition);

		int rocksForCalc = rocksTail.add(this.rocksOffset).intValue();
		String[] simulatorArgs = new String[]{"--dataFile=data.txt", ("--maxRocks=" + rocksForCalc)};

		// Add offset and tail using simulator.
		Simulator simulator = new Simulator(simulatorArgs);
		int offsetAndTailHeight = simulator.runSimulation();

		height = height.add(new BigInteger(Integer.toString(offsetAndTailHeight)));

		System.out.println("height = " + height);
		return height;
	}

}
