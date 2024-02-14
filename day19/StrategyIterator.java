import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class StrategyIterator {

	public static void main(String[] args) {
		StrategyIterator obj = new StrategyIterator(new RobotStrategy());
	}

	public StrategyIterator(RobotStrategy strategy) {
		robotStrategy = strategy;
		recommendedStrategyLabel = robotStrategy.recommendBestStrategy();
	}

	RobotStrategy robotStrategy;

	String recommendedStrategyLabel = "";

	private int counter = 0;

	public boolean hasNext() {
		return counter < 5;
	}

	public String next() {
		String out = labels[counter];

		counter++;

		return out;
	}

	private String[] labels = new String[]{"geode", "obsidian", "clay", "ore", "none"};

}
