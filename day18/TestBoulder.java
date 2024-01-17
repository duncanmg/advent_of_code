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

public class TestBoulder {

   public static void main(String[] args) throws Exception {
       TestBoulder obj = new TestBoulder();
   }

   @Test public void TestConstructor() throws Exception {

     Boulder boulder = new Boulder();

     assertEquals(boulder.getClass().getName(), "Boulder");

     assertEquals(boulder.id, "111");
   }

 }
