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
		robotStrategy = (RobotStrategy) rs.clone();

		blueprint = robotStrategy.blueprint;

		HashMap<String, Robot> robots = robotStrategy.robots;
		oreRobot = (OreRobot) robots.get("ore");
		clayRobot = (ClayRobot) robots.get("clay");
		obsidianRobot = (ObsidianRobot) robots.get("obsidian");
		geodeRobot = (GeodeRobot) robots.get("geode");
	}

	Logger logger = new Logger(this, true);

	RobotStrategy robotStrategy;

	OreRobot oreRobot;

	ClayRobot clayRobot;

	ObsidianRobot obsidianRobot;

	GeodeRobot geodeRobot;

	// This attempts to determine whether there is time to create another geode
	// robot and returns false if there isn't enough time.
	//
	// The estimate of time required will be more accurate as time progresses, so
	// this will return true unless the current minute is minSearchTime or greater.
	//
	// It adds a safety margin to the amount of time remaining.
	//
	boolean continueSearching() {
		float minSearchTime = (float) robotStrategy.maxMinutes/2;
		float safetyMarginMinutes = (float) robotStrategy.maxMinutes/4;
		int minutesRemaining = robotStrategy.maxMinutes - robotStrategy.minute;

		if (robotStrategy.minute < minSearchTime) {
			return true;
		}

		float minutesToGeodeRobot = calcTimeToGeodeRobot();

		if (minutesToGeodeRobot < minutesRemaining + safetyMarginMinutes) {
			return true;
		}

		return false;
	}

	// Number of minutes from requesting a robot to it collecting its first material.
	final int timeToCreateRobot = 1;

	Blueprint blueprint;

	// Beware! This mutates the object.
	public float addRobotAndRecalc(String robot) {
		Robot candidate = robotStrategy.robots.get(robot);
		if (candidate.canBuildRobot(robotStrategy.robots)) {
			candidate.requestRobot(robotStrategy.robots);
		}
		robotStrategy.nextMinute();
		return calcTimeToGeodeRobot();
	}

	// Return an estimate of the time in minutes to the next geode robot.
	// Can experiment with adding robot types to see which reduces the time most.
	public float calcTimeToGeodeRobot() {

		float timeToOreRobot = timeToOreRobot();

		float timeToClayRobot = timeToClayRobot();

		float timeToObsidianRobot = timeToObsidianRobot(timeToClayRobot);

		float timeToGeodeRobot = timeToGeodeRobot(timeToObsidianRobot);

		return timeToGeodeRobot;
	}

	float timeToOreRobot() {
		float timeToOreRobot = timeToCreateRobot + ((blueprint.oreRobotCost - oreRobot.total) / oreRobot.numRobots);
		if (timeToOreRobot < 0) {
			timeToOreRobot = 0;
		}
		return timeToOreRobot;
	}

	float timeToClayRobot() {
		float timeToClayRobot = timeToCreateRobot + ((blueprint.clayRobotCost - oreRobot.total) / oreRobot.numRobots);
		if (timeToClayRobot < 0) {
			timeToClayRobot = 0;
		}
		return timeToClayRobot;
	}

	float timeToObsidianRobot(float timeToClayRobot) {
		float timeToObsidianRobot = (float) 0.0;
		float oreTime = (blueprint.obsidianRobotOreCost - oreRobot.total) / oreRobot.numRobots;
		logger.log("oreTime " + oreTime + " blueprint.obsidianRobotOreCost " + blueprint.obsidianRobotOreCost + " oreTotal " + oreRobot.total);
		if (clayRobot.numRobots == 0) {
			float clayTime = timeToClayRobot + blueprint.obsidianRobotClayCost + timeToCreateRobot;
			timeToObsidianRobot = Math.max(oreTime, clayTime);
			logger.log("timeToObsidianRobot " + timeToObsidianRobot + " oreTime " + oreTime + " clayTime " + clayTime);
		}
		else {
			float clayTime = timeToCreateRobot + ((blueprint.obsidianRobotClayCost - clayRobot.total) / clayRobot.numRobots);
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
		float oreTime = (blueprint.geodeRobotOreCost - oreRobot.total) / oreRobot.numRobots;
		if (obsidianRobot.numRobots == 0) {
			float obsidianTime = timeToObsidianRobot + blueprint.geodeRobotObsidianCost + timeToCreateRobot;
			timeToGeodeRobot = Math.max(oreTime, obsidianTime);
		}
		else {
			float obsidianTime = timeToCreateRobot + ((blueprint.geodeRobotObsidianCost - obsidianRobot.total) / obsidianRobot.numRobots);
			timeToGeodeRobot =  Math.max(oreTime, obsidianTime);
		}

		logger.log("calcTimeToGeodeRobot timeToObsidianRobot " + timeToObsidianRobot + " timeToGeodeRobot " + timeToGeodeRobot);
		if (timeToGeodeRobot < 0) {
			timeToGeodeRobot = 0;
		}
		return timeToGeodeRobot;
	}
}
