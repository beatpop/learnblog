package com.bp.learnblog.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis 操作工具类
 *
 * @author DH
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    public RedisUtil() {
    }

    //======================== String =======================

    /**
     * 设置字符串键值对
     *
     * @param key 键
     * @param value 值
     * @return
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
     * 根据键获取值
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置带过期时间的键值对(过期时间单位/s)
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean setEx(String key, Object value, long expireTime) {
        try {
            if (expireTime > 0) {
                redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
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
     * 增量增加数值
     *
     * @param key
     * @param increment
     * @return
     * @throws Exception
     */
    public Long incrBy(String key, long increment) throws Exception {
        if (increment < 0) {
            throw new RuntimeException("增量值不可小于0");
        }
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 减量减少数值
     *
     * @param key
     * @param decrease
     * @return
     * @throws Exception
     */
    public Long decrBy(String key, long decrease) throws Exception {
        if (decrease < 0) {
            throw new RuntimeException("减量值不可小于0");
        }
        return redisTemplate.opsForValue().increment(key, decrease);
    }

    /**
     * 获取多个键值
     *
     * @param keys
     * @return
     */
    public List mGet(String ...keys) {
        if (keys.length <= 0) {
            return null;
        } else if (keys.length == 1) {
            return Collections.singletonList(get(keys[0]));
        } else {
            return redisTemplate.opsForValue().multiGet(CollectionUtils.arrayToList(keys));
        }
    }

    /**
     * 删除键值
     *
     * @param key
     * @return Long
     */
    public Long del(String ...key) {
        if (key != null && key.length > 0) {
            return redisTemplate.delete(CollectionUtils.arrayToList(key));
        }
        return 0L;
    }

    /**
     * 判断Key是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    //======================= Hash ===================

    /**
     * 获取hash值
     *
     * @param hashKey
     * @param itemKey
     * @return
     */
    public Object hGet(String hashKey, Object itemKey) {
        return redisTemplate.opsForHash().get(hashKey, itemKey);
    }

    /**
     * 新增hash值
     *
     * @param hashKey
     * @param itemKey
     * @param value
     * @return
     */
    public Boolean hSet(String hashKey, Object itemKey, Object value) {
        try {
            redisTemplate.opsForHash().put(hashKey, itemKey, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除 hash 值
     *
     * @param hashKey
     * @param itemKey
     * @return
     */
    public long hDel(String hashKey, Object ...itemKey) {
        return redisTemplate.opsForHash().delete(hashKey, itemKey);
    }

    /**
     * 判断是否存在 hash 值
     *
     * @param hashKey
     * @param itemKey
     * @return
     */
    public boolean hExists(String hashKey, Object itemKey) {
        return redisTemplate.opsForHash().hasKey(hashKey, itemKey);
    }

    /**
     * 设置 Key 的过期时间
     *
     * @param key 键
     * @param expireTime 过期时间（单位/s）
     * @return Boolean
     */
    public Boolean expire(String key, long expireTime) {
        return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 获取剩余过期时间（单位/s）
     *
     * @param key 键
     * @return Long
     */
    public Long getExpireLeft(String key) {
        return redisTemplate.getExpire(key);
    }
}
