package com.lambert.web.feedback;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lambert.common.service.facade.util.SessionUtils;
import com.lambert.common.uitl.CookieUtils;
import com.lambert.core.service.token.TokenManager;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@Autowired
	private TokenManager tokenManager;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String logout(String backUrl, HttpServletRequest request) {
		String token = CookieUtils.getCookie(request, "token");
		if (StringUtils.isNotBlank(token)) {
			tokenManager.remove(token);
		}
		SessionUtils.invalidate(request);
		return "redirect:" + (StringUtils.isBlank(backUrl) ? "/login" : backUrl);
	}
}
