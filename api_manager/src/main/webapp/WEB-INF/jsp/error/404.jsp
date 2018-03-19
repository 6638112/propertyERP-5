<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
    <%
	String path1 = request.getContextPath();
	String basePath1 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath1%>/"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>404页面</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body style="height:auto; background:#fff;">
    <div class="info" style="width:960px; margin:50px auto;">
        <div class="t_center f16">
         	<a id="returnIndex" href="#" class="blue" >
	            <div id="fofimg">
	            	<div id="fofman"><img src="images/fofman.png" /></div>
	            	<div id="fofdot"><img src="images/fofdot.png" /></div>
	            	<img class="fof" src="images/404.jpg" />
	            </div>
				<span class="black">对不起，找不到您请求的页面或请求出错！</span> 返回首页
			</a>
			<!-- ${stack} -->
		</div>
    </div>
</body>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
	$("#returnIndex").click(function(){
		window.top.location.href='<c:url value="/security/loginPage2.html"/>';
	});
</script>

</html>