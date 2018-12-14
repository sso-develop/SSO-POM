package com.lambert.web.feedback.uums;

import com.lambert.web.feedback.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lambert.biz.uums.UumsPermissionApplyManager;
import com.lambert.biz.uums.model.UumsPermissionApplyModel;
import com.lambert.biz.uums.queryObj.UumsPermissionApplyQueryObj;
import com.lambert.common.service.facade.model.SessionUser;
import com.lambert.common.service.facade.util.SessionUtils;
import com.lambert.common.uitl.result.Result;
import com.lambert.common.uitl.result.Pager;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author lambert  2018-10-15 22:22:03
 *
 */
@Controller
public class UumsPermissionApplyController extends BaseController {
	
	@Autowired
	private UumsPermissionApplyManager uumsPermissionApplyManager;
	
	@RequestMapping(value="/insertUumsPermissionApply.json",method = RequestMethod.POST) 
	public void insertUumsPermissionApply(HttpServletResponse response, UumsPermissionApplyModel uumsPermissionApplyModel){
		SessionUser sessionUser = SessionUtils.getSessionUser();
		uumsPermissionApplyModel.setOperater(sessionUser.getAccount());
		Result<Boolean> result = uumsPermissionApplyManager.insertUumsPermissionApply(uumsPermissionApplyModel);
		writeSuccess2Response(response,result);
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/queryUumsPermissionApplyByPager.json",method = RequestMethod.POST) 
	public void queryUumsPermissionApplyByPager(HttpServletResponse response,UumsPermissionApplyQueryObj queryObj) {
		Result<Pager> result = uumsPermissionApplyManager.queryUumsPermissionApplyByPager(queryObj);
		writeSuccess2Response(response,result);
	}

}
