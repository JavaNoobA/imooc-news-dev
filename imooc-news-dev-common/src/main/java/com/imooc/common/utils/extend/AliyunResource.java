package com.imooc.common.utils.extend;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 14:55
 */
@Component
@PropertySource(value = "classpath:sms.properties")
@ConfigurationProperties(prefix = "sms")
public class AliyunResource {
    private String accessKeyId;

    private String accessKeySecret;

    public String getAccessKeyID() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
