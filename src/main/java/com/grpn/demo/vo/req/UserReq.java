package com.grpn.demo.vo.req;

import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Data
public class UserReq {

    @Length(max = 20, message = "用户姓名长度不能超过20字符")
    @YapiParameter(value = "姓名")
    private String name;

    @NotNull
    @YapiParameter(value = "角色id")
    private Long roleId;

    @Length(max = 11, message = "手机号不能超过11字符")
    @YapiParameter(value = "联系电话")
    private String phone;

    @Length(max = 50, message = "登录账号长度不能超过50字符")
    @YapiParameter(value = "登录账号")
    private String account;

    @NotNull
    @YapiParameter(value = "机构id")
    private Long orgId;

    @NotNull
    @YapiParameter(value = "部门id")
    private Long depId;

    @NotNull
    @YapiParameter(value = "状态，1启用 0停用", mock = "@boolean")
    private Boolean state;
}
