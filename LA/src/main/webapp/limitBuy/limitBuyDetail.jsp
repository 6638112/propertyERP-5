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

    <title>抢购详情</title>
    <link rel="stylesheet" href="${resourcePathHttps}/commoncss/swiper.css" type="text/css">
    <link rel="stylesheet" href="${apiPathHttps}/css/common.css" type="text/css">
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
    <div class="swiper-container pos-relative" data-id="${limitBuyInfo.shelfId}">
        <div class="swiper-wrapper bggrey my-gallery">
                <figure class="swiper-slide" itemprop="associatedMedia" itemscope style="background:url(../images/loading01.gif) no-repeat center; background-size:26px;">
                    <a href="${limitBuyInfo.limitBuyPic}<c:choose><c:when test="${fn:contains(limitBuyInfo.limitBuyPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_560,h_473,limit_0/format,jpg/interlace,1"" itemprop="contentUrl" data-size="640x540">
                        <img src="${limitBuyInfo.limitBuyPic}<c:choose><c:when test="${fn:contains(limitBuyInfo.limitBuyPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_560,h_473,limit_0/format,jpg/interlace,1/quality,q_90" itemprop="thumbnail" />
                    </a>
                </figure>
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
        <div class="f16 bold mtop10 p010">${limitBuyInfo.limitBuyTitle}</div></a>
        <div class="message p010">
            <span>供应商：${limitBuyInfo.merchantName}</span>
        </div>
        <div class="special bordertopgrey mtop10">
            <div class="hot-price f16 p010 mtop5">限时购：<span class="red bold">￥${limitBuyInfo.limitBuyPrice}</span></div>
            <div class="p010 grey mtop5">开始时间：${limitBuyInfo.activityStartTime}</div>
            <div class="p010 grey mtop5">结束时间：${limitBuyInfo.activityEndTime}</div>
        </div>
        <div class="item-standard readonly-box dsn">
            <div>
                <div class="number disbox p010 mtop20">
	                <div class="name grey">数量</div>
	                <div class="btn-num btnReduce border-left-radius">-</div>
	                <input class="input-normal w80 itemNum border-black" maxlength="3" type="text" value="1" />
	                <div class="btn-num btnAdd border-right-radius">+</div>
                </div>
            </div>
        </div>
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
                                <img class="item-pic-big scrollLoading" src="${picserverUrl}${ProductIntroduce.urlMini}<c:choose><c:when test="${fn:contains(ProductIntroduce.urlMini, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,w_560/format,jpg/quality,q_80/interlace,1" />
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
    <div class="pop-tips-text">请稍候…</div>
</section>
<div id="gotop" class="go-top dsn"><img src="../images/icon-gotop.png" /><br/>顶部</div>
<section class="pbfooter readonly-box dsn"></section>
<div class="sectionBox readonly-box dsn">
    <div class="exchang-fixed displaybox">
    	<c:if test="${limitBuyInfo.leftCount != 0 && limitBuyInfo.status == 1 }">
		    <div class="shopping-cart bggradient">
			    <a href="../cart/qryBuyCar.do">
				    <div id="cartNum" class="item-num bgred">${productCount.productCount }</div>
				    <img src="../images/shoppingcart-black.png" />
			    </a>
		    </div>
	    	<div class="boxflex01">
		        <div class="btn-cart h56 t-center bgred white f16">加入购物车</div>
	        </div>
	    	<div class="boxflex01">
	            <div id="buyNowBtn" class="h56 t-center bgorange white f16">我要抢购</div>
	        </div>
        </c:if>
        <c:if test="${limitBuyInfo.leftCount == 0 && limitBuyInfo.status == 1 }">
            <div class="btn-fixed boxflex01">
                <div class="wp100 t-center"><a class="bgdarkgrey" href="javascript:void(0)">我要抢购(库存不足)</a></div>
            </div>
        </c:if>
        <c:if test="${limitBuyInfo.status == 0 }">
            <div class="btn-fixed boxflex01">
                <div class="wp100 t-center"><a class="bgdarkgrey" href="javascript:void(0)">活动尚未开始</a></div>
            </div>
        </c:if>
    </div>
</div>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script>
    $(function(){
        new FastClick(document.body);

        //调整轮播图片高度
        var $swiperContainer = $('.swiper-container');
        var swiperContainerWidth = $swiperContainer.width();
        $swiperContainer.height(swiperContainerWidth*540/640);

        //判断页面是否只读
        isReadonly();
    });

    function isReadonly(){
        if(location.search.indexOf('readonly') === -1){
            $('.readonly-box').removeClass('dsn');
        }
    }
</script>

<script src="${resourcePathHttps}/commonjs/swiper.min.js"></script>
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
<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>

<script src="${resourcePathHttps}/commonjs/requestAnimationFrame.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.flying.js"></script>
<script src="${apiPathHttps}/js/detail.common.js?20160505"></script>
<script src="${resourcePathHttps}/commonjs/TouchSlide.1.1.js"></script>

<script src="${resourcePathHttps}/commonjs/jquery.scrollLoading.js"></script>
<script src="${apiPathHttps}/js/imgPreloading.js"></script>
<script>
    $(function(){
        TouchSlide( { slideCell:"#tabBox",
            endFun:function(i){ //高度自适应
                var bd = document.getElementById("tabBox-bd");
                bd.parentNode.style.height = bd.children[i].children[0].offsetHeight+"px";
                if(i>0)bd.parentNode.style.transition="100ms";//添加动画效果
            }
        });
    });
</script>
<script type="text/javascript">
	//判断获取当前域名
	function getCurHref(project){
		var curHref = '';
		var curOrigin = location.origin;
		var curProtocol = location.protocol;
		if(curOrigin.indexOf('linlile.com.cn') > -1){
			curHref = curProtocol + '//'+project+'.linlile.com.cn';
		}else if(curOrigin.indexOf('linlile.cn') > -1){
			curHref = curProtocol + '//'+project+'.linlile.cn';
		}else if(curOrigin.indexOf('jiefangqu') > -1){
			curHref = curProtocol + '//'+project+'.jiefangqu.com';
		}else{
			curHref = curProtocol + '//localhost';
		}
		return curHref;
	}

    $(function() {
   		//我要抢购按钮
   		$("#buyNowBtn").click(function(){
   			$.ajax({
				type:"get",
				url:"../common/toUrl.do",
				//url:"checkBuyCountBeforelimitBuy.json",	//抢购前先确认促销商品库存
				data:{detailUrl: '/limitBuy/checkBuyCountBeforelimitBuy.json', id: '${limitBuyInfo.limitBuyId}', buyNum:$('.itemNum').val(),},
				dataType:"json",
				success: function(data){  
                 	if (data.status == '0000' ) {
               			//参数：商品id、数量
               			location.href = '../cart/limitBuyDetail.do?jfqsource=limitbuy&id=${limitBuyInfo.limitBuyId}&buyNum=' + $('.itemNum').val();
                 	}else{
               			//location.href = '../cart/limitBuyDetail.do?jfqsource=limitbuy&id=${limitBuyInfo.limitBuyId}&buyNum=' + $('.itemNum').val();
                 		$.Showmsg(data.message);
                 	}
                },  
                error: function(){  
                 	$.Showmsg('网络不给力，请稍后重试'); 
                }  
			});
   		});

		//微信分享
		setTimeout(function(){
			$.getScript('https://res.wx.qq.com/open/js/jweixin-1.0.0.js', function(){
			   $.getScript('../js/wx.share.info.js', function(){
				    //轻应用分享
					setShareInfo({
						title: '${limitBuyInfo.limitBuyTitle}',		// 分享标题
						desc: '限时价：${limitBuyInfo.limitBuyPrice}',		// 分享描述
						link: location.href,	// 分享链接
						imgIcon: getCurHref('resource') + '/docs/commonimages/jfqlogo_share.png'
					});
				});
			});
		}, 1500);
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