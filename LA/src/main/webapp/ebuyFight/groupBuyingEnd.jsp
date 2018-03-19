<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cnfantasia.server.api.pub.header.HeaderConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>拼购详情</title>
<link rel="stylesheet" href="../ebuyFight/css/swiper.css" type="text/css">
<link rel="stylesheet" href="../ebuyFight/css/groupbuying.css" type="text/css">
<link rel="stylesheet" href="${resourcePathHttps}/dist/photoswipe.css">
<link rel="stylesheet" href="${resourcePathHttps}/dist/default-skin/default-skin.css">
<style>
.my-gallery {   width: 100%; float: left;}
.my-gallery img { width: 100%;}
.my-gallery figure { display: block; float: left; margin:0; overflow: hidden;}
.my-gallery figcaption { display: none; }
</style>
</head>

<body class="bggrey">
<section id="newTestId" class="sectionBox">
    <!-- Swiper -->
    <div class="swiper-container pos-relative">
        <div class="swiper-wrapper bgdarkgrey my-gallery">
        <c:forEach var="productPic" items="${picList}">
        	<figure class="swiper-slide" itemprop="associatedMedia" itemscope style="background:url(images/loading01.gif) no-repeat center; background-size:26px;">
              <a href="${picserverUrl}${productPic}" itemprop="contentUrl" data-size="640x540">
                  <img src="${picserverUrl}${productPic}" itemprop="thumbnail" />
              </a> 
            </figure> 
        </c:forEach>  	
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
    </div>
    
	<!--swiper zoom start-->
    <div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="pswp__bg"></div>
        <div class="pswp__scroll-wrap">
            <div class="pswp__container">
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
            </div>
            <div class="pswp__ui pswp__ui--hidden">
                    <div class="pswp__top-bar">
                        <div class="pswp__counter"></div>
                        <div class="pswp__button pswp__button--close" title="Close (Esc)"></div>
                        <div class="pswp__button pswp__button--zoom" title="Zoom in/out"></div>
                        <div class="pswp__preloader">
                            <div class="pswp__preloader__icn">
                              <div class="pswp__preloader__cut">
                                <div class="pswp__preloader__donut"></div>
                              </div>
                            </div>
                        </div>
                    </div>
                    <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
                        <div class="pswp__share-tooltip"></div> 
                    </div>
                    <div class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)">
                    </div>
                    <div class="pswp__button pswp__button--arrow--right" title="Next (arrow right)">
                    </div>
                    <div class="pswp__caption">
                        <div class="pswp__caption__center"></div>
                    </div>
              </div>
        </div>
    </div>
    <!--swiper zoom end-->
</section>
<section class="sectionBox">
    <ul class="item-price f14 ptb1210 bgdarkblue">
        <li class="left countdown">倒计时：
        	<span class="days">00</span>天 
        	<span class="hours time-bg">00</span> :
        	<span class="minutes time-bg">00</span> :
        	<span class="seconds time-bg">00</span>
        </li>
        <li class="right grey">${fightProduct.buyNumMsg}</li>
    </ul>
   <div class="info pb0">
   <input class="endTime" type="hidden" name="endTime" id="endTime" value="${endTime}">
   <input class="productId" type="hidden" name="productId" id="productId" value="${fightProduct.id}">
   <input class="fightProductId" type="hidden" name="fightProductId" id="fightProductId" value="${fightProduct.fightProductId}">
        <div class="f16 bold mtop10 p010">${fightProduct.name}，大家快来拼吧</div></a>
        <div class="message p010">
            <span class="f14">${fightProduct.desc}</span>
        </div>
        <div class="ptb10 mtop10 bordertopgrey mleft10">
            <span class="green">自提点：${fightProduct.zitiDian.name}</span>
        </div>
        <div class="ptb10 lineheight140 bordertopgrey mleft10 pright10">
            <span>截止到截团时间，不足开团人数，自动退款到支付账户</span>
        </div>
		<section class="divide-box bordertbgrey"></section>
	    <div class="list-box bgwhite">
	        <a class="displaybox" href="../ebuyFight/groupBuyingDesc.jsp">
	            <div class="item-standard-name height36 f16 boxflex01">拼购说明</div>
	            <div class="boxflex01 box-arrow t-right grey"></div>
	        </a>
	    </div>
		<section class="divide-box bordertbgrey"></section>
	    <div class="list-box bgwhite">
	        <a class="displaybox" href="../laGroupPurchase/qryLaProIntroduce.do?productId=${fightProduct.id}">
	            <div class="item-standard-name height36 f16 boxflex01">商品详情</div>
	            <div class="boxflex01 box-arrow t-right grey"></div>
	        </a>
	    </div>
		<section class="divide-box bordertbgrey"></section>
        <ul class="item-price f16 borderbottomgrey ptb1210">
            <li class="left">客服电话</li>
            <li class="right"><a class="blue" href="tel:${phone}">${phone}</a></li>
        </ul>
    </div>
</section>           
<section class="divide-box"></section>
<section class="pbfooter"></section>
<div class="sectionBox">
    <div class="exchang-fixed displaybox">        
    <div class="exchang-fixed displaybox">
        <div id="joinShoppingCartBtn" class="btn-fixed height48 lineheight48 t-center boxflex01 bgdarkgrey">
            <a id="btnCart" href="javascript:void(0)">
        		<div class="white">已售罄</div>
			</a>
        </div>
    </div>
    </div>
</div>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.downCount.js"></script>
<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
		var endTime = $('#endTime').val();
		//倒计时
        $('.countdown').downCount({
            date: endTime,
            offset: +10
        }, function () {
            $.Showmsg('该拼团已结束!');
        });
</script>

<script src="${resourcePathHttps}/commonjs/swiper.min.js"></script>
<script>	
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: false,
        spaceBetween: 0,
		loop:false
    });
</script>
<script src="${resourcePathHttps}/dist/photoswipe.min.js"></script>
<script src="${resourcePathHttps}/dist/photoswipe-ui-default.min.js"></script>
<script src="${resourcePathHttps}/dist/index.js"></script>

<script src="../ebuyFight/js/groupBuy.common.js"></script>

<script src="${resourcePathHttps}/commonjs/jquery.scrollLoading.js"></script>
<script src="${resourcePathHttps}/commonjs/imgPreloading.js"></script>

</body>
</html>