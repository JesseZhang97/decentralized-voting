/*
 * @Date: 2020-03-16 23:03:28
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-16 23:05:12
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/config/WebConfigurer.java
 * @Description: 
 */
package com.zz.backend.config;

// import com.zz.backend.config.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootConfiguration
public class WebConfigurer implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**").excludePathPatterns("/index.html");
    }
}