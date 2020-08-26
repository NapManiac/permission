package com.fly.dao;

import com.fly.po.Dept;
import com.fly.po.Node;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("deptDao")
public interface DeptDao {

    /**
     * 查询所有的部门信息
     */
    List<Dept> queryDeptAll();

    /**
     * 添加部门信息
     */
    void addDept(Dept dept);

    /**
     * 根据id查询部门信息
     */
    Dept queryDeptByID(Integer id);

    /***
     * 修改操作
     */
    void updateDept(Dept dept);

    /**
     * 根据id删除记录信息
     */
    void deleteDeptByID(Integer id);

    /**
     * 查询树信息
     */
    List<Node> queryDeptTree();
}
