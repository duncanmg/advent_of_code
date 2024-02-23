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

	public int maxMinutes = 24;

	public ArrayList<RobotStrategy> bestRobotStrategies = new ArrayList<RobotStrategy>();

	// returnOneBestStrategy will normally be set to true, which means that only one RobotStrategy will be returned
	// from depthFirstTraversal. The number of geodes will always be correct, but the other data will be of
	// limited value.
	// Setting returnOneBestStrategy to false produces a marked slowdown and vastly increased memory usage. Some
	// blueprints may crash. However, it can be useful during testing because all the RobotStrategy objects with
	// the maximum number of geodes will be returned. This allows the tester to examine the routes.
	public boolean returnOneBestStrategy = true;

	public int optimize() throws Exception {
		logger.log("Num blueprints: " + this.blueprints.size() + ".  maxMinutes: " + this.maxMinutes + ". Optimizing...");
		int totalQuality = 0;
		TimeUnit time = TimeUnit.MINUTES;
		for (Blueprint blueprint : blueprints) {

			Date startDate = new Date();

			bestRobotStrategies = optimizeBlueprint(blueprint);
			if (bestRobotStrategies.size() == 0) {
				logger.log("No strategies");
				break;
			}

			int quality = blueprint.id * bestRobotStrategies.get(0).robots.get("geode").total;

			Date endDate = new Date();
			long durationInMilliseconds = endDate.getTime() - startDate.getTime();
			long durationInMinutes = time.convert(durationInMilliseconds,  TimeUnit.MILLISECONDS);

			System.out.println("Blueprint " + blueprint.id + " took " + durationInMinutes 
					+ " minutes. It has " + bestRobotStrategies.get(0).robots.get("geode").total 
					+ " geodes and has quality " + quality);

			for (RobotStrategy bestRobotStrategy : bestRobotStrategies) {
				System.out.println("--- " + bestRobotStrategy);
			}

			totalQuality += quality;
		}
		logger.log("maxMinutes: " + this.maxMinutes + ". End Optimizing");
		return totalQuality;
	}

	public ArrayList<RobotStrategy> optimizeBlueprint(Blueprint blueprint) throws Exception {
		int maxGeodes = 0;

		ArrayList<RobotStrategy> robotStrategies = new ArrayList<RobotStrategy>();

		RobotStrategy firstRobotStrategy = new RobotStrategy(blueprint);
		firstRobotStrategy.maxMinutes = maxMinutes;

		if (maxMinutes <= 0) {
			robotStrategies.add(firstRobotStrategy);
			return robotStrategies;
		}

		robotStrategies = depthFirstTraversal(firstRobotStrategy);

		return robotStrategies;
	}

	public ArrayList<RobotStrategy> depthFirstTraversal(RobotStrategy clonedRobotStrategy) throws Exception {
		int maxGeodes = 0;

		ArrayList<RobotStrategy> bestRobotStrategies = new ArrayList<RobotStrategy>();

		if (clonedRobotStrategy.minute >= maxMinutes) {
			throw new Exception("Error in depthFirstTraversal. Time out of range. " + clonedRobotStrategy.minute + " >= " + maxMinutes);
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

				RobotStrategy newRobotStrategy = (RobotStrategy) clonedRobotStrategy.clone();
				newRobotStrategy.requestThisRobot(robot);
				newRobotStrategy.collectResources();

				ArrayList<RobotStrategy> returnedRobotStrategies = new ArrayList<RobotStrategy>();
				if (clonedRobotStrategy.minute < maxMinutes) {
					returnedRobotStrategies = depthFirstTraversal((RobotStrategy) newRobotStrategy.clone());
				}
				else {
					logger.log("depthFirstTraversal. Do not recurse. Time exceeded. " + clonedRobotStrategy.minute + " >= " + maxMinutes);
					logger.log("depthFirstTraversal. Add to returnedRobotStrategies. " + newRobotStrategy);
					returnedRobotStrategies.add(newRobotStrategy);
				}

				if (returnedRobotStrategies.size() > 0) {
					returnedRobotStrategies = filterRobotStrategies(returnedRobotStrategies);
					int returnedGeodeTotal = returnedRobotStrategies.get(0).robots.get("geode").total;
					if (returnedGeodeTotal >= maxGeodes) {
						if (returnedGeodeTotal > maxGeodes) {
							bestRobotStrategies = new ArrayList<RobotStrategy>(0);
							maxGeodes = returnedGeodeTotal;
							bestRobotStrategies.addAll(returnedRobotStrategies);
						}
						else {
							if (!returnOneBestStrategy) {
								bestRobotStrategies.addAll(returnedRobotStrategies);
							}
						}
					}
				}

				if (robot.equals("geode")) {
					numRobotsRequested++;
				}
			}
		}

		if (bestRobotStrategies.size() == 0) {
			bestRobotStrategies.add(clonedRobotStrategy);
		}

		logRobotStrategies(bestRobotStrategies, clonedRobotStrategy.minute);
		logger.log("End minute " + clonedRobotStrategy.minute + ". depthFirstTraversal");
		return bestRobotStrategies;
	}

	ArrayList<RobotStrategy> filterRobotStrategies(ArrayList<RobotStrategy> robotStrategies) {

		if (returnOneBestStrategy) {
			ArrayList<RobotStrategy> filteredRobotStrategies = new ArrayList<RobotStrategy>(0);
			filteredRobotStrategies.add(robotStrategies.get(0));
			return filteredRobotStrategies;
		}
		return robotStrategies;
	}

	void logRobotStrategies(ArrayList<RobotStrategy> robotStrategies, int minute) {
		String prefix = "";
		for (int i=0; i<minute; i++){
			prefix += ".";
		}
		for (RobotStrategy robotStrategy : robotStrategies) {
			logger.log(prefix + " " + robotStrategy);
		}
	}

}
