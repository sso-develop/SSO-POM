package com.lambert.common.uums.dal.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lambert.common.uums.dal.dataobject.UumsSysPermissionDO;

/**
 * 
 * @author Administrator
 *
 */
public interface UumsSysPermissionDAO {
	/**
	 * 
	 */
	public List<UumsSysPermissionDO> queryUumsSysPermissionByPager(@Param("offset") int offset,@Param("limit")int limit,@Param("appId")Long appId,@Param("code")String code);
	/**
	 * 
	 * @return
	 */
	public int queryUumsSysPermissionCountByPager();
	
	public List<UumsSysPermissionDO> queryUumsSysPermission(@Param("appCode") String appCode,@Param("userId") Long userId);
	
	/**
	 * 新增权限
	 * 
	 * @param permissionDO
	 */
	public void insertUumsSysPermission(UumsSysPermissionDO permissionDO);
	/**
	 * 删除
	 * 
	 * @param userId
	 */
	public void deleteUumsSysPermissionById(@Param("id") long id);
	/**
	 * 更新
	 * 
	 * @param permissionDO
	 */
	public void updateUumsSysPermissionById(UumsSysPermissionDO permissionDO);
}
