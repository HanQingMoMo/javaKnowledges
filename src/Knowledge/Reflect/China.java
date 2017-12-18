package Knowledge.Reflect;

public class China implements Country {
  public String continent = "Asia";
  private int area = 9600000;

  @Override
  public void sing() {
    System.out.println("March of the Volunteers;");
  }

  @Override
  public void show() {
    System.out.println("京剧");
  }
}
