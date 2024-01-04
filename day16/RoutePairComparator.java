import java.io.IOException;
import java.util.*;

class RoutePairComparator implements Comparator<RoutePair> {

    public static void main(String[] args) {
		   RoutePairComparator obj = new RoutePairComparator();
     }

    public RoutePairComparator() {
    }

	public int compare(RoutePair r1, RoutePair r2) {
	    if (r1.estimatedTotalFlow() < r2.estimatedTotalFlow()) {
			return -1;
		}
		else if (r1.estimatedTotalFlow() > r2.estimatedTotalFlow()) {
			return 1;
		}
		return 0;
	}
}
