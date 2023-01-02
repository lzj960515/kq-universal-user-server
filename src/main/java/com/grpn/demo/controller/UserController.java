package com.grpn.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kqinfo.universal.common.response.BaseResult;
import com.kqinfo.universal.yapi.annotation.YapiApi;
import com.kqinfo.universal.yapi.annotation.YapiOperation;
import com.kqinfo.universal.yapi.annotation.YapiParameter;
import com.kqinfo.universal.yapi.annotation.YapiParameterObject;
import com.grpn.demo.config.security.JwtUtil;
import com.grpn.demo.domain.CurrentUser;
import com.grpn.demo.domain.User;
import com.grpn.demo.service.UserService;
import com.grpn.demo.vo.req.UpdatePasswordReq;
import com.grpn.demo.vo.req.UserPageReq;
import com.grpn.demo.vo.req.UserReq;
import com.grpn.demo.vo.req.UserUpdateReq;
import com.grpn.demo.vo.resp.LoginUserResp;
import com.grpn.demo.vo.resp.UserPageResp;
import com.grpn.demo.vo.resp.UserResp;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
* 账号管理 前端控制器
*
* @author Zijian Liao
* @since 1.0.0
*/
@YapiApi(value = "账号管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    protected UserService service;

    /**
     * 新增
     * @param domain 领域模型
     * @return {@link BaseResult}
     */
    @YapiOperation(value = "新增")
    @PostMapping
    public BaseResult<Void> create(@Valid @RequestBody UserReq domain) {
        service.createUser(domain);
        return BaseResult.success("创建成功");
    }

    /**
     * 删除
     * @param id {@code Long}
     * @return {@link BaseResult}
     */
    @YapiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public BaseResult<Void> remove(@PathVariable Long id) {
        service.removeUser(id);
        return BaseResult.success();
    }

    /**
     * 修改
     * @param domain 领域模型
     * @return {@link BaseResult}
     */
    @YapiOperation(value = "修改")
    @PutMapping
    public BaseResult<Void> update(@Valid @RequestBody UserUpdateReq domain) {
        service.updateUser(domain);
        return BaseResult.success();
    }

    /**
     * 获取
     * @param id {@code Long}
     * @return {@link BaseResult}
     */
    @YapiOperation(value = "获取用户详情")
    @GetMapping("/{id}")
    public BaseResult<UserResp> get(@PathVariable Long id) {
        return BaseResult.success(service.getUser(id));
    }

    /**
     * 分页
     * @return {@link BaseResult}
     */
    @YapiOperation(value = "分页")
    @GetMapping
    public BaseResult<IPage<UserPageResp>> page(@YapiParameterObject UserPageReq domain) {
        return BaseResult.success(service.pageUser(domain));
    }

    @YapiOperation(value = "重置密码")
    @PutMapping("/reset-passwd/{id}")
    public BaseResult<String> resetPasswd(@PathVariable Long id){
        return BaseResult.success("密码重置成功", service.resetPasswd(id));
    }


    @YapiOperation(value = "修改状态")
    @PutMapping("/update-state/{id}")
    public BaseResult<Void> updateState(@PathVariable Long id){
        service.lambdaUpdate().setSql("state = 1 - state")
                .eq(User::getId, id)
                .update();
        return BaseResult.success();
    }

    @YapiOperation(value = "查询当前登录用户信息", hidden = true)
    @GetMapping("/me")
    public BaseResult<LoginUserResp> me(@YapiParameter(value = "用户信息", hidden = true) @RequestAttribute(JwtUtil.USER) CurrentUser currentUser) {
        return BaseResult.success(service.me(currentUser));
    }

    @YapiOperation(value = "修改密码")
    @PutMapping("/update-passwd")
    public BaseResult<Void> updatePassword(@RequestBody UpdatePasswordReq updatePasswordReq, @YapiParameter(value = "用户信息", hidden = true)  @RequestAttribute(JwtUtil.USER)  CurrentUser currentUser){
        service.updatePassword(currentUser.getUserId(), updatePasswordReq);
        return BaseResult.success();
    }
}
