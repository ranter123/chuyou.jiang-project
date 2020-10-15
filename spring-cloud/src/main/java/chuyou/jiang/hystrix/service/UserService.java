package chuyou.jiang.hystrix.service;

import chuyou.jiang.hystrix.model.Product;
import chuyou.jiang.hystrix.model.User;
import org.springframework.stereotype.Service;

/**
 * @Author: ranter
 * @Date: 2020/10/7 12:11 下午
 * @Description:
 */
@Service
public interface UserService {

    /**
     * 查询用户信息
     * @return
     */
    public User findUserInfo();


    public Product findProductInfo();
}
