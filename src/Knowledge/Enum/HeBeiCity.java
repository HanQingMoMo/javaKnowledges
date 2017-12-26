package Knowledge.Enum;

/**
 * @author hw 2017.12.26
 * 复杂枚举类型，添加了字段 构造函数和方法
 */
public enum HeBeiCity {
  ShiJiaZhuang("石家庄"),
  TangShan("唐山"),
  QinHuangDao("秦皇岛"),
  HanDan("邯郸"),
  XingTai("邢台"),
  BaoDing("保定"),
  ZhangJiaKou("张家口"),
  ChengDe("承德"),
  CangZhou("沧州"),
  LangFang("廊坊"),
  HengShui("衡水");

  //这里可以定义多个私有字段，和枚举值括号中的值一一对应
  private String name;

  //构造函数必须私有,也默认私有
  HeBeiCity(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
