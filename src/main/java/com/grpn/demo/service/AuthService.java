package com.grpn.demo.service;

import com.kqinfo.universal.mybatisplus.base.BaseService;
import com.grpn.demo.domain.Auth;
import com.grpn.demo.vo.resp.AuthTreeResp;

import java.util.List;

/**
* 权限管理 服务类
*
* @author Zijian Liao
* @since 1.0.0
*/
public interface AuthService extends BaseService<Auth> {

    /**
     * 查询角色下所有接口
     * @param roleId 角色id
     * @return 角色下所有接口
     */
    List<Auth> listRoleInterface(Long roleId);

    /**
     * 查询系统中所有接口
     * @return 接口列表
     */
    List<Auth> listAllInterface();

    /**
     * 查询用户所拥有的权限树
     * @param userId 用户id
     * @return 用户所拥有的权限树
     */
    List<AuthTreeResp> userAuthTree(Long userId);
}
