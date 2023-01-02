package com.grpn.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kqinfo.universal.mybatisplus.base.BaseService;
import com.grpn.demo.domain.Role;
import com.grpn.demo.vo.req.RolePageReq;
import com.grpn.demo.vo.req.RoleReq;
import com.grpn.demo.vo.req.RoleUpdateReq;
import com.grpn.demo.vo.resp.RoleResp;
import org.springframework.lang.Nullable;

/**
* 账号管理 服务类
*
* @author Zijian Liao
* @since 1.0.0
*/
public interface RoleService extends BaseService<Role> {

    /**
     * 分页
     *
     * @param domain  领域模型
     * @return 分页数据
     */
    IPage<Role> pageRole(RolePageReq domain);

    /**
     * 获取角色信息
     * @param userId 用户id
     * @return 角色
     */
    @Nullable
    Role getByUserId(Long userId);

    /**
     * 创建角色
     * @param roleReq 角色信息
     */
    void createRole(RoleReq roleReq);

    /**
     * 删除角色
     * @param id 角色id
     */
    void removeRole(Long id);

    /**
     * 修改角色
     * @param roleUpdateReq 角色信息
     */
    void updateRole(RoleUpdateReq roleUpdateReq);

    /**
     * 获取角色信息
     * @param id 角色id
     * @return 角色信息
     */
    RoleResp getRole(Long id);
}
