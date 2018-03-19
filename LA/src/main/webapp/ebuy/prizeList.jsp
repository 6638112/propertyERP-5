<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">

<title>消费券列表</title>
<link rel="stylesheet" href="../css/common.css">
</head>

<body>

<header class="fantasia-header">
	<a class="disblock p010 left" href="userCenter_01.do">取消</a>
    <div class="header-title">消费券</div>
    <a class="disblock p010 right" href="#"><span id="confirmCouponSelect" class="red">确定</span></a>
</header>
<section class="divide-box bordertop"></section>
<section class="sectionBox">
    <ul class="item-price">
    	<li class="left">订单金额</li>
        <li class="right"><span class="red bold f16">￥<span class="items-total">${totalAmount}</span></span></li>
    </ul>
    <ul class="item-price">
    	<li class="left">最多可使用订单金额<fmt:formatNumber value="${ext_couponCombiInfo.payPercent}" type="percent" pattern="#0%" /></li>
        <li class="right"><span class="blue bold f16">￥<span >${ext_couponCombiInfo.maxCouponAmount }</span></span></li>
    </ul>
    <ul class="item-price">
    	<li class="left">使用消费券抵扣</li>
        <li class="right"><span class="blue bold f16">￥<span class="prize-total">0.00</span></span></li>
    </ul>
</section> 
<section class="divide-box bordertop borderbottom"></section>
<section class="sectionBox pb10">

<form action="../cart/checkProdctInfo.do" method="post" >
	<c:forEach items="${ext_couponCombiInfo.totalCouponList }" var="coupon">
	   <div class="info pb0 prizeListBox">
	        <div class="disbox01">
	        	<div class="prize-check">
	        		<c:set value="0" var="hasPrint" />
	        		<c:forEach items="${ext_couponCombiInfo.suggestIdList }" var="cpId" varStatus="index">
	        			<c:if test="${cpId==coupon.id}"> 
	        				<c:set value="1" var="hasPrint" />
				        	<input id="${coupon.id }" name="cpId" type="checkbox" checked="checked" value="${coupon.id}:${coupon.hbAmount}"  hbAmout="${coupon.hbAmount }" />
				        	<label for="${coupon.id }" class="item-check-box single-check item-checked "></label >
	        			</c:if>
	        		</c:forEach>
	        		<c:if test="${hasPrint==0 }">
				        	<input id="${coupon.id }" name="cpId" type="checkbox" value="${coupon.id }:${coupon.hbAmount }" hbAmout="${coupon.hbAmount }" />
				        	<label for="${coupon.id }" class="item-check-box single-check "></label >
	        		</c:if>
	        	</div>
	        		
	            <div class="prize-info mtop10">
	                <ul class="disbox01 record-list">
	                    <li class="head-img01"><img src="${coupon.iconUrl }" /></li>
	                    <li class="record-txt"><span class="f18">${coupon.name }</span><br><span class="f12">有效期至：
	                    <c:set var="expiryTimeLong" value="${coupon.expiryTimeLong }" />
	                    <%
	                    	String expiryTimeLong = pageContext.getAttribute("expiryTimeLong").toString();
							Date expiryTime = new java.util.Date(Long.parseLong(expiryTimeLong));
							out.write(new SimpleDateFormat("yyyy-MM-dd").format(expiryTime)); 
						%>
	                    </span></li>
	                    <li class="money bold">￥<span class="moneyNum">${coupon.hbAmount }</span></li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</c:forEach>
</form>
</section> 
<section class="divide-box bordertop pbfooter"></section>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script> 
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
<script src="../js/ebuy.common.js"></script>
</body>
</html>