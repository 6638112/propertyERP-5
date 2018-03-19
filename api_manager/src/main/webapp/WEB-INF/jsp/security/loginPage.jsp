<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>解放区管理系统登录</title>
<%@ include file="../common/static.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css?20160520">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/login.css">
</head>
<body class="resetbody">
<div class="login-main resetmain">
	<form action="<%=basePath%>/login/doLogin.html" method="post" accept-charset="UTF-8" ><!-- style="margin-top: 4ex;" -->
		<div class="login-top"><span class="txt">中国最大的互联网<br />社区服务平台</span></div>
	    <div class="login-title">登录管理后台</div>
	    <ul class="login-info">
	    	<li class="border">帐号 <input id="loginNumber" class="login-txt input_text" name="number" type="text" placeholder="请填写企业完整帐号或管理员帐号" /></li>
	    	<li>密码 <input id="loginPassword" class="login-txt" name="password" type="password" /></li>
	    	<c:if test="${rtInfo=='passwordError' }">
		    	<li class='red f14 erroTxt' style='text-align:left; margin:-10px 0 10px 72px !important'><img class="mtop3" src="../images/error.png" />&nbsp;用户密码不正确</li>
	    	</c:if>
	    </ul>
	    <input class="login-btn" name="" type="submit" value="登 录" />
	    <!-- <ul class="login-password">
	    	<li class="left"><span class="ck-box"></span> 记住密码</li>
	    	<li class="right"><a href="#" target="_blank">忘记密码？</a></li>
	    </ul> -->
    </form>
    <div class="register"><a href="../propertyCompany/register.html" target="_blank">账号申请 <span class="grey">我是物业公司人员，申请免费使用解放区平台</span></a></div>
</div>
<div class="footer rela-foot">
	<p>粤ICP备14059299号<br />Copyright <span>&copy;</span> 2014 深圳前海邻里乐科技服务有限公司 All rights reserved.</p>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/data.encrypt.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.easing.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js?V20170725"></script>
</html>