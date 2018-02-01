package Knowledge.Concurrent.ConMap;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author hw 2017.1.16
 * 并发Map分为ConcurrentMap和ConcurrentNavigableMap
 */
public class Test {
  public static void main(String[] args) {
    ConcurrentNavigableMap<Integer, String> map = new ConcurrentSkipListMap();

    map.put(1, "hanwei");
    map.put(2, "linian");
    map.put(3, "zhenbang");
    map.put(4, "xixi");

    //1 2
    ConcurrentNavigableMap headMap = map.headMap(3);
    print(headMap, "headMap");
    //3 4
    ConcurrentNavigableMap tailMap = map.tailMap(3);
    print(tailMap, "tailMap");
    //2 3
    ConcurrentNavigableMap subMap = map.subMap(2, 4);
    print(subMap, "subMap");

  }

  public static void print(ConcurrentNavigableMap<Integer, String> map, String name) {
    System.out.println("name : " + name);
    for(ConcurrentNavigableMap.Entry<Integer, String> entry : map.entrySet()) {
      System.out.println("key : " + entry.getKey() + "  value : " + entry.getValue());
    }
    System.out.println();
  }
}
