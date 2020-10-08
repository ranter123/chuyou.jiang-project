package chuyou.jiang.inventory.service.impl;

import chuyou.jiang.inventory.dao.RedisDAO;
import chuyou.jiang.inventory.mapper.UserMapper;
import chuyou.jiang.inventory.model.User;
import chuyou.jiang.inventory.service.UserService;
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

    @Resource
    private RedisDAO redisDao;

    @Override
    public User findUserInfo() {
        return userMapper.findUserInfo();
    }

    @Override
    public User getCacheUserInfo() {
        redisDao.set("cache_user", "{\"name\":\"lisi\", \"age\":\"28\"}");

        String userJson = redisDao.get("cache_user");
        JSONObject userJsonObject = JSONObject.parseObject(userJson);
        User user = new User();
        user.setName(userJsonObject.getString("name"));
        user.setAge(userJsonObject.getInteger("age"));
        return user;
    }
}
