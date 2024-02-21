import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Math;

class OreRobot extends Robot {

	public static void main(String[] args) {
		OreRobot obj = new OreRobot();
	}

	public OreRobot() {
	}

	public OreRobot(Blueprint blueprint, Stock stock) {
		super(blueprint, stock);
		numRobots = 1;
	}

	public boolean canBuildRobot(HashMap<String, Robot> robots) {
		return total >= blueprint.oreRobotCost;
	}

	public void requestRobot(HashMap<String, Robot> robots) throws RuntimeException{
		numRobotsRequested++;
		total -= blueprint.oreRobotCost;
	}

	public boolean hasMaxRobots() {
		return numRobots >= blueprint.maxOreRobots;
	}

        public boolean hasMaxStock() {
                return total >= calcMaxStock(blueprint.oreRobotCost
			+ blueprint.obsidianRobotOreCost + blueprint.geodeRobotOreCost);
        }

}
