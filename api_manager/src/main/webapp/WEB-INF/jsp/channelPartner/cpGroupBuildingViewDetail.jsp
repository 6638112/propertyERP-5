<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>代理商管理-代理商资料</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
        <h2 class="mtop20">物业公司信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	      <tr>
	        <td width="120" align="right">物业公司名称：</td>
	        <td>${company.name }</td>
	      </tr>
	      <tr>
	        <td align="right">联系人：</td>
	        <td>${company.linkman }</td>
	      </tr>
	      <tr>
	        <td align="right">联系方式：</td>
	        <td>${company.mobilePhone }</td>
	      </tr>
	      <tr>
	        <td align="right">联系邮箱：</td>
	        <td>${company.email }</td>
	      </tr>
	    </table>
        
        <h2 class="mtop20">小区信息</h2>
        
        <display:table class="info-list-02" name="gbrList" id="row" >
        	<display:column title="小区名称" property="gbrName"></display:column>
        	<display:column title="所在省" property="apName"></display:column>
        	<display:column title="所在市" property="acName"></display:column>
        	<display:column title="所在区" property="abName"></display:column>
        	<display:column title="详情地址" property="gbrAddressdesc"></display:column>
        </display:table>
        <div class="padb"><input type="button" class="info-btn" onclick="history.back();" value="返回" /></div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
</html>
