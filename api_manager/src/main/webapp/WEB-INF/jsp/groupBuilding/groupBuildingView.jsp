
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
</head>

<body>
<div class="info">
	<form class="inputform">
		<input type="hidden" name="supplyId" value="${entity.id }" />
        <h2>小区管理-查看详情</h2>
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
	      <tr>
	        <td align="right">管理处：</td>
	        <td>
	            ${entity.propertyManagementName }
	        </td>
	      </tr>
	      <tr>
	        <td align="right">小区所属街道办：</td>
	        <td>${entity.streetName }</td>
	      </tr>
	      <tr>
	        <td align="right">街道办电话：</td>
	        <td>${entity.streetTel }</td>
	      </tr>
	      <tr>
	        <td align="right">小区所在居委会：</td>
	        <td>${entity.neighborName }</td>
	      </tr>
	      <tr>
	        <td align="right">居委会电话：</td>
	        <td>${entity.neighborTel }</td>
	      </tr>
	      <tr>
	        <td align="right">缴费周期：</td>
	        <td>每月${entity.payPeriodStart }日 至 ${entity.payPeriodEnd }日</td>
	      </tr>
	    </table>
	    <div class="padb"><input type="button" class="info-btn" onclick="history.back();" value="返回" /></div>
    </form>
</div>
</body>
</html>
