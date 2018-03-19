<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>热门拼购</title>
<link rel="stylesheet" href="../ebuyFight/css/groupbuying.css?v20160815" type="text/css">
</head>

<body>
<div class="sectionBox list-onsell">
	<c:forEach items="${proList}" var="product" >
		<section class="divide-box10 bgwhite"></section>
		<div class="pos-relative overhidden imgLoading m010 groupbuying-list">
			<a class="disblock" href="../laGroupPurchase/laProductDetail.do?productId=${product.fightProductId}">
				<div class="item-list-info">
					<div class="lineheight140 text-clamp f16">${product.name}</div>
					<div class="w150 lineheight140 pright10 red">￥<span class="f22 bold">${product.fightPrice/100}</span></div>
					<input class="input-btn-radius4 bgred" type="button" name="button" value="我要拼" />
				</div>
				<img class="disblock" src="${dataValue.picserverUrl}${product.picName}" />
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
		<c:forEach items="${preSellList}" var="product" >
			<section class="divide-box10 bgwhite"></section>
			<div class="pos-relative overhidden imgLoading m010 groupbuying-list">
				<img class="icon-mark" src="../ebuyFight/images/icon-mark-presell.png" />
				<a class="disblock" href="../laGroupPurchase/laProductDetail.do?productId=${product.fightProductId}">
					<div class="item-list-info">
						<div class="lineheight140 text-clamp f16">${product.name}</div>
						<div class="w150 lineheight140 pright10 red">￥<span class="f22 bold">${product.fightPrice/100}</span></div>
						<input class="input-btn-radius4 bgorange" type="button" name="button" value="抢先了解" />
					</div>
					<img class="disblock" src="${dataValue.picserverUrl}${product.picName}" />
				</a>
			</div>
		</c:forEach>
	</div>
</c:if>




<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/imgResize.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script>
$(function(){
	//new FastClick(document.body);
	
	//设置轮播图片尺寸
	$('.groupbuying-list').reSetSwiperImgSize(560,210);
	
	$(window).resize(function(){
		$('.groupbuying-list').reSetSwiperImgSize(560,210);
	});
});
</script>
</body>
</html>