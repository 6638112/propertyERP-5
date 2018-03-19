<%@page import="com.cnfantasia.server.common.json.JsonResponse"%>
<%@page import="com.cnfantasia.server.common.exception.FocRuntimeException"%>
<%@page import="com.cnfantasia.server.common.exception.FocException"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript">
			function dosec(){
				window.top.location="<%=basePath%>/qrCode/baseDown.html";
			}
		</script>
	</head>
	<body onload="dosec();">
	</body>
	
	<%-- 
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.10.2.min.js">
	</script>
	<script type="text/javascript">
		$.ajax({
	        type: "GET",
	        url: "http://www.jiefangqu.com:8080/behaviorLog/thirdParty.json?uqCode='IndexApp_IndexPage'&fromUrl="+window.location.href,
		});
	</script> 
	--%>
	
</html>
