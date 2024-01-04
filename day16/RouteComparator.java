import java.io.IOException;
import java.util.*;

class RouteComparator implements Comparator<Route> {

    public static void main(String[] args) {
		   RouteComparator obj = new RouteComparator();
     }

    public RouteComparator() {
    }

	public int compare(Route r1, Route r2) {
	    if (r1.estimatedTotalFlow() < r2.estimatedTotalFlow()) {
			return -1;
		}
		else if (r1.estimatedTotalFlow() > r2.estimatedTotalFlow()) {
			return 1;
		}
		return 0;
	}
}
