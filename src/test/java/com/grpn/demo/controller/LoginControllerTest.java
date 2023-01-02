package com.grpn.demo.controller;

import com.kqinfo.universal.common.response.BaseResult;
import com.kqinfo.universal.test.base.BaseTest;
import com.grpn.demo.vo.req.LoginReq;
import com.grpn.demo.vo.resp.LoginResp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Rollback
@Transactional
public class LoginControllerTest extends BaseTest {

    @Resource
    private LoginController loginController;

    @Sql("/testsql/test_login.sql")
    @Test
    public void adminLogin() {
        LoginReq loginReq = new LoginReq();
        loginReq.setPhone("12312312311");
        loginReq.setPassword("dpHmXn3aDE7Ic1");
        loginReq.setSmsCode("123456");
        BaseResult<LoginResp> result = loginController.adminLogin(loginReq);
        Assertions.assertTrue(result.isSuccess());
    }
}