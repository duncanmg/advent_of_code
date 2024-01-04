import java.io.IOException;
import java.util.*;

class IntArrayColComparator implements Comparator<int[]> {

    public static void main(String[] args) {
		   IntArrayColComparator obj = new IntArrayColComparator();
     }

    public IntArrayColComparator() {
    }

	public int compare(int[] a, int[] b) {
		int x = a[1];
		int y = b[1];
	    if (x < y) {
			return -1;
		}
		else if (x > y) {
			return 1;
		}
		return 0;
	}
}
