package com.mx;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @program: StuDemo
 * @description: set test
 * @author: maxin
 * @create: 2020-04-09 13:48
 * @Modified By:
 * @Version: 1.0
 **/
public class SetTest {
    @Test
    public void testSet() {
        Jedis jedis = JedisUtil.getJedis();
///将member元素加入到集合key当中。
        jedis.sadd("set1", "name");
        jedis.sadd("set1", "age");

//移除集合中的member元素。
        jedis.srem("set1", "age");

//返回集合key中的所有成员。
        Set set = jedis.smembers("set1");

//判断元素是否是集合key的成员
        jedis.sismember("set1", "name");

//返回集合key的元素的数量
        jedis.scard("set1");

        jedis.sadd("set2", "name");
        jedis.sadd("set2", "age2");

//返回一个集合的全部成员，该集合是所有给定集合的交集
        jedis.sinter("set1","set2");

//返回一个集合的全部成员，该集合是所有给定集合的并集
        jedis.sunion("set1","set2");

//返回一个集合的全部成员，该集合是所有给定集合的差集
        jedis.sdiff("set1","set2");


        JedisUtil.closeJedis(jedis);
    }
}
