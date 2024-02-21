import java.io.IOException;
import java.util.*;

import java.util.concurrent.*; 
import java.util.Date;

class Optimizer {

	public static void main(String[] args) {
		Optimizer obj = new Optimizer();
	}

	public Optimizer() {
	}

	Logger logger = new Logger(this, true);

	public ArrayList<Blueprint> blueprints;

	// public int maxMinutes = 24;
	public int maxMinutes = 24;

	public int maxStrategies = 250000;

	public RobotStrategy topRobotStrategy;

	public int optimize() throws Exception {
		logger.log("Num blueprints: " + this.blueprints.size() + ".  maxMinutes: " + this.maxMinutes + ". Optimizing...");
		int totalQuality = 0;
		TimeUnit time = TimeUnit.MILLISECONDS;
		for (Blueprint blueprint : blueprints) {

			Date startDate = new Date();

			RobotStrategy bestRobotStrategy = optimizeBlueprint(blueprint);
			int quality = blueprint.id * bestRobotStrategy.robots.get("geode").total;

			Date endDate = new Date();
			long durationInMilliseconds = endDate.getTime() - startDate.getTime();
			long durationInMinutes = time.convert(durationInMilliseconds,  TimeUnit.MINUTES);

			System.out.println("Blueprint " + blueprint.id + " took " + durationInMinutes + ". It has " + bestRobotStrategy.robots.get("geode").total + " geodes and has quality " + quality);
			System.out.println("bestRobotStrategy " + bestRobotStrategy);
			totalQuality += quality;
		}
		logger.log("maxMinutes: " + this.maxMinutes + ". End Optimizing");
		return totalQuality;
	}

	public RobotStrategy optimizeBlueprint(Blueprint blueprint) throws Exception {
		int maxGeodes = 0;

		ArrayList<RobotStrategy> robotStrategies = new ArrayList<RobotStrategy>();
		RobotStrategy firstRobotStrategy = new RobotStrategy(blueprint);
		firstRobotStrategy.maxMinutes = maxMinutes;

		if (maxMinutes <= 0) {
			return firstRobotStrategy;
		}

		RobotStrategy bestRobotStrategy = depthFirstTraversal(firstRobotStrategy);

		return bestRobotStrategy;
	}

	public RobotStrategy depthFirstTraversal(RobotStrategy clonedRobotStrategy) throws Exception {
		int maxGeodes = 0;

		logger.log("End minute " + clonedRobotStrategy.minute + ". depthFirstTraversal " + clonedRobotStrategy);
		if (clonedRobotStrategy.minute >= maxMinutes) {
			logger.log("Stop depthFirstTraversal. Time exceeded. " + clonedRobotStrategy.minute + " >= " + maxMinutes);
			return clonedRobotStrategy;
		}
		clonedRobotStrategy.nextMinute();
		logger.log("Start minute " + clonedRobotStrategy.minute + ". depthFirstTraversal " + clonedRobotStrategy);

//		if (clonedRobotStrategy.minute > 10) {
//		Calculator calculator = new Calculator(clonedRobotStrategy);
//			if (!calculator.continueSearching()) {
//				return clonedRobotStrategy;
//			}
//		}

		int numRobotsRequested = 0;
		RobotStrategy bestRobotStrategy = clonedRobotStrategy;
		for (String robot : clonedRobotStrategy.robotList) {
			logger.log("Checking " + robot);

			if (numRobotsRequested > 0) {
				logger.log("Break because numRobotsRequested is " + numRobotsRequested);
				break;
			}

			if (clonedRobotStrategy.canBuildThisRobot(robot)) {

				logger.log("canBuildThisRobot " + robot + "!!!!");

				Robot current = clonedRobotStrategy.robots.get(robot);
				if (current.hasMaxRobots()) {
					continue;
				}

				if (current.hasMaxStock()) {
					continue;
				}

				// logger.log("clonedRobotStrategy: " + clonedRobotStrategy);
				RobotStrategy newRobotStrategy = (RobotStrategy) clonedRobotStrategy.clone();
				newRobotStrategy.requestThisRobot(robot);
				newRobotStrategy.collectResources();

				RobotStrategy returnedRobotStrategy = depthFirstTraversal((RobotStrategy) newRobotStrategy.clone());

				if (returnedRobotStrategy.robots.get("geode").total > maxGeodes) {
					maxGeodes = returnedRobotStrategy.robots.get("geode").total;
					bestRobotStrategy = returnedRobotStrategy;
				}

				if (robot.equals("geode")) {
					numRobotsRequested++;
				}
			}
		}
		// This is of limited use. It will be the first RobotStrategy found which has the maximum number of geodes.
		// So the geode total will be right, but the other data does not have much meaning. For example, if the
		// maximum geode total is genuinely zero then the original RobotStrategy from minute 1 will be returned.
		return bestRobotStrategy;
	}

}
