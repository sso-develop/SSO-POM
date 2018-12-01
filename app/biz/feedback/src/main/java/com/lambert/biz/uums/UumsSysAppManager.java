package com.lambert.biz.uums;


import java.util.List;

import com.lambert.biz.uums.model.UumsSysAppModel;
import com.lambert.biz.uums.queryObj.UumsSysAppQueryObj;
import com.lambert.common.uitl.result.DefaultResult;
import com.lambert.common.uitl.result.Pager;

/**
 * 应用
 *
 */
public interface UumsSysAppManager {
	/**
	 * 新增应用
	 * 
	 * @param sysAppModel
	 */
	public DefaultResult<Boolean> insertUumsSysApp(UumsSysAppModel sysAppModel);
	
	
	public DefaultResult<Pager> queryUumsSysAppByPager(UumsSysAppQueryObj queryObj);
	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public DefaultResult<UumsSysAppModel> queryUumsSysAppById(long id);
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public DefaultResult<Boolean> deleteUumsSysAppById(long id);
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public DefaultResult<List<UumsSysAppModel>> queryAllUumsSysApp(UumsSysAppQueryObj queryObj);
	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public DefaultResult<Boolean> updateUumsSysAppById(UumsSysAppModel sysAppModel);
}
