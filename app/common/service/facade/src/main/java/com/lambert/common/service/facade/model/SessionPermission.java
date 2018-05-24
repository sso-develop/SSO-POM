package com.lambert.common.service.facade.model;

import java.io.Serializable;
import java.util.Set;

public class SessionPermission implements Serializable {
	
	private static final long serialVersionUID = 7744061178030182892L;
	
	
	// 用户权限码
	private Set<String> permissionSet;
	// 用户没有的权限
	private String noPermissions;
	

	public Set<String> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<String> permissionSet) {
		this.permissionSet = permissionSet;
	}

	public String getNoPermissions() {
		return noPermissions;
	}

	public void setNoPermissions(String noPermissions) {
		this.noPermissions = noPermissions;
	}

}
