package com.lambert.common.service.facade.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.lambert.common.service.facade.rpc.AuthenticationRpcService;
import com.lambert.common.service.facade.util.SpringUtils;

/**
 * 单点登录权限系统Filter基类
 * 
 */
public abstract class ClientFilter implements Filter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientFilter.class);
	
	// 单点登录服务端URL
	protected String ssoServerUrl;
	// 当前应用关联权限系统的应用编码
	protected String ssoAppCode;
	// 单点登录服务端提供的RPC服务，由Spring容器注入
	protected AuthenticationRpcService authenticationRpcService;
	
	// 排除拦截
	protected List<String> excludeList = null;
		
	private PathMatcher pathMatcher = null;
	
	public void init(FilterConfig filterConfig) {
		ssoServerUrl = ClientFilterConfig.clientFilterProperties.getSsoServerUrl();
		ssoAppCode = ClientFilterConfig.clientFilterProperties.getSsoAppCode();
		if (StringUtils.isBlank(ssoServerUrl)) {
			throw new IllegalArgumentException("ssoServerUrl不能为空");
		}
		if (StringUtils.isBlank(ssoAppCode)) {
			throw new IllegalArgumentException("ssoAppCode不能为空");
		}
		
		if ((authenticationRpcService = SpringUtils.getBean(AuthenticationRpcService.class)) == null) {
			throw new IllegalArgumentException("authenticationRpcService注入失败");
		}
		
		/*ssoServerUrl = "http://localhost:8081";
		ssoAppCode = "lambert";*/
		
		
		String excludes = filterConfig.getInitParameter("excludes");
		if (StringUtils.isNotBlank(excludes)) {
			excludeList = Arrays.asList(excludes.split(","));
			pathMatcher = new AntPathMatcher();
		}
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (matchExcludePath(httpRequest.getServletPath()))
			chain.doFilter(request, response);
		else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			//try {
			doFilter(httpRequest, httpResponse, chain);
			/*}
			catch (ServiceException e) {
				httpResponse.setContentType("application/json;charset=UTF-8");
				httpResponse.setStatus(HttpStatus.OK.value());
				PrintWriter writer = httpResponse.getWriter();
				writer.write(JSON.toJSONString(Result.create(e.getCode()).setMessage(e.getMessage())));
				writer.flush();
				writer.close();
			}*/
		}
		
	}
	private boolean matchExcludePath(String path) {
		boolean flag = false;
		if (excludeList != null) {
			for (String ignore : excludeList) { 
				//LOGGER.info("【matchExcludePath】 ignore = "+ignore+"  path = "+path+"  "+pathMatcher.match(ignore, path));
				if (pathMatcher.match(ignore, path)) {
					flag = true;
					break;
				}
			}
		}
		//LOGGER.info("【matchExcludePath】 flag = "+flag);
		return flag;
	}

	
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException,ServletException;
}