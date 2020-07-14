package com.bp.learnblog.config;

import com.bp.learnblog.interceptor.ApiIdempotenceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义配置 WebMVC部分内容（设置接口幂等性拦截器）
 *
 * @author DH
 */
@Configuration
public class CustomWebMvcConfigureAdapter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 接口幂等性拦截器注册
        registry.addInterceptor(apiIdempotenceInterceptor());
    }

    /**
     * 接口幂等性拦截器
     *
     * @return
     */
    @Bean
    public ApiIdempotenceInterceptor apiIdempotenceInterceptor() {
        return new ApiIdempotenceInterceptor();
    }
}
