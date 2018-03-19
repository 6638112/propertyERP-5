<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色管理——操作完成中转页</title>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	alert("<%=request.getAttribute("result")%>");
		$(window.parent.document).find("#mainFrame").attr('src', "<%=basePath%>/omsUser/list.html");
	});
</script>
</head>
<body>

</body>
</html>
