package Knowledge.SerialAndDeserial;

import java.io.Serializable;

/**
 * @author hw
 * date 2017.12.12
 */
public class Person implements Serializable {
    //in mac : 光标放到类名上，按住control+enter键，生成serialVersionUID
    //反序列化时，会通过比较serialVersionUID来验证版本一致性，若版本不一致，则会拒绝反序列化
    private static final long serialVersionUID = -1383995810937209003L;
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
