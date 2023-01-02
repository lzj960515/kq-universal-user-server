package com.grpn.demo.service.impl;

import com.kqinfo.universal.common.exception.BusinessException;
import com.kqinfo.universal.common.response.BaseResultCode;
import com.kqinfo.universal.common.util.Assert;
import com.kqinfo.universal.common.util.PasswordUtil;
import com.grpn.demo.config.security.JwtUtil;
import com.grpn.demo.config.security.SecurityProperties;
import com.grpn.demo.domain.CurrentUser;
import com.grpn.demo.domain.Organization;
import com.grpn.demo.domain.Role;
import com.grpn.demo.domain.User;
import com.grpn.demo.service.LoginService;
import com.grpn.demo.service.OrganizationService;
import com.grpn.demo.service.RoleService;
import com.grpn.demo.service.UserService;
import com.grpn.demo.vo.req.LoginReq;
import com.grpn.demo.vo.resp.LoginResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserService userService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private RoleService roleService;
    @Resource
    private OrganizationService organizationService;

    @Override
    public LoginResp adminLogin(LoginReq loginReq) {
        // 查询用户信息
        User user = userService.getUserByPhone(loginReq.getPhone());
        Assert.notNull(user, "用户名或密码错误");
        if(!Boolean.TRUE.equals(user.getState())){
            throw new BusinessException(BaseResultCode.USER_ACCOUNT_FORBIDDEN);
        }
        // 校验密码是否正确
        if (!passwordEncoder.matches(loginReq.getPassword(), user.getPassword())) {
            throw new BusinessException(BaseResultCode.USER_LOGIN_ERROR);
        }
        // 校验密码安全性
        this.checkPasswordSecurity(loginReq.getPassword());
        // 校验角色是否被禁用
        Role role = roleService.getByUserId(user.getId());
        if(role == null || !role.getState()){
            throw new BusinessException("该用户所绑定的角色已失效");
        }
        // 校验机构是否被禁用
        Organization organization = organizationService.get(user.getOrgId());
        if(organization == null || !organization.getState()){
            throw new BusinessException("该用户所属的机构已失效");
        }
        // 生成token
        CurrentUser currentUser = this.buildCurrentUser(user, role.getName());
        String token = this.generateToken(currentUser);
        // 返回登录信息
        LoginResp loginResp = new LoginResp();
        loginResp.setToken(token)
                .setUserId(user.getId())
                .setTokenType("Bearer")
                .setExpireSeconds(securityProperties.getTokenExpireMinutes() * 60);
        return loginResp;
    }

    private void checkPasswordSecurity(String rawPassword){
        int level = PasswordUtil.checkPasswordSecurity(rawPassword);
        if(level < 2){
            throw new BusinessException("密码安全等级过低，请联系管理员重置密码后登录");
        }
    }

    private CurrentUser buildCurrentUser(User user, String roleName){
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserId(user.getId())
                .setUsername(user.getName())
                .setRoleName(roleName);
        return currentUser;
    }

    private String generateToken(CurrentUser currentUser){
       return JwtUtil.generateJwt(currentUser, securityProperties.getTokenExpireMinutes());
    }
}
