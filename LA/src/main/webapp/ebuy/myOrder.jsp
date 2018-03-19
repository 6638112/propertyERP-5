
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">

<title>我的订单</title>
<link rel="stylesheet" href="../css/common.css?v2">
</head>
<body class="bggrey">
<section id="tabBox" class="tabBox">
    <div class="hd tab-head h70">
        <ul class="order-tab h70">
            <li id="orderTab01" class="wp50 h70 tap-nobg"><a href="javascript:void(0)"><label for="orderTab01" class="order-icon01 tap-nobg on"><div class="item-num bgred" id="waitPayCount" >${fn:length(waitPayResponse.dataValue.list)}</div></label>待付款</a></li>
            <li id="orderTab03" class="wp50 h70 tap-nobg"><a href="javascript:void(0)"><label for="orderTab03" class="order-icon03 tap-nobg"><div class="item-num bgred" id='finishedCount' >${fn:length(finishedResponse.dataValue.list)}</div></label>已付款</a></li>
        </ul>
    </div>
    <div class="bd my-order" id="tabBox-bd" style="margin-top: -1px;">
        <div class="con">
            <div id="tabOne" class="myOrderList dsn">
                	<c:if test="${fn:length(waitPayResponse.dataValue.list)==0 }">
                	<div class="info p00 border-box order-info">
                		<h3 class="prompt-text">无待付款的订单</h3>
                	</div>
                	</c:if>                
               <c:forEach items="${waitPayResponse.dataValue.list}" var="order" varStatus="status">
                <div class="info p00 border-box order-info orderInfo">
	                   <div class="check-pay P010" style="padding-left:10px !important;">订单号：${order.orderNo }</div>
	                   <div class="check-pay borderbottomgrey">
	                   		<span class="left mleft10 grey">实付：<span class="red bold f16">￥${order.totalAmount }</span><br/>
	                   	共 <span class="totalCount"></span>件商品</span>
	                   		<span class="right"><input class="left btn-check btn-post deleteOrder" type="button" name="button" value="删除订单" orderId="${order.orderId}" />
	                   		<a href="../cart/qryOrderDetail.do?orderId=${order.orderId }"><input class="right bgred btn-check" type="button" name="button" value="去付款" /></a></span></div>
	                   		
	                   <c:forEach items="${order.productInfo }" var="productInfo">
		                   <h3 class="item-sort borderbottomgrey">${productInfo.name }</h3>
		                   <c:forEach items="${productInfo.productList}" var="product">
			                   <div class="order-info">
			                        <div class="tbl">
			                            <span class="disblock item-img-small"><img src="${product.picBase }<c:choose><c:when test="${fn:contains(product.picBase, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_110,h_110,limit_0/format,jpg/interlace,1/quality,q_90" /></span>
			                            <span class="item-info tbl-cell w500">
			                                <div class="f14">${product.name }</div>
			                                <div class="message">${product.specification }</div>
			                            </span>
			                            <span class="price-num tbl-cell">
			                                <span class="f14 red">￥${product.priceDiscount}</span><br/><span class="grey">x${product.productQty }</span>
			                            </span>
			                        </div>
			                    </div>
		                   </c:forEach>
		                   <ul class="item-price m010 borderbottomgrey">
		                       <li class="left" id="subCount">共 <span class="subCount">${productInfo.signalMer_totalCount }</span> 件商品</li>
		                       <li class="right mtop4"><span class="mright10">运费：￥${productInfo.deliveryMethod.fee }</span>小计：<span class="red bold f16">￥${productInfo.signalMer_totalMoney }</span></li>
                  			</ul>
	                   </c:forEach>
                </div>
                   </c:forEach>
            </div>
        </div>

        <div class="con">
            <div id="tabThree" class="myOrderList dsn">
    			  	<c:if test="${fn:length(finishedResponse.dataValue.list)==0 }">
                	<div class="info p00 border-box order-info">
                		<h3 class="prompt-text">无已结束的订单</h3>
                	</div>
                	</c:if>
               <c:forEach items="${finishedResponse.dataValue.list}" var="order" varStatus="status">
	               <a href="../order/viewOrderDetail.do?orderId=${order.orderId}&entrance=orderList">
	                	<div class="info p00 bordertop border-box order-info orderInfo">
		                   <div class="check-pay p10"><span class="right grey">查看详情</span>订单号：${order.orderNo }</div>
		                   <div class="check-pay borderbottomgrey">
		                   <span class="left mleft10 grey">实付：<span class="red bold f16">￥${order.totalAmount }</span><br/>
		                   	共 <span class="totalCount"></span>件商品</span><input class="right btn-check" type="button" name="button" value="已付款" /></div>
		                   		
		                   <c:forEach items="${order.productInfo }" var="productInfo">
			                   <h3 class="item-sort borderbottomgrey">${productInfo.name }</h3>
			                   <c:forEach items="${productInfo.productList}" var="product">
				                   
					                   <div class="order-info">
					                        <div class="tbl">
					                            <span class="disblock item-img-small"><img src="${product.picBase }<c:choose><c:when test="${fn:contains(product.picBase, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_110,h_110,limit_0/format,jpg/interlace,1/quality,q_90" /></span>
					                            <span class="item-info tbl-cell w500">
					                                <div class="f14">${product.name }</div>
					                                <div class="message">${product.specification }</div>
					                            </span>
					                            <span class="price-num tbl-cell">
					                                <span class="f14 red">￥${product.priceDiscount}</span><br/><span class="grey">x${product.productQty }</span>
					                            </span>
					                        </div>
					                    </div>
				                    
			                   </c:forEach>
			                   <ul class="item-price m010 borderbottomgrey">
			                       <li class="left" id="subCount">共 <span class="subCount">${productInfo.signalMer_totalCount }</span> 件商品</li>
			                       <li class="right mtop4"><span class="mright10">运费：￥${productInfo.deliveryMethod.fee }</span>小计：<span class="red bold f16">￥${productInfo.signalMer_totalMoney }</span></li>
	                  			</ul>
		                   </c:forEach>
	                	</div>
	                </a>
                </c:forEach>
                   
            </div>
        </div>
    </div>
<section class="divide-box"></section>
</section>

<script src="${resourcePathHttps}/commonjs/zepto.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script> 
<script>
	$(function(){
		new FastClick(document.body);	

		TouchSlide({ slideCell:"#tabBox",
			endFun:function(i){ 
				//高度自适应
				var curHeight = $('.con').eq(i).find('.myOrderList').height();
				$('.tempWrap').height(curHeight);
				//添加动画效果
				if(i>0){
					$('.tempWrap').css('transition','200ms')
				}
			}
		});
		
		//记录上一次选中的tab，加载时自动切换
		$('.order-tab li').click(function(){
			var thisIndex = $(this).index(),
				state = {
					'url':'#orderNum=' + thisIndex
				};
			history.replaceState(state, '', state.url); 
		});
		
		if(location.hash){
			var hashNum = location.hash.substring(10);
			TouchSlide({ 
				slideCell:"#tabBox",
				endFun:function(i){ 
					//高度自适应
					var curHeight = $('.con').eq(i).find('.myOrderList').height();
					$('.tempWrap').height(curHeight);
					//添加动画效果
					if(i>0){
						$('.tempWrap').css('transition','200ms')
					}
				},
				defaultIndex:hashNum
			});
			if(hashNum == 1){
				
			}
		}
		$('#tabOne, #tabThree').removeClass('dsn');
		
	})
</script>
<script src="${resourcePathHttps}/commonjs/TouchSlide.1.1.js"></script>
<script>
	;(function($){
		var $orderTab = $(".order-tab li");
		$orderTab.click(function(){
			if($(this).hasClass('on')){
				$(this).find('label').addClass('on');
				$(this).siblings().find('label').removeClass('on');
			}
		});	
		setInterval(function(){
			$orderTab.each(function(){
				if($(this).hasClass('on')){
					$(this).find('label').addClass('on');
					$(this).siblings().find('label').removeClass('on');
				}
			});	
		},100);
	})(Zepto); 
	
	;(function($){
		$(".orderInfo").each(function(){
			var totalCount = 0;
			$(".subCount", this).each(function(){
				totalCount += $(this).text()*1;
			});
			$(".totalCount", this).text(totalCount);
		});
	})(Zepto); 
	

	$(document).ready(function() {
		$('.deleteOrder').click(function() {
			if (window.confirm('确认要删除订单？')) {
				$this = $(this);
				var orderId = $this.attr('orderId');
				$.post('../order/delOrderById.do', {
					orderId:orderId,
				}, function(data, status) {
					if(data.status=='0000'){//删除成功
						$this.parents('div.info.p00.border-box.orderInfo').hide();
						var waitPayCount = $('#waitPayCount').text();
						$('#waitPayCount').text(waitPayCount*1-1);
						//删除完订单后，插入无订单提示
						var noOrderDom = '<div class="info p00 border-box order-info"><h3 class="prompt-text">无待付款的订单</h3></div>';
						if($('#tabOne div.info.p00.border-box.orderInfo:visible').length == 0){
							$('#tabOne').append(noOrderDom);
						}
					}
				});
			}
		});
	});
</script>
</body>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?37203afff68fe15791b03c221468237c";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</html>