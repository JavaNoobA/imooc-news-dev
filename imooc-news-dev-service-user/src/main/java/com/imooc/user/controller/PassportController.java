package com.imooc.user.controller;

import com.imooc.api.BaseController;
import com.imooc.api.controller.user.PassportControllerApi;
import com.imooc.common.enums.UserStatus;
import com.imooc.common.grace.result.GraceJSONResult;
import com.imooc.common.grace.result.ResponseStatusEnum;
import com.imooc.common.utils.IPUtil;
import com.imooc.common.utils.SMSUtils;
import com.imooc.pojo.AppUser;
import com.imooc.pojo.bo.RegisterLoginBO;
import com.imooc.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 15:51
 */
@RestController
public class PassportController extends BaseController implements PassportControllerApi {

    private static final Logger logger = LoggerFactory.getLogger(PassportController.class);

    @Autowired
    private SMSUtils smsUtils;
    @Autowired
    private UserService userService;

    @Override
    public GraceJSONResult getSMSCode(String mobile, HttpServletRequest request) {
        String userIp = IPUtil.getRequestIp(request);
        // 根据用户ip进行限制, 限制用户 60S 只能获取一次验证码
        redis.setnx60s(MOBILE_SMS_CODE + ":" + userIp, userIp);
        String code = (int)((Math.random() * 9 + 1) * 100000) + "";
        //smsUtils.sendSMS(mobile, code);
        redis.set(MOBILE_SMS_CODE + ":" + mobile, code, 30 * 60);
        return GraceJSONResult.ok();
    }

    @Override
    public GraceJSONResult doLogin(@Valid RegisterLoginBO bo, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) {
        // 1.检验手机号、验证码
        if (result.hasErrors()) {
            Map<String, String> errMap = handlerFieldError(result);
            return GraceJSONResult.errorMap(errMap);
        }

        String smsCode = redis.get(MOBILE_SMS_CODE + ":" + bo.getMobile());
        logger.info("验证码: {}", smsCode);
        if (StringUtils.isBlank(smsCode) || !bo.getSmsCode().equalsIgnoreCase(smsCode)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.SMS_CODE_ERROR);
        }
        // 2.根据手机号查询数据库, 判断用户是否注册
        AppUser user = userService.queryMobileIsExist(bo.getMobile());
        if (user != null && UserStatus.FROZEN.type.equals(user.getActiveStatus())) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.USER_STATUS_ERROR);
        } else if (user == null) {
            user = userService.createUser(bo.getMobile());
        }

        // 3.保存分布式用户会话信息
        Integer userActiveStatus = user.getActiveStatus();
        if (userActiveStatus != null && !UserStatus.FROZEN.type.equals(userActiveStatus)) {
            String uToken = UUID.randomUUID().toString();
            redis.set(REDIS_USER_TOKEN + ":" + user.getId(), uToken);

            setCookie("utoken", uToken, COOKIE_MONTH, request, response);
            setCookie("uid", user.getId(), COOKIE_MONTH, request, response);
        }

        // 4.用戶登录或注册成功后删除redis中的验证码
        redis.del(MOBILE_SMS_CODE + ":" + bo.getMobile());
        return GraceJSONResult.ok(userActiveStatus);
    }

}
