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

public class TestCube {

   public static void main(String[] args) throws Exception {
       TestCube obj = new TestCube();
   }

   @Test public void TestConstructor() throws Exception {

     Cube boulder = new Cube();

     assertEquals(boulder.getClass().getName(), "Cube");

     assertEquals(boulder.id, "I000001000001000001");
   }

 }
