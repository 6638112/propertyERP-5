<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>万能插所有客户端连接</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
	</head>
	<body>
		<div class="info">
		    <h2>万能插所有客户端连接管理</h2>
		    <table class="mars info-list-02 mtop20">
				<thead>
					<tr class="title t_center">
						<td>序号</td>
						<td>ioSessionId</td>
						<td>API ip</td>
						<td>客户端ip</td>
						<td>客户端版本号</td>
						<td>小区</td>
						<td>连接时间</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${links}" var="row" varStatus="i">
					<tr class="t_center">
						<td>#${i.index+1}</td>
						<td>${row.ioSessionId}</td>
						<td>${row.localIp}</td>
						<td>${row.remoteIp}</td>
						<td>${row.version}</td>
						<td>${row.gbName}</td>
						<td>${row.linkTime}</td>
						<td>
							<a class="blue" href="javascript:void(0);" onclick="addBlackList('${row.remoteIp}', this);">加入黑名单</a>&nbsp;&nbsp;
							<a class="blue" href="javascript:void(0);" onclick="closeLink('${row.ioSessionId}', this);">断开连接</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
		// 加入黑名单
		function addBlackList(ip, o){
			if(confirm("确定把该ip加入黑名单？")){
				$.post("${pageContext.request.contextPath}/accessLink/addBlackList.html", {"ip":ip}, function(data){
					if(data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>') {
						$(o).parent().parent().remove();
					}
					alert(data.message);
				});
			}
		}
		
		// 断开连接
		function closeLink(ioSessionId, o){
			if(confirm("确定要断开该连接吗？")){
				$.post("${pageContext.request.contextPath}/accessLink/closeLink.html", {"ioSessionId":ioSessionId}, function(data){
					if(data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>') {
						$(o).parent().parent().remove();
					}
					alert(data.message);
				});
			}
		}
	</script>
</html>