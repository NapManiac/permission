package com.fly.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private ShiroUtil shiroUtil;
    /**
     * 创建一个过滤器工厂
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean =new ShiroFilterFactoryBean();
        //设置过滤的原则 要拦截什么请求
        Map<String,String> map=shiroUtil.perms();

        //修改设置登录页面
        filterFactoryBean.setLoginUrl("/login");
        //设置未授权跳转的页面功能
//        filterFactoryBean.setUnauthorizedUrl("/noPermission");

        filterFactoryBean.setFilterChainDefinitionMap(map);
        //安全管理器
        filterFactoryBean.setSecurityManager(securityManager);
        return filterFactoryBean;
    }

    /**
     * 创建安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager(MyRealm myRealm){

        DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
        //给管理器赋值realm
        securityManager.setRealm(myRealm);
        return securityManager;

    }

    /**
     * 配置自定义realm
     */
    @Bean(name="myRealm")
    public MyRealm userRealm(){
        MyRealm myRealm=new MyRealm();
        return myRealm;
    }

    /**
     * 配置shiro和thtmeleaf标签
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
