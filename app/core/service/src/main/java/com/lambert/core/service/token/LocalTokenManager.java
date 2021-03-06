package com.lambert.core.service.token;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.lambert.common.service.facade.model.LoginInfo;

/**
 * 单实例环境令牌管理
 * 
 */
public class LocalTokenManager extends TokenManager {

	private static Logger LOGGER = LoggerFactory.getLogger(LocalTokenManager.class);

	// 令牌存储结构
	private static final ConcurrentHashMap<String, DummyUser> tokenMap = new ConcurrentHashMap<String, LocalTokenManager.DummyUser>();

	@Override
	public void verifyExpired() {
		
		
		
		Date now = new Date();
		for (Entry<String, DummyUser> entry : tokenMap.entrySet()) {
			String token = entry.getKey();
			DummyUser dummyUser = entry.getValue();
			// 当前时间大于过期时间
			if (now.compareTo(dummyUser.expired) > 0) {
				// 已过期，清除对应token
				if (now.compareTo(dummyUser.expired) > 0) {
					tokenMap.remove(token);
					LOGGER.info("token : " + token + "已失效");
				}
			}
		}
	}

	public void addToken(String token, LoginInfo loginUser) {
		DummyUser dummyUser = new DummyUser();
		loginUser.setToken(token);
		dummyUser.loginUser = loginUser;
		extendExpiredTime(dummyUser);
		//tokenMap.putIfAbsent(token, dummyUser);
		tokenMap.put(token, dummyUser);
	}

	public LoginInfo validate(String token) {
		DummyUser dummyUser = tokenMap.get(token);
		if (dummyUser == null) {
			return null;
		}
		extendExpiredTime(dummyUser);
		return dummyUser.loginUser;
	}

	public void remove(String token) {
		tokenMap.remove(token);
	}

	@Override
	public List<LoginInfo> getALLToken() {
		List<LoginInfo> list = new ArrayList<LoginInfo>();
		for (String key : tokenMap.keySet()) {
			DummyUser dummyUser = tokenMap.get(key);
			if (dummyUser != null) {
				list.add(dummyUser.loginUser);
			}
		}
		return list;
	}

	/**
	 * 扩展过期时间
	 * 
	 * @param dummyUser
	 */
	private void extendExpiredTime(DummyUser dummyUser) {

		System.currentTimeMillis();

		dummyUser.expired = new Date(new Date().getTime() + tokenTimeout * 1000);
		dummyUser.loginUser.setExpired(dummyUser.expired);
	}

	// 复合结构体，含loginUser与过期时间expried两个成员
	private class DummyUser {
		private LoginInfo loginUser;
		private Date expired; // 过期时间
	}

}
