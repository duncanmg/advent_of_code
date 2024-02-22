import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestBlueprintFactory {

	public static void main(String[] args) throws Exception {
		TestBlueprintFactory obj = new TestBlueprintFactory();
	}

	@Test public void TestConstructor() throws Exception {

		BlueprintFactory factory = new BlueprintFactory(new String[] {});

		assertEquals(factory.getClass().getName(), "BlueprintFactory");

	}

	@Test public void TestRealData() throws Exception {

		BlueprintFactory factory = new BlueprintFactory(new String[] {"--dataFile=data.txt"});
		factory.parseDataFile();
		HashMap<Integer, ArrayList<Integer>> blueprintValues= factory.blueprintValues;

		String[] expected = new String[]{
			"1 [4, 4, 4, 7, 2, 19]",
				"2 [2, 4, 4, 20, 4, 18]",
				"3 [4, 4, 3, 20, 2, 10]",
				"4 [3, 4, 2, 19, 2, 12]",
				"5 [3, 4, 3, 20, 3, 14]",
				"6 [3, 4, 2, 15, 3, 7]",
				"7 [3, 3, 2, 19, 2, 20]",
				"8 [2, 3, 3, 13, 2, 20]",
				"9 [2, 2, 2, 8, 2, 14]",
				"10 [4, 4, 2, 11, 3, 14]",
				"11 [3, 4, 4, 5, 4, 8]",
				"12 [3, 3, 2, 16, 2, 18]",
				"13 [3, 4, 2, 11, 2, 10]",
				"14 [4, 4, 2, 14, 3, 17]",
				"15 [3, 3, 3, 19, 3, 17]",
				"16 [2, 4, 3, 20, 2, 17]",
				"17 [4, 4, 3, 14, 4, 8]",
				"18 [2, 3, 3, 9, 3, 9]",
				"19 [4, 4, 2, 10, 3, 14]",
				"20 [3, 3, 2, 13, 3, 12]",
				"21 [4, 3, 4, 15, 4, 9]",
				"22 [3, 3, 3, 20, 2, 12]",
				"23 [4, 3, 4, 19, 4, 12]",
				"24 [4, 4, 4, 15, 3, 8]",
				"25 [2, 3, 3, 11, 2, 16]",
				"26 [3, 4, 3, 17, 3, 7]",
				"27 [4, 4, 3, 7, 3, 20]",
				"28 [4, 3, 3, 10, 2, 10]",
				"29 [4, 4, 4, 17, 2, 13]",
				"30 [4, 3, 4, 20, 4, 8]"
		};

		int i=0;
		for (Integer blueprintId : blueprintValues.keySet()) {
			ArrayList<Integer> values = blueprintValues.get(blueprintId);
			assertEquals(expected[i], blueprintId + " " + values);
			i++;
		}

	}

}
