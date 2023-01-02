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
@TableName("tbl_role")
public class Role extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @YapiParameter(value = "角色名称")
    private String name;

    @YapiParameter(value = "状态，1启用 0停用", mock = "@boolean")
    private Boolean state;

    @YapiParameter(value = "数据权限，1全部权限 2本机构数据")
    private Integer dataAuth;


}