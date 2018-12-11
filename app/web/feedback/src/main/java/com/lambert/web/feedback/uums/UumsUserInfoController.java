package com.lambert.web.feedback.uums;

import com.alibaba.fastjson.JSON;
import com.lambert.web.feedback.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lambert.biz.uums.UumsUserInfoManager;
import com.lambert.biz.uums.model.UumsUserInfoModel;
import com.lambert.biz.uums.queryObj.UumsUserInfoQueryObj;
import com.lambert.common.uitl.result.DefaultResult;
import com.lambert.common.uitl.result.DefaultWebUtils;
import com.lambert.common.uitl.result.Pager;
import com.lambert.common.uitl.result.ResultModel;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UumsUserInfoController extends BaseController {
	
	@Autowired
	UumsUserInfoManager uumsUserInfoManager;
	
	
	@RequestMapping(value="/queryUumsUserInfoByPager.json",method = RequestMethod.POST) 
	public void queryUumsUserInfoByPager(HttpServletResponse response, UumsUserInfoQueryObj queryObj){
		DefaultResult<Pager> result = uumsUserInfoManager.queryUumsUserInfoByPager(queryObj);
		writeSuccess2Response(response,JSON.toJSONString(result));

	}
	
	@RequestMapping(value="/insertUumsUserinfo.json",method = RequestMethod.POST)
	public void insertUumsUserinfo(HttpServletResponse response,UumsUserInfoModel userInfoModel){
		DefaultResult<Boolean> result = uumsUserInfoManager.insertUumsUserInfo(userInfoModel);
		writeSuccess2Response(response,JSON.toJSONString(result));
	}
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteUumsUserInfoById.json",method = RequestMethod.POST)
	public void deleteUumsUserInfoById(HttpServletResponse response,long id){
		DefaultResult<Boolean> result = uumsUserInfoManager.deleteUumsUserInfoById(id);
		writeSuccess2Response(response,JSON.toJSONString(result));
	}
	/**
	 * 更新
	 * 
	 * @param model
	 * @param userInfoModel
	 * @return
	 */
	@RequestMapping(value="/updateUumsUserInfoById.json",method = RequestMethod.POST)
	public void updateUumsUserInfoById(HttpServletResponse response,UumsUserInfoModel userInfoModel){
		DefaultResult<Boolean> result = uumsUserInfoManager.updateUumsUserInfoById(userInfoModel);
		writeSuccess2Response(response,JSON.toJSONString(result));
	}
	/**
	 * 通过ID获取用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryUumsUserInfoById.json",method = RequestMethod.POST)
	public void queryUumsUserInfoById(HttpServletResponse response,long id){
		DefaultResult<UumsUserInfoModel> result = uumsUserInfoManager.queryUumsUserInfoById(id);
		writeSuccess2Response(response,JSON.toJSONString(result));
	}
	/**
	 * 新增权限
	 * @param permissionIds
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/insertUumsUserPermissionRelation.json",method = RequestMethod.POST)
	public void insertUumsUserPermissionRelation(HttpServletResponse response,String permissionIds,Long userId) {
		String[] ids = permissionIds.split(",");
		Long[] s = new Long[ids.length];
		 for (int i = 0; i < ids.length; i++) {
	            s[i] = Long.valueOf(ids[i]);
	        }
		DefaultResult<Boolean> result = uumsUserInfoManager.insertUumsUserPermissionRelation(s, userId);
		writeSuccess2Response(response,JSON.toJSONString(result));
	}
	/**
	 * 删除权限
	 * @param permissionIds
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/deleteUumsUserPermissionRelation.json",method = RequestMethod.POST)
	public void deleteUumsUserPermissionRelation(HttpServletResponse response,String permissionIds,Long userId) {
		String[] ids = permissionIds.split(",");
		Long[] s = new Long[ids.length];
		 for (int i = 0; i < ids.length; i++) {
	            s[i] = Long.valueOf(ids[i]);
	        }
		DefaultResult<Boolean> result = uumsUserInfoManager.deleteUumsUserPermissionRelation(s, userId);
		writeSuccess2Response(response,JSON.toJSONString(result));
		
	}
	
}
