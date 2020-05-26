package com.mx;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @program: StuDemo
 * @description: put list
 * @author: maxin
 * @create: 2020-04-09 10:47
 * @Modified By:
 * @Version: 1.0
 **/
public class ListTest {

    @Test
    public void testRList() {
        Jedis jedis = JedisUtil.getJedis();

        //存储数据到列表中
  /*      Redis Lrange 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。
        其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。 你也可以使用负数下标，
        以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。*/
//  /将值value插入到列表key的表头。
        jedis.lpush("mlist", "Runoob");
        jedis.lpush("mlist", "Google");
        jedis.lpush("mlist", "Taobao");

        // 获取存储的数据并输出
        List<String> list = jedis.lrange("mlist", 0, 100);
        list.forEach(string -> {
            System.out.println("value：" + string);
        });

        JedisUtil.closeJedis(jedis);
    }
}
