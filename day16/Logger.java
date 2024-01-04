import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Logger {

	public static void main(String[] args) {
		Logger obj = new Logger();
	}

    public Logger() {
    }

    public Logger(Boolean dbg) {
		this.debug = dbg;
    }

	public Boolean debug = false;

	public void log(String msg) {
       if (this.debug) {
		   System.out.println(msg);
	   }
	}

}
