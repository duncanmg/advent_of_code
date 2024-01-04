import java.io.IOException;
 import java.util.*;

 class SignalStrength {

   public static void main(String[] args) throws Exception {
     System.out.println(args.length);
     if (args.length == 1) {
       SignalStrength obj = new SignalStrength(args[0]);
     } else {
       SignalStrength obj = new SignalStrength();
     }
   }

   private Data dataObj;

   private ArrayList < String > data;

   public SignalStrength() throws Exception {
     dataObj = new Data();
     data = dataObj.data;
     getSignalStrength();
   }

   public SignalStrength(String file) throws Exception {
     dataObj = new Data(file);
     data = dataObj.data;
     getSignalStrength();
   }

   ArrayList<Integer> xRegisterValues = new ArrayList<Integer>(0);

   int getCurrentValue() {
	   int s = this.xRegisterValues.size();
	   if (s == 0) {
		   return 1;
	   }
	   return this.xRegisterValues.get(s-1);
   }

   int getStepValue(int step) {
	   return this.xRegisterValues.get(step-1);
   }

   int getStepNo(int i) {
	   return i + 1;
   }

   void getSignalStrength() throws Exception {

	 // this.xRegisterValues.add(1);

	 for (int i=0; i<data.size(); i++) {
		 Instruction instruction = new Instruction(data.get(i));

		 if (instruction.op.equals(new String("noop"))) {
			 this.runNoop();
		 }
		 else if (instruction.op.equals(new String("addx"))) {	 
			 this.runAddx(instruction.amount);
		 }
	     else {
		   throw new Exception("Bad line " + data.get(i));
	     }	   
		 System.out.println("i=" + i + " step="+ this.xRegisterValues.size() + " current=" + this.getCurrentValue());
	 }

     System.out.println("===============================================");

	  ArrayList<Integer> keyValues = new ArrayList<Integer>(0);
	  keyValues.add(20);
	  keyValues.add(60);
	  keyValues.add(100);
	  keyValues.add(140);
	  keyValues.add(180);
	  keyValues.add(220);

	  int sum = 0;
	  for (int step=1; step<=this.xRegisterValues.size(); step++) {
		if (keyValues.size() > 0 && step == keyValues.get(0).intValue()) {
	    System.out.println(step+") xRegisterValue= " + this.getStepValue(step) + " Signal Strength=" + this.getSignalStrength(step));
	      // System.out.println(i+") xRegisterValue= " + this.xRegisterValues.get(i) + " Signal Strength=" + this.getSignalStrength(i));
		  sum += this.getSignalStrength(step).intValue();
		  keyValues.remove(0);
		}  
	  }
      System.out.println("Total: " + sum);
   }

   void runNoop() {
	   this.xRegisterValues.add(this.getCurrentValue());
   }

   void runAddx(Integer amount) {
	   int current = this.getCurrentValue();
	   // System.out.println("1 runAddx size=" + this.xRegisterValues.size() + " current: " + current + " amount: " + amount);
	   this.xRegisterValues.add(current);
	   // System.out.println("2 runAddx size=" + this.xRegisterValues.size() + " current: " +  this.xRegisterValues.get(this.xRegisterValues.size()-1) + " amount: " + amount);
	   this.xRegisterValues.add(current+amount.intValue());
   }

   // During the cycle, not at the end.
   Integer getSignalStrength(int step) {
      return this.getStepValue(step-1) * step;
   }

 }
