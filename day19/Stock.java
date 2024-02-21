import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Math;

class Stock implements Cloneable {

	public static void main(String[] args) {
		Stock obj = new Stock();
	}

	public Stock() {
	}

	public int ore = 0;

	public int clay = 0;

	public int obsidian = 0;

	public int geode = 0;

      @Override
                public Object clone() {
                        try {
                                return (Stock) super.clone();
                        }
                        catch (CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                        }
                }

}
