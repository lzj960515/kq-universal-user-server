package com.grpn.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grpn.demo.domain.User;
import com.grpn.demo.vo.req.UserPageReq;
import com.grpn.demo.vo.resp.UserPageResp;
import org.apache.ibatis.annotations.Param;

/**
* 账号管理 Mapper 接口
*
* @author Zijian Liao
* @since 1.0.0
*/
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询用户列表
     * @param page 分页查询
     * @param userPageReq 查询参数
     * @return 用户列表
     */
    IPage<UserPageResp> pageUser(Page<UserPageResp> page, @Param("param") UserPageReq userPageReq);
}
