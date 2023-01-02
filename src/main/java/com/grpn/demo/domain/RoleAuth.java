package com.grpn.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kqinfo.universal.mybatisplus.base.BaseDomain;
import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 角色-权限
*
* @author Zijian Liao
* @since 1.0.0
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tbl_role_auth")
public class RoleAuth extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @YapiParameter(value = "角色id")
    private Long roleId;

    @YapiParameter(value = "权限id")
    private Long authId;


}