package com.grpn.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kqinfo.universal.mybatisplus.base.BaseDomain;
import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 机构管理
*
* @author Zijian Liao
* @since 1.0.0
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tbl_organization")
public class Organization extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @YapiParameter(value = "机构名称")
    private String name;

    @YapiParameter(value = "状态，1启用 0停用")
    private Boolean state;

    @YapiParameter(value = "主机构，1是 0否")
    private Boolean main;

    @YapiParameter(value = "机构介绍")
    private String summary;

    @YapiParameter(value = "logo")
    private String logo;

    @YapiParameter(value = "机构大图")
    private String picture;

    @YapiParameter(value = "轮播图")
    private String carousel;


}