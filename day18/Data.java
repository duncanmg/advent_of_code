import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Data {

	public static void main(String[] args) {
		Data obj = new Data();
	}

	// Data dataObj = new Data();
	// ArrayList<String> data = dataObj.getData("data.txt");
	public Data() {
		// System.out.println("Data object created");
	}

	// Data dataObj = new Data("data.txt");
	// ArrayList<String> data = dataObj.data;
	public Data(String dataFile) {
		data = getData(dataFile);
	}

	public ArrayList<String> data = new ArrayList<String>(0);

	public ArrayList<String> getData(String dataFile) {

		ArrayList<String> out= new ArrayList<String>(1);
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(dataFile));
			String line = reader.readLine();

			while (line != null) {
				out.add(line);
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}

}
