import java.io.IOException;
import java.util.*;

class Walker {

    public static void main(String[] args) {
		 System.out.println(args.length);
		 if (args.length == 1) {
           Walker obj = new Walker(args[0]);
		 }
		 else {
		   Walker obj = new Walker();
		 }
     }

	private Data dataObj;

	private ArrayList<String> data;

    public Walker() {
		dataObj = new Data();
		data = dataObj.data;
		getWalker();
    }

    public Walker(String file) {
		dataObj = new Data(file);
		data = dataObj.data;
		getWalker();
    }

	private Dir root;

	private Dir current;

	private ArrayList<Dir> directories = new ArrayList<Dir>(0);

	void getWalker() {

		this.root = new Dir();
        this.current = this.root;

		System.out.println("Lines to process=" + this.data.size());
		for (int i=0; i<data.size(); i++) {

			String command = this.data.get(i);

			// System.out.println(i + " line=" + command);
			// System.out.println("current=" + current.name);
			if (command.equals("$ cd ..")) {
				try {
				  current = current.changeToParent();
				}
				catch (Exception e) {
			      break;	  
				}
			}
			else if (command.equals("$ cd /")){
				current = this.root;
			}
			else if (command.length() >= 6 && command.substring(0,5).equals("$ cd ")){
				current = current.changeDir(command.substring(5));
			}
			else if (command.length() > 0 && command.charAt(0) != '$' && command.charAt(0) != 'd') {
				String[] bits = command.split(" ");
				current.addFile(bits[1], Integer.parseInt(bits[0]));
			}

			if (i>5) {
				// break;
			}
		}

		ArrayList<Dir> filteredList = root.dirsByMaxSize(100000);
		if (root.totalSize() <= 100000) {
			filteredList.add(root);
		}
		System.out.println("Dirs which meet criteria= " + filteredList.size());
		int totalFilteredSize = 0;
		for (int i=0; i<filteredList.size(); i++) {
			totalFilteredSize += filteredList.get(i).totalSize();
		}
		System.out.println("Total size  which meets criteria= " + totalFilteredSize);
	}

}
