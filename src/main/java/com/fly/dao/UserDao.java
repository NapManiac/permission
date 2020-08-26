package com.fly.dao;

import com.fly.po.User;
import com.fly.po.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userDao")
public interface UserDao {

    /**
     * 查询所有的用户信息
     */
    List<User> queryUserAll(User user);

    /**
     * 用户添加
     */
    void addUser(User user);
    /**
     * 角色的关联关系添加
     */
    void addUserRole(List<UserRole> list);

    /**
     * 用户删除
     */
    void deleteUserByIds(List<Integer> ids);

    /**
     * 用户关联的角色删除
     */
    void deleteUserRoleByUserIds(List<Integer> ids);

    /**
     * 根据id查询用户信息
     */
    User queryUserById(Integer id);

    //修改提交
    void updateUserSubmit(User user);

    /**
     * 根据用户名密码查询对象信息
     */
    User queryUserInfoByNameAndPwd(@Param("username") String username, @Param("password") String password);

}
