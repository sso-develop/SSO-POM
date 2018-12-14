package com.lambert.biz.uums;

import java.util.List;

import com.lambert.biz.uums.model.UumsUserInfoModel;
import com.lambert.biz.uums.queryObj.UumsUserInfoQueryObj;
import com.lambert.common.uitl.result.Result;
import com.lambert.common.uitl.result.Pager;

public interface UumsUserInfoManager {
	
	public Result<List<UumsUserInfoModel>> queryUumsUserInfo();
	
	public Result<Pager> queryUumsUserInfoByPager(UumsUserInfoQueryObj queryObj);
	
	public Result<Boolean> insertUumsUserInfo(UumsUserInfoModel userInfoModel);
	
	public Result<UumsUserInfoModel> queryUumsUserInfoCountByOperatorName(String operatorName);
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public Result<Boolean> deleteUumsUserInfoById(long id);
	/**
	 * 通过ID修改用户
	 * 
	 * @param userInfoModel
	 * @return
	 */
	public Result<Boolean> updateUumsUserInfoById(UumsUserInfoModel userInfoModel);
	/**
	 * 通过ID查询
	 * 
	 * @param id
	 * @return
	 */
	public Result<UumsUserInfoModel> queryUumsUserInfoById(long id);
	/**
	 * 
	 * @param permissionIds
	 * @param userId
	 * @return
	 */
	public Result<Boolean> insertUumsUserPermissionRelation(Long[] permissionIds, Long userId);
	/**
	 * 
	 * @param permissionIds
	 * @param userId
	 * @return
	 */
	public Result<Boolean> deleteUumsUserPermissionRelation(Long[] permissionIds, Long userId);
}
