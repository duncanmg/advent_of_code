import java.io.IOException;
import java.util.*;

import java.util.concurrent.*; 
import java.util.Date;

class OptimizerWithCalculator extends Optimizer{

	public static void main(String[] args) {
		OptimizerWithCalculator obj = new OptimizerWithCalculator();
	}

	public OptimizerWithCalculator() {
	}

	public ArrayList<RobotStrategy> optimizeBlueprint(Blueprint blueprint) throws Exception {
		int maxGeodes = 0;

		ArrayList<RobotStrategy> robotStrategies = new ArrayList<RobotStrategy>();

		RobotStrategy firstRobotStrategy = new RobotStrategy(blueprint, maxMinutes);

		if (maxMinutes <= 0) {
			robotStrategies.add(firstRobotStrategy);
			return robotStrategies;
		}

		robotStrategies = traverse(firstRobotStrategy);

		return robotStrategies;
	}

	public ArrayList<RobotStrategy> traverse(RobotStrategy clonedRobotStrategy) throws Exception {
		int maxGeodes = 0;

		ArrayList<RobotStrategy> bestRobotStrategies = new ArrayList<RobotStrategy>();

		if (clonedRobotStrategy.minute >= maxMinutes) {
			throw new Exception("Error in traverse. Time out of range. " + clonedRobotStrategy.minute + " >= " + maxMinutes);
		}
		clonedRobotStrategy.nextMinute();
		logger.log("Start minute " + clonedRobotStrategy.minute + ". traverse " + clonedRobotStrategy);

		int numRobotsRequested = 0;

		// Calculator calculator = new Calculator(clonedRobotStrategy);
		ArrayList<String> robotList = calculateStrategy(clonedRobotStrategy);	
		logger.log("traverse : " + robotList);

		for (String robot : robotList) {
			logger.log("Checking " + robot);

				RobotStrategy newRobotStrategy = (RobotStrategy) clonedRobotStrategy.clone();
				newRobotStrategy.requestThisRobot(robot);
				newRobotStrategy.collectResources();

				ArrayList<RobotStrategy> returnedRobotStrategies = new ArrayList<RobotStrategy>();
				if (clonedRobotStrategy.minute < maxMinutes) {
					returnedRobotStrategies = traverse((RobotStrategy) newRobotStrategy.clone());
				}
				else {
					logger.log("traverse. Do not recurse. Time exceeded. " + clonedRobotStrategy.minute + " >= " + maxMinutes);
					logger.log("traverse. Add to returnedRobotStrategies. " + newRobotStrategy);
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
		}

		if (bestRobotStrategies.size() == 0) {
			bestRobotStrategies.add(clonedRobotStrategy);
		}

		logRobotStrategies(bestRobotStrategies, clonedRobotStrategy.minute);
		logger.log("End minute " + clonedRobotStrategy.minute + ". traverse");
		return bestRobotStrategies;
	}

	ArrayList<String> calculateStrategy(RobotStrategy robotStrategy) {
		Calculator calculator = new Calculator((RobotStrategy) robotStrategy.clone());
		float base = calculator.calcTimeToGeodeRobot();
		ArrayList<String> out = new ArrayList<String>();
		for (String robot : robotStrategy.robotList) {
			if (robotStrategy.canBuildThisRobot(robot)) {

				Robot current = robotStrategy.robots.get(robot);

				if (current.hasMaxRobots()) {
					continue;
				}

				if (current.hasMaxStock()) {
					continue;
				}

				// "geode" is always best.
				if (robot.equals("geode")) {
					out = new ArrayList<String>();
					out.add(robot);
					logger.log("Return geode");
					break;
				}

				calculator = new Calculator((RobotStrategy) robotStrategy.clone());
				float newCount = calculator.addRobotAndRecalc(robot);
				logger.log(robot + ". newCount " + newCount + " base " + base + " diff " + (newCount - base));
				if (newCount < base) {
					out = new ArrayList<String>();
					out.add(robot);
					base = newCount;
				}
				else if (newCount == base) {
					out.add(robot);
				}
			}
		}
		logger.log("CalculateStrategy returning: " + out.toString());
		return out;
	}

}
