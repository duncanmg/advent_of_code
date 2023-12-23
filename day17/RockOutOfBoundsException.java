import java.util.*;

public class RockOutOfBoundsException extends Exception {

    public static void main(String[] args) throws Exception {
		   RockOutOfBoundsException obj = new RockOutOfBoundsException("XXX");
     }

	// Accepts "34,40"
    public RockOutOfBoundsException(String errorMessage) {
		super(errorMessage);
    }

	private static final long serialVersionUID = 10l;
}
