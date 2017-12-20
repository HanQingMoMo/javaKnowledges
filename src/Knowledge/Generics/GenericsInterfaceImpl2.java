package Knowledge.Generics;

import java.util.Random;

public class GenericsInterfaceImpl2 implements GenericsInterface<Integer> {
  private int[] numbers = new int[]{1, 4, 9, 16, 25, 36};

  public Integer next() {
    Random rand = new Random();
    return numbers[rand.nextInt(6)];
  }
}
