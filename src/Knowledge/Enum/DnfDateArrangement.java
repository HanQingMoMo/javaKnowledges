package Knowledge.Enum;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author hw 2017.12.26
 * Weekday枚举的测试
 */
public class DnfDateArrangement {

  //这里只用到了枚举集合，还有枚举Map(EnumMap)
  public Set<Weekday> AnTuEn;
  public Set<Weekday> LuKe;

  public static void main(String[] args) {
    System.out.println("遍历Weekday : ");

    for(Weekday weekday : Weekday.values()) {
      System.out.println(weekday.ordinal()); //编号
      System.out.println(weekday.name()); //名称
      System.out.println(weekday.getDeclaringClass()); //所属类
      System.out.println();
    }

    DnfDateArrangement dnf = new DnfDateArrangement();
    dnf.AnTuEn = EnumSet.of(Weekday.Mon, Weekday.Thu, Weekday.Fri);
    dnf.LuKe = EnumSet.of(Weekday.Tue, Weekday.Sat);

    System.out.println("We can play AnTuEn for times : " + dnf.AnTuEn.size());
    System.out.println("We can play LuKe for times : " + dnf.LuKe.size());
  }
}
