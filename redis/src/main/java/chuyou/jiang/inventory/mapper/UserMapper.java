package chuyou.jiang.inventory.mapper;

import chuyou.jiang.inventory.model.User;

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
}
