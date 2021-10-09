package utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * @mx运知
 *  2021/04/08 14:14
 */
@Slf4j
public class JedisUtils {
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 20 * 1000;
    //超时时间
    private static int TIMEOUT = 30 * 1000;

    private static JedisPool jedisPool = null;

    public static void getInstance(String host, Integer port) {
        if (null == jedisPool) {
            try {
                getJedisPool(host, port);
            } catch (Exception e) {
                log.error("jedis[{}:{}]连接池初始化失败",host, port);
            }
        }
    }

    /**
     * 获取jedispool连接池
     * @param host
     * @param port
     * @return
     */
    private static JedisPool getJedisPool(String host, Integer port) throws Exception {
        JedisPoolConfig config = getJedisPoolConfig();
        jedisPool = new JedisPool(config, host, port, TIMEOUT);
        return jedisPool;
    }

    /**
     * 获取连接池配置
     * @return
     */
    private static JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数，如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxTotal(8);
        //最大空闲数，控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
        config.setMaxIdle(8);
        //最小空闲数
        config.setMinIdle(0);
        //是否在从池中取出连接前进行检验，如果检验失败，则从池中去除连接并尝试取出另一个
        config.setTestOnBorrow(true);
        //在return给pool时，是否提前进行validate操作
        config.setTestOnReturn(true);
        //在空闲时检查有效性，默认false
        config.setTestWhileIdle(true);
        //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；
        //这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        config.setMinEvictableIdleTimeMillis(30000);
        //表示idle object evitor两次扫描之间要sleep的毫秒数
        config.setTimeBetweenEvictionRunsMillis(60000);
        //表示idle object evitor每次扫描的最多的对象数
        config.setNumTestsPerEvictionRun(1000);
        //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(MAX_WAIT);
        return config;
    }

    /**
     * 同步获取Jedis实例
     *
     * @return Jedis
     */
    private static Jedis getJedis() {
        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            log.error("同步获取Jedis实例失败" + e.getMessage(), e);
            returnBrokenResource(jedis);
        }

        return jedis;
    }

    /**
     * 设置值
     *
     * @param key
     * @param value
     * @return -5：Jedis实例获取失败<br/>OK：操作成功<br/>null：操作失败
     */
    public static String set(String key, String value) {
        Jedis jedis = getJedis();
        if(jedis == null){
            return null;
        }

        String result = null;
        try {
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("设置值失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }

        return result;
    }

    /**
     * 缓存Map赋值
     *
     * @param key
     * @param field
     * @param value
     * @return -5：Jedis实例获取失败
     * @author jqlin
     */
    public static long hset(String key, String field, String value) {
        long result = -1L;
        Jedis jedis = getJedis();
        if(jedis == null){
            return result;
        }

        try {
            result = jedis.hset(key, field, value);
        } catch (Exception e) {
            log.error("缓存Map赋值失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }

        return result;
    }


    /**
     * 获取缓存的Map值
     *
     * @param key
     * @return
     */
    public static String hget(String key, String field){
        Jedis jedis = getJedis();
        if(jedis == null){
            return null;
        }

        String result = null;
        try {
            result = jedis.hget(key, field);
        } catch (Exception e) {
            log.error("获取缓存的Map值失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }

        return result;
    }

    public static Long hdel(String key, String field){
        Long result = -1L;
        Jedis jedis = getJedis();
        if(jedis == null){
            return result;
        }

        try {
            result = jedis.hdel(key, field);
        } catch (Exception e) {
            log.error("获取缓存的Map值失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }

        return result;
    }

    /**
     * 获取map所有的字段和值
     *
     * @param key
     * @return
     * @author jqlin
     */
    public static Map<String, String> hgetAll(String key){
        Map<String, String> map = new HashMap<String, String>();

        Jedis jedis = getJedis();
        if(jedis == null){
            log.warn("Jedis实例获取为空");
            return map;
        }

        try {
            map = jedis.hgetAll(key);
        } catch (Exception e) {
            log.error("获取map所有的字段和值失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }

        return map;
    }

    /**
     * 查看哈希表 key 中，指定的field字段是否存在。
     *
     * @param key
     * @param field
     * @return
     * @author jqlin
     */
    public static Boolean hexists(String key, String field){
        Jedis jedis = getJedis();
        if(jedis == null){
            log.warn("Jedis实例获取为空");
            return false;
        }

        try {
            if (!jedis.exists(key)) {
                return false;
            }
            return jedis.hexists(key, field);
        } catch (Exception e) {
            log.error("查看哈希表field字段是否存在失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }

        return false;
    }

    /**
     * 获取所有哈希表中的字段
     *
     * @param key
     * @return
     * @author jqlin
     */
    public static Set<String> hkeys(String key) {
        Set<String> set = new HashSet<String>();
        Jedis jedis = getJedis();
        if(jedis == null){
            log.warn("Jedis实例获取为空");
            return set;
        }

        try {
            return jedis.hkeys(key);
        } catch (Exception e) {
            log.error("获取所有哈希表中的字段失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }

        return null;
    }

    /**
     * 获取所有哈希表中的值
     *
     * @param key
     * @return
     * @author jqlin
     */
    public static List<String> hvals(String key) {
        List<String> list = new ArrayList<String>();
        Jedis jedis = getJedis();
        if(jedis == null){
            log.warn("Jedis实例获取为空");
            return list;
        }

        try {
            return jedis.hvals(key);
        } catch (Exception e) {
            log.error("获取所有哈希表中的值失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }

        return null;
    }

    /**
     * 设置值
     *
     * @param key
     * @param value
     * @param expire 过期时间，单位：秒
     * @return -5：Jedis实例获取失败<br/>OK：操作成功<br/>null：操作失败
     * @author jqlin
     */
    public static String set(String key, String value, int expire) {
        Jedis jedis = getJedis();
        if(jedis == null){
            return null;
        }

        String result = null;
        try {
            result = jedis.set(key, value);
            jedis.expire(key, expire);
        } catch (Exception e) {
            log.error("设置值失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 在列表key的头部插入元素
     *
     * @param key
     * @param values -1：Jedis实例获取失败，>0：返回操作成功的条数，0：失败
     * @return
     * @author jqlin
     */
    public static long lpush(String key, String... values) {
        long result = -1L;
        Jedis jedis = getJedis();
        if(jedis == null){
            return result;
        }

        try {
            result = jedis.lpush(key, values);
            log.debug("队列消息发送成功");
        } catch (Exception e) {
            log.error("在列表key的头部插入元素失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向channel推送message
     * @param channel
     * @param message
     * @return
     */
    public static long publish(String channel, String message) {
        long result = -1L;
        Jedis jedis = getJedis();
        if(jedis == null){
            return result;
        }
        try {
            result = jedis.publish(channel, message);
            log.debug("消息推送成功");
        } catch (Exception e) {
            log.error("消息推送失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 在列表key的尾部插入元素
     *
     * @param key
     * @param values -5：Jedis实例获取失败，>0：返回操作成功的条数，0：失败
     * @return
     * @author jqlin
     */
    public static long rpush(String key, String... values) {
        long result = -1L;
        Jedis jedis = getJedis();
        if(jedis == null){
            return result;
        }

        try {
            result = jedis.rpush(key, values);
        } catch (Exception e) {
            log.error("在列表key的尾部插入元素失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }

        return result;
    }

    /**
     * 返回存储在key列表的特定元素
     *
     *
     * @param key
     * @param start 开始索引，索引从0开始，0表示第一个元素，1表示第二个元素
     * @param end 结束索引，-1表示最后一个元素，-2表示倒数第二个元素
     * @return redis client获取失败返回null
     * @author jqlin
     */
    public static List<String> lrange(String key, long start, long end) {
        Jedis jedis = getJedis();
        if(jedis == null){
            return null;
        }

        List<String> result = null;
        try {
            result = jedis.lrange(key, start, end);
        } catch (Exception e) {
            log.error("查询列表元素失败：" + e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnResource(jedis);
        }

        return result;
    }

    /**
     * 获取List缓存对象
     *
     * @param key
     * @param start
     * @param end
     * @return List<T> 返回类型
     * @author jqlin
     */
    public static <T> List<T> range(String key, long start, long end, Class<T> clazz){
        List<String> dataList = lrange(key, start, end);
        if(StringUtils.isEmpty(dataList)){
            return new ArrayList<T>();
        }

        return JSON.parseArray(dataList.toString(),clazz);
    }

    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    @SuppressWarnings("deprecation")
    public static void returnResource(final Jedis jedis) {
        if (jedis != null && jedisPool != null) {
            jedisPool.returnResource(jedis);
        }
    }

    @SuppressWarnings("deprecation")
    public static void returnBrokenResource(final Jedis jedis) {
        if (jedis != null && jedisPool != null) {
            jedisPool.returnBrokenResource(jedis);
        }
    }
}
