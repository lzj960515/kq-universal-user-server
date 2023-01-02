package com.grpn.demo.vo.req;

import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 登录参数
 *
 * @author Zijian Liao
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class LoginReq {

    @NotBlank
    @YapiParameter(value = "手机号")
    private String phone;

    @NotBlank
    @YapiParameter(value = "密码")
    private String password;

    @NotBlank
    @YapiParameter("短信验证码")
    private String smsCode;

}
