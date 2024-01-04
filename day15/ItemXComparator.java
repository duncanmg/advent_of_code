import java.io.IOException;
import java.util.*;

class ItemXComparator implements Comparator<ArrayList<Item>> {

    public static void main(String[] args) {
		   ItemXComparator obj = new ItemXComparator();
     }

    public ItemXComparator() {
    }

	public int compare(ArrayList<Item> list1, ArrayList<Item> list2) {
		Item i1 = list1.get(0);
		Item i2 = list2.get(0);
	    if (i1.x < i2.x) {
			return -1;
		}
		else if (i1.x > i2.x) {
			return 1;
		}
		return 0;
	}
}
