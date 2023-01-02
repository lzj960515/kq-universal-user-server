package com.grpn.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grpn.demo.domain.RoleAuth;
import com.grpn.demo.vo.resp.RoleAuthTreeResp;

import java.util.List;

/**
* 角色-权限 Mapper 接口
*
* @author Zijian Liao
* @since 1.0.0
*/
public interface RoleAuthMapper extends BaseMapper<RoleAuth> {

    /**
     * 查询角色权限树
     * @param roleId 角色id
     * @return 角色权限树
     */
    List<RoleAuthTreeResp> selectRoleAuthTree(Long roleId);
}
