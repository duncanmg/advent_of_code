//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;

public class TestBlueprint {

   public static void main(String[] args) throws Exception {
       TestBlueprint obj = new TestBlueprint();
   }

   @Test public void TestConstructor() throws Exception {

     Blueprint factory = new Blueprint(100, new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5)));

     assertEquals(factory.getClass().getName(), "Blueprint");

   }

 }
