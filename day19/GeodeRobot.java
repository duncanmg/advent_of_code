import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Math;

class GeodeRobot extends Robot {

	public static void main(String[] args) {
		GeodeRobot obj = new GeodeRobot();
	}

	public GeodeRobot() {
	}

	public GeodeRobot(Blueprint blueprint, Stock stock, int max) {
		super(blueprint, stock, max);
	}

        public boolean canBuildRobot(HashMap<String, Robot> robots) {
                OreRobot oreRobot = (OreRobot) robots.get("ore");
                ObsidianRobot obsidianRobot = (ObsidianRobot) robots.get("obsidian");

                return oreRobot.total >= blueprint.geodeRobotOreCost
                        && obsidianRobot.total >= blueprint.geodeRobotObsidianCost;
        }

        public void requestRobot(HashMap<String, Robot> robots) throws RuntimeException{
                OreRobot oreRobot = (OreRobot) robots.get("ore");
                ObsidianRobot obsidianRobot = (ObsidianRobot) robots.get("obsidian");

                oreRobot.total -= blueprint.geodeRobotOreCost;
                obsidianRobot.total -= blueprint.geodeRobotObsidianCost;

                numRobotsRequested++;
        }

}
