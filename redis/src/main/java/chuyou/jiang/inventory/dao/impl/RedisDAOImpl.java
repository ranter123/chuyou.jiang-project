package chuyou.jiang.inventory.dao.impl;

import chuyou.jiang.inventory.dao.RedisDAO;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * @Author: ranter
 * @Date: 2020/10/7 1:14 下午
 * @Description:
 */
@Repository("redisDao")
public class RedisDAOImpl implements RedisDAO {

    @Resource
    private JedisCluster jedisCluster;

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    @Override
    public void delete(String key) {
        jedisCluster.del(key);
    }
}
