
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>渠道合伙人-注册小区查看</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
		<input type="hidden" name="gbrId" value="${entity.id }" />
        <h2>小区审核-查看</h2>
	    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	      <tr>
	        <td width="120" align="right">小区名称：</td>
	        <td>${entity.name }</td>
	      </tr>
	      <tr>
	        <td align="right">小区所属物业：</td>
	        <td>${entity.propertyCompanyName }</td>
	      </tr>
	      <tr>
	        <td align="right">小区所在地：</td>
	        <td>
	            ${entity.addressProvinceName}-${entity.addressCityName}-${entity.addressBlockName}
	         </td>
	      </tr>
	      <tr>
	        <td align="right">详细地址：</td>
	        <td>${entity.addressDesc }</td>
	      </tr>
	    </table>
        <div class="padb">
        	<input type="button" class="info-btn" onclick="history.back();" value="返回" />
        </div>
</div>
</body>
</html>
