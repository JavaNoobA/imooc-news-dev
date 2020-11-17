package com.imooc.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengfei.zhao
 * @date 2020/11/17 17:00
 */
@RestController
@RequestMapping("/hello")
@Api(value = "测试controller", tags = {"测试controller"})
public class HelloController {

    @GetMapping("/test")
    @ApiOperation(value="测试api",  notes = "测试api", httpMethod = "GET")
    public String hello() {
        return "hello";
    }
}
