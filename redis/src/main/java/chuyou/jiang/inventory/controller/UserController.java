package chuyou.jiang.inventory.controller;

import chuyou.jiang.inventory.model.User;
import chuyou.jiang.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ranter
 * @Date: 2020/10/7 12:20 下午
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户信息
     * @return
     */
    @RequestMapping("/getUserInfo")
    public User getUserInfo() {
        User user = userService.findUserInfo();
        return user;
    }

    /**
     * 查询缓存用户信息
     * @return
     */
    @RequestMapping("/getCacheUserInfo")
    public User getCacheUserInfo() {
        User user = userService.getCacheUserInfo();
        return user;
    }
}
