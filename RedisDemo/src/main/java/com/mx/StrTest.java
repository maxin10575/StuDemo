package com.mx;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @program: StuDemo
 * @description: put string
 * @author: maxin
 * @create: 2020-04-09 10:46
 * @Modified By:
 * @Version: 1.0
 **/
public class StrTest {

    @Test
    public void testStr() {
        Jedis jedis = JedisUtil.getJedis();

        //设置 redis 字符串数据
//        jedis.set("mstr", "testStr");
        //设置key过期时间
//        jedis.setex("ma"+":"+"xin",1000,"ok");
        //key 存在  s秒
        jedis.setex("ma"+":"+"he",10,"ok1");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("ma:he"));

        JedisUtil.closeJedis(jedis);
    }
}
