package com.fly.config;

import com.fly.po.Menu;
import com.fly.po.Role;
import com.fly.po.User;
import com.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component("shiroUtil")
public class ShiroUtil {

    @Autowired
    private UserService userService;

    /**
     * 获取过滤的配置
     */
    public Map<String,String> perms(){
        LinkedHashMap <String,String> map=new LinkedHashMap<String,String>();
        /**
         * shiro内置过滤器
         *  常用过滤器
         *  anon:不需要认证就可以访问的页面
         *  authc:必须认证才可以访问的页面
         *  user:如果使用了记住我功能可以直接访问的页面
         *  perms：必须得到资源权限才可以访问的
         *  role：必须得到角色权限才可以访问的
         */
        //假设用户主页都可以访问，增删改必须认证后访问
        map.put("/login","anon");
        map.put("/verifyCode","anon");
        map.put("/loginIn","anon");//放行登录
        map.put("/**/*.js","anon");//放行登录
        map.put("/**/*.css","anon");//放行登录
        map.put("/**/*.woff","anon");//放行登录
        map.put("/**/*.json","anon");//放行登录
        map.put("/**/*.png","anon");//放行登录
         map.put("/**/*.jpg","anon");//放行登录

//
//        "/**/*.js",
//                "/**/*.css",
//                "/**/*.png",
//                "/**/*.woff",
//                "/**/*.ttf",
//                "/**/*.html"
        map.put("/**","authc");//添加认证
        return map;
    }

    /**
     * 获取权限列表
     */
    public List<String> queryPerm(Integer userId){
       //创建容器对象
        List<String> list=new ArrayList<>();
        //获取用户的权限菜单
        List<User> userlist=userService.findUrlAndPermByUserID(userId);
        //获取用户角色
        for(User user:userlist){
            List<Role> roleList= user.getRoleList();
            //获取角色关联的菜单
            for(Role role:roleList){
                List <Menu> menuList=role.getMenuList();
                for(Menu menu:menuList){
                    list.add(menu.getPermission());//加入权限标识
                }
            }
        }
        return list;
    }

}
