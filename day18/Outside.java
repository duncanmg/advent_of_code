import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Outside extends Cube {

	public static void main(String[] args) {
		Outside obj = new Outside();
	}

	public Outside() {
		super(1, 1, 1);
	}

	public Outside(Cube cube) {
		super(cube);
	}

}
