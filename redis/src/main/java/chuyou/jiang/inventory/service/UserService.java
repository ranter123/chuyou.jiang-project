package chuyou.jiang.inventory.service;

import chuyou.jiang.inventory.model.User;
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

    /**
     * 查询缓存中的用户信息
     * @return
     */
    public User getCacheUserInfo();
}
