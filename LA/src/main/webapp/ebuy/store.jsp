<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="../error/404.jsp" %>
<%@page import="java.util.Calendar"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	String jfqsource = request.getParameter("jfqsource");//用request得到 
	String storeId = request.getParameter("storeId");
	String proCarNum = request.getParameter("proCarNum");
	String storetest = request.getParameter("storetest");
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cleartype" content="on">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">
<title></title>
<script>
	//微信端-判断是否已关注公众号20170802
	if(location.search.indexOf('jfqapp') === -1 && '${subscribe}' === '0'){
		location.href = 'https://resource.jiefangqu.com/docs/followjfq/index.htm';
	}
</script>
<link type="text/css" rel="stylesheet" href="${resourcePathHttps}/commoncss/swiper-3.4.2.min.css">
<link type="text/css" rel="stylesheet" href="../css/common.css?v20170808c">
</head>
<body class="opacity0">
<!-- 悬浮购物车 -->
<div class="float-menu" data-id="${storeId}">
	<div class="coupon-list-icon borderrightwhite dsn">
		<a href="../coupon/couponList.do">
			<img class="w22" src="../images/icon_coupon_white.png" />
		</a>
	</div>
	<div class="shopping-cart-index dsn">
		<a id="shoppingCartBtn" href="../cart/qryBuyCar.do">
			<!-- 轻应用进入 -->
			<c:if test="${param.proCarNum == undefined }">
				<div id="cartNum" class="item-num bgwhite dsn"></div>
			</c:if>
			<!-- app进入 -->
			<c:if test="${param.proCarNum != undefined }">
				<div id="cartNum" class="item-num bgwhite <c:if test='${param.proCarNum == 0 }'>dsn</c:if> ">${param.proCarNum}</div>
			</c:if>
			<img src="../images/shoppingcart-white-new.png" />
		</a>
	</div>
</div>
<div class="main-part01 pos-relative"> 
	
	<section id="storeInfo" class="sectionBox pt10 displaybox opacity0 <c:if test='${param.jfqsource == "jfqapp" }'>dsn</c:if>">
		<div class="boxflex01 mleft10">
			<img class="w13 aligntop" src="../images/icon-person.png">
			<span id="viewCount"></span>
		</div>
		<div class="t-right displaybox">
			<div class="borderright">
				<a id="storeSearch" class="p10" href="storeSearch.html?storeId=${param.storeId}&jfqsource=jfqstore">
					<img class="w13 aligntop" src="../images/search-icon.png">
				</a>
			</div>
			<a id="storePhoneLink" class="p10" href="tel:">
				<img class="w13 aligntop" src="../images/icon-phone.png">
				<!-- <span id="storePhone"></span> -->
			</a>
		</div>
	</section>
	
	<!-- 其他楼下店图片 -->
	<c:if test="${param.storeId != '110214' && param.storeId != '10012278' && param.storeId != '110211' && param.storetest == undefined }">
		<section id="storeImgBox" class="sectionBox borderbottomgrey">
			<div class="swiper-container m10 opacity0">
				<ul class="swiper-wrapper">

				</ul>
			</div>
		</section>
	</c:if>
	<!-- 解放区体验店banner -->
	<c:if test="${param.storeId == '110214' || param.storeId == '10012278' || param.storeId == '110211' || param.storetest == 'storetest' }">
		<section id="slideBox" class="slideBox store-banner box-shadow mtop10 opacity0">
		    <div class="bd noborder">
		        <ul id="storeBanner">
		            <li><a href="${resourcePathHttps}/supplierInfo/supplier03.htm"><img class="radius4" src="../images/supplier_c_01.jpg" /></a></li>
		            <li><a href="${resourcePathHttps}/supplierInfo/supplier01.htm"><img class="radius4" src="../images/supplier_a_01.jpg" /></a></li>
		            <li><a href="${resourcePathHttps}/supplierInfo/supplier02.htm"><img class="radius4" src="../images/supplier_b_01.jpg" /></a></li>
		        </ul>
	            <li class="banner-li dsn"><a href="javascript:void(0)"><img class="radius4" src="../images/supplier_b_01.jpg" /></a></li>
		    </div>
		    <div class="hd">
		        <ul></ul>
		    </div>
		</section>
		<section class="sectionBox ptb15">
			<ul class="mleft10">
				<li>
					<img class="w10" style="vertical-align: text-bottom;" src="../images/icon-location-new.png">
					<span class="mleft5">深圳市南山区蛇口工业七路爱榕路61号</span>
				</li>
				<li class="mtop5">
					<img class="w10" src="../images/icon-delivery.png">
					<span class="mleft5">3公里范围内，满79元包配送，不满加5元配送</span>
				</li>
			</ul>
		</section>
	</c:if>

	<c:if test="${fn:length(limitBuyList) > 0}">
		<section id="limitBuyBox" class="sectionBox borderbottomgrey pb15">
			<h3 class="common-title title-shadow f18 pleft10">限时促销</h3>
			<div class="swiper-container opacity0" style="max-height: 240px">
				<ul class="swiper-wrapper">

					<c:forEach items="${limitBuyList}" var="item" varStatus="status">
						<li class="swiper-slide">
							<c:choose>
								<c:when test="${param.jfqsource == 'jfqapp' }">
									<a class="limitbuy-link" data-itemid="${item.limitBuyId}" href="../API/limitBuy/limitBuyDetail.html?id=${item.limitBuyId}">
								</c:when>
								<c:otherwise>
									<a class="limitbuy-link" data-itemid="${item.limitBuyId}" href="../limitBuy/limitBuyDetail.html?id=${item.limitBuyId}">
								</c:otherwise>
							</c:choose>
								<div><img class="radius4" src="${item.limitBuyPic}<c:choose><c:when test="${fn:contains(item.limitBuyPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_140,h_140,limit_0/format,jpg/interlace,1/quality,q_95" /></div>
								<div class="f12 bold mtop10 word-break1">${item.limitBuyTitle}</div>
								<div class="red mtop5 f14">￥<fmt:formatNumber value="${item.limitBuyPrice}" pattern="0.00"/></div>
								<div><span class="line-through f12 grey">￥<fmt:formatNumber value="${item.shelfPrice/100}" pattern="0.00"/></span></div>
							</a>
						</li>
					</c:forEach>

				</ul>
			</div>
		</section>
	</c:if>

	<c:if test="${fn:length(hotSaleList) > 0}">
		<section id="hotItemsBox" class="sectionBox pos-relative borderbottomgrey pb15" style="z-index: 2;">
			<h3 class="common-title f18 pleft10 <c:if test="${fn:length(limitBuyList) == 0}"> title-shadow</c:if>">今日爆款</h3>
			<%-- <div class="m010">
				<a href="javascript:void(0)">
					<c:choose>
						<c:when test="${param.storeId == '110214' || param.storeId == '10012278' || param.storetest == 'storetest' }">
							<img class="radius4" src="../images/sub_banner.jpg" />
						</c:when>
						<c:otherwise>
							<img class="radius4" src="../images/sub_banner.jpg" />
						</c:otherwise>
					</c:choose>
				</a>
			</div> --%>
			<div class="swiper-container opacity0" style="max-height: 240px">
				<ul class="swiper-wrapper">

					<c:forEach items="${hotSaleList}" var="item" varStatus="status">
						<li class="swiper-slide">
							<a class="item-link" data-itemid="${item.id}" href="../product/productDetail.do?ptId=${item.id}">
								<div><img class="radius4" src="${item.picBaseMini}<c:choose><c:when test="${fn:contains(item.picBaseMini, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_140,h_140,limit_0/format,jpg/interlace,1/quality,q_95" /></div>
								<div class="f12 bold mtop10 word-break1">${item.name}</div>
								<div class="red mtop5 f14">￥<fmt:formatNumber value="${item.priceDiscount/100}" pattern="0.00"/></div>
								<div><span class="line-through f12 grey">￥<fmt:formatNumber value="${item.price/100}" pattern="0.00"/></span></div>
							</a>
						</li>
					</c:forEach>

				</ul>
			</div>
		</section>
	</c:if>
	<section id="tabBox" class="tabBox bgwhite pos-relative overvisible">
		<h3 class="common-title f18 pleft10 <c:if test="${fn:length(limitBuyList) == 0 && fn:length(hotSaleList) == 0}"> title-shadow</c:if>" style="padding-bottom: 8px;">优选精品</h3>
		<div id="tabMenu" class="swiper-container tab-menu m010 opacity0  borderbottomgrey">
			<div id="loadingToast" class="opacity0"></div>
			<ul class="sell-date swiper-wrapper">
			</ul>
		</div>
	    <div id="tabBox-bd">
	        <div class="con bgwhite">
	        	<div id="storeListBox" class="p010 storelist-minheight">
	        		<div class="sectionBox loading grey mtop10 newloading"><img src="../images/loading01.gif" /> 加载中…</div>
	            </div>
            	<ul class="displaybox boxalignend pos-relative mtop5 pt10 store-item dsn">
            		<li class="store-item-img radius4 overhidden" prdtId="1">
            			<a class="item-link" data-itemid="121895" href="#">
            				<img class="bgloading bgsize110" data-original="../images/prev_img.jpg" src="../images/pixel.gif">
            			</a>
            		</li>
            		<li class="boxflex01 f16 m010 mb5 lineheight110">
            			<a class="item-link" data-itemid="121895" href="#" target="_blank">
	            			<div class="store-item-title">
		            			<div class="store-item-name word-break1"></div>
	            				<div class="store-item-desc mtop2 f12 grey word-break1"></div>
            				</div>
	            			<div class="red"><span class="f12">精选价 </span>￥<span class="store-item-price">39.90</span></div>
	            			<div class=""><span class="line-through f12 grey">原价 ￥<span class="market-price">39.90</span></span></div>
	            		</a>
	            		<div class="store-supplier grey f12 dsn">来自<a class="supplier-name mleft10 grey" href="javascript:void(0)">解放区平台</a></div>
            		</li>
            		<li class="join-btn store-shoppingcart" leftCount="10"><img src="../images/icon_redcart.png"></li>
            	</ul>
	        </div>
	    </div>
	</section>
	<%-- <c:if test='${param.jfqsource != "jfqapp" }'>
		<footer class="bordertopgrey">
			<p>Copyright <span>&copy;</span> 2014-<%=Calendar.getInstance().get(Calendar.YEAR)%>深圳前海邻里乐科技服务有限公司 <br />All rights reserved.</p>
		</footer>
	</c:if> --%>
</div>
<div class="sectionBox loading grey mtop15 hide"><img src="../images/loading01.gif" /> 加载中…</div>
<section class="pop-tips dsn">
	<div class="pop-tips-text">请选择</div>
</section>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="${resourcePathHttps}/commonjs/TouchSlide.1.1.js"></script>
<script src="${resourcePathHttps}/commonjs/requestAnimationFrame.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.flying.js"></script>
<script src="${resourcePathHttps}/commonjs/swiper-3.4.2.jquery.min.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.lazyload.js"></script>
<script src="../js/method.common.js"></script>
<script src="../js/store.index.js?v20171211"></script>
</body>
<script type="text/javascript">
	var cnzz_s_tag = document.createElement('script');
	cnzz_s_tag.type = 'text/javascript';
	cnzz_s_tag.async = true;
	cnzz_s_tag.charset = 'utf-8';
	cnzz_s_tag.src = 'https://s11.cnzz.com/z_stat.php?id=1261123817&async=1';
	var root_s = document.getElementsByTagName('script')[0];
	setTimeout(function(){
		root_s.parentNode.insertBefore(cnzz_s_tag, root_s);
	},4000);
</script>
</html>