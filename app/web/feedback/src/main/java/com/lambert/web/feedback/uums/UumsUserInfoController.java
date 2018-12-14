package com.lambert.web.feedback.uums;

import com.lambert.web.feedback.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lambert.biz.uums.UumsUserInfoManager;
import com.lambert.biz.uums.model.UumsUserInfoModel;
import com.lambert.biz.uums.queryObj.UumsUserInfoQueryObj;
import com.lambert.common.uitl.result.Result;
import com.lambert.common.uitl.result.Pager;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UumsUserInfoController extends BaseController {
	
	@Autowired
	UumsUserInfoManager uumsUserInfoManager;
	
	
	@RequestMapping(value="/queryUumsUserInfoByPager.json",method = RequestMethod.POST) 
	public void queryUumsUserInfoByPager(HttpServletResponse response, UumsUserInfoQueryObj queryObj){
		Result<Pager> result = uumsUserInfoManager.queryUumsUserInfoByPager(queryObj);
		writeSuccess2Response(response,result);

	}
	
	@RequestMapping(value="/insertUumsUserinfo.json",method = RequestMethod.POST)
	public void insertUumsUserinfo(HttpServletResponse response,UumsUserInfoModel userInfoModel){
		Result<Boolean> result = uumsUserInfoManager.insertUumsUserInfo(userInfoModel);
		writeSuccess2Response(response,result);
	}
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteUumsUserInfoById.json",method = RequestMethod.POST)
	public void deleteUumsUserInfoById(HttpServletResponse response,long id){
		Result<Boolean> result = uumsUserInfoManager.deleteUumsUserInfoById(id);
		writeSuccess2Response(response,result);
	}
	/**
	 * 更新
	 * 
	 * @param userInfoModel
	 * @return
	 */
	@RequestMapping(value="/updateUumsUserInfoById.json",method = RequestMethod.POST)
	public void updateUumsUserInfoById(HttpServletResponse response,UumsUserInfoModel userInfoModel){
		Result<Boolean> result = uumsUserInfoManager.updateUumsUserInfoById(userInfoModel);
		writeSuccess2Response(response,result);
	}
	/**
	 * 通过ID获取用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryUumsUserInfoById.json",method = RequestMethod.POST)
	public void queryUumsUserInfoById(HttpServletResponse response,long id){
		Result<UumsUserInfoModel> result = uumsUserInfoManager.queryUumsUserInfoById(id);
		writeSuccess2Response(response,result);
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
		Result<Boolean> result = uumsUserInfoManager.insertUumsUserPermissionRelation(s, userId);
		writeSuccess2Response(response,result);
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
		Result<Boolean> result = uumsUserInfoManager.deleteUumsUserPermissionRelation(s, userId);
		writeSuccess2Response(response,result);
	}
	
}
