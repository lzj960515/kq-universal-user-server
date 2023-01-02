package com.grpn.demo.controller;

import com.kqinfo.universal.common.response.BaseResult;
import com.kqinfo.universal.yapi.annotation.YapiApi;
import com.kqinfo.universal.yapi.annotation.YapiOperation;
import com.grpn.demo.service.LoginService;
import com.grpn.demo.vo.req.LoginReq;
import com.grpn.demo.vo.resp.LoginResp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 登录管理
 *
 * @author Zijian Liao
 * @since 1.0.0
 */
@YapiApi(value = "登录管理")
@RestController
@RequestMapping("/open/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @YapiOperation("管理端登录")
    @PostMapping("/admin")
    public BaseResult<LoginResp> adminLogin(@RequestBody @Valid LoginReq loginReq){
        return BaseResult.success(loginService.adminLogin(loginReq));
    }
}
