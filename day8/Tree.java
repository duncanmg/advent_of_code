import java.io.IOException;
import java.util.*;

class Tree {

    public static void main(String[] args) {
           // Tree obj = new Tree(Forest grid, Integer height, Integer row, Integer col);
     }

    public Tree(Forest g, Integer h, Integer r, Integer c) {
		forest = g;
		height = h;
		row = r;
		col = c;
    }

	public Integer height;

	public Integer row;

	public Integer col;

	private Forest forest;

	public Boolean  isVisible() {
	  if (this.isOnEdge()) {
		  return true;
	  }	  
	  if (this.isVisibleUpCol() || this.isVisibleDownCol()){
		  return true;
	  }	  
	  if (this.isVisibleUpRow() || this.isVisibleDownRow()){
		  return true;
	  }	  
      return false;
	}

	private Tree getTree(Integer row, Integer col) {
		return this.forest.get(row).get(col);
	}

	private Boolean isOnEdge() {
		if (this.row == 0 || this.col == 0) {
			return true;
		}
	    if (this.row == this.forest.size() -1 || this.col == this.forest.get(this.row).size() - 1) {
			return true;
		}
		return false;
	}

	private Boolean isVisibleUpCol() {
	   for (int i=this.col+1; i<this.forest.get(this.row).size(); i++) {
		   Tree tree = this.getTree(this.row, i);
		   if ( this.height <= tree.height ) {
			   return false;
		   }
	   }
	   return true;
	}

	private Boolean isVisibleDownCol() {
	   for (int i=this.col-1; i>=0; i--) {
		   Tree tree = this.getTree(this.row, i);
		   if ( this.height <= tree.height ) {
			   return false;
		   }
	   }
	   return true;
	}

	private Boolean isVisibleUpRow() {
	   for (int i=this.row+1; i<this.forest.size(); i++) {
		   Tree tree = this.getTree(i, this.col);
		   if ( this.height <= tree.height ) {
			   return false;
		   }
	   }
	   return true;
	}

	private Boolean isVisibleDownRow() {
	   for (int i=this.row-1; i>=0; i--) {
		   Tree tree = this.getTree(i, this.col);
		   if ( this.height <= tree.height ) {
			   return false;
		   }
	   }
	   return true;
	}

	public Integer visibleScore() {
		Integer score = this.distanceUpCol() * this.distanceDownCol() * this.distanceUpRow()
			* this.distanceDownRow();
		return score;
	}

	private Integer distanceUpCol() {
	   if (this.col == this.forest.get(this.row).size() - 1) {
		   return 0;
	   }	   
	   int distance = 0;
	   for (int i=this.col+1; i<this.forest.get(this.row).size(); i++) {
		   Tree tree = this.getTree(this.row, i);
		   distance++;
		   if ( this.height <= tree.height ) {
			   break;
		   }
	   }
	   return distance;
	}

	private Integer distanceDownCol() {
	   if (this.col == 0) {
		   return 0;
	   }	   
	   int distance = 0;
	   for (int i=this.col-1; i>=0; i--) {
		   Tree tree = this.getTree(this.row, i);
		   distance++;
		   if ( this.height <= tree.height ) {
			   break;
		   }
	   }
	   return distance;
	}

	private Integer distanceUpRow() {
	   if (this.row == this.forest.size() - 1) {
		   return 0;
	   }
	   int distance = 0;
	   for (int i=this.row+1; i<this.forest.size(); i++) {
		   Tree tree = this.getTree(i, this.col);
		   distance++;
		   if ( this.height <= tree.height ) {
			   break;
		   }
	   }
	   return distance;
	}

	private Integer distanceDownRow() {
	   if (this.row == 0) {
		   return 0;
	   }	   
	   int distance = 0;
	   for (int i=this.row-1; i>=0; i--) {
		   Tree tree = this.getTree(i, this.col);
		   distance++;
		   if ( this.height <= tree.height ) {
			   break;
		   }
	   }
	   return distance;
	}

}
