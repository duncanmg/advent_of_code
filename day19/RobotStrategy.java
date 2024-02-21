import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Math;

class RobotStrategy implements Cloneable, Comparable<RobotStrategy>{

	public static void main(String[] args) {
		RobotStrategy obj = new RobotStrategy();
	}

	public RobotStrategy() {
	}

	public RobotStrategy(Blueprint b) {
		blueprint = b;
		robots.put("ore",new OreRobot(blueprint, stock));
		robots.put("clay",new ClayRobot(blueprint, stock));
		robots.put("obsidian",new ObsidianRobot(blueprint, stock));
		robots.put("geode",new GeodeRobot(blueprint, stock));
		robots.put("none",new NoneRobot(blueprint, stock));
	}

	Logger logger = new Logger(this, true);

	public Blueprint blueprint;

	public int minute = 0;

	public int maxMinutes = 24;

	Stock stock = new Stock();

	HashMap<String, Robot> robots = new HashMap<String, Robot>();

	ArrayList<String> robotList = new ArrayList<String>(Arrays.asList("geode", "obsidian", "clay", "ore", "none"));

	ArrayList<String> reversedRobotList = new ArrayList<String>(Arrays.asList("none", "ore", "clay", "obsidian", "geode"));

	public void nextMinute() {
		minute++;

		// Robots requested in the previous minute are now built and collecting.
		for(String label : robotList) {
			robots.get(label).nextMinute();
		}
	}

	public void collectResources() throws RuntimeException {
		for(String label : robotList) {
			robots.get(label).collectResources();
		}
	}

	public boolean canBuildThisRobot(String robot){
		logger.log("canBuildThisRobot " + robot);
		return robots.get(robot).canBuildRobot(robots);
	}

	public void requestThisRobot(String robot) throws RuntimeException {
		robots.get(robot).requestRobot(robots);
	}

	// Deepish clone
	@Override
		public Object clone() {
			RobotStrategy clone;
			try {
				clone = (RobotStrategy) super.clone();
				clone.robots = new HashMap<String, Robot>();
				for(String label : robotList) {
					clone.robots.put(label, (Robot) robots.get(label).clone());
				}
			}
			catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
			return clone;
		}

	// s1 == s2 : The method returns 0.
	// s1 > s2 : The method returns a positive value.
	// s1 < s2 : The method returns a negative value.
	@Override
		public int compareTo(RobotStrategy s) {
			logger.log("compareTo: " + this + " " + s);
			return compareProjectedGeodeTotal(s);
		}

	// The projected number of Geode nodes is the best indicator.
	private int compareProjectedGeodeTotal(RobotStrategy s) {
		int t1 = robots.get("geode").total;
		int t2 = s.robots.get("geode").total;
		if (t1 > t2) {
			return 1;
		}
		else if (t1  == t2) {
			return 0;
		}
		else {
			return -1;
		}
	}

	public String toString() {
		return "Minute: " + minute + " oreRobot " + robots.get("ore") + " clayRobot " + robots.get("clay") + " obsidianRobot " + robots.get("obsidian") + " geodeRobot " + robots.get("geode");
	}
}
