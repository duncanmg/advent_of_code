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

	void nextSideRight() throws Exception {
		logger.log("nextSideRight()");
		switch(getCurrentSideNo()) {
			case 1:
				// "6L". Mapping for 4*4 cube.
				changeCurrentSideTo(6, 'L', "TOPRIGHT->BOTTOMRIGHT:BOTTOMRIGHT->TOPRIGHT");
				break;
			case 2:
				// "3R"
				changeCurrentSideTo(3, 'R', "TOPRIGHT->TOPLEFT:BOTTOMRIGHT->BOTTOMLEFT");
				break;
			case 3:
				// "4R"
				changeCurrentSideTo(4, 'R', "TOPRIGHT->TOPLEFT:BOTTOMRIGHT->BOTTOMLEFT");
				break;
			case 4:
				// "6D"
				changeCurrentSideTo(6, 'D', "TOPRIGHT->TOPRIGHT:BOTTOMRIGHT->TOPLEFT");
				break;
			case 5:
				// "6R"
				changeCurrentSideTo(6, 'R', "TOPRIGHT->TOPLEFT:BOTTOMRIGHT->BOTTOMLEFT");
				break;
			case 6:
				// "1L"
				changeCurrentSideTo(1, 'L', "TOPRIGHT->BOTTOMRIGHT:BOTTOMRIGHT->TOPRIGHT");
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
				// "5U"
				changeCurrentSideTo(5, 'U', "BOTTOMLEFT->BOTTOMRIGHT:BOTTOMRIGHT->BOTTOMLEFT");
				break;
			case 3:
				// "5R"
				changeCurrentSideTo(5, 'R', "BOTTOMLEFT->BOTTOMRIGHT:BOTTOMRIGHT->TOPLEFT");
				break;
			case 4:
				// "5D"
				changeCurrentSideTo(5, 'D', "BOTTOMLEFT->TOPLEFT:BOTTOMRIGHT->TOPRIGHT");
				break;
			case 5:
				// "2U"
				changeCurrentSideTo(2, 'U', "BOTTOMLEFT->BOTTOMRIGHT:BOTTOMRIGHT->BOTTOMLEFT");
				break;
			case 6:
				// "2R"
				changeCurrentSideTo(2, 'R', "BOTTOMLEFT->BOTTOMLEFT:BOTTOMRIGHT->TOPLEFT");
				break;

		}
		// Error
	}	

	void nextSideLeft() throws Exception {
		switch(getCurrentSideNo()) {
			case 1:
				// "3D"
				changeCurrentSideTo(3, 'D', "TOPLEFT->TOPLEFT:BOTTOMLEFT->TOPRIGHT");
				break;
			case 2:
				// "6U"
				changeCurrentSideTo(6, 'U', "TOPLEFT->BOTTOMRIGHT:BOTTOMLEFT->BOTTOMLEFT");
				break;
			case 3:
				// "2L"
				changeCurrentSideTo(2, 'L', "TOPLEFT->TOPRIGHT:BOTTOMLEFT->BOTTOMRIGHT");
				break;
			case 4:
				// "3L"
				changeCurrentSideTo(3, 'L', "TOPLEFT->TOPRIGHT:BOTTOMLEFT->BOTTOMRIGHT");
				break;
			case 5:
				// "3U"
				changeCurrentSideTo(3, 'U', "TOPLEFT->BOTTOMRIGHT:BOTTOMLEFT->BOTTOMLEFT");
				break;
			case 6:
				// "5L"
				changeCurrentSideTo(5, 'L', "TOPLEFT->TOPRIGHT:BOTTOMLEFT->BOTTOMRIGHT");
				break;

		}
		// Error
	}	

	void nextSideUp() throws Exception {
		switch(getCurrentSideNo()) {
			case 1:
				// "2D"
				changeCurrentSideTo(2, 'D', "TOPLEFT->TOPRIGHT:TOPRIGHT->TOPLEFT");
				break;
			case 2:
				// "1D"
				changeCurrentSideTo(1, 'D', "TOPLEFT->TOPRIGHT:TOPRIGHT->TOPLEFT");
				break;
			case 3:
				// "1R"
				changeCurrentSideTo(1, 'R', "TOPLEFT->TOPLEFT:TOPRIGHT->BOTTOMLEFT");
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
				// "4L"
				changeCurrentSideTo(4, 'L', "TOPLEFT->BOTTOMRIGHT:TOPRIGHT->TOPRIGHT");
				break;

		}
		// Error
	}	
}
