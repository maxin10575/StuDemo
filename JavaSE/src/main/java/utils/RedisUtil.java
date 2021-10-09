package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * redisTemplate封装
 * </p>
 *
 * @author：mx
 * @date: 2020-09-08
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisUtil() {

    }


    /**
     * @param key
     * @Description: 新增 或者 对 zset中元素 值 + 1
     * @Param: * @param hotZSet
     * @exception/throws
     * @return: java.lang.Double
     * @Author: maxin
     * @Date: 2020/9/8
     */
    public Double incrementScore(String hotZSet, String key) {
        return redisTemplate.opsForZSet().incrementScore(hotZSet, key, 1);
    }


    /**
     * @param key
     * @param value
     * @Description: zset累加value值
     * @Param: * @param hotZSet
     * @exception/throws
     * @return: java.lang.Double
     * @Author: maxin
     * @Date: 2021/5/24
     */
    public Double incrementScore(String hotZSet, String key, int value) {
        return redisTemplate.opsForZSet().incrementScore(hotZSet, key, value);
    }

    /**
     * @param key
     * @Description: 获取zset key值
     * @Param: * @param hotZSet
     * @exception/throws
     * @return: java.lang.Object
     * @Author: maxin
     * @Date: 2021/5/24
     */
    public Double getKeyScore(String hotZSet, String key) {
        return redisTemplate.opsForZSet().score(hotZSet, key);
    }


    /**
     * @param start
     * @param end
     * @Description: 获取zset范围值内数据
     * @Param: * @param key
     * @exception/throws
     * @return: java.util.Set
     * @Author: maxin
     * @Date: 2020/9/8
     */
    public Set reverseRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }


    /**
     * @description: 指定缓存失效时间
     * @author：mx
     * @date：2020-09-08 13:03:52
     * @param: key 指定缓存失效时间
     * @param: time 时间(秒)
     * @return: boolean
     * @exception/throws
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 根据key 获取过期时间
     * @author：mx
     * @date：2020-09-08 13:04:29
     * @param: key 键 不能为null
     * @return: long 时间(秒) 返回0代表为永久有效
     * @exception/throws
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * @description: 判断key是否存在
     * @author：mx
     * @date：2020-09-08 13:05:07
     * @param: key 键
     * @return: boolean true 存在 false不存在
     * @exception/throws
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 删除缓存
     * @author：mx
     * @date：2020-09-08 13:05:46
     * @param: key 可以传一个值 或多个
     * @return: void
     * @exception/throws
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * @description: 普通缓存获取
     * @author：mx
     * @date：2020-09-08 13:06:30
     * @param: key 键
     * @return: java.lang.Object
     * @exception/throws
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * @description: 普通缓存放入
     * @author：mx
     * @date：2020-09-08 13:06:55
     * @param: key 键
     * @param: value 值
     * @return: boolean true成功 false失败
     * @exception/throws
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 普通缓存放入并设置时间
     * @author：mx
     * @date：2020-09-08 13:07:41
     * @param: key 键
     * @param: value 值
     * @param: time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return: boolean true成功 false 失败
     * @exception/throws
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 递增
     * @author：mx
     * @date：2020-09-08 13:08:20
     * @param: key 键
     * @param: delta 要增加几(大于0)
     * @return: long
     * @exception/throws
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * @description: 递减
     * @author：mx
     * @date：2020-09-08 13:08:49
     * @param: key 键
     * @param: delta 要减少几(小于0)
     * @return: long
     * @exception/throws
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * @description:
     * @author：mx
     * @date：2020-09-08 13:09:27
     * @param: key 键 不能为null
     * @param: item 项 不能为null
     * @return: java.lang.Object
     * @exception/throws
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * @description: 获取hashKey对应的所有键值
     * @author：mx
     * @date：2020-09-08 13:10:21
     * @param: key 键
     * @return: 对应的多个键值
     * @exception/throws
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * @description: HashSet
     * @author：mx
     * @date：2020-09-08 13:10:51
     * @param: key 键
     * @param: map 对应多个键值
     * @return: boolean true 成功 false 失败
     * @exception/throws
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: HashSet 并设置时间
     * @author：mx
     * @date：2020-09-08 13:11:23
     * @param: key 键
     * @param: map 对应多个键值
     * @param: time 时间(秒)
     * @return: boolean true成功 false失败
     * @exception/throws
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 向一张hash表中放入数据, 如果不存在将创建
     * @author：mx
     * @date：2020-09-08 13:12:12
     * @param: key 键
     * @param: item 项
     * @param: value 值
     * @return: boolean true 成功 false失败
     * @exception/throws
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 向一张hash表中放入数据, 如果不存在将创建
     * @author：mx
     * @date：2020-09-08 13:12:43
     * @param: key 键
     * @param: item 项
     * @param: value 值
     * @param: time 时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return: boolean true 成功 false失败
     * @exception/throws
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 删除hash表中的值
     * @author：mx
     * @date：2020-09-08 13:13:29
     * @param: key 键 不能为null
     * @param: item 项 可以使多个 不能为null
     * @return: void
     * @exception/throws
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * @description: 判断hash表中是否有该项的值
     * @author：mx
     * @date：2020-09-08 13:13:53
     * @param: key 键 不能为null
     * @param: item 项 不能为null
     * @return: boolean true 存在 false不存在
     * @exception/throws
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * @description: hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @author：mx
     * @date：2020-09-08 13:14:34
     * @param: key 键
     * @param: item 项
     * @param: by 要增加几(大于0)
     * @return: double
     * @exception/throws
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * @description: hash递减
     * @author：mx
     * @date：2020-09-08 13:15:05
     * @param: key 键
     * @param: item 项
     * @param: by 要减少记(小于0)
     * @return: double
     * @exception/throws
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * @description: 根据key获取Set中的所有值
     * @author：mx
     * @date：2020-09-08 13:15:34
     * @param: key 键
     * @return: java.util.Set<java.lang.Object>
     * @exception/throws
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description: 根据value从一个set中查询, 是否存在
     * @author：mx
     * @date：2020-09-08 13:15:55
     * @param: key 键
     * @param: value 值
     * @return: boolean true 存在 false不存在
     * @exception/throws
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 将数据放入set缓存
     * @author：mx
     * @date：2020-09-08 13:16:29
     * @param: key 键
     * @param: values 值 可以是多个
     * @return: long 成功个数
     * @exception/throws
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @description: 将set数据放入缓存
     * @author：mx
     * @date：2020-09-08 13:17:13
     * @param: key 键
     * @param: time 值
     * @param: values 值 可以是多个
     * @return: long 成功个数
     * @exception/throws
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param list
     * @Description: 使用pipelined批量向set中添加值
     * @Param: * @param key
     * @exception/throws
     * @return: java.lang.Object
     * @Author: maxin
     * @Date: 2021/5/8
     */
    public Object sSet(String key, final List<String> list) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        final byte[] rawKey = jackson2JsonRedisSerializer.serialize(key);
        redisTemplate.executePipelined(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (String str : list) {
                    connection.sAdd(rawKey, jackson2JsonRedisSerializer.serialize(str));
                }
                connection.closePipeline();
                return null;
            }
        });
        return null;
    }


    /**
     * 功能描述: 使用pipelined批量存储
     *
     * @param: [map, seconds]
     * @return: void
     * @auther: liyiyu
     * @date: 2020/4/19 14:34
     */
    public void executePipelined(Map map, long seconds) {
        RedisSerializer serializer = redisTemplate.getStringSerializer();
        redisTemplate.executePipelined(new RedisCallback() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                map.forEach((key, value) -> {
                    connection.set(serializer.serialize(key), serializer.serialize(value), Expiration.seconds(seconds), RedisStringCommands.SetOption.UPSERT);
                });
                return null;
            }
        }, serializer);
    }

    /**
     * @description: 获取set缓存的长度
     * @author：mx
     * @date：2020-09-08 13:17:52
     * @param: key 键
     * @return: long
     * @exception/throws
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @description: 移除值为value的
     * @author：mx
     * @date：2020-09-08 13:18:20
     * @param: key 键
     * @param: values 值 可以是多个
     * @return: long 移除的个数
     * @exception/throws
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @description: 获取list缓存的内容
     * @author：mx
     * @date：2020-09-08 13:19:00
     * @param: key 键
     * @param: start 开始
     * @param: end 结束 0 到 -1代表所有值
     * @return: java.util.List<java.lang.Object>
     * @exception/throws
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description: 获取list缓存的长度
     * @author：mx
     * @date：2020-09-08 13:19:32
     * @param: key 键
     * @return: long
     * @exception/throws
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @description: 通过索引 获取list中的值
     * @author：mx
     * @date：2020-09-08 13:19:53
     * @param: key 键
     * @param: index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return: java.lang.Object
     * @exception/throws
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description: 将list放入缓存
     * @author：mx
     * @date：2020-09-08 13:20:27
     * @param: key 键
     * @param: value 值
     * @return: boolean
     * @exception/throws
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 将list放入缓存
     * @author：mx
     * @date：2020-09-08 13:20:52
     * @param: key 键
     * @param: value 值
     * @param: time 时间(秒)
     * @return: boolean
     * @exception/throws
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 将list放入缓存
     * @author：mx
     * @date：2020-09-08 13:21:26
     * @param: key 键
     * @param: value 值
     * @return: boolean
     * @exception/throws
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 将list放入缓存
     * @author：mx
     * @date：2020-09-08 13:21:48
     * @param: key 键
     * @param: value 值
     * @param: time 时间(秒)
     * @return: boolean
     * @exception/throws
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description:
     * @author：mx
     * @date：2020-09-08 13:23:37
     * @param: key 键
     * @param: index 索引
     * @param: value 值
     * @return: boolean
     * @exception/throws
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 移除N个值为value
     * @author：mx
     * @date：2020-09-08 13:24:11
     * @param: key 键
     * @param: count 移除多少个
     * @param: value 值
     * @return: long 移除的个数
     * @exception/throws
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
