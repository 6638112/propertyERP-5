<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>临时车缴费详情</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopping.common.css">
	</head>
	<body class="bggrey">
		<c:choose>
			<c:when test="${feeDetail.status eq '0000'}">
				<section class="sectionBox bordertopgrey">
					<ul class="p20 t-center">
				    	<li class="mtop10"><img class="wp15 rotateZoomIn animated1s" src="${pageContext.request.contextPath}/images/icon-gathering-done.png" /></li>
				    	<li class="mtop10"><span class="f20 red">${feeDetail.dataValue.payStatus}</span></li>
				    	<li class="mtop10 f20"><span class="f30"><fmt:formatNumber value="${feeDetail.dataValue.payMoney}" type="currency" pattern="0.00" maxFractionDigits="2"/></span> 元</li>
				    </ul>
				</section>
				<section class="divide-box bgwhite user-info-title recommend-details-info borderbottomgrey">
					<span class="disblock f14 black bordertopgrey">
						<c:if test="${(not empty feeDetail.dataValue.couponAmount) and (feeDetail.dataValue.couponAmount>0)}">
							<span class="list-name red">优惠明细</span><span class="right red">已随机立减￥<span><fmt:formatNumber value="${feeDetail.dataValue.couponAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/></span></span><br/>
						</c:if>
				        <span class="list-name">支付方式</span><span class="right">${feeDetail.dataValue.payMethod}</span><br/>
				        <span class="list-name">交易时间</span><span class="right">${feeDetail.dataValue.payTime}</span><br/>
				        <span class="list-name">交易单号</span><span class="right">${feeDetail.dataValue.orderNo}</span>
				    </span>
				</section>
				<!-- 广告 -->
				<%-- <c:if test="${feeDetail.dataValue.isAd}"> --%>
					<section id="slideBox" class="slideBox">
					    <div class="bd noborder lineheight0">
					    	<%-- <a class="pic" href="${feeDetail.dataValue.adUrl}" target="_blank"><img src="${feeDetail.dataValue.adImageUrl}" /></a> --%>
					    	<a class="pic" href="${adUrl}" target="_blank"><img src="${pageContext.request.contextPath}/images/wash_car.png" /></a>
					        <!-- <ul>
					            <li>
					                <a class="pic" href="javascript:void(0)" target="_blank"><img src="images/chexianAD.jpg" /></a>
					            </li>
					            <li>
					                <a class="pic" href="javascript:void(0)" target="_blank"><img src="images/chexianAD.jpg" /></a>
					            </li>
					        </ul> -->
					    </div>
					    <!-- <div class="hd">
					        <ul></ul>
					    </div> -->
					</section>
				<%-- </c:if> --%>
			</c:when>
			<c:otherwise>
				${feeDetail.message}
			</c:otherwise>
		</c:choose>
		<!--
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
		<script src="${pageContext.request.contextPath}/js/TouchSlide.1.1.js"></script>
		<script>
		    TouchSlide({ 
		        slideCell:"#slideBox",
		        titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
		        mainCell:".bd ul", 
		        effect:"left", 
		        autoPage:true,//自动分页
		        autoPlay:true //自动播放
		    });
		    
		    $(function(){
		    	var hdWidth = $('.hd').width();
		    	$('.hd').css('margin-left',-hdWidth/2);
		    })
		</script>  -->
	</body>
</html>