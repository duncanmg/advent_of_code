import java.io.IOException;
import java.util.*;

//    11116666
//    11116666
//    11116666
//    11116666
//    4444
//    4444
//    4444
//    4444
//33335555
//33335555
//33335555
//33335555
//2222
//2222
//2222
//2222

class CubeTwo extends Cube{

	public static void main(String[] args) {
		CubeTwo obj = new CubeTwo();
	}

	public CubeTwo() {
	}

	public CubeTwo(int sideLength, ArrayList<ArrayList<Character>> layout) {
		this.sideLength = sideLength;
		this.layout = layout;
	}

	// Add the six sides of CubeTwo. The numbers passed to buildSide() give the position
	// of the side within the layout. Side one in the layout above is marked by 1s.
	// It is the second side in from the right on the top. It has an id of 1. Hence 1, 0, 1.
	void addSides() {

		sides.add(buildSide(1, 0, 1));

		sides.add(buildSide(2, 3, 0));

		sides.add(buildSide(3, 2, 0));

		sides.add(buildSide(4, 1, 1));

		sides.add(buildSide(5, 2, 1));

		sides.add(buildSide(6, 2, 0));

	}

}
