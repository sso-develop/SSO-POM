package com.lambert.common.uums.dal.dataobject;
/**
 * 用户权限关系
 * @author Administrator
 *
 */
public class UumsUserPermissionRelationDO {
	private Long userId;
	private Long permissionId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	
}
