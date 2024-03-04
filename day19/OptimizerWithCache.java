import java.io.IOException;
import java.util.*;

import java.util.concurrent.*; 
import java.util.Date;

class OptimizerWithCache extends Optimizer {

	public static void main(String[] args) {
		OptimizerWithCache obj = new OptimizerWithCache();
	}

	public OptimizerWithCache() {
	}

	public ArrayList<RobotStrategy> optimizeBlueprint(Blueprint blueprint) throws Exception {
		int maxGeodes = 0;

		ArrayList<RobotStrategy> robotStrategies = new ArrayList<RobotStrategy>();

		RobotStrategy firstRobotStrategy = new RobotStrategy(blueprint, maxMinutes);

		Cache cache = new Cache();
		cache.check(cache.buildKey(firstRobotStrategy), firstRobotStrategy.minute);

		if (maxMinutes <= 0) {
			robotStrategies.add(firstRobotStrategy);
			return robotStrategies;
		}

		robotStrategies = depthFirstTraversal(firstRobotStrategy, firstRobotStrategy.minute + "-start", cache);

		return robotStrategies;
	}

	public ArrayList<RobotStrategy> depthFirstTraversal(RobotStrategy clonedRobotStrategy, String trace, Cache cache) throws Exception {
		int maxGeodes = 0;

		logger.log(trace);
		ArrayList<RobotStrategy> bestRobotStrategies = new ArrayList<RobotStrategy>();

		if (clonedRobotStrategy.minute >= maxMinutes) {
			throw new Exception("Error in depthFirstTraversal. Time out of range. " + clonedRobotStrategy.minute + " >= " + maxMinutes);
		}
		clonedRobotStrategy.nextMinute();
		logger.log("Start minute " + clonedRobotStrategy.minute + ". depthFirstTraversal " + clonedRobotStrategy);

		int numRobotsRequested = 0;

		ArrayList<String> robotList = clonedRobotStrategy.robotList;

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
					if (cache.check(cache.buildKey(newRobotStrategy), clonedRobotStrategy.minute)) {
						String newTrace = trace;
						if (useTrace) {
							newTrace += "-" + clonedRobotStrategy.minute + "-" + robot;
						}
						returnedRobotStrategies = depthFirstTraversal((RobotStrategy) newRobotStrategy.clone(), newTrace, cache);
					}
					else {
						logger.log("Found in cache: " + cache.buildKey(newRobotStrategy) + " -- " + clonedRobotStrategy);
					}
				}
				else {
					logger.log("depthFirstTraversal. Do not recurse. Time exceeded. " + clonedRobotStrategy.minute + " >= " + maxMinutes);
					logger.log("depthFirstTraversal. Add to returnedRobotStrategies. " + newRobotStrategy);
					if (newRobotStrategy.robots.get("geode").total >= 4) {
						// System.out.println( trace + "-" + newRobotStrategy.minute + "-" + robot);
						// System.out.println(newRobotStrategy);
					}
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

}
