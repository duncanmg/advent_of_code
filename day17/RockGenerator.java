import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class RockGenerator {

	public static void main(String[] args) {
			RockGenerator obj = new RockGenerator();
	}

	public RockGenerator() {
		this.logger.log("chamber NOT set by constructor");
	}

	public RockGenerator(Chamber chamber) {
		this.chamber = chamber;
		this.logger.log("chamber set by constructor");
	}

	Chamber chamber;

	Logger logger = new Logger(this, true);

	int currentRockNo = 0;

	long totalRocksGenerated = 0;

	public Rock next() throws Exception {
		Rock r;
		this.logger.log("01 nextRock " + this.currentRockNo);
		this.chamber.startY = this.chamber.getCurrentHeight() + 3;
		switch (this.currentRockNo) {
			case 0:
				r = new Horizontal(this.chamber);
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
				throw new Exception("No Rock for " + currentRockNo);
		}
		this.currentRockNo++;
		this.totalRocksGenerated++;
		if (this.currentRockNo>4) {
			this.currentRockNo=0;
		}
		return r;
	}

}
