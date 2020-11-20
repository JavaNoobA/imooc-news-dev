package com.imooc.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 16:35
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 添加 cors 配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        // 设置是否发送 cookie 信息
        config.setAllowCredentials(true);
        // 设置请求方式
        config.addAllowedMethod("*");
        // 设置允许的请求头
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource urlConfig = new UrlBasedCorsConfigurationSource();
        urlConfig.registerCorsConfiguration("/**", config);
        return new CorsFilter(urlConfig);
    }
}
