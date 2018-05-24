/**
 * LAMBERT Lin. Zekuan Lin
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package com.lambert.biz.uums.model;

import java.util.Date;

/**
 * 
 * @author Administrator	
 * @version $Id: UumsSysPermissionModel.java, v 0.1 2017年12月17日 下午10:56:39 Administrator Exp $
 */
public class UumsSysPermissionModel {
	private Long id;
	private String createDate;
	private String modifyDate;
	/* 权限名称 */
	private String name;
	/* 权限码 */
	private String code;
	/* 风险等级*/
	private String level;
	/*权限描述*/
	private String des;
	/* 是否启用 */
	private boolean isEnable;
	/* appId */
	private Long appId;
	/* 应用编号  */
	private String appCode;
	/* 应用名称  */
	private String appName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
}
