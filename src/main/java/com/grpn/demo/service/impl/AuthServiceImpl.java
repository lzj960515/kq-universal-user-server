package com.grpn.demo.service.impl;

import com.kqinfo.universal.mybatisplus.base.BaseServiceImpl;
import com.kqinfo.universal.redis.util.RedisUtil;
import com.grpn.demo.constant.RedisKey;
import com.grpn.demo.domain.Auth;
import com.grpn.demo.domain.Role;
import com.grpn.demo.mapper.AuthMapper;
import com.grpn.demo.service.AuthService;
import com.grpn.demo.service.RoleService;
import com.grpn.demo.vo.resp.AuthTreeResp;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* 权限管理 服务实现类
*
* @author Zijian Liao
* @since 1.0.0
*/
@Service
public class AuthServiceImpl extends BaseServiceImpl<AuthMapper, Auth> implements AuthService {
    @Resource
    private AuthMapper authMapper;
    @Resource
    private RoleService roleService;

    /**
     * 权限类型，3:接口
     */
    private static final Integer INTERFACE = 3;


    private List<AuthTreeResp> toAuthTree(Long parentId, List<Auth> authList) {
        return authList.stream().filter(auth -> auth.getParentId().equals(parentId))
                .map(auth -> {
                    AuthTreeResp authTree = new AuthTreeResp();
                    authTree.setKey(auth.getId());
                    authTree.setCode(auth.getCode());
                    authTree.setUrl(auth.getUrl());
                    authTree.setName(auth.getName());
                    authTree.setParentId(auth.getParentId());
                    authTree.setWeight(auth.getWeight());
                    authTree.setChildren(toAuthTree(authTree.getKey(), authList));
                    return authTree;
                }).sorted(Comparator.comparing(AuthTreeResp::getWeight)).collect(Collectors.toList());
    }

    @Override
    public List<Auth> listRoleInterface(Long roleId) {
        String key = String.format(RedisKey.ROLE_INTERFACE, roleId);
        Set<Object> aclList = RedisUtil.sGet(key);
        if(!CollectionUtils.isEmpty(aclList)){
            return convert(aclList);
        }
        List<Auth> auths = authMapper.selectRoleInterface(roleId);
        return setAclToRedis(key, auths);
    }

    @Override
    public List<Auth> listAllInterface() {
        Set<Object> aclList = RedisUtil.sGet(RedisKey.ALL_INTERFACE);
        if(!CollectionUtils.isEmpty(aclList)){
            return convert(aclList);
        }
        List<Auth> auths = super.lambdaQuery()
                .eq(Auth::getType, INTERFACE)
                .list();
        return setAclToRedis(RedisKey.ALL_INTERFACE, auths);
    }

    private List<Auth> setAclToRedis(String key, List<Auth> authList){
        // 缓存5分钟
        if(!authList.isEmpty()){
            RedisUtil.sSetAndTime(key, 300L, authList.toArray());
        }
        return authList;
    }

    private List<Auth> convert(Set<Object> aclList){
        return aclList.stream().map(item -> (Auth)item).collect(Collectors.toList());
    }

    @Override
    public List<AuthTreeResp> userAuthTree(Long userId) {
        Role role = roleService.getByUserId(userId);
        if(role == null) {
            return new ArrayList<>(0);
        }
        return toAuthTree(0L, authMapper.selectRoleAuth(role.getId()));
    }
}
