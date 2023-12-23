import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Logger {

	public static void main(String[] args) {
		Logger obj = new Logger();
	}

    public Logger() {
		name = "";
    }

    public Logger(Object o) {
		this.name = o.getClass().getName();
		this.loggerEnabled = this.readConf();
    }

	public Boolean debug = false;

	final String name;

	Data dataObj = new Data();

	ArrayList<String> data = this.dataObj.getData("logger.conf");

	Boolean loggerEnabled = false;

	Boolean readConf() {
		for (int i=0; i<this.data.size(); i++) {
			String line = this.data.get(i);
			// System.out.println(line);
			if (line.equals(this.name)) {
				return true;
			}
		}
		return false;
	}

	public void log(String msg) {
       if (this.debug && this.loggerEnabled) {
		   System.out.println(this.name + ": " + msg);
	   }
	}

}
