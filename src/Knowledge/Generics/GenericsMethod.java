package Knowledge.Generics;

import java.util.Arrays;

/**
 * @author hw 2017.12.20
 * 泛型方法和可变参数
 */
public class GenericsMethod {

  /**
   * 该泛型方法用于求和，例子不是太好
   *
   * @param args 可变参数
   * @param <T>  类型参数
   * @return 所有输入参数的和
   */
  public static <T extends Number> T add(T... args) throws Exception{
    if (args.length > 0) {

      if (args[0] instanceof Integer) {

        Integer sum = 0;
        for (int i = 0; i < args.length; i++) {
          sum += args[i].intValue();
        }
        return (T) sum;

      } else if (args[0] instanceof Float) {

        Float sum = 0f;
        for (int i = 0; i < args.length; i++) {
          sum += args[i].floatValue();
        }
        return (T) sum;

      } else if (args[0] instanceof Short) {

        //short做加法运算会自动转换为int
        Integer sum = 0;
        for (int i = 0; i < args.length; i++) {
          sum += args[i].shortValue();
        }
        return (T) sum;

      } else if (args[0] instanceof Long) {

        Long sum = 0l;
        for (int i = 0; i < args.length; i++) {
          sum += args[i].longValue();
        }
        return (T) sum;

      } else { //统一按double计算

        Double sum = 0d;
        for (int i = 0; i < args.length; i++) {
          sum += args[i].doubleValue();
        }
        return (T) sum;
      }
    } else {
      throw new Exception("args cannot be null!");
    }
  }

  public static void main(String[] args) {
    try {
      int sum_i = add(1, 2, 3);
      float sum_f = add(1.0f, 2.0f, 3.0f);
      double sum_d = add(1.0d, 2.0d, 3.0d);
      long sum_l = add(1l, 2l, 3l);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}