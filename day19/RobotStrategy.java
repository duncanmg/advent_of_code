import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class RobotStrategy implements Cloneable, Comparable<RobotStrategy>{

	public static void main(String[] args) {
		RobotStrategy obj = new RobotStrategy();
	}

	public RobotStrategy() {
	}

	Logger logger = new Logger(this, true);

	public Blueprint blueprint;

	public int minute = 0;

	public int maxMinutes = 24;

	public void nextMinute() {
		minute++;

		// Robots requested in the previous minute are now built and collecting.
		numClayRobots += numClayRobotsRequested;
		numClayRobotsRequested = 0;

		numOreRobots += numOreRobotsRequested;
		numOreRobotsRequested = 0;

		numObsidianRobots += numObsidianRobotsRequested;
		numObsidianRobotsRequested = 0;

		numGeodeRobots += numGeodeRobotsRequested;
		numGeodeRobotsRequested = 0;

	}

	public void collectResources() throws RuntimeException {
		// Update raw materials totals
		oreTotal += numOreRobots;

		clayTotal += numClayRobots;

		obsidianTotal += numObsidianRobots;

		geodeTotal += numGeodeRobots;

		// Estimate/project what the final number of geodes collected will be.
		projectedGeodeTotal = geodeTotal + (numGeodeRobots * (maxMinutes - minute));

	}

	// Raw materials
	public int oreTotal = 0;

	public int clayTotal = 0;

	public int obsidianTotal = 0;

	public int geodeTotal = 0;

	public int projectedGeodeTotal = 0;

	// Robots collecting raw material
	public int numOreRobots = 1;

	public int numClayRobots = 0;

	public int numObsidianRobots = 0;

	public int numGeodeRobots = 0;

	// Robots requested to be built
	public int numOreRobotsRequested = 0;

	public int numClayRobotsRequested = 0;

	public int numObsidianRobotsRequested = 0;

	public int numGeodeRobotsRequested = 0;

	// Which robots can be built from the raw materials available?
	public boolean canBuildOreRobot() {
		return oreTotal >= blueprint.oreRobotCost;
	}

	public boolean canBuildClayRobot() {
		return oreTotal >= blueprint.clayRobotCost;
	}

	public boolean canBuildObsidianRobot() {
		if (oreTotal >= blueprint.obsidianRobotOreCost && clayTotal >= blueprint.obsidianRobotClayCost) {
			return true;
		}
		return false;
	}

	public boolean canBuildGeodeRobot() {
		if (oreTotal >= blueprint.geodeRobotOreCost && obsidianTotal >= blueprint.geodeRobotObsidianCost) {
			return true;
		}
		return false;
	}

	public boolean canBuildTheseRobots(boolean ore, boolean clay, boolean obsidian, boolean geode) {
		// Operate on the clone so that we don't change the original inventory.
		RobotStrategy clone = (RobotStrategy) this.clone();
		if (ore) {
			if (clone.canBuildOreRobot()) {
				clone.requestOreRobot();
			} else {
				return false;
			}
		}
		if (clay) {
			if (clone.canBuildClayRobot()) {
				clone.requestClayRobot();
			} else {
				return false;
			}
		}
		if (obsidian) {
			if (clone.canBuildObsidianRobot()) {
				clone.requestObsidianRobot();
			} else {
				return false;
			}
		}
		if (geode) {
			logTotals();
			if (clone.canBuildGeodeRobot()) {
				clone.requestGeodeRobot();
			} else {
				return false;
			}
		}
		return true;
	}

	public void logTotals() {
		logger.log("Minute: " + minute + " Num Robots: " + numOreRobots + " " + numClayRobots + " " + numObsidianRobots + " " + numGeodeRobots);
		logger.log("Minute: " + minute + " Totals: " + oreTotal + " " + clayTotal + " " + obsidianTotal + " " + geodeTotal);
	}

	public boolean canBuildTheseRobots(boolean[] requested) {
		return canBuildTheseRobots(requested[0], requested[1], requested[2], requested[3]);
	}

	// Request robots to be built
	public void requestOreRobot() throws RuntimeException{
		if (!canBuildOreRobot()) {
			throw new RuntimeException("Cannot build ore robot");
		}
		numOreRobotsRequested++;
		oreTotal -= blueprint.oreRobotCost;
	}

	public void requestClayRobot() throws RuntimeException{
		if (!canBuildClayRobot()) {
			throw new RuntimeException("Cannot build clay robot");
		}
		numClayRobotsRequested++;
		oreTotal -= blueprint.clayRobotCost;
	}

	public void requestObsidianRobot() throws RuntimeException{
		if (!canBuildObsidianRobot()) {
			throw new RuntimeException("Cannot build obsidian robot");
		}
		numObsidianRobotsRequested++;
		oreTotal -= blueprint.obsidianRobotOreCost;
		clayTotal -= blueprint.obsidianRobotClayCost;
	}

	public void requestGeodeRobot() throws RuntimeException{
		if (!canBuildGeodeRobot()) {
			throw new RuntimeException("Cannot build geode robot");
		}
		numGeodeRobotsRequested++;
		oreTotal -= blueprint.geodeRobotOreCost;
		obsidianTotal -= blueprint.geodeRobotObsidianCost;
	}

	public void requestTheseRobots(boolean ore, boolean clay, boolean obsidian, boolean geode) throws RuntimeException {
		if (ore) {
			requestOreRobot();
		}
		if (clay) {
			requestClayRobot();
		}
		if (obsidian) {
			requestObsidianRobot();
		}
		if (geode) {
			requestGeodeRobot();
		}
	}

	public void requestTheseRobots(boolean[] requested) {
		requestTheseRobots(requested[0], requested[1], requested[2], requested[3]);
	}

	// Shallow clone
	@Override
		public Object clone() {
			try {
				return super.clone();
			}
			catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
		}

	// s1 == s2 : The method returns 0.
	// s1 > s2 : The method returns a positive value.
	// s1 < s2 : The method returns a negative value.
	@Override
		public int compareTo(RobotStrategy s) {
			logger.log("compareTo: " + this + " " + s);
			return compareProjectedGeodeTotal(s);
		}

	private int compareProjectedGeodeTotal(RobotStrategy s) {
		logger.log("compareProjectedGeodeTotal: " + this.projectedGeodeTotal + " " + s.projectedGeodeTotal);
		if (this.projectedGeodeTotal > s.projectedGeodeTotal) {
			return 1;
		}
		else if (this.projectedGeodeTotal  == s.projectedGeodeTotal) {
			return compareNumGeodeRobots(s);
		}
		else {
			return -1;
		}
	}

	private int compareNumGeodeRobots(RobotStrategy s) {
		logger.log("compareNumGeodeRobots: " + this.numGeodeRobots + " " + s.numGeodeRobots);
		if (this.numGeodeRobots > s.numGeodeRobots) {
			return 1;
		}
		else if (this.numGeodeRobots == s.numGeodeRobots) {
			return compareNumObsidianRobots(s);
		}
		else {
			return -1;
		}
	}

	private int compareNumObsidianRobots(RobotStrategy s) {
		logger.log("compareNumObsidianRobots: " + this.numObsidianRobots + " " + s.numObsidianRobots);
		if (this.numObsidianRobots > s.numObsidianRobots) {
			return 1;
		}
		else if (this.numObsidianRobots == s.numObsidianRobots) {
			return compareNumClayRobots(s);
		}
		else {
			return -1;
		}
	}

	private int compareNumClayRobots(RobotStrategy s) {
		logger.log("compareNumClayRobots: " + this.numClayRobots + " " + s.numClayRobots);
		if (this.numClayRobots > s.numClayRobots) {
			return 1;
		}
		else if (this.numClayRobots == s.numClayRobots) {
			return compareNumOreRobots(s);
		}
		else {
			return -1;
		}
	}

	private int compareNumOreRobots(RobotStrategy s) {
		logger.log("compareNumOreRobots: " + this.numOreRobots + " " + s.numOreRobots);
		if (this.numOreRobots > s.numOreRobots) {
			return 1;
		}
		else if (this.numOreRobots == s.numOreRobots) {
			return compareNumGeodeRobotsRequested(s);
		}
		else {
			return -1;
		}
	}

	private int compareNumGeodeRobotsRequested(RobotStrategy s) {
		logger.log("compareNumGeodeRobotsRequested: " + this.numGeodeRobotsRequested + " " + s.numGeodeRobotsRequested);
		if (this.numGeodeRobotsRequested > s.numGeodeRobotsRequested) {
			return 1;
		}
		else if (this.numGeodeRobotsRequested == s.numGeodeRobotsRequested) {
			return compareNumObsidianRobotsRequested(s);
		}
		else {
			return -1;
		}
	}

	private int compareNumObsidianRobotsRequested(RobotStrategy s) {
		logger.log("compareNumObsidianRobotsRequested: " + this.numObsidianRobotsRequested + " " + s.numObsidianRobotsRequested);
		if (this.numObsidianRobotsRequested > s.numObsidianRobotsRequested) {
			return 1;
		}
		else if (this.numObsidianRobotsRequested == s.numObsidianRobotsRequested) {
			return compareNumClayRobotsRequested(s);
		}
		else {
			return -1;
		}
	}

	private int compareNumClayRobotsRequested(RobotStrategy s) {
		logger.log("compareNumClayRobotsRequested: " + this.numClayRobotsRequested + " " + s.numClayRobotsRequested);
		if (this.numClayRobotsRequested > s.numClayRobotsRequested) {
			return 1;
		}
		else if (this.numClayRobotsRequested == s.numClayRobotsRequested) {
			return compareNumOreRobotsRequested(s);
		}
		else {
			return -1;
		}
	}

	private int compareNumOreRobotsRequested(RobotStrategy s) {
		if (this.numOreRobotsRequested > s.numOreRobotsRequested) {
			return 1;
		}
		else if (this.numOreRobotsRequested == s.numOreRobotsRequested) {
			return 0;
		}
		else {
			return -1;
		}
	}

	public String toString() {
		return "Minute: " + minute + " Num Robots: " + numOreRobots + " " + numClayRobots + " " + numObsidianRobots + " " + numGeodeRobots + " Num Robots Requested: " + numOreRobotsRequested + " " + numClayRobotsRequested + " " + numObsidianRobotsRequested + " " + numGeodeRobotsRequested + " Totals: " + oreTotal + " " + clayTotal + " " + obsidianTotal + " " + geodeTotal + " Projected: " + projectedGeodeTotal;
	}
}
