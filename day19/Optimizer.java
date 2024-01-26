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

	public int maxMinutes = 24;

	public int maxStrategies = 200000;

	public int optimize() {
		logger.log("Optimizing...");
		int maxGeodes = 0;
		for (Blueprint blueprint : blueprints) {
			int geodes = optimizeBlueprint(blueprint);
			if (geodes > maxGeodes) {
				maxGeodes = geodes;
			}
		}
		logger.log("End Optimizing");
		return maxGeodes;
	}

	public int optimizeBlueprint(Blueprint blueprint) {
		int maxGeodes = 0;

		if (maxMinutes <= 0) {
			return maxGeodes;
		}

		ArrayList<RobotStrategy> robotStrategies = new ArrayList<RobotStrategy>();
		RobotStrategy firstRobotStrategy = new RobotStrategy();
		firstRobotStrategy.blueprint = blueprint;
		firstRobotStrategy.maxMinutes = maxMinutes;
		firstRobotStrategy.nextMinute();
		robotStrategies.add(firstRobotStrategy);

		for (int minute = 1; minute <= maxMinutes; minute++) {

			ArrayList<RobotStrategy> newRobotStrategies = new ArrayList<RobotStrategy>();

			for (RobotStrategy robotStrategy : robotStrategies) {

				StrategyIterator strategyIterator = new StrategyIterator();

				// logger.log("Minute " + minute + " has " + robotStrategies.size() + " strategies");
				while (strategyIterator.hasNext()) {
					// logger.log("Loop");
					boolean[] strategy = strategyIterator.next();

					if (robotStrategy.canBuildTheseRobots(strategy)) {
						RobotStrategy newRobotStrategy = (RobotStrategy) robotStrategy.clone();
						newRobotStrategy.requestTheseRobots(strategy);
						if (newRobotStrategy.geodeTotal > maxGeodes) {
							maxGeodes = newRobotStrategy.geodeTotal;
							logger.log("New maxGeodes = " + maxGeodes + " from " 
								+ newRobotStrategy.numGeodeRobots + " geode robots"
								+ " and " + newRobotStrategy.numObsidianRobots + " obsidian robots"
								+ " and " + newRobotStrategy.numClayRobots + " clay robots"
								+ " and " + newRobotStrategy.numOreRobots + " ore robots"
							);
							logger.log("Requested robots: " + newRobotStrategy.numGeodeRobotsRequested + " geode robots"
								+ " and " + newRobotStrategy.numObsidianRobotsRequested + " obsidian robots"
								+ " and " + newRobotStrategy.numClayRobotsRequested + " clay robots"
								+ " and " + newRobotStrategy.numOreRobotsRequested + " ore robots"
							);
						}
						newRobotStrategy.nextMinute();
						newRobotStrategies.add(newRobotStrategy);
					}
				}
			}
			logger.log("Minute " + minute + " has " + newRobotStrategies.size() + " strategies. maxStrategies = " + maxStrategies);
			if (newRobotStrategies.size() >= maxStrategies) {
				logger.log("Sort");
				Collections.sort(newRobotStrategies, Collections.reverseOrder());
				List<RobotStrategy> list = newRobotStrategies.subList(0, maxStrategies);
				newRobotStrategies = new ArrayList<RobotStrategy>(list);
			}
			robotStrategies = newRobotStrategies;
		}
		return maxGeodes;
	}
}
