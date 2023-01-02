package com.grpn.demo.vo.resp;

import com.kqinfo.universal.mybatis.annotation.FieldEncrypt;
import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Data
public class UserPageResp {

    private Long id;

    @YapiParameter(value = "机构名称")
    private String orgName;

    @YapiParameter(value = "姓名")
    private String name;

    @FieldEncrypt
    @YapiParameter(value = "联系电话")
    private String phone;

    @YapiParameter(value = "登录账号")
    private String account;

    @YapiParameter(value = "角色id")
    private Long roleId;

    @YapiParameter(value = "角色名称")
    private String roleName;

    @YapiParameter(value = "状态，1启用 0停用", mock = "@boolean)")
    private Boolean state;

    @YapiParameter(value = "创建时间")
    private LocalDateTime createTime;
}
