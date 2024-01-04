import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Pairs {

	private Data data = new Data();

    public static void main(String[] args) {
        Pairs obj = new Pairs();
    }

    public Pairs() {
		ArrayList<String> data = this.data.data;
		ArrayList<RangePair> rangePairs = new ArrayList<RangePair>(0);

		for (int i=0; i<data.size(); i++) {
			rangePairs.add(new RangePair(data.get(i)));
		}

		int total = 0;
		for (int i=0; i<rangePairs.size(); i++) {
			RangePair rp = rangePairs.get(i);

			// Right totally contained within left.
			if (rp.leftLower <= rp.rightLower && rp.leftUpper >= rp.rightUpper) {
					total++;
			}
			// Left totally contained within right.
			else if (rp.rightLower <= rp.leftLower && rp.rightUpper >= rp.leftUpper) {
                     total++;
             }
		}
        System.out.println("total="+ total);
    }

}
