package com.grpn.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kqinfo.universal.mybatisplus.base.BaseService;
import com.grpn.demo.domain.CurrentUser;
import com.grpn.demo.domain.User;
import com.grpn.demo.vo.req.UpdatePasswordReq;
import com.grpn.demo.vo.req.UserPageReq;
import com.grpn.demo.vo.req.UserReq;
import com.grpn.demo.vo.req.UserUpdateReq;
import com.grpn.demo.vo.resp.LoginUserResp;
import com.grpn.demo.vo.resp.UserPageResp;
import com.grpn.demo.vo.resp.UserResp;

/**
* 账号管理 服务类
*
* @author Zijian Liao
* @since 1.0.0
*/
public interface UserService extends BaseService<User> {

    /**
     * 通过手机号获取用户信息
     *
     * @param phone 手机号
     * @return 用户信息
     */
    User getUserByPhone(String phone);

    /**
     * 分页查询用户列表
     *
     * @param userPageReq 分页参数
     * @return 用户列表
     */
    IPage<UserPageResp> pageUser(UserPageReq userPageReq);

    /**
     * 查询当前登录用户信息
     * @param currentUser 当前登录用户信息
     * @return {@link LoginUserResp}
     */
    LoginUserResp me(CurrentUser currentUser);

    /**
     * 创建用户
     * @param domain 用户信息
     */
    void createUser(UserReq domain);

    /**
     * 删除
     * @param id 用户id
     */
    void removeUser(Long id);

    /**
     * 更新
     * @param userUpdateReq 用户信息
     */
    void updateUser(UserUpdateReq userUpdateReq);

    /**
     * 查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    UserResp getUser(Long id);

    /**
     * 重置密码
     * @param id userId
     * @return 密码
     */
    String resetPasswd(Long id);

    /**
     * 修改密码
     * @param id 用户id
     * @param updatePasswordReq 旧密码与新密码
     */
    void updatePassword(Long id, UpdatePasswordReq updatePasswordReq);
}
