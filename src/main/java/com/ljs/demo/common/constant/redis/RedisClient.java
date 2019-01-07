package com.ljs.demo.common.constant.redis;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class RedisClient {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加key到redis数据库
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) throws Exception {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
    }

    /**
     * 取值key从redis数据库
     *
     * @param key
     */
    public Object get(String key) throws Exception {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 根据指定的key删除数据
     *
     * @param key
     */
    public void del(String key) throws Exception {
        redisTemplate.delete(key);
    }

    /**
     * 添加obj对象到redis数据库
     *
     * @param obj
     */
    public void setObj(Object obj) {
        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        operations.set(obj.getClass().getName(), obj);
    }

    /**
     * 取值obj对象从redis数据库
     *
     * @param obj
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getObj(Object obj, Class<T> tClass) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return (T) operations.get(obj.getClass().getName());
    }

    /**
     * 删除obj对象
     *
     * @param obj
     */
    public void delObj(Object obj) {
        redisTemplate.delete(obj);
    }

    /**
     * Set集合的赋值去取
     *
     * @param key
     * @param value
     */
    public void setSetCollections(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public String getSetCollections(String key) {
        String result = new Gson().toJson(redisTemplate.opsForSet().members(key));
        return result.substring(1, result.length() - 1);
    }

    public Set<String> getMapKey(String key) {
        Set<String> resultMapSet = redisTemplate.opsForHash().keys(key);
        return resultMapSet;
    }

    /**
     * Map集合的赋值去取
     *
     * @param key
     * @param value
     */
    public void setMapCollections(String key, Map<String, Object> value) {
        redisTemplate.opsForHash().putAll(key, value);
    }

    public String getMapCollections(String key) {
        return new Gson().toJson(redisTemplate.opsForHash().entries(key));
    }

    /**
     * List集合去取
     *
     * @param key
     * @param list
     */
    public void setLists(String key, List list) {
        redisTemplate.opsForList().leftPush(key, list);
    }

    public String getListStartEnd(String key, int start, int end) {
        String result = new Gson().toJson(redisTemplate.opsForList().range(key, start, end));
        return result.substring(1, result.length() - 1);
    }

    /**
     * 查看key的剩余时间
     */
    public long getKeyExpireTime(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断key是否存在
     */
    public boolean exitsKey(String key) {
        Object obj = redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
        boolean flag = true;
        if (obj.toString().equals("false")) {
            return false;
        }
        return flag;
    }

}
