//import java.io.IOException;
import java.util.*;

 class TestItem {

   public static void main(String[] args) throws Exception {
       TestItem obj = new TestItem(52, "divisible by 11", "throw to monkey 3", "throw to monkey 3");
   }

   public TestItem(int item, String line, String trueAction, String falseAction) throws Exception {
     getTestItem(item, line, trueAction, falseAction);
   }

   public TestItem(int item, Monkey m) throws Exception {
     getTestItem(item, m.test, m.testTrue, m.testFalse);
   }

   public int throwToMonkeyNo;
   public int divideBy;;

   void getTestItem(int item, String test, String trueAction, String falseAction) throws Exception {
	 // divisible by 11
	 // throw to monkey 3
	 // throw to monkey 1

     String[] t = test.trim().split(" ");
     String[] ta = trueAction.trim().split(" ");
     String[] fa = falseAction.trim().split(" ");

	 int trueMonkeyNo = Integer.parseInt(ta[3].trim());
	 int falseMonkeyNo = Integer.parseInt(fa[3].trim());

	 int divideBy = Integer.parseInt(t[2]);
     this.divideBy = divideBy;

	 if (item %  divideBy == 0) {
         System.out.println("item " + item + " is divisible by " + divideBy + " throw to " + trueMonkeyNo);
		 this.throwToMonkeyNo = trueMonkeyNo;
	 }
	 else {
         System.out.println("item " + item + " is NOT divisible by " + divideBy + " throw to " + falseMonkeyNo);
		 this.throwToMonkeyNo = falseMonkeyNo;
	 }
   }

 }
