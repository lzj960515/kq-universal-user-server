package com.grpn.demo.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kqinfo.universal.common.response.BaseResult;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Zijian Liao
 * @since v1.0.0
 */
@Slf4j
public class ResponseUtil {

	private static final String CONTENT_TYPE = "application/json;charset=utf-8";

	private ResponseUtil(){}

	public static void sendMessage(HttpServletResponse response, BaseResult<?> baseResult) {
		response.setContentType(CONTENT_TYPE);
		response.setStatus(HttpServletResponse.SC_OK);
		try (PrintWriter out = response.getWriter()) {
			out.write(new ObjectMapper().writeValueAsString(baseResult));
			out.flush();
		}
		catch (IOException e) {
			log.error("使用ResponseUtil发送消息失败:{}", e.getMessage(), e);
		}
	}

}
