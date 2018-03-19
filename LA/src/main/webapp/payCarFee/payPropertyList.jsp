<%-- 
    Document   : 物业缴费
    Created on : 2016-3-16, 13:20:24
    Author     : Liyl lyl010991@126.com
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
		<form class="inputform">
			<section class="sectionBox">
				<div class="lineheight0"><a href="#"><img src="${pageContext.request.contextPath}/images/banner.png" /></a></div>
		    	<section class="divide-box10 bordertbgrey"></section>
			    <div class="list-box borderbottomgrey">
			        <div class="item-standard-name f14 grey">
			        	<span class="left">兰园商住楼</span>
			        	<span class="right"><a href="${pageContext.request.contextPath}/payCarFee/billingHistory.jsp">往期账单</a></span>
			        </div>
			    </div>
			    <div class="cash-out-order-list-box borderbottomgrey">
					<ul class="cash-out-order-list mleft15">
						<li class="f16 icon-portcar-bill"><span class="left">停车费账单</span><span class="right"></span></li>
						<li class="property-bill-list borderbottomgrey">
							<div class="f14 displaybox height28">
								<span class="boxflex01 red f20">￥<span class="singleOrderPrice">200.00</span></span>
								<span class="boxflex01 t-right"><a href="parkingFee.html"><input class="btn-pay-property btn-next" type="button" value="续费" /></a></span>
							</div>
							<div class="grey mtop5">粤B34567  月卡<span class="red">3天</span>后到期</div>
						</li>
						<li class="property-bill-list mtop10">
							<div class="f14 displaybox height28">
								<span class="boxflex01 f20">￥<span class="singleOrderPrice">2400.00</span></span>
								<span class="boxflex01 t-right"><a href="${pageContext.request.contextPath}/payCarFee/parkingFee.jsp">查看详情</a></span>
							</div>
							<div class="grey mtop5">粤B07HB0  年卡<span class="red">238天</span>后到期</div>
						</li>
					</ul>
			    </div>
			    <section class="divide-box10 pb48 bggrey"></section>
			    <ul class="bottom-menu-box displaybox t-center height48 bordertopgrey">
			        <li class="p00 boxflex01"><a class="pleft25 f16 icon-car" href="${pageContext.request.contextPath}/payCarFee/newCar.jsp">新增车辆</a></li>
			    </ul>
			</section>
		</form>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
		<script>
			$(function(){
				new FastClick(document.body);
			});
		</script>
	</body>
</html>