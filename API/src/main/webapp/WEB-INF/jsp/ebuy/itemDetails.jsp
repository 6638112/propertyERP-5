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

<title>商品详情</title>
<link rel="stylesheet" href="${resourcePathHttps}/commoncss/swiper-3.4.2.min.css" type="text/css">
<link rel="stylesheet" href="../css/common.css?v20170907" type="text/css">
<link rel="stylesheet" href="${resourcePathHttps}/dist/photoswipe.css">
<link rel="stylesheet" href="${resourcePathHttps}/dist/default-skin/default-skin.css">
<style>
.my-gallery {   width: 100%; float: left;}
.my-gallery img { width: 100%;}
.my-gallery figure { display: block; float: left; margin:0; overflow: hidden;}
.my-gallery figcaption { display: none; }
.pop-tips,.pop-tips1,.pop-tips2,.pop-tips3,.pop-tips4{ margin:0 auto; position:fixed; width:100%; top:45%; overflow:hidden; z-index:999;}
.pop-tips-text{ margin:0 auto; padding:10px 15px; width:160px; line-height:20px; text-align:center; background:#232736; color:#fff; border-radius:4px;}
</style>
</head>

<body>
<section id="newTestId" class="sectionBox">
    <!-- Swiper -->
    <div class="swiper-container pos-relative">
        <div class="swiper-wrapper bggrey my-gallery">
        	<c:forEach var="ebuyProductPic" items="${ebuyProductPicList}">
	            <figure class="swiper-slide bgloading" itemprop="associatedMedia" itemscope>
	              <a href="${picserverUrl}${ebuyProductPic}<c:choose><c:when test="${fn:contains(ebuyProductPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_560,h_473,limit_0/format,jpg/interlace,1/quality,q_95" itemprop="contentUrl" data-size="640x540">
	                  <img src="${picserverUrl}${ebuyProductPic}<c:choose><c:when test="${fn:contains(ebuyProductPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_560,h_473,limit_0/format,jpg/interlace,1/quality,q_95" itemprop="thumbnail" />
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
   <div class="info pb0">
        <div class="f16 bold mtop10 p010">${ebuyProductInfo.name}</div></a>
        <div class="message p010">
            <span>${ebuyProductInfo.desc}</span>
        </div>
        <input class="dsn" type="hidden" name="productId" value="${ebuyProductInfo.id}" />
        <input class="dsn" type="hidden" name="productSpecId" value="${ebuyProductInfo.id}" />
        <div class="special bordertopgrey mtop10">
            <div class="grey p010">原价：<span class="market-price">￥${ebuyProductInfo.price/100}</span></div>
            <div class="hot-price f16 p010 mtop5">精选价：<span class="red bold">￥${ebuyProductInfo.shelfProduct.priceDicount/100}</span></div>
            <div class="p010 grey mtop5">${defaultDeliveryName}</div>
            <div class="p010 grey mtop5">供应商：${supplyMerchantName }</div>
            <div class="p010 grey mtop5">${themeAdDesc }</div>
        </div>
        <div class="item-standard readonly-box bordertopgrey dsn">
            <div>
                <div class="number disbox p010 mtop20">
	                <div class="name grey">数量</div>
	                <div class="btn-num btnReduce border-left-radius">-</div>
	                <input class="input-normal w80 itemNum border-black" maxlength="3" type="text" value="1" />
	                <div class="btn-num btnAdd border-right-radius">+</div>
                </div>
            </div>
        </div> 
        <c:if test="${filmStatus==1}">
			<section class="divide-box bordertbgrey"></section>
		    <div class="list-box bgwhite">
		        <a class="displaybox" href="${filmLookUrl}">
		            <div class="item-standard-name height36 f16 boxflex01">支持在线订座的影院</div>
		            <div class="box-arrow t-right grey"></div>
		        </a>
		    </div>
			<section class="divide-box bordertbgrey"></section>
		    <div class="list-box bgwhite">
		        <a class="displaybox" href="${filmPayUrl}">
		            <div class="item-standard-name height36 f16 boxflex01">在线订座<span class="bgred icon-new">new</span></div>
		            <div class="box-arrow t-right grey"></div>
		        </a>
		    </div>
	    </c:if>  
		<section class="divide-box bordertopgrey"></section>
        <ul class="item-price f16 borderbottomgrey" style="padding:12px 10px;">
            <li class="left"><img style="width:15px; vertical-align:middle; margin-top:-2px;" src="../images/phone-icon.png" /> 客服电话</li>
            <li class="right"><a href="tel:${phone}">${phone}</a></li>
        </ul>
    </div>
</section>       
<section class="divide-box"></section>
<section id="tabBox" class="tabBox">
    <div class="hd tab-head borderbottomgrey">
        <ul>
            <li class="wp50"><a href="javascript:void(0)">介绍</a></li>
            <li class="wp50"><a href="javascript:void(0)">参数</a></li>
        </ul>
    </div>
    <div class="bd" id="tabBox-bd">
        <div class="con bgwhite">
            <div id="tabOne" class="p010">
               <div class="info pt10 lineheight0">
               	   <c:choose>
               			<c:when test="${not empty ebuyProductIntroducePicList}">
               				<c:forEach var="ProductIntroduce" items="${ebuyProductIntroducePicList}">
		               			<img class="item-pic-big bgloading" data-original="${picserverUrl}${ProductIntroduce.urlMini}<c:choose><c:when test="${fn:contains(ProductIntroduce.urlMini, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,w_560/format,jpg/quality,q_95/interlace,1" src="../images/pixel.gif" />
		               			<div class="img-desc-text-box">${ProductIntroduce.textDesc}</div>
		               		</c:forEach>
               			</c:when>
               			<c:otherwise>
               				<div class="img-desc-text-box t-center">该商品暂无图文介绍</div>
               			</c:otherwise>
               		</c:choose>
                </div>
            </div>
        </div>  
        <div class="con bgwhite">
            <div id="tabTwo" class="p010">
               <div class="info pt10">
               		<c:choose>
               			<c:when test="${not empty productParametersList}">
               				<table class="bordered grey f12">
								<c:forEach var="productParameter"  items="${productParametersList}">
								  <tr>
			                        <td>${productParameter.tPropName}</td>
			                        <td>${productParameter.tPropValue}</td>
			                      </tr>
								</c:forEach>
		                    </table>
               			</c:when>
               			<c:otherwise>
               				<div class="img-desc-text-box t-center">该商品暂无参数</div>
               			</c:otherwise>
               		</c:choose>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="pop-tips2 upload-erro hide">
	<div class="pop-tips-text">系统异常，请联系管理员！…</div>
</section>
<section class="pop-tips1 upload-save hide">
	<div class="pop-tips-text">请稍候</div>
</section>
<div id="gotop" class="go-top dsn"><img src="../images/icon-gotop.png" /><br/>顶部</div>
<section class="pbfooter readonly-box dsn"></section>
<div class="sectionBox readonly-box dsn">
    <div class="exchang-fixed displaybox">
        <div id="shoppingCartBtn" class="shopping-cart bggradient">
       		<div id="cartNum" class="item-num bgred">
		    	<c:if test="${empty proCarNum }">0</c:if>
		    	<c:if test="${not empty proCarNum }">${proCarNum }</c:if>
       		</div>
       		<img src="../images/shoppingcart-black.png" />
       	</div>
       	<c:if test="${ebuyProductInfo.leftCount != 0 }">
	        <div id="joinShoppingCartBtn" class="btn-fixed boxflex01">
	            <div class="btn-cart"><a id="btnCart" class="bgred" href="javascript:void(0)">加入购物车</a></div>
	        </div>
        </c:if>
       	<c:if test="${ebuyProductInfo.leftCount == 0 }">
	        <div class="btn-fixed boxflex01">
	            <div class="t-center"><a id="btnCart" class="bgdarkgrey" href="javascript:void(0)">加入购物车(库存不足)</a></div>
	        </div>
        </c:if>
    </div>
</div>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script>
$(function(){
	new FastClick(document.body);
	
    //判断页面是否只读
    isReadonly();
    
    //如果没有商品描述，则设置间隔高度为0
    $('.img-desc-text-box').each(function(){
    	if($(this).text() === ''){
    		$(this).css('margin', '0');
    	}
    })
});

function isReadonly(){
	if(location.search.indexOf('readonly') === -1){
		$('.readonly-box').removeClass('dsn');
	}
}
</script>

<script src="${resourcePathHttps}/commonjs/swiper-3.4.2.jquery.min.js"></script>
<script>	
$(function(){
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: false,
        spaceBetween: 0,
		loop:false
    });
    
    var $swiperFigure = $('.swiper-wrapper figure');
    if($swiperFigure.length == 1){
    	$('.swiper-pagination').hide();
    }
});
</script>
<script src="${resourcePathHttps}/dist/photoswipe.min.js"></script>
<script src="${resourcePathHttps}/dist/photoswipe-ui-default.min.js"></script>
<script src="${resourcePathHttps}/dist/index.js"></script>

<script src="${resourcePathHttps}/commonjs/requestAnimationFrame.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.flying.js"></script>

<script src="../js/detail.common.js?20160505"></script>
<script src="${resourcePathHttps}/commonjs/TouchSlide.1.1.js"></script>

<script src="${resourcePathHttps}/commonjs/jquery.lazyload.js"></script>
<script>
$(function(){
	TouchSlide( { slideCell:"#tabBox",
		endFun:function(i){ //高度自适应
			var bd = document.getElementById("tabBox-bd");
			bd.parentNode.style.height = bd.children[i].children[0].offsetHeight+"px";
			if(i>0)bd.parentNode.style.transition="100ms";//添加动画效果
		}
	});

    //lazyload
    $("img.bgloading").lazyload(
    	{effect: "fadeIn"},
    	resetImgBoxHeight
    );
	function resetImgBoxHeight(){
		var curHeight = $('#tabOne .info').height();
		$(".tempWrap").height(curHeight + 20);
	}
});
</script>
<script type="text/javascript">

$(function() {
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	//跳转购物车按钮
	//安卓
	if(isAndroid){
		$('#shoppingCartBtn').click(function(){
			window.itemDetails.shoppingCartBtn('param');
		});
	}
	//ios
	if(isiOS){
		function shoppingCartBtn(param01,param02){
			document.location="jfq://"+param01+":/"+param02;
		};
		
		$('#shoppingCartBtn').click(function(){
			shoppingCartBtn('shoppingCartBtn','param');
		});
	}
	
	//加入购物车按钮
	//安卓
	if(isAndroid){
		$('#joinShoppingCartBtn').click(function(){
			var itemNumSelect = $('.number .itemNum').val(); //选择商品数量
			window.itemDetails.joinShoppingCartBtn(itemNumSelect);
		});
	}
	//ios
	if(isiOS){
		function joinShoppingCartBtn(param01,param02){
			document.location="jfq://"+param01+":/"+param02;
		};
		
		$('#joinShoppingCartBtn').click(function(){
			var itemNumSelect = $('.number .itemNum').val(); //选择商品数量
			joinShoppingCartBtn('joinShoppingCartBtn',itemNumSelect);
		});
	}
	
});
</script>

<script type="text/javascript">
	//设置购物车数量
	function setCartNum(param){
		$('#cartNum').text(param);
	}
	
	//分享给朋友
    function appShareFn(newOptions){
	    var appShare = {
	    	options: {
	    		shareUrl: '分享链接地址',
		    	shareImg: '分享图片地址',
		    	shareTitle: '文字标题',
		    	sharePrice: '商品价格'
	    	},
			//ios
			iosShare: function(){
				var me = this;
				document.location='jfq://' + 'shareToFriend' + '/' + me.options.shareUrl + ',' + me.options.shareImg + ',' + me.options.shareTitle + ',' + me.options.sharePrice;
			},
		    //安卓
			androidShare: function(){
				var me = this;
				window.itemDetails.shareToFriendAD(JSON.stringify(me.options));
			}
		}
	    
	    return function newAppShareFn(){
	    	var newAppShare = Object.create(appShare);
	    	newAppShare.options = $.extend({}, newAppShare.options, newOptions);
	    	return newAppShare;
	    }
    }
    
	//域名协议已修改为https，超市项目资源尚未修改为https，暂时将该分享链接协议修改为http
	var curUrl = '${shareUrl}';
	var shareImg = '${shareImg}' === '' ? 'https://resource.jiefangqu.com/docs/commonimages/jfqlogo_share.png' : '${shareImg}';
	
	//超市资源已修改为https
	//curUrl = curUrl.replace(/https/, 'http');
	
    var appShareFunc = appShareFn({
		shareUrl: curUrl,
    	shareImg: shareImg,
    	shareTitle: '${ebuyProductInfo.name}',
    	sharePrice: '抢购价：￥${ebuyProductInfo.shelfProduct.priceDicount/100}'
    });
	
</script>
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
</body>
</html>