package chuyou.jiang.hystrix.mapper;


import chuyou.jiang.hystrix.model.Product;
import chuyou.jiang.hystrix.model.User;

/**
 * @Author: ranter
 * @Date: 2020/10/7 12:10 下午
 * @Description:
 */
public interface UserMapper {

    /**
     * 查询测试用户信息
     * @return 测试用户信息
     */
    public User findUserInfo();

    /**
     * 查找产品信息
     * @return
     */
    public Product findProductInfo();
}
