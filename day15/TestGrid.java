//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;

public class TestGrid {

   public static void main(String[] args) throws Exception {
       TestGrid obj = new TestGrid();
   }

   @BeforeEach
   	void setUp() throws Exception {
   	}

   @Test
   public void TestConstructor() throws Exception {
     Grid grid = new Grid();
     assertEquals(grid.getClass(), Grid.class);
   }

   @Test
   public void TestRemoveOverlap() throws Exception {
     Grid grid = new Grid();

	 assertEquals(1,1);

	 int[] in1 = new int[]{1,2};
	 int[] in2 = new int[]{3,4};
     assertEquals(this.convert(grid.removeOverlap(in1,in2)), "[1, 2][3, 4]");

	 in1 = new int[]{1,3};
	 in2 = new int[]{3,4};
     assertEquals(this.convert(grid.removeOverlap(in1,in2)), "[1, 2][3, 4]");

	 in1 = new int[]{1,4};
	 in2 = new int[]{3,6};
     assertEquals(this.convert(grid.removeOverlap(in1,in2)), "[1, 2][3, 6]");

	 in1 = new int[]{1,8};
	 in2 = new int[]{3,6};
     assertEquals(this.convert(grid.removeOverlap(in1,in2)), "[1, 8]");
   }

   @Test
   public void TestRemoveOverlaps() throws Exception {
     Grid grid = new Grid();

	 // ArrayList<int[]> removeOverlaps(ArrayList<int[]> allRanges) {

	 int[] in1 = new int[]{1,2};
	 int[] in2 = new int[]{3,4};
	 ArrayList<int[]> allIn = new ArrayList<int[]>(0);
	 allIn.add(in1);
	 allIn.add(in2);
     assertEquals("[1, 2][3, 4]", this.convert(grid.removeOverlaps(allIn)));

	 in1 = new int[]{1,2};
	 in2 = new int[]{3,7};
	 int[] in3 = new int[]{3,4};
	 allIn = new ArrayList<int[]>(0);
	 allIn.add(in1);
	 allIn.add(in2);
	 allIn.add(in3);
     assertEquals("[1, 2][3, 7]", this.convert(grid.removeOverlaps(allIn)));

   }

   @Test
   public void TestRemoveOverlapsInTestData() throws Exception {
     Grid grid = new Grid();

	 // ArrayList<int[]> removeOverlaps(ArrayList<int[]> allRanges) {

	 int[] in1 = new int[]{-2,2};
	 int[] in2 = new int[]{2,14};
	 int[] in3 = new int[]{2,2};

	 ArrayList<int[]> allIn = new ArrayList<int[]>(0);
	 allIn.add(in1);
	 allIn.add(in2);
	 allIn.add(in3);

     assertEquals("[-2, 1][2, 14]", this.convert(grid.removeOverlaps(allIn)));

   }

   @Test
   public void TestRemoveOverlapsInTestData2() throws Exception {
     Grid grid = new Grid();

	 // ArrayList<int[]> removeOverlaps(ArrayList<int[]> allRanges) {

	 int[] in1 = new int[]{-2,2};
	 int[] in2 = new int[]{2,14};
	 int[] in3 = new int[]{2,2};
	 int[] in4 = new int[]{12,12};

	 ArrayList<int[]> allIn = new ArrayList<int[]>(0);
	 allIn.add(in1);
	 allIn.add(in2);
	 allIn.add(in3);
	 allIn.add(in4);

     assertEquals("[-2, 1][2, 14]", this.convert(grid.removeOverlaps(allIn)));

   }

   @Test
   public void TestRemoveOverlapsInTestData3() throws Exception {
     Grid grid = new Grid();

	 // ArrayList<int[]> removeOverlaps(ArrayList<int[]> allRanges) {

	 int[] in1 = new int[]{-2,2};
	 int[] in2 = new int[]{2,14};
	 int[] in3 = new int[]{2,2};
	 int[] in4 = new int[]{12,12};
	 int[] in5 = new int[]{14,18};
	 int[] in6 = new int[]{16,24};

	 ArrayList<int[]> allIn = new ArrayList<int[]>(0);
	 allIn.add(in1);
	 allIn.add(in2);
	 allIn.add(in3);
	 allIn.add(in4);
	 allIn.add(in5);
	 allIn.add(in6);

     assertEquals("[-2, 1][2, 13][14, 15][16, 24]", this.convert(grid.removeOverlaps(allIn)));

   }

   String convert(ArrayList<int[]> in) {
	   String out = "";
	   Iterator<int[]> iterator = in.iterator();
	   while (iterator.hasNext()) {
		   out = out + Arrays.toString(iterator.next());
	   }
	   return out;
   }
 }
