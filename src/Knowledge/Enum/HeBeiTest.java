package Knowledge.Enum;

/**
 * @author hw 2017.12.26
 * 添加了字段和方法的枚举类型测试
 */
public class HeBeiTest {

  public static void main(String[] args) {
    HeBeiCity myhome = HeBeiCity.BaoDing;
    String name = myhome.getName();

    System.out.println("I come from " + myhome.name() + ", in chinese is called " + name);
  }
}
