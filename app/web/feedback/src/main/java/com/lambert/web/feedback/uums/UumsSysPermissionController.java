/**
 * LAMBERT Lin. Zekuan Lin
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package com.lambert.web.feedback.uums;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lambert.biz.uums.UumsSysPermissionManager;
import com.lambert.biz.uums.model.UumsSysPermissionModel;
import com.lambert.biz.uums.queryObj.UumsSysPermissionQueryObj;
import com.lambert.common.service.facade.model.SessionUser;
import com.lambert.common.service.facade.util.SessionUtils;
import com.lambert.common.uitl.result.DefaultResult;
import com.lambert.common.uitl.result.DefaultWebUtils;
import com.lambert.common.uitl.result.Pager;

/**
 * 应用权限
 * @author Administrator	
 * @version $Id: UumsSysPermissionController.java, v 0.1 2017年12月17日 下午11:07:27 Administrator Exp $
 */
@Controller
public class UumsSysPermissionController {
	
	@Autowired
	private UumsSysPermissionManager uumsSysPermissionManager;
	
	@RequestMapping(value="/queryUumsSysPermissionByPager.json",method = RequestMethod.POST)
	public @ResponseBody ModelMap queryUumsSysPermissionByPager(UumsSysPermissionQueryObj queryObj){
		DefaultResult<Pager> result = uumsSysPermissionManager.queryUumsSysPermissionByPager(queryObj);
		return DefaultWebUtils.putResult2ModelMap(result);
	}
	/**
	 * 
	 * @param queryObj
	 * @return
	 */
	@RequestMapping(value="/queryUumsSysPermission.json",method = RequestMethod.POST)
	public @ResponseBody ModelMap queryUumsSysPermission(UumsSysPermissionQueryObj queryObj) {
		DefaultResult<List<UumsSysPermissionModel>> result = uumsSysPermissionManager.queryUumsSysPermission(queryObj);
		return DefaultWebUtils.putResult2ModelMap(result);
	}
	/**
	 * 新增权限
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/insertUumsSysPermission.json",method = RequestMethod.POST)
	public @ResponseBody ModelMap insertUumsSysPermission(UumsSysPermissionModel model){
		DefaultResult<Boolean> result = uumsSysPermissionManager.insertUumsSysPermission(model);
		return DefaultWebUtils.putResult2ModelMap(result);
	}
	/**
	 * 删除权限
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteUumsSysPermissionById.json",method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteUumsSysPermissionById(long id){
		DefaultResult<Boolean> result = uumsSysPermissionManager.deleteUumsSysPermissionById(id);
		return DefaultWebUtils.putResult2ModelMap(result);
	}
	/**
	 * 更新权限
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateUumsSysPermissionById.json",method = RequestMethod.POST)
	public @ResponseBody ModelMap updateUumsSysPermissionById(UumsSysPermissionModel model){
		DefaultResult<Boolean> result =  uumsSysPermissionManager.updateUumsSysPermissionById(model);
		return DefaultWebUtils.putResult2ModelMap(result);
	}
}
