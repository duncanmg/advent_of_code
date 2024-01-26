import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class StrategyIterator {

	public static void main(String[] args) {
		StrategyIterator obj = new StrategyIterator();
	}

	public StrategyIterator() {
	}

	private int counter = 0;

	private boolean currentOre = true;

	private boolean currentClay = false;

	private boolean currentObsidian = false;

	private boolean currentGeode = false;

	private void init() {
		counter = 0;
		currentOre = true;
		currentClay = false;
		currentObsidian = false;
		currentGeode = false;
	}

	private boolean nextOre() {
		currentOre = toggle(currentOre);
		return currentOre;
	}

	private boolean nextClay() {
		if (counter == 0) {
			return currentClay;
		}

		if (counter % 2 != 0) {
			return currentClay;
		} else {
			currentClay = toggle(currentClay);
			return currentClay;
		}
	}

	private boolean nextObsidian() {
		if (counter == 0) {
			return currentClay;
		}

		if (counter % 4 != 0) {
			return currentObsidian;
		} else {
			currentObsidian = toggle(currentObsidian);
			return currentObsidian;
		}
	}

	private boolean nextGeode() {
		if (counter == 0) {
			return currentClay;
		}

		if (counter % 8 != 0) {
			return currentGeode;
		} else {
			currentGeode = toggle(currentGeode);
			return currentGeode;
		}
	}

	public boolean hasNext() {
		return counter < 16;
	}

	public boolean[] next() {
		boolean[] out = new boolean[4];
		out[0] = nextOre();
		out[1] = nextClay();
		out[2] = nextObsidian();
		out[3] = nextGeode();

		counter++;

		return out;
	}

	private boolean toggle(boolean value) {
		return value ? false : true;
	}
}
