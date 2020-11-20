package com.imooc.api;

import com.imooc.common.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 15:52
 */
public class BaseController {
    @Autowired
    public RedisOperator redis;

    @Value("${website.domain}")
    private String webSiteDomain;

    public static final String MOBILE_SMS_CODE = "mobile:smscode";
    public static final String REDIS_USER_TOKEN = "redis_user_token";

    public static final Integer COOKIE_MONTH = 30 * 24 * 60 * 60;

    protected Map<String, String> handlerFieldError(BindingResult result) {
        Map<String, String> map = new HashMap<>();

        for (FieldError field : result.getFieldErrors()) {
            String key = field.getField();
            String msg = field.getDefaultMessage();
            map.put(key, msg);
        }

        return map;
    }

    protected void setCookie(String cookieName, String cookieValue, int maxAge,
                          HttpServletRequest request, HttpServletResponse response) {
        try {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            setCookieValue(cookieName, cookieValue, maxAge, request, response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    protected void setCookieValue(String cookieName, String cookieValue, int maxAge,
                                  HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(webSiteDomain);
        cookie.setPath("/");

        response.addCookie(cookie);
    }
}
