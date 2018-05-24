/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lambert.core.service.security;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lambert.common.uums.dal.daointerface.UumsUserInfoDAO;
import com.lambert.common.uums.dal.dataobject.UumsUserInfoDO;
import com.lambert.core.model.model.Principal;

/**
 * 系统安全认证实现类
 * 
 * @author Lambert
 * @version 2016-06-23
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {
	 private static final Logger logger = LoggerFactory.getLogger(SystemAuthorizingRealm.class); 
	
	@Autowired
	private UumsUserInfoDAO uumsUserInfoDAO;
	
	//这里因为没有调用后台，直接默认只有一个用户("luoguohui"，"123456")
    //private static final String USER_NAME = "admin";  
    //private static final String PASSWORD = "admin";  

    /* 
     * 授权
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { 
        Set<String> roleNames = new HashSet<String>();  
        Set<String> permissions = new HashSet<String>();  
        roleNames.add("administrator");//添加角色
        permissions.add("newPage.jhtml");  //添加权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);  
        info.setStringPermissions(permissions);  
        return info;  
    }

    /* 
     * 登录验证  认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken){
    	
		    UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
	        String operatorName = token.getUsername();
	        String passwd = String.valueOf(token.getPassword());
	        UumsUserInfoDO userInfoDO = uumsUserInfoDAO.queryUumsUserInfoByOperatorName(operatorName);
	        if(userInfoDO == null){
	        	//logger.info("用户名错误！！！");
	        	throw new UnknownAccountException("用户名错误！");  
	        }
		    if(!userInfoDO.getPassword().equals(passwd)){
		    	//logger.info("密码错误！！！");
		    	throw new IncorrectCredentialsException("密码错误！！！");    
		    }
	        Principal principal =  new Principal(userInfoDO.getId()+"",userInfoDO.getOperatorName(),userInfoDO.getNickName());
	        return new SimpleAuthenticationInfo(principal, passwd, getName());  
		
      
        /*if(token.getUsername().equals(USER_NAME)){
        	Principal principal =  new Principal("1",USER_NAME,USER_NAME);
        	return new SimpleAuthenticationInfo(principal, 
        			passwd, getName());  
        }else{
           throw new AuthenticationException();  
            return null;
        }*/
    }

  
	
}
