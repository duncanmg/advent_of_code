//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;

public class TestPacket {

   public static void main(String[] args) throws Exception {
       TestPacket obj = new TestPacket();
   }

   @BeforeEach
   	void setUp() throws Exception {
   	}

   @Test
   public void TestConstructor() throws Exception {
     Packet packet = new Packet("[1]", "[1,[2]]");
     assertEquals(packet.getClass(), Packet.class);
   }

   @Test
   public void TestParseInt() throws Exception {

	   // Successful parse.
	   assertEquals(7, Integer.parseInt("7"));

	   // Throws exception if not integer.
	   Boolean ok = false;
	   try {
	     int x = Integer.parseInt("[7]");
	   }
	   catch(Exception e) {
		   ok = true;
	   }
	   assertTrue(ok);
   }

   @Test
   public void TestBothIntegers() throws Exception {
	   Packet packet = new Packet("1", "1,2");
	   assertEquals(-1, packet.comparePackets("1", "2"));
	   assertEquals(0, packet.comparePackets("1", "1"));
	   assertEquals(1, packet.comparePackets("3", "2"));

   }

   @Test
   public void TestBothMultiples() throws Exception {
	   Packet packet = new Packet("1", "1,2");
	   assertEquals(-1, packet.comparePackets("1,2,3", "1,2,4"));
	   assertEquals(0, packet.comparePackets("44,55,56", "44,55,56"));
	   assertEquals(1, packet.comparePackets("1,2,5", "1,2,3"));

	   assertEquals(-1, packet.comparePackets("44,55,56", "44,55,56,66"));
	   assertEquals(1, packet.comparePackets("44,55,56,66,76", "44,55,56,66"));

   }

   @Test
   public void TestBothSimpleLists() throws Exception {
	   Packet packet = new Packet("1", "1,2");
	   assertEquals(-1, packet.comparePackets("[1,2,3]", "[1,2,4]"));
	   assertEquals(0, packet.comparePackets("[44,55,56]", "[44,55,56]"));
	   assertEquals(1, packet.comparePackets("[1,2,5]", "[1,2,3]"));

	   assertEquals(-1, packet.comparePackets("[44,55,56]", "[44,55,56,66]"));
	   assertEquals(1, packet.comparePackets("[44,55,56,66,76]", "[44,55,56,66]"));

   }

   @Test
   public void TestCombinations() throws Exception {
	   Packet packet = new Packet("1", "1,2");

	   assertEquals(-1, packet.comparePackets("1", "[1,2,4]"));
	   assertEquals(0, packet.comparePackets("44", "[44]"));
	   assertEquals(1, packet.comparePackets("5", "[1,2,3]"));

	   assertEquals(-1, packet.comparePackets("[1,2,3]", "5"));
	   assertEquals(0, packet.comparePackets("[3]", "3"));
	   assertEquals(1, packet.comparePackets("[3,2,1]", "3"));
   }

   @Test
   public void TestCombinations2() throws Exception {
	   Packet packet = new Packet("1", "1,2");

	   assertEquals(-1, packet.comparePackets("1", "1,2,4"));
	   assertEquals(0, packet.comparePackets("44", "44"));
	   assertEquals(1, packet.comparePackets("56", "54,7"));

	   assertEquals(-1, packet.comparePackets("1,2,3", "5"));
	   assertEquals(0, packet.comparePackets("3", "3"));
	   assertEquals(1, packet.comparePackets("3,2,1", "3"));
   }

   @Test
   public void TestNextInteger() throws Exception {
	   Packet packet = new Packet("1", "1,2");

	   String[] result = packet.nextInteger("10,20,30");
	   assertEquals(result[0],"10");
	   assertEquals(result[1],"20,30");

	   result = packet.nextInteger("[10,20,30]");
	   assertEquals("",result[0]);
	   assertEquals("[10,20,30]",result[1]);

	   result = packet.nextInteger("");
	   assertEquals("",result[0]);
	   assertEquals("",result[1]);
   }

   @Test
   public void TestNextList() throws Exception {
	   Packet packet = new Packet("1", "1,2");

	   String[] result = packet.nextList("[10,20,30]");
	   assertEquals("[10,20,30]", result[0]);
	   assertEquals("",result[1]);

	   result = packet.nextList("[10,[20],30]");
	   assertEquals("[10,[20],30]", result[0]);
	   assertEquals("",result[1]);

	   result = packet.nextList("[10,[20],30],1,2,3");
	   assertEquals("[10,[20],30]", result[0]);
	   assertEquals("1,2,3",result[1]);

	   result = packet.nextList("");
	   assertEquals("", result[0]);
	   assertEquals("",result[1]);

	   result = packet.nextList("1,2,4");
	   assertEquals("", result[0]);
	   assertEquals("",result[1]);

   }

   @Test
   public void TestCombinations3() throws Exception {
	   System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	   Packet packet = new Packet("1", "1,2");
       System.out.println("\n1 Start TestCombinations3");
	   assertEquals(-1, packet.comparePackets("[1]", "1,2,4"));
       System.out.println("\n2 Start TestCombinations3");
	   assertEquals(1, packet.comparePackets("[1,[2]]", "1,2,4"));
       System.out.println("\n3 Start TestCombinations3");
	   assertEquals(1, packet.comparePackets("[1,[2],3]", "1,2,4"));
       System.out.println("\n4 Start TestCombinations3");
	   assertEquals(1, packet.comparePackets("[1,[2,4],3]", "1,2,4"));
       System.out.println("\n5 Start TestCombinations3");
	   assertEquals(1, packet.comparePackets("2,2,4", "[1,[2,4],3]"));
   }

 }
