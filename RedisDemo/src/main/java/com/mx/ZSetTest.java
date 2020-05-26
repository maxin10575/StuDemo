package com.mx;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @program: StuDemo
 * @description: 有序集合edis中对于ZSet类型的: zadd 操作示例
 * zadd ：添加元素，格式是：zadd zset的key score值 项的值， Score和项可以是多对，
 * score可以是整数，也可以是浮点数，还可以是+inf表示无穷大，-inf表示负无穷大
 * @author: maxin
 * @create: 2020-04-09 13:56
 * @Modified By:
 * @Version: 1.0
 **/
public class ZSetTest {

    @Test
    public void ZTest() {
        Jedis jedis = JedisUtil.getJedis();

        Long zadd = jedis.zadd("myzset", 10.0, "z1");
        System.out.println("zadd = " + zadd);

        Map<String, Double> map = new HashMap<>();
        map.put("z2", 20.0);
        map.put("z3", 30.0);
        map.put("z4", 25.0);
        map.put("z5", 5.0);
        map.put("z6", 29.0);
        Long myzset = jedis.zadd("myzset", map);
        System.out.println("myzset = " + myzset);
        JedisUtil.closeJedis(jedis);
    }


    @Test
    public void ZTest2() {
        /**
         * zrange ：获取索引区间内的元素，格式是：zrange zset的key 起始索引 终止索引 （withscore）
         */
        Jedis jedis = JedisUtil.getJedis();
        //zrange = [z5, z1, z2, z4, z6, z3]
        Set<String> zrange = jedis.zrange("myzset", 0, -1);
        System.out.println("zrange = " + zrange);
        //zrangeWithScores = [[z5,5.0], [z1,10.0], [z2,20.0], [z4,25.0], [z6,29.0], [z3,30.0]]
        Set<Tuple> zrangeWithScores = jedis.zrangeWithScores("myzset", 0, -1);
        System.out.println("zrangeWithScores = " + zrangeWithScores);

        /**
         * zrangebyscore  zrangeByScoreWithScores 操作 ：
         * 获取分数区间内的元素，格式是：zrangebyscore zset的key
         * 起始score 终止score (withscore),默认是包含端点值的，
         * 如果加上"("表示不包含，后面还可以加上limit来限制。*/
        //        zrangeByScore = [z1, z2, z4]
        Set<String> zrangeByScore = jedis.zrangeByScore("myzset", 10.0, 25.0);
        System.out.println("zrangeByScore = " + zrangeByScore);
        //zrangeByScoreWithScores = [[z1,10.0], [z2,20.0], [z4,25.0]]
        Set<Tuple> zrangeByScoreWithScores = jedis.zrangeByScoreWithScores("myzset", 10.0, 25.0);
        System.out.println("zrangeByScoreWithScores = " + zrangeByScoreWithScores);

        /**
         * zrem ：删除元素，格式是：zrem zset的key 项的值，项的值可以是多个
         */
        Long zrem = jedis.zrem("myzset", "z1", "z2");
        System.out.println("zrem = " + zrem);

        /**
         * zcard ：获取集合中元素个数，格式是：zcard zset的key
         */
        Long zcard = jedis.zcard("myzset");
        System.out.println("zcard = " + zcard);
        /**
         zincrby ：增减元素的score，格式是：zincrby zset的key 正负数字 项的值
         */
        Double zincrby = jedis.zincrby("myzset", 12, "z1");
        System.out.println("zincrby = " + zincrby);
        /**
         zcount ： 获取分数区间内元素个数，格式是：zcount zset的key 起始score 终止score
         */
        Long zcount = jedis.zcount("myzset", 20.0, 50.0);
        System.out.println("zcount = " + zcount);
        /**
         * zrank : 获取项在zset中的索引，格式是：zrank zset的key 项的值
         */
        Long zrank = jedis.zrank("myzset", "z2");
        System.out.println("zrank = " + zrank);
        /**
         zscore ：获取元素的分数，格式是：zscore zset的key 项的值，返回项在zset中的score
         */
        Double zscore = jedis.zscore("myzset", "s2");
        System.out.println("zscore = " + zscore);

        /**
         * zrevrank ：获取项在zset中倒序的索引，格式是：zrevrank zset的key 项的值
         */

        Long zrevrank = jedis.zrevrank("myzset", "s2");
        System.out.println("zrevrank = " + zrevrank);

        /**
         * zrevrange ：获取索引区间内的元素，格式是：zrevrange zset的key 起始索引 终止索引（withscores）
         */
        Set<String> zrevrange = jedis.zrevrange("myzset", 0, -1);
        System.out.println("zrevrange = " + zrevrange);


        /**
         zrevrangebyscore：获取分数区间内的元素，
         格式是：zrevrangebyscore zset的key 终止score 起始score(withscores)
         */
        Set<String> zrevrangeByScore = jedis.zrevrangeByScore("myzset", 35.0, 15.0);
        System.out.println("zrevrangeByScore = " + zrevrangeByScore);
        Set<Tuple> zrevrangeByScoreWithScores = jedis.zrevrangeByScoreWithScores("myzset", 35.0, 15.0);
        System.out.println("zrevrangeByScoreWithScores = " + zrevrangeByScoreWithScores);

        /**
         * zremrangebyrank：删除索引区间内的元素，格式是：zremrangebyrank zset的key 起始索引 终止索引
         */
        Long zremrangeByScore = jedis.zremrangeByScore("myzset", 30, 40);
        System.out.println("zremrangeByScore = " + zremrangeByScore);

        /**
         zinterstore：交集，格式是：zinterstore dest-key key-count key[key ...][WEIGHTS weight [weight ...]] [AGGREGATE SUM|MIN|MAX]
         */
        Long zinterstore = jedis.zinterstore("myzset3", "myzset1", "myzset2");
        System.out.println("zinterstore = " + zinterstore);

        /**
         * zunionstore：并集，格式是：zunionstore dest-key key-count key[key ...][WEIGHTS weight [weight ...]] [AGGREGATE SUM|MIN|MAX]
         */
        Long zinterstore1 = jedis.zinterstore("myzset4", new ZParams().aggregate(ZParams.Aggregate.MAX), "myzset1", "myzset2");
        System.out.println("zinterstore1 = " + zinterstore1);

        JedisUtil.closeJedis(jedis);


    }

}
