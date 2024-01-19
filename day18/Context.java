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

	HashMap<String, Outside> outsideMap = new HashMap<String, Outside>();

	boolean isLava(Cube cube) {
		return lavaMap.containsKey(cube.id);
	}

	boolean isPotentialAir(Cube cube) {
		return potentialAirs.containsKey(cube.id);
	}

	// Cube is not lava and not part of the sealed air pocket. It is either outside
	// the droplet or open to the outside.
	boolean isOutside(Cube cube) {
		return outsideMap.containsKey(cube.id);
	}

	boolean isOutOfRange(Cube cube) {
		return cube.x < minX || cube.x > maxX || cube.y < minY || cube.y > maxY || cube.z < minZ || cube.z > maxZ;
	}
}
