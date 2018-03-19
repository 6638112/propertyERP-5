<%-- 
    Document   : 物业缴费-账单未出
    Created on : 2016-3-15, 11:49:24
    Author     : Liyl lyl010991@126.com
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>物业缴费</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopping.common.css">
	</head>
	<body class="bggrey">
		<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100 heightp100">
			<form class="inputform">
				<section class="sectionBox">
					<div class="lineheight0"><a href="#"><img src="${pageContext.request.contextPath}/images/banner.png" /></a></div>
			    	<section class="divide-box10 bordertbgrey"></section>
				    <div class="list-box borderbottomgrey">
				        <div class="item-standard-name f14 grey">
				        	<span class="left">兰园商住楼</span>
				        	<span class="right"><a href="${pageContext.request.contextPath}/payProperty/billingHistory.jsp">往期账单</a></span>
				        </div>
				    </div>
				    <div class="cash-out-order-list-box borderbottomgrey">
						<ul class="cash-out-order-list mleft15">
							<li class="f16 icon-property-bill"><span class="left">物业费账单</span><span class="right"></span></li>
							<li class="property-bill-list">
								<div class="f14 displaybox height28">
									<span class="boxflex01 red f20">￥<span class="singleOrderPrice">365.20</span></span>
									<span class="boxflex01 t-right"><a href="${pageContext.request.contextPath}/payProperty/payBill.jsp"><input class="btn-pay-property btn-next" type="button" value="缴费" /></a></span>
								</div>
								<div class="grey mtop5">06月22日-06月30日 可在线缴6月账单</div>
							</li>
						</ul>
				    </div>
			    	<section class="divide-box10 borderbottomgrey"></section>
				    <div class="list-box borderbottomgrey">
				        <div class="item-standard-name f14 grey">
				        	<span class="left">千叶苑</span>
				        	<span class="right"><a href="${pageContext.request.contextPath}/payProperty/billingHistory.jsp">往期账单</a></span>
				        </div>
				    </div>
				    <div class="cash-out-order-list-box borderbottomgrey">
						<ul class="cash-out-order-list mleft15">
							<li class="f16 icon-property-bill"><span class="left">物业费账单</span><span class="right"></span></li>
							<li class="property-bill-list">
								<div class="f14 displaybox height28">
									<span class="boxflex01 f20">￥<span class="singleOrderPrice">288.50</span></span>
									<span class="boxflex01 t-right"><a href="payBill-已过缴费时间.html">查看详情</a></span>
								</div>
								<div class="grey mtop5">已过在线缴费周期，请选择其他缴费方式</div>
							</li>
						</ul>
				    </div>
			    	<section class="divide-box10 borderbottomgrey"></section>
				    <div class="list-box borderbottomgrey">
				        <div class="item-standard-name f14 grey">
				        	<span class="left">金竹苑</span>
				        	<span class="right"><a href="${pageContext.request.contextPath}/payProperty/billingHistory.jsp">往期账单</a></span>
				        </div>
				    </div>
				    <div class="cash-out-order-list-box borderbottomgrey">
						<ul class="cash-out-order-list mleft15">
							<li class="f16 icon-property-bill"><span class="left">物业费账单</span><span class="right"></span></li>
							<li class="property-bill-list">
								<div class="f14 displaybox height28">
									<span class="boxflex01 f16">未出账单</span>
									<span class="boxflex01 t-right"><a href="${pageContext.request.contextPath}/payProperty/payBillExpired.jsp">查看详情</a></span>
								</div>
								<div class="grey mtop5">07月22日-07月30日 可在线缴7月账单</div>
							</li>
						</ul>
				    </div>
				    <section class="divide-box10 pb48 bggrey"></section>
				    <ul class="bottom-menu-box displaybox t-center height48 bordertopgrey">
			        	<li class="p00 boxflex01 t-center blue f16"><a href="${pageContext.request.contextPath}/payProperty/newVillageCooperated.jsp">新增小区</a></li>
				    </ul>
				</section>
			</form>
			<div class="tips-box tips-done bounceInDown animated1s">
				<img class="rotateZoomIn animated1s delay" src="${pageContext.request.contextPath}/images/icon-tips-done.png"/><br>已为您新增<br>小区账单信息
			</div>
		</section>
		<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
		<script>
			$(function(){
				new FastClick(document.body);
				
				setTimeout(function(){
					$('.tips-box').fadeOut();
					$('#wrapBox').removeClass('heightp100');
				},3000);
			})
		</script>
	</body>
</html>