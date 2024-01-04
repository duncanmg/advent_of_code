import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunnerPart2 {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestDay9Part2.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());

   }

}  	
