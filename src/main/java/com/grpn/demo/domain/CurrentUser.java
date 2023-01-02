package com.grpn.demo.domain;

import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author GuiLin Han
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class CurrentUser {


    @YapiParameter("用户id")
    private Long userId;

    @YapiParameter("用户名称")
    private String username;

    private String roleName;

}
