package com.udemo.redis;

import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc: 缓存工具
 * User: hansh
 * Date: 2017/12/12
 * Time: 17:57
 */
public interface CacheClient {

    <T> boolean set(String var1, T var2);

    boolean set(String var1, byte[] var2);

    <T> boolean set(String var1, int var2, T var3);

    boolean set(String var1, int var2, byte[] var3);

    <T> T  get(String var1);

    byte[] getBytes(String var1);

    <T> List<T> batchGet(String[] var1);

    boolean delete(String var1);

    List<Long> batchDelete(String[] var1);

    boolean exists(String var1);

    boolean expire(String var1, int var2);

    List<String> keys(String var1);

    Long ttl(String var1);

    Long llen(String var1);

    Long incr(String var1);

    Long decrBy(String var1, long var2);

    Long lpush(String var1, Object var2);

    Long lpush(String var1, byte[] var2);

    Long rpush(String var1, Object var2);

    Long rpush(String var1, byte[] var2);

    Object lpop(String var1);

    byte[] lpopBytes(String var1);

    Object rpop(String var1);

    byte[] rpopBytes(String var1);

    List<Object> lrange(String var1, int var2, int var3);

    Long hset(String var1, String var2, Object var3);

    Object hget(String var1, String var2);

    List<Object> hmget(String var1, String... var2);

    Map<String, Object> hgetAll(String var1);

    boolean hexists(String var1, String var2);

    Set<String> hkeys(String var1);

    Long hdel(String var1, String var2);

    <T> boolean publish(String var1, T var2);

    void psubscribe(JedisPubSub var1, String[] var2);

    void flushAll();
}
