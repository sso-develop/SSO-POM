package com.lambert.common.service.facade.filter;

public class ClientFilterProperties {
	// 单点登录服务端URL
		public String ssoServerUrl;
		// 当前应用关联权限系统的应用编码
		public String ssoAppCode;
		
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
