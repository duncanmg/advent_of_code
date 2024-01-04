import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Data {

	public ArrayList<String> data = new ArrayList<String>(0);

    public Data() {
		data = getData("data.txt");
    }

    public Data(String dataFile) {
		data = getData(dataFile);
    }

	ArrayList<String> getData(String dataFile) {

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
