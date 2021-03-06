package com.performane.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送/index  请求会来到index.html
        registry.addViewController("/register.html").setViewName("user/register");
        registry.addViewController("/information.html").setViewName("user/information");
        registry.addViewController("/postInfo.html").setViewName("postcomment/postInfo");
        registry.addViewController("/activityInfo.html").setViewName("activity/activityInfo");
        registry.addViewController("/performanePost.html").setViewName("postcomment/performanePost");
        registry.addViewController("/performaneActivity.html").setViewName("activity/performaneActivity");
        registry.addViewController("/number.html").setViewName("performane/number");
        registry.addViewController("/myperformane.html").setViewName("performane/myperformane");
        registry.addViewController("/myActivity.html").setViewName("activity/myActivity");
    }

    @Bean
    public PageHelper createPaeHelper(){
        PageHelper page= new PageHelper();
        return page;
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
        //静态资源；  *.css , *.js
        //SpringBoot已经做好了静态资源映射
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/performane/**","/login","/webjars/**","/asserts/**");
    }
}
