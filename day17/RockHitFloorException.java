import java.util.*;

public class RockHitFloorException extends Exception {

    public static void main(String[] args) throws Exception {
		   RockHitFloorException obj = new RockHitFloorException("XXX");
     }

    public RockHitFloorException(String errorMessage) {
		super(errorMessage);
    }

	private static final long serialVersionUID = 10l;
}
