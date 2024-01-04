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

public class TestRouteMapper {

   public static void main(String[] args) throws Exception {
       TestRouteMapper obj = new TestRouteMapper();
   }

   ArrayList<Cave> makeCaves(String[] lines) {
	   ArrayList<Cave> caves = new ArrayList<Cave>(0);
	   for (int x=0; x<lines.length; x++) {
		   caves.add(new Cave(lines[x]));
	   }
	   return caves;
   }

   Boolean debug = false;

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

   ArrayList<Cave> buildCaves(String[] caveDescriptions) {
	   ArrayList<Cave> list = new ArrayList<Cave>(0);
	   for (int i=0; i<caveDescriptions.length; i++) {
		   Cave cave = new Cave(caveDescriptions[i]);
		   list.add(cave);
	   }
	   return list;
   }

   // Artificial case where a single route loops back to itself. Rate is 0.
   // Final version won't work if rate is greater than 0 because there will be delays
   // while to tries to start valves.
   void runTestSingle(int minutesLeft,String[] expectedCaveNames) throws Exception{

	 System.out.println("----");

	 ArrayList<Cave> caves = this.buildCaves(new String[]{ "Valve AA has flow rate=0; tunnels lead to valves AA" });

	 HashMap<String, Cave> cavesMap = this.buildCavesHashMap( new String[]{ "Valve AA has flow rate=0; tunnels lead to valves AA" });
	 // cavesMap.put("AA", cave);

	 RouteMapper mapper = new RouteMapper(caves.get(0), caves);

	 // System.out.println("xxxxx----");
	 ArrayList<Route> expected = new ArrayList<Route>(0);
	 if (expectedCaveNames.length > 0) {
		 expected.add(new Route());
	 }

	 // System.out.println("2 xxxxx----");
	 for (int i=0; i<expectedCaveNames.length; i++) {
		expected.get(0).addCave(cavesMap.get(expectedCaveNames[i]));
	 }

     assertEquals(expected, mapper.map(minutesLeft));
   }

   @Test
   public void TestSingle0() throws Exception {
	   // Out of time. Returns empty  ArrayList<Route>
	   this.runTestSingle(0, new String[]{"AA"});
   }

   @Test
   public void TestSingle1() throws Exception {
	   // Adds itself before running out of time.
	   // Return single route containing cave "AA".
	   this.runTestSingle(1, new String[]{"AA", "AA"});
   }

   @Test
   public void TestSingle2() throws Exception {
	   // Return single route containing cave "AA" once.
	   this.runTestSingle(2, new String[]{"AA", "AA", "AA"});
   }

   @Test
   public void TestSingle3() throws Exception {
	   // Reurn single route containing cave "AA" once.
	   this.runTestSingle(3, new String[]{"AA", "AA", "AA", "AA"});
   }

   @Test
   public void TestSingle4() throws Exception {
	   // Reurn single route containing cave "AA" once.
	   this.runTestSingle(4, new String[]{"AA", "AA", "AA", "AA", "AA"});
   }

   // Two routes. Rate 0.
   public void testTwo(int minutesLeft, ArrayList<Route> expected) throws Exception {
	   System.out.println("+++++++++++++++++++++++++++++++++++ testTwo minutesLeft: " + minutesLeft);
	   ArrayList<Cave> caves = this.makeCaves(new String[]{"Valve AA has flow rate=0; tunnels lead to valves AA, BB",
		                                                  "Valve BB has flow rate=0; tunnels lead to valves AA"});
	 RouteMapper mapper = new RouteMapper(caves.get(0), caves);
	 mapper.debug = this.debug;

	 ArrayList<RoutePair> got = mapper.map(minutesLeft);

     assertEquals(expected, got);
	 this.debug = false;
   }

   @Test
   public void TestTwoCaves0() throws Exception {
	   ArrayList<Route> expected = this.makeRoutes(new String[][] {{"Valve AA has flow rate=0; tunnels lead to valves AA, BB"}});
	   this.testTwo(0, expected);
   }

   @Test
   public void TestTwoCaves1() throws Exception {
	   String AA = "Valve AA has flow rate=0; tunnels lead to valves AA, BB";
	   ArrayList<Route> expected = this.makeRoutes(new String[][] { {"Valve AA has flow rate=0; tunnels lead to valves AA, BB",
																	"Valve BB has flow rate=0; tunnels lead to valves AA"},
																	{"Valve AA has flow rate=0; tunnels lead to valves AA, BB",
	   																"Valve AA has flow rate=0; tunnels lead to valves AA, BB"}});
	   // this.debug = true;
	   this.testTwo(1, expected);
   }

   @Test
   public void TestTwoCaves2() throws Exception {
	   String AA = "Valve AA has flow rate=0; tunnels lead to valves AA, BB";
	   String BB = "Valve BB has flow rate=13; tunnels lead to valves AA";
	   ArrayList<Route> expected = this.makeRoutes(new String[][] { {AA, BB, AA}, {AA, AA, BB}, {AA, AA, AA}});
	   this.testTwo(2, expected);
   }

   // Three routes.
   HashMap<String, String> dataThreeRoutes() {
	    HashMap<String, String> map = new HashMap<String, String>();
		map.put("AA", "Valve AA has flow rate=0; tunnels lead to valves CC, BB");
		map.put("BB", "Valve BB has flow rate=0; tunnels lead to valves CC, AA");
		map.put("CC", "Valve CC has flow rate=0; tunnels lead to valves AA, BB");
		return map;
   }

   public void testThree(int minutesLeft, ArrayList<Route> expected) throws Exception {
	   System.out.println("+++++++++++++++++++++++++++++++++++ testThree");
	   HashMap<String, String> map = this.dataThreeRoutes();
	   ArrayList<Cave> caves = this.makeCaves(new String[]{map.get("AA"),
		                                                  map.get("BB"),
	   													  map.get("CC")});
	 RouteMapper mapper = new RouteMapper(caves.get(0), caves);

     assertEquals(expected, mapper.map(minutesLeft));
   }

   @Test
   public void TestThreeCaves0() throws Exception {
	   HashMap<String, String> h = this.dataThreeRoutes();
	   ArrayList<Route> expected = this.makeRoutes(new String[][] { {h.get("AA")}});
	   this.testThree(0, expected);
   }

   @Test
   public void TestThreeCaves4() throws Exception {
	   HashMap<String, String> map = this.dataThreeRoutes();
	   String AA = map.get("AA");
	   String BB = map.get("BB");
	   String CC = map.get("CC");

	   ArrayList<Route> expected = this.makeRoutes(new String[][] { 
												  {AA, BB, AA, BB, AA},
												  {AA, BB, AA, BB, CC}, 
												  {AA, BB, AA, CC, BB}, 
												  {AA, BB, AA, CC, AA}, 
												  {AA, BB, CC, BB, AA}, 
												  {AA, BB, CC, BB, CC}, 
												  {AA, BB, CC, AA, BB}, 
												  {AA, BB, CC, AA, CC}, 
												  {AA, CC, BB, AA, BB}, 
												  {AA, CC, BB, AA, CC}, 
												  {AA, CC, BB, CC, BB}, 
												  {AA, CC, BB, CC, AA}, 
												  {AA, CC, AA, BB, AA}, 
												  {AA, CC, AA, BB, CC}, 
												  {AA, CC, AA, CC, BB}, 
												  {AA, CC, AA, CC, AA} 
	   															});
	   this.testThree(4, expected);
   }

}
