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

		sides.add(buildSide(6, 0, 2));

	}

	void nextSideRight() throws Exception {

		switch(getCurrentSideNo()) {
			case 1:
				// "6R". Mapping for 4*4 cube.
				changeCurrentSideTo(6, 'R', "TOPRIGHT->TOPLEFT:BOTTOMRIGHT->BOTTOMLEFT");
				break;
			case 2:
				// "5U"
				changeCurrentSideTo(5, 'U', "TOPRIGHT->BOTTOMLEFT:BOTTOMRIGHT->BOTTOMRIGHT");
				break;
			case 3:
				// "5R"
				changeCurrentSideTo(5, 'R', "TOPRIGHT->TOPLEFT:BOTTOMRIGHT->BOTTOMLEFT");
				break;
			case 4:
				// "6U"
				changeCurrentSideTo(6, 'U', "TOPRIGHT->BOTTOMLEFT:BOTTOMRIGHT->BOTTOMRIGHT");
				break;
			case 5:
				// "6L"
				changeCurrentSideTo(6, 'L', "TOPRIGHT->BOTTOMRIGHT:BOTTOMRIGHT->TOPRIGHT");
				break;
			case 6:
				// "5L"
				changeCurrentSideTo(5, 'L', "TOPRIGHT->BOTTOMRIGHT:BOTTOMRIGHT->TOPRIGHT");
				break;

		}
		// Error
	}	

	void nextSideDown() throws Exception {

		switch(getCurrentSideNo()) {
			case 1:
				// "4D"
				changeCurrentSideTo(4, 'D', "BOTTOMLEFT->TOPLEFT:BOTTOMRIGHT->TOPRIGHT");
				break;
			case 2:
				// "6D"
				changeCurrentSideTo(6, 'D', "BOTTOMLEFT->TOPLEFT:BOTTOMRIGHT->TOPRIGHT");
				break;
			case 3:
				// "2D"
				changeCurrentSideTo(2, 'D', "BOTTOMLEFT->TOPLEFT:BOTTOMRIGHT->TOPRIGHT");
				break;
			case 4:
				// "5D"
				changeCurrentSideTo(5, 'D', "BOTTOMLEFT->TOPLEFT:BOTTOMRIGHT->TOPRIGHT");
				break;
			case 5:
				// "2L"
				changeCurrentSideTo(2, 'L', "BOTTOMLEFT->TOPRIGHT:BOTTOMRIGHT->BOTTOMRIGHT");
				break;
			case 6:
				// "4L"
				changeCurrentSideTo(4, 'L', "BOTTOMLEFT->TOPRIGHT:BOTTOMRIGHT->BOTTOMRIGHT");
				break;

		}
		// Error
	}	

	void nextSideLeft() throws Exception {

		switch(getCurrentSideNo()) {
			case 1:
				// "3R"
				changeCurrentSideTo(3, 'R', "TOPLEFT->BOTTOMLEFT:BOTTOMLEFT->TOPLEFT");
				break;
			case 2:
				// "1D"
				changeCurrentSideTo(1, 'D', "TOPLEFT->TOPLEFT:BOTTOMLEFT->TOPRIGHT");
				break;
			case 3:
				// "1R"
				changeCurrentSideTo(1, 'R', "TOPLEFT->BOTTOMLEFT:BOTTOMLEFT->TOPLEFT");
				break;
			case 4:
				// "3D"
				changeCurrentSideTo(3, 'D', "TOPLEFT->TOPLEFT:BOTTOMLEFT->TOPRIGHT");
				break;
			case 5:
				// "3L"
				changeCurrentSideTo(3, 'L', "TOPLEFT->TOPRIGHT:BOTTOMLEFT->BOTTOMRIGHT");
				break;
			case 6:
				// "1L"
				changeCurrentSideTo(1, 'L', "TOPLEFT->TOPRIGHT:BOTTOMLEFT->BOTTOMRIGHT");
				break;

		}
		// Error
	}	

	void nextSideUp() throws Exception {

		switch(getCurrentSideNo()) {
			case 1:
				// "2R"
				changeCurrentSideTo(2, 'R', "TOPLEFT->TOPLEFT:TOPRIGHT->BOTTOMLEFT");
				break;
			case 2:
				// "3U"
				changeCurrentSideTo(3, 'U', "TOPLEFT->BOTTOMLEFT:TOPRIGHT->BOTTOMRIGHT");
				break;
			case 3:
				// "4R"
				changeCurrentSideTo(4, 'R', "TOPLEFT->TOPLEFT:TOPRIGHT->BOTTOMLEFT");
				break;
			case 4:
				// "1U"
				changeCurrentSideTo(1, 'U', "TOPLEFT->BOTTOMLEFT:TOPRIGHT->BOTTOMRIGHT");
				break;
			case 5:
				// "4U"
				changeCurrentSideTo(4, 'U', "TOPLEFT->BOTTOMLEFT:TOPRIGHT->BOTTOMRIGHT");
				break;
			case 6:
				// "2U"
				changeCurrentSideTo(2, 'U', "TOPLEFT->BOTTOMLEFT:TOPRIGHT->BOTTOMRIGHT");
				break;

		}
		// Error
	}	
}
