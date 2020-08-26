package com.fly.service;

import com.fly.dao.MenuDao;
import com.fly.po.Menu;
import com.fly.po.Node;
import com.fly.po.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> queryMenuAll() {
        return menuDao.queryMenuAll();
    }

    @Override
    public void addMenu(Menu menu) {
        menuDao.addMenu(menu);
    }

    @Override
    public void deleteMenuByID(Integer id) {
        menuDao.deleteMenuByID(id);
    }

    @Override
    public Menu queryMenuByID(Integer id) {
        return menuDao.queryMenuByID(id);
    }

    @Override
    public void updateMenuSubmit(Menu menu) {
        menuDao.updateMenuSubmit(menu);
    }

    @Override
    public List<RoleMenu> queryMenuByRoleID(Integer roleID) {
        return menuDao.queryMenuByRoleID(roleID);
    }

    @Override
    public List<Node> queryMenuTree() {
        return menuDao.queryMenuTree();
    }
}
