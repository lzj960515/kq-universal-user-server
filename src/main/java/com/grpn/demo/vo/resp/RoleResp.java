package com.grpn.demo.vo.resp;

import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;

import java.util.List;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Data
public class RoleResp {

    private Long id;

    @YapiParameter(value = "角色名称")
    private String name;

    @YapiParameter(value = "状态，1启用 0停用", mock = "@boolean")
    private Boolean state;

    @YapiParameter(value = "数据权限，1全部权限 2本机构数据")
    private Integer dataAuth;

    @YapiParameter(value = "权限树")
    private List<RoleAuthTreeResp> authTree;

    @YapiParameter(value = "权限id列表", required = true)
    private List<Long> authIds;
}
