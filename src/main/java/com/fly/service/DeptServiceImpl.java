package com.fly.service;

import com.fly.dao.DeptDao;
import com.fly.po.Dept;
import com.fly.po.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> queryDeptAll() {
        return deptDao.queryDeptAll();
    }

    @Override
    public void addDept(Dept dept) {
        deptDao.addDept(dept);
    }

    @Override
    public Dept queryDeptByID(Integer id) {
        return deptDao.queryDeptByID(id);
    }

    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(new Date());
        deptDao.updateDept(dept);
    }

    @Override
    public void deleteDeptByID(Integer id) {
        deptDao.deleteDeptByID(id);
    }

    @Override
    public List<Node> queryDeptTree() {
        return null;
    }
}
