import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Math;

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

		lastGeodeTotal = geodeTotal;

		geodeTotal += numGeodeRobots;

		// Rate at which geode collection is taking place. Geodes per minute.
		int geodeGradient = geodeTotal - lastGeodeTotal;

		// Estimate/project what the final number of geodes collected will be.
		projectedGeodeTotal = geodeTotal + (geodeGradient * (maxMinutes - minute));


	}

	float progress;

	private float robotWeighting = (float) 1.2;

	private float requestedRobotWeighting = (float) 1.1; 

	// Raw materials
	public int oreTotal = 0;

	public int clayTotal = 0;

	public int obsidianTotal = 0;

	public int geodeTotal = 0;

	public int lastGeodeTotal = 0;

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
		logger.log("01 canBuildOreRobot oreTotal " + oreTotal + " oreRobotCost " + blueprint.oreRobotCost);
		if (oreTotal < blueprint.oreRobotCost) {
			return false;
		}
		return true;

	}

	public boolean canBuildClayRobot() {
		if (oreTotal < blueprint.clayRobotCost) {
			return false;
		}
		return true;

	}

	public boolean canBuildObsidianRobot() {
		if (oreTotal < blueprint.obsidianRobotOreCost || clayTotal < blueprint.obsidianRobotClayCost) {
			return false;
		}
		return true;
	}

	public boolean canBuildGeodeRobot() {
		if (oreTotal >= blueprint.geodeRobotOreCost && obsidianTotal >= blueprint.geodeRobotObsidianCost) {
			return true;
		}
		return false;
	}

	public boolean canBuildThisRobot(String robot) throws Exception {

		switch(robot) {
			case "ore":
				return this.canBuildOreRobot();
			case "clay":
				return this.canBuildClayRobot();
			case "obsidian":
				return this.canBuildObsidianRobot();
			case "geode":
				return this.canBuildGeodeRobot();
			case "none":
				return true;
		}
		throw new Exception("Unknown robot: " + robot);
	}

	public void logTotals() {
		logger.log("Minute: " + minute + " Num Robots: " + numOreRobots + " " + numClayRobots + " " + numObsidianRobots + " " + numGeodeRobots);
		logger.log("Minute: " + minute + " Totals: " + oreTotal + " " + clayTotal + " " + obsidianTotal + " " + geodeTotal);
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

	public void requestThisRobot(String robot) throws RuntimeException {
		switch(robot) {
			case "ore":
				requestOreRobot();
				break;
			case "clay":
				requestClayRobot();
				break;
			case "obsidian":
				requestObsidianRobot();
				break;
			case "geode":
				requestGeodeRobot();
				break;
		}
	}

	// Rules for skipping.

	// Simple rule to eliminate the most useles paths.
	boolean hasTimeToMakeFirstGeodeRobot() {
		logger.log("01 hasTimeToMakeFirstGeodeRobot");
		if (numClayRobots > 0 || numObsidianRobots > 0) {
			return true;
		}

		logger.log("02 hasTimeToMakeFirstGeodeRobot");
		int minutesRemaining = maxMinutes - minute;
		int minutesRequired = blueprint.obsidianRobotClayCost + blueprint.geodeRobotObsidianCost;

		if (minutesRemaining > minutesRequired) {
		logger.log("03 hasTimeToMakeFirstGeodeRobot. Has time.");
			return true;
		}

		logger.log("04 hasTimeToMakeFirstGeodeRobot. No time. " + minutesRemaining + " > " + minutesRequired);
		return false;
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

	// The projected number of Geode nodes is the best indicator.
	private int compareProjectedGeodeTotal(RobotStrategy s) {
		logger.log("compareProjectedGeodeTotal: " + this.projectedGeodeTotal + " " + s.projectedGeodeTotal);
		if (this.geodeTotal > s.geodeTotal) {
			return 1;
		}
		else if (this.projectedGeodeTotal  == s.projectedGeodeTotal) {
			return 0;
		}
		else {
			return -1;
		}
	}

	public String toString() {
		return "Minute: " + minute + " Num Robots: " + numOreRobots + " " + numClayRobots + " " + numObsidianRobots + " " + numGeodeRobots + " Num Robots Requested: " + numOreRobotsRequested + " " + numClayRobotsRequested + " " + numObsidianRobotsRequested + " " + numGeodeRobotsRequested + " Totals: " + oreTotal + " " + clayTotal + " " + obsidianTotal + " " + geodeTotal;
	}
}
