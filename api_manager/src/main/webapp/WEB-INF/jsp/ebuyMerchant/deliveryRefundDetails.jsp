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

<title>退款详情</title>
<link rel="stylesheet" href="css/merchant/shopping.common.css">
</head>

<body class="bggrey">	
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock mleft15 left white w80" href="javascript:void(0)" onclick="history.back()"><img class="back-arrow left" src="images/back-arrow.png" /> <span class="mleft10">返回</span></a>
    <div class="header-title">退款详情</div>
</header>
<section class="divide-box"></section>
<section class="sectionBox bordertopgrey">
	<ul class="p10 f14">
    	<li>退款金额</li>
    	<li class="mtb20 f30 red">￥<span>${detail.deliveryOrderRefundFee}</span></li>
    	<li class="mtop10">申请时间：${detail.refundApplyTime}</li>
    </ul>
</section>
<section class="divide-box bggrey02 ptb10 bordertbgrey"><div class="mleft10 grey">退款原因</div></section>
<section class="sectionBox">
	<ul class="f14">
	    <li class="ptb10 mleft10">${detail.refundReason}</li>
	</ul>
</section>
<section class="divide-box bordertbgrey"></section>
<section class="sectionBox payList borderbottomgrey">
   <div class="pay-list-info mleft10">
       <c:set var="isFirst" value="1"/>
       <c:forEach items="${detail.orderProductList}" var="product" varStatus="index">

           <c:if test="${product.hasProductRefund}">
               <div class="info ptb10 <c:if test="${isFirst != 1}">bordertopgrey</c:if>">
                   <c:set var="isFirst" scope="session" value="0"/>
                   <div class="displaybox">
                       <div class="item-img-small01"><img src="${product.picBase}" /></div>
                       <div class="item-info boxflex01">
                           <div class="f14">${product.productName}</div>
                       </div>
                       <div class="price-num w80">
                           <span class="f14">￥${product.price}</span><br/>
                           <span class="grey">x${product.qty}</span><br/>
                       </div>
                   </div>
               </div>
           </c:if>

       </c:forEach>
    </div>
</section>

</body>
</html>