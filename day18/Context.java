import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Context {

	public static void main(String[] args) {
		Context obj = new Context();
	}

	public Context() {
	}

	public int minX = 0;

	public int maxX = 0;

	public int minY = 0;

	public int maxY = 0;

	public int minZ = 0;

	public int maxZ = 0;

	HashMap<String, Lava> lavaMap = new HashMap<String, Lava>();

	HashMap<String, Air> potentialAirs = new HashMap<String, Air>();

}
