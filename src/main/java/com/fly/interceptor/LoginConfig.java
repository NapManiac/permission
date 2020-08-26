package com.fly.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        //拦截所有的路径
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(//不拦截的路径信息
                "/login",
                "/loginIn",
                "/verifyCode",
                "/**/*.js",
                "/**/*.css",
                "/**/*.png",
                "/**/*.woff",
                "/**/*.ttf",
                "/**/*.html"
        );
    }
}
