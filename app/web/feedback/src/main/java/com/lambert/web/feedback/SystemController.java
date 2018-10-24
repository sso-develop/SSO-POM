package com.lambert.web.feedback;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lambert.common.uitl.result.DefaultResult;
import com.lambert.common.uitl.result.DefaultWebUtils;
import com.lambert.common.uitl.result.ResultModel;
import com.lambert.core.model.model.SystemConfig;

@Controller
public class SystemController {
	
	@Autowired
	private SystemConfig systemConfig;
	
	@RequestMapping(value="/findSystemConfig.json",method = RequestMethod.POST) 
	public @ResponseBody ResultModel findSystemConfig(HttpServletRequest request, ServletResponse response,ResultModel resultModel){
		DefaultResult<SystemConfig> result = new DefaultResult<SystemConfig>(systemConfig);
		DefaultWebUtils.putResult2ModelMap(result, resultModel);
		return resultModel;
		
	}
	
}
