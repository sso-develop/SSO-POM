/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lambert.core.service.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lambert.common.uitl.LoggerUtil;



/**
 * 表单验证（包含验证码）过滤类
 * 
 * @author ThinkGem
 * @version 2014-5-19 org.apache.shiro.web.filter.authc.FormAuthenticationFilter
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FormAuthenticationFilter.class);

	public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
	public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
	public static final String DEFAULT_MESSAGE_PARAM = "message";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
	private String messageParam = DEFAULT_MESSAGE_PARAM;
	private String redirectUrl = "/";


	
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		redirectUrl = request.getParameter("redirectUrl")==null?"/":request.getParameter("redirectUrl");
		//LoggerUtil.info(LOGGER, "【FormAuthenticationFilter createToken】：redirectUrl = "+redirectUrl);
		String username = getUsername(request); 
		String password = getPassword(request);
		if (password == null) {
			password = "";
		}
		/*
		 * boolean rememberMe = isRememberMe(request); String host =
		 * StringUtils.getRemoteAddr((HttpServletRequest)request); String
		 * captcha = getCaptcha(request); boolean mobile =
		 * isMobileLogin(request);
		 */

		boolean rememberMe = true;
		String captcha = getCaptcha(request);
		boolean mobile = isMobileLogin(request);
		String host = "";
		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, mobile);
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	public String getMobileLoginParam() {
		return mobileLoginParam;
	}

	protected boolean isMobileLogin(ServletRequest request) {
		return WebUtils.isTrue(request, getMobileLoginParam());
	}

	public String getMessageParam() {
		return messageParam;
	}

	/**
	 * 登录成功之后跳转URL
	 */
	public String getSuccessUrl() {
		return super.getSuccessUrl();
		//return redirectUrl;
	}

	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		// Principal p = UserUtils.getPrincipal();
		// if (p != null && !p.isMobileLogin()){
		WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
		// }else{
		// super.issueSuccessRedirect(request, response);
		// }
	}

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		String className = e.getClass().getName(), message = "";
		
		if (IncorrectCredentialsException.class.getName().equals(className) ) {
			message = "密码错误, 请重试.";
		} else if(UnknownAccountException.class.getName().equals(className)){
			message = "用户错误, 请重试.";
		}/*
			 * else if (e.getMessage() != null &&
			 * StringUtils.startsWith(e.getMessage(), "msg:")){ message =
			 * StringUtils.replace(e.getMessage(), "msg:", ""); }
			 */
		else {
			message = "系统出现点问题，请稍后再试！";
			//e.printStackTrace(); // 输出到控制台
		}
		LoggerUtil.info(LOGGER,"【FormAuthenticationFilter onLoginFailure】:"+message);
		request.setAttribute(getFailureKeyAttribute(), className);
		request.setAttribute(getMessageParam(), message);
		return true;
	}
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		return super.onLoginSuccess(token, subject, request, response);

	}

}