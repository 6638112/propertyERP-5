<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>/"/>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
	<link rel="dns-prefetch" href="//jiefangqu.com">

<title>结算记录</title>
<link rel="stylesheet" href="css/merchant/shopping.common.css">
</head>

<body class="bggrey">
<section id="wrapBox" class="sectionBox pos-relative minheight100">
	<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
		<header class="sectionBox fantasia-header order-top-bg">
			<a class="disblock mleft15 left white w80" href="ebuyMerchant/settleCenter.html"><img class="back-arrow left" src="images/back-arrow.png" /> <span class="mleft10">返回</span></a>
    		<div class="header-title">结算记录</div>
		</header>
	    <div class="hd tab-head borderbottomgrey">
	        <ul class="order-tab">
	            <li id="orderTab01" class="wp50 tap-nobg order-tab-title" data-page="1" data-settleStatus="1" data-next="true"><a href="javascript:void(0)">待结算</a></li>
	            <li id="orderTab02" class="wp50 tap-nobg order-tab-title" data-page="1" data-settleStatus="2" data-next="true"><a href="javascript:void(0)">已结算</a></li>
	        </ul>
	    </div>
		<section class="divide-box borderbottomgrey"></section>
		<section class="sectionBox">
		    <div class="order-con dsn">
			    <div class="cash-out-order-list-box borderbottomgrey">
			    </div>
		    </div>
		    
		    <div class="order-con dsn">
			    <div class="cash-out-order-list-box borderbottomgrey">
			    </div>
		    </div>
		</section>
	    <a class="order-list hide" href="tallyOrder.html">
			<ul class="p1015 lineheight180 displaybox borderbottomgrey">
				<li class="mtop5 f14 boxflex01"><span class="f14"><span class="settle-date">2016.9.12</span>申请结算订单</span><br><span class="grey f12 settle-status">审核中</span></li>
				<li class="f12 grey t-right"><span class="red f18">￥<span class="settle-amount">60.00</span></span><br><span class="grey f12">共<span class="settle-count">4</span>笔订单</span></li>
			</ul>
			<section class="divide-box borderbottomgrey"></section>
		</a>
	</section>
</section>
<div class="sectionBox loading grey hide"><img src="images/merchant/loading01.gif" /> 加载中…</div>

<script src="js/merchant/jquery-1.11.2.min.js"></script>
<script src="js/merchant/fastclick.js"></script>
<script src="js/merchant/getSettleRecords.pages.js"></script>
</body>
</html>