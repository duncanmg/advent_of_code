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

// Example cavernMap. This shows the first and last ten lines of a real file.
// Three TAB separated columns.
// duncan@ubuntu-s-1vcpu-1gb-lon1-01:~/java/AdventOfCode2022/day17$ head chamber_2022rocks.txt
// 0       (3085)  .c.....
// 1       (3084)  ccc....
// 2       (3083)  .c.....
// 3       (3082)  .hhhh..
// 4       (3081)  ...v...
// 5       (3080)  ...v...
// 6       (3079)  ...vs..
// 7       (3078)  ...vs..
// 8       (3077)  ..sss..
// 9       (3076)  .qq.c..
// duncan@ubuntu-s-1vcpu-1gb-lon1-01:~/java/AdventOfCode2022/day17$ tail chamber_2022rocks.txt
// 3075    (10)    ..v....
// 3076    (9)     v.v....
// 3077    (8)     v.v....
// 3078    (7)     v.s....
// 3079    (6)     v.s....
// 3080    (5)     sssqq..
// 3081    (4)     ..cqq..
// 3082    (3)     .ccc...
// 3083    (2)     ..c....
// 3084    (1)     ..hhhh.
// duncan@ubuntu-s-1vcpu-1gb-lon1-01:~/java/AdventOfCode2022/day17$ 

public class TestRepetitionFinder {

	public static void main(String[] args) throws Exception {
		TestRepetitionFinder obj = new TestRepetitionFinder();
	}

	long startTime = 0;
	void startTimer() {
		startTime = System.currentTimeMillis();
	}

	void stopTimer(String msg) {
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(msg + ". Current time: " + java.time.LocalDateTime.now() + ". Elapsed time: " + (elapsedTime/1000) + " seconds.");
	}

	@Test
		public void TestConstructor() throws Exception {

			RepetitionFinder rf = new RepetitionFinder();

			assertEquals(1, 1);

		}

	@Test
		public void TestSearchEmptyArrayLists() throws Exception {

			ArrayList<String> reversedCavernMap = new ArrayList<String>(0);

			this.testSearch(reversedCavernMap, 0, 0, false, -1);

		}

	@Test
		public void TestSearchRepetitionZero() throws Exception {


			ArrayList<String> reversedCavernMap = new ArrayList<String>(Arrays.asList("0	(2)	.c.....",
						"1	(1)	.c...h."));

			this.testSearch(reversedCavernMap, 0, 0, false, -1);
			RepetitionFinder rf = new RepetitionFinder();

		}

	@Test
		public void TestSearchShortArrayListsWithMatch() throws Exception {


			ArrayList<String> reversedCavernMap = new ArrayList<String>(Arrays.asList("0	(2)	.c.....",
						"1	(1)	.c....."));

			this.testSearch(reversedCavernMap, 1, 0, true, 1);
		}

	@Test
		public void TestSearchShortArrayListsNoMatch() throws Exception {

			ArrayList<String> reversedCavernMap = new ArrayList<String>(Arrays.asList("0	(2)	.c.....",
						"1	(1)	.c...h."));

			this.testSearch(reversedCavernMap, 1, 0, false, -1);
		}

	@Test
		public void TestSearchArrayListsRep2Match() throws Exception {

			ArrayList<String> reversedCavernMap = new ArrayList<String>(Arrays.asList("5	(1)	.c.....",
						"4	(2)	.c...h.",
						"3	(3)	.c.....",
						"2	(4)	.c.....",
						"1	(5)	.c.....",
						"0	(6)	.c...h."));

			this.testSearch(reversedCavernMap, 2, 0, true, 4);
		}

	@Test
		public void TestSearchArrayListsRep2NoMatch() throws Exception {

			ArrayList<String> reversedCavernMap = new ArrayList<String>(Arrays.asList("5	(1)	.c.....",
						"4	(2)	.c...h.",
						"3	(3)	.c.....",
						"2	(4)	.c.....",
						"1	(5)	.c...s.",
						"0	(6)	.c...h."));

			this.testSearch(reversedCavernMap, 2, 0, false, -1);
		}

	@Test
		public void TestSearchArrayListsRep1Offset1NoMatch() throws Exception {

			ArrayList<String> reversedCavernMap = new ArrayList<String>(Arrays.asList("5	(1)	.c.....",
						"4	(2)	.c...h.",
						"3	(3)	.c.....",
						"2	(4)	.c.....",
						"1	(5)	.c...s.",
						"0	(6)	.c...s."));

			this.testSearch(reversedCavernMap, 1, 1, false, -1);
		}

	@Test
		public void TestSearchArrayListsRep1Offset1Match() throws Exception {

			ArrayList<String> reversedCavernMap = new ArrayList<String>(Arrays.asList("5	(1)	.c.....",
						"4	(2)	.c...h.",
						"3	(3)	.c.....",
						"2	(4)	.c.....",
						"1	(5)	.c...h.",
						"0	(6)	.c...s."));

			this.testSearch(reversedCavernMap, 1, 1, true, 4);
		}

	@Test
		public void TestSearchArrayListsRep2Offset3NoMatch() throws Exception {

			ArrayList<String> reversedCavernMap = new ArrayList<String>(Arrays.asList("5	(1)	.c.....",
						"4	(2)	.c...h.",
						"3	(3)	.c.....",
						"2	(4)	.c.....",
						"1	(5)	.c...h.",
						"0	(6)	.c...s."));

			this.testSearch(reversedCavernMap, 2, 2, false, -1);
		}

	@Test
		public void TestSearchArrayListsRep2Offset3Match() throws Exception {

			ArrayList<String> reversedCavernMap = new ArrayList<String>(Arrays.asList("7	(1)	.c.....",
						"6	(2)	.c...h.",
						"5	(3)	.cv....",
						"4	(4)	.ch....",
						"3	(5)	.cv..h.",
						"2	(6)	.cv..h.",
						"1	(7)	.cv....",
						"0	(8)	.ch...."));

			this.testSearch(reversedCavernMap, 2, 2, true, 6);
		}

	void testSearch(ArrayList<String>reversedCavernMap, int repetitionSize, int offset, boolean expectedResult, int expectedRepetitionStartsAt) throws Exception {
			System.out.println("----------");
			RepetitionFinder rf = new RepetitionFinder();

			rf.reversedCavernMap = reversedCavernMap;

			rf.buildPatternArrayLists(repetitionSize, offset);

			assertEquals(repetitionSize, rf.targetPatterns.size());
			assertEquals(reversedCavernMap.size(), rf.allPatterns.size());

			Boolean found = rf.search(offset);

			assertEquals(expectedResult, found);
			assertEquals(expectedRepetitionStartsAt, rf.repetitionStartsAt);
	}

	@Test
		public void TestRepetitionFinderMatch() throws Exception {

			RepetitionFinder rf = new RepetitionFinder();

			// The file has 40 lines, 2 identical sets of 20. 0-19 and 20-39.
			assertEquals(true, rf.findRepetition("test_repetition_finder01.txt", 2, 0));
			assertEquals(20, rf.repetitionStartsAt);
		}

	@Test
		public void TestRepetitionFinderNoMatch() throws Exception {

			RepetitionFinder rf = new RepetitionFinder();

			// The file has 40 lines, no repetition.
			assertEquals(false, rf.findRepetition("test_repetition_finder02.txt", 2, 0));
			assertEquals(-1, rf.repetitionStartsAt);
		}
}
