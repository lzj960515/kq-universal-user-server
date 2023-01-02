package com.grpn.demo.vo.resp;

import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登陆返回结果
 *
 * @author Zijian Liao
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class LoginResp {

    @YapiParameter(value = "jwt", required = true)
    private String token;

    @YapiParameter(value = "token类型，默认bearer", required = true)
    private String tokenType;

    @YapiParameter(value = "token有效期，秒")
    private Integer expireSeconds;

    @YapiParameter(value = "用户id")
    private Long userId;

}
