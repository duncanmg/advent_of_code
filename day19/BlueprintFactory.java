import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class BlueprintFactory {

	public static void main(String[] args) {
		BlueprintFactory obj = new BlueprintFactory(args);
	}

	public BlueprintFactory(String[] args) {
		ArgumentProcessor argProcessor = new ArgumentProcessor(args);
		argMap = argProcessor.process();
		String dataFile = argMap.get("dataFile");
		if (dataFile != null) {
			data = dataObj.getData(dataFile);
		}
	}

	public BlueprintFactory(String dataFile) {
		if (dataFile != null) {
			data = dataObj.getData(dataFile);
		}
	}

	HashMap<String, String> argMap;

	Data dataObj = new Data();

	// Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 7 clay. Each geode robot costs 2 ore and 19 obsidian.
	// Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 20 clay. Each geode robot costs 4 ore and 18 obsidian.
	ArrayList<String> data;

	// Key: BlueprintId, Value: ArrayList of values
	// eg 1: 4, 4, 4, 7, 2, 19
	// eg 2: 2, 4, 4, 20, 4, 18
	public  HashMap<Integer, ArrayList<Integer>> blueprintValues = new HashMap<Integer, ArrayList<Integer>>();

	void parseDataFile() {
		// Match all integers except at beginning and end of string.
		String patternString = "[^\\d](\\d+)[^\\d]";

		Pattern pattern = Pattern.compile(patternString);

		for (int i = 0; i < data.size(); i++) {
			Matcher matcher = pattern.matcher(data.get(i));

			int blueprintId = 0;
			if (matcher.find()) {
				blueprintId = Integer.parseInt(matcher.group(1));
				blueprintValues.put(blueprintId, new ArrayList<Integer>());
			}

    			while(matcher.find()) {
				blueprintValues.get(blueprintId).add(Integer.parseInt(matcher.group(1)));
        		}
		}
	}

	public Blueprint  build( int blueprintId ) {
		return new Blueprint(blueprintId, blueprintValues.get(blueprintId));
	}
}
