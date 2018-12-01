package com.lambert.web.feedback.uums;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lambert.biz.uums.UumsSysAppManager;
import com.lambert.biz.uums.model.UumsSysAppModel;
import com.lambert.biz.uums.queryObj.UumsSysAppQueryObj;
import com.lambert.common.uitl.result.DefaultResult;
import com.lambert.common.uitl.result.DefaultWebUtils;
import com.lambert.common.uitl.result.Pager;
import com.lambert.common.uitl.result.ResultModel;
/**
 * 
 * @author lambert  2018-10-15 22:29:56
 *
 */
@Controller
public class UumsSysAppController {
	@Autowired
	UumsSysAppManager uumsSysAppManager;
	
	@RequestMapping(value="/queryUumsSysAppByPager.json",method = RequestMethod.POST) 
	public @ResponseBody ResultModel queryUumsSysAppByPager(ResultModel resultModel, UumsSysAppQueryObj uumsSysAppQuqeryObj){
		DefaultResult<Pager> result = uumsSysAppManager.queryUumsSysAppByPager(uumsSysAppQuqeryObj);
		DefaultWebUtils.putResult2ModelMap(result, resultModel);
		return resultModel;
	}
	
	@RequestMapping(value="/queryUumsSysAppById.json",method = RequestMethod.POST)
	public @ResponseBody ResultModel queryUumsSysAppById(ResultModel resultModel,Long id){
		DefaultResult<UumsSysAppModel> result = uumsSysAppManager.queryUumsSysAppById(id);
		DefaultWebUtils.putResult2ModelMap(result, resultModel);
		return resultModel;
	}
	@RequestMapping(value="/insertUumsSysApp.json",method = RequestMethod.POST)
	public @ResponseBody ResultModel insertUumsSysApp(ResultModel resultModel, UumsSysAppModel sysAppModel){
		sysAppModel.setSort(1);
		DefaultResult<Boolean> result = uumsSysAppManager.insertUumsSysApp(sysAppModel);
		DefaultWebUtils.putResult2ModelMap(result, resultModel);
		return resultModel;
	}
	/**
	 * 删除
	 * 
	 * @param resultModel
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteUumsSysAppById.json",method = RequestMethod.POST)
	public @ResponseBody ResultModel deleteUumsSysAppById(ResultModel resultModel,long id){
		DefaultResult<Boolean> result = uumsSysAppManager.deleteUumsSysAppById(id);
		DefaultWebUtils.putResult2ModelMap(result, resultModel);
		return resultModel;
	}
	/**
	 * 
	 * 
	 * @param resultModel
	 * @return
	 */
	@RequestMapping(value="/queryAllUumsSysApp.json",method = RequestMethod.POST)
	public @ResponseBody ResultModel queryAllUumsSysApp(ResultModel resultModel, UumsSysAppQueryObj quqeryObj){
		DefaultResult<List<UumsSysAppModel>> result = uumsSysAppManager.queryAllUumsSysApp(quqeryObj);
		DefaultWebUtils.putResult2ModelMap(result, resultModel);
		return resultModel;
	}
	/**
	 * 更新
	 * 
	 * @param resultModel
	 * @param sysAppModel
	 * @return
	 */
	@RequestMapping(value="/updateUumsSysAppById.json",method = RequestMethod.POST)
	public @ResponseBody ResultModel updateUumsSysAppById(ResultModel resultModel,UumsSysAppModel sysAppModel){
		DefaultResult<Boolean> result = uumsSysAppManager.updateUumsSysAppById(sysAppModel);
		DefaultWebUtils.putResult2ModelMap(result, resultModel);
		return resultModel;
	}
}
