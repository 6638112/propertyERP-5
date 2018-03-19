<%-- 
    Document   : 往期账单
    Created on : 2016-3-16, 13:20:24
    Author     : Liyl lyl010991@126.com
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		<c:forEach items="${carFees}" var="carFeeList">
			<section class="sectionBox">
			    <div class="list-box bggrey bordertbgrey">
			        <div class="item-standard-name f14 grey">
			        	<span class="left">${carFeeList.caption}</span>
			        	<span class="right">合计：${carFeeList.total}元</span>
			        </div>
			    </div>
			    <c:forEach items="${carFeeList.list}" var="carFee" varStatus="k">
				    <div class="cash-out-order-list-box borderbottomgrey">
						<ul class="cash-out-order-list billing-history-list mleft15">
							<li class="f16">
								<span class="left">${carFee.carNum} 停车费</span>
								<span class="right f20">￥<span class="singleOrderPrice">${carFee.fee}</span></li>
							<li class="property-bill-list">
								<div class="grey mtop5">${carFee.resultDes}</div>
								<div class="grey mtop5">${carFee.paytime}</div>
							</li>
						</ul>
				    </div>
			    </c:forEach>
			</section>
		</c:forEach>
		<c:if test="${fn:length(carFees)==0}">
			<section class="sectionBox" style="height:100%;text-align: center;padding-top:50px;font-size: 18px;">
				暂无记录！
			</section>
		</c:if>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
		<script>
			$(function(){
				new FastClick(document.body);
			});
		</script>
	</body>
</html>