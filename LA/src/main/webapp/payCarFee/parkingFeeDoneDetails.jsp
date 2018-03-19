<%-- 
    Document   : 缴费成功
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
		<title>缴费详情</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopping.common.css">
	</head>
	<body class="bggrey">
		<section class="sectionBox bordertopgrey">
			<ul class="p20 t-center">
		    	<li class="mtop10"><img class="wp15 rotateZoomIn animated1s" src="${pageContext.request.contextPath}/images/icon-gathering-done.png" /></li>
		    	<li class="mtop10"><span class="f20 red">${payCarFeeResult.resultDes}</span></li>
		    	<li class="mtop10 f20"><span class="f30">${payCarFeeResult.fee}</span> 元</li>
		    </ul>
		</section>
		<section class="divide-box bgwhite user-info-title recommend-details-info borderbottomgrey">
			<span class="disblock f14 black bordertopgrey">
		    	<span class="list-name">停车费</span><span class="right">${payCarFeeResult.pay_fee}</span><br/>
		    	<span class="list-name">续费时长</span><span class="right">${payCarFeeResult.pay_num}</span><br/>
		        <span class="list-name">支付方式</span><span class="right">${payCarFeeResult.pay_method}</span><br/>
		        <span class="list-name">交易时间</span><span class="right">${payCarFeeResult.pay_time}</span><br/>
		        <span class="list-name">交易单号</span><span class="right">￥{payCarFeeResult.order_no}</span>
		    </span>
			<span class="disblock f14 black bordertopgrey">
		        <span class="list-name">车牌号</span><span class="right">${payCarFeeResult.car_num}</span><br/>
		        <span class="list-name">登记信息</span><span class="right">${payCarFeeResult.GroupBuildName}${cardname}</span><br/>
		        <span class="list-name">车主姓名</span><span class="right">${realname}</span>
		    </span>
		</section>
	</body>
</html>