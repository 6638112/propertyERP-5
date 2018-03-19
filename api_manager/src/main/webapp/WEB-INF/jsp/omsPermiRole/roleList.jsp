<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限-角色管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="../styles/displaytag-css/alternative.css">
</head>

<body>
<div class="info">
    <h2>角色管理</h2>
    <div class="distr">
        <div class="bs-example">
            <ul class="ranking">
            	<form id="searchForm" name="form_main" action="" method=post>
	            	<li>角色编码： <input type="text" class="input_text" name="roleCode" title="请输入角色编码" value="${param.roleCode}" ></li>
	            	<li>角色名： <input type="text" class="input_text" name="roleName" title="请输入角色名" value="${param.roleName}" ></li>
	                <li><input id="searchBtn" class="input-btn" type="submit" value="搜索"></li>
                </form>
            </ul>
            
			<display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" >
				<display:column title="序号"  sortable="true" headerClass="sortable">
				<c:out value="${row_rowNum}"/></display:column>
				<display:column title="角色编码" property="code" sortable="true"/>
				<display:column title="角色名" property="name" sortable="true"/>
				<display:column title="权限说明" property="desc"></display:column>
				<display:column title="启用状态">
					<c:if test="${row.status==1}">启用</c:if> <c:if test="${row.status==0}">禁用</c:if>
				</display:column>
				<display:column title="操作" media="html">
					<a class="blue" href="<%=basePath%>/omsPermiRole/roleEdit.html?id=${row.id}">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;
					<a class="blue" href="<%=basePath%>/omsPermiRole/roleStatusChange.html?id=${row.id}&status=${row.status}">
						<c:if test="${row.status==0}">启用</c:if> <c:if test="${row.status==1}">禁用</c:if>&nbsp;&nbsp;&nbsp;&nbsp;
					</a> 
					<a class="deleteRole" href="<%=basePath%>/omsPermiRole/roleDelete.html?id=${row.id}" >删除</a> 
				</display:column>
			</display:table>
      	</div>
    </div>
    <div class="padb"><div id="addRole" class="info-btn mtop20 left"><a href="#">新增角色</a></div> </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">
	$("#searchBtn").click(function(){//查找
		$("#searchForm").attr("action","<%=basePath%>/omsPermiRole/roleSearch.html");
		$("#searchForm").submit();
	});
	
	$("#addRole").click(function(){//新增
		var href = "<%=basePath%>/omsPermiRole/roleEdit.html";
		$(window.parent.document).find("#mainFrame").attr("src", href);
	});
	
	$(".deleteRole").click(function(){//删除
		if(!window.confirm('您确定要删除该记录吗？')){
			return false;
		}
	});
</script>
</html>
