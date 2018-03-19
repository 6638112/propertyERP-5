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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- title><fmt:message key="manage.title" /></title> -->
	</head>
	<body>
	<center>
		<%
		  JsonResponse jsonResponse = (JsonResponse) request.getAttribute("jsonResponse");
		  String errCode = jsonResponse.getErrcode(); //错误代码
		  String status = jsonResponse.getStatus(); //错误代码
		  String errMessage = jsonResponse.getMessage(); //错误信息
		%>
		<table>
			<tr>
			  <td align='left' width='50px'>
					<img src='<%=basePath%>/images/error.png' />
				</td>
				<td align='left' height='100px'>
					<div>操作失败</div><br />
					<input type="hidden" value="<%=errCode%>"/>
					<div>状态码:<%=status%>,错误信息:<%=errMessage%></div><br />
				</td>
			</tr>
			<tr>
			   <td colspan="2" align="center"><input type="button" value="返回首页" onClick="window.top.location.href='<c:url value="/security/doLoginSuccess.html"/>'" ></td>
			</tr>
		</table>
		</center>
	</body>
</html>
