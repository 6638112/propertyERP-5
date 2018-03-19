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
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>商品详情</title>
<link rel="stylesheet" href="<%=basePath%>/css/swiper.css" type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/common.css" type="text/css">
<link rel="stylesheet" href="<%=basePath%>/dist/photoswipe.css">
<link rel="stylesheet" href="<%=basePath%>/dist/default-skin/default-skin.css">
<style>
.my-gallery {   width: 100%; float: left;}
.my-gallery img { width: auto;}
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
        <div class="swiper-wrapper bgdarkgrey my-gallery">
        	<c:forEach var="ebuyProductPic" items="${ebuyProductPicList}">
	            <figure class="swiper-slide" itemprop="associatedMedia" itemscope>
	              <a href="${picserverUrl}${ebuyProductPic}" itemprop="contentUrl" data-size="640x540">
	                  <img src="${picserverUrl}${ebuyProductPic}" itemprop="thumbnail" alt="Image description" />
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
            <span>供应商：${supplyMerchantName}</span>
        </div>
        <input class="dsn" type="hidden" name="productId" value="${ebuyProductInfo.id}" />
        <input class="dsn" type="hidden" name="productSpecId" value="${ebuyProductInfo.id}" />
        <div class="special bordertopgrey mtop10">
            <div class="grey p010">原价：<span class="market-price">￥${ebuyProductInfo.price/100}</span></div>
            <div class="hot-price f16 p010 mtop5">限时购：<span class="red bold">￥${ebuyProductInfo.shelfProduct.priceDicount/100}</span></div>
            <div class="p010 grey mtop5">${defaultDeliveryName}</div>
        </div>
        <div class="item-standard">
        	<!--<div class="title"><span class="left f16">选择商品规格</span><span class="item-arrow-show animated"></span></div>-->
            <div>
                <!--<div class="size p010 tbl">
                    <div class="name tbl-cell grey">尺寸</div>
                    <div class="standards tbl-cell">
                        <span class="on">1.5m x 1.8m</span><span>1.5m x 1.8m</span><span>1.5m x 1.8m</span><span>1.5m x 1.8m</span>
                    </div>
                </div>
                <div class="color p010 tbl">
                    <div class="name tbl-cell grey">颜色</div>
                    <div class="standards tbl-cell">
                        <span class="on">白</span><span>粉红</span><span>深蓝</span>
                    </div>
                </div>-->
                <div class="number disbox p010 mtop20"><div class="name grey">数量</div><div class="btn-num btnReduce">-</div><input class="input-normal w80 itemNum" type="text" value="1" /><div class="btn-num btnAdd">+</div></div>
            </div>
        </div> 
		<section class="divide-box"></section>
        <ul class="item-price f16 borderbottom" style="padding:12px 10px;">
            <li class="left"><img style="width:15px; vertical-align:middle; margin-top:-2px;" src="<%=basePath%>/images/phone-icon.png" /> 客服电话</li>
            <li class="right"><a href="tel:0755-22690962">${phone}</a></li>
        </ul>
    </div>
</section>           
<section class="divide-box"></section>
<section id="tabBox" class="tabBox">
    <div class="hd tab-head borderbottom">
        <ul>
            <li class="wp50"><a href="javascript:void(0)">介绍</a></li>
            <li class="wp50"><a href="javascript:void(0)">参数</a></li>
        </ul>
    </div>
    <div class="bd" id="tabBox-bd">  
        <div class="con bgwhite">
            <div id="tabOne" class="p010">
               <div class="info pt10 lineheight0">
                    <c:forEach var="ProductIntroducePic" items="${ebuyProductIntroducePicList}">
               			<img class="item-pic-big" src="${picserverUrl}${ProductIntroducePic}" />
               		</c:forEach>
                </div>
            </div>
        </div>
        <div class="con bgwhite">
            <div id="tabTwo" class="p010">
               <div class="info pt10">
                    <table class="bordered grey">
					<c:forEach var="productParameter"  items="${productParametersList}">
						  <tr>
	                        <td>${productParameter.tPropName}</td>
	                        <td>${productParameter.tPropValue}</td>
	                      </tr>
					</c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="pop-tips2 upload-erro hide">
	<div class="pop-tips-text">系统异常，请联系管理员！…</div>
</section>
<section class="pop-tips1 upload-save hide">
	<div class="pop-tips-text">提交成功<br>我们将在3个工作日内完成审核！</div>
</section>
<div id="gotop" class="go-top dsn"><img src="<%=basePath%>/images/icon-gotop.png" /><br/>顶部</div>
<section class="pbfooter"></section>
<div class="sectionBox">
    <div class="exchang-fixed displaybox">
        <div id="shoppingCartBtn" class="shopping-cart bggreen">
        <c:choose>
        	<c:when test="${proCarNum != null && proCarNum!=''}">
        		<a href="javascript:void(0)" onclick="addToEbuyCar();"><div id="cartNum" class="item-num bgwhite">${proCarNum}</div><img src="<%=basePath%>/images/shoppingcart-white-big.png" /></a>
        	</c:when>
        	<c:when test="${proCarNum == null || proCarNum==''}">
        		<a href="javascript:void(0)"><img src="<%=basePath%>/images/shoppingcart-white-big.png" /></a>
        	</c:when>
        </c:choose>
       	</div>
        <div id="joinShoppingCartBtn" class="btn-fixed boxflex01">
            <div class="btn-cart"><a id="btnCart" class="bgred" href="javascript:void(0)">加入购物车</a></div>
        </div>
    </div>
</div>
<script src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script src="<%=basePath%>/js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
</script>

<script src="<%=basePath%>/js/swiper.min.js"></script>
<script>	
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: false,
        spaceBetween: 0,
		loop:false
    });
</script>
<script src="<%=basePath%>/dist/photoswipe.min.js"></script>
<script src="<%=basePath%>/dist/photoswipe-ui-default.min.js"></script>
<script src="<%=basePath%>/dist/index.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/requestAnimationFrame.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.flying.js"></script>

<script src="<%=basePath%>/js/detail.common.js"></script>
<script src="<%=basePath%>/js/TouchSlide.1.1.js"></script>
<script>
	TouchSlide( { slideCell:"#tabBox",
		endFun:function(i){ //高度自适应
			var bd = document.getElementById("tabBox-bd");
			bd.parentNode.style.height = bd.children[i].children[0].offsetHeight+"px";
			if(i>0)bd.parentNode.style.transition="100ms";//添加动画效果
		}
	});
</script>
<script type="text/javascript">
function addToEbuyCar(){
	var productId =$("input[name='productId']").val();
	var $itemNum = $('#cartNum');
	var productQty=$itemNum.text()*1; //购物车数量
	var productSpecId="54444";
	$.post("../ebuy/add2BuyCar.json",{productId:productId,productQty:productQty,productSpecId:productSpecId},function(data) {
		if (data.status == '0000') {
			
		}else if(data.status == '0001'){
			$('.upload-save').html(data.message);
		}else if(data.status == '0002'){
			$('.upload-erro').removeClass('hide');
		}else if(data.status == '0004'){
			$('.upload-save').html('该商品不存在！！！！');
			$('.upload-save').removeClass('hide');
		}else{
			$('.upload-erro').removeClass('hide');
		}
	});
}

$(function() {
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	//跳转购物车按钮
	//安卓
	if(isAndroid){
		$('#shoppingCartBtn').click(function(param){
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
		$('#joinShoppingCartBtn').click(function(param){
			window.itemDetails.joinShoppingCartBtn('param');
		});
	}
	//ios
	if(isiOS){
		function joinShoppingCartBtn(param01,param02){
			document.location="jfq://"+param01+":/"+param02;
		};
		
		$('#joinShoppingCartBtn').click(function(){
			joinShoppingCartBtn('joinShoppingCartBtn','param');
		});
	}
});
</script>

</body>
</html>