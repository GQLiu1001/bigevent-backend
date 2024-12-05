package com.example.config;

import com.example.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    //重写一个addInterceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器 还得判断一下注册接口和登录接口 放行.excludePathPatterns
        registry.addInterceptor(loginInterceptor).
                excludePathPatterns(
                        "/user/login",
                        "/user/register" ,
                        "/user/updata");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
