/**
 * LAMBERT Lin. Zekuan Lin
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package com.lambert.web.feedback.uums;

import java.util.List;

import com.lambert.web.feedback.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lambert.biz.uums.UumsSysPermissionManager;
import com.lambert.biz.uums.model.UumsSysPermissionModel;
import com.lambert.biz.uums.queryObj.UumsSysPermissionQueryObj;
import com.lambert.common.uitl.result.Result;
import com.lambert.common.uitl.result.Pager;

import javax.servlet.http.HttpServletResponse;

/**
 * 应用权限
 * @author Administrator	
 * @version $Id: UumsSysPermissionController.java, v 0.1 2017年12月17日 下午11:07:27 Administrator Exp $
 */
@Controller
public class UumsSysPermissionController extends BaseController {
	
	@Autowired
	private UumsSysPermissionManager uumsSysPermissionManager;
	
	@RequestMapping(value="/queryUumsSysPermissionByPager.json",method = RequestMethod.POST)
	public void queryUumsSysPermissionByPager(HttpServletResponse response, UumsSysPermissionQueryObj queryObj){
		Result<Pager> result = uumsSysPermissionManager.queryUumsSysPermissionByPager(queryObj);
		writeSuccess2Response(response,result);
	}
	/**
	 * 
	 * @param queryObj
	 * @return
	 */
	@RequestMapping(value="/queryUumsSysPermission.json",method = RequestMethod.POST)
	public void queryUumsSysPermission(HttpServletResponse response,UumsSysPermissionQueryObj queryObj) {
		Result<List<UumsSysPermissionModel>> result = uumsSysPermissionManager.queryUumsSysPermission(queryObj);
		writeSuccess2Response(response,result);
	}
	/**
	 * 新增权限
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/insertUumsSysPermission.json",method = RequestMethod.POST)
	public void insertUumsSysPermission(HttpServletResponse response,UumsSysPermissionModel model){
		Result<Boolean> result = uumsSysPermissionManager.insertUumsSysPermission(model);
		writeSuccess2Response(response,result);
	}
	/**
	 * 删除权限
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteUumsSysPermissionById.json",method = RequestMethod.POST)
	public void deleteUumsSysPermissionById(HttpServletResponse response,long id){
		Result<Boolean> result = uumsSysPermissionManager.deleteUumsSysPermissionById(id);
		writeSuccess2Response(response,result);
	}
	/**
	 * 更新权限
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateUumsSysPermissionById.json",method = RequestMethod.POST)
	public void updateUumsSysPermissionById(HttpServletResponse response,UumsSysPermissionModel model){
		Result<Boolean> result =  uumsSysPermissionManager.updateUumsSysPermissionById(model);
		writeSuccess2Response(response,result);
	}
}
