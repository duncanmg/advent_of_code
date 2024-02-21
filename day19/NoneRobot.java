import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Math;

class NoneRobot extends Robot {

	public static void main(String[] args) {
		NoneRobot obj = new NoneRobot();
	}

	public NoneRobot() {
	}

	public NoneRobot(Blueprint blueprint, Stock stock) {
		super(blueprint, stock);
	}

        public void nextMinute() {
                minute++;
        }

	public boolean canBuildRobot(HashMap<String, Robot> robots) {
		return true;
	}

	public void requestRobot(HashMap<String, Robot> robots) throws RuntimeException{
		// Do nothing
	}

	public void collectResources(){
		// Do nothing
	}

}
