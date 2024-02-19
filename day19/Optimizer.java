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
			RobotStrategy bestRobotStrategy = optimizeBlueprint(blueprint);
			int quality = blueprint.id * bestRobotStrategy.projectedGeodeTotal;
			System.out.println("Blueprint " + blueprint.id + " has " + bestRobotStrategy.projectedGeodeTotal + " geodes and has quality " + quality);
			System.out.println("bestRobotStrategy " + bestRobotStrategy);
			totalQuality += quality;
		}
		logger.log("maxMinutes: " + this.maxMinutes + ". End Optimizing");
		return totalQuality;
	}

	public RobotStrategy optimizeBlueprint(Blueprint blueprint) throws Exception {
		int maxGeodes = 0;

		ArrayList<RobotStrategy> robotStrategies = new ArrayList<RobotStrategy>();
		RobotStrategy firstRobotStrategy = new RobotStrategy();
		firstRobotStrategy.blueprint = blueprint;
		firstRobotStrategy.maxMinutes = maxMinutes;
		// firstRobotStrategy.minute = 1;
		// firstRobotStrategy.nextMinute();

		if (maxMinutes <= 0) {
			return firstRobotStrategy;
		}

		RobotStrategy bestRobotStrategy = depthFirstTraversal(firstRobotStrategy);

		logger.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA " + bestRobotStrategy);
		return bestRobotStrategy;
	}

	public RobotStrategy depthFirstTraversal(RobotStrategy clonedRobotStrategy) throws Exception {
		int maxGeodes = 0;

		logger.log("Start depthFirstTraversal " + clonedRobotStrategy);
		clonedRobotStrategy.nextMinute();
		if (clonedRobotStrategy.minute > maxMinutes) {
			return clonedRobotStrategy;
		}

//		if (clonedRobotStrategy.minute > 10) {
//		Calculator calculator = new Calculator(clonedRobotStrategy);
//			if (!calculator.continueSearching()) {
//				return clonedRobotStrategy;
//			}
//		}

		logger.log("01 XXXXXXXXXX");
		StrategyIterator strategyIterator = new StrategyIterator(clonedRobotStrategy);

		int numRobotsRequested = 0;
		RobotStrategy bestRobotStrategy = clonedRobotStrategy;
		while (strategyIterator.hasNext()) {
			String robot = strategyIterator.next();
			logger.log("02 XXXXXXXXXX " + robot);

			if (numRobotsRequested > 0) {
				logger.log("Break because numRobotsRequested is " + numRobotsRequested);
				break;
			}

			if (clonedRobotStrategy.canBuildThisRobot(robot)) {

				if (clonedRobotStrategy.hasReachedRecommendedStockLimit(robot)) {
					logger.log("Continue because hasReachedRecommendedStockLimit is true for " + robot);
					continue;
				}

				logger.log("03 XXXXXXXXXX canBuildThisRobot");

				RobotStrategy newRobotStrategy = (RobotStrategy) clonedRobotStrategy.clone();
				newRobotStrategy.requestThisRobot(robot);
				newRobotStrategy.collectResources();

				RobotStrategy returnedRobotStrategy = depthFirstTraversal((RobotStrategy) newRobotStrategy.clone());

				if (returnedRobotStrategy.geodeTotal > maxGeodes) {
					maxGeodes = returnedRobotStrategy.geodeTotal;
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
