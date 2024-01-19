import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Air extends Cube {

	public static void main(String[] args) {
		Air obj = new Air();
	}

	public Air() {
		super(1, 1, 1);
	}

	public Air(Cube cube) {
		super(cube);
	}

	public Air(int x, int y, int z) {
		super(x, y, z);
	}

}
