package com.lambert.biz.uums.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lambert.biz.uums.UumsSysAppManager;
import com.lambert.biz.uums.convertor.UumsSysAppConvertor;
import com.lambert.biz.uums.model.UumsSysAppModel;
import com.lambert.biz.uums.queryObj.UumsSysAppQueryObj;
import com.lambert.common.uitl.LoggerUtil;
import com.lambert.common.uitl.result.Result;
import com.lambert.common.uitl.result.ResultCode;
import com.lambert.common.uitl.result.Pager;
import com.lambert.common.uums.dal.daointerface.UumsSysAppDAO;
import com.lambert.common.uums.dal.dataobject.UumsSysAppDO;

public class UumsSysAppManagerImpl implements UumsSysAppManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(UumsSysAppManagerImpl.class);

	@Autowired
	private UumsSysAppDAO uumsSysAppDAO;

	public Result<Pager> queryUumsSysAppByPager(UumsSysAppQueryObj queryObj) {
		try {
			Pager pager = new Pager(queryObj.getPageNumber(), queryObj.getPageSize());
			List<UumsSysAppDO> uumsSysAppDOList = uumsSysAppDAO.queryUumsSysAppByPager(pager.getBeginIndex(),
					pager.getEndIndex(),
					queryObj.getName(),
					queryObj.getAppCode());
			List<UumsSysAppModel> appModelList = UumsSysAppConvertor.convertor2Model(uumsSysAppDOList);
			int count = uumsSysAppDAO.queryUumsSysAppCountByPager(queryObj.getName(),
					queryObj.getAppCode());
			pager.setResult(appModelList);
			pager.setTotalCount(count);
			return new Result<Pager>(pager);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new Result(ResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}

	/**
	 * @see com.lambert.biz.uums.UumsSysAppManager#queryUumsSysAppById(long)
	 */
	public Result<UumsSysAppModel> queryUumsSysAppById(long id) {
		try {
			UumsSysAppDO sysAppDO = uumsSysAppDAO.queryUumsSysAppById(id);
			UumsSysAppModel model = UumsSysAppConvertor.convertor2Model(sysAppDO);
			return new Result<UumsSysAppModel>(model);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new Result(ResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}

	/**
	 * @see com.lambert.biz.uums.UumsSysAppManager#insertUumsSysApp(com.lambert.biz.uums.model.UumsSysAppModel)
	 */
	public Result<Boolean> insertUumsSysApp(UumsSysAppModel sysAppModel) {
		try {
			if(StringUtils.isEmpty(sysAppModel.getAppCode())){
				LoggerUtil.info(LOGGER, "【insertUumsSysApp】:appCode 不能为空 , appCode="+sysAppModel.getAppCode());
				return new Result(ResultCode.ILLEGAL_PARAMS,"appCode 不能为空 , appCode="+sysAppModel.getAppCode());
			}
			UumsSysAppDO appDO = uumsSysAppDAO.queryUumsSysAppByCode(sysAppModel.getAppCode());
			if(appDO != null){
				LoggerUtil.info(LOGGER, "【insertUumsSysApp】: appCode ： "+sysAppModel.getAppCode()+" 已存在");
				return new Result(ResultCode.ILLEGAL_PARAMS,"appCode ： "+sysAppModel.getAppCode()+" 已存在");
			}
			UumsSysAppDO sysAppDO = UumsSysAppConvertor.convertor2DO(sysAppModel);
			uumsSysAppDAO.insertUumsSysApp(sysAppDO);
			return new Result<Boolean>(true);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new Result(ResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}

	/**
	 * @see com.lambert.biz.uums.UumsSysAppManager#deleteUumsSysAppById(long)
	 */
	public Result<Boolean> deleteUumsSysAppById(long id) {
		try {
			UumsSysAppDO appDO = uumsSysAppDAO.queryUumsSysAppById(id);
			if (appDO != null && appDO.getCode().equals("SSO_LOGIN")) {
				return new Result(ResultCode.SATTUS_ERROR, "应用 " + appDO.getCode() + " 不允许删除");
			}
			uumsSysAppDAO.deleteUumsSysAppById(id);
			return new Result<Boolean>(true);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new Result(ResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}

	/** 
	 * @see com.lambert.biz.uums.UumsSysAppManager#queryAllUumsSysApp()
	 */
	public Result<List<UumsSysAppModel>> queryAllUumsSysApp(UumsSysAppQueryObj queryObj) {
		try {
			List<UumsSysAppDO> uumsSysAppDOList = uumsSysAppDAO.queryAllUumsSysApp(queryObj);
			List<UumsSysAppModel> appModelList = UumsSysAppConvertor.convertor2Model(uumsSysAppDOList);
			return new Result<List<UumsSysAppModel>>(appModelList);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new Result(ResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}

	/** 
	 * @see com.lambert.biz.uums.UumsSysAppManager#updateUumsSysAppById(com.lambert.biz.uums.model.UumsSysAppModel)
	 */
	public Result<Boolean> updateUumsSysAppById(UumsSysAppModel sysAppModel) {
		try{
			UumsSysAppDO appDO = uumsSysAppDAO.queryUumsSysAppById(sysAppModel.getId());
			if(appDO  == null){
				return new Result(ResultCode.ILLEGAL_PARAMS, "应用 " + sysAppModel.getId() + " 不存在");
			}else if(appDO.getCode().equals("SSO_LOGIN")){
				return new Result(ResultCode.ILLEGAL_PARAMS, "应用 " + appDO.getCode() + " 不允许修改");
			}
			UumsSysAppDO updateAppDO = uumsSysAppDAO.queryUumsSysAppByCode(sysAppModel.getAppCode());
			if(updateAppDO != null && sysAppModel.getId() != updateAppDO.getId()){
				LoggerUtil.info(LOGGER, "【updateUumsSysAppById】: appCode ： "+sysAppModel.getAppCode()+" 已存在");
				return new Result(ResultCode.ILLEGAL_PARAMS,"appCode ： "+sysAppModel.getAppCode()+" 已存在");
			}
			UumsSysAppDO sysAppDO = UumsSysAppConvertor.convertor2DO(sysAppModel);
			uumsSysAppDAO.updateUumsSysAppById(sysAppDO);
			return new Result<Boolean>(true);
		}catch (Exception ex){
			LOGGER.error(ex.getMessage(), ex);
			return new Result(ResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}

}
