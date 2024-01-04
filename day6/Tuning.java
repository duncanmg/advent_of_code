import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Tuning {

    public static void main(String[] args) {
        Tuning obj = new Tuning();
    }

	private Data dataObj = new Data();

	private String sequence = "";

	public String data = this.dataObj.data.get(0);

    public Tuning() {
		getTuning();
    }

	void getTuning() {

		for (int i=0; i<this.data.length(); i++){
			if (this.parse(data.charAt(i))) {
				System.out.println("Result=" + (i+1) + " Sequence: " + this.sequence);
				break;
			}
		}	
	}

	Boolean parse(char c) {

		int markerLength = 14;

		this.sequence = this.sequence + c;
		int l = this.sequence.length();
		if (l < markerLength){
			return false;
		}

		int start = l - markerLength;
		String lastMarker = this.sequence.substring(start,l);
		for (int i=0; i<lastMarker.length(); i++){
			if (lastMarker.lastIndexOf(lastMarker.charAt(i)) > i) {
				return false;
			}
		}

		return true;
	}
}
