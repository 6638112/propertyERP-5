<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
				window.top.location="<%=basePath%>/security/loginPage2.jsp";
			}
		</script>
	</head>
	<body onload="dosec();">
	</body>
</html>
