package com.imooc.api.interceptor;

import com.imooc.common.exception.GraceException;
import com.imooc.common.grace.result.ResponseStatusEnum;
import com.imooc.common.utils.IPUtil;
import com.imooc.common.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.imooc.api.BaseController.MOBILE_SMS_CODE;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 16:46
 */
public class PassportInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisOperator redis;

    /**
     * 拦截请求, 进入 controller 之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userIp = IPUtil.getRequestIp(request);
        String userKey = MOBILE_SMS_CODE + ":" + userIp;
        if (redis.keyIsExist(userKey)) {
            GraceException.display(ResponseStatusEnum.SMS_NEED_WAIT_ERROR);
            return false;
        }
        return true;
    }

    /**
     * 请求访问 controller 之后, 渲染视图之前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求访问 controller 之后, 渲染视图之前
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
