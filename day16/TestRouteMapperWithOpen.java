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

public class TestRouteMapperWithOpen {

   Boolean debug = false;

   public static void main(String[] args) throws Exception {
       TestRouteMapperWithOpen obj = new TestRouteMapperWithOpen();
   }

   ArrayList<Cave> makeCaves(String[] lines) {
	   ArrayList<Cave> caves = new ArrayList<Cave>(0);
	   for (int x=0; x<lines.length; x++) {
		   caves.add(new Cave(lines[x]));
	   }
	   return caves;
   }

   ArrayList<Route> makeRoutes(String[][] lists) {
	     ArrayList<Route> routes = new ArrayList<Route>(0);
		 for (int x=0; x<lists.length; x++) {
			 Route r = new Route();
		     r.caves.addAll(this.makeCaves(lists[x]));
		     routes.add(r);
		 }
		return routes;
   }

   HashMap<String, Cave> buildCavesHashMap(String[] caveDescriptions) {
	   HashMap<String, Cave> map = new HashMap<String, Cave>();
	   for (int i=0; i<caveDescriptions.length; i++) {
		   Cave cave = new Cave(caveDescriptions[i]);
		   map.put(cave.name, cave);
	   }
	   return map;
   }

   String[] tenCaves() {
	 return(new String[]{
		 "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB",
		 "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
		 "Valve CC has flow rate=2; tunnels lead to valves DD, BB",
		 "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE",
		 "Valve EE has flow rate=3; tunnels lead to valves FF, DD",
		 "Valve FF has flow rate=0; tunnels lead to valves EE, GG",
		 "Valve GG has flow rate=0; tunnels lead to valves FF, HH",
		 "Valve HH has flow rate=22; tunnel leads to valve GG",
		 "Valve II has flow rate=0; tunnels lead to valves AA, JJ",
		 "Valve JJ has flow rate=21; tunnel leads to valve II"
	 										  });
   }

   // This uses the test data given in the question.
   HashMap<String, Cave> testData10Routes() {
	 return(this.buildCavesHashMap(this.tenCaves()));
   }

   ArrayList<Cave> buildCaves(String[] caveDescriptions) {
	   ArrayList<Cave> list = new ArrayList<Cave>(0);
	   for (int i=0; i<caveDescriptions.length; i++) {
		   Cave cave = new Cave(caveDescriptions[i]);
		   list.add(cave);
	   }
	   return list;
   }

   // Ten routes.
   ArrayList<RoutePair> runTen(int minutesLeft) throws Exception {
	   	System.out.println("+++++++++++++++++++++++++++++++++++ runTen minutesLeft: " + minutesLeft);
		HashMap<String, Cave> map = this.testData10Routes();
	   	ArrayList<Cave> caves = new ArrayList<Cave>(0);
	  	caves.add(map.get("AA"));
	  	caves.add(map.get("BB"));
	  	caves.add(map.get("CC"));
	  	caves.add(map.get("DD"));
	  	caves.add(map.get("EE"));
	  	caves.add(map.get("FF"));
	  	caves.add(map.get("GG"));
	  	caves.add(map.get("HH"));
	  	caves.add(map.get("II"));
	  	caves.add(map.get("JJ"));

	 	RouteMapper mapper = new RouteMapper(caves.get(0), caves);
	 	ArrayList<RoutePair> got =  mapper.map(minutesLeft);
		mapper.debug = false;
		return got;
		
   }

   // Ten routes.
   public void testTen(int minutesLeft, ArrayList<Route> expected) throws Exception {
	   	System.out.println("+++++++++++++++++++++++++++++++++++ testTen minutesLeft: " + minutesLeft);

	 	ArrayList<RoutePair> got = this.runTen(minutesLeft);
		
		// System.out.println("got " + got);
		// System.out.println("expected " + expected);
		this.debug = false;
     	assertEquals(expected, got);
	   // System.out.println("End testTen");
   }

   @Test
   public void TestTwoCaves0() throws Exception {
	   ArrayList<Route> expected = this.makeRoutes(new String[][] {{"Valve AA has flow rate=0; tunnels lead to valves AA, BB"}});
	   this.testTen(0, expected);
   }

   @Test
   public void TestTenCaves1() throws Exception {
		HashMap<String, Cave> map = this.testData10Routes();
	   	String AA = map.get("AA").line;
	   	String BB = map.get("BB").line;
	   	String DD = map.get("DD").line;
	   	String II = map.get("II").line;
	   	ArrayList<Route> expected = this.makeRoutes(new String[][] { { AA, BB}, {AA, BB}, {AA, II}, {AA, DD}, {AA, DD}});
	   this.testTen(1, expected);
   }

   @Test
   public void TestTenCaves2() throws Exception {
		HashMap<String, Cave> map = this.testData10Routes();
	   	String AA = map.get("AA").line;
	   	String BB = map.get("BB").line;
	   	String CC = map.get("CC").line;
	   	String DD = map.get("DD").line;
	   	String EE = map.get("EE").line;
	   	String II = map.get("II").line;
	   	String JJ = map.get("JJ").line;

	   	ArrayList<Route> expected = this.makeRoutes(new String[][] {
		// Goes to BB and opens it. Then goes to AA and CC. Opens CC
		// but not AA because it's flow is 0.
	         											    {AA, BB, BB}, 
		            										{AA, BB, AA}, 
			   		    									{AA, BB, CC}, 
						    								{AA, BB, CC},
		// This block is correct. It goes to II but doesn't open it because the flow is 0. Then goes to
		// JJ and AA. Open JJ but not AA because its flow is 0.		   
		 												    {AA, II, JJ}, 
				    										{AA, II, JJ}, 
		  		    										{AA, II, AA},
		// This block is correct. It go to DD and opens it. Then goes to EE, AA and CC. It opena EE and CC
 		// but not AA because it has flow 0.		   
														    {AA, DD, DD}, 
		    												{AA, DD, EE}, 
		  		    										{AA, DD, EE}, 
				  		    								{AA, DD, AA}, 
						  		    						{AA, DD, CC}, 
								  		    				{AA, DD, CC}}
																	);
	   this.testTen(2, expected);
   }

   @Test
   public void TestTenCaves3() throws Exception {
		HashMap<String, Cave> map = this.testData10Routes();
	   	String AA = map.get("AA").line;
	   	String BB = map.get("BB").line;
	   	String CC = map.get("CC").line;
	   	String DD = map.get("DD").line;
	   	String EE = map.get("EE").line;
	   	String II = map.get("II").line;
	   	String JJ = map.get("JJ").line;

		// this.debug = true;
	   ArrayList<RoutePair> got = this.runTen(3);
	   assertEquals(20, got.get(0).estimatedTotalFlow());
   }

   @Test
   public void TestTenCaves4() throws Exception {

	   ArrayList<RoutePair> got = this.runTen(4);
	   assertEquals(40, got.get(0).estimatedTotalFlow());
   }

   @Test
   public void TestTenCaves5() throws Exception {

	   ArrayList<RoutePair> got = this.runTen(5);
	   assertEquals("63 : [AA, DD, DD, EE, EE, DD]", got.get(0).toString());
	   assertEquals(63, got.get(0).estimatedTotalFlow());
   }

   @Test
   public void TestTenCaves30() throws Exception {
		HashMap<String, Cave> map = this.testData10Routes();
	   	String AA = map.get("AA").line;
	   	String BB = map.get("BB").line;
	   	String CC = map.get("CC").line;
	   	String DD = map.get("DD").line;
	   	String EE = map.get("EE").line;
	   	String II = map.get("II").line;
	   	String JJ = map.get("JJ").line;

		// this.debug = true;
	   ArrayList<RoutePair> got = this.runTen(30);
	   assertEquals(1651, got.get(0).estimatedTotalFlow());
   }

}
