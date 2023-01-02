package com.grpn.demo.vo.req;

import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePageReq extends PageReq {

    @YapiParameter(value = "角色名称")
    private String name;
    @YapiParameter(value = "状态，1启用 0停用", mock = "@boolean")
    private Boolean state;
}
