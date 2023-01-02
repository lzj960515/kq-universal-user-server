package com.grpn.demo.service.impl;

import com.kqinfo.universal.mybatisplus.base.BaseServiceImpl;
import com.grpn.demo.domain.UserRole;
import com.grpn.demo.mapper.UserRoleMapper;
import com.grpn.demo.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 账号-角色 服务实现类
*
* @author Zijian Liao
* @since 1.0.0
*/
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {


    @Override
    public Long getRoleIdByUserId(Long userId) {
        UserRole userRole = super.lambdaQuery()
                .eq(UserRole::getUserId, userId)
                .one();
        if (userRole != null) {
            return userRole.getRoleId();
        }
        return null;
    }

    @Override
    public List<UserRole> listByRoleId(Long roleId) {
        return super.lambdaQuery()
                .eq(UserRole::getRoleId, roleId)
                .list();
    }

    @Override
    public void bindRole(Long userId, Long roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId).setRoleId(roleId);
        super.save(userRole);
    }

    @Override
    public void removeBindRelation(Long userId) {
        // 删除原有的绑定关系
        super.lambdaUpdate()
                .eq(UserRole::getUserId, userId)
                .remove();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void reBindRole(Long userId, Long roleId) {
        this.removeBindRelation(userId);
        this.bindRole(userId, roleId);
    }
}
