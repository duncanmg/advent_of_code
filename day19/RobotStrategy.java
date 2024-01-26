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

		oreTotal += numOreRobots;

		clayTotal += numClayRobots;

		obsidianTotal += numObsidianRobots;

		geodeTotal += numGeodeRobots;

		// Estimate/project what the final number of geodes collected will be.
		projectedGeodeTotal = geodeTotal + (numGeodeRobots * (maxMinutes - minute));

		// Requested robots don't contribute yet.
		numClayRobots += numClayRobotsRequested;
		numClayRobotsRequested = 0;

		numOreRobots += numOreRobotsRequested;
		numOreRobotsRequested = 0;

		numObsidianRobots += numObsidianRobotsRequested;
		numObsidianRobotsRequested = 0;

		numGeodeRobots += numGeodeRobotsRequested;
		numGeodeRobotsRequested = 0;
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
		int numToBuild = 0;
		if (ore) {
			if (clone.canBuildOreRobot()) {
				numToBuild++;
				clone.requestOreRobot();
			} else {
				return false;
			}
		}
		if (clay) {
			if (clone.canBuildClayRobot()) {
				numToBuild++;
				clone.requestClayRobot();
			} else {
				return false;
			}
		}
		if (obsidian) {
			if (clone.canBuildObsidianRobot()) {
				numToBuild++;
				clone.requestObsidianRobot();
			} else {
				return false;
			}
		}
		if (geode) {
			logTotals();
			if (clone.canBuildGeodeRobot()) {
				numToBuild++;
				clone.requestGeodeRobot();
			} else {
				return false;
			}
		}
		return numToBuild > 0 ? true : false;
	}

	public void logTotals() {
		logger.log("Minute: " + minute + " Num Robots: " + numOreRobots + " " + numClayRobots + " " + numObsidianRobots + " " + numGeodeRobots);
		logger.log("Minute: " + minute + " Totals: " + oreTotal + " " + clayTotal + " " + obsidianTotal + " " + geodeTotal);
	}

	public boolean canBuildTheseRobots(boolean[] requested) {
		return canBuildTheseRobots(requested[0], requested[1], requested[2], requested[3]);
	}

	// Request robots to be built
	public void requestOreRobot() {
		numOreRobotsRequested++;
		oreTotal -= blueprint.oreRobotCost;
	}

	public void requestClayRobot() {
		numClayRobotsRequested++;
		oreTotal -= blueprint.clayRobotCost;
	}

	public void requestObsidianRobot() {
		numObsidianRobotsRequested++;
		oreTotal -= blueprint.obsidianRobotOreCost;
		clayTotal -= blueprint.obsidianRobotClayCost;
	}

	public void requestGeodeRobot() {
		numGeodeRobotsRequested++;
		oreTotal -= blueprint.geodeRobotOreCost;
		obsidianTotal -= blueprint.geodeRobotObsidianCost;
	}

	public void requestTheseRobots(boolean ore, boolean clay, boolean obsidian, boolean geode) {
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

	public String sortCode() {
		String formatString = "%06d%06d%06d%06d";
		return String.format(formatString, projectedGeodeTotal, numObsidianRobots, numClayRobots, numOreRobots);
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
			return this.sortCode().compareTo(s.sortCode());
		}
}
