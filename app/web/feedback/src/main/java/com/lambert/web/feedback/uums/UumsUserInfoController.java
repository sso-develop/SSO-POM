package com.lambert.web.feedback.uums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.dialect.odps.parser.OdpsExprParser;
import com.alibaba.fastjson.JSON;
import com.lambert.biz.uums.UumsUserInfoManager;
import com.lambert.biz.uums.model.UumsUserInfoModel;
import com.lambert.biz.uums.queryObj.UumsUserInfoQueryObj;
import com.lambert.common.uitl.result.DefaultResult;
import com.lambert.common.uitl.result.DefaultWebUtils;
import com.lambert.common.uitl.result.Pager;
import com.lambert.common.uitl.result.ResultModel;

@Controller
public class UumsUserInfoController {
	
	@Autowired
	UumsUserInfoManager uumsUserInfoManager;
	
	
	@RequestMapping(value="/queryUumsUserInfoByPager.json",method = RequestMethod.POST) 
	public @ResponseBody ResultModel queryUumsUserInfoByPager(ResultModel model,UumsUserInfoQueryObj queryObj){
		DefaultResult<Pager> result = uumsUserInfoManager.queryUumsUserInfoByPager(queryObj);
		DefaultWebUtils.putResult2ModelMap(result, model);
		return model;  
	}
	
	@RequestMapping(value="/insertUumsUserinfo.json",method = RequestMethod.POST)
	public @ResponseBody ResultModel insertUumsUserinfo(ResultModel model,UumsUserInfoModel userInfoModel){
		DefaultResult<Boolean> result = uumsUserInfoManager.insertUumsUserInfo(userInfoModel);
		DefaultWebUtils.putResult2ModelMap(result, model);
		return model;
	}
	/**
	 * 删除
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteUumsUserInfoById.json",method = RequestMethod.POST)
	public @ResponseBody ResultModel deleteUumsUserInfoById(ResultModel model,long id){
		DefaultResult<Boolean> result = uumsUserInfoManager.deleteUumsUserInfoById(id);
		DefaultWebUtils.putResult2ModelMap(result, model);
		return model;
	}
	/**
	 * 更新
	 * 
	 * @param model
	 * @param userInfoModel
	 * @return
	 */
	@RequestMapping(value="/updateUumsUserInfoById.json",method = RequestMethod.POST)
	public @ResponseBody ResultModel updateUumsUserInfoById(ResultModel model,UumsUserInfoModel userInfoModel){
		DefaultResult<Boolean> result = uumsUserInfoManager.updateUumsUserInfoById(userInfoModel);
		DefaultWebUtils.putResult2ModelMap(result, model);
		return model;
	}
	/**
	 * 通过ID获取用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryUumsUserInfoById.json",method = RequestMethod.POST)
	public @ResponseBody ModelMap  queryUumsUserInfoById(long id){
		DefaultResult<UumsUserInfoModel> result = uumsUserInfoManager.queryUumsUserInfoById(id);
		return DefaultWebUtils.putResult2ModelMap(result);
	}
	/**
	 * 新增权限
	 * @param permissionIds
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/insertUumsUserPermissionRelation.json",method = RequestMethod.POST)
	public @ResponseBody ModelMap insertUumsUserPermissionRelation(String permissionIds,Long userId) {
		String[] ids = permissionIds.split(",");
		Long[] s = new Long[ids.length];
		 for (int i = 0; i < ids.length; i++) {
	            s[i] = Long.valueOf(ids[i]);
	        }
		DefaultResult<Boolean> result = uumsUserInfoManager.insertUumsUserPermissionRelation(s, userId);
		return DefaultWebUtils.putResult2ModelMap(result);
	}
	/**
	 * 删除权限
	 * @param permissionIds
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/deleteUumsUserPermissionRelation.json",method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteUumsUserPermissionRelation(String permissionIds,Long userId) {
		String[] ids = permissionIds.split(",");
		Long[] s = new Long[ids.length];
		 for (int i = 0; i < ids.length; i++) {
	            s[i] = Long.valueOf(ids[i]);
	        }
		DefaultResult<Boolean> result = uumsUserInfoManager.deleteUumsUserPermissionRelation(s, userId);
		return DefaultWebUtils.putResult2ModelMap(result);
		
	}
	
}
