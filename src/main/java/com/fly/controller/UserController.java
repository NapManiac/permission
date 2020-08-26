package com.fly.controller;

import com.fly.po.User;
import com.fly.service.UserService;
import com.fly.util.JsonObject;
import com.fly.util.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有的用户信息并进行分页处理
     * @return
     */
    @RequestMapping("user/userAll")
    @ResponseBody
    public JsonObject queryUserInfo(User user, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10")Integer limit) {
        JsonObject object = new JsonObject();
        PageInfo<User> pageInfo = userService.findUserAll(page, limit, user);
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        object.setMsg("ok");
        return object;
    }

    /**
     * 添加功能
     * @return
     */
    @ResponseBody
    @RequestMapping("user/addUserSubmit")
    public R addUserSubmit(@RequestBody User user){
        userService.addUser(user);
        return R.ok();
    }

    /**
     * 修改功能
     * @return
     */
    @ResponseBody
    @RequestMapping("user/updateUserSubmit")
    public R updateUserSubmit(@RequestBody User user){
        System.out.println(user);
        userService.updateUserSubmit(user);
        return R.ok();
    }

    /**
     * 删除功能
     * @return
     */
    @ResponseBody
    @RequestMapping("user/deleteUserByIds")
    public R deleteUserByIds(String ids){
        List list= Arrays.asList(ids.split(","));
        userService.deleteUserInfoByIds(list);
        return R.ok();
    }

    @RequestMapping("/user")
    public String userIndex(){
        return "pages/user";
    }

    /**
     * 菜单的转发
     */
    @RequestMapping("/addUser")
    public String addUser(){
        return "pages/addUser";
    }

    /**
     * 修改菜单的映射
     */
    @RequestMapping("/updateUser")
    public String updateUser(Integer id, Model model){
        //根据用户的id查询用户信息
        User user=userService.queryUserById(id);
        model.addAttribute("user",user);
        System.out.println(user);
        return "pages/updateUser";
    }

}
