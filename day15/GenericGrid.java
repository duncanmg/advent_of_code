import java.io.IOException;
import java.util.*;

// The grid can hold any subclass of Item.
class GenericGrid {

    public static void main(String[] args) {
		   GenericGrid obj = new GenericGrid();
     }

    public GenericGrid() {
    }

	private ArrayList<ArrayList<Item>> items = new ArrayList<ArrayList<Item>>(0);

	// Add an item which is known not to exist.
	public void addItem(Item item) {
		  ArrayList<Item> column;
		  try {
		  	column = this.getColumn(item.x);
		  }
		  catch(Exception e) {
			  column = new ArrayList<Item>(0);
			  this.items.add(column);
		  }
		  column.add(item);
	}

	// Search the rows for a column with given x value. Return empty ArrayList if not found.
	ArrayList<Item> getColumn(int x) throws Exception {
		for (int i=0; i<this.items.size(); i++) {
			ArrayList<Item> column = this.items.get(i);
			if (column.get(0).x== x) {
				return column;
			}
		}
		throw new Exception("Column not found");
	}

	protected Item getItem(Item item) {
		ArrayList<Item> column;
		try { 
			column = this.getColumn(item.x);
		}	
		catch(Exception e) {
			return new Empty();
		}
		for (int i=0; i<column.size(); i++) {
			Item current = column.get(i);
			if (column.get(i).y == item.y) {
				return column.get(i);
			}
		}
		return new Empty();
	}

	protected Item getItem(int x, int y) {
		return this.getItem(new Item(x, y));
	}

	public Boolean isEmpty(int x, int y) {
		ArrayList<Item> column;
		try {
			column = this.getColumn(x);
		}
		catch(Exception e) {
			return true;
		}	
		for (int i=0; i<column.size(); i++) {
			if (column.get(i).y == y) {
				return false;
			}
		}
		return true;
	}

	// Returns an array of integers: {minX,maxX,minY,maxY}
	int[] getLimits() {
		int minX;
		int maxX;
		int minY;
		int maxY;

		if (this.items.size() == 0 || this.items.get(0).size() == 0) {
			return new int[]{0,0,0,0};
		}

        minX = this.items.get(0).get(0).x;
		maxX = minX;
		minY = this.items.get(0).get(0).y;
		maxY = minY;

		for (int i=1; i<this.items.size(); i++ ) {
			ArrayList<Item> column = this.items.get(i);
			Item first = column.get(0);
			if (first.x < minX) {
				minX = first.x;
            }
			if (first.x > maxX) {
				maxX = first.x;
			}
			for (int j=0; j<column.size(); j++) {
				Item item = column.get(j);
				if (item.y < minY) {
					minY = item.y;
				}
				if (item.y > maxY) {
					maxY = item.y;
				}
			}
		}
		return new int[]{minX,maxX,minY,maxY};
	}

	// Returns a visual representation of the grid with 0,0 at the top left.
	// System.out.println(this.visualize());
	public String visualize() {
		int[] limits = this.getLimits();

		String line = "";
		for (int i=limits[0]; i<=limits[1]; i++) {
			for (int j=limits[2]; j<=limits[3]; j++) {
				if (this.isEmpty(i,j) == true) {
					line = line + '.';
				}
				else {
					line = line + "X";
				}
			}
			line = line + "\n";
		}
        return line;
	}

	public void sortGrid() {
		ItemXComparator cX = new ItemXComparator();
		Collections.sort(this.items, cX);

		ItemYComparator cY = new ItemYComparator();
		for(int x=0; x<this.items.size(); x++){
			Collections.sort(this.items.get(x),  cY);
		}
	}

	public String toString() {
		return this.items.toString();
	}
}
