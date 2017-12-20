package Knowledge.Generics;

/**
 * @author hw 2017.12.20
 * 定义一个泛型类, 该类保存了一对key-value键值对
 * @param <K> 类型参数K
 * @param <V> 类型参数V
 */

public class GenericsClass<K, V> {
  private K k;
  private V v;

  public GenericsClass(K k, V v) {
    this.k = k;
    this.v = v;
  }

  public V getV() {
    return v;
  }

  public void setK(K k) {
    this.k = k;
  }

  public void setV(V v) {
    this.v = v;
  }

  public K getK() {
    return k;
  }

  public static void main(String[] args) {
    GenericsClass<String, String> gcss = new GenericsClass<>("xixi", "haha");
    GenericsClass<Integer, String> gcis = new GenericsClass<>(20, "hanwei");

    System.out.println("Type : Generics<String, String>, get key : " + gcss.getK());
    System.out.println("Type : Generics<Integer, String>, get value : " + gcis.getV());

    //在实例化泛型类或者给泛型类赋值时，可以使用通配符？
    GenericsClass<?, String> gc_s = new GenericsClass<String, String>("didi", "dada");
    System.out.println("Type : Generics<?, String>, get key : " + gc_s.getK());

    gc_s = new GenericsClass<Integer, String>(30, "zhenbang");
    System.out.println("Type : Generics<?, String>, get key : " + gc_s.getK());
  }
}
