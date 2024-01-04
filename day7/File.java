import java.io.IOException;
import java.util.*;

class File {

    public static void main(String[] args) {
         File obj = new File("test", 1);
     }

	public Integer size;

	public String name = "";

    public File(String fileName, Integer fileSize) {
		name = fileName;
		size = fileSize;
    }

	public String toString() {
		return "name: " + name + " size: " + size;
	}

}
