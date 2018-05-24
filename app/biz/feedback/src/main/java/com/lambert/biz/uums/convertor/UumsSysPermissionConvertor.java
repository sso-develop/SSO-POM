/**
 * LAMBERT Lin. Zekuan Lin
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package com.lambert.biz.uums.convertor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.lambert.biz.uums.model.UumsSysPermissionModel;
import com.lambert.common.uums.dal.dataobject.UumsSysAppDO;
import com.lambert.common.uums.dal.dataobject.UumsSysPermissionDO;

/**
 * 
 * @author Administrator	
 * @version $Id: UumsSysPermissionConvertor.java, v 0.1 2017年12月17日 下午10:53:49 Administrator Exp $
 */
public class UumsSysPermissionConvertor {
	
	public static UumsSysPermissionModel convertor2Model(UumsSysPermissionDO uumsSysPermissionDO,UumsSysAppDO sysAppDO){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UumsSysPermissionModel model = new UumsSysPermissionModel();
		model.setId(uumsSysPermissionDO.getId());
		model.setAppId(uumsSysPermissionDO.getAppId());
		model.setCode(uumsSysPermissionDO.getCode());
		model.setDes(uumsSysPermissionDO.getDes());
		model.setIsEnable(uumsSysPermissionDO.getIsEnable());
		model.setLevel(uumsSysPermissionDO.getLevel());
		model.setName(uumsSysPermissionDO.getName());
		model.setCreateDate(sdf.format(uumsSysPermissionDO.getCreateDate()));
		model.setModifyDate(sdf.format(uumsSysPermissionDO.getModifyDate()));
		model.setAppCode(uumsSysPermissionDO.getAppCode());
		if(sysAppDO != null){
			model.setAppName(sysAppDO.getName());
		}
		return model;
	}
	
	
	public static List<UumsSysPermissionModel> Convertor2Model(List<UumsSysPermissionDO> list){
		List<UumsSysPermissionModel> models = new ArrayList<UumsSysPermissionModel>();
		for (UumsSysPermissionDO uumsSysPermissionDO : list) {
			models.add(convertor2Model(uumsSysPermissionDO,null));
		}
		return models;
	}
	
	public static UumsSysPermissionDO convertor2DO(UumsSysPermissionModel model){
		UumsSysPermissionDO permissionDO = new UumsSysPermissionDO();
		permissionDO.setAppId(model.getAppId());
		permissionDO.setCode(model.getCode());
		permissionDO.setDes(model.getDes());
		permissionDO.setId(model.getId());
		permissionDO.setIsEnable(model.getIsEnable());
		permissionDO.setLevel(model.getLevel());
		permissionDO.setName(model.getName());
		return permissionDO;
		
	}

}
