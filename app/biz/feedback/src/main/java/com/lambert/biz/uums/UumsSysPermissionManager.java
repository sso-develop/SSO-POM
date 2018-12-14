package com.lambert.biz.uums;

import java.util.List;

import com.lambert.biz.uums.model.UumsSysPermissionModel;
import com.lambert.biz.uums.queryObj.UumsSysPermissionQueryObj;
import com.lambert.common.uitl.result.Result;
import com.lambert.common.uitl.result.Pager;
/**
 * 
 * 
 * @author lambert
 * @version $Id: UumsSysPermissionManager.java, v 0.1 2017年12月17日 下午10:45:38 Administrator Exp $
 */
public interface UumsSysPermissionManager {
	/**
	 * 
	 * @param pager
	 * @return
	 */
	public Result<Pager> queryUumsSysPermissionByPager(UumsSysPermissionQueryObj queryObj);
	/**
	 * 
	 * @param queryObj
	 * @return
	 */
	public Result<List<UumsSysPermissionModel>> queryUumsSysPermission(UumsSysPermissionQueryObj queryObj);
	/**
	 * 新增权限
	 * 
	 * @param permissionModel
	 * @return
	 */
	public Result<Boolean> insertUumsSysPermission(UumsSysPermissionModel permissionModel);
	/**
	 * 删除权限
	 * 
	 * @param id
	 * @return
	 */
	public Result<Boolean> deleteUumsSysPermissionById(long id);
	/**
	 * 
	 * 更新
	 * @param permissionModel
	 * @return
	 */
	public Result<Boolean> updateUumsSysPermissionById(UumsSysPermissionModel permissionModel);
}
