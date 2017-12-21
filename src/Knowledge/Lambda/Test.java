package Knowledge.Lambda;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

/**
 * @author hw 2017.12.21
 * 熟悉lambda和函数式接口(FunctionalInterface)
 */

public class Test {

  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>();

    map.putIfAbsent("name", "hanwei");
    map.putIfAbsent("age", "24");
    map.putIfAbsent("sex", "boy");

    String height = Optional.ofNullable(map.get("height")).orElse("unknown");
    String age = Optional.ofNullable(map.get("age")).orElse("unknown");

    System.out.println("- - - - - Optional Test - - - - -\n");
    System.out.println("height : " + height + "\nage : " + age);

    List<String> list = new ArrayList<>();
    System.out.println("- - - - - Stream Test - - - - -\n");
    System.out.println("遍历map，并将所有键值对存入list");

    map.forEach((k, v) -> {
      list.add(k);
      list.add(v);
    });

    list.forEach(s -> {
      System.out.print(s + "\t");
    });

    System.out.println("\n\nfilter 过滤测试");
    list.stream().filter(s -> {
      return s.startsWith("a") || s.startsWith("h");
    }).forEach(System.out::println);

    System.out.println("\n根据第二个字母排序测试");
    list.stream().sorted((a, b) -> {
      return a.substring(1).compareTo(b.substring(1));
    }).forEach(s -> {
      System.out.print(s + "\t");
    });

    System.out.println("\n\nmap映射测试");
    list.stream().map(
            String::toUpperCase
    ).forEach(s -> {
      System.out.print(s + "\t");
    });

    System.out.println("\n\nmatch匹配测试");
    Boolean b1 = list.stream().anyMatch(s -> {
      return s.contains("han");
    });
    Boolean b2 = list.stream().allMatch(s -> {
      return s.contains("han");
    });
    Boolean b3 = list.stream().noneMatch(s -> {
      return s.contains("han");
    });
    System.out.println(b1 + " " + b2 + " " + b3);

    System.out.println("\ncount计数测试");
    long count = list.stream().filter(s -> {
      return s.length() > 3;
    }).count();
    System.out.println("list中长度大于3的元素的个数为" + count);

    System.out.println("\nreduce规约测试");
    Optional<String> optional = list.stream().reduce((a, b) -> {
      return a + "---" + b;
    });

    optional.ifPresent(System.out::println);
  }
}
