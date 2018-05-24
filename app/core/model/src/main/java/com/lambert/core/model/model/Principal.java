package com.lambert.core.model.model;

import java.io.Serializable;

/**
 * 授权用户信息
 */
public class Principal implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id; // 编号
	private String loginName; // 登录名
	private String name; // 姓名
	private String email;
	private boolean mobileLogin; // 是否手机登录

	// private Map<String, Object> cacheMap;
	public Principal() {
		super();
	}
	
	

	public Principal(String id, String loginName, String name) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.name = name;
	}



	public String getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getName() {
		return name;
	}

	public boolean isMobileLogin() {
		return mobileLogin;
	}

	@Override
	public String toString() {
		return id;
	}

	public String getEmail() {
		return email == null ? "" : email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}