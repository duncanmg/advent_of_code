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
	// eg ./run.sh RepetitionFinder chamber_2022rocks.txt 25 1
	public static void main(String[] args) {


		try { 
			if (args.length == 2) {
				String cavernMapFile = args[0];
				int repetitionSize = Integer.parseInt(args[1]);
				System.out.println("cavernMapFile=" + cavernMapFile + " repetitionSize=" + repetitionSize);
				RepetitionFinder obj = new RepetitionFinder();
				obj.movingFindRepetition(cavernMapFile, repetitionSize);
			}
			else if (args.length == 3) {
				String cavernMapFile = args[0];
				int repetitionSize = Integer.parseInt(args[1]);
				int offset = Integer.parseInt(args[2]);
				System.out.println("cavernMapFile=" + cavernMapFile + " repetitionSize=" + repetitionSize + " offset=" + offset);
				System.out.println("About to call obj.findRepetition(cavernMapFile, repetitionSize, offset)");
				RepetitionFinder obj = new RepetitionFinder();
				obj.findRepetition(cavernMapFile, repetitionSize, offset);
			}
			else {
				System.out.println("Call with no arguments");
				RepetitionFinder obj = new RepetitionFinder();
			}
		} catch (Exception e) {
			System.out.println(e);
		}	
	}

	public RepetitionFinder() {
		System.out.println("RepetitionFinder object created");
	}

	public RepetitionFinder(String cavernMapFile) {
		reversedCavernMap = this.cavernMapObj.getData(cavernMapFile);
		Collections.reverse(this.reversedCavernMap);
	}

	public RepetitionFinder(String cavernMapFile, int maxRocks) {
		reversedCavernMap = this.cavernMapObj.getData(cavernMapFile);
		Collections.reverse(this.reversedCavernMap);
	}

	public RepetitionFinder(String cavernMapFile, int maxRocks, boolean show) {
		reversedCavernMap = this.cavernMapObj.getData(cavernMapFile);
		Collections.reverse(this.reversedCavernMap);
	}

	Logger logger = new Logger(this, true);

	Data cavernMapObj = new Data();

	public ArrayList<String> reversedCavernMap = new ArrayList<String>(0);

	ArrayList<String> targetPatterns = new ArrayList<String>(0);

	ArrayList<String> allPatterns = new ArrayList<String>(0);

	// Zero based.
	int repetitionStartsAt = -1;

	// Zero based.
	int offsetWhenMatchFound = -1;

	public boolean  findRepetition(String cavernMapFile, int repetitionSize, int offset) throws Exception {

		// Load the cavernMap and reverse it so it starts from the ground.
		reversedCavernMap = this.cavernMapObj.getData(cavernMapFile);
		Collections.reverse(reversedCavernMap);

		this.buildPatternArrayLists(repetitionSize, offset);

		this.logger.log("Size allPatterns=" + allPatterns.size() + " targetPatterns=" + targetPatterns.size());
		return this.search(offset);
	}

	// Note that the cavernMap has bee reversed so it starts from the ground.
	// Chamber: 76760  (1)     ..hhhh.
	// Chamber: 76759  (2)     ..c....
	public void  buildPatternArrayLists(int repetitionSize, int offset) throws Exception {

		String patternRegEx = "^[.hcqsv]{7}$";
		Pattern patternMatcher = Pattern.compile(patternRegEx);

		targetPatterns = new ArrayList<String>(0);
		allPatterns = new ArrayList<String>(0);

		// Each row in reversedCavernMap looks like: Chamber: 30636  (31)    ...v...
		for (int i=0; i<reversedCavernMap.size(); i++) {

			// Split on tabs. Get 3 bits. Last bit is the pattern eg ...v...
			String[] bits = reversedCavernMap.get(i).split("\t");
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

	}

	// Repeatedely call findRepetition with increasing offsets until a repetition is found or the chamber has been traversed.
	public boolean movingFindRepetition( String cavernMapFile, int repetitionSize) throws Exception {

		boolean found = false;
		int offset = 0;
		while (! found) {
			this.logger.log("offset=" + offset);
			found = this.findRepetition(cavernMapFile, repetitionSize, offset);
			if (found) {
				System.out.println("Found at offset " + offset);
				return true;
			}
			offset++;
			if (offset >= this.allPatterns.size() - repetitionSize) {
				return false;
			}
		}
		if (! found) {
			System.out.println("Not found");
		}
		System.out.println("Finished");
		return found;
	}

	boolean search(int offset) {

		int repetitionSize = this.targetPatterns.size();

		if (repetitionSize <= 0 ) {
			this.logger.log("repetitionSize must be greater than 0 for a match.");
			return false;
		}

		if (repetitionSize * 2 > this.allPatterns.size() ) {
			this.logger.log("repetitionSize cannot be more than half the number of patterns for a match.");
			return false;
		}

		// eg If the repetition size is 2 then the earliest possible match
		// is at positions 2 to 3 (abab).
		// So we would start at position 3 and compare 0 and 2, 1 and 3.
		int start = repetitionSize + offset;

		System.out.println("Repetition size=" + repetitionSize + " offset=" + offset);
		System.out.println("Start=" + start + " allPatterns.size()=" + this.allPatterns.size());
		// Iterate across all the patterns.
		for (int i = start; i<this.allPatterns.size(); i++) {

			int patternNo = 0;
			int numMatches = 0;

			for (int j = i; j < i + repetitionSize; j++) {

				if (j >= this.allPatterns.size()) {
					break;
				}	

				// System.out.println("i=" + i + " j=" + j + " patternNo=" + patternNo);
				// System.out.println("Compare allPatterns.get(j) " + this.allPatterns.get(j) 
				// 	+ " and targetPatterns.get(patternNo) " + this.targetPatterns.get(patternNo));
				if (this.allPatterns.get(j).equals(this.targetPatterns.get(patternNo))) {
					// System.out.println("i=" + i + " j=" + j + " Got a match. numMatches was " + numMatches + " now " + (numMatches + 1));
					numMatches++;
				}
				patternNo++;
			}

			if (numMatches == repetitionSize) {
				this.logger.log("i=" + i + " numMatches=" + numMatches + " repetitionSize=" + repetitionSize);
				this.repetitionStartsAt = i;
				this.offsetWhenMatchFound = offset;
				System.out.println("Match starts at " + this.repetitionStartsAt + " zero based or " + (this.repetitionStartsAt + 1) + " counting from 1");
				return true;
			}
		}
		System.out.println("No match found");
		return false;
	}

}
