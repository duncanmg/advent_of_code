import java.io.IOException;
import java.util.*;

 class Parser {

   public static void main(String[] args) throws Exception {
	   if (args.length > 0) {
         Parser obj = new Parser(args[0]);
	   } 
	   else {
		   Parser obj = new Parser();
	   }
   }

   public Parser(String fileName) throws Exception {
	 System.out.println("In Parser fileName " + fileName);
	 dataObj = new Data(fileName);
     run();
   }

   public Parser(Data obj) throws Exception {
	 dataObj = obj;
     run();
   }

   public Parser() throws Exception {
     run();
   }

   private Data dataObj = new Data();
   private ArrayList<String> data = this.dataObj.data;

   ArrayList<Monkey> monkeys = new ArrayList<Monkey>(0);

   void run() throws Exception {

	   this.data = this.dataObj.data;

	   Boolean more = true;
	   while (more) {
		   ArrayList<String> monkeySection = this.getMonkeySection();
		   if (monkeySection.size() == 0){
			   break;
		   }
		   this.monkeys.add(new Monkey(this.monkeys.size(), monkeySection));
	   }
   }

   int index = 0;
   ArrayList<String> getMonkeySection() throws Exception {
	 ArrayList<String> out = new ArrayList<String>(0);

	 System.out.println("data.size()=" + this.data.size());
	 for (int i=this.index; i<this.data.size(); i++) {
		 String line = this.data.get(i);
		 this.index = i;
		 if (line.length() == 0) {
			 continue;
		 }
		 if (line.contains("Monkey") || line.contains("Starting items") || line.contains("Operation") || line.contains("Test:") || line.contains("If true:") || line.contains("If false:")) {
			 out.add(line);
		 }
         if (out.size() >= 6) {
			 break;
		 }	 
	 }
	 this.index++;
	 if (out.size() != 0 && out.size() != 6) {
		 for (int i=0; i<out.size(); i++) {
		   System.out.println(out.get(i));
		 }		   
		 throw new Exception("getMonkeySection() should return 6 lines");
	 }
     return out;
   }

 }
