package chuyou.jiang.inventory.dao;

/**
 * @Author: ranter
 * @Date: 2020/10/7 1:14 下午
 * @Description:
 */
public interface RedisDAO {

    public String get(String key);

    public void set(String key, String value);

    public void delete(String key);
}
