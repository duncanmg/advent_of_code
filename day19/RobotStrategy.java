import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class RobotStrategy {

	public static void main(String[] args) {
		RobotStrategy obj = new RobotStrategy();
	}

	public RobotStrategy() {
	}

	public Blueprint blueprint;

	public int minute = 0;

	public void nextMinute() {
		minute++;


		oreTotal += numOreRobots;

		clayTotal += numClayRobots;

		obsidianTotal += numObsidianRobots;

		geodeTotal += numGeodeRobots;

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

}
