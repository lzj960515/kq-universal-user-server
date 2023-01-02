package com.grpn.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kqinfo.universal.common.response.BaseResult;
import com.kqinfo.universal.yapi.annotation.YapiApi;
import com.kqinfo.universal.yapi.annotation.YapiOperation;
import com.kqinfo.universal.yapi.annotation.YapiParameterObject;
import com.grpn.demo.domain.Role;
import com.grpn.demo.service.RoleService;
import com.grpn.demo.vo.req.RolePageReq;
import com.grpn.demo.vo.req.RoleReq;
import com.grpn.demo.vo.req.RoleUpdateReq;
import com.grpn.demo.vo.resp.RoleResp;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/role")
public class RoleController {

    @Resource
    protected RoleService service;

    /**
     * 新增
     * @param domain 领域模型
     * @return {@link BaseResult}
     */
    @YapiOperation(value = "新增")
    @PostMapping
    public BaseResult<Void> create(@Valid @RequestBody RoleReq domain) {
        service.createRole(domain);
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
        service.removeRole(id);
        return BaseResult.success("删除成功");
    }

    /**
     * 修改
     * @param domain 领域模型
     * @return {@link BaseResult}
     */
    @YapiOperation(value = "修改")
    @PutMapping
    public BaseResult<Void> update(@Valid @RequestBody RoleUpdateReq domain) {
        service.updateRole(domain);
        return BaseResult.success("编辑成功");
    }

    /**
     * 获取
     * @param id {@code Long}
     * @return {@link BaseResult}
     */
    @YapiOperation(value = "获取")
    @GetMapping("/{id}")
    public BaseResult<RoleResp> get(@PathVariable Long id) {
        return BaseResult.success(service.getRole(id));
    }

    /**
     * 分页
     * @return {@link BaseResult}
     */
    @YapiOperation(value = "分页")
    @GetMapping
    public BaseResult<IPage<Role>> page(@YapiParameterObject RolePageReq domain) {
        return BaseResult.success(service.pageRole(domain));
    }

    @YapiOperation(value = "修改状态")
    @PutMapping("/update-state/{id}")
    public BaseResult<Void> updateState(@PathVariable Long id){
        service.lambdaUpdate().setSql("state = 1 - state")
                .eq(Role::getId, id)
                .update();
        return BaseResult.success();
    }

}
