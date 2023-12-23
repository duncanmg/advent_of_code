import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;

public class TestRunner {

   @SuppressWarnings("rawtypes")
   public static void main(String[] args) {

	  HashMap<String, Class> testClasses = new HashMap<String, Class>();

	  testClasses.put("TestRock", TestRock.class);
	  testClasses.put("TestChamber", TestChamber.class);
	  testClasses.put("TestPyroclastic01Basic", TestPyroclastic01Basic.class);
	  testClasses.put("TestPyroclastic02PartOne", TestPyroclastic02PartOne.class);
	  testClasses.put("TestPyroclastic03Repetition", TestPyroclastic03Repetition.class);
	  testClasses.put("TestCycle", TestCycle.class);

	  if (args.length > 0) {
	  	for (int i=0; i<args.length; i++) {
		  	runTests(testClasses.get(args[i]));
	  	}
	  }
	  else {
		for (Map.Entry<String, Class> entry : testClasses.entrySet()) {
			String key = entry.getKey();
			runTests(entry.getValue());
		}
	  }

   }

   @SuppressWarnings("rawtypes")
   static void runTests(Class testClass) {
      Result result = JUnitCore.runClasses(testClass);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(testClass.getName() + ": " + result.getRunCount() + " tests run. " + (result.wasSuccessful() ? "Success" : "Failure"));

   }
}  	
