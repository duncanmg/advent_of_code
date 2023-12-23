import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class RepetitionFinder {

	// A cavernMapFile gives the rock at each position in this format:
	// duncan@ubuntu-s-1vcpu-1gb-lon1-01:~/java/AdventOfCode2022/day17$ head -5 ./real_data50000.tmp
	// Chamber: 0      (76761) ..qq...
	// Chamber: 1      (76760) ..qq...
	// Chamber: 2      (76759) ..v....
	// Chamber: 3      (76758) ..v....
	// Chamber: 4      (76757) ..v.s..
	// duncan@ubuntu-s-1vcpu-1gb-lon1-01:~/java/AdventOfCode2022/day17$ tail -5 ./real_data50000.tmp
	// Chamber: 76756  (5)     sssqq..
	// Chamber: 76757  (4)     ..cqq..
	// Chamber: 76758  (3)     .ccc...
	// Chamber: 76759  (2)     ..c....
	// Chamber: 76760  (1)     ..hhhh.
	//
	// The repetitionSize is the number of repeating patterns to look for.
	//
	// The offset is the number of lines to skip before starting to look for the repetition.
	//
	public static void main(String[] args) {

		String cavernMapFile = args[0];
		int repetitionSize = Integer.parseInt(args[1]);
		int offset = Integer.parseInt(args[2]);

		try { 
			if (args.length == 2) {
				RepetitionFinder obj = new RepetitionFinder();
				obj.movingFindRepetition(cavernMapFile, repetitionSize);
			}
			else if (args.length == 3) {
				RepetitionFinder obj = new RepetitionFinder();
				obj.findRepetition(cavernMapFile, repetitionSize, offset);
			}
			else {
				RepetitionFinder obj = new RepetitionFinder();
			}
		} catch (Exception e) {
			System.out.println(e);
		}	
	}

	public RepetitionFinder() {
		System.out.println("RepetitionFinder object created");
	}

	public RepetitionFinder(String dataFile) {
		data = this.dataObj.getData(dataFile);
	}

	public RepetitionFinder(String dataFile, int maxRocks) {
		data = this.dataObj.getData(dataFile);
	}

	public RepetitionFinder(String dataFile, int maxRocks, boolean show) {
		data = this.dataObj.getData(dataFile);
	}

	Logger logger = new Logger(this);

	Boolean debug = false;

	void log(String msg) {
		this.logger.debug = this.debug;
		this.logger.log(msg);
	}

	Data dataObj = new Data();

	public ArrayList<String> data = new ArrayList<String>(0);

	ArrayList<String> targetPatterns = new ArrayList<String>(0);

	ArrayList<String> allPatterns = new ArrayList<String>(0);

	public boolean  findRepetition(String cavernMapFile, int repetitionSize, int offset) throws Exception {

		// Load the cavernMap and reverse it so it starts from the ground.
		data = this.dataObj.getData(cavernMapFile);
		Collections.reverse(data);

		String patternRegEx = "^[.hcqsv]{7}$";
		Pattern patternMatcher = Pattern.compile(patternRegEx);

		targetPatterns = new ArrayList<String>(0);
		allPatterns = new ArrayList<String>(0);
		// Each row in data looks like: Chamber: 30636  (31)    ...v...
		for (int i=0; i<data.size(); i++) {

			// Split on tabs. Get 3 bits. Last bit is the pattern eg ...v...
			String[] bits = data.get(i).split("\t");
			String pattern = bits[2];

			Matcher matcher = patternMatcher.matcher(pattern);
			if (! matcher.matches()) {
				throw new Exception(i + " " + pattern + " does not match");
			}

			allPatterns.add(pattern);

			if (i >= offset && i<repetitionSize + offset) {
				targetPatterns.add(pattern);
			}

		}

		// System.out.println("Size allPatterns=" + allPatterns.size() + " targetPatterns=" + targetPatterns.size());
		return this.search(offset);
	}

	void movingFindRepetition( String cavernMapFile, int repetitionSize) throws Exception {
		boolean more = true;
		int i = 0;
		while (more) {
			System.out.println("i=" + i);
			boolean found = this.findRepetition(cavernMapFile, repetitionSize, i);
			if (found) {
				System.out.println("Found at offset " + i);
				break;
			}
			i++;
			if (i >= this.allPatterns.size() - repetitionSize) {
				more = false;
			}
		}
		if (! more) {
			System.out.println("Not found");
		}
		System.out.println("Finished");
	}

	boolean search(int offset) {

		int repetitionSize = this.targetPatterns.size();

		// eg If the repetition size is 2 then the earliest possible match
		// is at positions 2 to 3 (abab).
		// So we would start at position 3 and compare 0 and 2, 1 and 3.
		int start = (repetitionSize * 2) + offset;

		// System.out.println("Repetition size=" + repetitionSize);
		System.out.println("Start=" + start);
		// Iterate across all the patterns.
		for (int i = start; i<this.allPatterns.size(); i++) {

			int patternNo = 0;
			int numMatches = 0;

			for (int j = i - repetitionSize; j < i; j++) {
				// System.out.println("i=" + i + " compare " + this.allPatterns.get(j) + " and " + this.targetPatterns.get(patternNo));
				// System.out.println("j=" + j + " patternNo=" + patternNo);
				if (this.allPatterns.get(j).equals(this.targetPatterns.get(patternNo))) {
					// System.out.println("Match " + numMatches);
					numMatches++;
				}
				patternNo++;
			}

			if (numMatches == repetitionSize) {
				System.out.println("numMatches=" + numMatches + " repetitionSize=" + repetitionSize);
				int repStart = i - repetitionSize;
				System.out.println("Match starts at " + repStart + " zero based or " + (repStart + 1) + " counting from 1");
				return true;
			}
		}
		return false;
	}

}
