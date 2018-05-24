package com.lambert.common.uums.dal.daointerface;

import org.apache.ibatis.annotations.Param;

import com.lambert.common.uums.dal.dataobject.UumsUserPermissionRelationDO;
/**
 * 用户权限关系
 * @author linzekuan
 *
 */
public interface UumsUserPermissionRelationDAO {
	/**
	 * 新增用户权限关系
	 * @param userPermissionRelationDO
	 */
	void insertUumsUserPermissionRelation(UumsUserPermissionRelationDO userPermissionRelationDO);
	/**
	 * 删除
	 * @param userPermissionRelationDO
	 */
	void deleteUumsUserPermissionRelation(@Param("permissionId")long permissionId,@Param("userId")long userId);
}
