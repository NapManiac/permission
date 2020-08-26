package com.fly.service;

import com.fly.po.Node;
import com.fly.po.Role;

import java.util.List;

public interface RoleService {
    /**
     * 查询所有的角色信息
     */
    public List<Role> queryRoleAll();

    /**
     * 添加
     */
    void addRole(Role role );


    /**
     * 根据id查询角色信息
     */
    public Role queryRoleByID(Integer id);

    /**
     * 修改
     */
    void updateRole(Role role);

    /**
     * 删除
     */
    void deleteRoleByID(Integer id);

    /**
     * 批量进行权限和角色关联
     */
    void insertForEach(int [] ids,int roleId);

    /**
     * 根据角色删除原来的关联关系
     */
    void deleteRoleMenuByRoleID(int id);


    /**
     * 查询树
     */
    List<Node> queryRoleTree();
}
