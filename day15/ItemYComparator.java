import java.io.IOException;
import java.util.*;

class ItemYComparator implements Comparator<Item> {

    public static void main(String[] args) {
		   ItemYComparator obj = new ItemYComparator();
     }

    public ItemYComparator() {
    }

	public int compare(Item i1, Item i2) {
	    if (i1.y < i2.y) {
			return -1;
		}
		else if (i1.y > i2.y) {
			return 1;
		}
		return 0;
	}
}
