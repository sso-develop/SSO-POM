package com.lambert.common.service.facade.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lambert.common.service.facade.model.LoginInfo;
import com.lambert.common.service.facade.model.SessionUser;
import com.lambert.common.service.facade.util.SessionUtils;


public class SSOFilter extends ClientFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SSOFilter.class);
	
	// sso授权回调参数token名称
	public static final String SSO_TOKEN_NAME = "__vt_param__";

	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			String token = getLocalToken(request);
			if (token == null) {
				if (getParameterToken(request) != null) {
					// 再跳转一次当前URL，以便去掉URL中token参数
					response.sendRedirect(request.getRequestURL().toString());
				}else redirectLogin(request, response);
			}
			else if (isLogined(token))chain.doFilter(request, response);
			else redirectLogin(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 跳转登录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (isAjaxRequest(request)) {
			throw new Exception("未登录或已超时");
			/*throw new ServiceException(SsoResultCode.SSO_TOKEN_ERROR, "未登录或已超时");*/
		}else{
			String ssoLoginUrl = new StringBuilder().append(ssoServerUrl).append("/login?backUrl=").append(request.getRequestURL()).append("&appCode=").append(ssoAppCode).toString();
			response.sendRedirect(ssoLoginUrl);
		}
	}
	
	/**
	 * 获取Session中token
	 * 
	 * @param request
	 * @return
	 */
	private String getLocalToken(HttpServletRequest request) {
		SessionUser sessionUser = SessionUtils.getSessionUser(request);
		return sessionUser == null ? null : sessionUser.getToken();
	}
	/**
	 * 获取服务端回传token参数且验证
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private String getParameterToken(HttpServletRequest request) throws IOException {
		String token = request.getParameter(SSO_TOKEN_NAME);
		if (token != null) {
			LoginInfo rpcUser = authenticationRpcService.findAuthInfo(token);
			 if(rpcUser != null) {
				invokeAuthenticationInfoInSession(request, token, rpcUser.getAccount(),rpcUser.getUserId());
				return token;
			}
		}
		return token;
	}
	/**
	 * 是否已登录
	 * 
	 * @param token
	 * @return
	 */
	private boolean isLogined(String token) {
		//return authenticationRpcService.validate(token);
		return true;
	}

	protected boolean isAjaxRequest(HttpServletRequest request) {
		  //判断是否为ajax请求，默认不是  
        boolean isAjaxRequest = false;  
        if(!StringUtils.isBlank(request.getHeader("x-requested-with")) && request.getHeader("x-requested-with").equals("XMLHttpRequest")){  
            isAjaxRequest = true;  
        }
        return isAjaxRequest;
	}

	
	/**
	 * 保存认证信息到Session
	 * 
	 * @param token
	 * @param account
	 * @param profile
	 */
	private void invokeAuthenticationInfoInSession(HttpServletRequest request, String token, String account,Long userId) {
		LOGGER.info("【invokeAuthenticationInfoInSession】：token = "+token+"  account = "+account);
		SessionUtils.setSessionUser(request, new SessionUser(token, account,userId));
	}
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}
