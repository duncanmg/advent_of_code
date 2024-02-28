import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Math;

class ClayRobot extends Robot {

	public static void main(String[] args) {
		ClayRobot obj = new ClayRobot();
	}

	public ClayRobot() {
	}

	public ClayRobot(Blueprint b, Stock s, int max) {
		super(b, s, max);
	}

	public boolean canBuildRobot(HashMap<String, Robot> robots) {
		Robot oreRobot = robots.get("ore");
		return oreRobot.total >= blueprint.clayRobotCost;
	}

	public void requestRobot(HashMap<String, Robot> robots) throws RuntimeException{
		Robot oreRobot = robots.get("ore");
		numRobotsRequested++;
		oreRobot.total -= blueprint.clayRobotCost;
	}

	public boolean hasMaxRobots() {
		return numRobots >= blueprint.maxClayRobots;
	}

        public boolean hasMaxStock() {
                return total >= calcMaxStock(blueprint.obsidianRobotClayCost);
        }

}
