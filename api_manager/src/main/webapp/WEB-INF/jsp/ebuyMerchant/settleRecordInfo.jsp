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

<title>结算详情</title>
<link rel="stylesheet" href="css/merchant/shopping.common.css">
</head>

<body class="bggrey">	
<header class="sectionBox fantasia-header order-top-bg">
    <div class="header-title">提款详情</div>
	<a class="disblock p010 right white" href="ebuyMerchant/settleCenter.html">完成</a>
</header>
<section class="sectionBox bordertopgrey">
	<ul class="p20 t-center">
    	<li class="mtop10">
    		<img class="wp15 rotateZoomIn animated1s" src="images/icon-gathering-done.png" />
    	</li>
    	<li class="mtop10"><span class="f20 red">申请成功</span></li>
    	<li class="mtop10 f20"><fmt:formatNumber value="${revenueApply.totalAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/> 元</li>
    </ul>
</section>
<section class="divide-box bgwhite user-info-title borderbottomgrey pb0">
	<span class="disblock bordertopgrey cash-out-info-text f12 black">
    	<span class="list-name">订单合计</span><span class="right">${applyTotal}笔</span><br/>
        <span class="list-name">费用合计</span><span class="right">￥<fmt:formatNumber value="${revenueApply.totalAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/></span><br/>
        <span class="list-name">结算时间</span><span class="right">${revenueApply.applyTime}</span><br/>
        <span class="list-name">收款银行</span><span class="right">${revenueApply.bBankName}</span><br/>
        <span class="list-name">收款帐号</span><span class="right">${cardNo}</span><br/>
        <span class="list-name">结算状态</span>
		<span class="right">
			<c:if test="${revenueApply.auditStatus == 1}">结算中</c:if>
			<c:if test="${revenueApply.auditStatus == 2}">申请失败</c:if>
			<c:if test="${revenueApply.auditStatus == 3}">申请通过</c:if>
		</span>
    </span>
</section>
</body>
</html>