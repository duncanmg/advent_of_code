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
		int numRocks = this.chamber.rocks.size();

		char fallingRockLabel = ' ';
		if (numRocks > 0) {
			fallingRockLabel = this.chamber.getFallingRock().label;
		}

		int currentHeight = this.chamber.getCurrentHeight();
		this.logger.log("Testing end conditions. fallingRock=" + fallingRockLabel + " maxRocks=" + this.maxRocks + ", maxHeight=" + this.maxHeight + ".");
		this.logger.log("Rocks: " + numRocks + ", Height: " + currentHeight + ".");
		this.logger.log("Jets: " + this.jetGenerator.totalJetsGenerated + ", maxJets: " + this.maxJets + ".");

		if (this.maxRocks != null && numRocks >= this.maxRocks) {
			if (numRocks != this.maxRocks) {
				// throw new Exception("maxRocks exceeded. maxRocks=" + this.maxRocks + ", rocks=" + numRocks + ".");
			}
			this.logger.log("End. maxRocks reached.");
			return true;
		}
		if (this.maxHeight != null && currentHeight >= this.maxHeight) {
			if (currentHeight != this.maxHeight) {
				// throw new Exception("maxHeight exceeded. maxHeight=" + this.maxHeight + ", currentHeight=" + currentHeight + ".");
			}
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
