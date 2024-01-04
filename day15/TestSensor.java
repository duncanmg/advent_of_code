//import java.io.IOException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;

public class TestSensor {

   public static void main(String[] args) throws Exception {
       TestSensor obj = new TestSensor();
   }

   @BeforeEach
   	void setUp() throws Exception {
   	}

   @Test
   public void TestConstructor() throws Exception {
     Sensor sensor = new Sensor(1,2);
     assertEquals(sensor.getClass(), Sensor.class);
   }

   @Test
   public void TestAttributes() throws Exception {
     Sensor sensor = new Sensor(1,2);
     assertEquals(1, sensor.x);
     assertEquals(2, sensor.y);
   }

   @Test
   public void TestGetDistance() throws Exception {
     Sensor sensor = new Sensor(1,2);
	 Beacon beacon = new Beacon(-1,-1);
     sensor.nearestBeacon = beacon;

     assertEquals(Math.abs(beacon.x - sensor.x) + Math.abs(beacon.y - sensor.y), sensor.getDistance(sensor.nearestBeacon));
   }

 }
