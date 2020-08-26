package com.fly.dao;

import com.fly.po.Menu;
import com.fly.po.Node;
import com.fly.po.RoleMenu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("menuDao")
public interface MenuDao {
    /**
     * 查询所有的菜单信息
     */
    List<Menu> queryMenuAll();

    /**
     * 添加菜单
     */
    void addMenu(Menu menu);

    /**
     * 删除功能
     */
    void deleteMenuByID(Integer id);

    /**
     * 根据id查询菜单详细信息
     */
    Menu queryMenuByID(Integer id);

    /**
     * 修改菜单记录信息
     */
    void updateMenuSubmit(Menu menu);

    /**
     * 根据角色查询关联到的菜单
     */
    List<RoleMenu> queryMenuByRoleID(Integer roleID);

    /**
     * 获取树结构的菜单
     */
    List<Node> queryMenuTree();
}
