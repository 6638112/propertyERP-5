<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cnfantasia.jfq.share.constant.ShareConstant"%>
<%@page import="com.cnfantasia.pub.constant.CookieConstant"%>
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
        <div class="swiper-wrapper imgLoading my-gallery">
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
   <input class="productId" type="hidden" name="productId" id="productId" value="${fightProduct.productShelfId}">
   <input class="fightProductId" type="hidden" name="fightProductId" id="fightProductId" value="${fightProduct.fightProductId}">
        <div class="f16 bold mtop10 p010">${fightProduct.name}，大家快来拼吧</div></a>
        <div class="message p010">
            <span class="f14">${fightProduct.desc}</span>
        </div>
        <div class="item-standard mtop10 mleft10 bordertopgrey">
            <div class="number disbox">
            	<div class="name grey">拼购数量</div>
            	<div class="btn-num btnReduce">-</div>
            	<input class="input-normal w80 itemNum" type="text" maxlength="3" value="1" />
            	<div class="btn-num btnAdd">+</div>
            </div>
        </div> 
        <div class="ptb10 mtop10 bordertopgrey mleft10">
            <span class="green">自提点：${fightProduct.zitiDian.name}</span>
            <span class="green" onclick="goZitidian()" style="float:right;margin-right:10px;">[自提点切换]</span>
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
        <div id="singleBuyBtn" class="shopping-cart t-center bgorange ptb13 white">
    		<div class="f16">￥ ${fightProduct.priceDiscount/100}</div>
    		<div class="f12 opacity50 mtop2">单独购买</div>
        </div>
        <div id="groupBuyBtn" class="boxflex01 t-center bgred ptb13 white">
    		<div class="f16">￥ ${fightProduct.fightPrice/100}</div>
    		<div class="f12 opacity50 mtop2">拼购(${fightProduct.fightNumStr})</div>
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

		//调整轮播图片高度
	    var $swiperContainer = $('.swiper-container');
	    var swiperContainerWidth = $swiperContainer.width();
	    $swiperContainer.height(swiperContainerWidth*540/640);
	    
		var endTime = $('#endTime').val();
		//倒计时
        $('.countdown').downCount({
            date: endTime,
            offset: +8
        }, function () {
            $.Showmsg('该拼团已结束!');
        });
		var productId = $("#productId").val();
		var fightProductId =  $("#fightProductId").val();
		$('#groupBuyBtn').click(function(){
			location.href="../cart/getLaFightOrderMsg.do?productId="+fightProductId+"&productNum=" + $('.itemNum').val();
		});
		$('#singleBuyBtn').click(function(){
			location.href="../product/productDetail.do?ptId="+productId;
		});
	});
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">

//统一微信分享风格 
var shareDesc = '${fightProduct.desc}' || '${fightProduct.fightPrice/100}';
var shareImgUrl = $("div.swiper-wrapper.imgLoading.my-gallery img").eq(0).attr("src");
var shareTitle = '${fightProduct.name}';
var shareUrl = '${signInfo.url}';
shareUrl = shareUrl.replace("code=","codeT=");

wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: '<%=com.cnfantasia.pub.util.WeChatConfig.APPID%>', // 必填，公众号的唯一标识
    timestamp: '${signInfo.timestamp}', // 必填，生成签名的时间戳
    nonceStr: '${signInfo.nonceStr}', // 必填，生成签名的随机串
    signature: '${signInfo.signature}',// 必填，签名，见附录1
    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});
wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
    
	//分享朋友圈
	wx.onMenuShareTimeline({
	    title: shareTitle, // 分享标题
	    link: shareUrl, // 分享链接
	   	imgUrl: shareImgUrl, // 分享图标
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	
	//发送给朋友
	wx.onMenuShareAppMessage({
	    title: shareTitle, // 分享标题
	    desc: shareDesc, // 分享描述
	    link: shareUrl, // 分享链接
	   	imgUrl: shareImgUrl, // 分享图标
	    type: 'link', // 分享类型,music、video或link，不填默认为link
	    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
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
    
    $(function(){
    	var swiperImgLength = $('.my-gallery figure').length;
    	if(swiperImgLength <= 1){
    		$('.swiper-pagination').hide();
    	}
    });
    
    function goZitidian(){
    	localStorage['zitiDianName'] = "${fightProduct.zitiDian.name}";
    	location='${pageContext.request.contextPath}/laGroupPurchase/ziTiDianList.do?version=100';
    }
</script>
<script src="${resourcePathHttps}/dist/photoswipe.min.js"></script>
<script src="${resourcePathHttps}/dist/photoswipe-ui-default.min.js"></script>
<script src="${resourcePathHttps}/dist/index.js"></script>
<script src="../ebuyFight/js/groupBuy.common.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.scrollLoading.js"></script>
<script src="${resourcePathHttps}/commonjs/imgPreloading.js"></script>
</body>
</html>