import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Blueprint {

	public static void main(String[] args) {

		ArrayList<Integer> argInts = new ArrayList<Integer>();
		for (int i=1; i<args.length; i++) {
			argInts.add(Integer.parseInt(args[i]));
		}

		Blueprint obj = new Blueprint(Integer.parseInt(args[0]), argInts);
	}

	public Blueprint(int blueprintId, ArrayList<Integer> args) {
		id = blueprintId;
		oreRobotCost = args.get(0);
		clayRobotCost = args.get(1);
		obsidianRobotOreCost = args.get(2);
		obsidianRobotClayCost = args.get(3);
		geodeRobotOreCost = args.get(4);
		geodeRobotObsidianCost = args.get(5);

		int safetyFactor = 2;

		recommendedOreStock = (oreRobotCost + obsidianRobotOreCost + geodeRobotOreCost) * safetyFactor;
		recommendedClayStock = obsidianRobotClayCost * safetyFactor;
		recommendedObsidianStock = geodeRobotObsidianCost * safetyFactor;

		maxClayRobots = obsidianRobotClayCost;
		maxObsidianRobots = geodeRobotObsidianCost;
		maxOreRobots = oreRobotCost + clayRobotCost + geodeRobotObsidianCost;
	}

	public final int id;
	public final int oreRobotCost;
	public final int clayRobotCost;
	public final int obsidianRobotOreCost;
	public final int obsidianRobotClayCost;
	public final int geodeRobotOreCost;
	public final int geodeRobotObsidianCost;

	// Can never have too many geode robots.

	// Minimum recommended levels of stock. Don't create another robot of the given type if level exceeded.
	// Really? Not sure about that advice.

	public final int recommendedOreStock;

	public final int recommendedClayStock;

	public final int recommendedObsidianStock;

	// The maximum number of robots means it's possible to create a robot of any type
	// every second. Can only ever create one robot per second, so don't need any more.

	public final int maxOreRobots;

	public final int maxClayRobots;

	public final int maxObsidianRobots;
}
