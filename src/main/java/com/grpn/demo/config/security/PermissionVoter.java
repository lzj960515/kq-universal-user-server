package com.grpn.demo.config.security;

import com.kqinfo.universal.common.exception.BusinessException;
import com.kqinfo.universal.common.response.BaseResultCode;
import com.grpn.demo.domain.Auth;
import com.grpn.demo.domain.Role;
import com.grpn.demo.service.AuthService;
import com.grpn.demo.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 自定义权限过滤器
 * @author Zijian Liao
 * @since 1.0.0
 */
@Slf4j
public class PermissionVoter implements AccessDecisionVoter<FilterInvocation> {


    @Autowired
    RequestMappingHandlerMapping handlerMapping;

    @Resource
    private AuthService authService;
    @Resource
    private RoleService roleService;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        if (authentication == null) {
            return ACCESS_DENIED;
        }
        HttpServletRequest request = object.getRequest();
        try {
            handlerMapping.getHandler(request);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        String method = request.getMethod();
        String urlPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        //获取所有权限
        List<Auth> auths = authService.listAllInterface();
        //请求url是否存在权限列表中
        if (inAllAcl(auths, urlPattern, method)) {
            Long userId = (Long) authentication.getPrincipal();
            Role role = roleService.getByUserId(userId);
            if(role == null){
                throw new BusinessException(BaseResultCode.PERMISSION_NO_ACCESS);
            }
            List<Auth> roleAuths = authService.listRoleInterface(role.getId());
            for (Auth acl : roleAuths) {
                if(acl.getUrl().equals(urlPattern) && acl.getMethod().equalsIgnoreCase(method)){
                    return ACCESS_GRANTED;
                }
            }
            return ACCESS_DENIED;
        }
        return ACCESS_ABSTAIN;
    }

    private boolean inAllAcl(List<Auth> auths, String urlPattern, String method){
        for (Auth acl : auths) {
            if(acl.getUrl().equals(urlPattern) && acl.getMethod().equalsIgnoreCase(method)){
                return true;
            }
        }
        return false;
    }
}
