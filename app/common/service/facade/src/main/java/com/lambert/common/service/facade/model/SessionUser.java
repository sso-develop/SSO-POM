package com.lambert.common.service.facade.model;

import java.io.Serializable;

public class SessionUser implements  Serializable {
	private static final long serialVersionUID = 1764365572138947234L;

	// 登录用户访问Token
	private String token;
	// 登录名
	private String account;
	//
	private Long userId;

	public SessionUser() {
		super();
	}

	public SessionUser(String token, String account,Long userId) {
		super();
		this.token = token;
		this.account = account;
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
