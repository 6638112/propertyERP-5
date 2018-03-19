<%@page import="com.cnfantasia.server.ms.pub.session.UserContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>解放区运营管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/animate.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
</head>

<body>
<div class="info">
    <div class="welcome-txt">
        <div class="txt01 zoomInDown animated">欢迎进入<br /><span>解放区运营管理系统！</span></div>
        <div class="points">
            <ul class="point-list">
                <li><img src="<%=basePath%>/images/point01.png" /><br />任务管理</li>
                <li><img src="<%=basePath%>/images/point02.png" /><br />表格编辑</li>
                <li><img src="<%=basePath%>/images/point03.png" /><br />消息推送</li>
                <li><img src="<%=basePath%>/images/point04.png" /><br />数据分析</li>
            </ul>
            <div class="point-bg"><img src="<%=basePath%>/images/point-bg.png" /></div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js"></script>
<!--[if IE 6]>
<script type="text/javascript">
   document.getElementById("menu3").style.height=document.documentElement.clientHeight-72+"px";
</script>
<script type="text/javascript" src="js/DD_belatedPNG.js"></script>
<script type="text/javascript">
DD_belatedPNG.fix('.parent,.expanded,.points img');
</script>
<![endif]-->
</html>
