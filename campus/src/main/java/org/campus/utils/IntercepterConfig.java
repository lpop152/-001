package org.campus.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;

    //构造方法
    public IntercepterConfig(@Autowired TokenInterceptor tokenInterceptor){
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // 定义不需要拦截的路径
        List<String> excludePath = Arrays.asList(
                "/user/login" // 登录接口
        );
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        //除了登陆接口其他所有接口都需要token验证
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
