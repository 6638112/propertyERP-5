<%@page import="com.cnfantasia.server.api.pub.header.HeaderConstant"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path2 = request.getContextPath();
	String basePath2 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path2;
	String currPath = basePath2+"/signalStyle/xibaoPage/";
%>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>

<title>解放区喜报</title>
<base href="<%=currPath%>" target="_blank">
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<header class="top-bg">
	<div class="top-text">
        <div class="f16">截止${propPayEndDay}日${gbName}${propMonth}月物业折扣</div>
    </div>
    <img src="images/xibao-top-bg.png" />
</header>
<section>
    <div class="p010 mb20">
        <table class="bordered grey">
          <tr class="title">
            <th>排名</th>
            <th>楼栋</th>
            <th>解放号</th>
            <th>手机尾号</th>
            <th>折扣</th>
            <th>节省物业费（元）</th>
          </tr>
          <c:forEach var="tmpRow" items="${dataList}" >
          	<c:if test="${tmpRow.order==1}">
	          	<tr class="numb01">
		          	<th>${tmpRow.order}</th>
		          	<th>${tmpRow.buildingName}</th>
		            <th>${tmpRow.jfNum}</th>
		            <th>${tmpRow.phoneTail}</th>
		            <th>${tmpRow.discount}</th>
		            <th>${tmpRow.savedMoney}</th>
	           	</tr>
          	</c:if>
          	<c:if test="${tmpRow.order!=1}">
	          	<tr>
		          	<td>${tmpRow.order}</td>
		          	<td>${tmpRow.buildingName}</td>
		            <td>${tmpRow.jfNum}</td>
		            <td>${tmpRow.phoneTail}</td>
		            <td>${tmpRow.discount}</td>
		            <td>${tmpRow.savedMoney}</td>
	           	</tr>
          	</c:if>
		  </c:forEach>
        </table>
    </div>
    <ul class="xibao-foot displaybox">
    	<li class="boxflex01 text">*本月已有 <span class="xibao-num">${userCount}</span> 户小区居民<br> 享受了解放区物业折扣！</li>
        <li class="boxflex01"><img src="images/xibao-erweima.jpg" /></li>
    </ul>
</section>

</body>
</html>