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
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<title>购物车</title>
<link rel="stylesheet" href="../css/common.css?20160524">
</head>

<body class="bggrey">
<form id="toPay" action="../cart/checkProdctInfo.do?jfqsource=checkPay" method="post">
	<c:forEach items="${jsonResponse.dataValue.list }" var="item" varStatus="status">
		<section class="divide-box borderbottomgrey"></section>
		<section class="sectionBox shoppingPage">
			<h3 class="item-sort">
				<input id="cbox01${item.name}" name="checkbox1" type="checkbox" checked="checked" hidden="hidden" />
				<label for="cbox01${item.name}" class="item-check-box item-checked all-check"></label > ${item.name }<%-- 电商名称 --%> 
				 <a class="disblock p010 right" href="../cart/edit.do"><span  class="red">编辑</span></a>
			</h3>
			<c:forEach items="${item.productList }" var="product" varStatus="subStatus">
				<c:choose>
					<c:when test="${fn:length(item.productList) == (subStatus.index + 1) }">
						<div class="info ptb20">
					</c:when>
					<c:otherwise>
						<div class="info ptb20  border-dashed">
					</c:otherwise>
				</c:choose>
			        <div class="tbl" >
			        	<span class="tbl-cell align-top">
				        	<input id="${product.id}${product.name}" alt="${product.priceDiscount *product.productQty }" class="single-check" name="ptId" type="checkbox" checked="checked" hidden="hidden" value="${product.id }:${product.productQty }:${product.productSpecId }" />
				        	<label for="${product.id}${product.name }" class="item-check-box item-checked single-check"></label >
			        	</span>
			            <span class="tbl-cell item-img-small disblock">
				            <a href="../product/productDetail.do?ptId=${product.id}" >
				            	<img src="${product.picBase }<c:choose><c:when test="${fn:contains(product.picBase, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_110,h_110,limit_0/format,jpg/interlace,1/quality,q_90" />
				            </a>
			            </span>
			            <span class="item-info tbl-cell w500">
			                <a href="../product/productDetail.do?ptId=${product.id}"><div class="f14">${product.name }</div>
			                <div class="message">${product.desc } &nbsp; ${product.specification }</div></a>
			            </span>
			            <span class="price-num tbl-cell">
			                <span class="f14 red">￥${product.priceDiscount }</span><br/><span class="grey">x<span class="shopSingleNum">${product.productQty }</span></span>
			            </span>
			        </div>
			    </div>
			</c:forEach>
		    <ul class="item-price">
		    	<li class="left">共 <span class="ShopTotalNum">${fn:length(item.productList) }</span> 件商品</li>
		        <li class="right">总计：<span class="red bold f16">￥<span class="sub-total">0.00</span></span> </li>
		    </ul>
		   
		</section> 
	</c:forEach> 
</form>

<section class="divide-box bordertop pbfooter"></section>

<section class="sectionBox">
	<c:if test="${not empty coupons.dataValue.list && not empty jsonResponse.dataValue.list }">
	    <section class="sectionBox couponAvailableList">
	    	<a href="../product/index.do">
		        <ul class="user-menu-list m0">
		            <li class="lineheight38">
		                <div class="prize-list displaybox orange">
		                	<span class="boxflex01"><img class="width18 mright10" src="../images/icon_coupon.png" />消费券满赠活动(先到先得)</span>
		                	<span class="t-right">现在去凑单<img src="../images/arrow_yellow.png" /></span>
		                </div>
		            </li>
		            <li>
		                <div class="prize-list mleft38">
			                <c:forEach items="${coupons.dataValue.list }" var="item" varStatus="status">
								<c:if test="${status.index < 3 }">
									<span class="couponAvailable">${item.desc}</span>
								</c:if>
							</c:forEach>
		                </div>
		            </li>
		        </ul>
	    	</a>
	    </section>
    </c:if>
	<div class="exchang-fixed displaybox">
	    <div class="item-all-check bggradient"><input id="allCheck" name="checkbox" checked="checked" type="checkbox" hidden="hidden" /><label for="allCheck" class="item-check-box item-checked"></label >全选</div>
	    <div class="total-price bggradient boxflex01">
	    	<div class="red bold f14 mtop10">应付：￥<span id="totalPrice" class="all-total">0.00</span></div>
			<span class="f12 grey">不含运费</span>
	    </div>
	    <div class="btn-pay"><a id="btnPay" class="bgred" href="javascript:void(0);" onclick="return toPay();">结算(<span class="AllTotalNum">${product.productQty }</span>)</a></div>
	</div>
</section>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script> 
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);

		//设置有消费券情况下，页面的底部距离
		var couponAvailableListHeight = $('.couponAvailableList').height();
		if(couponAvailableListHeight){
			$('.pbfooter').css('padding-bottom', couponAvailableListHeight + 56);
		}
	});
</script>
<script src="${resourcePathHttps}/commonjs/TouchSlide.1.1.js"></script>
<script src="../js/ebuy.common.js"></script>
<script type="text/javascript">
	//提交表单
	function toPay(){
		if($("input[type=checkbox][name='ptId']:checked").length==0){
			alert("请选择要付款的商品");
			return false;
		};
		$("#toPay").submit();
	}
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