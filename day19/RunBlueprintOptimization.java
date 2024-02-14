import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class RunBlueprintOptimization {

	public static void main(String[] args) throws Exception {
		RunBlueprintOptimization obj = new RunBlueprintOptimization(args);
	}

	public RunBlueprintOptimization(String[] args) throws Exception {
		ArgumentProcessor argProcessor = new ArgumentProcessor(args);
		HashMap<String, String> argMap = argProcessor.process();
		String dataFile = argMap.get("dataFile");
		if (dataFile != null) {
			BlueprintFactory blueprintFactory = new BlueprintFactory(dataFile);
			blueprintFactory.parseDataFile();

			HashMap<Integer, ArrayList<Integer>> blueprintValues = blueprintFactory.blueprintValues;

			ArrayList<Blueprint> blueprints = new ArrayList<Blueprint>();
			for (Integer blueprintId : blueprintValues.keySet()) {
				ArrayList<Integer> values = blueprintValues.get(blueprintId);
				Blueprint blueprint = new Blueprint(blueprintId, values);
				blueprints.add(blueprint);
			}
			Optimizer optimizer = new Optimizer();
			optimizer.blueprints = blueprints;
			int totalQuality = optimizer.optimize();
			logger.log("totalQuality: " + totalQuality);
		}
	}

	Logger logger = new Logger(this, true);

	ArrayList<Blueprint> blueprints = new ArrayList<Blueprint>();

}
