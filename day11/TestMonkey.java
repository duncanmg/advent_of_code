import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.*;
import java.util.*;

public class TestMonkey {

  public static void main(String[] args) throws Exception {

    TestMonkey obj = new TestMonkey();
  }

  @Test
  public void testConstructor() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

    ArrayList < String > monkeySection0 = new ArrayList < String > (0);
    monkeySection0.add("Monkey 0:");
    monkeySection0.add(" Starting items: 63, 84, 80, 83, 84, 53, 88, 72");
    monkeySection0.add("   Operation: new = old * 11");
    monkeySection0.add("   Test: divisible by 13");
    monkeySection0.add("     If true: throw to monkey 4");
    monkeySection0.add("     If false: throw to monkey 7");

    Monkey monkey = new Monkey(0, monkeySection0);

  }

  @Test
  public void testThrowto() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

    ArrayList < String > monkeySection0 = new ArrayList < String > (0);
    monkeySection0.add("Monkey 0:");
    monkeySection0.add(" Starting items: 63, 84, 80, 83, 84, 53, 88, 72");
    monkeySection0.add("   Operation: new = old * 11");
    monkeySection0.add("   Test: divisible by 13");
    monkeySection0.add("     If true: throw to monkey 1");
    monkeySection0.add("     If false: throw to monkey 1");

    ArrayList < String > monkeySection1 = new ArrayList < String > (0);
    monkeySection1.add("Monkey 0:");
    monkeySection1.add(" Starting items: 63, 84, 80, 83, 84, 53, 88, 72");
    monkeySection1.add("   Operation: new = old * 11");
    monkeySection1.add("   Test: divisible by 13");
    monkeySection1.add("     If true: throw to monkey 0");
    monkeySection1.add("     If false: throw to monkey 0");

    Monkey monkey0 = new Monkey(0, monkeySection0);

    Monkey monkey1 = new Monkey(1, monkeySection1);

    ArrayList<Monkey> monkeys = new ArrayList<Monkey>(0);
    monkeys.add(monkey0);
    monkeys.add(monkey1);

    monkey0.throwTo(monkeys);
    assertEquals(231, (int)monkey1.items.get(monkey1.items.size()-1));
    assertEquals(7, monkey0.items.size());
    assertEquals(9, monkey1.items.size());
  }

  @Test
  public void testThrowAll() throws Exception, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

    ArrayList < String > monkeySection0 = new ArrayList < String > (0);
    monkeySection0.add("Monkey 0:");
    monkeySection0.add(" Starting items: 63, 84, 80, 83, 84, 53, 88, 72");
    monkeySection0.add("   Operation: new = old * 11");
    monkeySection0.add("   Test: divisible by 13");
    monkeySection0.add("     If true: throw to monkey 1");
    monkeySection0.add("     If false: throw to monkey 1");

    ArrayList < String > monkeySection1 = new ArrayList < String > (0);
    monkeySection1.add("Monkey 0:");
    monkeySection1.add(" Starting items: 63, 84, 80, 83, 84, 53, 88, 72");
    monkeySection1.add("   Operation: new = old * 11");
    monkeySection1.add("   Test: divisible by 13");
    monkeySection1.add("     If true: throw to monkey 0");
    monkeySection1.add("     If false: throw to monkey 0");

    Monkey monkey0 = new Monkey(0, monkeySection0);

    Monkey monkey1 = new Monkey(1, monkeySection1);

    ArrayList<Monkey> monkeys = new ArrayList<Monkey>(0);
    monkeys.add(monkey0);
    monkeys.add(monkey1);

    monkey0.throwAllItems(monkeys);
    assertEquals(264, (int)monkey1.items.get(monkey1.items.size()-1));
    assertEquals(0, monkey0.items.size());
    assertEquals(16, monkey1.items.size());

    monkey1.throwAllItems(monkeys);
    System.out.println("monkey0 items: " + monkey0.items);
    assertEquals(968, (int)monkey0.items.get(monkey0.items.size()-1));
    assertEquals(16, monkey0.items.size());
    assertEquals(0, monkey1.items.size());

  }

}
