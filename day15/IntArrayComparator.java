import java.io.IOException;
import java.util.*;

class IntArrayComparator implements Comparator<int[]> {

    public static void main(String[] args) {
		   IntArrayComparator obj = new IntArrayComparator();
     }

    public IntArrayComparator() {
    }

	public int compare(int[] a, int[] b) {
		int x = a[0];
		int y = b[0];
	    if (x < y) {
			return -1;
		}
		else if (x > y) {
			return 1;
		}
		return 0;
	}
}
