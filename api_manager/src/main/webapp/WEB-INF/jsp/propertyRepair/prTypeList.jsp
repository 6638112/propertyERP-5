<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修管理-报修类型管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>报修类型管理</h2>
    <div class="bs-example bgebeb">
    	<form action="../propertyRepair/prtSearch.html">
	        <table class="info-list" border="0">
	          <tr>
	            <td width="60"><div align="right">报修类型：</div></td>
	            <td width="120"><input type="text" class="input_text w100" name="prtName" value="${param.prtName }" ></td>
	            <td><input class="input-btn w80" type="submit" value="搜索"></td>
	            <td></td>
	          </tr>
	        </table>
    	</form>
    </div>    
    
   <display:table class="info-list-02 mtop20" cellpadding="0" cellspacing="1"  name="resList" id="row" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize"> 
    	<display:column property="name" title="报修类型" />
    	<display:column property="pmName" title="管理处名称"/>
    	<display:column title="操作"  >
	    	<%-- <a class="blue editRepair" href="../propertyRepair/editRepairerType.html?repairerTypeId=${row.id }">编辑</a>&nbsp;&nbsp; --%>
	    	<a class="blue" href="../propertyRepair/deleteRepairerType.html?repairerTypeId=${row.id }">删除</a>
    	</display:column> 
    </display:table>
	<div class="padb"><div class="info-btn mtop20 left newRepairType"><a href="../propertyRepair/addNewRepairerType.html">维护报修类型</a></div></div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
</html>
