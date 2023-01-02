package com.grpn.demo.config.security.handle;

import com.kqinfo.universal.common.response.BaseResult;
import com.kqinfo.universal.common.response.BaseResultCode;
import com.grpn.demo.config.security.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限校验异常处理
 *
 * @author Zijian Liao
 * @since 1.0.0
 */
public class OauthAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AccessDeniedException e) throws IOException, ServletException {
		ResponseUtil.sendMessage(httpServletResponse, BaseResult.failure(BaseResultCode.PERMISSION_NO_ACCESS));
	}

}
