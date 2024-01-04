import java.io.IOException;
import java.util.*;

class Move {

    public static void main(String[] args) {
           // Move obj = new Move(Forest grid, Integer height, Integer row, Integer col);
     }

    public Move(String pType, Integer pAmount) {
		type = pType;
		amount = pAmount;
		expandedAmount = this.expandAmount(amount);
    }

    public Move(String line ) {
		// String[] chars = line.split("(?!^)");
		String[] chars = line.split(" ");
		type = chars[0];
		amount = Integer.parseInt(chars[1]);
		expandedAmount = this.expandAmount(amount);
    }

	private ArrayList<Integer> expandAmount(Integer amount) {
		ArrayList<Integer> a = new ArrayList<Integer>(0);
		for (int i=0; i<amount; i++) {
			a.add(1);
		}
		return a;
	}

	public final String type;

	public final Integer amount;

	public final ArrayList<Integer> expandedAmount;

	public String toString() {
		return this.type + " " + this.amount;
	}

}
