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

public class TestContext {

   public static void main(String[] args) throws Exception {
       TestContext obj = new TestContext();
   }

   @Test public void TestConstructor() throws Exception {

     Context boulder = new Context();

     assertEquals(boulder.getClass().getName(), "Context");

   }

 }
