package com.grpn.demo.vo.resp;

import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 当前登录用户信息
 *
 * @author Zijian Liao
 * @since 2.0.0
 */
@Data
@Accessors(chain = true)
public class LoginUserResp implements Serializable {

	private static final long serialVersionUID = -2694595789636738205L;

	@YapiParameter(value = "用户名", required = true)
	private String username;

	@YapiParameter(value = "电话", required = true)
	private String phone;

	@YapiParameter(value = "当前用户的权限树", required = true)
	private List<AuthTreeResp> authTree;

}
