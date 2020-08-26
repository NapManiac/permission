package com.fly.service;

import com.fly.dao.RoleDao;
import com.fly.po.Node;
import com.fly.po.Role;
import com.fly.po.RoleMenu;
import com.fly.util.TreeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private TreeBuilder treeBuilder;

    @Override
    public List<Role> queryRoleAll() {
        return roleDao.queryRoleAll();
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public Role queryRoleByID(Integer id) {
        return roleDao.queryRoleByID(id);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    public void deleteRoleByID(Integer id) {
        roleDao.deleteRoleByID(id);
    }

    @Override
    public void insertForEach(int[] ids, int roleId) {
        //先删除原来的映射关系
        this.deleteRoleMenuByRoleID(roleId);
        //遍历添加
        List<RoleMenu> list = new ArrayList<>();
        for (int id : ids){
            list.add(new RoleMenu(roleId, id));
        }
        roleDao.insertForEach(list);
    }

    @Override
    public void deleteRoleMenuByRoleID(int id) {
        roleDao.deleteRoleMenuByRoleID(id);
    }

    @Override
    public List<Node> queryRoleTree() {
        //查询所有的数据
        List nodeList = roleDao.queryRoleTree();
        treeBuilder.buildTree(nodeList);
        return nodeList;
    }
}
