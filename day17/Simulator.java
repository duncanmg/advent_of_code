import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Simulator {

	// dataFile is a containing a single line which represents a list of jets. eg ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
	// maxRocks is the maximum number of rocks to drop
	// show is a boolean which determines whether the chamber is shown after each rock is dropped

	public static void main(String[] args) {
			Simulator obj = new Simulator(args);
	}

	public Simulator(String[] args) {
		ArgumentProcessor argProcessor = new ArgumentProcessor(args);
		argMap = argProcessor.process();
		System.out.println("Simulator " + argMap.toString());
		if (argMap.containsKey("dataFile")) {
			String dataFile = argMap.get("dataFile");
			this.dataObj = new Data();
			this.data = this.dataObj.getData(dataFile);
			this.jetGenerator = new JetGenerator(this.argMap.get("dataFile"));
		}
	}

	HashMap<String, String> argMap;

	Logger logger = new Logger(this);

	Boolean debug = false;

	Boolean show = false;

	void log(String msg) {
		this.logger.debug = this.debug;
		this.chamber.logger.debug = this.debug;
		this.logger.log(msg);
	}

	Data dataObj = new Data();

	public ArrayList<String> data = new ArrayList<String>(0);

	JetGenerator jetGenerator;

	public Chamber chamber = new Chamber();

	RockGenerator rockGenerator = new RockGenerator(this.chamber);

	EndTester endTester = new EndTester(this.chamber);

	public int runSimulation(String dataFile) throws Exception {
		this.dataObj = new Data();
		this.data = this.dataObj.getData(dataFile);
		return this.runSimulation();
	}

	public int runSimulation() throws Exception {

		if (this.data.size()==0) {
			throw new Exception("No data and no jets!");
		}

		StateMachine stateMachine = new StateMachine();
		stateMachine.jetGenerator = this.jetGenerator;
		stateMachine.rockGenerator = this.rockGenerator;
		stateMachine.chamber = this.chamber;
		stateMachine.endTester = endTester;

		endTester.rockGenerator = this.rockGenerator;
		endTester.jetGenerator = this.jetGenerator;

		stateMachine.run();

		return this.chamber.getCurrentHeight();
	}

}
