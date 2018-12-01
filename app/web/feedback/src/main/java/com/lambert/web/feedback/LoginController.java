package com.lambert.web.feedback;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.lambert.biz.uums.UumsUserInfoManager;
import com.lambert.biz.uums.impl.UumsUserInfoManagerImpl;
import com.lambert.biz.uums.model.UumsUserInfoModel;
import com.lambert.common.service.facade.filter.SSOFilter;
import com.lambert.common.service.facade.model.LoginInfo;
import com.lambert.common.service.facade.model.SessionUser;
import com.lambert.common.service.facade.result.SSOResultCode;
import com.lambert.common.service.facade.util.SessionUtils;
import com.lambert.common.uitl.CookieUtils;
import com.lambert.common.uitl.provider.IdProvider;
import com.lambert.common.uitl.result.DefaultResult;
import com.lambert.core.service.token.TokenManager;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	// 登录页
	private static final String LOGIN_PATH = "login";
	private static final String INDEX_PATH = "/main.htm";
	
	
	@Autowired
	private TokenManager tokenManager;
	@Autowired
	private UumsUserInfoManager uumsUserInfoManager;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(String backUrl,String appCode,HttpServletRequest request) {
		String token = CookieUtils.getCookie(request, "token");
		if (token == null) {
			return goLoginPath(backUrl, appCode, request);
		}
		else {
			LoginInfo loginUser = tokenManager.validate(token);
			if (loginUser != null) {
				return "redirect:" + authBackUrl(backUrl, token);
			}
			else {
				return goLoginPath(backUrl, appCode, request);
			}
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String login(String backUrl, String appCode,String username,String password,String captcha,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		DefaultResult<UumsUserInfoModel> result = uumsUserInfoManager.queryUumsUserInfoCountByOperatorName(username);
		if(result.getResultCode().getCode() != SSOResultCode.SUCCESS.getCode()){
			request.setAttribute("errorMessage", result.getMessage());
			return goLoginPath(backUrl, appCode, request);
		}
		
		UumsUserInfoModel uumsUserInfoModel = result.getValue();
		if (uumsUserInfoModel == null) {
			request.setAttribute("errorMessage", "用户不存在，请重新输入");
			return goLoginPath(backUrl, appCode, request);
		}
		if(!uumsUserInfoModel.getPassword().equals(password)){
			request.setAttribute("errorMessage", "密码不正确，请重新输入");
			return goLoginPath(backUrl, appCode, request);
		}
		UumsUserInfoModel user = (UumsUserInfoModel) result.getValue();
		LoginInfo loginUser = new LoginInfo(user.getId(), user.getOperatorName());
		//~ 获取 cookie 中的 token
		String token = CookieUtils.getCookie(request, "token");
		//~ 若token为null，则没有登录
		if (StringUtils.isBlank(token) || tokenManager.validate(token) == null) {// 没有登录的情况
			//~ 创建用户token,同时保存到缓存中
			token = createToken(loginUser);
			//~ 添加cookie用户token
			addTokenInCookie(token, request, response);
			LoginInfo u = tokenManager.validate(token);
		}
		SessionUser sessionUser = new SessionUser(token, user.getOperatorName(),user.getId());
		SessionUtils.setSessionUser(request, sessionUser);
		// 跳转到原请求
		backUrl = URLDecoder.decode(backUrl, "utf-8");
		return "redirect:" + authBackUrl(backUrl, token);
		
	}
	
	private String goLoginPath(String backUrl, String appCode, HttpServletRequest request) {
		if(StringUtils.isBlank(backUrl) || backUrl.equals("null")){
			backUrl = INDEX_PATH;
		}
		request.setAttribute("backUrl", backUrl);
		request.setAttribute("appCode", appCode);
		return LOGIN_PATH;
	}

	private String authBackUrl(String backUrl, String token) {
		
		if(StringUtils.isBlank(backUrl) || backUrl.equals("null")){
			backUrl = INDEX_PATH;
		}
		
		StringBuilder sbf = new StringBuilder(backUrl);
		if (backUrl.indexOf("?") > 0) {
			sbf.append("&");
		}
		else {
			sbf.append("?");
		}
		sbf.append(SSOFilter.SSO_TOKEN_NAME).append("=").append(token);
		return sbf.toString();
	}

	/**
	 * 创建用户token,同时保存到缓存中
	 * @param loginUser 登录用户信息
	 * @return token
	 */
	private String createToken(LoginInfo loginUser) {
		// 生成token
		String token = IdProvider.createUUIDId();
		// 缓存中添加token对应User
		tokenManager.addToken(token, loginUser);
		return token;
	}

	/**
	 *  向cookie中添加token
	 * @param token
	 * @param request
	 * @param response
	 */
	private void addTokenInCookie(String token, HttpServletRequest request, HttpServletResponse response) {
		// Cookie添加token
		Cookie cookie = new Cookie("token", token);
		cookie.setPath("/");
		if ("https".equals(request.getScheme())) {
			cookie.setSecure(true);
		}
		//cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}

}
