<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>限时购详情</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <form class="inputform">
        <h2>限时购详情</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">商品信息</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">供应商：</div></td>
            <td>${lba.merchantName}</td>
          </tr>
          <tr>
            <td><div class="list-name">商品ID：</div></td>
            <td>${lba.productId}</td>
          </tr>
          <tr>
            <td><div class="list-name">货架分类：</div></td>
            <td>${lba.shelfType}</td>
          </tr>
          <tr>
            <td><div class="list-name">商品名称：</div></td>
            <td>${lba.productName}</td>
          </tr>
          <tr>
            <td><div class="list-name">商品售价：</div></td>
            <td><fmt:formatNumber value="${lba.originalPrice}" type="currency" pattern="0.00" maxFractionDigits="2"/>元</td>
          </tr>
        </table>
        <table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">抢购信息</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 开始时间：</div></td>
            <td>${lba.startTime}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 结束时间：</div></td>
            <td>${lba.endTime}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 标题：</div></td>
            <td>${lba.title}</td>
          </tr> 
          <tr>
            <td><div class="list-name"><span class="red">*</span> 抢购价：</div></td>
            <td><fmt:formatNumber value="${lba.robPrice}" type="currency" pattern="0.00" maxFractionDigits="2"/>元</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 促销库存：</div></td>
            <td>${lba.leftCount}</td>
          </tr> 
          <tr>
            <td><div class="list-name"><span class="red">*</span> 每人限购：</div></td>
            <td>
	            <c:choose>
	            	<c:when test="${lba.maxCanBuy>-1 }">${lba.maxCanBuy}</c:when>
	            	<c:otherwise>不限购</c:otherwise>
	            </c:choose> 
            </td>
          </tr> 
        </table>
        <input class="info-btn" onclick="history.back()" type="button" value="返回">
        <div class="h30"></div>
    </form>
</div>
</body>
</html>