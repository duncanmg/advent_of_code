import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Pyroclastic {

	// dataFile is a containing a single line which represents a list of jets. eg ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
	// maxRocks is the maximum number of rocks to drop
	// show is a boolean which determines whether the chamber is shown after each rock is dropped

	public static void main(String[] args) {

		String dataFile = args[0];
		int maxRocks = Integer.parseInt(args[1]);
		boolean show = Boolean.parseBoolean(args[2]);

		if (args.length == 1) {
			Pyroclastic obj = new Pyroclastic(dataFile);
		}
		else if (args.length == 2) {
			Pyroclastic obj = new Pyroclastic(dataFile, maxRocks);
		}
		else if (args.length == 3) {
			System.out.println("SSSS " + dataFile + " " + args[1] + " " + args[2] + " " + show + " SSSS");
			Pyroclastic obj = new Pyroclastic(dataFile, maxRocks, show);
		}
		else {
			Pyroclastic obj = new Pyroclastic();
		}
	}

	public Pyroclastic() {
		System.out.println("Pyroclastic object created");
	}

	public Pyroclastic(String dataFile) {
		data = this.dataObj.getData(dataFile);
	}

	public Pyroclastic(String dataFile, int maxRocks) {
		data = this.dataObj.getData(dataFile);
		this.maxRocks = maxRocks;
	}

	public Pyroclastic(String dataFile, int maxRocks, boolean show) {
		System.out.println("SSSS");
		data = this.dataObj.getData(dataFile);
		this.maxRocks = maxRocks;
		if (show) {
			this.show = show;
		}
		try {
		System.out.println("SSSS");
			this.runSimulation();
		}
		catch (Exception e) {
			System.out.println("Failure: " + e);
		}
	}

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

	public int maxJets = -1;

	String[] jets = {};

	int jet = 0;

	void initializeJets() {
		if (this.jets.length==0) {
			this.jets = this.data.get(0).split("");
		}
	}

	public String nextJet() {
		String nj = this.jets[this.jet];
		int numJets = jets.length;
		this.log("nextJet() jet=" + this.jet);
		this.jet++;
		this.jet = this.jet % numJets == 0 ? 0 : this.jet;
		return nj;
	}

	int rockNo = 0;
	public Rock nextRock() throws Exception {
		Rock r;
		// System.out.println("01 nextRock " + this.rockNo);
		switch (this.rockNo) {
			case 0:
				// System.out.println("02 nextRock " + this.rockNo);
				r = new Horizontal(this.chamber);
				// System.out.println("03 nextRock " + this.rockNo);
				break;
			case 1:
				r = new Cross(this.chamber);
				break;
			case 2:
				r = new Step(this.chamber);
				break;
			case 3:
				r = new Vertical(this.chamber);
				break;
			case 4:
				r = new Square(this.chamber);
				break;
			default:
				throw new Exception("No Rock for " + rockNo);
		}
		this.rockNo++;
		if (this.rockNo>4) {
			this.rockNo=0;
		}
		this.log("Next rock is a " + r.getClass().getName());
		r.logger.debug = this.debug;
		return r;
	}

	public Chamber chamber = new Chamber();

	public int maxRocks = 0;

	public int runSimulation(String dataFile) throws Exception {
		this.dataObj = new Data();
		this.data = this.dataObj.getData(dataFile);
		return this.runSimulation();
	}

	public int runSimulation() throws Exception {

		System.out.println("03 SSSSSSSS " + this.logger.debug + " " + this.chamber.logger.debug);
		if (this.data.size()==0) {
			throw new Exception("No data and no jets!");
		}
		System.out.println("04 SSSSSSSS");
		this.initializeJets();

		int counter = this.maxRocks;
		int targetSize = 0;
		System.out.println("05 SSSSSSSS");
		while (counter > 0) {

		// System.out.println("06 SSSSSSSS");
			boolean blow = true;

			this.log("startY =" + this.chamber.startY + " getCurrentHeight() =" + chamber.getCurrentHeight());
			this.chamber.startY = chamber.getCurrentHeight() + 3;
			// this.chamber.startY = chamber.getCurrentHeight() + 4;
			this.log("------ New rock start to fall. counter=" + counter + " startY=" + this.chamber.startY);
			Rock currentRock = this.nextRock();
			targetSize = targetSize + currentRock.size();

			this.chamber.addRock(currentRock);

			int safetyCheck = 100;
			while (safetyCheck > 0) {

				safetyCheck--;
				if (safetyCheck <= 0) {
					throw new Exception("safetyCheck: " + safetyCheck);
				}

				Rock savedRock = new Rock(currentRock);
				this.log("runSimulation() currentRock.coords=" + currentRock.coords.toString());
				try{
					this.log("runSimulation blow=" + blow);
					if (blow == true) {
						String nj = this.nextJet();
						this.log("jet=" + nj);
						if (nj.equals("<")) {
							this.log("Left " + nj);
							currentRock.moveLeft();
							this.chamber.moveRock(currentRock);
						}
						else {
							this.log("Right " + nj);
							currentRock.moveRight();
							this.chamber.moveRock(currentRock);
						}
					}
					else {
						currentRock.moveDown();
						this.chamber.moveRock(currentRock);
					}
					blow = blow == true ? false : true;
				}
				catch (RockOutOfBoundsException e) {
					blow = blow == true ? false : true;
					continue;
				}
				catch (RockHitFloorException e) {
					break;
				}
				catch (RocksOverlapException e) {
					currentRock = savedRock;
					this.chamber.moveRock(currentRock);
					if (blow == true) {
						blow = false;
						continue;
					}
					else {
						break;
					}	
				}


			}

			counter--;
		}

		boolean just_show = false;

		// If show is true but debug is false, we need to turn debug back off after show has run.
		if (this.show) {
			if (!this.debug) {
				just_show = true;
			}
			this.chamber.logger.debug = true;
		}

		this.chamber.show();
		
		if (just_show) {
			this.chamber.logger.debug = false;
		}

		return this.chamber.getCurrentHeight();
	}

	// Accepts an ArrayList of rocks and a second smaller ArrayList of rocks. Loops through the first list
	// looking for occurrences of the second list. Stops when it finds numOccurrences occurrences of the second list.
	// The second ArrayList will often be first few elements of the first ArrayList
	public ArrayList<Integer> isSet(ArrayList<Rock> allRocks, ArrayList<Rock> targetSet, int numOccurrences) {
		System.out.println("Start isSet() size of allRocks=" + allRocks.size() + " size of targetSet=" + targetSet.size());
		int k = targetSet.size();
		int numOccurrencesFound = 0;
		int height = 0;

		ArrayList<Integer> returnValues = new ArrayList<Integer>();
		returnValues.add(0);
		returnValues.add(0);
		returnValues.add(0);

		for (int i=0; i<allRocks.size(); i++) {
			// System.out.println("isSet() Outer loop i=" + i);
			boolean same = true;
			int j;
			for (j=0; j<k; j++) {
				// System.out.println("isSet() Inner loop j=" + j);
				if (i+j>allRocks.size()-1) {
					break;
				}
				// System.out.println("isSet() Inner loop call hasSameXCoords");
				Rock tmp = allRocks.get(i+j);
				// System.out.println("xxxx");
				if (! allRocks.get(i+j).hasSameXCoords(targetSet.get(j))) {
					same=false;
					break;
				}
				// System.out.println("isSet() Inner loop bottom");
			}
			if (same == false) {
				continue;
			}
			if (j>=k) {
				numOccurrencesFound++;
				if (numOccurrencesFound >= numOccurrences) {
				    System.out.println(allRocks.get(i));
				    System.out.println(targetSet.get(0));
					returnValues.set(0, 1);
					returnValues.set(1, i);
					int maxY = allRocks.get(i).maxY();
					if (maxY > height) {
						height = maxY;
					}	
					returnValues.set(2, height);
				    System.out.println("FOUND!!!! found, i, height. "+returnValues);
				    return returnValues;
				}
			}
		}
		System.out.println("isSet returning false");
		return returnValues;
	}

}
