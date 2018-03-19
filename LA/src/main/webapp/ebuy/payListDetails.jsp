<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
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

<title>订单详情</title>
<link rel="stylesheet" href="../css/common.css?2017110303">
</head>

<body class=" <c:if test="${fn:length(list) gt 0}">heightp100 </c:if>bggrey">
<div class="check-pay-box heightp100">
	<input value="${orderDetail.orderId}" type="text" name="orderId" hidden="hidden">
	<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100 heightp100 <%-- <c:if test="${moneySend > 0}">heightp100</c:if> --%>">
		<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
			<section class="sectionBox addressInfo paid-info bordertopgrey">
				<ul class="p2010 mtop10 t-center">
			    	<li class="mtop5"><img class="paid-icon" src="../images/pay-success.png" />
			    		<c:if test="${orderDetail.payStatus eq 1 }"><span class="f18 mright10">未支付</span></c:if>
			    		<c:if test="${orderDetail.payStatus eq 2 }"><span class="f18 mright10">交易成功</span></c:if>
			    		<c:if test="${orderDetail.payStatus eq 3 }"><span class="f18 mright10">交易失败</span></c:if>
			    	</li>
			    	<li class="paid-num">￥${orderDetail.totalAmount}</li>
			    </ul>
			</section>
			<section class="user-center sectionBox bordertbgrey pb10">
				<ul class="user-info heightauto">
			    	<li class="user-img">
			    	<span class="user-name f16"><span class="grey">收货人：</span>${orderDetail.deliveryAddress.userName} 
				    	<span class="mleft10">${orderDetail.deliveryAddress.userPhone} </span><br>
				    	<span class="disblock f14 mtop5"><span class="grey">收货地址：</span>${orderDetail.deliveryAddress.addressTotal}</span>
			    	</span>
			    	</li>
			    </ul>
			</section>
			<c:if test="${orderDetail.hasFilmTicket eq 1}">
				<section class="divide-box bordertbgrey"></section>
			    <div class="list-box bgwhite">
			        <a class="displaybox" href="${orderDetail.filmPayUrl}">
			            <div class="item-standard-name height36 f16 boxflex01">在线订座<span class="bgred icon-new">new</span></div>
			            <div class="boxflex01 box-arrow t-right grey"><span style="color:red;">订座前请先复制兑换码</span></div>
			        </a>
			    </div>
		    </c:if>
			
			<section class="divide-box borderbottomgrey"></section>
			<c:forEach items="${orderDetail.productInfo}" var="productSupply" varStatus="status">
			  <section class="sectionBox payList">
			    <div class="pay-list-info mleft10">
			       <h3 class="item-sort ptb10">${productSupply.name}<c:if test="${productSupply.isDelivOrderFullyRefund == true}"><span class="red mleft10 normal f12">已退款</span></c:if></h3>
			       <c:forEach items="${productSupply.productList}" var="product">
				       <div class="info ptb10 borderbottomgrey">
				            <div class="tbl">
				                <span class="item-img-small01 inline-table"><img src="${product.picBase}" /></span>
				                <span class="item-info tbl-cell">
				                    <div class="f14">${product.name}</div>
				                    <c:choose>
				                    	<c:when test="${fn:length(product.filmTicketList) gt 0}">
				                    		<div class="message">有效期至：${product.filmTicketList[0].expireTm}</div>
				                    	</c:when>
				                    	<c:otherwise>
				                    		<div class="message">${product.specification}</div>
				                    	</c:otherwise>
				                    </c:choose>
				                </span>
				                <span class="price-num tbl-cell">
				                	<span class="f14">￥${product.priceDiscount} </span><br/><span class="grey">x${product.productQty}</span><c:if test="${productSupply.isDelivOrderFullyRefund == false && product.isProductRefund == true}"><br/><span class="red">已退款</span></c:if>
				                </span>
				            </div>
				        </div>
				        <c:if test="${(orderDetail.payStatus eq 2) and (fn:length(product.filmTicketList) gt 0)}">
				        	<div class="info ptb10 borderbottomgrey">
					            <div class="tbl">
					                <span class="item-img-small01 inline-table"></span>
					                <span class="item-info tbl-cell">
					                    <div class="f14">兑换码</div>
					                    <div class="message"></div>
					                </span>
					                <ul class="price-num tbl-cell f14 lineheight24">
					                	<c:forEach items="${product.filmTicketList}" var="filmTicket">
					                		<li>${filmTicket.identifyCode}</li>
										</c:forEach>
					                </ul>
					            </div>
					        </div>
				        </c:if>
			       </c:forEach>
			        <!-- 有运费 -->
					<c:if test="${productSupply.isSelfGet == 0 }">	        
				        <ul class="item-price deliver-box displaybox noborder">
				            <li class="left grey displaybox">
								<div class="boxflex01">${productSupply.deliveryMethod.desc}</div>
							</li>
				            <li class="boxflex01 t-right">运费：￥<span class="item-deliver-fee">${productSupply.deliveryMethod.fee }</span></li>
				        </ul>
			        </c:if>
			        <!-- 到店自提 -->
			        <c:if test="${productSupply.isSelfGet == 1 }">	
				        <ul class="item-price deliver-box displaybox noborder">
				            <li class="left grey displaybox">
								<div class="boxflex01">到店自提</div>
							</li>
				            <li class="boxflex01 t-right">运费：￥<span class="item-deliver-fee">0.00</span></li>
				        </ul>
			        </c:if>
			        <ul class="item-price">
			            <li class="left">共 ${productSupply.signalMer_totalCount} 件商品</li>
			            <li class="right">应付：
				            <span class="red bold f16">￥
					            <c:if test="${productSupply.isSelfGet == 0 }">
					            	<span>${productSupply.signalMer_totalMoney + productSupply.deliveryMethod.fee }</span>
				            	</c:if>
					            <c:if test="${productSupply.isSelfGet == 1 }">
					            	<span>${productSupply.signalMer_totalMoney }</span>
				            	</c:if>
				             </span>
			             </li>
			        </ul>
			    </div>
			  </section> 
		      <c:if test="${(fn:length(orderDetail.productInfo))-1 gt status.index}">
			    <section class="divide-box borderbottomgrey"></section>
		      </c:if> 
			</c:forEach>
			
			<section class="divide-box user-info-title">
				<span class="disblock p010 f14 black lineheight22">
			    	<span class="list-name">商品合计</span>￥${orderDetail.ext_totalMoney}<br/>
			        <span class="list-name">运费</span>￥${orderDetail.deliveryFee}<br/>
			        <c:if test="${orderDetail.totalRefundFee > 0}">
			        	<span class="list-name red">退款金额</span><span class="red">￥${orderDetail.totalRefundFee}</span><br/>
			        </c:if>
			        <span class="list-name">已优惠</span>￥${orderDetail.totalCouponAmount}<br/>
			        <span class="list-name">支付方式</span>
			                <c:if test="${orderDetail.payMethod eq 1}">微信支付</c:if>
			                <c:if test="${orderDetail.payMethod eq 2}">支付宝钱包支付</c:if>
			                <c:if test="${orderDetail.payMethod eq 3}">银联支付</c:if>
			                <c:if test="${orderDetail.payMethod eq 4}">纯粮票支付</c:if>
			                <c:if test="${orderDetail.payMethod eq 5}">纯积分支付</c:if>
			                <c:if test="${orderDetail.payMethod eq 6}">微信轻应用支付</c:if>
			                <c:if test="${orderDetail.payMethod eq 7}">纯消费券支付</c:if><br/>
			        <span class="list-name">成交时间</span>${orderDetail.payTime}<br/>
			        <span class="list-name">订单号</span>${orderDetail.orderNo}
			    </span>
			</section>
			<section class="divide-box"></section>
		</section>
	</section>
</div>

<c:if test="${hasShareCoupon}">
	<section class="sectionBox wrap-bg pop-box02">
		<div class="sectionBox wrap-bg pop-bg01"></div>
		<div >
			<div class="t-center white">
				<div class="mtop3" style="width: 57px;height: 138.5px; margin-top: 2px;margin-left: 80%"><img src="../images/shareguide-arrow.png"/></div>
				<div class="mtop15">
					<div class="f24 lineheight110">分享至好友或者朋友圈</div>
					<div class="f30 lineheight110 mtop5">立即获得优惠券</div>
					<div class="f20 lineheight110 mtop30">- 点击右上角分享领取 -</div>
				</div>
			</div>
			<div class="displaybox mtop15">
				<div class="boxflex01 ptb5 t-center btn-submit share-tip-close f18 m060 black" style="background-color: #d6d6d6;">知道了</div>
			</div>
		</div>
	</section>
</c:if>

<c:if test="${!hasShareCoupon && fn:length(list) gt 0}">
	<section class="sectionBox wrap-bg pop-box02">
		<div class="sectionBox wrap-bg pop-bg"></div>
		<div class="tips-box pb20 tips-info" style="width: 80%; margin: -210px 0 0 10%;">
			<div class="t-center color-gold">
				<div class="mtop3"><img src="../images/voucher-top.png"/></div>
				<section class="mtop10 p010">
					<c:forEach items="${list}" var="coupon" varStatus="status">
						<c:if test="${status.index < 2 }">
							<div class="displaybox pos-relative prizeListBox">
								<div class="record-list-bg bgwhite bordered boxflex01">
									<ul class="displaybox p510 m0">
										<li class="new-ticket-num voucher-pop-num">
											￥<span class="f30">${coupon.discountMoney}</span>
										</li>
										<li class="lineheight140 black boxflex01 borderleft mtb5 pleft10 t-left">
											<span class="f16">${coupon.couponName}</span>
											<div class="f12 grey">
												<c:if test="${coupon.leastSpendUse == 0}">
													任意金额可用
												</c:if>
												<c:if test="${coupon.leastSpendUse != 0}">
													满${coupon.leastSpendUse}元可用
												</c:if>
											</div>
											<div class="f12 grey">有效期至 ${coupon.useEndDate}</div>
										</li>
									</ul>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</section>
				<div class="f14 mtop5 black">本次共获得${fn:length(list) }张消费券</div>
			</div>
			<ul class="displaybox mtop15">
				<li class="boxflex01 ptb5 t-center bgred btn-submit f16 m020"><a id="jumpToVoucherList" class="disblock white" href="../coupon/couponList.do">查看全部</a></li>
			</ul>
			<div class="tip-close-text f16">轻触关闭</div>
		</div>
	</section>
</c:if>

<section class="sectionBox share_coupon wrap-bg pop-box02 dsn">
	<div class="sectionBox wrap-bg pop-bg"></div>
	<div class="tips-box pb20 tips-info" style="width: 80%; margin: -210px 0 0 10%;">
		<div class="t-center color-gold">
			<div class="mtop3"><img src="../images/share-header.png"/></div>
			<section class="mtop10 p010">
				<div class="displaybox pos-relative prizeListBox">
					<div class="record-list-bg bgwhite bordered boxflex01">

					</div>
				</div>
			</section>
			<div class="f14 mtop5 black coupon_count"></div>
		</div>
		<ul class="displaybox mtop15">
			<li class="boxflex01 ptb5 t-center bgred btn-submit f16 m020"><a class="disblock white" href="../coupon/couponList.do">查看全部</a></li>
		</ul>
		<div class="tip-close-text f16">轻触关闭</div>
	</div>
</section>

<ul class="displaybox coupon_info p510 m0 dsn">
	<li class="new-ticket-num voucher-pop-num">
		￥<span class="f30 coupon_discountmoney"></span>
	</li>
	<li class="lineheight140 black boxflex01 borderleft mtb5 pleft10 t-left">
		<span class="f16 coupon_name"></span>
		<div class="f12 grey coupon_desc"></div>
		<div class="f12 grey coupon_enddate"></div>
	</li>
</ul>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="../js/paylistdetail.js?20171113"></script>
<script>
$(function(){
	new FastClick(document.body);
	
	history.replaceState({}, '我的订单', '../order/qryOrderList.do');
	
	$('.pop-bg, .share-tip-close,.tip-close-text').click(function(){
		$('#wrapBox').removeClass('heightp100');
		$('.pop-box02').addClass('dsn');

//		getShareCoupons();
	});

	var hasShareCoupon = ${hasShareCoupon};
	if(hasShareCoupon){
        setShareInfo();
	}
})

</script>

</body>
</html>