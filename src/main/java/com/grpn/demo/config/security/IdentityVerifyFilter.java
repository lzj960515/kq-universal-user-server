package com.grpn.demo.config.security;

import com.grpn.demo.domain.CurrentUser;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * 检验Token 过滤器
 *
 * @author Zijian Liao
 * @since 1.0.0
 */
public class IdentityVerifyFilter extends BasicAuthenticationFilter {

	public IdentityVerifyFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = getToken(request);
		if(StringUtils.hasText(token)){
			CurrentUser currentUser = JwtUtil.validToken(token);
			UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(
					currentUser.getUserId(), null, Collections.singleton(new SimpleGrantedAuthority(currentUser.getRoleName())));
			SecurityContextHolder.getContext().setAuthentication(authResult);
			request.setAttribute(JwtUtil.USER, currentUser);
		}
		chain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (token == null) {
			return request.getParameter(JwtUtil.TOKEN);
		} else {
			return token.replace("Bearer", "").trim();
		}
	}

}
