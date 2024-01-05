import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Simulator {

	// dataFile is a containing a single line which represents a list of jets. eg ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
	// maxRocks is the maximum number of rocks to drop
	// show is a boolean which determines whether the chamber is shown after the run has finished.
	// debug is a boolean which determines whether log messages are shown

	// If "run" is present, it causes the simulation to be executed from the command line. It must have a value.
	// eg ./run.sh Simulator --dataFile=data.txt --maxRocks=2022 --run=1

	// eg ./run.sh Simulator --dataFile=data.txt --maxRocks=2022 --run=1 --show=chamber_2022rocks.txt

	public static void main(String[] args) {
		Simulator obj = new Simulator(args);
		try {
			if (obj.argMap.containsKey("run")) {
				System.out.println("Running simulation");
				System.out.println(obj.runSimulation());
				if (obj.argMap.containsKey("show")) {
					System.out.println("Showing chamber");
					obj.chamber.showRaw(obj.argMap.get("show"));
				}
			}
		} catch (Exception e) {
			System.out.println("Error running simulation");
			System.out.println(e.getMessage());
		}
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
		if (argMap.containsKey("maxRocks")) {
			String maxRocks = argMap.get("maxRocks");
			this.endTester.maxRocks = Integer.parseInt(maxRocks);
		}
		if (argMap.containsKey("maxHeight")) {
			String maxHeight = argMap.get("maxHeight");
			this.endTester.maxHeight = Integer.parseInt(maxHeight);
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
