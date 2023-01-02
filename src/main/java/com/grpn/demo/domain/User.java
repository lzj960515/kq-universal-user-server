package com.grpn.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kqinfo.universal.mybatisplus.base.BaseDomain;
import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 账号管理
*
* @author Zijian Liao
* @since 1.0.0
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tbl_user")
public class User extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @YapiParameter(value = "姓名")
    private String name;

    @YapiParameter(value = "联系电话")
    private String phone;

    @YapiParameter(value = "登录账号")
    private String account;

    @YapiParameter(value = "登录密码")
    private String password;

    @YapiParameter(value = "机构id")
    private Long orgId;

    @YapiParameter(value = "部门id")
    private Long depId;

    @YapiParameter(value = "状态，1启用 0停用", mock = "@boolean")
    private Boolean state;


}