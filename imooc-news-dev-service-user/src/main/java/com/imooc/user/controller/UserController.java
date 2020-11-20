package com.imooc.user.controller;

import com.imooc.api.BaseController;
import com.imooc.api.controller.user.UserControllerApi;
import com.imooc.common.grace.result.GraceJSONResult;
import com.imooc.common.grace.result.ResponseStatusEnum;
import com.imooc.pojo.AppUser;
import com.imooc.pojo.bo.UpdateUserInfoBO;
import com.imooc.pojo.vo.UserAccountInfoVO;
import com.imooc.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 20:51
 */
@RestController
public class UserController extends BaseController implements UserControllerApi {
    @Autowired
    private UserService userService;

    @Override
    public GraceJSONResult getUserInfo(String userId) {
        if (StringUtils.isBlank(userId)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.UN_LOGIN);
        }
        AppUser user = getUser(userId);
        UserAccountInfoVO vo = new UserAccountInfoVO();
        BeanUtils.copyProperties(user, vo);
        return GraceJSONResult.ok(vo);
    }

    @Override
    public GraceJSONResult updateUserInfo(@Valid UpdateUserInfoBO userInfoBO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errMap = handlerFieldError(result);
            return GraceJSONResult.errorMap(errMap);
        }
        userService.updateUserInfo(userInfoBO);
        return GraceJSONResult.ok();
    }

    private AppUser getUser(String userId) {
        return userService.getUser(userId);
    }
}
