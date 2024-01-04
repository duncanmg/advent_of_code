import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;

public class TestOperation {
	
  public static void main(String[] args) throws Exception {
     TestOperation obj = new TestOperation();
   }

   @Test
   public void testMultiply() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
	  Operation operation = new Operation("new = old * 11", 10);
	  
	  assertEquals(110,(int)operation.newWorry);
   }

   @Test
   public void testMultiplyOld() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
	  Operation operation = new Operation("new = old * old", 10);
	  
	  assertEquals(100,(int)operation.newWorry);
   }

   @Test
   public void testDivide() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
	  Operation operation = new Operation("new = old / 2", 10);
	  
	  assertEquals(5,(int)operation.newWorry);
   }

   @Test
   public void testDivideRound() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
	  Operation operation = new Operation("new = old / 3", 11);
	  
	  // Always rounds down
	  assertEquals(3,(int)operation.newWorry);
   }

   @Test
   public void testAddition() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
	  Operation operation = new Operation("new = old + 3", 11);
	  
	  assertEquals(14,(int)operation.newWorry);
   }

   @Test
   public void testSubtraction() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
	  Operation operation = new Operation("new = old - 3", 11);
	  
	  assertEquals(8,(int)operation.newWorry);
   }

}
