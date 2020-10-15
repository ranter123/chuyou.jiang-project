package chuyou.jiang.hystrix.service.impl;

import chuyou.jiang.hystrix.mapper.UserMapper;
import chuyou.jiang.hystrix.model.Product;
import chuyou.jiang.hystrix.model.User;
import chuyou.jiang.hystrix.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: ranter
 * @Date: 2020/10/7 12:11 下午
 * @Description: 用户service 实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;


    @Override
    public User findUserInfo() {
        return userMapper.findUserInfo();
    }

    @Override
    public Product findProductInfo() {
        return userMapper.findProductInfo();
    }
}
