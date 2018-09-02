package com.lambert.common.service.facade.filter;

public class ClientFilterConfig {
	
   public static ClientFilterProperties clientFilterProperties;
//   
// 单点登录服务端URL
	protected String ssoServerUrl;
	// 当前应用关联权限系统的应用编码
	protected String ssoAppCode;
   
   
   public void init() {  
	   if(clientFilterProperties == null) {
		   clientFilterProperties = new ClientFilterProperties();
	   } 
	   clientFilterProperties.setSsoAppCode(this.ssoAppCode);
	   clientFilterProperties.setSsoServerUrl(this.ssoServerUrl);
	  
   }


	public String getSsoServerUrl() {
		return ssoServerUrl;
	}
	
	
	public void setSsoServerUrl(String ssoServerUrl) {
		this.ssoServerUrl = ssoServerUrl;
	}
	
	
	public String getSsoAppCode() {
		return ssoAppCode;
	}
	
	
	public void setSsoAppCode(String ssoAppCode) {
		this.ssoAppCode = ssoAppCode;
	}  
	
}
