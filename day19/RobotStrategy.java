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
			if (this.projectedGeodeTotal > s.projectedGeodeTotal) {
				return 1;
			}
			else if (this.projectedGeodeTotal  == s.projectedGeodeTotal) {
				if (this.numObsidianRobots > s.numObsidianRobots) {
					return 1;
				}
				else if (this.numObsidianRobots == s.numObsidianRobots) {
					if (this.numClayRobots > s.numClayRobots) {
						return 1;
					}
					else if (this.numClayRobots == s.numClayRobots) {
						if (this.numOreRobots > s.numOreRobots) {
							return 1;
						}
						else if (this.numOreRobots == s.numOreRobots) {
							return 0;
						}
						else {
							return -1;
						}
					}
					else {
						return -1;
					}
				}
				else {
					return -1;
				}
			}
			else {
				return -1;
			}	
		}
}
