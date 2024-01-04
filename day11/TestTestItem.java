import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;

public class TestTestItem {
	
  public static void main(String[] args) throws Exception {
     TestTestItem obj = new TestTestItem();
   }

   @Test
   public void testAdd() {
      String str = "Junit is working fine";
      assertEquals("Junit is working fine",str);
   }
	
   @Test
   public void testTestItem() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
	  TestItem testItem = new TestItem(10, "divisible by 13", "throw to monkey 4", "throw to monkey 7");
	  
	  assertEquals(7,testItem.throwToMonkeyNo);
   }

   @Test
   public void testTestItem2() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
	  TestItem testItem = new TestItem(26, "divisible by 13", "throw to monkey 4", "throw to monkey 7");
	  
	  assertEquals(4,testItem.throwToMonkeyNo);
   }

   @Test
   public void testTestItem3() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
	  TestItem testItem = new TestItem(7, "divisible by 3", "throw to monkey 5", "throw to monkey 8");
	  
	  assertEquals(8,testItem.throwToMonkeyNo);
   }

   @Test
   public void testTestItem4() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
	  TestItem testItem = new TestItem(9, "divisible by 3", "throw to monkey 5", "throw to monkey 8");
	  
	  assertEquals(5,testItem.throwToMonkeyNo);
   }

}
