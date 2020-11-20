package com.imooc.api.controller.user;

import com.imooc.common.grace.result.GraceJSONResult;
import com.imooc.pojo.bo.RegisterLoginBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 15:47
 */
@Api(value = "用户注册登录", tags = {"用户注册登录的controller"})
@RequestMapping("/passport")
public interface PassportControllerApi {

    @ApiOperation(value = "获得短信验证码", notes = "获得短信验证码", httpMethod = "GET")
    @GetMapping(value = "/getSMSCode")
    GraceJSONResult getSMSCode(@RequestParam String mobile, HttpServletRequest request);

    @ApiOperation(value = "一键注册登录接口", notes = "一键注册登录接口", httpMethod = "POST")
    @PostMapping(value = "/doLogin")
    GraceJSONResult doLogin(@RequestBody @Valid RegisterLoginBO bo, BindingResult result,
                            HttpServletRequest request, HttpServletResponse response);
}
