package com.mx.distributid;

import com.mx.JedisUtil;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @program: StuDemo
 * @description:
 * @author: maxin
 * @create: 2020-04-10 15:57
 * @Modified By:
 * @Version: 1.0
 **/
public class FormNoGenerateServiceImpl implements FormNoGenerateService{
    /**
     * redis 服务
     * demo 项目没有加redis相关
     */

    Jedis redisCache = JedisUtil.getJedis();
    /**
     * 根据单据编号类型 生成单据编号
     *
     * @param formNoTypeEnum 单据编号类型
     * @author mengqiang
     * @date 2019/1/1
     */
    @Override
    public String generateFormNo(FormNoTypeEnum formNoTypeEnum) {
        //获得单号前缀
        //格式 固定前缀 +时间前缀 示例 ：YF20190101
        String formNoPrefix = FormNoSerialUtil.getFormNoPrefix(formNoTypeEnum);
        //获得缓存key
        String cacheKey = FormNoSerialUtil.getCacheKey(formNoPrefix);
        //获得当日自增数
        Long incrementalSerial = redisCache.incr(cacheKey);
        //设置失效时间 7天
//        redisCache.expire(cacheKey, FormNoConstants.DEFAULT_CACHE_DAYS, TimeUnit.DAYS);
        redisCache.expire(cacheKey, FormNoConstants.DEFAULT_CACHE_DAYS);
        //组合单号并补全流水号
        String serialWithPrefix = FormNoSerialUtil
                .completionSerial(formNoPrefix, incrementalSerial, formNoTypeEnum);
        JedisUtil.closeJedis(redisCache);
        //补全随机数
        return FormNoSerialUtil.completionRandom(serialWithPrefix, formNoTypeEnum);

    }
}
