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

<title>订单详情</title>
<link rel="stylesheet" href="css/merchant/shopping.common.css?rev=d1bb698a9d4671f8f27133ee6efc69cb">
</head>

<body>	
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock mleft15 left white w80" href="javascript:void(0)" onclick="history.back()"><img class="back-arrow left" src="images/back-arrow.png" /> <span class="mleft10">返回</span></a>
    <div class="header-title">订单详情</div>
</header>
<section class="sectionBox bordertopgrey">
	<ul class="p20 t-center">
    	<li class="mtop10"><img class="wp15 rotateZoomIn animated1s" src="images/icon-gathering-done.png" /></li>
    	<li class="mtop10"><span class="f20 red">已收款</span></li>
    	<li class="mtop10 f30">￥<span>${detail.totalDeliveryOrderFee - detail.deliveryOrderRefundFee}</span></li>
    </ul>
</section>
<section class="sectionBox">
	<ul class="ptb10 m010 overhidden displaybox f14 bordertopgrey">
    	<li class="grey"><span>服务地址</span><br><span>收货人</span><br><span>联系方式</span></li>
    	<li class="boxflex01 t-right"><span>${detail.delivAddress}</span><br><span>${detail.delivPeopleName}</span><br><span>${detail.delivPhone}</span></li>
 </ul>
</section>
<section class="divide-box bordertbgrey"></section>
<section class="sectionBox payList">
   <div class="pay-list-info mleft10">
       <c:forEach items="${detail.orderProductList}" var="product" varStatus="index">
           <c:if test="${index.count == 1}">
               <div class="info ptb10">
                   <div class="displaybox">
                       <div class="item-img-small01"><img src="${product.picBase}" /></div>
                       <div class="item-info boxflex01">
                           <div class="f14">${product.productName}</div>
                       </div>
                       <div class="price-num w80">
                           <span class="f14">￥${product.price}</span><br/>
                           <span class="grey">x${product.qty}</span><br/>
                           <c:if test="${product.hasProductRefund}">
                               <span class="red">已退款</span>
                           </c:if>
                       </div>
                   </div>
               </div>
           </c:if>
           <c:if test="${index.count != 1}">
               <div class="info ptb10 bordertopgrey">
                   <div class="displaybox">
                       <div class="item-img-small01"><img src="${product.picBase}" /></div>
                       <div class="item-info boxflex01">
                           <div class="f14">${product.productName}</div>
                       </div>
                       <div class="price-num w80">
                           <span class="f14">￥${product.price}</span><br/>
                           <span class="grey">x${product.qty}</span><br/>
                           <c:if test="${product.hasProductRefund}">
                               <span class="red">已退款</span>
                           </c:if>
                       </div>
                   </div>
               </div>
           </c:if>
       </c:forEach>
    </div>
</section>
<section class="divide-box bordertbgrey"></section>
<section class="divide-box bgwhite user-info-title">
	<span class="disblock p010 f14 black">
    	<span class="list-name">商品合计</span><span class="right">￥${detail.totalProductFee}</span><br/>
        <span class="list-name">商品总数</span><span class="right">${detail.totalProductQty}件</span><br/>
        <span class="list-name">运费</span><span class="right">￥${detail.deliveryRealFee}</span><br/>
        <c:if test="${detail.refundType != 0}">
        <span class="list-name red">退款金额</span><span class="right red">￥${detail.deliveryOrderRefundFee}</span><br/>
        </c:if>
        <span class="list-name">已优惠</span><span class="right">￥${detail.totalCoupon}</span><br/>
        <span class="list-name">支付方式</span><span class="right">
        <c:choose>
            <c:when test="${detail.payMethod==1}">微信支付</c:when>
            <c:when test="${detail.payMethod==2}">支付宝</c:when>
            <c:when test="${detail.payMethod==3}">银联支付</c:when>
            <c:when test="${detail.payMethod==4}">纯粮票支付</c:when>
            <c:when test="${detail.payMethod==5}">纯积分支付</c:when>
            <c:when test="${detail.payMethod==6}">微信轻应用支付</c:when>
            <c:when test="${detail.payMethod==7}">纯优惠券支付</c:when>
            <c:when test="${detail.payMethod==8}">纯折扣支付</c:when>
            <c:when test="${detail.payMethod==9}">双乾支付</c:when>
        </c:choose>
        </span><br/>
        <span class="list-name">成交时间</span><span class="right">${detail.payTime}</span><br/>
        <span class="list-name">订单号</span><span class="right">${detail.orderNo}</span>
    </span>
</section>
<c:if test="${detail.refundType != 0}">
    <section class="divide-box bggrey02 ptb10 bordertbgrey t-center" style="line-height: 15px;"><a class="blue f14" href="ebuyMerchant/settleCenter/orderDetail.html?page=refund&deliveryOrderId=${detail.id}">查看退款详情</a></section>
</c:if>

</body>
</html>