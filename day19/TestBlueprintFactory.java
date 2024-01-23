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

public class TestBlueprintFactory {

   public static void main(String[] args) throws Exception {
       TestBlueprintFactory obj = new TestBlueprintFactory();
   }

   @Test public void TestConstructor() throws Exception {

     BlueprintFactory factory = new BlueprintFactory(new String[] {});

     assertEquals(factory.getClass().getName(), "BlueprintFactory");

   }

 }
