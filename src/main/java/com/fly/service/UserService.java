package com.fly.service;

import com.fly.po.User;
import com.fly.po.UserRole;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    /**
     * 分页查询用户信息
     */
    PageInfo<User> findUserAll(int page, int pageSize, User user);

    /**
     * 用户添加功能
     */
    void addUser(User user);

    /**
     * 删除用户信息
     */
    void deleteUserInfoByIds(List<Integer> ids);

    /**
     * 根据id查询用户信息
     */
    User queryUserById(Integer id);

    //修改提交
    void updateUserSubmit(User user);


    User queryUserInfoByNameAndPwd(String username,String password);
}
