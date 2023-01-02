package com.grpn.demo.vo.req;

import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Zijian Liao
 * @since 2.0.0
 */
@Data
public class RoleReq {

    @NotBlank
    @YapiParameter(value = "角色名称")
    private String name;

    @NotNull
    @YapiParameter(value = "状态，1启用 0停用", mock = "@boolean")
    private Boolean state;

    @NotNull
    @YapiParameter(value = "权限id列表", required = true)
    private List<Long> authIds;

    @NotNull
    @YapiParameter(value = "数据权限，1全部权限 2本机构数据")
    private Integer dataAuth;

}
