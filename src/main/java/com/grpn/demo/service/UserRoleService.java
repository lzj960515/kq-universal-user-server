package com.grpn.demo.service;

import com.kqinfo.universal.mybatisplus.base.BaseService;
import com.grpn.demo.domain.UserRole;
import org.springframework.lang.Nullable;

import java.util.List;

/**
* 账号-角色 服务类
*
* @author Zijian Liao
* @since 1.0.0
*/
public interface UserRoleService extends BaseService<UserRole> {

    /**
     * 通过用户id获取角色id
     * @param userId 用户id
     * @return 角色id
     */
    @Nullable
    Long getRoleIdByUserId(Long userId);

    /**
     * 通过角色id获取用户id
     * @param roleId 用户id
     * @return 角色id
     */
    List<UserRole> listByRoleId(Long roleId);

    /**
     * 绑定角色
     * @param userId 用户id
     * @param roleId 角色id
     */
    void bindRole(Long userId, Long roleId);

    /**
     * 删除绑定关系
     * @param userId 用户id
     */
    void removeBindRelation(Long userId);

    /**
     * 重新绑定角色
     * @param userId 用户id
     * @param roleId 角色id
     */
    void reBindRole(Long userId, Long roleId);
}
