import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class AirPocket {

	public static void main(String[] args) {
		AirPocket obj = new AirPocket();
	}

	public AirPocket() {
		this.x = 1;
		this.y = 1;
		this.z = 1;
		this.id = this.setId();
	}

	public AirPocket(Boulder boulder) {
		this.x = boulder.x;
		this.y = boulder.y;
		this.z = boulder.z;
		this.id = this.setId();}

	public AirPocket(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = this.setId();
	}

	final public int x;

	final public int y;

	final public int z;

	final String id;

	String setId() {
		return String.format("I%06d%06d%06d", x, y, z);
	}

	public int sidesCovered = 0;

	public boolean isCovered() {
		return sidesCovered == 6;
	}
}
