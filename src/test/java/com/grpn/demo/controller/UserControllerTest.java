package com.grpn.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kqinfo.universal.common.response.BaseResult;
import com.kqinfo.universal.test.base.BaseTest;
import com.grpn.demo.domain.CurrentUser;
import com.grpn.demo.service.UserService;
import com.grpn.demo.vo.req.UpdatePasswordReq;
import com.grpn.demo.vo.req.UserPageReq;
import com.grpn.demo.vo.req.UserReq;
import com.grpn.demo.vo.req.UserUpdateReq;
import com.grpn.demo.vo.resp.LoginUserResp;
import com.grpn.demo.vo.resp.UserPageResp;
import com.grpn.demo.vo.resp.UserResp;
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
public class UserControllerTest extends BaseTest {

    @Resource
    private UserController userController;
    @Resource
    private UserService userService;

    @Test
    public void create() {
        UserReq user = new UserReq();
        user.setOrgId(1L);
        user.setDepId(1L);
        user.setName("张三");
        user.setPhone("12312312311");
        user.setAccount("zhangsan");
        user.setRoleId(1L);
        user.setState(Boolean.TRUE);
        userController.create(user);
    }

    @Sql("/testsql/test_user.sql")
    @Test
    public void remove() {
        BaseResult<Void> result = userController.remove(1L);
        Assertions.assertEquals(0, result.getCode());
    }

    @Sql("/testsql/test_user.sql")
    @Test
    public void update() {
        UserUpdateReq userUpdateReq = new UserUpdateReq();
        userUpdateReq.setId(1L);
        userUpdateReq.setOrgId(1L);
        userUpdateReq.setName("3333");
        userUpdateReq.setPhone("12341111");
        userUpdateReq.setAccount("xxx");
        userUpdateReq.setRoleId(2L);
        userUpdateReq.setState(false);
        BaseResult<Void> result = userController.update(userUpdateReq);
        Assertions.assertEquals(0, result.getCode());
    }

    @Sql("/testsql/test_user.sql")
    @Test
    public void get() {
        BaseResult<UserResp> result = userController.get(1L);
        Assertions.assertEquals("张三", result.getData().getName());
    }

    @Sql("/testsql/test_user.sql")
    @Test
    public void page() {
        UserPageReq userPageReq = new UserPageReq();
        userPageReq.setOrgId(1L);
        userPageReq.setState(true);
        userPageReq.setCurrent(1);
        userPageReq.setSize(10);
        BaseResult<IPage<UserPageResp>> result = userController.page(userPageReq);
        Assertions.assertEquals(1, result.getData().getTotal());
    }

    @Sql("/testsql/test_user.sql")
    @Test
    public void resetPasswd() {
        BaseResult<String> result = userController.resetPasswd(1L);
        Assertions.assertNotNull(result.getData());
    }

    @Sql("/testsql/test_user.sql")
    @Test
    public void updateState() {
        userController.updateState(1L);
        BaseResult<UserResp> result = userController.get(1L);
        Assertions.assertFalse(result.getData().getState());
    }


    @Sql("/testsql/test_me.sql")
    @Test
    public void me() {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserId(1L);
        BaseResult<LoginUserResp> result = userController.me(currentUser);
        Assertions.assertTrue(result.isSuccess());
    }

    @Sql("/testsql/test_me.sql")
    @Test
    public void updatePassword() {
        UpdatePasswordReq updatePasswordReq = new UpdatePasswordReq();
        updatePasswordReq.setOldPassword("123456qwer");
        updatePasswordReq.setNewPassword("NVwf0oJfoGahZqv2");
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserId(1L);
        BaseResult<Void> result = userController.updatePassword(updatePasswordReq, currentUser);
        Assertions.assertTrue(result.isSuccess());
    }
}