package Knowledge.Reflect;

import java.lang.reflect.*;

public class Test {

  public static void main(String[] args) {
    Class c = null;

    try {
      c = Class.forName("Knowledge.Reflect.Hebei");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    listFields(c);
    invokeMethod(c);
  }

  private static void listFields(Class c) {

    System.out.println("-----获取Hebei自身的所有属性-----");
    Field[] selfFields = c.getDeclaredFields();

    for (int i = 0; i < selfFields.length; i++) {
      Field f = selfFields[i];
      String name = f.getName();
      Type type = f.getGenericType();
      String modifiers = Modifier.toString(f.getModifiers());
      System.out.println(modifiers + " " + type.toString() + " : " + name);
    }

    System.out.println("\n-----获取Hebei的所有公有属性,包含父类和实现的接口-----");
    Field[] fields = c.getFields();

    for (int i = 0; i < fields.length; i++) {
      Field f = fields[i];
      String name = f.getName();
      Type type = f.getGenericType();
      String modifiers = Modifier.toString(f.getModifiers());
      System.out.println(modifiers + " " + type.toString() + " : " + name);
    }
  }

  private static void invokeMethod(Class c) {
    Constructor<Hebei> constructor = null;

    try {
      System.out.println("\n-----通过class生成对象实例-----");
      constructor = c.getDeclaredConstructor(String.class, int.class, String.class);
      Hebei hb = constructor.newInstance("河北", 100000000, "北方");

      System.out.println("\n-----通过class调用Hebei的接口-----");
      Method method = c.getDeclaredMethod("toString");
      System.out.println((String) (method.invoke(hb)));

      Method setName = c.getDeclaredMethod("setName", String.class);
      setName.invoke(hb, "HeBei");
      System.out.println((String)(method.invoke(hb)));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}