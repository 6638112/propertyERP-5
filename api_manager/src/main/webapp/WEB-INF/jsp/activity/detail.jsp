<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>活动详情</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/picbox.css">
	</head>
	<body>
		<div class="info">
		    <form class="inputform">
		        <h2>活动详情</h2>
		        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
		          <tr>
		            <td width="20%"><div class="list-name"><span class="red">*</span> 广告名称：</div></td>
		            <td>${ebuyAdvertise.tittle}</td>
		          </tr>
		          <tr>
		            <td><div class="list-name"><span class="red">*</span> 活动链接：</div></td>
		            <td>${ebuyAdvertise.linkUrl}</td>
		          </tr>
		          <tr>
		            <td><div class="list-name"><span class="red">*</span> 优先级：</div></td>
		            <td>${ebuyAdvertise.order}</td>
		          </tr> 
		          <tr>
		            <td align="right"><span class="red">*</span> app显示图片：</td>
		            <td class="item-upload-img">
		                <ul class="menu-img">
			            	<li><a href="${basePicPath}${appPic}" rel="lightbox-group"><img src="${basePicPath}${appPic}" border="0" /></a></li>
			            </ul>
		            </td>
		          </tr>
		          <tr>
		            <td align="right"><span class="red">*</span> 预告图片：</td>
		            <td class="item-upload-img">
		                <ul class="menu-img">
			            	<li><a href="${basePicPath}${previewPic}" rel="lightbox-group"><img src="${basePicPath}${previewPic}" border="0" /></a></li>
			            </ul>
		            </td>
		          </tr> 
		          <tr>
		            <td width="20%"><div class="list-name"><span class="red">*</span> 用户范围：</div></td>
		            <td>
						<c:choose>
							<c:when test="${areaType eq 1}">全国范围</c:when>
							<c:when test="${areaType eq 2}">城市</c:when>
							<c:when test="${areaType eq 3}">小区</c:when>
						</c:choose>
					</td>
		          </tr>
		          <c:choose>
					<c:when test="${areaType eq 2}">
						<tr class="city-con swap-con swap-box-5">
				            <td><div class="list-name">已选城市：</div></td>
				            <td>
					            <ul class="address-list selected-box">
									<c:forEach items="${areas}" var="city">
										<li class="posrelative address-selected"><span class="address-name">${city.cityName}</span></li>
									</c:forEach>
								</ul>
							</td>
			            </tr>
					</c:when>
					<c:when test="${areaType eq 3}">
						<tr class="city-con swap-con swap-box-5">
				            <td><div class="list-name">已选小区：</div></td>
				            <td>
				            	<ul class="address-list selected-box">
									<c:forEach items="${areas}" var="gb">
										<li class="posrelative address-selected"><span class="address-name">${gb.gbName}</span></li>
									</c:forEach>
								</ul>
							</td>
			            </tr>
					</c:when>
				  </c:choose>
		        </table>
		        <input class="info-btn" onclick="history.back()" type="button" value="返回">
		    </form>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/picbox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
</html>