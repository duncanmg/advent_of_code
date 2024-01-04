import java.io.IOException;
import java.util.*;

class Walker2 {

    public static void main(String[] args) {
		 System.out.println(args.length);
		 if (args.length == 1) {
           Walker2 obj = new Walker2(args[0]);
		 }
		 else {
		   Walker2 obj = new Walker2();
		 }
     }

	private Data dataObj;

	private ArrayList<String> data;

    public Walker2() {
		dataObj = new Data();
		data = dataObj.data;
		getWalker2();
    }

    public Walker2(String file) {
		dataObj = new Data(file);
		data = dataObj.data;
		getWalker2();
    }

	private Dir root;

	private Dir current;

	private ArrayList<Dir> directories = new ArrayList<Dir>(0);

	void getWalker2() {

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

		int maxSpace = 70000000;
		int requiredSpace = 30000000;
        int currentSpace = root.totalSize();

		int availableSpace = maxSpace - currentSpace;

		int toBeDeleted = requiredSpace - availableSpace;

		ArrayList<Dir> filteredList = root.dirsByMinSize(toBeDeleted);
		if (root.totalSize() >= toBeDeleted) {
			filteredList.add(root);
		}
		System.out.println("Dirs which meet criteria= " + filteredList.size());

		int min = maxSpace;
		String smallestDir = "";
		for (int i=0; i<filteredList.size(); i++) {
			Dir d = filteredList.get(i);
			if (d.totalSize() < min) {
				min = d.totalSize();
				smallestDir = d.name;
			}
		}
		System.out.println("Smallest directory which meets criteria= " + smallestDir + " size " + min);
	}

}
