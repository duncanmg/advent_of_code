import java.io.IOException;
 import java.util.*;

 class CRT {

   public static void main(String[] args) throws Exception {
     System.out.println(args.length);
     if (args.length == 1) {
       CRT obj = new CRT(args[0]);
     } else {
       CRT obj = new CRT();
     }
   }

   private Data dataObj;

   private ArrayList < String > data;

   public CRT() throws Exception {
     dataObj = new Data();
     data = dataObj.data;
     getCRT();
   }

   public CRT(String file) throws Exception {
     dataObj = new Data(file);
     data = dataObj.data;
     getCRT();
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

   int getInStepValue(int step) {
	   int valueDuringStep = step - 2;
	   if (valueDuringStep<0) {
		   return 1;
	   }
	   return this.xRegisterValues.get(valueDuringStep);
   }

   int getStepNo(int i) {
	   return i + 1;
   }

   // Parse instructions and load results into xRegisterValues.
   void parseInstructions()  throws Exception {
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
		 // System.out.println("i=" + i + " step="+ this.xRegisterValues.size() + " current=" + this.getCurrentValue());
	 }
   }

   // Output the figures required for Part 1.
   void outputKeyValues() throws Exception {
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
	    System.out.println(step+") xRegisterValue= " + this.getStepValue(step) + " Signal Strength=" + this.calculateSignalStrength(step));
		  sum += this.calculateSignalStrength(step).intValue();
		  keyValues.remove(0);
		}  
	  }
      System.out.println("Total: " + sum);
   }


   void getCRT() throws Exception {

     this.parseInstructions();

     System.out.println("===============================================");

	 this.outputKeyValues();

	 this.outputCRTImage();

   }

   // 6 lines of 40, 0 based.
   void outputCRTImage() {

	   int lineNo = 1;
	   for (int pos=0; pos<this.xRegisterValues.size(); pos++) {
		   if (pos %40 == 0){
			   System.out.printf("\n");
			   lineNo++;
		   }
		   System.out.printf(this.outputChar(pos));
	   }
	   System.out.printf("\n");
   }

   String outputChar(int pos) {
	   if (this.isSpriteVisible(pos)) {
	     return "#";
	   }
	   else {
	     return ".";
	   }
   }

   Boolean isSpriteVisible(int pos) {
	   int value = this.getInStepValue(this.getStepNo(pos));
	   // System.out.println("pos=" + pos + " step " + this.getStepNo(pos) + " value " + value);
	   if (Math.abs(pos % 40 - value) <= 1) {
		   // System.out.println("Visible");
		   return true;
	   }
	   // System.out.println("Not Visible");
       return false;
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
   Integer calculateSignalStrength(int step) {
      return this.getStepValue(step-1) * step;
   }

 }
