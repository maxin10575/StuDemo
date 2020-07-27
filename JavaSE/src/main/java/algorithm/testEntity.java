package algorithm;

/**
 * @Author maxin
 * @Date 2019-09-04 12:21
 * @ClassName testEntity
 * @Description
 * @Version 1.0
 **/
public class testEntity implements Comparable<testEntity>{
    private String name ;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(testEntity o) {
        return this.id - o.id;
    }
}
