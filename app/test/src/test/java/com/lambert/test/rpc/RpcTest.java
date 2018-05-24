package com.lambert.test.rpc;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.lambert.common.service.facade.model.LoginInfo;
import com.lambert.common.service.facade.rpc.AuthenticationRpcService;

public class RpcTest {
	 public static void main(String[] args) {  
		    //在服务器端的web.xml文件中配置的HessianServlet映射的访问URL地址  
		    String url = "http://127.0.0.1:8081/sso/rpc/authenticationRpcService";   
		    HessianProxyFactory factory = new HessianProxyFactory();   
		    AuthenticationRpcService sayHello = null;  
		    
		    
		    try {
		    	sayHello = (AuthenticationRpcService) factory.create(AuthenticationRpcService.class, url);
				
				LoginInfo a = sayHello.findAuthInfo("abc");
				//System.out.println("fafa   "+a);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
		 }
		 
}
