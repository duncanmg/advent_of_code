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

public class TestLava {

   public static void main(String[] args) throws Exception {
       TestLava obj = new TestLava();
   }

   @Test public void TestConstructor() throws Exception {

     Lava lava = new Lava();

     assertEquals(lava.getClass().getName(), "Lava");

     assertEquals(lava.id, "I000001000001000001");
   }

 }
