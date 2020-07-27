package DesignPattern.principle.LiskovSubsitution;

/**
 * @Author maxin
 * @Date 2019-11-14 10:49
 * @ClassName Animal
 * @Description TODO
 * @Version 1.0
 **/
public class Animal {
    public static void main(String[] args) {
        Penguin penguin = new Penguin("企鹅",1);
        penguin.sleep();
    }
        private String name;
        private int id;
        public Animal(String myName, int myid) {
            name = myName;
            id = myid;
        }
        public void eat(){
            System.out.println(name+"正在吃");
        }
        protected void sleep(){
            System.out.println(name+"正在睡");
        }
        public void introduction() {
            System.out.println("大家好！我是"         + id + "号" + name + ".");
        }
}

class Penguin extends Animal {
    private String pname;
    private int pid;
    public Penguin(String pname,int pid) {
        super(pname, pid);
        this.pname = pname;
        this.pid = pid;
    }
}

class Mouse extends Animal {
    public Mouse(String myName, int myid) {
        super(myName, myid);
    }
}