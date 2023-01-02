package com.grpn.demo.service.impl;

import com.kqinfo.universal.mybatisplus.base.BaseServiceImpl;
import com.kqinfo.universal.redis.util.RedisUtil;
import com.grpn.demo.constant.RedisKey;
import com.grpn.demo.domain.RoleAuth;
import com.grpn.demo.mapper.RoleAuthMapper;
import com.grpn.demo.service.RoleAuthService;
import com.grpn.demo.vo.resp.RoleAuthTreeResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
* 角色-权限 服务实现类
*
* @author Zijian Liao
* @since 1.0.0
*/
@Service
public class RoleAuthServiceImpl extends BaseServiceImpl<RoleAuthMapper, RoleAuth> implements RoleAuthService {

    @Override
    public void reBindAuth(Long roleId, List<Long> authIds) {
        this.removeRoleAuth(roleId);
        this.bindAuth(roleId, authIds);
    }

    @Override
    public void bindAuth(Long roleId, List<Long> authIds) {
        for (Long authId : authIds) {
            RoleAuth roleAuth = new RoleAuth();
            roleAuth.setRoleId(roleId);
            roleAuth.setAuthId(authId);
            super.save(roleAuth);
        }
    }

    @Override
    public void removeRoleAuth(Long roleId) {
        super.lambdaUpdate()
                .eq(RoleAuth::getRoleId, roleId)
                .remove();
        // 删除缓存
        String roleAclKey = String.format(RedisKey.ROLE_INTERFACE, roleId);
        RedisUtil.del(roleAclKey);
    }

    @Override
    public List<RoleAuthTreeResp> listRoleAuthTree(Long roleId) {
        List<RoleAuthTreeResp> roleAuthTree = super.baseMapper.selectRoleAuthTree(roleId);
        return toRoleAuthTree(0L, roleAuthTree);
    }


    private List<RoleAuthTreeResp> toRoleAuthTree(Long parentId, List<RoleAuthTreeResp> roleAuthTree) {
        return roleAuthTree.stream().filter(roleAuthTreeResp -> roleAuthTreeResp.getParentId().equals(parentId))
                .map(roleAuthTreeResp -> {
                    RoleAuthTreeResp authTree = new RoleAuthTreeResp();
                    BeanUtils.copyProperties(roleAuthTreeResp, authTree);
                    authTree.setChildren(toRoleAuthTree(authTree.getKey(), roleAuthTree));
                    return authTree;
                }).sorted(Comparator.comparing(RoleAuthTreeResp::getWeight)).collect(Collectors.toList());
    }
}
