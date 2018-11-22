package com.lambert.web.feedback.uums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lambert.biz.uums.UumsPermissionApplyManager;
import com.lambert.biz.uums.model.UumsPermissionApplyModel;
import com.lambert.biz.uums.queryObj.UumsPermissionApplyQueryObj;
import com.lambert.common.service.facade.model.SessionUser;
import com.lambert.common.service.facade.util.SessionUtils;
import com.lambert.common.uitl.result.DefaultResult;
import com.lambert.common.uitl.result.DefaultWebUtils;
import com.lambert.common.uitl.result.Pager;
import com.lambert.common.uitl.result.ResultModel;

/**
 * 
 * @author lambert  2018-10-15 22:22:03
 *
 */
@Controller
public class UumsPermissionApplyController {
	
	@Autowired
	private UumsPermissionApplyManager uumsPermissionApplyManager;
	
	@RequestMapping(value="/insertUumsPermissionApply.json",method = RequestMethod.POST) 
	public @ResponseBody ResultModel insertUumsPermissionApply(ResultModel resultModel,UumsPermissionApplyModel uumsPermissionApplyModel){
		SessionUser sessionUser = SessionUtils.getSessionUser();
		uumsPermissionApplyModel.setOperater(sessionUser.getAccount());
		DefaultResult<Boolean> result = uumsPermissionApplyManager.insertUumsPermissionApply(uumsPermissionApplyModel);
		DefaultWebUtils.putResult2ModelMap(result, resultModel);
		return resultModel;
	}
	
	/**
	 * 
	 * @param resultModel
	 * @return
	 */
	@RequestMapping(value="/queryUumsPermissionApplyByPager.json",method = RequestMethod.POST) 
	public @ResponseBody ResultModel queryUumsPermissionApplyByPager(ResultModel resultModel,UumsPermissionApplyQueryObj queryObj) {
		DefaultResult<Pager> result = uumsPermissionApplyManager.queryUumsPermissionApplyByPager(queryObj);
		DefaultWebUtils.putResult2ModelMap(result, resultModel);
		return resultModel;
	}

}
