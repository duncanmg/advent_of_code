import java.io.IOException;
import java.util.*;

//        1111
//        1111
//        1111
//        1111
//222233334444
//222233334444
//222233334444
//222233334444
//        55556666
//        55556666
//        55556666
//        55556666

class CubeOne extends Cube {

	public static void main(String[] args) {
		CubeOne obj = new CubeOne();
	}

	public CubeOne() {
	}

	public CubeOne(int sideLength, ArrayList<ArrayList<Character>> layout) {
		this.sideLength = sideLength;
		this.layout = layout;
	}

	// Add the six sides of CubeOne. The numbers passed to buildSide() give the position
	// of the side within the layout. Side one in the layout above is marked by 1s.
	// It is the third side in from the right on the top. Hence 0, 2.
	void addSides() {

		logger.log("addSides() 01");
		sides.add(buildSide(1, 0, 2));

		logger.log("addSides() 02");
		sides.add(buildSide(2, 1, 0));

		logger.log("addSides() 03");
		sides.add(buildSide(3, 1, 1));

		logger.log("addSides() 04");
		sides.add(buildSide(4, 1, 2));

		logger.log("addSides() 05");
		sides.add(buildSide(5, 2, 2));

		logger.log("addSides() 06");
		sides.add(buildSide(6, 2, 3));

		logger.log("addSides() 07");
	}

}
