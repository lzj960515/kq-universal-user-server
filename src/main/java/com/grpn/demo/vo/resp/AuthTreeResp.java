package com.grpn.demo.vo.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kqinfo.universal.yapi.annotation.YapiParameter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class AuthTreeResp implements Serializable {

	private static final long serialVersionUID = -1198242680519935108L;

	@YapiParameter(value = "资源id")
	private Long key;

	@YapiParameter(value = "权限标识")
	private String code;

	@YapiParameter(value = "资源地址")
	private String url;

	@YapiParameter(value = "资源名称")
	private String name;

	@JsonIgnore
	@YapiParameter(value = "父级id", hidden = true)
	private Long parentId;

	@JsonIgnore
	@YapiParameter(value = "权重", hidden = true)
	private Integer weight;

	@YapiParameter(value = "children", hidden = true)
	private List<AuthTreeResp> children;

}