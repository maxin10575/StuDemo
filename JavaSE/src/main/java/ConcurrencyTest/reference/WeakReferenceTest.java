package ConcurrencyTest.reference;

import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
* @Description: 弱引用
* @Author: maxin
* @Date: 2020/3/12
* @Modified By:
* @Version: 1.0.0
*/
public class WeakReferenceTest {
    public static void main(String[] args) throws Exception {

        testSoftReferenceNoDirectReference();   // 有  有

//        testWeakReferenceNoDirectReference();  //有     null

//        testWeakReferenceWithDirectReference(); //有    有
    }

    /**
     * 测试SoftReference，SoftReference引用的对象并没有被其他对象直接引用
     */
    @Test
    private static void testSoftReferenceNoDirectReference() throws Exception {
        Object obj = new Object();
        SoftReference<Object> softRef = new SoftReference<>(obj);
        System.out.println(softRef.get());
        obj = null;
        System.gc();
        Thread.sleep(3000L);
        System.out.println(softRef.get());
    }


    /**
     * 测试WeakReference，WeakReference引用的对象并没有被其他对象直接引用
     */
    @Test
    private static void testWeakReferenceNoDirectReference() throws Exception {
        Object obj = new Object();
        WeakReference<Object> weakRef = new WeakReference<>(obj);
        System.out.println(weakRef.get());
        obj = null;
        System.gc();
        Thread.sleep(3000L);
        System.out.println(weakRef.get());
    }

    /**
     * 测试WeakReference，WeakReference引用的对象并没有被其他对象直接引用
     */
    @Test
    private static void testWeakReferenceWithDirectReference() throws Exception {
        Object obj = new Object();
        WeakReference<Object> weakRef = new WeakReference<>(obj);
        System.out.println(weakRef.get());
        List<Object> objectList = new ArrayList<>();
        objectList.add(obj);
        obj = null;
        System.gc();
        Thread.sleep(3000L);
        System.out.println(weakRef.get());
    }
}
