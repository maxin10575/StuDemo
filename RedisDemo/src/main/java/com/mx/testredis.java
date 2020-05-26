package com.mx;

import redis.clients.jedis.Jedis;
import java.util.Set;

/**
 * @Author maxin
 * @Date 2019-12-13 16:27
 * @ClassName testredis
 * @Description
 * @Version 1.0
 **/
public class testredis {
    private static void testKeys() {
        Jedis jedis = JedisUtil.getJedis();
        jedis.del("key1", "key2", "key3");
        //移除给定key的生存时间(设置这个key永不过期)
        jedis.persist("key1");
        //检查给定key是否存在
        jedis.exists("key1");
        //将key改名为newkey,当key和newkey相同或者key不存在时,返回一个错误
        jedis.rename("key1", "key2");
        //返回key所储存的值的类型。
        //none(key不存在),string(字符串),list(列表),set(集合),zset(有序集),hash(哈希表)
        jedis.type("key1");
        //设置key生存时间，当key过期时，它会被自动删除。
        jedis.expire("key1", 5);//5秒过期
        //清空所有的key
        jedis.flushAll();

        //返回key的个数
        jedis.dbSize();
        //返回列表key的长度。
        jedis.llen("key1");


        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        keys.forEach(string -> {
            System.out.println("keys:" + keys);
        });
        JedisUtil.closeJedis(jedis);
    }
}

