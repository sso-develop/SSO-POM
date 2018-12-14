package com.lambert.web.feedback.uums;

import java.util.List;

import com.lambert.web.feedback.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lambert.biz.uums.UumsSysAppManager;
import com.lambert.biz.uums.model.UumsSysAppModel;
import com.lambert.biz.uums.queryObj.UumsSysAppQueryObj;
import com.lambert.common.uitl.result.Result;
import com.lambert.common.uitl.result.Pager;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author lambert  2018-10-15 22:29:56
 *
 */
@Controller
public class UumsSysAppController extends BaseController {
	@Autowired
	UumsSysAppManager uumsSysAppManager;
	
	@RequestMapping(value="/queryUumsSysAppByPager.json",method = RequestMethod.POST) 
	public void queryUumsSysAppByPager(HttpServletResponse response, UumsSysAppQueryObj uumsSysAppQuqeryObj){
		Result<Pager> result = uumsSysAppManager.queryUumsSysAppByPager(uumsSysAppQuqeryObj);
		writeSuccess2Response(response,result);
	}
	
	@RequestMapping(value="/queryUumsSysAppById.json",method = RequestMethod.POST)
	public void queryUumsSysAppById(HttpServletResponse response,Long id){
		Result<UumsSysAppModel> result = uumsSysAppManager.queryUumsSysAppById(id);
		writeSuccess2Response(response,result);
	}
	@RequestMapping(value="/insertUumsSysApp.json",method = RequestMethod.POST)
	public void insertUumsSysApp(HttpServletResponse response, UumsSysAppModel sysAppModel){
		sysAppModel.setSort(1);
		Result<Boolean> result = uumsSysAppManager.insertUumsSysApp(sysAppModel);
		writeSuccess2Response(response,result);
	}
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteUumsSysAppById.json",method = RequestMethod.POST)
	public void deleteUumsSysAppById(HttpServletResponse response,long id){
		Result<Boolean> result = uumsSysAppManager.deleteUumsSysAppById(id);
		writeSuccess2Response(response,result);
	}
	/**
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value="/queryAllUumsSysApp.json",method = RequestMethod.POST)
	public void queryAllUumsSysApp(HttpServletResponse response, UumsSysAppQueryObj quqeryObj){
		Result<List<UumsSysAppModel>> result = uumsSysAppManager.queryAllUumsSysApp(quqeryObj);
		writeSuccess2Response(response,result);
	}
	/**
	 * 更新
	 * 
	 * @param sysAppModel
	 * @return
	 */
	@RequestMapping(value="/updateUumsSysAppById.json",method = RequestMethod.POST)
	public void updateUumsSysAppById(HttpServletResponse response,UumsSysAppModel sysAppModel){
		Result<Boolean> result = uumsSysAppManager.updateUumsSysAppById(sysAppModel);
		writeSuccess2Response(response,result);
	}
}
