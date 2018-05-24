package com.lambert.common.uums.dal.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lambert.common.uums.dal.dataobject.UumsUserInfoDO;

public interface UumsUserInfoDAO {
	public List<UumsUserInfoDO> queryUumsUserInfo();
	public List<UumsUserInfoDO> queryUumsUserInfoByPager(@Param("offset") int offset,@Param("limit")int limit,@Param("nickName")String nickName);
	public UumsUserInfoDO queryUumsUserInfoByOperatorName(@Param("operatorName") String operatorName);
	public int queryUumsUserInfoCountByPager();
	public void insertUumsUserInfo(UumsUserInfoDO userInfoDO);
	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteUumsUserInfoById(@Param("id")long id);
	/**
	 * 通过ID查询用户
	 * 
	 * @param id
	 */
	public UumsUserInfoDO queryUumsUserInfoById(@Param("id")long id);
	/**
	 * 通过ID修改用户信息
	 * 
	 * @param userInfoDO
	 */
	public void updateUumsUserInfoById(UumsUserInfoDO userInfoDO);
	/**
	 * 
	 * 
	 * @param mobile
	 */
	public UumsUserInfoDO queryUumsUserInfoByMobile(@Param("mobile")String mobile);
}
