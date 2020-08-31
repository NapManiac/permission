package com.fly.config;

import com.fly.po.User;
import com.fly.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private ShiroUtil shiroUtil;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("走到执行授权方法了....");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //添加资源授权的字符串 保证配置一样
        //动态查询数据库权限
        Subject subject= SecurityUtils.getSubject();
        //获取获取用户对象
        User user= (User) subject.getPrincipal();
        //获取用户对象的权限信息
//        info.addStringPermission(user.getPermission());
        List<String> list=shiroUtil.queryPerm(user.getId());
        for(String m:list){
           info.addStringPermission(m);
        }
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证执行....");
        //判段
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        User user=userService.queryUserByUserName(token.getUsername());
        if(user==null){
            //用户名不存在
            return null;
        }
        //判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
