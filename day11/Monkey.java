import java.io.IOException;
import java.util.*;

 class Monkey implements Comparable<Monkey>{

   public static void main(String[] args) throws Exception {
       // Monkey obj = new Monkey();
   }

   public Monkey(int num, ArrayList<String> monkeySection) throws Exception{
	 monkeyNo = num;
     run(monkeySection);
   }

   public ArrayList<Integer> items = new ArrayList<Integer>(0);

   public String operation;

   public String test;

   public String testTrue;

   public String testFalse;

   public int numItemsInspected = 0;

   public int monkeyNo;

   public int modulo;

   void throwAllItems(ArrayList<Monkey> monkeys, int modulo) throws Exception {
	   this.modulo = modulo;
	   while (this.items.size() > 0) {
		   this.throwTo(monkeys);
	   }
   }

   void throwTo(ArrayList<Monkey> monkeys) throws Exception {

	   if (this.items.size()==0) {
		   return;
	   }

	   // System.out.println(this.items.get(0));
	   // System.out.println(this.test);
	   // System.out.println(this.testTrue);
	   // System.out.println(this.testFalse);

	   int item = this.items.get(0).intValue();

	   // Apply operation
	   Operation operation = new Operation(this.operation, item);
       System.out.println(this.monkeyNo + ") old worry " + item + " new worry " + operation.newWorry);
	   operation.newWorry = operation.newWorry % this.modulo;
	   item = (int)operation.newWorry;
	   if (item <0) {
		   throw new Exception("XXXXXXXXXXXXXXXXXX");
	   }

	   // Divide worry level by 3
	   // item = item/3;
	   System.out.println(this.monkeyNo + ") worry level divided by 3: " + item); 

	   TestItem test = new TestItem(item, this.test, this.testTrue, this.testFalse);

	   Monkey targetMonkey = monkeys.get(test.throwToMonkeyNo);

	   // Throw to target monkey
	   this.items.remove(0);
	   targetMonkey.items.add(item);
	   // targetMonkey.items.add(item);
	   this.numItemsInspected++;

       for (int i=0; i<monkeys.size();i++){
         System.out.println(i + ") " + monkeys.get(i).items);
       }
   }

   void run(ArrayList<String> monkeySection) throws Exception {

	   ArrayList<String> commandData = new ArrayList<String>(0);
	   for( int i=0; i<monkeySection.size(); i++) {
		   String[] chars = monkeySection.get(i).split(":");
		   if (chars.length > 1) {
		     commandData.add(chars[1]);
		   }
	   }

	   this.addItems(commandData.get(0));

       this.operation = commandData.get(1);

	   this.test = commandData.get(2);

	   this.testTrue = commandData.get(3);

	   this.testFalse = commandData.get(4);

   }

   void addItems(String line) {
	   String[] chars = line.split(",");
	   for (int i=0; i<chars.length; i++) {
		   int num = Integer.parseInt(chars[i].trim());
		   this.items.add(num);
	   }
   }

    @Override
	public int compareTo(Monkey m) {
        return this.numItemsInspected - m.numItemsInspected;
	}
 }
