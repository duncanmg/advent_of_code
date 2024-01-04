import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class PairsPart2 {

	private Data data = new Data();

    public static void main(String[] args) {
        PairsPart2 obj = new PairsPart2();
    }

    public PairsPart2() {
		ArrayList<String> data = this.data.data;
		ArrayList<RangePair> rangePairs = new ArrayList<RangePair>(0);

		for (int i=0; i<data.size(); i++) {
			rangePairs.add(new RangePair(data.get(i)));
		}

		int total = 0;
		for (int i=0; i<rangePairs.size(); i++) {
			RangePair rp = rangePairs.get(i);

			// Left overlaps right at the lower end.
			if (rp.leftLower <= rp.rightLower && rp.leftUpper >= rp.rightLower) {
					total++;
			}
			// Left overlaps right at the upper end
			else if (rp.leftLower <= rp.rightUpper && rp.leftUpper >= rp.rightUpper) {
					total++;
			}
			// Right overlaps left at the lower end
			else if (rp.rightLower <= rp.leftLower && rp.rightUpper >= rp.leftLower) {
                    total++;
            }
			// Right overlaps left at the upper end
			else if (rp.rightLower <= rp.leftUpper && rp.rightUpper >= rp.leftUpper) {
                    total++;
            }
		}
        System.out.println("total="+ total);
    }

}
