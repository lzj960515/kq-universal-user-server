package com.grpn.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.kqinfo.universal.common.response.BaseResult;
import com.kqinfo.universal.test.base.BaseTest;
import com.grpn.demo.domain.Role;
import com.grpn.demo.vo.req.RolePageReq;
import com.grpn.demo.vo.req.RoleReq;
import com.grpn.demo.vo.req.RoleUpdateReq;
import com.grpn.demo.vo.resp.RoleResp;
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
public class RoleControllerTest extends BaseTest {

    @Resource
    private RoleController roleController;

    @Test
    public void create() {
        RoleReq role = new RoleReq();
        role.setName("管理员");
        role.setState(Boolean.TRUE);
        role.setDataAuth(1);
        role.setAuthIds(Lists.newArrayList(1L,2L,3L));
        BaseResult<Void> result = roleController.create(role);
        Assertions.assertTrue(result.isSuccess());
    }

    @Sql("/testsql/test_role.sql")
    @Test
    public void remove() {
        BaseResult<Void> result = roleController.remove(1L);
        Assertions.assertTrue(result.isSuccess());
    }

    @Sql("/testsql/test_role.sql")
    @Test
    public void update() {
        RoleUpdateReq role = new RoleUpdateReq();
        role.setName("运营");
        role.setState(false);
        role.setDataAuth(2);
        role.setAuthIds(Lists.newArrayList(1L,2L,3L));
        role.setId(1L);
        BaseResult<Void> result = roleController.update(role);
        Assertions.assertTrue(result.isSuccess());
    }

    @Sql("/testsql/test_role.sql")
    @Test
    public void get() {
        BaseResult<RoleResp> result = roleController.get(1L);
        Assertions.assertTrue(result.isSuccess());
    }

    @Sql("/testsql/test_role.sql")
    @Test
    public void page() {
        RolePageReq rolePageReq = new RolePageReq();
        rolePageReq.setName("管理员");
        rolePageReq.setState(Boolean.TRUE);
        rolePageReq.setCurrent(1);
        rolePageReq.setSize(10);
        BaseResult<IPage<Role>> result = roleController.page(rolePageReq);
        Assertions.assertEquals(1, result.getData().getTotal());
    }

    @Sql("/testsql/test_role.sql")
    @Test
    public void updateState() {
        roleController.updateState(1L);
        BaseResult<RoleResp> result = roleController.get(1L);
        Assertions.assertFalse(result.getData().getState());
    }
}