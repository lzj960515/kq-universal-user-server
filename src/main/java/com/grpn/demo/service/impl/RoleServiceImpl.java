package com.grpn.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kqinfo.universal.mybatisplus.base.BaseServiceImpl;
import com.grpn.demo.domain.Role;
import com.grpn.demo.mapper.RoleMapper;
import com.grpn.demo.service.RoleAuthService;
import com.grpn.demo.service.RoleService;
import com.grpn.demo.service.UserRoleService;
import com.grpn.demo.vo.req.RolePageReq;
import com.grpn.demo.vo.req.RoleReq;
import com.grpn.demo.vo.req.RoleUpdateReq;
import com.grpn.demo.vo.resp.RoleResp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
* 账号管理 服务实现类
*
* @author Zijian Liao
* @since 1.0.0
*/
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleAuthService roleAuthService;

    @Override
    public IPage<Role> pageRole(RolePageReq domain) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(domain.getState() != null, Role::getState, domain.getState())
                .eq(StringUtils.hasText(domain.getName()), Role::getName, domain.getName());
        return super.page(new Page<>(domain.getCurrent(), domain.getSize()), wrapper);
    }

    @Override
    public Role getByUserId(Long userId) {
        Long roleId = userRoleService.getRoleIdByUserId(userId);
        if (roleId == null){
            return null;
        }
        return super.getById(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createRole(RoleReq roleReq) {
        Role role = new Role();
        role.setName(roleReq.getName());
        role.setState(roleReq.getState());
        role.setDataAuth(roleReq.getDataAuth());
        super.save(role);
        // 绑定权限
        roleAuthService.reBindAuth(role.getId(), roleReq.getAuthIds());
    }

    @Override
    public void removeRole(Long id) {
        super.removeById(id);
        // 删除权限
        roleAuthService.removeRoleAuth(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole(RoleUpdateReq roleUpdateReq) {
        Role role = new Role();
        role.setName(roleUpdateReq.getName());
        role.setState(roleUpdateReq.getState());
        role.setDataAuth(roleUpdateReq.getDataAuth());
        role.setId(roleUpdateReq.getId());
        super.updateById(role);
        // 重新绑定权限
        roleAuthService.reBindAuth(roleUpdateReq.getId(), roleUpdateReq.getAuthIds());
    }

    @Override
    public RoleResp getRole(Long id) {
        Role role = super.getById(id);
        RoleResp roleResp = new RoleResp();
        roleResp.setId(role.getId());
        roleResp.setName(role.getName());
        roleResp.setState(role.getState());
        roleResp.setDataAuth(role.getDataAuth());
        // 获取权限
        roleResp.setAuthTree(roleAuthService.listRoleAuthTree(id));
        return roleResp;
    }
}
