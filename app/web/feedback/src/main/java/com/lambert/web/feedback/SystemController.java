package com.lambert.web.feedback;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lambert.biz.uums.UumsUserInfoManager;
import com.lambert.biz.uums.model.UumsUserInfoModel;
import com.lambert.common.service.facade.model.SessionUser;
import com.lambert.common.service.facade.util.SessionUtils;
import com.lambert.common.uitl.result.Result;
import com.lambert.core.model.model.SystemConfig;
/**
 * 
 * @author lambert  2018-10-24 22:06:38
 *
 */
@Controller
public class SystemController extends BaseController {
	
	@Autowired
	private SystemConfig systemConfig;
	@Autowired
	UumsUserInfoManager uumsUserInfoManager;

	@RequestMapping(value="/findSystemConfig.json",method = RequestMethod.POST) 
	public void findSystemConfig(HttpServletResponse response){
		Result<SystemConfig> result = new Result<SystemConfig>(systemConfig);
		writeSuccess2Response(response,result);
		
	}
	@RequestMapping(value="/findSystemUserInfo.json",method = RequestMethod.POST) 
	public void findSystemUserInfo(HttpServletResponse response){
		SessionUser sessionUser = SessionUtils.getSessionUser();
		Result<UumsUserInfoModel> result = uumsUserInfoManager.queryUumsUserInfoById(sessionUser.getUserId());
		writeSuccess2Response(response,result);
	}
	
	
}
