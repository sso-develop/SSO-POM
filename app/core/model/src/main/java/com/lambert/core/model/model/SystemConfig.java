package com.lambert.core.model.model;


/**
 * 
 * @author lambert  2018-10-24 19:58:59
 *
 */
public class SystemConfig {
	/**
	 * 页面模板路径
	 */
	private static String VELOCITY_FILE_RESOURCE_LOADER_PAHT;
	/**
	 * 单点登录路径
	 */
	private static String SSO_SERVER_URL;
	/**
	 * 单点登录应用CODE
	 */
	private static String SSO_APP_CODE;
	/**
	 *
	 */
	public static String ASSETS_SERVER_HOST;
	/**
	 *
	 */
	public static String ASSETS_SERVER_PORT;
	
	public void setSsoAppCode(String ssoAppCode) {
		this.SSO_APP_CODE = ssoAppCode;
	}  
	public void setSsoServerUrl(String ssoServerUrl) {
		this.SSO_SERVER_URL = ssoServerUrl;
	}
	
	public void setVelocityFile(String velocityFile) {
		this.VELOCITY_FILE_RESOURCE_LOADER_PAHT = velocityFile;
	}
	public String getSsoServerUrl() {
		return SSO_SERVER_URL;
	}
	
	public String getSsoAppCode() {
		return SSO_APP_CODE;
	}
	
	public String getVelocityFile() {
		return this.VELOCITY_FILE_RESOURCE_LOADER_PAHT;
	}

	//assetsServer_host = linzekuan.gotoip11.com
	//assetsServer_port = 80

	public void setAssetsServerHost(String assetsServerHost){
		this.ASSETS_SERVER_HOST = assetsServerHost;
	}

	public void setAssetsServerPort(String assetsServerPort){
		this.ASSETS_SERVER_PORT = assetsServerPort;
	}
}
