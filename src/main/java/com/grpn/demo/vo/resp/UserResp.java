package com.grpn.demo.vo.resp;

import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;

import java.util.List;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Data
public class UserResp {

    @YapiParameter("id")
    private Long id;

    @YapiParameter(value = "机构id")
    private Long orgId;

    @YapiParameter(value = "部门id")
    private Long depId;

    @YapiParameter(value = "部门id列表", mock = "[1,2,3]")
    private List<Long> depIdList;

    @YapiParameter(value = "姓名")
    private String name;

    @YapiParameter(value = "联系电话")
    private String phone;

    @YapiParameter(value = "登录账号")
    private String account;

    @YapiParameter(value = "角色id")
    private Long roleId;

    @YapiParameter(value = "状态，1启用 0停用", mock = "@pick(0,1)")
    private Boolean state;
}
