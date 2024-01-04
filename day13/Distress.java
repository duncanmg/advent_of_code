import java.io.IOException;
import java.util.*;

 class Distress {

   private String fileName;

   public static void main(String[] args) throws Exception {
	   System.out.println(args[0]);
	   if (args.length > 0) {
         Distress obj = new Distress(args[0]);
	   } 
	   else {
		   Distress obj = new Distress("data.txt");
	   }
   }

   public Distress(String fName) throws Exception {
	   System.out.println(fName);
	 fileName = fName;
     run();
   }


   ArrayList<String> data;

   public Distress() throws Exception {
     run();
   }

   void run() throws Exception {

       Data dataObj = new Data(this.fileName);
	   this.data = dataObj.getData(this.fileName);

	   String first = "";
	   ArrayList<Packet> packets = new ArrayList<Packet>(0);
	   System.out.println(this.data.size() + " lines loaded");
	   for (int i=0; i<this.data.size(); i++) {
		   String line = this.data.get(i);
		   if (line.length() == 0) {
			   continue;
		   }
		   if (first.length() == 0) {
			   first = line;
		   }
		   else {
			   packets.add(new Packet(first, line));
			   first = "";
		   }
	   }
	   System.out.println(packets.size() + " packets to compare");

	   ArrayList<String> combined = new ArrayList<String>(0);
	   combined.add("[[2]]");
	   combined.add("[[6]]");

	   int totalCorrectOrder = 0;
	   for (int i=0; i<packets.size(); i++) {
		   System.out.println("\nNew Packet\n");
		   int result = packets.get(i).comparePackets();
		   if (result == -1) {
			   totalCorrectOrder = totalCorrectOrder + i + 1;
			   System.out.println((i+1) + ". These pairs are in the correct order: " + packets.get(i).first + " : " + packets.get(i).second);
		   }
		   combined.add(packets.get(i).first);
		   combined.add(packets.get(i).second);
	   }
	   System.out.println("totalCorrectOrder = " + totalCorrectOrder);

	   Boolean more = true;
	   Packet comparator = new Packet("", "");

	   while (more == true) {
		   more = false;
		   for (int i=0; i<combined.size()-1; i++) {
			   String lower = combined.get(i);
			   String upper = combined.get(i+1);
			   int result = comparator.comparePackets(lower, upper);
			   if (result == 1) {
				   more = true;
				   String tmp = lower;
				   combined.set(i, upper);
				   combined.set(i+1, tmp);
			   }
		   }
	   }

	   System.out.println("\n--");
	   for (int i=0; i<combined.size(); i++) {
		   System.out.println(combined.get(i));
	   }
	   System.out.println("\n--");

	   int firstMarker = combined.indexOf("[[2]]") + 1;
	   int secondMarker = combined.indexOf("[[6]]") + 1;
	   System.out.println("Decoder key=" + (firstMarker * secondMarker));
   }

 }
