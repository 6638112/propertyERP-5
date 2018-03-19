<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>商品详情</title>
<link rel="stylesheet" href="../../css/merchant/shopping.common.css" type="text/css">
<link rel="stylesheet" href="../../css/merchant/swiper.css" type="text/css">
<link rel="stylesheet" href="../../dist/photoswipe.css">
<link rel="stylesheet" href="../../dist/default-skin/default-skin.css">

<link href="${resourcePath}/commoncss/mobiscroll_002.css" rel="stylesheet" type="text/css">
<link href="${resourcePath}/commoncss/mobiscroll.css" rel="stylesheet" type="text/css">
<link href="${resourcePath}/commoncss/mobiscroll_003.css" rel="stylesheet" type="text/css">
<style>
.my-gallery {   width: 100%; float: left; }
.my-gallery img { width: 100%;}
.my-gallery figure { display: block; float: left; margin:0; overflow: hidden;}
.my-gallery figcaption { display: none; }
</style>
</head>

<body>
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock mleft15 left" href="javascript:history.back();"><img class="back-arrow" src="../../images/merchant/back-arrow.png" /></a>
    <div class="header-title">商品详情-出售中</div>
</header>

<form class="inputform pos-relative" id="inputform" enctype="multipart/form-data" method="post" onsubmit="return false;">
<section class="sectionBox item-details-info">
    <!-- Swiper -->
    <div class="swiper-container pos-relative">
        <div class="swiper-wrapper bgdarkgrey my-gallery lineheight0">
            <figure class="swiper-slide" itemprop="associatedMedia" itemscope>
                <a href="${limitBuyInfo.limitBuyPic}<c:choose><c:when test="${fn:contains(limitBuyInfo.limitBuyPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/format,jpg/interlace,1/quality,q_80" itemprop="contentUrl" data-size="640x540">
                    <img src="${limitBuyInfo.limitBuyPic}<c:choose><c:when test="${fn:contains(limitBuyInfo.limitBuyPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,limit_0,w_640,h_467/format,jpg/interlace,1/quality,q_80" itemprop="thumbnail" alt="Image description" />
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

<section class="sectionBox padding-menu pos-relative item-details-info f16">
    <section class="divide-box bordertbgrey"></section>
    <input class="prodId" type="hidden" name="tEbuyProductFId" id="prodId" value="${limitBuyInfo.productId }" />
    <ul class="register-list f16">
        <li class="borderbottomgrey displaybox">
            <div class="item-standard-name">名称</div>
            <div class="boxflex01 height36 t-right">${limitBuyInfo.productName }</div>
        </li>
        <li class="displaybox">
            <div class="item-standard-name">参与活动</div>
            <div class="boxflex01 height36 t-right red">限时促销</div>
        </li>
    </ul>
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list">
        <li class="borderbottomgrey displaybox">
            <div class="item-standard-name f16">促销标题</div>
            <input type="hidden" name="id" value="${limitBuyInfo.limitBuyId}">
            <div class="boxflex01"><input class="input-text holder-right t-right wp100 red" value="${limitBuyInfo.limitBuyTitle }" type="text" name="title" placeholder="请输入促销标题" datatype="*2-30" nullmsg="请输入促销标题！" errormsg="促销标题由2-30个字符组成" /></div>
        </li>
        <li class="borderbottomgrey displaybox">
            <div class="item-standard-name f16">促销价格</div>
            <div class="boxflex01"><input class="input-text holder-right t-right wp100 red" value="${limitBuyInfo.limitBuyPriceInteger div 100}" type="text" name="price" placeholder="￥" datatype="numberFixTwo" nullmsg="请输入促销价格！" errormsg="请填写数字，可带两位小数！" /></div>
        </li>
        <li class="borderbottomgrey displaybox">
            <div class="item-standard-name">促销库存</div>
            <div class="boxflex01 height36 t-right red">${limitBuyInfo.leftCount}</div>
        </li>
        <li class="displaybox">
            <div class="item-standard-name">每人限购</div>
            <div class="boxflex01 height36 t-right red">
            		<c:choose>
						<c:when test="${limitBuyInfo.maxCanBuy>-1 }"> ${limitBuyInfo.maxCanBuy }</c:when>
						<c:otherwise>不限购</c:otherwise>
					</c:choose>
            </div>
        </li>
    </ul>
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list">
        <li class="borderbottomgrey displaybox">
            <div class="item-standard-name f16">开始时间</div>
            <div class="boxflex01"><input id="startTime" class="input-text holder-right t-right wp100 red" value="${fn:substring(limitBuyInfo.activityStartTime, 0, 16)}" type="text" name="activityStartTime" placeholder="请选择" datatype="*" nullmsg="请选择开始时间！" /></div>
        </li>
        <li class="displaybox">
            <div class="item-standard-name f16">结束时间</div>
            <div class="boxflex01"><input id="endTime" class="input-text holder-right t-right wp100 red" value="${fn:substring(limitBuyInfo.activityEndTime, 0, 16)}" type="text" name="activityEndTime" placeholder="请选择" datatype="*" nullmsg="请选择结束时间！" /></div>
        </li>
    </ul>

    <section class="divide-box bordertbgrey"></section>
    <ul class="bottom-menu-box displaybox t-center">
    	<li id="closePromotionBtn" class="p00"><input class="btn-submit btn-next noradius bordertbgrey red" data-param="1" type="button" name="button" value="结束促销" /></li>
    	<li id="editPromotionBtn" class="p00 borderleft"><input class="btn-submit btn-next noradius bordertbgrey red" data-param="2" type="button" name="button" value="保存修改" /></li>
    </ul>
    <div class="hide"><input class="click-btn-param" type="text" name="submitStatus" value="" /></div>
</section>

</form>
<section class="pop-tips2 hide">
	<div class="pop-tips-text">提交成功<br></div>
</section>

<section class="pop-tips3 upload-text hide">
	<div class="pop-tips-text">信息提交中，请稍候...</div>
</section>

<script src="../../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../../js/merchant/swiper.min.js"></script>
<script src="../../js/merchant/fastclick.js"></script>
<script src="../../js/merchant/resetSwaperSize.js"></script>
<script src="../../js/jquery.form.js"></script>
<script src="../../js/merchant/Validform_v5.3.2.js"></script>

<script src="${resourcePath}/commonjs/mobiscroll_002.js"></script>
<script src="${resourcePath}/commonjs/mobiscroll_004.js"></script>
<script src="${resourcePath}/commonjs/mobiscroll_original.js"></script>
<script src="${resourcePath}/commonjs/mobiscroll_003.js"></script>
<script src="${resourcePath}/commonjs/mobiscroll_005.js"></script>
<script src="../../js/merchant/datetimePicker.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
		
		$('#closePromotionBtn').click(function(){
			if(confirm('确定要结束该促销？')){
				$.post('closeLimitBuy.html', {limitBuyId: '${limitBuyInfo.limitBuyId}'}, function(data,status){
					if(data.status === '0000'){
						$('#closePromotionBtn').attr('disabled', 'disabled');
						$.Showmsg('操作成功！');
						setTimeout(function(){
							location.href = '../itemManage.html';
						},2000);
					}else{
						$.Showmsg(data.message);
					}
				})
			}
		})

		//表单验证
		$("#inputform").Validform({
			tiptype:1,
			btnSubmit:"#editPromotionBtn", 
			postonce: false,
			beforeSubmit:function(){
				$('.pop-tips3').removeClass('hide');
				$('#editPromotionBtn').attr('disabled', 'disabled');
			},
			callback:function(data){
				$("#inputform").ajaxSubmit({  
					url: 'editLimitBuy.html',
		            success: function(data){
						$('.pop-tips3').addClass('hide');
						if (data.status == '0000') {
							$('.pop-tips2').removeClass('hide');
							setTimeout(function(){
								location.href = "../itemManage.html";
							},2000);
						} else {
							$.Showmsg(data.message);
							return;
						}
		            },  
		            error: function(){
		            	$.Showmsg('网络不给力，请稍后重试'); 
		            }  
		        }); 
			}
		});
	})
</script>
<script>
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: false,
        spaceBetween: 0,
		loop:false
    });
</script>
<script src="../../js/merchant/shopping.common.js"></script>
<script src="../../dist/photoswipe.min.js"></script>
<script src="../../dist/photoswipe-ui-default.min.js"></script>
<script src="../../dist/index.js"></script>

</body>
</html>