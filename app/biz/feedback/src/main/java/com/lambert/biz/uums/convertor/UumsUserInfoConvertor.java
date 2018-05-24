/**
 * LAMBERT Lin. Zekuan Lin
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.lambert.biz.uums.convertor;

import com.lambert.biz.uums.model.UumsUserInfoModel;
import com.lambert.common.uitl.DateUtil;
import com.lambert.common.uums.dal.dataobject.UumsUserInfoDO;

/**
 * 
 * @author Administrator	
 * @version $Id: UumsUserInfoConvertor.java, v 0.1 2018年2月6日 下午9:26:21 Administrator Exp $
 */
public class UumsUserInfoConvertor {
	
	public static UumsUserInfoModel convertor2Model(UumsUserInfoDO uumsUserInfoDO){
		return new UumsUserInfoModel(uumsUserInfoDO.getId(),
				DateUtil.getWebDateString(uumsUserInfoDO.getCreateDate()), 
				DateUtil.getWebDateString(uumsUserInfoDO.getModifyDate()),
				uumsUserInfoDO.getOperatorName(),
				uumsUserInfoDO.getRealName(), 
				uumsUserInfoDO.getNickName(), 
	  			uumsUserInfoDO.getMobile(), 
				uumsUserInfoDO.getStaffNo());
	}

}
