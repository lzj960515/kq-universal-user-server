package com.grpn.demo.service;

import com.kqinfo.universal.mybatisplus.base.BaseService;
import com.grpn.demo.domain.RoleAuth;
import com.grpn.demo.vo.resp.RoleAuthTreeResp;

import java.util.List;

/**
* 角色-权限 服务类
*
* @author Zijian Liao
* @since 1.0.0
*/
public interface RoleAuthService extends BaseService<RoleAuth> {

    /**
     * 重新绑定权限
     * @param roleId 角色id
     * @param authIds 权限id列表
     */
    void reBindAuth(Long roleId, List<Long> authIds);

    /**
     * 绑定权限
     * @param roleId 角色id
     * @param authIds 权限id列表
     */
    void bindAuth(Long roleId, List<Long> authIds);

    /**
     * 删除权限绑定关系
     * @param roleId 角色id
     */
    void removeRoleAuth(Long roleId);

    /**
     * 查询角色权限树
     * @param roleId 角色id
     * @return 权限树
     */
    List<RoleAuthTreeResp> listRoleAuthTree(Long roleId);

    /**
     * 查询权限id列表
     * @param roleId 角色id
     * @return 权限id列表
     */
    List<Long> listRoleAuthIds(Long roleId);
}
