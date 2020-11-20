package com.imooc.api.controller.user;

import com.imooc.common.grace.result.GraceJSONResult;
import com.imooc.pojo.bo.UpdateUserInfoBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 20:50
 */
@Api(value = "用户信息相关Controller", tags = {"用户信息相关Controller"})
@RequestMapping("/user")
public interface UserControllerApi {

    @ApiOperation(value = "获得用户基本信息", notes = "获得用户基本信息", httpMethod = "POST")
    @PostMapping("/getUserInfo")
    GraceJSONResult getUserInfo(@RequestParam String userId);

    @ApiOperation(value = "修改/完善用户信息", notes = "修改/完善用户信息", httpMethod = "POST")
    @PostMapping("/updateUserInfo")
    GraceJSONResult updateUserInfo(@RequestBody @Valid UpdateUserInfoBO userInfoBO, BindingResult result);
}
