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
<link rel="stylesheet" href="../css/swiper.css" type="text/css">
<link rel="stylesheet" href="../css/groupbuying.css" type="text/css">
<link rel="stylesheet" href="../dist/photoswipe.css">
<link rel="stylesheet" href="../dist/default-skin/default-skin.css">
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
        <div class="swiper-wrapper imgLoading my-gallery">
        <c:forEach var="productPic" items="${picList}">
        	<figure class="swiper-slide" itemprop="associatedMedia" itemscope style="background:url(images/loading01.gif) no-repeat center; background-size:26px;">
              <a href="${picserverUrl}${productPic}<c:choose><c:when test="${fn:contains(productPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_560,h_473,limit_0/format,jpg/interlace,1" itemprop="contentUrl" data-size="640x540">
                  <img src="${picserverUrl}${productPic}<c:choose><c:when test="${fn:contains(productPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_560,h_473,limit_0/format,jpg/interlace,1/quality,q_80" itemprop="thumbnail" />
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
        <li class="t-center">该轮拼购${fightProduct.startDate}开始</li>
    </ul>
   <div class="info pb0">
   <input class="endTime" type="hidden" name="endTime" id="endTime" value="${endTime}">
   <input class="ziTiId" type="hidden" name="ziTiId" id="ziTiId" value="${fightProduct.zitiDian.id}">
   <input class="productId" type="hidden" name="productId" id="productId" value="${fightProduct.productShelfId}">
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
	        <a class="displaybox" href="../groupPurchase/toPage.html?page=groupBuyingDesc">
	            <div class="item-standard-name height36 f16 boxflex01">拼购说明</div>
	            <div class="boxflex01 box-arrow t-right grey"></div>
	        </a>
	    </div>
		<section class="divide-box bordertbgrey"></section>
	    <div class="list-box bgwhite">
	        <a class="displaybox" href="../groupPurchase/qryProIntroduce.html?productId=${fightProduct.id}">
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
        <div id="singleBuyBtn" class="shopping-cart t-center bgorange ptb13 white">
    		<div class="f16">￥ ${fightProduct.priceDiscount/100}</div>
    		<div class="f12 opacity50 mtop2">单独购买</div>
        </div>
        <div class="boxflex01 t-center bgdarkgrey ptb13 white">
    		<div class="f16">￥ ${fightProduct.fightPrice/100}</div>
    		<div class="f12 opacity50 mtop2">${fightProduct.fightNumStr}</div>
        </div>
    </div>
</div>
<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/jquery.downCount.js"></script>
<script src="../js/Validform_v5.3.2.js"></script>
<script src="../js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);

		//调整轮播图片高度
	    var $swiperContainer = $('.swiper-container');
	    var swiperContainerWidth = $swiperContainer.width();
	    $swiperContainer.height(swiperContainerWidth*540/640);
		
		//判断安卓ios系统
		var u = navigator.userAgent, app = navigator.appVersion;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		var productId = $("#productId").val();
		var fightProductId =  $("#fightProductId").val();
		var ziTiId = $("#ziTiId").val();
		//跳转购物车按钮
		//安卓
		if(isAndroid){
			$('#singleBuyBtn').click(function(){
				window.groupBuy.singleBuyBtn(productId);
			});
		}
		//ios
		if(isiOS){
			function singleBuyBtn(param01,param02){
				document.location="jfq://"+param01+":/"+param02;
			};
			
			$('#singleBuyBtn').click(function(){
				singleBuyBtn('singleBuyBtn',productId);
			});
		}
		
		//加入购物车按钮
		//安卓
		if(isAndroid){
			$('#groupBuyBtn').click(function(){
				window.groupBuy.groupBuyBtn(fightProductId,ziTiId);
			});
		}
		//ios
		if(isiOS){
			function groupBuyBtn(param01,param02,param03){
				document.location="jfq://"+param01+":/"+param02+"/"+param03;
			};
			
			$('#groupBuyBtn').click(function(){
				groupBuyBtn('groupBuyBtn',fightProductId,ziTiId);
			});
		}

		
	});
    
	//微信分享	
    var shareUrl = "${share.url }",
    	shareImg = "${share.pic }",
    	shareTitle = "${share.tittle }",
    	shareDesc = "${share.desc}";

    //分享给朋友
   	//ios
   	function shareToFriend(){
   		document.location='jfq://'+'shareToFriend'+'/'+' '+shareUrl+','+shareImg+','+shareTitle+','+shareDesc;
   	};
   	
   	//分享到朋友圈
   	//ios
   	function shareTimeLine(){
   		document.location='jfq://'+'shareTimeLine'+'/'+' '+shareUrl+','+shareImg+','+shareTitle;
   	};
       
    var shareArray = [shareUrl, shareImg, shareTitle, shareDesc];
   	//安卓
   	function androidShare(){
   		window.groupBuy.shareToFriendAD(shareArray);
   	};
</script>
<script src="../js/swiper.min.js"></script>
<script>	
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: false,
        spaceBetween: 0,
		loop:false
    });
    
    $(function(){
    	var swiperImgLength = $('.my-gallery figure').length;
    	if(swiperImgLength <= 1){
    		$('.swiper-pagination').hide();
    	}
    });
</script>
<script src="../dist/photoswipe.min.js"></script>
<script src="../dist/photoswipe-ui-default.min.js"></script>
<script src="../dist/index.js"></script>

<script src="../js/groupBuy.common.js"></script>

<script src="../js/jquery.scrollLoading.js"></script>
<script src="../js/imgPreloading.js"></script>

</body>
</html>