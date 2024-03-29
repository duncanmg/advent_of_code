import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class ArgumentProcessor {

	public static void main(String[] args) {

		ArgumentProcessor obj = new ArgumentProcessor(args);
	}

     	public ArgumentProcessor(String[] args) {
		this.argString = Arrays.copyOf(args, args.length);
                System.out.println("ArgumentProcessor object created. " + args.length + " arguments passed.");
        }       

	String[] argString;

	public HashMap<String, String> process() {
		HashMap<String, String> out = new HashMap<String, String>();
		for (int i=0; i < argString.length; i++) {
			if (argString[i].substring(0,2).equals("--")) {
				String arg = argString[i];
				String[] bits = arg.split("=");
				String key = bits[0].substring(2, bits[0].length());
				String value = bits[1];
				out.put(key, value);
				System.out.println(out.get("dataFile"));
			}
		}
		System.out.println("Arguments are: " + out);
		return out;
	}

}
