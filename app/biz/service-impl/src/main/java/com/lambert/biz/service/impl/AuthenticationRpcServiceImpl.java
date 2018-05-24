package com.lambert.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.lambert.common.service.facade.model.LoginInfo;
import com.lambert.common.service.facade.model.Permission;
import com.lambert.common.service.facade.rpc.AuthenticationRpcService;
import com.lambert.common.uums.dal.daointerface.UumsSysPermissionDAO;
import com.lambert.common.uums.dal.dataobject.UumsSysPermissionDO;
import com.lambert.core.service.token.TokenManager;


public class AuthenticationRpcServiceImpl implements AuthenticationRpcService{
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationRpcServiceImpl.class);
	@Autowired
	private TokenManager tokenManager;
	@Autowired
	private UumsSysPermissionDAO uumsSysPermissionDAO;
	
	public boolean validate(String token) {
		return tokenManager.validate(token) != null;
	}

	public LoginInfo findAuthInfo(String token) {
		LoginInfo user = tokenManager.validate(token);
		return user;
	}

	public List<Permission> findPermissionList(String token, String appCode) {
		
		List<Permission> permissionList = new ArrayList<Permission>();
		
		if (StringUtils.isBlank(token)) {
			List<UumsSysPermissionDO> list = uumsSysPermissionDAO.queryUumsSysPermission(appCode, null);
			for (UumsSysPermissionDO uumsSysPermissionDO : list) {
				Permission permission = new Permission();
				permission.setCode(uumsSysPermissionDO.getCode());
				permission.setName(uumsSysPermissionDO.getName());
				permissionList.add(permission);
			}
		}else {
			LoginInfo user = tokenManager.validate(token);
			if (user != null) {
				List<UumsSysPermissionDO> list = uumsSysPermissionDAO.queryUumsSysPermission(appCode, user.getUserId());
				for (UumsSysPermissionDO uumsSysPermissionDO : list) {
					Permission permission = new Permission();
					permission.setCode(uumsSysPermissionDO.getCode());
					permission.setName(uumsSysPermissionDO.getName());
					permissionList.add(permission);
				}
			}
		}
		return permissionList;
	}

}
