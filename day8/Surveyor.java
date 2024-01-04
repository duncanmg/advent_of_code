import java.io.IOException;
import java.util.*;

class Surveyor {

    public static void main(String[] args) {
		 System.out.println(args.length);
		 if (args.length == 1) {
           Surveyor obj = new Surveyor(args[0]);
		 }
		 else {
		   Surveyor obj = new Surveyor();
		 }
     }

	private Data dataObj;

	private ArrayList<String> data;

    public Surveyor() {
		dataObj = new Data();
		data = dataObj.data;
		getSurveyor();
    }

    public Surveyor(String file) {
		dataObj = new Data(file);
		data = dataObj.data;
		getSurveyor();
    }

	private Forest forest = new Forest(0);

	void getSurveyor() {

		System.out.println("Lines to process=" + this.data.size());
		for (int i=0; i<data.size(); i++) {

			String row = this.data.get(i);
            String[] cols = row.split("(?!^)");
			ArrayList<Tree> trees = new ArrayList<Tree>(0);
			for (int j=0; j<cols.length; j++){
			  trees.add(new Tree(this.forest, Integer.parseInt(cols[j]), i, j));
		    }  
			this.forest.add(trees);
		}

		int maxView = 0;
		for (int i=0; i<this.forest.size(); i++) {
			ArrayList<Tree> row = this.forest.get(i);
			for (int j=0; j<row.size(); j++){
				Tree tree = row.get(j);
				Integer score = tree.visibleScore();
				if (maxView < score) {
					maxView =score;
				}
			}
		}
		System.out.println("maxView=" + maxView	);
   }
}
