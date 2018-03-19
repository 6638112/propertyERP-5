<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限-用户管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>子账号管理</h2>
    <div class="distr">
        <div class="bs-example">
            <ul class="ranking">
            	<form id="searchForm" name="form_main" action="<%=basePath%>/omsUser/listSubUser.html" method=post>
	            	<li>角色名称： <input type="text" class="input_text pp" name="roleName" value="${param.roleName }" title="请输入角色名称"></li>
	            	<li>账号姓名： <input type="text" class="input_text pp" name="userName" value="${param.userName }" title="请输入姓名"></li>
	            	<li>账号： <input type="text" class="input_text pp" name="userAccount" value="${param.userAccount }" title="请输入账号"></li>
	                <li><input id="searchBtn" class="input-btn" type="submit" value="查询"></li>
	                <li><input id="addOmsUser" class="input-btn" type="button" value="新增账号"></li>
                </form>
            </ul>
            
            <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" >
				<display:column title="账号归属" property="parentName" />
				<display:column title="账号" property="userAccount" sortable="true"/>
				<display:column title="姓名" property="realName"></display:column>
				<display:column title="已选角色" > 
					<c:if test="${fn:length(row.roleNames)>0 }">${row.roleNames }</c:if>
				</display:column>
				<display:column title="联系方式" property="telPhone"></display:column>
				<display:column title="操作" media="html">
					<a class="blue" href="<%=basePath%>/omsUser/viewSubUser.html?id=${row.id}">查看</a> &nbsp;&nbsp;&nbsp;&nbsp;
					<a class="blue" href="<%=basePath%>/omsUser/editSubUser.html?id=${row.id}&parentUserId=${row.parentUserId }">编辑</a> &nbsp;&nbsp;&nbsp;&nbsp;
				</display:column> 
			</display:table>

      	</div>
    </div>
    
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">
	$("#addOmsUser").click(function(){//新增
		var href = "<%=basePath%>/omsUser/addNewSubUser.html";
		$(window.parent.document).find("#mainFrame").attr("src", href);
	});
	
</script>
</html>
    