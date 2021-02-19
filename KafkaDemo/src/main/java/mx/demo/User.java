package mx.demo;

/**
 * @program: KafkaTest
 * @description: 自定义序列化对象
 * @author: maxin
 * @create: 2020-03-11 14:05
 * @Modified By:
 * @Version: 1.0
 **/
public class User {

    private int id;
    private String name;
    private String number;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", number=" + number + ", age=" + age + "]";
    }

    public User(int id, String name, String number, int age) {
        super();
        this.id = id;
        this.name = name;
        this.number = number;
        this.age = age;
    }

    public User() {
        super();
    }

}