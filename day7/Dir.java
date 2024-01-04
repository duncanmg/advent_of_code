import java.io.IOException;
import java.util.*;

class Dir {

    public static void main(String[] args) {
         Dir obj = new Dir();
     }

	public Dir parent;

	public ArrayList<File> files = new ArrayList<File>(0);;

	public ArrayList<Dir> directories = new ArrayList<Dir>(0);;

	public String name = "";

    public Dir() {
		name = "/";
    }

    public Dir(String dirName, Dir parentObj) {
		name = dirName;
		parent = parentObj;
    }

	public void addFile(String name, Integer size) {
		this.files.add(new File(name, size));
	}

	public String toString() {
		String[] out = { "name: " + name + " size: " + this.totalSize() + " parentName: " + this.parent.name + " numFiles: " + this.files.size() + " numDirectories " + this.directories.size() };

		for (int i=0; i<this.files.size(); i++) {
			int n = out.length;
			out[n] = this.files.get(i).toString();
		}

		for (int i=0; i<this.directories.size(); i++) {
			int n = out.length;
			out[n] = this.directories.get(i).toString();
		}
		return String.join("\n", out);
	}

	public Integer totalSize() {
       int totalSize = 0;
       for (int i=0; i<this.files.size(); i++) {
		   totalSize += this.files.get(i).size;
	   }
       for (int i=0; i<this.directories.size(); i++) {
		   int t = this.directories.get(i).totalSize();
		   totalSize += this.directories.get(i).totalSize();
	   }
	   return totalSize;
	}

	public ArrayList<Dir> dirsByMaxSize(int max) {

	   ArrayList<Dir> out = new ArrayList<Dir>(0);
       for (int i=0; i<this.directories.size(); i++) {
		   Dir d = this.directories.get(i);
		   if (d.totalSize() <= max) {
			  out.add(d);
		   }
		   out.addAll(d.dirsByMaxSize(max));
	   }
	   return out;
	}

	public ArrayList<Dir> dirsByMinSize(int min) {

	   ArrayList<Dir> out = new ArrayList<Dir>(0);
       for (int i=0; i<this.directories.size(); i++) {
		   Dir d = this.directories.get(i);
		   if (d.totalSize() >= min) {
			  out.add(d);
		   }
		   out.addAll(d.dirsByMinSize(min));
	   }
	   return out;
	}

	public Dir changeToParent() throws Exception {
		if ( this.name.equals("/")){
			throw new Exception("Root has no parent");
		}
		return this.parent;
	}

	public Dir changeDir(String name) {
		for (int i=0; i<this.directories.size(); i++) {
			Dir d = this.directories.get(i);
			if (d.name.equals(name)) {
				return d;
			}
		}
		Dir n = new Dir(name, this);
		this.directories.add(n);
		return n;
	}
}
