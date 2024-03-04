import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Math;

class Cache {

	public static void main(String[] args) {
		Cache obj = new Cache();
	}

	public Cache() {
	}

	Logger logger = new Logger(this, true);

	HashMap<String, Integer> alreadySeen = new HashMap<String, Integer>();

	// Returns false if already seen.
	// It's seen if the key exists and the minute is the same or earlier.
	boolean check(String key, int minute) {
		if(alreadySeen.containsKey(key)) {
			if (alreadySeen.get(key) <= minute) {
				return false;
			}
		}
		alreadySeen.put(key, minute);
		return true;
	}

	public String buildKey(RobotStrategy robotStrategy) {
		String key = "";
		for (String robotName : robotStrategy.robotList) {
			Robot robot = robotStrategy.robots.get(robotName);
			key += robot.numRobots + "-";
			key += robot.numRobotsRequested + "-";
			key += robot.total + "-";
		}
		return key;
	}
}
