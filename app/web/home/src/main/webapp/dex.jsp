<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String userName = request.getParameter("userName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>统一用户管理平台</title>
<script type="text/javascript">
	var userName = '${userName}';
</script>
</head>
<body>
   <div id="root"></div>
</body> 
</html>
<script type="text/javascript" src="http://localhost:3000/static/js/bundle.js"></script>

