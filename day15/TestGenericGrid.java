//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;

public class TestGenericGrid {

   public static void main(String[] args) throws Exception {
       TestGenericGrid obj = new TestGenericGrid();
   }

   @BeforeEach
   	void setUp() throws Exception {
   	}

   @Test
   public void TestConstructor() throws Exception {
     GenericGrid grid = new GenericGrid();
     assertEquals(grid.getClass(), GenericGrid.class);
   }

   @Test
   public void TestAttributes() throws Exception {
     GenericGrid grid = new GenericGrid();
	 grid.addItem(new Sensor(1,2));
     assertEquals(1, grid.getItem(1,2).x);
     assertEquals(2, grid.getItem(1,2).y);
   }

 }
