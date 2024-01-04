import java.io.IOException;
 import java.util.*;

 class Instruction {

   public static void main(String[] args) throws Exception {
     if (args.length == 1) {
       Instruction obj = new Instruction(args[0]);
     } else {
       Instruction obj = new Instruction("noop");
     }
   }

   public final String op;

   public final int amount;

   public final Boolean hasAmount;

   public Instruction(String line) throws Exception {
	 String[] chars = line.split(" ");
     op = chars[0];
	 if (chars.length>1) {
		 hasAmount = true;
		 amount = Integer.parseInt(chars[1]);
	 }
	 else {
		 hasAmount = false;
		 amount = 0;
	 }		 
   }

   public String toString() {
	   String str = "op: " + this.op;
	   if (this.hasAmount) {
		   str = str + " amount: " + amount;
	   }
	   return str;
   }

 }
