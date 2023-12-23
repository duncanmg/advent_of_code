import java.util.*;

public class RocksOverlapException extends Exception {

    public static void main(String[] args) throws Exception {
		   RocksOverlapException obj = new RocksOverlapException("XXX");
     }

	// Accepts "34,40"
    public RocksOverlapException(String errorMessage) {
		super(errorMessage);
    }

	private static final long serialVersionUID = 10l;
}
