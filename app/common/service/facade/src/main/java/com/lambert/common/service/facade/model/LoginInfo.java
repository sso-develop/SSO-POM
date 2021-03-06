package com.lambert.common.service.facade.model;

import java.io.Serializable;
import java.util.Date;

public class LoginInfo implements Serializable{
	private static final long serialVersionUID = 4507869346123296527L;

	// 登录成功ID
	private Long userId;
	// 登录成功用户名
	private String account;
	//登录token
	private String token;
	//失效时间
	private Date   expired;

	public LoginInfo(Long userId, String account) {
		super();
		this.userId = userId;
		this.account = account;

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginInfo other = (LoginInfo) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		}
		else if (!userId.equals(other.userId))
			return false;
		return true;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}
}
