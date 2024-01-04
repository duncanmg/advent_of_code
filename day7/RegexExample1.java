import java.util.regex.*;  
public class RegexExample1{  
public static void main(String args[]){  
//1st way  
Pattern p = Pattern.compile(".s");//. represents single character  
Matcher m = p.matcher("as");  
boolean b = m.matches();  
  
//2nd way  
boolean b2=Pattern.compile(".s").matcher("as").matches();  
  
//3rd way  
boolean b3 = Pattern.matches(".s", "as");  
  
System.out.println(b+" "+b2+" "+b3);  

// https://stackoverflow.com/questions/3853726/java-regular-expressions-and-dollar-sign

// You need to escape $ in the regex with a back-slash (\), but as a back-slash 
// is an escape character in strings you need to escape the back-slash itself.
// String pattern = "/feedback/com\\.navteq\\.lcms\\.common\\.domain\\.poi\\.feedback\\.Review\\$0(.)*";

// My example
 String line = "$ cd /";
 String line2 = "$ cd abcd";
 String line3 = "abcd";

 p = Pattern.compile("\\$ cd .*");
 Boolean b4 =  p.matcher(line).matches();
 System.out.println( "Out= " + b4);

 p = Pattern.compile("\\$ cd /");
 b4 =  p.matcher(line).matches();
 System.out.println( "Out= " + b4);

 p = Pattern.compile("\\$ cd .*");
 b4 =  p.matcher(line).matches();
 System.out.println( "Out= " + b4);

 p = Pattern.compile("abcd");
 b4 =  p.matcher(line3).matches();
 System.out.println( "Out= " + b4);

 p = Pattern.compile("a\\$bcd");
 b4 =  p.matcher("a$bcd").matches();
 System.out.println( "Out= " + b4);

}}  
