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

			ArrayList<String> cavernMap = new ArrayList<String>(0);

			this.testSearch(cavernMap, 0, 0, false);

		}

	@Test
		public void TestSearchRepetitionZero() throws Exception {


			ArrayList<String> cavernMap = new ArrayList<String>(Arrays.asList("0	(2)	.c.....",
						"1	(1)	.c...h."));

			this.testSearch(cavernMap, 0, 0, false);
			RepetitionFinder rf = new RepetitionFinder();

		}

	@Test
		public void TestSearchShortArrayListsWithMatch() throws Exception {


			ArrayList<String> cavernMap = new ArrayList<String>(Arrays.asList("0	(2)	.c.....",
						"1	(1)	.c....."));

			this.testSearch(cavernMap, 1, 0, true);
		}

	@Test
		public void TestSearchShortArrayListsNoMatch() throws Exception {

			ArrayList<String> cavernMap = new ArrayList<String>(Arrays.asList("0	(2)	.c.....",
						"1	(1)	.c...h."));

			this.testSearch(cavernMap, 1, 0, false);
		}

	@Test
		public void TestSearchArrayListsRep2Match() throws Exception {

			ArrayList<String> cavernMap = new ArrayList<String>(Arrays.asList("0	(6)	.c.....",
						"1	(5)	.c...h.",
						"2	(4)	.c.....",
						"3	(3)	.c.....",
						"4	(2)	.c.....",
						"5	(1)	.c...h."));

			this.testSearch(cavernMap, 2, 0, true);
		}

	@Test
		public void TestSearchArrayListsRep2NoMatch() throws Exception {

			ArrayList<String> cavernMap = new ArrayList<String>(Arrays.asList("0	(6)	.c.....",
						"1	(5)	.c...h.",
						"2	(4)	.c.....",
						"3	(3)	.c.....",
						"4	(2)	.c...s.",
						"5	(1)	.c...h."));

			this.testSearch(cavernMap, 2, 0, false);
		}

	void testSearch(ArrayList<String>cavernMap, int repetitionSize, int offset, boolean expectedResult) throws Exception {
			System.out.println("----------");
			RepetitionFinder rf = new RepetitionFinder();

			rf.cavernMap = cavernMap;

			rf.buildPatternArrayLists(repetitionSize, offset);

			assertEquals(repetitionSize, rf.targetPatterns.size());
			assertEquals(cavernMap.size(), rf.allPatterns.size());
			assertEquals(expectedResult, rf.search(offset));
	}
}
