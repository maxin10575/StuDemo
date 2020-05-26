package com.mx;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: StuDemo
 * @description: hset
 * @author: maxin
 * @create: 2020-04-09 10:44
 * @Modified By:
 * @Version: 1.0
 **/
public class HashTest {

    @Test
    public void testHash() {
        Jedis jedis = JedisUtil.getJedis();

        Map map = new HashMap();
        map.put("name", "mx");
        map.put("age", "11");
        jedis.hmset("k1", map);
        //或
        jedis.hset("k2", "name", "mx");
        jedis.hset("k2", "age", "12");

        //返回哈希表key中给定域field的值
        jedis.hget("key1", "field1");

        //返回哈希表key中给定域field的值(多个)
        List list = jedis.hmget("k2", "field1", "field2");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        //返回哈希表key中所有域和值
        Map<String, String> map1= jedis.hgetAll("key1");
        for (Map.Entry entry : map1.entrySet()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + "\t");
        }

//删除哈希表key中的一个或多个指定域
        jedis.hdel("key1", "field1");
        jedis.hdel("key1", "field1", "field2");

//查看哈希表key中，给定域field是否存在。
        jedis.hexists("key1", "field1");

//返回哈希表key中的所有域
        jedis.hkeys("k2");

//返回哈希表key中的所有值
        jedis.hvals("key1");
        JedisUtil.closeJedis(jedis);
    }

}
