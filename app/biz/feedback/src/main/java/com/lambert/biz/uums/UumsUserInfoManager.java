package com.lambert.biz.uums;

import java.util.List;

import com.lambert.biz.uums.model.UumsUserInfoModel;
import com.lambert.biz.uums.queryObj.UumsUserInfoQueryObj;
import com.lambert.common.uitl.result.DefaultResult;
import com.lambert.common.uitl.result.Pager;

public interface UumsUserInfoManager {
	
	public DefaultResult<List<UumsUserInfoModel>> queryUumsUserInfo();
	
	public DefaultResult<Pager> queryUumsUserInfoByPager(UumsUserInfoQueryObj queryObj);
	
	public DefaultResult<Boolean> insertUumsUserInfo(UumsUserInfoModel userInfoModel);
	
	public DefaultResult<UumsUserInfoModel>  queryUumsUserInfoCountByOperatorName(String operatorName);
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public DefaultResult<Boolean> deleteUumsUserInfoById(long id);
	/**
	 * 通过ID修改用户
	 * 
	 * @param userInfoModel
	 * @return
	 */
	public DefaultResult<Boolean> updateUumsUserInfoById(UumsUserInfoModel userInfoModel);
	/**
	 * 通过ID查询
	 * 
	 * @param id
	 * @return
	 */
	public DefaultResult<UumsUserInfoModel> queryUumsUserInfoById(long id);
	/**
	 * 
	 * @param permissionIds
	 * @param userId
	 * @return
	 */
	public DefaultResult<Boolean> insertUumsUserPermissionRelation(Long[] permissionIds,Long userId);
	/**
	 * 
	 * @param permissionIds
	 * @param userId
	 * @return
	 */
	public DefaultResult<Boolean> deleteUumsUserPermissionRelation(Long[] permissionIds,Long userId);
}
