import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;

public class TestChamber {

   Chamber c;

   ArrayList<Coords> coords;

   public static void main(String[] args) throws Exception {
       TestChamber obj = new TestChamber();
   }

   @Before
   public void setUp() {
	   c = new Chamber();
	   coords = new ArrayList<Coords>(0);
   }

   @Test
   public void TestConstructor() throws Exception {
     assertEquals(c.getClass().getName(), "Chamber");
   }

   // 03 Sept 23. Just deleted most of the test because they didn't do anything useful.
   // I suppose I ought to add some useful tests at some point!
 }

