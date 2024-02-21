import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Math;

class Robot implements Cloneable, Comparable<Robot>{

	public static void main(String[] args) {
		Robot obj = new Robot();
	}

	public Robot() {
	}

	public Robot(Blueprint b, Stock s) {
		blueprint = b;
		stock = s;
	}

	Logger logger = new Logger(this, true);

	public Blueprint blueprint;

	Stock stock;

	public int minute = 0;

	public int maxMinutes = 24;

	public void nextMinute() {
		minute++;

		// Robots requested in the previous minute are now built and collecting.
		numRobots += numRobotsRequested;
		numRobotsRequested = 0;

	}

	public void collectResources() throws RuntimeException {
		// Update raw materials totals
		total += numRobots;

	}

	// Raw materials
	public int total = 0;

	public int projectedTotal = 0;

	// Robots collecting raw material
	public int numRobots = 0;

	// Robots requested to be built
	public int numRobotsRequested = 0;

	public boolean canBuildRobot(HashMap<String, Robot> robots) {
		logger.log("canBuildRobot() not implemented");
		return false;
	}

	// Request robots to be built
	public void requestRobot(HashMap<String, Robot> robots) throws RuntimeException{
		logger.log("requestRobot() Not implemented");
	}

	// Geode and None do not have a maximum. Others must override this.
        public boolean hasMaxRobots() {
                return false;
        }

	// Geode and None do not have a maximum. Others must override this.
        public boolean hasMaxStock() {
                return false;
        }

	// Stock required to create 1 robot per minute for the remaining time.
	public int calcMaxStock(int robotCost) {
		int shortfallPerMinute = robotCost - numRobots;
		int minutesRemaining = maxMinutes - minute;
		return shortfallPerMinute * minutesRemaining;
	}

	// Deepish clone
	@Override
		public Object clone() {
			try {
				Robot clone =  (Robot) super.clone();
				clone.stock = (Stock) stock.clone();
				return clone;
			}
			catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
		}

	// s1 == s2 : The method returns 0.
	// s1 > s2 : The method returns a positive value.
	// s1 < s2 : The method returns a negative value.
	@Override
		public int compareTo(Robot s) {
			logger.log("compareTo: " + this + " " + s);
			if (this.total > s.total) {
				return 1;
			}
			else if (this.total > s.total) {
				return 0;
			}
			else {
				return -1;
			}
		}

	public String toString() {
		return "Num Robots: " + numRobots + " Num Robots Requested: " + numRobotsRequested + " Total: " + total;
	}
}
