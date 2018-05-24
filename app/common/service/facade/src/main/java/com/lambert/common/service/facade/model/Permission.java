/**
 * LAMBERT Lin. Zekuan Lin
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package com.lambert.common.service.facade.model;

import java.io.Serializable;

/**
 * 
 * @author Administrator	
 * @version $Id: Permission.java, v 0.1 2017年12月19日 下午9:25:52 Administrator Exp $
 */
public class Permission implements Serializable{

	/**  */
	private static final long serialVersionUID = 5700232057143398027L;
	
	private String name;
	private String code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
