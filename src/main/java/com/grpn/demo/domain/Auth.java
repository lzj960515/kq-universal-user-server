package com.grpn.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kqinfo.universal.mybatisplus.base.BaseDomain;
import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 权限管理
*
* @author Zijian Liao
* @since 1.0.0
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tbl_auth")
public class Auth extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @YapiParameter(value = "资源名称")
    private String name;

    @YapiParameter(value = "资源类型 1.目录 2.菜单 3.按钮(绑定接口)")
    private String type;

    @YapiParameter(value = "资源地址")
    private String url;

    @YapiParameter(value = "请求方式")
    private String method;

    @YapiParameter(value = "权限标识")
    private String code;

    @YapiParameter(value = "parent_id")
    private Long parentId;

    @YapiParameter(value = "权重")
    private Integer weight;


}