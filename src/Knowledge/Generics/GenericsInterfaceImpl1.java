package Knowledge.Generics;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.ObjectInputStream;
import java.util.Random;

public class GenericsInterfaceImpl1 implements GenericsInterface<String> {

  private String[] weeks = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

  public String next() {
    Random rand = new Random();
    return weeks[rand.nextInt(7)];
  }

  public static void main(String[] args) {

    System.out.println("GenericsInterfaceImpl1 : invoke next method");

    GenericsInterface<?> gi = new GenericsInterfaceImpl1();
    for (int i = 0; i < 10; i++) {
      System.out.println(gi.next());
    }

    System.out.println("\n\nGenericsInterfaceImpl2 : invoke next method");

    GenericsInterface<?> gi2 = new GenericsInterfaceImpl2();
    for (int i = 0; i < 10; i++) {
      System.out.println(gi2.next());
    }

    try {
      Boolean[] bs = new Boolean[6];
      bs[0] = gi instanceof GenericsInterface;
      bs[1] = gi instanceof GenericsInterfaceImpl1;
      bs[2] = gi instanceof GenericsInterfaceImpl2;

      bs[3] = gi.getClass() == Class.forName("Knowledge.Generics.GenericsInterface");
      bs[4] = gi.getClass() == Class.forName("Knowledge.Generics.GenericsInterfaceImpl1");
      bs[5] = gi.getClass() == Class.forName("Knowledge.Generics.GenericsInterfaceImpl2");

      System.out.println("\n\nTest instanceof and class ==\n");
      for(Boolean b : bs) {
        System.out.println(b);
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
