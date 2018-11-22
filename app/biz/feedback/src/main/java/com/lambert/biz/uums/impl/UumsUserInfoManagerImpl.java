package com.lambert.biz.uums.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.lambert.biz.uums.UumsUserInfoManager;
import com.lambert.biz.uums.convertor.UumsUserInfoConvertor;
import com.lambert.biz.uums.model.UumsUserInfoModel;
import com.lambert.biz.uums.queryObj.UumsUserInfoQueryObj;
import com.lambert.common.uitl.DateUtil;
import com.lambert.common.uitl.LoggerUtil;
import com.lambert.common.uitl.result.DefaultResult;
import com.lambert.common.uitl.result.DefaultResultCode;
import com.lambert.common.uitl.result.Pager;
import com.lambert.common.uums.dal.daointerface.UumsUserInfoDAO;
import com.lambert.common.uums.dal.daointerface.UumsUserPermissionRelationDAO;
import com.lambert.common.uums.dal.dataobject.UumsUserInfoDO;
import com.lambert.common.uums.dal.dataobject.UumsUserPermissionRelationDO;

public class UumsUserInfoManagerImpl implements UumsUserInfoManager{

	private static final Logger LOGGER = LoggerFactory.getLogger(UumsUserInfoManagerImpl.class);
	@Autowired
	private UumsUserInfoDAO uumsUserInfoDAO;
	@Autowired
	private UumsUserPermissionRelationDAO uumsUserPermissionRelationDAO;
	
	
	
	// 注入事务管理的模板  
	@Autowired
    private TransactionTemplate transactionTemplate;
	
	public DefaultResult<List<UumsUserInfoModel>> queryUumsUserInfo() {
		try {
			List<UumsUserInfoModel> models = new ArrayList<UumsUserInfoModel>();
			List<UumsUserInfoDO> list = uumsUserInfoDAO.queryUumsUserInfo();
			for (UumsUserInfoDO uumsUserInfoDO : list) {
				UumsUserInfoModel model = new UumsUserInfoModel(uumsUserInfoDO.getId(),
						DateUtil.getWebDateString(uumsUserInfoDO.getCreateDate()), 
						DateUtil.getWebDateString(uumsUserInfoDO.getModifyDate()),
						uumsUserInfoDO.getOperatorName(),
						uumsUserInfoDO.getRealName(), 
						uumsUserInfoDO.getNickName(), 
						uumsUserInfoDO.getMobile(), 
						uumsUserInfoDO.getStaffNo());
				models.add(model);
			}
			return new DefaultResult<List<UumsUserInfoModel>>(models);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}
	
	
	public DefaultResult<Pager> queryUumsUserInfoByPager(UumsUserInfoQueryObj queryObj) {
		try {
			Pager pager = new Pager(queryObj.getPageNumber(),queryObj.getPageSize());
			List<UumsUserInfoModel> models = new ArrayList<UumsUserInfoModel>();
			List<UumsUserInfoDO> list = uumsUserInfoDAO.queryUumsUserInfoByPager(pager.getBeginIndex(), 
					pager.getEndIndex(),
					queryObj.getNickName());
			for (UumsUserInfoDO uumsUserInfoDO : list) {
				UumsUserInfoModel model = new UumsUserInfoModel(uumsUserInfoDO.getId(),
						DateUtil.getWebDateString(uumsUserInfoDO.getCreateDate()), 
						DateUtil.getWebDateString(uumsUserInfoDO.getModifyDate()),
						uumsUserInfoDO.getOperatorName(),
						uumsUserInfoDO.getRealName(), 
						uumsUserInfoDO.getNickName(), 
			  			uumsUserInfoDO.getMobile(), 
						uumsUserInfoDO.getStaffNo());
				models.add(model);
			}
			
			int count = uumsUserInfoDAO.queryUumsUserInfoCountByPager();
			pager.setResult(models);
			pager.setTotalCount(count);
			return new DefaultResult<Pager>(pager);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
		
	}
	
	
	public DefaultResult<Boolean> insertUumsUserInfo(UumsUserInfoModel userInfoModel) {
		try {
			if(StringUtils.isBlank(userInfoModel.getOperatorName())){
				LoggerUtil.info(LOGGER, "【insertUumsUserInfo】:operatorName 不能为空 , operatorName="+userInfoModel.getOperatorName());
				return new DefaultResult(DefaultResultCode.ILLEGAL_PARAMS, "operatorName 不能为空 , operatorName="+userInfoModel.getOperatorName());
			}
			if(StringUtils.isBlank(userInfoModel.getRealName())){
				LoggerUtil.info(LOGGER, "【insertUumsUserInfo】:realName 不能为空 , realName="+userInfoModel.getRealName());
				return new DefaultResult(DefaultResultCode.ILLEGAL_PARAMS, "realName 不能为空 , realName="+userInfoModel.getRealName());
			}
			if(StringUtils.isBlank(userInfoModel.getMobile())){
				LoggerUtil.info(LOGGER, "【insertUumsUserInfo】:mobile 不能为空 , realName="+userInfoModel.getMobile());
				return new DefaultResult(DefaultResultCode.ILLEGAL_PARAMS, "mobile 不能为空 , realName="+userInfoModel.getMobile());
			}
			if(StringUtils.isBlank(userInfoModel.getNickName())){
				userInfoModel.setNickName(userInfoModel.getRealName());
			}
			String staffNo = UUID.randomUUID().toString();
			String password = "123456";
			UumsUserInfoDO userInfoDO = new UumsUserInfoDO(
					userInfoModel.getOperatorName(),
					userInfoModel.getRealName(), 
					userInfoModel.getNickName(), 
					userInfoModel.getMobile(), 
					staffNo, 
					password);
			uumsUserInfoDAO.insertUumsUserInfo(userInfoDO);
			return new DefaultResult<Boolean>(true);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
		
	}


	public DefaultResult<UumsUserInfoModel> queryUumsUserInfoCountByOperatorName(String operatorName) {
		try {
			UumsUserInfoDO uumsUserInfoDO = uumsUserInfoDAO.queryUumsUserInfoByOperatorName(operatorName);
			if(uumsUserInfoDO == null){
				return new DefaultResult(DefaultResultCode.DUPLICATE_DATA,"【"+operatorName+"】用户不存在");
			}
			UumsUserInfoModel model = new UumsUserInfoModel(uumsUserInfoDO.getId(),
					DateUtil.getWebDateString(uumsUserInfoDO.getCreateDate()), 
					DateUtil.getWebDateString(uumsUserInfoDO.getModifyDate()),
					uumsUserInfoDO.getOperatorName(),
					uumsUserInfoDO.getRealName(), 
					uumsUserInfoDO.getNickName(), 
		  			uumsUserInfoDO.getMobile(), 
					uumsUserInfoDO.getStaffNo());
			model.setPassword(uumsUserInfoDO.getPassword());
			
			return new DefaultResult<UumsUserInfoModel>(model);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}
	public DefaultResult<Boolean> deleteUumsUserInfoById(long id){
		try {
			
		    UumsUserInfoDO uumsUserInfoDO =	uumsUserInfoDAO.queryUumsUserInfoById(id);
		    if(uumsUserInfoDO == null){
		    	LoggerUtil.info(LOGGER, "【deleteUumsUserInfoById】:id 为 "+id+" 的用户不存在");
				return new DefaultResult(DefaultResultCode.ILLEGAL_PARAMS, "id 为 "+id+" 的用户不存在");
		    }
		    
		    if(uumsUserInfoDO.getOperatorName().equals("admin")){
		    	LoggerUtil.info(LOGGER, "【deleteUumsUserInfoById】:帐号  "+uumsUserInfoDO.getOperatorName()+" 的用户不能删除");
				return new DefaultResult(DefaultResultCode.ILLEGAL_PARAMS, "帐号  "+uumsUserInfoDO.getOperatorName()+" 的用户不能删除");
		    }
		    
			uumsUserInfoDAO.deleteUumsUserInfoById(id);
			return new DefaultResult<Boolean>(true);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}


	/** 
	 * @see com.lambert.biz.uums.UumsUserInfoManager#updateUumsUserInfoById(com.lambert.biz.uums.model.UumsUserInfoModel)
	 */
	public DefaultResult<Boolean> updateUumsUserInfoById(UumsUserInfoModel userInfoModel) {
		try {
			
			if(StringUtils.isBlank(userInfoModel.getOperatorName())){
				LoggerUtil.info(LOGGER, "【insertUumsUserInfo】:operatorName 不能为空 , operatorName="+userInfoModel.getOperatorName());
				return new DefaultResult(DefaultResultCode.ILLEGAL_PARAMS, "operatorName 不能为空 , operatorName="+userInfoModel.getOperatorName());
			}
			if(StringUtils.isBlank(userInfoModel.getRealName())){
				LoggerUtil.info(LOGGER, "【insertUumsUserInfo】:realName 不能为空 , realName="+userInfoModel.getRealName());
				return new DefaultResult(DefaultResultCode.ILLEGAL_PARAMS, "realName 不能为空 , realName="+userInfoModel.getRealName());
			}
			if(StringUtils.isBlank(userInfoModel.getMobile())){
				LoggerUtil.info(LOGGER, "【insertUumsUserInfo】:mobile 不能为空 , realName="+userInfoModel.getMobile());
				return new DefaultResult(DefaultResultCode.ILLEGAL_PARAMS, "mobile 不能为空 , realName="+userInfoModel.getMobile());
			}
			if(StringUtils.isBlank(userInfoModel.getNickName())){
				userInfoModel.setNickName(userInfoModel.getRealName());
			}
			UumsUserInfoDO infoDO = uumsUserInfoDAO.queryUumsUserInfoById(userInfoModel.getId());
			if(infoDO == null){
				LoggerUtil.info(LOGGER, "【insertUumsUserInfo】:id = "+userInfoModel.getId()+" 的帐号不存在");
				return new DefaultResult(DefaultResultCode.ILLEGAL_PARAMS, "admin 帐号允许修改");
			}else if(infoDO.getOperatorName().equals("admin")){
				LoggerUtil.info(LOGGER, "【insertUumsUserInfo】:admin 帐号允许修改");
				return new DefaultResult(DefaultResultCode.ILLEGAL_PARAMS, "admin 帐号允许修改");
			}
			/*****************************************************************************************************/
			UumsUserInfoDO infoMobileDO = uumsUserInfoDAO.queryUumsUserInfoByMobile(userInfoModel.getMobile());
			if(infoMobileDO != null && userInfoModel.getId() != infoMobileDO.getId()){
				LoggerUtil.info(LOGGER, "【insertUumsUserInfo】:手机号 = "+userInfoModel.getMobile()+" 的帐号已存在");
				return new DefaultResult(DefaultResultCode.DUPLICATE_DATA, "手机号 = "+userInfoModel.getMobile()+" 的帐号已存在");
			}
			UumsUserInfoDO infoOperatorNameDO = uumsUserInfoDAO.queryUumsUserInfoByOperatorName(userInfoModel.getOperatorName());
			if(infoOperatorNameDO != null && userInfoModel.getId() != infoOperatorNameDO.getId()){
				LoggerUtil.info(LOGGER, "【insertUumsUserInfo】:帐号  "+userInfoModel.getOperatorName()+" 已存在");
				return new DefaultResult(DefaultResultCode.DUPLICATE_DATA, "帐号  "+userInfoModel.getOperatorName()+" 已存在");
			}
			
			UumsUserInfoDO userInfoDO = new UumsUserInfoDO(
					userInfoModel.getOperatorName(),
					userInfoModel.getRealName(), 
					userInfoModel.getNickName(), 
					userInfoModel.getMobile(), 
					userInfoModel.getStaffNo(), 
					userInfoModel.getPassword());
			userInfoDO.setId(userInfoModel.getId());
			uumsUserInfoDAO.updateUumsUserInfoById(userInfoDO);
			return new DefaultResult<Boolean>(true);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}


	/** 
	 * @see com.lambert.biz.uums.UumsUserInfoManager#queryUumsUserInfoById(long)
	 */
	public DefaultResult<UumsUserInfoModel> queryUumsUserInfoById(long id) {
		try {
			UumsUserInfoDO userInfoDO = uumsUserInfoDAO.queryUumsUserInfoById(id);
			if(userInfoDO == null){
				LoggerUtil.info(LOGGER, "【queryUumsUserInfoById】:用户ID "+id+" 不存在");
				return new DefaultResult(DefaultResultCode.ILLEGAL_PARAMS, "用户ID "+id+" 不存在");
			}
			return new DefaultResult<UumsUserInfoModel>(UumsUserInfoConvertor.convertor2Model(userInfoDO));
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
		}
	}


	public DefaultResult<Boolean> insertUumsUserPermissionRelation(final Long[] permissionIds, final Long userId) {
		return transactionTemplate.execute(new TransactionCallback<DefaultResult<Boolean>>() {

			public DefaultResult<Boolean> doInTransaction(TransactionStatus status) {
				try {
					for (Long permissionId : permissionIds) {
						UumsUserPermissionRelationDO relationDO = new UumsUserPermissionRelationDO();
						relationDO.setPermissionId(permissionId);
						relationDO.setUserId(userId);
						uumsUserPermissionRelationDAO.insertUumsUserPermissionRelation(relationDO);
					}
					return new DefaultResult<Boolean>(true);
				} catch (Exception ex) {
					status.isRollbackOnly();
					LOGGER.error(ex.getMessage(), ex);
					return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
				}
			}
		});
	}


	public DefaultResult<Boolean> deleteUumsUserPermissionRelation(final Long[] permissionIds, final Long userId) {
		return transactionTemplate.execute(new TransactionCallback<DefaultResult<Boolean>>() {
			public DefaultResult<Boolean> doInTransaction(TransactionStatus status) {
				try {
					for (Long permissionId : permissionIds) {
						UumsUserPermissionRelationDO relationDO = new UumsUserPermissionRelationDO();
						relationDO.setPermissionId(permissionId);
						relationDO.setUserId(userId);
						uumsUserPermissionRelationDAO.deleteUumsUserPermissionRelation(permissionId, userId);
					}
					return new DefaultResult<Boolean>(true);
				} catch (Exception ex) {
					status.isRollbackOnly();
					LOGGER.error(ex.getMessage(), ex);
					return new DefaultResult(DefaultResultCode.SERVER_EXCEPTION, ex.getMessage());
				}
			}
		});
	}

}
