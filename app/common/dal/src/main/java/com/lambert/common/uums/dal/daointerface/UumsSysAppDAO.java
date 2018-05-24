package com.lambert.common.uums.dal.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lambert.common.uums.dal.dataobject.UumsSysAppDO;

/**
 * 应用
 *
 */
public interface UumsSysAppDAO {

	public List<UumsSysAppDO> queryUumsSysAppByPager(@Param("offset") int offset, @Param("limit") int limit,
			@Param("name") String name,
			@Param("code") String code);

	public int queryUumsSysAppCountByPager(@Param("name") String name,
			@Param("code") String code);

	/**
	 * 
	 * @param code
	 * @return
	 */
	public UumsSysAppDO queryUumsSysAppByCode(@Param("code") String code);

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public UumsSysAppDO queryUumsSysAppById(@Param("id") long id);

	/**
	 * 
	 * 
	 * @param uumsSysAppDO
	 */
	public void insertUumsSysApp(UumsSysAppDO uumsSysAppDO);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteUumsSysAppById(@Param("id") long id);
	/**
	 * 查询所有App
	 * @return 
	 */
	public List<UumsSysAppDO> queryAllUumsSysApp();
	/**
	 * App
	 * 
	 * @param uumsSysAppDO
	 */
	public void updateUumsSysAppById(UumsSysAppDO uumsSysAppDO);
}
