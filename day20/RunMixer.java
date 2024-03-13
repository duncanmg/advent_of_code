import java.io.IOException;
import java.util.*;

class RunMixer {

	public static void main(String[] args) throws Exception {
		RunMixer obj = new RunMixer(args);
	}

	public RunMixer(String[] args) throws Exception {
		ArgumentProcessor argProcessor = new ArgumentProcessor(args);
		HashMap<String, String> argMap = argProcessor.process();
		String dataFile = argMap.get("dataFile");

		Mixer mixer = new Mixer(dataFile);
		mixer.mix();

		mixer.logChain(0, "");
	}

	Logger logger = new Logger(this, true);

}
