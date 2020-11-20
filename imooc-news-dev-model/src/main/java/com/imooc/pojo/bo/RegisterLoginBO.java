package com.imooc.pojo.bo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 17:27
 */
public class RegisterLoginBO {
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @NotBlank(message = "验证码不能为空")
    private String smsCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        return "RegisterLoginBO{" +
                "mobile='" + mobile + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';
    }
}
