package com.lambert.web.feedback;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lambert.common.service.facade.model.SessionUser;
import com.lambert.common.service.facade.util.SessionUtils;

@Controller
public class IndexController {
	
	
	
	@RequestMapping(value="/main.htm",method = RequestMethod.GET) 
	public String main(HttpServletRequest request, ServletResponse response, Model model){
		//SessionUser sessionUser = SessionUtils.getSessionUser(request);
		SessionUser sessionUser = SessionUtils.getSessionUser();
		model.addAttribute("userName", sessionUser.getAccount());
     	return "index";
		
	}
	
}
