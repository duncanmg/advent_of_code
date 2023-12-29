import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class JetGenerator {

	// dataFile is a containing a single line which represents a list of jets. eg ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
	//
	// java JetGenerator.class dataFile=dataFile.txt
	//
	public static void main(String[] args) {
			JetGenerator obj = new JetGenerator(args);
	}

	// JetGenerator jetGenerator = new JetGenerator(new String[] {"dataFile=dataFile.txt"});
	public JetGenerator(String[] args) {
		ArgumentProcessor argProcessor = new ArgumentProcessor(args);
		argMap = argProcessor.process();
		if (argMap.containsKey("dataFile")) {
			String dataFile = argMap.get("dataFile");
			this.dataObj = new Data();
			this.data = this.dataObj.getData(dataFile);
		}
		logger.debug = true;
	}

	// JetGenerator jetGenerator = new JetGenerator(new String[] {"dataFile=dataFile.txt"});
	public JetGenerator(String dataFile) {
		this.dataObj = new Data();
		this.data = this.dataObj.getData(dataFile);
		logger.debug = true;
	}

	Logger logger = new Logger(this);

	HashMap<String, String> argMap;

	Data dataObj = new Data();

	public ArrayList<String> data = new ArrayList<String>(0);

	String[] jets = {};

	int jet = 0;

	void initializeJets() {
		if (this.jets.length==0) {
			this.jets = this.data.get(0).split("");
		}
	}

	public String next() {
		this.initializeJets();
		this.logger.log("jet=" + this.jet + " jets.length=" + this.jets.length);
		String nj = this.jets[this.jet];
		int numJets = jets.length;
		this.jet++;
		this.jet = this.jet % numJets == 0 ? 0 : this.jet;
		return nj;
	}

}
