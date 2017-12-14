package com.udemo.utils.cache;

import com.udemo.redis.CacheClient;
import com.udemo.utils.application.SpringContextUtils;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc:
 * User: hansh
 * Date: 2017/12/13
 * Time: 11:04
 */
public class CacheUtils {

    public static CacheClient cacheClient = SpringContextUtils.getBean(CacheClient.class);

    public CacheUtils() {
    }

    public static <T> boolean set(String key, T value) {
        try {
            return cacheClient.set(key, value);
        } catch (JedisConnectionException var3) {
            return false;
        }
    }

    public static <T> boolean set(String key, int expiredTime, T value) {
        try {
            return cacheClient.set(key, expiredTime, value);
        } catch (JedisConnectionException var4) {
            return false;
        }
    }

    public static <T> T get(String key) {
        try {
            return cacheClient.get(key);
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static <T> List<T> batchGet(String... keys) {
        try {
            return keys != null && keys.length != 0 ? cacheClient.batchGet(keys) : null;
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static <T> List<T> batchGet(List<String> keyList) {
        try {
            if (keyList.isEmpty()) {
                return null;
            } else {
                String[] e = new String[keyList.size()];
                keyList.toArray(e);
                return batchGet(e);
            }
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static boolean delete(String key) {
        try {
            return cacheClient.delete(key);
        } catch (JedisConnectionException var2) {
            return false;
        }
    }

    public static List<Long> batchDelete(String... keys) {
        try {
            return keys != null && keys.length != 0 ? cacheClient.batchDelete(keys) : null;
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static Object batchDelete(List<String> keyList) {
        try {
            if (keyList != null && !keyList.isEmpty()) {
                String[] e = new String[keyList.size()];
                keyList.toArray(e);
                return batchDelete(e);
            } else {
                return null;
            }
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static boolean exists(String key) {
        try {
            return cacheClient.exists(key);
        } catch (JedisConnectionException var2) {
            return false;
        }
    }

    public static boolean expire(String key, int expire) {
        try {
            return cacheClient.expire(key, expire);
        } catch (JedisConnectionException var3) {
            return false;
        }
    }

    public static List<String> keys(String like) {
        try {
            return cacheClient.keys(like);
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static Long ttl(String key) {
        try {
            return cacheClient.ttl(key);
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static Long llen(String key) {
        try {
            return cacheClient.llen(key);
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static Long decrBy(String key, long integer) {
        try {
            return cacheClient.decrBy(key, integer);
        } catch (JedisConnectionException var4) {
            return null;
        }
    }

    public static Long incr(String key) {
        try {
            return cacheClient.incr(key);
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static Long lpush(String key, Object value) {
        try {
            return cacheClient.lpush(key, value);
        } catch (JedisConnectionException var3) {
            return null;
        }
    }

    public static Long rpush(String key, Object value) {
        try {
            return cacheClient.rpush(key, value);
        } catch (JedisConnectionException var3) {
            return null;
        }
    }

    public static <T> T lpop(String key) {
        try {
            return (T) cacheClient.lpop(key);
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static <T> T rpop(String key) {
        try {
            return (T) cacheClient.rpop(key);
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static <T> List<T> lrange(String key, int start, int end) {
        try {
            return (List<T>) cacheClient.lrange(key, start, end);
        } catch (JedisConnectionException var4) {
            return null;
        }
    }

    public static Long hset(String key, String field, Object value) {
        try {
            return cacheClient.hset(key, field, value);
        } catch (JedisConnectionException var4) {
            return null;
        }
    }

    public static Object hget(String key, String field) {
        try {
            return cacheClient.hget(key, field);
        } catch (JedisConnectionException var3) {
            return null;
        }
    }

    public static List<Object> hmget(String key, String... fields) {
        return cacheClient.hmget(key, fields);
    }

    public static boolean hexists(String key, String field) {
        try {
            return cacheClient.hexists(key, field);
        } catch (Exception var3) {
            return false;
        }
    }

    public static Set<String> hkeys(String key) {
        try {
            return cacheClient.hkeys(key);
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static Map<String, Object> hgetAll(String key) {
        try {
            return cacheClient.hgetAll(key);
        } catch (JedisConnectionException var2) {
            return null;
        }
    }

    public static Long hdel(String key, String field) {
        try {
            return cacheClient.hdel(key, field);
        } catch (JedisConnectionException var3) {
            return null;
        }
    }

    public static <T> boolean publish(String channel, T message) {
        return cacheClient.publish(channel, message);
    }

    public static void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        cacheClient.psubscribe(jedisPubSub, patterns);
    }

    public static void flushAll() {
        cacheClient.flushAll();
    }
}
