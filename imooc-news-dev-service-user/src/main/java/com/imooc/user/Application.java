package com.imooc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author pengfei.zhao
 * @date 2020/11/17 16:54
 */
@SpringBootApplication
@MapperScan(basePackages = "com.imooc.user.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
