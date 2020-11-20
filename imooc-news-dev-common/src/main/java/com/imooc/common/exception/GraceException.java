package com.imooc.common.exception;

import com.imooc.common.grace.result.ResponseStatusEnum;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 16:59
 */
public class GraceException {
    public static void display(ResponseStatusEnum responseStatusEnum) {
        throw new MyCustomException(responseStatusEnum);
    }
}
