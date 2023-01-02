package com.grpn.demo.config.security;

import com.grpn.demo.config.security.handle.OauthAccessDeniedHandler;
import com.grpn.demo.config.security.handle.OauthAuthenticationEntryPoint;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zijian Liao
 * @since 1.0.0
 *
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Resource
	private SecurityProperties securityProperties;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
				// 关闭跨站请求防护
				.csrf().disable()
				.authorizeRequests(request -> request.requestMatchers(CorsUtils::isPreFlightRequest)
						.permitAll()
						//自定义accessDecisionManager
						.accessDecisionManager(accessDecisionManager())
                        // 其他的需要授权后访问
						.anyRequest().authenticated())
				// 增加自定义验证认证过滤器
				.addFilterBefore(verifyFilter(), UsernamePasswordAuthenticationFilter.class)
				// 禁用session
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// 增加自定义异常
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
				.accessDeniedHandler(accessDeniedHandler());
		// 开启xss防护
		http.headers().cacheControl().and().xssProtection().xssProtectionEnabled(true);

		// 加载jwt key
		JwtUtil.loadKey();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		List<String> ignoreUrls = securityProperties.getIgnoreUrls();
		web.ignoring().antMatchers(ignoreUrls.toArray(new String[0]));
	}

	@Bean
	public IdentityVerifyFilter verifyFilter() throws Exception {
		return new IdentityVerifyFilter(super.authenticationManager());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public OauthAccessDeniedHandler accessDeniedHandler() {
		return new OauthAccessDeniedHandler();
	}

	@Bean
	public OauthAuthenticationEntryPoint authenticationEntryPoint() {
		return new OauthAuthenticationEntryPoint();
	}

	@Bean
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters
				= Arrays.asList(
				new WebExpressionVoter(),
				permissionVoter());
		return new UnanimousBased(decisionVoters);
	}

	@Bean
	public PermissionVoter permissionVoter(){
		return new PermissionVoter();
	}

}
