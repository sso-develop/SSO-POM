package com.lambert.core.model.enums;

import org.apache.commons.lang.StringUtils;
/**
 * 工单审批状态
 * @author lambert  2018-10-29 16:51:56
 *
 */
public enum ApplyStatusEnum {
	
	
	CREACT("CREACT","创建"),
	
	SUBMIT("SUBMIT","提交");
	
	private String code;
	private String name;
	  
	
	private ApplyStatusEnum(String code,String name) {
		this.code = code;
		this.name = name;
	}
	
	public static ApplyStatusEnum getByCode(String code) {
		ApplyStatusEnum[] enums = ApplyStatusEnum.values();
		
		for (ApplyStatusEnum applyStatusEnum : enums) {
			if(StringUtils.equals(applyStatusEnum.getCode(), code)) {
				return applyStatusEnum;
			}
		}
		return null;
	}
	
	public String getCode() {
		return this.code;
	}
	public String getName() {
		return this.name;
	}
	
	
}
