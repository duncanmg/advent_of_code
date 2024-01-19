//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;

public class TestAirPocketIdentifier {

	public static void main(String[] args) throws Exception {
		TestAirPocketIdentifier obj = new TestAirPocketIdentifier();
	}

	@Test public void TestConstructor() throws Exception {

		AirPocketIdentifier finder = new AirPocketIdentifier(new Context());

		assertEquals(finder.getClass().getName(), "AirPocketIdentifier");

	}

	@Test public void TestSimple() throws Exception {

		Context context = new Context();

		AirPocketIdentifier finder = new AirPocketIdentifier(context);
		assertEquals(finder.getClass().getName(), "AirPocketIdentifier");

		finder.find();
		assertEquals(context.potentialAirs.size(), 0);
		assertEquals(context.outsideMap.size(), 0);
	}

	@Test public void TestOneOutside() throws Exception {

		Context context = new Context();

		AirPocketIdentifier finder = new AirPocketIdentifier(context);
		assertEquals(finder.getClass().getName(), "AirPocketIdentifier");

		Air air = new Air();
		context.potentialAirs.put(air.id, air);

		assertEquals(context.potentialAirs.size(), 1);
		assertEquals(context.outsideMap.size(), 0);

		finder.find();
		assertEquals(context.potentialAirs.size(), 0);
		assertEquals(context.outsideMap.size(), 1);
	}

	@Test public void TestOpen() throws Exception {

		Context context = new Context();

		// Solid 3*3*3 cube of lava.
		this.addLavaCubes(context, 3);
		assertEquals(27, context.lavaMap.size());

		// Remove the centre cube and one outer cube with air to leave it open.
		this.replaceLavaWithAir(context, 1, 1, 1);
		this.replaceLavaWithAir(context, 1, 1, 2);

		assertEquals(25, context.lavaMap.size());
		assertEquals(2, context.potentialAirs.size());
		assertEquals(0, context.outsideMap.size());

		AirPocketIdentifier finder = new AirPocketIdentifier(context);
		assertEquals(finder.getClass().getName(), "AirPocketIdentifier");

		finder.find();

		assertEquals(25, context.lavaMap.size());
		assertEquals(0, context.potentialAirs.size());
		assertEquals(2, context.outsideMap.size());

	}

	@Test public void TestOpen2() throws Exception {

		Context context = new Context();

		// Solid 3*3*3 cube of lava.
		this.addLavaCubes(context, 3);
		assertEquals(27, context.lavaMap.size());

		// Remove the centre cube with lava, completely remove one element to leave it open.
		this.replaceLavaWithAir(context, 1, 1, 1);
		context.lavaMap.remove(new Lava(1, 1, 2).id);

		assertEquals(25, context.lavaMap.size());
		assertEquals(1, context.potentialAirs.size());
		assertEquals(0, context.outsideMap.size());

		AirPocketIdentifier finder = new AirPocketIdentifier(context);
		assertEquals(finder.getClass().getName(), "AirPocketIdentifier");

		finder.find();

		assertEquals(25, context.lavaMap.size());
		assertEquals(0, context.potentialAirs.size());
		assertEquals(2, context.outsideMap.size());

	}

	@Test public void TestClosed() throws Exception {

		Context context = new Context();

		// Solid 3*3*3 cube of lava.
		this.addLavaCubes(context, 3);
		assertEquals(27, context.lavaMap.size());

		// Replace the centre cube with Air to leave it closed.
		this.replaceLavaWithAir(context, 1, 1, 1);

		assertEquals(26, context.lavaMap.size());
		assertEquals(1, context.potentialAirs.size());
		assertEquals(0, context.outsideMap.size());

		AirPocketIdentifier finder = new AirPocketIdentifier(context);
		assertEquals(finder.getClass().getName(), "AirPocketIdentifier");

		finder.find();

		assertEquals(26, context.lavaMap.size());
		assertEquals(1, context.potentialAirs.size());
		assertEquals(0, context.outsideMap.size());

	}

	// The centre cube is removed but not replaced with anything.
	// This should be treated as a closed air pocket. It isn't.
	// Is that really correct operation?
	@Test public void TestClosed2() throws Exception {

		Context context = new Context();

		// Solid 3*3*3 cube of lava.
		this.addLavaCubes(context, 3);
		assertEquals(27, context.lavaMap.size());

		// Completely remove the centre cube to leave it closed.
		context.lavaMap.remove(new Lava(1, 1, 1).id);

		assertEquals(26, context.lavaMap.size());
		assertEquals(0, context.potentialAirs.size());
		assertEquals(0, context.outsideMap.size());

		AirPocketIdentifier finder = new AirPocketIdentifier(context);
		assertEquals(finder.getClass().getName(), "AirPocketIdentifier");

		finder.find();

		assertEquals(26, context.lavaMap.size());
		assertEquals(0, context.potentialAirs.size());
		assertEquals(0, context.outsideMap.size());

	}

	void addLava(Context context, int x, int y, int z) {
		Lava lava = new Lava(x, y, z);
		context.lavaMap.put(lava.id, lava);
	}	

	void addAir(Context context, int x, int y, int z) {
		Air air = new Air(x, y, z);
		context.potentialAirs.put(air.id, air);
	}	

	void addLavaCubes(Context context, int dimension) {

		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				for (int z = 0; z < dimension; z++) {
					this.addLava(context, x, y, z);
				}
			}
		}
		context.minX = 0;
		context.maxX = dimension - 1;
		context.minY = 0;
		context.maxY = dimension - 1;
		context.minZ = 0;
		context.maxZ = dimension - 1;
	}

	void replaceLavaWithAir(Context context, int x, int y, int z) {
		Air air = new Air(x, y, z);
		context.lavaMap.remove(air.id);
		context.potentialAirs.put(air.id, air);
	}
}
