import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class StrategyIterator {

	public static void main(String[] args) {
		StrategyIterator obj = new StrategyIterator();
	}

	// This was much simpler because it ws thought that several robots could be requested at once.
	// This is not the case, so this a simple cycle through the four robot types is sufficient.
	public StrategyIterator() {
	}

	private int counter = 0;

	private boolean currentOre = true;

	private boolean currentClay = false;

	private boolean currentObsidian = false;

	private boolean currentGeode = false;

	private boolean nextOre() {
		return counter == 1;
	}

	private boolean nextClay() {
		return counter == 2;
	}

	private boolean nextObsidian() {
		return counter == 3;
	}

	private boolean nextGeode() {
		return counter == 4;
	}

	public boolean hasNext() {
		return counter < 5;
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

}
