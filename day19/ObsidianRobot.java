import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Math;

class ObsidianRobot extends Robot {

	public static void main(String[] args) {
		ObsidianRobot obj = new ObsidianRobot();
	}

	public ObsidianRobot() {
	}

	public ObsidianRobot(Blueprint b, Stock s) {
		 super(b, s);
	}

        public boolean canBuildRobot(HashMap<String, Robot> robots) {
		OreRobot oreRobot = (OreRobot) robots.get("ore");
		ClayRobot clayRobot = (ClayRobot) robots.get("clay");
                return oreRobot.total >= blueprint.obsidianRobotOreCost 
			&& clayRobot.total >= blueprint.obsidianRobotClayCost;
        }

        public void requestRobot(HashMap<String, Robot> robots) throws RuntimeException{
		OreRobot oreRobot = (OreRobot) robots.get("ore");
		ClayRobot clayRobot = (ClayRobot) robots.get("clay");
                numRobotsRequested++;
		oreRobot.total -= blueprint.obsidianRobotOreCost;
                clayRobot.total -= blueprint.obsidianRobotClayCost;
        }


}
