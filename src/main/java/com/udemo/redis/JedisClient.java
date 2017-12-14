package com.udemo.redis;

import com.udemo.utils.serialize.SerializeUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Pipeline;
import redis.clients.util.SafeEncoder;
import org.apache.commons.lang3.StringUtils;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Desc: redis工具类
 * User: hansh
 * Date: 2017/12/12
 * Time: 17:58
 */
public class JedisClient implements CacheClient {

    private JedisPool jedisPool;

    private String version;

    public JedisClient() {
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    protected Jedis getResource() {
        return this.jedisPool.getResource();
    }

    protected void returnResource(Jedis resource, boolean broken) {
        if (broken) {
            this.jedisPool.returnBrokenResource(resource);
        } else {
            this.jedisPool.returnResource(resource);
        }

    }

    protected String buildKeyByVersion(String key) {
        return StringUtils.isNotBlank(key) && StringUtils.isNotBlank(this.version) && !key.startsWith(this.version + "_") ? this.version + "_" + key : key;
    }

    public <T> boolean set(String key, T value) {
        byte[] valueBytes = SerializeUtils.serialize(value);
        return this.set(key, valueBytes);
    }

    public boolean set(String key, byte[] value) {
        boolean broken = false;
        String result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.set(SafeEncoder.encode(this.buildKeyByVersion(key)), value);
        } catch (Exception var10) {
            broken = true;
        } finally {
            this.returnResource(jedis, broken);
        }

        return "OK".equals(result);
    }

    public <T> boolean set(String key, int seconds, T value) {
        byte[] valueBytes = SerializeUtils.serialize(value);
        return this.set(key, seconds, valueBytes);
    }

    public boolean set(String key, int seconds, byte[] value) {
        boolean broken = false;
        String result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.setex(SafeEncoder.encode(this.buildKeyByVersion(key)), seconds, value);
        } catch (Exception var11) {
            broken = true;
        } finally {
            this.returnResource(jedis, broken);
        }

        return "OK".equals(result);
    }

    public <T> T get(String key) {
        byte[] valueBytes = this.getBytes(key);
        return valueBytes == null ? null : (T) SerializeUtils.deserialize(valueBytes);
    }

    public byte[] getBytes(String key) {
        boolean broken = false;
        byte[] result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.get(SafeEncoder.encode(this.buildKeyByVersion(key)));
        } catch (Exception var9) {
            broken = true;
        } finally {
            this.returnResource(jedis, broken);
        }

        return result;
    }

    public <T> List<T> batchGet(String[] keys) {
        Jedis jedis = this.getResource();
        List plList = null;

        try {
            Pipeline resultList = jedis.pipelined();
            int length = keys.length;

            for (int index = 0; index < length; ++index) {
                String key = keys[index];
                resultList.get(SafeEncoder.encode(this.buildKeyByVersion(key)));
            }

            plList = resultList.syncAndReturnAll();
        } finally {
            this.returnResource(jedis, false);
        }

        ArrayList results = new ArrayList(plList.size());
        results.addAll((Collection) plList.stream().filter((var) -> {
            return var != null;
        }).map((var15) -> {
            return SerializeUtils.deserialize((byte[]) ((byte[]) var15));
        }).collect(Collectors.toList()));
        return results;
    }

    public boolean delete(String key) {
        Long result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.del(SafeEncoder.encode(this.buildKeyByVersion(key)));
        } finally {
            this.returnResource(jedis, false);
        }

        return result.longValue() == 1L;
    }

    public List<Long> batchDelete(String[] keys) {
        Jedis jedis = this.getResource();
        List plList = null;

        try {
            Pipeline resultList = jedis.pipelined();
            int object = keys.length;

            for (int var8 = 0; var8 < object; ++var8) {
                String key = keys[var8];
                resultList.del(SafeEncoder.encode(this.buildKeyByVersion(key)));
            }

            plList = resultList.syncAndReturnAll();
        } finally {
            this.returnResource(jedis, false);
        }

        ArrayList results = new ArrayList(plList.size());
        plList.stream().filter((var15) -> {
            return var15 != null;
        }).forEach(results::add);
        return results;
    }

    public boolean exists(String key) {
        boolean result = false;
        Jedis jedis = this.getResource();

        try {
            result = jedis.exists(SafeEncoder.encode(this.buildKeyByVersion(key))).booleanValue();
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    public boolean expire(String key, int seconds) {
        Long result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.expire(SafeEncoder.encode(this.buildKeyByVersion(key)), seconds);
        } finally {
            this.returnResource(jedis, false);
        }

        return result.longValue() == 1L;
    }

    public List<String> keys(String like) {
        ArrayList<String> list = null;
        Jedis jedis = this.getResource();

        try {
            Set set = jedis.keys(SafeEncoder.encode(this.buildKeyByVersion(like)));
            Iterator iterator = set.iterator();
            list = new ArrayList();

            while (iterator.hasNext()) {
                byte[] key = (byte[]) ((byte[]) iterator.next());
                list.add(SafeEncoder.encode(key));
            }
        } finally {
            this.returnResource(jedis, false);
        }

        return list;
    }

    public Long ttl(String key) {
        Long result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.ttl(SafeEncoder.encode(this.buildKeyByVersion(key)));
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    public Long llen(String key) {
        Long result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.llen(SafeEncoder.encode(this.buildKeyByVersion(key)));
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    public Long incr(String key) {
        Long result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.incr(SafeEncoder.encode(this.buildKeyByVersion(key)));
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    public Long decrBy(String key, long integer) {
        Long result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.decrBy(SafeEncoder.encode(this.buildKeyByVersion(key)), integer);
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    public Long lpush(String key, Object value) {
        byte[] valueBytes = SerializeUtils.serialize(value);
        return this.lpush(key, valueBytes);
    }

    public Long lpush(String key, byte[] value) {
        Long result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.lpush(SafeEncoder.encode(this.buildKeyByVersion(key)), new byte[][]{value});
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    public Long rpush(String key, Object value) {
        byte[] valueBytes = SerializeUtils.serialize(value);
        return this.lpush(key, valueBytes);
    }

    public Long rpush(String key, byte[] value) {
        Long result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.rpush(SafeEncoder.encode(this.buildKeyByVersion(key)), new byte[][]{value});
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    public Object lpop(String key) {
        byte[] bytes = this.lpopBytes(key);
        return SerializeUtils.deserialize(bytes);
    }

    public byte[] lpopBytes(String key) {
        Jedis jedis = this.getResource();

        byte[] result;
        try {
            result = jedis.lpop(SafeEncoder.encode(this.buildKeyByVersion(key)));
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    public Object rpop(String key) {
        byte[] bytes = this.rpopBytes(key);
        return SerializeUtils.deserialize(bytes);
    }

    public byte[] rpopBytes(String key) {
        Jedis jedis = this.getResource();

        byte[] result;
        try {
            result = jedis.rpop(SafeEncoder.encode(this.buildKeyByVersion(key)));
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    public List<Object> lrange(String key, int start, int end) {
        List list = null;
        Jedis jedis = this.getResource();

        try {
            list = jedis.lrange(SafeEncoder.encode(this.buildKeyByVersion(key)), (long) start, (long) end);
        } finally {
            this.returnResource(jedis, false);
        }

        ArrayList oList = new ArrayList(list.size());
        Iterator var7 = list.iterator();

        while (var7.hasNext()) {
            Object aList = var7.next();
            byte[] ba = (byte[]) ((byte[]) aList);
            oList.add(SerializeUtils.deserialize(ba));
        }

        return oList;
    }

    public Long hset(String key, String field, Object value) {
        byte[] valueBytes = SerializeUtils.serialize(value);
        return this.hsetBytes(key, field, valueBytes);
    }

    public Object hget(String key, String field) {
        byte[] bytes = this.hgetBytes(key, field);
        return SerializeUtils.deserialize(bytes);
    }

    public List<Object> hmget(String key, String... fields) {
        List list = this.hmgetBytes(key, fields);
        ArrayList<Object> oList = new ArrayList(list.size());
        Iterator var5 = list.iterator();

        while (var5.hasNext()) {
            Object aList = var5.next();
            byte[] ba = (byte[]) ((byte[]) aList);
            oList.add(SerializeUtils.deserialize(ba));
        }

        return oList;
    }

    public Map<String, Object> hgetAll(String key) {
        Map map = this.hgetAllBytes(key);
        HashMap<String, Object> omap = new HashMap();
        Iterator var4 = map.keySet().iterator();

        while (var4.hasNext()) {
            Object o = var4.next();
            byte[] mapKey = (byte[]) ((byte[]) o);
            omap.put(SafeEncoder.encode(mapKey), SerializeUtils.deserialize((byte[]) ((byte[]) map.get(mapKey))));
        }

        return omap;
    }

    public boolean hexists(String key, String field) {
        boolean result = false;
        Jedis jedis = this.getResource();

        try {
            result = jedis.hexists(key, field).booleanValue();
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    public Set<String> hkeys(String key) {
        Set<String> result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.hkeys(key);
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    public Long hdel(String key, String field) {
        Long result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.hdel(key, new String[]{field});
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    private Long hsetBytes(String key, String field, byte[] value) {
        Long result = 0L;
        Jedis jedis = this.getResource();

        try {
            result = jedis.hset(SafeEncoder.encode(key), SafeEncoder.encode(field), value);
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    private byte[] hgetBytes(String key, String field) {
        Jedis jedis = this.getResource();

        byte[] result;
        try {
            result = jedis.hget(SafeEncoder.encode(key), SafeEncoder.encode(field));
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    private List<byte[]> hmgetBytes(String key, String... fields) {
        byte[][] byteFields = new byte[fields.length][];

        for (int result = 0; result < fields.length; ++result) {
            byteFields[result] = SafeEncoder.encode(fields[result]);
        }

        List<byte[]> results = null;
        Jedis jedis = this.getResource();

        try {
            results = jedis.hmget(SafeEncoder.encode(this.buildKeyByVersion(key)), byteFields);
        } finally {
            this.returnResource(jedis, false);
        }

        return results;
    }

    private Map<byte[], byte[]> hgetAllBytes(String key) {
        Map<byte[], byte[]> result = null;
        Jedis jedis = this.getResource();

        try {
            result = jedis.hgetAll(SafeEncoder.encode(this.buildKeyByVersion(key)));
        } finally {
            this.returnResource(jedis, false);
        }

        return result;
    }

    public <T> boolean publish(String channel, T message) {
        Jedis jedis = this.getResource();
        Long result = 0L;

        try {
            result = jedis.publish(SafeEncoder.encode(channel), SerializeUtils.serialize(message));
        } finally {
            this.returnResource(jedis, false);
        }

        return result.longValue() != 0L;
    }

    public void psubscribe(JedisPubSub jedisPubSub, String[] patterns) {
        Jedis jedis = this.getResource();

        try {
            jedis.psubscribe(jedisPubSub, patterns);
        } finally {
            this.returnResource(jedis, false);
        }

    }

    public void flushAll() {
        Jedis jedis = this.getResource();
        jedis.flushAll();
    }
}
