<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cnfantasia.server.ms.pub.constant.JSPConstants" %>
<!DOCTYPE html>
<html>
<head>
<title>操作完成中转页</title>
</head>
<body>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	alert("<%=request.getAttribute(JSPConstants.OprtResult)%>");
		$(window.parent.document).find("#mainFrame").attr('src', "<%=request.getAttribute(JSPConstants.ToURL)%>");
	});
</script>
</html>
