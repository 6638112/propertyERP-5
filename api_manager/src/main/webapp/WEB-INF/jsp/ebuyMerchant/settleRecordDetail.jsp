<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<base href="<%=basePath%>/"/>
	<meta http-equiv="cleartype" content="on">
	<meta name="description" content="">
	<meta name="HandheldFriendly" content="True">
	<meta name="MobileOptimized" content="320">
	<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
	<link rel="dns-prefetch" href="//jiefangqu.com">

	<title>结算订单详情</title>
	<link rel="stylesheet" href="css/merchant/shopping.common.css">
</head>

<body class="bggrey">
<section id="wrapBox" class="sectionBox pos-relative minheight100">
	<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
		<header class="sectionBox fantasia-header order-top-bg">
			<a class="disblock mleft15 left white w80" href="javascript:void(0)" onclick="history.back()"><img class="back-arrow left" src="images/back-arrow.png" /> <span class="mleft10">返回</span></a>
			<div class="header-title">结算订单详情</div>
		</header>
		<section class="sectionBox">
			<!--有订单状态，显示内容-->
			<div class="cash-out-order-list-box borderbottomgrey" data-hasnext="true">
				<a class="cash-out-order-single hide" href="javascript:void(0)">
					<ul class="cash-out-order-list mleft15 borderbottomgrey">
						<li class="mtop5 f14 icon-order-list"><span class="left"><span class="deliveryAddress">星海名城1栋105室</span></span><span class="right red f20">￥<span class="deliveryOrderTotalAmount">60.00</span></span></li>
						<li class="f12 grey"><span class="left">共<span class="productQty">4</span>件商品</span><span class="right"></span></li>
						<li class="f12 grey">下单时间：<span class="deliveryOrderAddTime">2015-12-23</span></li>
					</ul>
				</a>
			</div>
		</section>
	</section>
</section>
<div class="sectionBox loading grey hide"><img src="images/merchant/loading01.gif" /> 加载中…</div>
<script src="js/merchant/jquery-1.11.2.min.js"></script>
<script src="js/merchant/fastclick.js"></script>
<script src="js/merchant/getSettleDetails.pages.js"></script>
</body>
</html>