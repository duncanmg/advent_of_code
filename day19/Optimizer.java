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

	public int maxStrategies = 250000;

	public RobotStrategy topRobotStrategy;

	public int optimize() {
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

	public int optimizeBlueprint(Blueprint blueprint) {
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
		robotStrategies.add(firstRobotStrategy);

		for (int minute = 1; minute <= maxMinutes; minute++) {

			ArrayList<RobotStrategy> newRobotStrategies = new ArrayList<RobotStrategy>();

			logger.log("Start of minute " + minute + ". Got " + robotStrategies.size() + " strategies.");
			for (RobotStrategy robotStrategy : robotStrategies) {

				// Increment minute. Build robots requested in previous minute.
				// Collect resources.
				robotStrategy.nextMinute();
				// System.out.println(robotStrategy);

				StrategyIterator strategyIterator = new StrategyIterator(robotStrategy);

				logger.log("Minute " + minute + " process robotStrategy. " + robotStrategy.toString());
				int numRobotsRequested = 0;
				while (strategyIterator.hasNext()) {

					boolean[] strategy = strategyIterator.next();
					logger.log("Loop. Try strategy " + Arrays.toString(strategy));
					if (robotStrategy.canBuildTheseRobots(strategy)) {
						logger.log("Can follow this strategy!");

						String label = strategyIterator.label;
						// if (label.equals("none") && numRobotsRequested > 0) {
						if (numRobotsRequested > 0) {
							logger.log("A robot has been built, so skip the 'none' strategy.");
							break;
						}

						RobotStrategy newRobotStrategy = (RobotStrategy) robotStrategy.clone();
						newRobotStrategy.requestTheseRobots(strategy);
						newRobotStrategy.collectResources();

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

						logger.log("Adding newRobotStrategy: " + newRobotStrategy);
						newRobotStrategies.add(newRobotStrategy);

						numRobotsRequested++;

						if (label.equals("geode")) {
							logger.log("Can build geode robot. Skip other options");
							break;
						}
					}
				}
			}
			logger.log("End of minute " + minute + ". Got " + newRobotStrategies.size() + " strategies. maxStrategies = " + maxStrategies);
			if (newRobotStrategies.size() >= maxStrategies) {
				logger.log("Sort and prune");
				Collections.sort(newRobotStrategies, Collections.reverseOrder());
				List<RobotStrategy> list = newRobotStrategies.subList(0, maxStrategies);
				newRobotStrategies = new ArrayList<RobotStrategy>(list);
			}
			else if (minute == maxMinutes) {
				logger.log("Sort");
				Collections.sort(newRobotStrategies, Collections.reverseOrder());
			}
			robotStrategies = newRobotStrategies;
		}
		topRobotStrategy = robotStrategies.get(0);
		logger.log("maxMinutes: " + this.maxMinutes + " topRobotStrategy: " + topRobotStrategy + ". End Optimizing");
		int topFew = robotStrategies.size() > 10 ? 10 : robotStrategies.size();
		for (int i=0; i<topFew; i++) {
			System.out.println(robotStrategies.get(i).toString());
		}
		logger.log("maxGeodes = " + maxGeodes + " from " 
				+ topRobotStrategy.numGeodeRobots + " geode robots"
				+ " and " + topRobotStrategy.numObsidianRobots + " obsidian robots"
				+ " and " + topRobotStrategy.numClayRobots + " clay robots"
				+ " and " + topRobotStrategy.numOreRobots + " ore robots"
			  );
		logger.log("Requested robots: " + topRobotStrategy.numGeodeRobotsRequested + " geode robots"
				+ " and " + topRobotStrategy.numObsidianRobotsRequested + " obsidian robots"
				+ " and " + topRobotStrategy.numClayRobotsRequested + " clay robots"
				+ " and " + topRobotStrategy.numOreRobotsRequested + " ore robots"
			  );
		return maxGeodes;
	}
}
