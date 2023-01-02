package com.grpn.demo.service;


import com.grpn.demo.vo.req.LoginReq;
import com.grpn.demo.vo.resp.LoginResp;

/**
 * 登录业务
 *
 * @author Zijian Liao
 * @since 1.0.0
 */
public interface LoginService {

    /**
     * 管理端登录
     * @param loginReq {@link LoginReq}
     * @return token
     */
    LoginResp adminLogin(LoginReq loginReq);
}
