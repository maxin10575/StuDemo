package test;

import lombok.Data;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2021-07-16 16:15
 * @Modified By:
 * @Version: 1.0
 **/
@Data
public class User {
    private String id;
    private String name;
    private String age;



    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (age == null ? 0 : age.hashCode());
        return result;
    }

}
