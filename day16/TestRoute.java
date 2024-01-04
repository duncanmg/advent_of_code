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

public class TestRoute {

   public static void main(String[] args) throws Exception {
       TestRoute obj = new TestRoute();
   }

   @Test
   public void TestConstructor() throws Exception {
     Route route = new Route();
     assertEquals(route.getClass(), Route.class);
   }

   @Test
   public void TestConstructor1() throws Exception {
	 Cave c = new Cave("Valve AA has flow rate=0; tunnels lead to valves DD, II, BB");
	 ArrayList<Cave> caves = new ArrayList<Cave>(0);
	 caves.add(c);
     Route route = new Route(caves);
     assertEquals(route.getClass(), Route.class);
   }

   @Test
   public void TestConstructor2() throws Exception {
	 Cave c = new Cave("Valve AA has flow rate=0; tunnels lead to valves DD, II, BB");
	 ArrayList<Cave> caves = new ArrayList<Cave>(0);
	 caves.add(c);
     Route route = new Route(caves);

	 assertEquals(0, route.caves.get(0).flow);
	 assertEquals("AA", route.caves.get(0).name);

	 assertEquals(0, route.estimatedTotalFlow());
   }

   Route setUp(int minutesLeft) throws Exception {
	 Cave AA = new Cave("Valve AA has flow rate=0; tunnels lead to valves DD, II, BB");
	 Cave BB = new Cave("Valve BB has flow rate=10; tunnels lead to valves DD, II, BB");

	 ArrayList<Cave> caves = new ArrayList<Cave>(0);
	 caves.add(AA);
	 caves.add(BB.open(minutesLeft));
	 caves.add(BB);
     Route route = new Route(caves);
	 return route;
   }

   @Test
   public void TestEstimatedTotalFlow() throws Exception {
	 Cave AA = new Cave("Valve AA has flow rate=0; tunnels lead to valves DD, II, BB");
	 ArrayList<Cave> caves = new ArrayList<Cave>(0);
	 caves.add(AA);
     Route route = new Route(caves);

	 assertEquals(0, route.estimatedTotalFlow());

	 caves.add(new Cave(AA));
	 assertEquals(0, route.estimatedTotalFlow());

   }

   @Test
   public void TestEstimatedTotalFlow2() throws Exception {

	 Route route = this.setUp(2);
	 assertEquals(10, route.estimatedTotalFlow());

   }

   @Test
   public void TestEstimatedTotalFlow4() throws Exception {

	 Route route = this.setUp(4);
	 assertEquals(30, route.estimatedTotalFlow());

   }

   @Test
   public void TestEstimatedTotalFlow5() throws Exception {

	 Route route = this.setUp(4);
	 Cave BB = new Cave("Valve BB has flow rate=10; tunnels lead to valves DD, II, BB");

	 // BB already in route and open. No change in estimatedTotalFlow.
	 route.addCave(BB.open(10));
	 route.addCave(BB.open(10));
	 route.addCave(BB.open(10));

	 assertEquals(30, route.estimatedTotalFlow());
   }

   @Test
   public void TestStringification() throws Exception {

	 Route route = this.setUp(4);
	 assertEquals("30 : [AA, BB, BB]", route + "");
   }

   @Test
   public void TestEquals() throws Exception {

	 Route route1 = this.setUp(4);
	 Route route2 = this.setUp(4);
	 assertTrue(route1.equals(route2));
   }

 }
