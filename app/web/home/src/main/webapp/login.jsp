<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String backUrl = request.getParameter("backUrl");
	String appCode = request.getParameter("appCode");
	
	String errorMessage = request.getParameter("errorMessage");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head> 
		<meta charset="UTF-8">
		<title></title>
		<!-- <link rel="stylesheet" type="text/css" href="./static/login/css/reveal.css"/> -->
		<link rel="stylesheet" type="text/css" href="./static/login/css/login.css"/>
		<style type="text/css">
			.errorMessage{
			    font-size: 14px;
			    margin: 10px;
			    text-align: center;
			    color: red;
			}
		</style>
	</head>
	<body>
		<!-- <div class="header clearfix">
			<div class="logo container">
				<img src="image/logo.jpg" alt="" /><span>Merchant service system</span>
			</div>
		</div> -->
		<div class="main">
			<div class="container">
				<div class="login">
					<div class="login_cont">
						<h3>统一登录</h3>
						<form action="<%=basePath %>login" method="post" id="ff">
							<input name="backUrl" type="hidden" value="<%=backUrl %>"/>
							<input name="appCode" type="hidden" value="<%=appCode%>"/>
			                <label for="name">账户名</label>
			                <input id="name" class="name" type="text" name="username" placeholder="请输入邮箱或手机号码"><i class="reg f_name"></i>
			                <label for="pwd">密码</label>
			                <input id="pwd" class="pwd" type="password" name="password" placeholder="请填入密码"><i class="reg f_pwd"></i>
			                <div class="clearfix"></div>
			                <div class="btn">
			                	<button class="loginBtn" id="#btn-login">登录</button>
			                </div>
			                <div class="clearfix"></div>
			                <div class="errorMessage">${errorMessage }</div>
			            </form>
					</div>
				</div>
				<div class="clearfix"></div>
				<!-- <div class="prompt">
					<p style="color: red;">提示：欢迎登录点佰趣支付 收单服务系统；</p>
					<p style="color: red;">为了您的信息安全，首次登录后，请立即修改个人密码；</p>
					<p style="color: red;">技术故障请联系点佰趣支付客服：400-782-8006</p>
					<p>Key盾数字证书客户请下载安装：<a href="https://www.baidu.com">下载安全控件</a></p>
				</div> -->
			</div>
		</div>
		<div class="footer container">
			<p>Copyright © 2017 - . All Rights Reserved 版权所有</p>
		</div>
		<script src="./static/jquery.min.js"></script>
	</body>
</html>

<script>
	$("#btn-login").click(function(){
		 $('#ff').submit(); 
		return;
	});
</script>

