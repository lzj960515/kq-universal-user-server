package com.grpn.demo.vo.resp;

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
public class RoleAuthTreeResp implements Serializable {

	private static final long serialVersionUID = 325389061329412689L;

	@YapiParameter(value = "父级id", hidden = true)
	private Long parentId;

	@YapiParameter(value = "权重", hidden = true)
	private Integer weight;

	@YapiParameter(value = "是否拥有权限", required = true)
	private boolean hasAuth;

	@YapiParameter(value = "资源id", required = true)
	private Long key;

	@YapiParameter(value = "资源地址", required = true)
	private String url;

	@YapiParameter(value = "资源名称", required = true)
	private String name;

	@YapiParameter(value = "children", hidden = true)
	private List<RoleAuthTreeResp> children;

}