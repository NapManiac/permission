package com.fly.controller;

import com.alibaba.fastjson.JSONObject;
import com.fly.po.Dept;
import com.fly.service.DeptService;
import com.fly.util.JsonObject;
import com.fly.util.R;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Controller

public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询所有的部门信息
     */
    @ResponseBody
    @RequestMapping("dept/deptAll")
    public JsonObject queryDeptAll() {
        List<Dept> list = deptService.queryDeptAll();
        System.out.println(list);
        //创建返回值对象信息
        JsonObject object = new JsonObject();
        object.setCode(0);
        object.setCount((long) list.size());
        object.setData(list);
        object.setMsg("ok");
        object.setData(list);
        return object;
    }

    /**
     * 页面的渲染使用
     */
    @RequestMapping("/dept")
    public String deptIndex() {
        return "pages/dept";
    }

    @RequestMapping("/addDept")
    public String addDept(Integer type, Integer parentId, Model model) {
        model.addAttribute("type", type+1);
        model.addAttribute("parentId", parentId);
        return "pages/addDept";
    }

    @ResponseBody
    @RequestMapping("/dept/addDeptSubmit")
    public R addDept(Dept dept) {
        dept.setCreateTime(new Date());
        deptService.addDept(dept);
        return R.ok();
    }

    /**
     * 修改操作
     */
    @ResponseBody
    @RequestMapping("/dept/updateDeptSubmit")
    public R updateDept(Dept dept){
        deptService.updateDept(dept);
        return R.ok();
    }

    /**
     * 根据id删除部门信息
     */
    @RequestMapping("/dept/deleteDeptByID")
    @ResponseBody
    public R deleteDeptByID(int id){
        deptService.deleteDeptByID(id);
        return R.ok();
    }

}
