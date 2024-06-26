import java.io.IOException;
import java.util.*;

import java.util.concurrent.*; 
import java.util.Date;

class OptimizerWithDepthFirstSearch extends Optimizer {

	public static void main(String[] args) {
		OptimizerWithDepthFirstSearch obj = new OptimizerWithDepthFirstSearch();
	}

	public OptimizerWithDepthFirstSearch() {
	}

	public ArrayList<RobotStrategy> optimizeBlueprint(Blueprint blueprint) throws Exception {
		int maxGeodes = 0;

		ArrayList<RobotStrategy> robotStrategies = new ArrayList<RobotStrategy>();

		RobotStrategy firstRobotStrategy = new RobotStrategy(blueprint, maxMinutes);

		if (maxMinutes <= 0) {
			robotStrategies.add(firstRobotStrategy);
			return robotStrategies;
		}

		robotStrategies = depthFirstTraversal(firstRobotStrategy, new HashMap<String, Boolean>());

		return robotStrategies;
	}

	public ArrayList<RobotStrategy> depthFirstTraversal(RobotStrategy clonedRobotStrategy, HashMap<String, Boolean> robotsToSkip) throws Exception {
		int maxGeodes = 0;

		ArrayList<RobotStrategy> bestRobotStrategies = new ArrayList<RobotStrategy>();

		if (clonedRobotStrategy.minute >= maxMinutes) {
			throw new Exception("Error in depthFirstTraversal. Time out of range. " + clonedRobotStrategy.minute + " >= " + maxMinutes);
		}
		clonedRobotStrategy.nextMinute();
		logger.log("Start minute " + clonedRobotStrategy.minute + ". depthFirstTraversal " + clonedRobotStrategy);

		int numRobotsRequested = 0;

		ArrayList<String> robotList = clonedRobotStrategy.robotList;
		HashMap<String, Boolean> requestedRobots = new HashMap<String, Boolean>();

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
					Boolean prune = clonedRobotStrategy.minute > pruneAfterMinutes 
						&& clonedRobotStrategy.robots.get("geode").total < pruneBelowGeodeRobots;
					// System.out.println("clonedRobotStrategy.minute " + clonedRobotStrategy.minute + " pruneAfterMinutes " + pruneAfterMinutes);
					// System.out.println(" clonedRobotStrategy.robots.get(geode).total " +  clonedRobotStrategy.robots.get("geode").total + " pruneBelowGeodeRobots " + pruneBelowGeodeRobots);
					if (!prune) {
						// System.out.println("DO NOT PRUNE!");
						if (robot.equals("none")) {
							if (requestedRobots.isEmpty()) {
								requestedRobots = robotsToSkip;
							}
							returnedRobotStrategies = depthFirstTraversal((RobotStrategy) newRobotStrategy.clone(), requestedRobots);
						}
						else {
							if (robotsToSkip.containsKey(robot)){
								// Skip this robot.
							}
							else{
								returnedRobotStrategies = depthFirstTraversal((RobotStrategy) newRobotStrategy.clone(), new HashMap<String, Boolean>());
								requestedRobots.put(robot, true);
							}
						}
					}
				}
				else {
					logger.log("depthFirstTraversal. Do not recurse. Time exceeded. " + clonedRobotStrategy.minute + " >= " + maxMinutes);
					logger.log("depthFirstTraversal. Add to returnedRobotStrategies. " + newRobotStrategy);
//					if (newRobotStrategy.robots.get("geode").total >= 4) {
//						System.out.println( trace + "-" + newRobotStrategy.minute + "-" + robot);
//						System.out.println(newRobotStrategy);
//					}
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
