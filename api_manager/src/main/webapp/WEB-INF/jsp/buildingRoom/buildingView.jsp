
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-小区管理-查看详情</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
</head>

<body>
<div class="info">
    <form class="inputform">
		<input type="hidden" name="buildingId" value="${entity.id }" />
        <h2>楼栋管理-查看详情</h2>
	    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	      <tr>
	        <td width="120" align="right">管理处：</td>
	        <td>
	        	${entity.managementName }
	        </td>
	      </tr>
	      <tr>
	        <td align="right">所属小区：</td>
	        <td>
	        	${entity.groupbuildingName }
	        </td>
	      </tr>
	      <tr>
	        <td align="right"><span class="red">*</span> 楼栋名：</td>
	        <td>
	        	${entity.name }
	         </td>
	      </tr>
	    </table>
    </form>
    <h2>房号管理</h2>
    <display:table name="roomList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="roomSize" >
		<display:column title="单元号" sortable="true">
			${row.unitName}
		</display:column>
		<display:column title="房间号">
			${row.numTail}
		</display:column>
		<display:column title="户名">${row.proprietor.name}</display:column>
		<display:column title="联系电话">${row.proprietor.phone}</display:column>
		<display:column title="身份证">${row.proprietor.identifyNo}</display:column>
	</display:table>
	<div class="padb"><input type="button" class="info-btn" onclick="history.back();" value="返回" /></div>
</div>
</body>
</html>
