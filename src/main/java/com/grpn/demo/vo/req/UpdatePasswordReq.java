package com.grpn.demo.vo.req;

import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Data
public class UpdatePasswordReq {

	@NotBlank
	@YapiParameter(value = "旧密码", required = true)
	private String oldPassword;

	@NotBlank
	@YapiParameter(value = "新密码", required = true)
	private String newPassword;

}