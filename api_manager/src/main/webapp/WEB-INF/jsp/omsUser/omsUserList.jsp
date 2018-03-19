<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="../error/404.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%
	response.setHeader("Pragma","No-cache");    
	response.setHeader("Cache-Control","no-cache");    
	response.setDateHeader("Expires", -10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限-用户管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<div class="info">
    <h2>用户管理</h2>
    <div class="distr">
        <div class="bs-example">
            <ul class="ranking">
            	<form id="searchForm" name="form_main" action="${pageContext.request.contextPath}/omsUser/search.html" method=post>
	            	<li>账号： <input type="text" class="input_text pp" name="account" title="请输入账号" value="${param.account}"></li>
	            	<li>姓名： <input type="text" class="input_text pp" name="realName" title="请输入姓名" value="${param.realName}"></li>
	            	<li>状态： <select class="select_normal w131" name="userState">
	                <option value="">全部</option>
	                <option value="0" <c:if test="${(not empty param.userState) and (param.userState eq 0)}"> selected</c:if>>启用</option>
	                <option value="1" <c:if test="${(not empty param.userState) and (param.userState eq 1)}"> selected</c:if>>禁用</option>
	                </select></li>
	                <li><input id="searchBtn" class="input-btn" type="submit" value="搜索"></li>
                </form>
            </ul>
            
            <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" size="total">
				<display:column title="序号"  sortable="true" headerClass="sortable">
				<c:out value="${row_rowNum}"/></display:column>
				<display:column title="账号" property="userAccount" sortable="true"/>
				<display:column title="姓名" property="realName"></display:column>
				<display:column title="邀请码" property="inviteCode"></display:column>
				<display:column title="启用状态">
					<c:if test="${row.userState==0}">启用</c:if> <c:if test="${row.userState==1}">禁用</c:if>&nbsp;&nbsp;&nbsp;&nbsp;
				</display:column>
				<display:column title="操作" media="html">
					<a class="blue" href="${pageContext.request.contextPath}/omsUser/edit.html?id=${row.id}">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;
					<a class="blue" href="${pageContext.request.contextPath}/omsUser/changeStatus.html?id=${row.id}&status=${row.userState}">
						<c:if test="${row.userState==1}">启用</c:if> <c:if test="${row.userState==0}">禁用</c:if>&nbsp;&nbsp;&nbsp;&nbsp;
					</a> 
					<a class="deleteOmsUser" href="${pageContext.request.contextPath}/omsUser/delete.html?id=${row.id}" >删除</a> 
				</display:column> 
			</display:table>

      	</div>
    </div>
    <div class="padb"><div id="addOmsUser" class="info-btn mtop20 left"><a href="#">新增账号</a></div> </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
<script type="text/javascript">
	$("#searchBtn").click(function(){//查找
		$("#searchForm").attr("action","${pageContext.request.contextPath}/omsUser/search.html");
		$("#searchForm").submit();
	});
	
	$("#addOmsUser").click(function(){//新增
		var href = "${pageContext.request.contextPath}/omsUser/addNew.html";
		$(window.parent.document).find("#mainFrame").attr("src", href);
	});
	
	$(".deleteOmsUser").click(function(){//删除
		if(!window.confirm('您确定要删除该记录吗？')){
			return false;
		}
	});
</script>
</html>
    