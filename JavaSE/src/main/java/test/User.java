package test;

import org.apache.commons.lang.StringUtils;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2021-07-16 16:15
 * @Modified By:
 * @Version: 1.0
 **/
public class User {
    private String id;
    private String name;
    private String age;

    public User() {

    }

    public User(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;//地址相等
        }

        if (obj == null) {
            return false;//非空性：对于任意非空引用x，x.equals(null)应该返回false。
        }

        if (obj instanceof User) {
            User other = (User) obj;
            //需要比较的字段相等，则这两个对象相等
            if (equalsStr(this.name, other.name)
                    && equalsStr(this.age, other.age)) {
                return true;
            }
        }
        return false;
    }

    private boolean equalsStr(String str1, String str2) {
        if (StringUtils.isEmpty(str1) && StringUtils.isEmpty(str2)) {
            return true;
        }
        if (!StringUtils.isEmpty(str1) && str1.equals(str2)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (age == null ? 0 : age.hashCode());
        return result;
    }

}
