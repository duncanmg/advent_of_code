import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class EndTester {

	public static void main(String[] args) {
			EndTester obj = new EndTester();
	}

	public EndTester() {
	}

	public EndTester(Chamber chamber) {
		this.chamber = chamber;
	}

	Logger logger = new Logger(this, true);

	Chamber chamber;

	public Integer maxRocks;

	public Integer maxHeight;

	public Integer maxJets;

	public RockGenerator rockGenerator;

	public JetGenerator jetGenerator;

	public Boolean ended() throws Exception {
		this.logger.log("Testing end conditions. maxRocks=" + this.maxRocks + ", maxHeight=" + this.maxHeight + ".");
		this.logger.log("Rocks: " + this.chamber.rocks.size() + ", Height: " + this.chamber.getCurrentHeight() + ".");
		this.logger.log("Jets: " + this.jetGenerator.totalJetsGenerated + ", maxJets: " + this.maxJets + ".");
		if (this.maxRocks != null && this.chamber.rocks.size() >= this.maxRocks) {
			this.logger.log("End. maxRocks reached.");
			return true;
		}
		if (this.maxHeight != null && this.chamber.getCurrentHeight() >= this.maxHeight) {
			this.logger.log("End. maxHeight reached.");
			return true;
		}
		if (this.maxJets != null && this.jetGenerator.totalJetsGenerated >= this.maxJets) {
			this.logger.log("End. maxJets reached.");
			return true;
		}
		return false;
	}

}
