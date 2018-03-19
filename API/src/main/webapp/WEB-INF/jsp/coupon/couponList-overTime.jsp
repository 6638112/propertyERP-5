<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>我的消费券</title>
<link rel="stylesheet" href="../css/coupon.common.css">
</head>

<body class="bggrey">
<section class="sectionBox prize-list">
    <div class="get-record">
        <c:forEach items="${couponList}" var="coupon">
            <a class="record-list-bg out-of-date" href="javascript:void(0)" target="_self">
                <ul class="displaybox record-list p10 mleft5 noborderleft">
                    <c:if test="${coupon.couponType == 1}">
                        <li class="new-ticket-num grey">
                       		 ￥<span class="f30">${coupon.discountMoney}</span>
		                	<c:choose>
							     <c:when test="${coupon.leastSpendUse == 0 }">  
	    							<div class="f12 mtop10">消费即可用</div>
							     </c:when>
							     <c:otherwise> 
									<div class="f12 mtop10">满${coupon.leastSpendUse }元可用</div>
							     </c:otherwise>
							</c:choose>
                        </li>
                    </c:if>
                    <c:if test="${coupon.couponType == 2}">
                        <li class="new-ticket-num grey"><span class="f30">${coupon.discountValue} 折</span></li>
                    </c:if>
                    <li class="record-txt boxflex01 borderleft">
                        <span class="f16">${coupon.couponName}</span>
                        <div class="f12 grey">${coupon.couponDesc}</div>
                        <div class="f12 grey mtop5">有效期至 ${coupon.useEndDate}</div>
                    </li>
                </ul>
            </a>
        </c:forEach>
    </div>
</section>
</body>
</html>