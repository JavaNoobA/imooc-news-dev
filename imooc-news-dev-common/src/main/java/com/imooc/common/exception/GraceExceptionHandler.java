package com.imooc.common.exception;

import com.imooc.common.grace.result.GraceJSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 17:03
 */
@ControllerAdvice
public class GraceExceptionHandler {

    @ExceptionHandler(MyCustomException.class)
    @ResponseBody
    public GraceJSONResult handlerCustomEx(MyCustomException ex) {
        return GraceJSONResult.errorCustom(ex.getResponseStatusEnum());
    }
}
