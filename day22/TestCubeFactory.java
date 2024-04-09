import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.Before;

// test_layout.txt
//        ...#
//        .#..
//        #...
//        ....
//...#.......#
//........#...
//..#....#....
//..........#.
//        ...#....
//        .....#..
//        .#......
//        ......#.

// test_layount2.txt
//    ...#....
//    .#...#..
//    #.......
//    ......#.
//    ...#
//    #...
//    ....
//    ..#.
//.......#
//........
//...#.#..
//........
//..#.
//..#.
//..#.
//..#.

public class TestCubeFactory {

	public static void main(String[] args) throws Exception {
		TestCubeFactory obj = new TestCubeFactory();
	}

	Logger logger = new Logger(this, true);

	CubeFactory cubeFactory;
	ArrayList<String> cubeOneData;
	ArrayList<String> cubeTwoData;

	CubeOne cubeOne;

	CubeTwo cubeTwo;

	@Before
		public void setUp() throws Exception {
			logger.log("Start setUp");

			Data dataObj = new Data();
			cubeOneData = dataObj.getData("test_layout.txt");

			dataObj = new Data();
			cubeTwoData = dataObj.getData("test_layout2.txt");

			cubeFactory = new CubeFactory();
		}



	@Test public void TestConstructor() {
		assertEquals("CubeFactory", cubeFactory.getClass().getName());
	}

	@Test public void TestParseData01() {
		assertEquals(0, cubeFactory.layout.size());
		cubeFactory.parseData(cubeOneData);
		assertEquals(12, cubeFactory.layout.size());
	}

	@Test public void TestParseData02() {
		assertEquals(0, cubeFactory.layout.size());
		cubeFactory.parseData(cubeTwoData);
		assertEquals(16, cubeFactory.layout.size());
	}

	@Test public void TestCalcSideLength01() {
		cubeFactory.parseData(cubeOneData);
		assertEquals(-1, cubeFactory.sideLength);
		cubeFactory.calcSideLength();
		assertEquals(4, cubeFactory.sideLength);
	}

	@Test public void TestCalcSideLength02() {
		cubeFactory.parseData(cubeTwoData);
		assertEquals(-1, cubeFactory.sideLength);
		cubeFactory.calcSideLength();
		assertEquals(4, cubeFactory.sideLength);
	}

	@Test public void TestBuildCube01() throws Exception {
		Cube cube = cubeFactory.buildCube(cubeOneData);
		assertEquals("CubeOne", cube.getClass().getName());
	}

	@Test public void TestBuildCube02() throws Exception {
		logger.log("Start TestBuildCube02");
		Cube cube = cubeFactory.buildCube(cubeTwoData);
		assertEquals("CubeTwo", cube.getClass().getName());
	}

}
