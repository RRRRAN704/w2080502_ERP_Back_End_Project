//package com.erp.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
////TODO is this necessary
//@Configuration
//public class CorsConfiguration implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // 所有路径
//                .allowedOriginPatterns("*") // 允许所有源
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的方法
//                .allowedHeaders("*") // 允许所有请求头
//                .allowCredentials(true) // 是否允许带 cookie
//                .maxAge(3600); // 预检请求缓存 1 小时
//    }
//}
