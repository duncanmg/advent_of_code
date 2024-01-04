import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class RangePair {

	public Integer leftLower;
	public Integer leftUpper;
	public Integer rightLower;
	public Integer rightUpper;

    public static void main(String[] args) {

        RangePair obj = new RangePair("82-82,8-83");

    }

    public RangePair(String line) {
		Integer delimiter = line.indexOf(",");

        String left = line.substring(0,delimiter);
		String right = line.substring(delimiter+1, line.length());

		ArrayList<Integer> l = split(left);
        leftLower = l.get(0);
		leftUpper = l.get(1);

		ArrayList<Integer> r = split(right);
		rightLower = r.get(0);
		rightUpper = r.get(1);
    }

	private ArrayList<Integer> split(String line) {

	    Integer delimiter = line.indexOf("-");
		String lower = line.substring(0,delimiter);
		String upper = line.substring(delimiter+1, line.length());

		ArrayList<Integer> out =  new ArrayList<Integer>(0);
        out.add(Integer.parseInt(lower));
		out.add(Integer.parseInt(upper));

		return out;
	}

}
