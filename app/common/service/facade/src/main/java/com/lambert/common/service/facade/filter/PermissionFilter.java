/**
 * LAMBERT Lin. Zekuan Lin
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package com.lambert.common.service.facade.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.lambert.common.service.facade.model.Permission;
import com.lambert.common.service.facade.model.SessionPermission;
import com.lambert.common.service.facade.model.SessionUser;
import com.lambert.common.service.facade.util.ApplicationPermissionUtils;
import com.lambert.common.service.facade.util.SessionUtils;

/**
 * 权限控制Filter
 * @author Administrator	
 * @version $Id: PermissionFilter.java, v 0.1 2017年12月19日 下午8:55:49 Administrator Exp $
 */
public class PermissionFilter extends ClientFilter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionFilter.class);

	/** 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}
	
	@Override
	public void init(FilterConfig filterConfig) {
		super.init(filterConfig);
		ApplicationPermissionUtils.initApplicationPermissions(authenticationRpcService, ssoAppCode);
	}

	/** 
	 * @see com.lambert.common.service.facade.filter.ClientFilter#doFilter(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		if (isPermitted(request)){
			chain.doFilter(request, response);
		}else{
			System.err.println("没有登录权限");
			String errorUrl = ssoServerUrl + "/loginPermissionError?code="+ssoAppCode;
			response.sendRedirect(errorUrl);
		}
			
	}
	/**
	 * 是否有权限
	 * 
	 * @param request
	 * @return
	 */
	private boolean isPermitted(HttpServletRequest request) {
		Set<String> permissionSet = getLocalPermissionSet(request);
		LOGGER.info("【isPermitted】 permissionSet ：" +permissionSet);
		return permissionSet.contains("SSO_LOGIN_PERMISSION");
	}
	private Set<String> getLocalPermissionSet(HttpServletRequest request) {
		SessionPermission sessionPermission = SessionUtils.getSessionPermission(request);
		LOGGER.info("【getLocalPermissionSet】 sessionPermission ：" +JSON.toJSONString(sessionPermission));
		if (sessionPermission == null || sessionPermission.getPermissionSet().size() <= 0) {
			sessionPermission = invokePermissionInSession(request);
		}
		return sessionPermission.getPermissionSet();
	}
	
	/**
	 * 保存权限信息
	 * 
	 * @param
	 * @return
	 */
	public SessionPermission invokePermissionInSession(HttpServletRequest request) {
		SessionUser user = SessionUtils.getSessionUser(request);
		LOGGER.info("【invokePermissionInSession】 重新获取权限，token[{}] user[{}] ssoAppCode[{}]",user.getToken(),user.getAccount(),ssoAppCode);
		List<Permission> dbList = authenticationRpcService.findPermissionList(user.getToken(), ssoAppCode);
		Set<String> operateSet = new HashSet<String>();
		for (Permission menu : dbList) {
			operateSet.add(menu.getCode());
		}

		SessionPermission sessionPermission = new SessionPermission();
		// 保存登录用户权限列表
		sessionPermission.setPermissionSet(operateSet);
		
		SessionUtils.setSessionPermission(request, sessionPermission);

		return sessionPermission;
	}
	
}
