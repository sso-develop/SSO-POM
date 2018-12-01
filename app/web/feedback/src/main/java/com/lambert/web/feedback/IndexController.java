package com.lambert.web.feedback;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.lambert.biz.uums.UumsSysAppManager;
import com.lambert.biz.uums.model.UumsSysAppModel;
import com.lambert.biz.uums.queryObj.UumsSysAppQueryObj;
import com.lambert.common.uitl.result.DefaultResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lambert.common.service.facade.model.SessionUser;
import com.lambert.common.service.facade.util.SessionUtils;

import java.util.List;

@Controller
public class IndexController {

	@Autowired
	UumsSysAppManager uumsSysAppManager;
	
	
	@RequestMapping(value="/main.htm",method = RequestMethod.GET) 
	public String main(HttpServletRequest request, ServletResponse response, Model model){
		//SessionUser sessionUser = SessionUtils.getSessionUser(request);
		SessionUser sessionUser = SessionUtils.getSessionUser();
		model.addAttribute("userName", sessionUser.getAccount());
     	return "index";
		
	}
	@RequestMapping(value="/loginPermissionError",method = RequestMethod.GET)
	public String loginPermissionError(HttpServletRequest request, ServletResponse response,String code,Model model){
		UumsSysAppQueryObj uumsSysAppQueryObj = new UumsSysAppQueryObj();
		uumsSysAppQueryObj.setAppCode(code);
		DefaultResult<List<UumsSysAppModel>> result = uumsSysAppManager.queryAllUumsSysApp(uumsSysAppQueryObj);
		List<UumsSysAppModel> list = result.getValue();
		if(list != null && list.size() > 0){
			UumsSysAppModel UumsSysAppModel = list.get(0);
			model.addAttribute("appName", UumsSysAppModel.getName());
		}
		return "loginPermissionError";
	}
	
}
