package com.grpn.demo.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 认证授权配置
 *
 * @author Zijian Liao
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "kq.security")
public class SecurityProperties {
    /**
     * token过期时间
     * 默认:7天
     */
    private Integer tokenExpireMinutes = 60 * 24 * 7;
    /**
     * 无须授权的接口
     */
    private List<String> ignoreUrls;

    public Integer getTokenExpireMinutes() {
        return tokenExpireMinutes;
    }

    public void setTokenExpireMinutes(Integer tokenExpireMinutes) {
        this.tokenExpireMinutes = tokenExpireMinutes;
    }

    public List<String> getIgnoreUrls() {
        return ignoreUrls;
    }

    public void setIgnoreUrls(List<String> ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }
}
