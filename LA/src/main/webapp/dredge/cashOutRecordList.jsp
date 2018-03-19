<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>提款记录</title>
<link rel="stylesheet" href="css/recommendaward.css">
</head>

<body class="bggrey">

<section class="sectionBox">
	<div class="cash-out-order-list-box">
		<c:forEach items="${dataValue.list }" var="item">
			<section class="divide-box bordertbgrey"></section>
		    <a href="viewWithdrawDetail.do?revenueApplyId=${item.id }">
				<ul class="cash-out-order-list pleft15 f14">
					<li class="mtop5">
						<span class="left f16">${item.applyStatus }</span>
						<span class="right red f20">￥<span class="singleOrderPrice">${item.applyAmount }</span></span>
					</li>
					<li class="grey mtop5">
						<span class="left">提款状态：<span class="accounting-status <c:if test="${item.tkStatus=='结算中'}">red</c:if>">${item.tkStatus }</span></span>
						<span class="right">共${item.billCount }笔订单</span>
					</li>
					<li class="grey">提款时间：${item.tkTime }</li>
				</ul>
			</a>
		</c:forEach>
    </div>
</section>
		<c:if test="${fn:length(dataValue.list)==0}">
			<section class="sectionBox bggrey big-text-tips-box">
				<div class="big-text-tips grey t-center">暂无提款记录</div>
			</section>
		</c:if>
</body>
</html>