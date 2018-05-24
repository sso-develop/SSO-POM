package com.lambert.web.feedback.vo;

import java.util.List;

import com.lambert.common.uitl.result.Pager;

public class PageResult {
	
	List rows;
	int total;
	
	
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public void convertorPageResult(Pager pager){
		if(pager != null){
			this.rows = pager.getResult();
			this.total = pager.getTotalCount();
		}
	}

}
