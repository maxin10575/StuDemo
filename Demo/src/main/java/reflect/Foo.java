package reflect;

public class Foo {

    private int book ;
    public Foo() {

    }

    @annoTest
    public void who() {
        System.out.println("who()method..."+book);
    }

    public void who(Integer a) {
        System.out.println("who(Integer)method...." + a);
    }

    public int who1(Integer a) {
        System.out.println("who111111(Integer)method..." + a);
        return a;
    }

    //private修饰的方法无法被反射发现
    private void test(int n) {
        System.out.println(n);
    }

    private void test(String s, int n) {
        System.out.println(s + n);
    }
}
