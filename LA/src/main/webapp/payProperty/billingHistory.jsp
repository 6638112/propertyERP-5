<%-- 
    Document   : 往期账单
    Created on : 2016-3-15, 11:49:24
    Author     : Liyl lyl010991@126.com
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>往期账单</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopping.common.css">
	</head>
	<body class="bggrey">
		<section class="sectionBox">
		    <div class="list-box bggrey bordertbgrey">
		        <div class="item-standard-name f14 grey">
		        	<span class="left">2015年8月</span>
		        	<span class="right">合计：669.00元</span>
		        </div>
		    </div>
		    <div class="cash-out-order-list-box borderbottomgrey">
				<ul class="cash-out-order-list billing-history-list mleft15">
					<li class="f16">
						<span class="left">物业费</span>
						<span class="right f20">￥<span class="singleOrderPrice">288.50</span></li>
					<li class="property-bill-list borderbottomgrey">
						<div class="grey mtop5">缴费成功</div>
						<div class="grey mtop5">5月25日 18:30</div>
					</li>
				</ul>
		    </div>
		</section>
		<section class="sectionBox">
		    <div class="list-box bggrey borderbottomgrey">
		        <div class="item-standard-name f14 grey">
		        	<span class="left">2015年7月</span>
		        	<span class="right">合计：669.00元</span>
		        </div>
		    </div>
		    <div class="cash-out-order-list-box borderbottomgrey">
				<ul class="cash-out-order-list billing-history-list mleft15">
					<li class="f16">
						<span class="left">物业费</span>
						<span class="right f20">￥<span class="singleOrderPrice">288.50</span></li>
					<li class="property-bill-list borderbottomgrey">
						<div class="grey mtop5">缴费成功</div>
						<div class="grey mtop5">4月25日 18:30</div>
					</li>
				</ul>
		    </div>
		</section>
		<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
		<script>
			$(function(){
				new FastClick(document.body);
			});
		</script>
	</body>
</html>