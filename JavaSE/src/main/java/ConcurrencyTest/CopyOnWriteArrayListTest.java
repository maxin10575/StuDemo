package ConcurrencyTest;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/***
 * @Description: CopyOnWriteArrayList   线程安全，可以兼容读写并发
 *                                     耗内存（写时复制）； 数据实时性不高，可能获取到旧数据
 *                                      适用场景：1.读多写少，白名单黑名单
 *                                               2.集合数量不大
 *                                                 3.数据要求不是强实时
 * @Author: maxin
 * @Date: 2020/3/25
 * @Modified By:
 * @Version: 1.0.0
 */
public class CopyOnWriteArrayListTest {

    @Test
    public void testArrayListConcurrentModificationException() {
        List<Integer> integerList = new ArrayList<>();
        addElementWhenIterator(integerList);
    }

    private void addElementWhenIterator(List<Integer> integerList) {
        integerList.add(1);
        integerList.add(2);

        Iterator<Integer> iterator = integerList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            integerList.add(next + 100);
        }
        System.out.println(JSONObject.toJSONString(integerList));
    }

    @Test
    public void testCopyOnWriteArrayListConcurrentModificationException() {
        List<Integer> integerList = new CopyOnWriteArrayList<>();
        addElementWhenIterator(integerList);
    }


}
