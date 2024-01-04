import java.io.IOException;
import java.util.*;

 class Operation {

   public static void main(String[] args) throws Exception {
       Operation obj = new Operation("new = old * 11", 0);
   }

   public Operation(String line, int worry) throws Exception {
	 oldWorry = worry;
     getOperation(line);
   }

   public final Integer oldWorry;

   public long newWorry;

   void getOperation(String line) throws Exception {
	 // new = old * old
     String[] chars = line.trim().split(" ");
	 char operator = chars[3].charAt(0);
	 long change;
	 if (chars[4].equals("old")) {
		 change = this.oldWorry.intValue();
	 }	 
	 else {
		 change = Integer.parseInt(chars[4]);
	 }

	 switch (operator) {
		 case '+':
			 this.newWorry = this.oldWorry + change;
			 break;
		 case '-':
			 this.newWorry = this.oldWorry - change;
			 break;
		 case '/':
			 this.newWorry = this.oldWorry / change;
			 break;
		 case '*':
			 this.newWorry = this.oldWorry * change;
			 break;

	 }

   }

 }
