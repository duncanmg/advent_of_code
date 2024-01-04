import java.io.IOException;
import java.util.*;

 class Packet {

   private String fileName;

   public static void main(String[] args) throws Exception {
	   if (args.length > 0) {
         Packet obj = new Packet(args[0], args[1]);
	   } 
	   else {
		   Packet obj = new Packet("[1]","[2]");
	   }
   }

   public Packet(String f, String s) throws Exception {
	   first = f;
	   second = s;
     run();
   }

   final String first;
   final String second;

   ArrayList<ArrayList<Integer>> firstPacket = new ArrayList<ArrayList<Integer>>(0);
   ArrayList<ArrayList<Integer>> secondPacket = new ArrayList<ArrayList<Integer>>(0);

   void run() throws Exception {

   }

   public Boolean isInteger(String s) {
	   Boolean ok = true;
	   try {
	     int x = Integer.parseInt(s);
	   }
	   catch(Exception e) {
		   ok = false;
	   }
	   return ok;
   }

   // True for "[1,2,3]" but not "[1,[3],4]".
   public Boolean isSimpleList(String s) {
	   return s.contains("[") && s.lastIndexOf("[") ==0 && s.indexOf("]") == s.length()-1;
   }

   // True for "" or "1" or "1,2,3".
   public Boolean isMultipleIntegers(String s) {
	   return ! s.contains("[");
   }

   // Accepts "[1,2,[3,4],5]" returns "1,2,[3,4],5".
   public String removeSingleBrackets(String s) {
	     return s.substring(1, s.length()-1);
   }

   // Accepts "10,20,30" returns {"10", "20,20"}
   public String[] nextInteger(String s) {
	   // System.out.println(s);
	   String first = "";
	   String last = "";
	   for (int i=0; i<s.length(); i++) {
		   char ch = s.charAt(i);
		   // System.out.println("nextInteger " + i + " first: " + first + " ch: " + ch);
		   if (this.isInteger(ch+"")) {
			   first = first + ch;
		   }
		   else {
			   if (ch == ',') {
				   last = s.substring(i+1,s.length());
			   }
			   else {
			       last = s.substring(i,s.length());
			   }
			   break;
		   }
	   }
	   // System.out.println("nextInteger got " + s + " returning first: " + first + " last: " + last);
	   String[] out =  { first, last};
	   return out;
   }

   // Accepts "[10,[20],30],1,2,3" returns {"[10,[20],30]", "1,2,3"}
   public String[] nextList(String s) {
	   String first = "";
	   String last = "";
	   int count = 0;
	   // System.out.println("1 nextList " + s.length() + " : " + s.charAt(0));
	   if (s.length() == 0 || s.charAt(0) != '[') {
	     // System.out.println("nextList got " + s + " returning first " + first + " last " + last);
	     String[] out =  { first, last};
	     return out;
	   }
	   // System.out.println("2 nextList " + s.length() + " : " + s.charAt(0));
	   for (int i=0; i<s.length(); i++) {
		   char ch = s.charAt(i);
		   if (ch == '[') {
			   count++;
		   }
	       else if (ch == ']') {
			   count--;
		   }	   
		   first = first + ch;
		   if (count <= 0) {
              last = s.substring(i+1,s.length());
			  if (last.length()>0 && last.charAt(0) == ',') {
                last = last.substring(1);
			  }
			  break;
		   }
	   }
	   String[] out =  { first, last};
	   // System.out.println("nextList got " + s + " returning first " + first + " last " + last);
	   return out;
   }

   public int compareMultipleIntegers(String left, String right) {
		   // System.out.println("Both multiples " + left + " : " + right);
		   String[] l = left.split(",");
		   String[] r = right.split(",");

		   int i;
		   int max = l.length > r.length ? r.length : l.length;
		   for (i=0; i<max; i++) {
			   int l1 = Integer.parseInt(l[i]);
			   int r1 = Integer.parseInt(r[i]);
			   if (l1 < r1) {
				   return -1;
			   }
		       else if (l1>r1) {
				   return 1;
			   }	   
		   }
		   if (l.length<r.length) {
			   return -1;
		   }
		   else if (l.length > r.length) {
			   return 1;
		   }
		   return 0;
   }

   public String[] nextToken(String s) {
	   String[] first = this.nextInteger(s);
	   if (first[0].length() > 0) {
		   return first;
	   }
	   else {
	       first = this.nextList(s);
		   String[] out = { this.removeSingleBrackets(first[0]), first[1] };
		   return out;
	   }
   }

   public int comparePackets() {
	   return this.comparePackets(this.first, this.second);
   }

   public int comparePackets(String left, String right) {

	   // System.out.println("\n==== Starting comparePackets with " + left + " and " + right);
	   if (left.length() == 0) {
		   if (right.length() > 0) {
			   return -1;
		   }
		   else if (right.length() == 0) {
			   return 0;
		   }
	       else {
		     return 1;
	      }
       }

	   if (right.length() == 0) {
		   if (left.length() > 0) {
			   return 1;
		   }
		   else if (left.length() == 0) {
			   return 0;
		   }
	       else {
		     return -1;
	      }
       }

	   // Both integers.
	   if (this.isInteger(left) && this.isInteger(right)) {
		   int l = Integer.parseInt(left);
		   int r = Integer.parseInt(right);

		   if (l<r) {
			   return -1;
		   }
	       else if (l>r) {
		       return 1;
		   }
		   else {
			   return 0;
		   }	   
	   }

	   // Both simple lists.
	   if (this.isSimpleList(left) && this.isSimpleList(right)) {

		 // System.out.println("Both simple lists");
	     String l = left.substring(1, left.length()-1);
	     String r = right.substring(1, right.length()-1);

		 return this.comparePackets(l, r);

	   }

	   // Both lists of integers.
	   if (this.isMultipleIntegers(left) && this.isMultipleIntegers(right)) {
		   return compareMultipleIntegers(left, right);
	   }

	   if (this.isMultipleIntegers(left)) {
		   // System.out.println("left is an integer. " + left);
		   if (this.isSimpleList(right)) {
		   // System.out.println("right is a simple list. " + right);
			   return this.comparePackets("["+left+"]", right);
		   }
		   if (this.isMultipleIntegers(right)) {
		      return compareMultipleIntegers(left, right);
		   }
	   }

	   if (this.isMultipleIntegers(right)) {
		   if (this.isSimpleList(left)) {
			   return this.comparePackets(this.removeSingleBrackets(left), right);
		   }
		   if (this.isMultipleIntegers(left)) {
		      return compareMultipleIntegers(left, right);
		   }
	   }

	   String nextLeft[] = this.nextToken(left);
	   String nextRight[] = this.nextToken(right);
	   int result = this.comparePackets(nextLeft[0], nextRight[0]);
	   if (result != 0) {
		   return result;
	   }
	   else {
		   return this.comparePackets(nextLeft[1], nextRight[1]);
	   }

   }

   public int compareSimpleLists(String left, String right) {
	   return 99;
   }

 }
