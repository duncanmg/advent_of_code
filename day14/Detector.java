import java.util.*;

class Detector {

    public static void main(String[] args) {
		if (args.length > 0) {
		   Detector obj = new Detector(args[0]);
		}
		else {
			Detector obj = new Detector();
		}
     }

    public Detector() {
    }

    public Detector(String f) {
		Data d = new Data(f);
		data = d.getData(f);
		this.run();
    }

	ArrayList<String> data = new ArrayList<String>(0);
    Grid grid = new Grid();

	void run() {
		if (this.data.size() == 0) {
			System.out.println("No data");
			return;
		}	
        System.out.println(data.size() + " paths loaded");
		for (int x=0; x<data.size(); x++) {
		  this.grid.addPaths(data.get(x));
		}

		this.grid.findLowestRock();
        System.out.println(this.grid);
 	    Boolean more = true;
		int total = 0;
	    while (more == true) {
		    more = this.grid.dropGrain();
			// Don't increment if rock fell into abyss.
			if (more == true) {
				total++;
			}	
			// break;
	    }
		System.out.println(total + " grains dropped ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
	}

}
