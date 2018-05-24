package com.lambert.common.service.facade.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import com.lambert.common.service.facade.model.SessionPermission;
import com.lambert.common.service.facade.model.SessionUser;

public class SessionUtils {
	/**
	 * 用户信息
	 */
	public static final String SESSION_USER = "_sessionUser";

	/**
	 * 用户权限
	 */
	public static final String SESSION_USER_PERMISSION = "_sessionUserPermission";

	public static SessionUser getSessionUser(HttpServletRequest request) {
		return (SessionUser) WebUtils.getSessionAttribute(request, SESSION_USER);
	}

	public static void setSessionUser(HttpServletRequest request, SessionUser sessionUser) {
		WebUtils.setSessionAttribute(request, SESSION_USER, sessionUser);
	}

	public static SessionPermission getSessionPermission(HttpServletRequest request) {
		return (SessionPermission) WebUtils.getSessionAttribute(request, SESSION_USER_PERMISSION);
	}

	public static void setSessionPermission(HttpServletRequest request, SessionPermission sessionPermission) {
		WebUtils.setSessionAttribute(request, SESSION_USER_PERMISSION, sessionPermission);
	}
	
	public static void invalidate(HttpServletRequest request){
		setSessionUser(request, null);
		setSessionPermission(request, null);
	}
	/************************************************************/
	
	public static SessionUser getSessionUser() {
		return (SessionUser) getSession().getAttribute(SESSION_USER);
	}
	
	public static HttpSession getSession() { 
	    HttpSession session = null; 
	    try { 
	        session = getRequest().getSession(); 
	    } catch (Exception e) {} 
	        return session; 
	} 
	
	public static HttpServletRequest getRequest() { 
	    ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
	    return attrs.getRequest(); 
	} 
}
