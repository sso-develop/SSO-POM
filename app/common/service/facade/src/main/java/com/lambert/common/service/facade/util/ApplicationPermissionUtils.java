/**
 * LAMBERT Lin. Zekuan Lin
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package com.lambert.common.service.facade.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lambert.common.service.facade.model.Permission;
import com.lambert.common.service.facade.rpc.AuthenticationRpcService;

/**
 * 
 * @author Administrator	
 * @version $Id: ApplicationPermissionUtils.java, v 0.1 2017年12月19日 下午9:01:48 Administrator Exp $
 */
public class ApplicationPermissionUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPermissionUtils.class);
	
	
	// 应用所有菜单
	private static List<Permission> applicationMenuList = null;
	// 并发监控
	//private static Object monitor = new Object();
	/**
	 * 1.应用初始化，获取应用所有的菜单及权限 2.权限有变动修改，JMS通知重新加载
	 */
	public static void initApplicationPermissions(AuthenticationRpcService authenticationRpcService, String ssoAppCode) {
		List<Permission> dbList = null;
		
		try {
			dbList = authenticationRpcService.findPermissionList(null, ssoAppCode);
		}catch (Exception e) {
			dbList = new ArrayList<Permission>();
			LOGGER.error("无法连接到单点登录鉴权系统,请检查配置sso.server.url", e);
		}
		applicationMenuList = dbList;
		
	}

}
