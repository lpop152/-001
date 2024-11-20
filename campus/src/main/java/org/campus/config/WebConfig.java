package org.campus.config;

import org.campus.interceptor.JwtRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//拦截器
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtRequestInterceptor jwtRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtRequestInterceptor)
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/user/login", "/user/getVerificationCode"); // 排除登录路径
    }
}
