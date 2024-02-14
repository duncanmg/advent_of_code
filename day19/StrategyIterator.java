import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class StrategyIterator {

	public static void main(String[] args) {
		StrategyIterator obj = new StrategyIterator(new RobotStrategy());
	}

	// This was much more complex because it was thought that several robots could be requested at once.
	// This is not the case, so this a simple cycle through the four robot types is sufficient.
	public StrategyIterator(RobotStrategy strategy) {
		robotStrategy = strategy;
		recommendedStrategyLabel = robotStrategy.recommendBestStrategy();
	}

	RobotStrategy robotStrategy;

	String recommendedStrategyLabel = "";

	private int counter = 0;

	private boolean currentOre = true;

	private boolean currentClay = false;

	private boolean currentObsidian = false;

	private boolean currentGeode = false;

	private boolean nextOre() {
		return counter == 3;
	}

	private boolean nextClay() {
		return counter == 2;
	}

	private boolean nextObsidian() {
		return counter == 1;
	}

	private boolean nextGeode() {
		return counter == 0;
	}

	public boolean hasNext() {
		return counter < 5;
	}

	public boolean[] next() {
		boolean[] out = sortStrategies();

		counter++;

		return out;
	}

	private HashMap<String, boolean[]> strategies = new HashMap<String, boolean[]>();

	// Always return geode first.
	// Return the recommended strategy next. (obsidian, clay or ore)
	// Then return obsidian unless it has aleady been returned as the recommended choice.
	// Then return ore unless clay has been pushed down because ore is the recommended choice.
	boolean[] sortStrategies() {
		strategies.put("geode", new boolean[]{false, false, false, true});
		strategies.put("obsidian", new boolean[]{false, false, true, false});
		strategies.put("clay", new boolean[]{false, true, false, false});
		strategies.put("ore", new boolean[]{true, false, false, false});
		strategies.put("none", new boolean[]{false, false, false, false});

		switch(counter) {
			case 0:
				label = "geode";
				return strategies.get("geode");
			case 1:
				label = recommendedStrategyLabel;
				return strategies.get(recommendedStrategyLabel);
			case 2:
				label = recommendedStrategyLabel.equals("obsidian") ? "clay" : "obsidian";
				return strategies.get(label);
			case 3:
				label = recommendedStrategyLabel.equals("ore") ? "clay" : "ore";
				return strategies.get(label);
			default:
				label = "none";
				return strategies.get(label);
		}

	}

	// Which strategy was returned by the last call to next();
	public String label = "";


}
