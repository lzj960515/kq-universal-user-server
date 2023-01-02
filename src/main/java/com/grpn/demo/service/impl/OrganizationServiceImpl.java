package com.grpn.demo.service.impl;

import com.kqinfo.universal.mybatisplus.base.BaseServiceImpl;
import com.grpn.demo.domain.Organization;
import com.grpn.demo.mapper.OrganizationMapper;
import com.grpn.demo.service.OrganizationService;
import org.springframework.stereotype.Service;

/**
* 机构管理 服务实现类
*
* @author Zijian Liao
* @since 1.0.0
*/
@Service
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

}
