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
	}

	public final int id;
	public final int oreRobotCost;
	public final int clayRobotCost;
	public final int obsidianRobotOreCost;
	public final int obsidianRobotClayCost;
	public final int geodeRobotOreCost;
	public final int geodeRobotObsidianCost;

}
