package com.grpn.demo.config.security.handle;

import com.kqinfo.universal.common.response.BaseResult;
import com.kqinfo.universal.common.response.BaseResultCode;
import com.grpn.demo.config.security.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份校验异常处理
 *
 * @author Zijian Liao
 * @since 1.0.0
 */
public class OauthAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
		ResponseUtil.sendMessage(httpServletResponse, BaseResult.failure(BaseResultCode.TOKEN_EXPIRATION));
	}

}
