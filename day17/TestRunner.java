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
	  testClasses.put("TestSimulator01Basic", TestSimulator01Basic.class);
	  testClasses.put("TestSimulator02PartOne", TestSimulator02PartOne.class);
	  // testClasses.put("TestSimulator03Repetition", TestSimulator03Repetition.class); Very slow! 36 hours! 4 Jan 24.
	  testClasses.put("TestCycle", TestCycle.class);
	  testClasses.put("TestRepetitionFinder", TestRepetitionFinder.class);

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
