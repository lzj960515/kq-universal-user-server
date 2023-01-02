package com.grpn.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kqinfo.universal.common.response.BaseResult;
import com.kqinfo.universal.common.response.BaseResultCode;
import com.kqinfo.universal.common.util.PageBean;
import com.kqinfo.universal.yapi.annotation.YapiApi;
import com.kqinfo.universal.yapi.annotation.YapiOperation;
import com.kqinfo.universal.yapi.annotation.YapiParameterObject;
import com.grpn.demo.domain.Organization;
import com.grpn.demo.service.OrganizationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
* 机构管理 前端控制器
*
* @author Zijian Liao
* @since 1.0.0
*/
@YapiApi(value = "机构管理")
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Resource
    protected OrganizationService service;

    /**
    * 新增
    * @param domain 领域模型
    * @return {@link BaseResult}
    */
    @YapiOperation(value = "新增")
    @PostMapping
    public BaseResult<Void> create(@Valid @RequestBody Organization domain) {
        // 业务逻辑
        boolean created = service.create(domain);
        if (created) {
            return BaseResult.success("创建成功");
        }

        return BaseResult.failure(BaseResultCode.FAILURE);
    }

    /**
    * 删除
    * @param id {@code Long}
    * @return {@link BaseResult}
    */
    @YapiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public BaseResult<Void> remove(@PathVariable Long id) {
        // 业务逻辑
        boolean deleted = service.remove(id);
        if (deleted) {
            return BaseResult.success("删除成功");
        }

        return BaseResult.failure(BaseResultCode.FAILURE);
    }

    /**
    * 修改
    * @param domain 领域模型
    * @return {@link BaseResult}
    */
    @YapiOperation(value = "修改")
    @PutMapping
    public BaseResult<Void> update(@Valid @RequestBody Organization domain) {
        // 业务逻辑
        boolean updated = service.update(domain);
        if (updated) {
            return BaseResult.success("编辑成功");
        }

        return BaseResult.failure(BaseResultCode.FAILURE);
    }

    /**
    * 获取
    * @param id {@code Long}
    * @return {@link BaseResult}
    */
    @YapiOperation(value = "获取")
    @GetMapping("/{id}")
    public BaseResult<Organization> get(@PathVariable Long id) {
        Organization domain = service.get(id);
        return BaseResult.success(domain);
    }

    /**
    * 分页
    * @param pageNum {@code int} 页码
    * @param numPerPage {@code int} 笔数
    * @return {@link BaseResult}
    */
    @YapiOperation(value = "分页")
    @GetMapping
    public BaseResult<PageBean<Organization>> page(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int numPerPage, @YapiParameterObject Organization domain) {
        IPage<Organization> page = service.page(pageNum, numPerPage, domain);
        return BaseResult.success(PageBean.of(page));
    }
}
