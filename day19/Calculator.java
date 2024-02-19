import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Math;

class Calculator {

	public static void main(String[] args) {
		Calculator obj = new Calculator(new RobotStrategy());
	}

	public Calculator(RobotStrategy rs) {
		robotStrategy = rs;
		blueprint = rs.blueprint;
	}

	Logger logger = new Logger(this, true);

	RobotStrategy robotStrategy;

	boolean continueSearching() {
		float minSearchTime = (float) robotStrategy.maxMinutes/2;
		float safetyMarginMinutes = (float) robotStrategy.maxMinutes/4;
		int minutesRemaining = robotStrategy.maxMinutes - robotStrategy.minute;

		if (robotStrategy.minute < minSearchTime) {
			return true;
		}

		float minutesToGeodeRobot = calcTimeToGeodeRobot("none");

		if (minutesToGeodeRobot < minutesRemaining + safetyMarginMinutes) {
			return true;
		}

		return false;
	}

	// Number of minutes from requesting a robot to in collecting its first material.
	final int timeToCreateRobot = 1;

	Blueprint blueprint;

	// Return an estimate of the time in minutes to the next geode robot.
	// Can experiment with adding robot types to see which reduces the time most.
	public float calcTimeToGeodeRobot(String robot) {

		float timeToOreRobot = timeToOreRobot();

		float timeToClayRobot = timeToClayRobot();

		float timeToObsidianRobot = timeToObsidianRobot(timeToClayRobot);

		float timeToGeodeRobot = timeToGeodeRobot(timeToObsidianRobot);

		return timeToGeodeRobot;
	}

	float timeToOreRobot() {
		float timeToOreRobot = timeToCreateRobot + ((blueprint.oreRobotCost - robotStrategy.oreTotal) / robotStrategy.numOreRobots);
		if (timeToOreRobot < 0) {
			timeToOreRobot = 0;
		}
		return timeToOreRobot;
	}

	float timeToClayRobot() {
		float timeToClayRobot = timeToCreateRobot + ((blueprint.clayRobotCost - robotStrategy.oreTotal) / robotStrategy.numOreRobots);
		if (timeToClayRobot < 0) {
			timeToClayRobot = 0;
		}
		return timeToClayRobot;
	}

	float timeToObsidianRobot(float timeToClayRobot) {
		float timeToObsidianRobot = (float) 0.0;
		float oreTime = (blueprint.obsidianRobotOreCost - robotStrategy.oreTotal) / robotStrategy.numOreRobots;
		logger.log("oreTime " + oreTime + " blueprint.obsidianRobotOreCost " + blueprint.obsidianRobotOreCost + " oreTotal " + robotStrategy.oreTotal);
		if (robotStrategy.numClayRobots == 0) {
			float clayTime = timeToClayRobot + blueprint.obsidianRobotClayCost + timeToCreateRobot;
			timeToObsidianRobot = Math.max(oreTime, clayTime);
			logger.log("timeToObsidianRobot " + timeToObsidianRobot + " oreTime " + oreTime + " clayTime " + clayTime);
		}
		else {
			float clayTime = timeToCreateRobot + ((blueprint.obsidianRobotClayCost - robotStrategy.clayTotal) / robotStrategy.numClayRobots);
			timeToObsidianRobot = Math.max(oreTime, clayTime);
			logger.log("timeToObsidianRobot " + timeToObsidianRobot + " oreTime " + oreTime + " clayTime " + clayTime);
		}

		if (timeToObsidianRobot < 0) {
			timeToObsidianRobot = 0;
		}

		return timeToObsidianRobot;
	}

	float timeToGeodeRobot(float timeToObsidianRobot) {
		float timeToGeodeRobot = (float) 0.0;
		float oreTime = (blueprint.geodeRobotOreCost - robotStrategy.oreTotal) / robotStrategy.numOreRobots;
		if (robotStrategy.numObsidianRobots == 0) {
			float obsidianTime = timeToObsidianRobot + blueprint.geodeRobotObsidianCost + timeToCreateRobot;
			timeToGeodeRobot = Math.max(oreTime, obsidianTime);
		}
		else {
			float obsidianTime = timeToCreateRobot + ((blueprint.geodeRobotObsidianCost - robotStrategy.obsidianTotal) / robotStrategy.numObsidianRobots);
			timeToGeodeRobot =  Math.max(oreTime, obsidianTime);
		}

		logger.log("calcTimeToGeodeRobot timeToObsidianRobot " + timeToObsidianRobot + " timeToGeodeRobot " + timeToGeodeRobot);
		if (timeToGeodeRobot < 0) {
			timeToGeodeRobot = 0;
		}
		return timeToGeodeRobot;
	}
}
