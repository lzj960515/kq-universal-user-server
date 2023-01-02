package com.grpn.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kqinfo.universal.common.exception.BusinessException;
import com.kqinfo.universal.common.util.PasswordUtil;
import com.kqinfo.universal.mybatis.util.EncryptHandler;
import com.kqinfo.universal.mybatisplus.base.BaseServiceImpl;
import com.grpn.demo.domain.CurrentUser;
import com.grpn.demo.domain.User;
import com.grpn.demo.mapper.UserMapper;
import com.grpn.demo.service.AuthService;
import com.grpn.demo.service.UserRoleService;
import com.grpn.demo.service.UserService;
import com.grpn.demo.vo.req.UpdatePasswordReq;
import com.grpn.demo.vo.req.UserPageReq;
import com.grpn.demo.vo.req.UserReq;
import com.grpn.demo.vo.req.UserUpdateReq;
import com.grpn.demo.vo.resp.AuthTreeResp;
import com.grpn.demo.vo.resp.LoginUserResp;
import com.grpn.demo.vo.resp.UserPageResp;
import com.grpn.demo.vo.resp.UserResp;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* 账号管理 服务实现类
*
* @author Zijian Liao
* @since 1.0.0
*/
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private EncryptHandler encryptHandler;
    @Resource
    private AuthService authService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserRoleService userRoleService;

    @Override
    public User getUserByPhone(String phone) {
        return this.getIfExist(phone);
    }

    private User getIfExist(String phone) {
        String encryptPhone = encryptHandler.encrypt(phone);
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getPhone, encryptPhone);
        return super.getOne(wrapper);
    }

    @Override
    public IPage<UserPageResp> pageUser(UserPageReq userPageReq) {
        // todo 返回部门信息, 也可无须部门信息，由具体业务而定
        return super.baseMapper.pageUser(new Page<>(userPageReq.getCurrent(), userPageReq.getSize()), userPageReq);
    }

    @Override
    public LoginUserResp me(CurrentUser currentUser) {
        User user = super.getById(currentUser.getUserId());
        List<AuthTreeResp> authTreeResps = authService.userAuthTree(user.getId());
        return new LoginUserResp().setUsername(user.getName())
                .setAuthTree(authTreeResps)
                .setPhone(user.getPhone());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createUser(UserReq domain) {
        User user = new User();
        BeanUtils.copyProperties(domain, user);
        // 初始密码为账号+手机号后6位
        user.setPassword(passwordEncoder.encode(generatePwd(domain.getAccount(), domain.getPhone())));
        super.save(user);
        // 绑定角色
        userRoleService.bindRole(user.getId(), domain.getRoleId());
    }

    private String generatePwd(String account, String phone){
        return account + phone.substring(5);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeUser(Long id) {
        super.removeById(id);
        userRoleService.removeBindRelation(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(UserUpdateReq userUpdateReq) {
        User user = new User();
        BeanUtils.copyProperties(userUpdateReq, user);
        userRoleService.reBindRole(user.getId(), userUpdateReq.getRoleId());
    }

    @Override
    public UserResp getUser(Long id) {
        User user = super.getById(id);
        UserResp userResp = new UserResp();
        BeanUtils.copyProperties(user, userResp);
        // 查询角色
        Long roleId = userRoleService.getRoleIdByUserId(user.getId());
        userResp.setRoleId(roleId);
        // todo 查询部门, 也可无须部门信息，由具体业务而定
        userResp.setDepIdList(null);
        return userResp;
    }

    @Override
    public String resetPasswd(Long id) {
        String passwd = PasswordUtil.generatePasswd();
        User user = new User();
        user.setId(id);
        user.setPassword(passwordEncoder.encode(passwd));
        super.update(user);
        return passwd;
    }

    @Override
    public void updatePassword(Long id, UpdatePasswordReq updatePasswordReq) {
        User user = super.get(id);
        String oldPassword = user.getPassword();
        if (!passwordEncoder.matches(updatePasswordReq.getOldPassword(), oldPassword)) {
            throw new BusinessException("旧密码错误");
        }
        String newPassword = updatePasswordReq.getNewPassword();
        PasswordUtil.validPasswordSecurity(newPassword);
        User update = new User();
        update.setId(id);
        update.setPassword(passwordEncoder.encode(newPassword));
        super.updateById(update);
    }
}
