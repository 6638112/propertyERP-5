<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>片区管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="../styles/displaytag-css/alternative.css">
</head>

<body>
<div class="info">
    <h2>片区管理</h2>
    <div class="distr">
        <div class="bs-example">
        	<div class="padb"><div id="addDistrict" class="info-btn mtop20 left"><a href="#">新增片区</a></div> </div>
			<display:table name="districtManagerList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" >
				<display:column title="片区名称"  sortable="true" headerClass="sortable">
					<c:out value="${row.name}"/>
				</display:column>
				<display:column title="片区负责人" property="director" sortable="true"/>
				<display:column title="负责人电话" property="directorPhone" sortable="true"/>
				<display:column title="负责人邮件" style="max-width:260px;">
					${fn:replace(row.directorEmail, ",", ", ")}
				</display:column>
				<display:column title="片区账户" style="max-width:220px;">
					<c:forEach var="user" items="${row.omsUserList }">
						${user.userAccount };
					</c:forEach>
				</display:column>
				<display:column title="片区管理的小区" style="max-width:300px;">
					<c:forEach var="gb" items="${row.groupBuildingList }">
						${gb.name };
					</c:forEach>
				</display:column>
				<display:column title="操作" media="html">
					<a class="blue" href="<%=basePath%>/district/districtEdit.html?id=${row.id}">片区管理</a> &nbsp;&nbsp;&nbsp;&nbsp;
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
$(function(){
	$("#addDistrict").click(function(){//新增
		var href = "<%=basePath%>/district/addDistrictPage.html";
		$(window.parent.document).find("#mainFrame").attr("src", href);
	});
});
</script>
</html>
