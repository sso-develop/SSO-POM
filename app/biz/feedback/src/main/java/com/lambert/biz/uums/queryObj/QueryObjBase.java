/**
 * LAMBERT Lin. Zekuan Lin
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package com.lambert.biz.uums.queryObj;

/**
 * 
 * @author Administrator	
 * @version $Id: QueryObjBase.java, v 0.1 2017年12月31日 下午7:04:03 Administrator Exp $
 */
public class QueryObjBase {
	
	private int pageNumber = 1;// 当前页码
	private int pageSize = 10;// 每页记录数
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
