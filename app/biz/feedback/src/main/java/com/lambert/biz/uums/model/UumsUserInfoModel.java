package com.lambert.biz.uums.model;

public class UumsUserInfoModel {
	private Long id;
	private String createDate;
	private String modifyDate;
	private String operatorName;
	private String realName;
	private String nickName;
	private String mobile;
	private String staffNo;
	private String password;
	
	
	
	
	
	public UumsUserInfoModel() {
		super();
	}
	
	
	
	
	
	public UumsUserInfoModel(Long id, String createDate, String modifyDate, String operatorName, String realName,
			String nickName, String mobile, String staffNo) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.operatorName = operatorName;
		this.realName = realName;
		this.nickName = nickName;
		this.mobile = mobile;
		this.staffNo = staffNo;
	}





	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
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
