import java.io.IOException;
import java.util.*;
import java.math.BigInteger;

 class Grid {

   private String fileName;

   public static void main(String[] args) throws Exception {
	   if (args.length > 0) {
         Grid obj = new Grid(args[0]);
	   } 
	   else {
		   Grid obj = new Grid("data.txt");
	   }
   }

   public Grid(String fName) throws Exception {
	 fileName = fName;
	 for (int i=0; i<chars.length;i++){
		 valueMap.add(chars[i]);
	 }

     run();
   }

   public Grid() throws Exception {
     run();
   }

   char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
	                           'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

   public ArrayList<Character> valueMap = new ArrayList<Character>(0);

   public ArrayList<ArrayList<Position>> squares = new ArrayList<ArrayList<Position>>(0);

   public int startRow;
   public int startCol;

   void run() throws Exception {
	   Data dataObj = new Data(this.fileName);
	   ArrayList<String> data = dataObj.data;

	   int startCol;
	   int StartRow;
	   for (int i=0; i<data.size(); i++) {
		   char[] bits = data.get(i).toCharArray();
		   ArrayList<Position> columns = new ArrayList<Position>(0);
		   for (int j=0; j<bits.length; j++){
			   // System.out.println(i + " " + j + " index " + this.valueMap.indexOf(bits[j]) + " character " + bits[j]);
			   int index = this.valueMap.indexOf(bits[j]);
			   if (bits[j] == 'S') {
				   index = 0;
				   this.startRow = i;
				   this.startCol = j;
			   }
		       else if (bits[j] == 'E') {
			      index = 25;
		       }		  
			   columns.add(new Position(i, j, index, bits[j]));
		   }
	       this.squares.add(columns);
	   }
       System.out.println("Grid: " + this.squares.size() + " lines loaded from " + this.fileName + ". squares: " + this.squares);
   }

 }
