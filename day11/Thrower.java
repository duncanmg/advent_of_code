import java.io.IOException;
import java.util.*;
import java.math.BigInteger;

 class Thrower {

   private String fileName;

   public static void main(String[] args) throws Exception {
	   if (args.length > 0) {
         Thrower obj = new Thrower(args[0]);
	   } 
	   else {
		   System.out.println("W!!");
		   Thrower obj = new Thrower("data.txt");
	   }
   }

   public Thrower(String fName) throws Exception {
	 fileName = fName;
     run();
   }

   public Thrower() throws Exception {
     run();
   }

   Parser parser;

   int numRounds = 10000;

   ArrayList<Monkey> monkeys;

   void run() throws Exception {
       this.parser = new Parser(this.fileName);
	   this.monkeys = this.parser.monkeys;

	   int modulo = 1;
	   for (int i=0; i<this.monkeys.size(); i++) {
		   Monkey m = this.monkeys.get(i);
		   TestItem testItem = new TestItem(100, m);
		   modulo *= testItem.divideBy;
       System.out.println(modulo);
	   }

	   for (int i=0; i<this.numRounds; i++) {
         for (int monkeyNo=0; monkeyNo<this.monkeys.size(); monkeyNo++) {
			 this.monkeys.get(monkeyNo).throwAllItems(monkeys, modulo);


		 }
	   System.out.println("a===");
	   for (int j=0; j<this.monkeys.size(); j++){
		   Monkey m = this.monkeys.get(j);
		   System.out.println(m.monkeyNo + ") Items: " + m.items);
	   }
	   System.out.println("b===");
	   }	 

	   System.out.println("\nSummary\n=======");
	   for (int i=0; i<this.monkeys.size(); i++){
		   Monkey m = this.monkeys.get(i);
		   System.out.println(m.monkeyNo + ") Items: " + m.items);
	   }

       Collections.sort(this.monkeys);
	   for (int i=0; i<this.monkeys.size(); i++){
		   Monkey m = this.monkeys.get(i);
		   System.out.println(m.monkeyNo + ") numItemsInspected: " + m.numItemsInspected);
	   }
	   BigInteger business = new BigInteger(Integer.toString(this.monkeys.get(this.monkeys.size()-1).numItemsInspected));
	   business = business.multiply(new BigInteger(Integer.toString(this.monkeys.get(this.monkeys.size()-2).numItemsInspected)));
	   //Math.BigInteger business =  new Math.BigInteger(Long.toString(this.monkeys.get(this.monkeys.size()-1).numItemsInspected));
	   //business.multiplyBy(new BigInteger(this.monkeys.get(this.monkeys.size()-2).numItemsInspected.toString()));
	   // (this.monkeys.get(this.monkeys.size()-1).numItemsInspected * this.monkeys.get(this.monkeys.size()-2).numItemsInspected);
	   System.out.println("Level of monkey business: " + business);
   }

 }
