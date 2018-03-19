<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cnfantasia.server.api.pub.header.HeaderConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>热门拼购</title>
<link rel="stylesheet" href="../css/groupbuying.css?v20160815" type="text/css">
</head>

<body style="opacity:0">

<div class="sectionBox list-onsell">
	<c:if test="${fn:length(preSellList) <= 0 && fn:length(proList) <= 0}">
		<div class="f16 mtop10 t-center lineheight140">暂无拼购</div>
	</c:if>
	<c:forEach var="product" items="${proList}">
		<section class="divide-box10 bgwhite"></section>
		<div class="pos-relative overhidden imgLoading m010 groupbuying-list">
			<a class="disblock" href="${product.linkUrl}">
				<div class="item-list-info">
					<div class="lineheight140 text-clamp f16">${product.productName}</div>
					<div class="w150 lineheight140 pright10 red">￥<span class="f22 bold">${product.fightPrice}</span></div>
					<input class="input-btn-radius4 bgred" type="button" name="button" value="我要拼" />
				</div>
				<img class="disblock" src="${product.fightPic}<c:choose><c:when test="${fn:contains(product.fightPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_560,h_211,limit_0/format,jpg/quality,q_80/interlace,1" />
			</a>
		</div>
	</c:forEach>
</div>
<c:if test="${fn:length(preSellList) > 0}">
<div class="sectionBox list-presell">
	<ul class="pos-relative presell-text m010">
		<li class="pos-relative text grey">下期预告</li>
		<li class="pos-absolute line borderbottomgrey"></li>
	</ul>
	<c:forEach items="${preSellList}" var="product">
		<section class="divide-box10 bgwhite"></section>
		<div class="pos-relative overhidden imgLoading m010 groupbuying-list">
			<img class="icon-mark" src="../images/icon-mark-presell.png" />
			<a class="disblock" href="${product.linkUrl}">
				<div class="item-list-info">
					<div class="lineheight140 text-clamp f16">${product.productName}</div>
					<div class="w150 lineheight140 pright10 red">￥<span class="f22 bold">${product.fightPrice}</span></div>
					<input class="input-btn-radius4 bgorange" type="button" name="button" value="抢先了解" />
				</div>
				<img class="disblock" src="${product.fightPic}<c:choose><c:when test="${fn:contains(product.fightPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_560,h_211/format,jpg/quality,q_80/interlace,1" />
			</a>
		</div>
	</c:forEach>
</div>
</c:if>
<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/imgResize.js?20160704"></script>
<script src="../js/fastclick.js"></script>
<script>
$(function(){
	new FastClick(document.body);

	if( $('.groupbuying-list').length > 0 || ( $('.groupbuying-list').length === 0 && getUrlParam('pic') == null ) ){
		$('body').css('opacity','1');
	}
	//如果没有商品，则跳转到活动预告
	if( $('.groupbuying-list').length === 0 && getUrlParam('pic') != null ){
		location.href = 'https://resource.jiefangqu.com/docs/actPreview/index.htm?pic=' + getUrlParam('pic');
	}
	function getUrlParam(name) {
		//构造一个含有目标参数的正则表达式对象
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		//匹配目标参数
		var r = window.location.search.substr(1).match(reg);
		//返回参数值
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	//设置轮播图片尺寸
	$('.groupbuying-list').reSetSwiperImgSize(560,210);
	
	$(window).resize(function(){
		$('.groupbuying-list').reSetSwiperImgSize(560,210);
	});
});
</script>
</body>
</html>