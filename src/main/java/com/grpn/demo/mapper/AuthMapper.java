package com.grpn.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grpn.demo.domain.Auth;

import java.util.List;

/**
* 权限管理 Mapper 接口
*
* @author Zijian Liao
* @since 1.0.0
*/
public interface AuthMapper extends BaseMapper<Auth> {

    /**
     * 查询角色下所有接口
     * @param roleId 角色id
     * @return 角色下所有接口
     */
    List<Auth> selectRoleInterface(Long roleId);

    /**
     * 查询角色下所有权限
     * @param roleId 角色id
     * @return 角色下所有权限
     */
    List<Auth> selectRoleAuth(Long roleId);
}
