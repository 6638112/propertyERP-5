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
<title>社区商家——操作完成中转页</title>
    <%@ include file="../common/static.jsp"%>
<script type="text/javascript">
    $(document).ready(function(){
    	alert("<%=request.getAttribute("result")%>");
		$(window.parent.document).find("#mainFrame").attr('src', "<%=basePath%>/communitySupply/list.html");
	});
</script>
</head>
<body>

</body>
</html>
