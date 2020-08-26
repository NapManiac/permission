package com.fly.interceptor;

import com.fly.po.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    /*
      请求之前调用处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登录
        User user= (User) request.getSession().getAttribute("user");
        if(user != null){//存在
            return true ;
        }
        //跳转到登录页面
        response.sendRedirect(request.getContextPath()+"/login");
        return false;
    }


}
