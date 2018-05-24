package com.lambert.biz.uums.convertor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.lambert.biz.uums.model.UumsSysAppModel;
import com.lambert.common.uums.dal.dataobject.UumsSysAppDO;
/**
 * 
 *
 */
public class UumsSysAppConvertor {
	
	public static List<UumsSysAppModel>  convertor2Model(List<UumsSysAppDO> UumsSysAppDOList){
		List<UumsSysAppModel> appModels = new ArrayList<UumsSysAppModel>();
		for (UumsSysAppDO uumsSysAppDO : UumsSysAppDOList) {
			appModels.add(convertor2Model(uumsSysAppDO));
		}
		return appModels;
	}
	
	public static UumsSysAppModel convertor2Model(UumsSysAppDO sysAppDO){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UumsSysAppModel model = new UumsSysAppModel();
		model.setId(sysAppDO.getId());
		model.setCreateDate(sdf.format(sysAppDO.getCreateDate()));
		model.setModifyDate(sdf.format(sysAppDO.getModifyDate()));
		model.setAppCode(sysAppDO.getCode());
		model.setIsEnable(sysAppDO.getIsEnable());
		model.setName(sysAppDO.getName());
		model.setSort(sysAppDO.getSort());
		
		return model;
	}
	
	public static UumsSysAppDO convertor2DO(UumsSysAppModel uumsSysAppModel){
		UumsSysAppDO appDO = new UumsSysAppDO();
		appDO.setCode(uumsSysAppModel.getAppCode());
		appDO.setId(uumsSysAppModel.getId());
		appDO.setIsEnable(uumsSysAppModel.getIsEnable());
		appDO.setName(uumsSysAppModel.getName());
		appDO.setSort(uumsSysAppModel.getSort());
		return appDO;
	}

}
