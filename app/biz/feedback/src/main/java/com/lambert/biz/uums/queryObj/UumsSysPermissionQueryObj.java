/**
 * LAMBERT Lin. Zekuan Lin
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package com.lambert.biz.uums.queryObj;

/**
 * 
 * @author Lambert	
 * @version $Id: UumsSysPermissionQueryObj.java, v 0.1 2017年12月31日 下午10:47:57 Lambert Exp $
 */
public class UumsSysPermissionQueryObj extends QueryObjBase{
	
	private Long appId;
	private Long userId;
	private String name;
	private String code;
	
	
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}
