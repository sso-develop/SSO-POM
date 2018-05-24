package com.lambert.common.uums.dal.dataobject;

import java.util.Date;

public class UumsUserInfoDO {
	
	private Long id;
	private Date createDate;
	private Date modifyDate;
	private String operatorName;
	private String realName;
	private String nickName;
	private String mobile;
	private String staffNo;
	private String password;
	
	
	public UumsUserInfoDO() {
		super();
	}



	public UumsUserInfoDO(String operatorName, String realName, String nickName, String mobile, String staffNo,
			String password) {
		super();
		this.operatorName = operatorName;
		this.realName = realName;
		this.nickName = nickName;
		this.mobile = mobile;
		this.staffNo = staffNo;
		this.password = password;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
