/**
 * LAMBERT Lin. Zekuan Lin
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package com.lambert.biz.uums.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lambert.biz.uums.UumsSysPermissionManager;
import com.lambert.biz.uums.convertor.UumsSysPermissionConvertor;
import com.lambert.biz.uums.model.UumsSysPermissionModel;
import com.lambert.biz.uums.queryObj.UumsSysPermissionQueryObj;
import com.lambert.common.uitl.LoggerUtil;
import com.lambert.common.uitl.result.DefaultResult;
import com.lambert.common.uitl.result.DefaultResultCode;
import com.lambert.common.uitl.result.Pager;
import com.lambert.common.uums.dal.daointerface.UumsSysAppDAO;
import com.lambert.common.uums.dal.daointerface.UumsSysPermissionDAO;
import com.lambert.common.uums.dal.dataobject.UumsSysAppDO;
import com.lambert.common.uums.dal.dataobject.UumsSysPermissionDO;

/**
 * 
 * @author Administrator	
 * @version $Id: UumsSysPermissionManagerImpl.java, v 0.1 2017年12月17日 下午10:50:48 Administrator Exp $
 */
public class UumsSysPermissionManagerImpl implements UumsSysPermissionManager{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UumsSysPermissionManager.class);
	
	@Autowired
	private UumsSysPermissionDAO uumsSysPermissionDAO;
	@Autowired
	private UumsSysAppDAO uumsSysAppDAO;

	/** 
	 * @see com.lambert.biz.uums.UumsSysPermissionManager#queryUumsSysPermissionByPager(com.lambert.common.uitl.result.Pager)
	 */
	public DefaultResult<Pager> queryUumsSysPermissionByPager(UumsSysPermissionQueryObj queryObj) {
		try {
			Pager pager = new Pager(queryObj.getPageNumber(), queryObj.getPageSize());
			List<UumsSysPermissionDO> uumsSysPermissionDOList = uumsSysPermissionDAO.queryUumsSysPermissionByPager(pager.getBeginIndex(), pager.getEndIndex(),
					queryObj.getAppId(),
					queryObj.getCode());
			int count = uumsSysPermissionDAO.queryUumsSysPermissionCountByPager();
			
			List<UumsSysPermissionModel> models = new ArrayList<UumsSysPermissionModel>();
			for (UumsSysPermissionDO uumsSysPermissionDO : uumsSysPermissionDOList) {
				UumsSysAppDO appDO = uumsSysAppDAO.queryUumsSysAppById(uumsSysPermissionDO.getAppId());
				models.add(UumsSysPermissionConvertor.convertor2Model(uumsSysPermissionDO,appDO));
			}
			pager.setResult(models);
			pager.setTotalCount(count);
			return new DefaultResult<Pager>(pager);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}

	/** 
	 * @see com.lambert.biz.uums.UumsSysPermissionManager#insertUumsSysPermission(com.lambert.biz.uums.model.UumsSysPermissionModel)
	 */
	public DefaultResult<Boolean> insertUumsSysPermission(UumsSysPermissionModel permissionModel) {
		try {
			UumsSysPermissionDO uumsSysPermissionDO = UumsSysPermissionConvertor.convertor2DO(permissionModel);
			uumsSysPermissionDAO.insertUumsSysPermission(uumsSysPermissionDO);
			return new DefaultResult<Boolean>(true);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}

	/** 
	 * @see com.lambert.biz.uums.UumsSysPermissionManager#deleteUumsSysPermissionById(long)
	 */
	public DefaultResult<Boolean> deleteUumsSysPermissionById(long id) {
		try {
			uumsSysPermissionDAO.deleteUumsSysPermissionById(id);
			return new DefaultResult<Boolean>(true);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}

	/** 
	 * @see com.lambert.biz.uums.UumsSysPermissionManager#updateUumsSysPermissionById(com.lambert.biz.uums.model.UumsSysPermissionModel)
	 */
	public DefaultResult<Boolean> updateUumsSysPermissionById(UumsSysPermissionModel permissionModel) {
		try {
			UumsSysPermissionDO uumsSysPermissionDO = UumsSysPermissionConvertor.convertor2DO(permissionModel);
			uumsSysPermissionDAO.updateUumsSysPermissionById(uumsSysPermissionDO);
			return new DefaultResult<Boolean>(true);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}

	public DefaultResult<List<UumsSysPermissionModel>> queryUumsSysPermission(UumsSysPermissionQueryObj queryObj) {
		try {
			List<UumsSysPermissionModel> list = new ArrayList<UumsSysPermissionModel>();
			String appCode = null;
			if(queryObj.getAppId() != null) {
				UumsSysAppDO appDO = uumsSysAppDAO.queryUumsSysAppById(queryObj.getAppId());
				if(appDO == null) {
					LoggerUtil.info(LOGGER, "【queryUumsSysPermission】:app id : "+queryObj.getAppId()+" 不存在");
					return new DefaultResult(DefaultResultCode.ILLEGAL_PARAMS,"app id : "+queryObj.getAppId()+" 不存在");
				}
				appCode = appDO.getCode();
			}
			List<UumsSysPermissionDO> permissionDOs = uumsSysPermissionDAO.queryUumsSysPermission(appCode, queryObj.getUserId());
			for (UumsSysPermissionDO uumsSysPermissionDO : permissionDOs) {
				UumsSysAppDO appDO = uumsSysAppDAO.queryUumsSysAppById(uumsSysPermissionDO.getAppId());
				list.add(UumsSysPermissionConvertor.convertor2Model(uumsSysPermissionDO,appDO));
			}
			return new DefaultResult<List<UumsSysPermissionModel>>(list);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}
}
