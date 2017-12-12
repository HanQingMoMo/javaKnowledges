package Knowledge.SerialAndDeserial;

import java.io.*;

public class Test {
    private static String path = "/Users/hw/git/javaKnowledges/conf/SerialAndDeserial/Person.txt";

    public static void main(String[] args) throws Exception {
        SerializePerson();
        Person person = DeserializePersion();
        System.out.println(person.getAge() + " " + person.getName());
    }

    private static void SerializePerson() throws IOException {
        //要被序列化的对象
        Person person = new Person();
        person.setAge(10);
        person.setName("hanqing");

        //序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
        oos.writeObject(person);
        System.out.println("序列化成功");
        oos.close();
    }

    private static Person DeserializePersion() throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)));
        Person person = (Person) ois.readObject();
        System.out.println("反序列化成功");
        return person;
    }
}
