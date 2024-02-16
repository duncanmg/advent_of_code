import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
		for (Blueprint blueprint : blueprints) {
			int geodes = optimizeBlueprint(blueprint);
			int quality = blueprint.id * geodes;
			System.out.println("Blueprint " + blueprint.id + " has " + geodes + " geodes and has quality " + quality);
			totalQuality += quality;
		}
		logger.log("maxMinutes: " + this.maxMinutes + ". End Optimizing");
		return totalQuality;
	}

	public int optimizeBlueprint(Blueprint blueprint) throws Exception {
		int maxGeodes = 0;

		if (maxMinutes <= 0) {
			return maxGeodes;
		}

		ArrayList<RobotStrategy> robotStrategies = new ArrayList<RobotStrategy>();
		RobotStrategy firstRobotStrategy = new RobotStrategy();
		firstRobotStrategy.blueprint = blueprint;
		firstRobotStrategy.maxMinutes = maxMinutes;
		// firstRobotStrategy.minute = 1;
		// firstRobotStrategy.nextMinute();

		maxGeodes = depthFirstTraversal(firstRobotStrategy);

		return maxGeodes;
	}

	public int depthFirstTraversal(RobotStrategy clonedRobotStrategy) throws Exception {
		int maxGeodes = 0;

		logger.log("Start depthFirstTraversal " + clonedRobotStrategy);
		clonedRobotStrategy.nextMinute();
		if (clonedRobotStrategy.minute > maxMinutes) {
			return clonedRobotStrategy.geodeTotal;
		}

		logger.log("01 XXXXXXXXXX");
		StrategyIterator strategyIterator = new StrategyIterator(clonedRobotStrategy);

		int numRobotsRequested = 0;
		while (strategyIterator.hasNext()) {
			String robot = strategyIterator.next();
			logger.log("02 XXXXXXXXXX " + robot);

			if (numRobotsRequested > 0) {
				logger.log("Skip none because numRobotsRequested is " + numRobotsRequested);
				break;
			}

			if (clonedRobotStrategy.canBuildThisRobot(robot)) {

				logger.log("03 XXXXXXXXXX canBuildThisRobot");
//				if (!clonedRobotStrategy.hasTimeToMakeFirstGeodeRobot()) {
//					continue;
//				}
//				logger.log("04 XXXXXXXXXX hasTimeToMakeFirstGeodeRobot");

				RobotStrategy newRobotStrategy = (RobotStrategy) clonedRobotStrategy.clone();
				newRobotStrategy.requestThisRobot(robot);
				newRobotStrategy.collectResources();

				int numGeodes = depthFirstTraversal((RobotStrategy) newRobotStrategy.clone());

				if (numGeodes > maxGeodes) {
					maxGeodes = numGeodes;
				}
//				if (robot.equals("geode")) {
//					numRobotsRequested++;
//				}
			}
		}
		return maxGeodes;
	}

}
