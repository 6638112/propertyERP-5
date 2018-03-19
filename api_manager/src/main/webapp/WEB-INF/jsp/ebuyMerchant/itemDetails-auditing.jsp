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
<link rel="stylesheet" href="../css/merchant/shopping.common.css" type="text/css">
<link rel="stylesheet" href="../css/merchant/swiper.css" type="text/css">
<link rel="stylesheet" href="../dist/photoswipe.css">
<link rel="stylesheet" href="../dist/default-skin/default-skin.css">

<style>
.my-gallery {   width: 100%; float: left; }
.my-gallery img { width: auto;}
.my-gallery figure { display: block; float: left; margin:0; overflow: hidden;}
.my-gallery figcaption { display: none; }

/*商品详情图片*/
.my-gallery.desc-img img { max-width: 100%; width: 90px; height:90px; }
.my-gallery.desc-img figure { display: block; float: left; margin: 0 10px 10px 0; width: 90px; height:90px; }
</style>

</head>

<body>
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock mleft15 left" href="javascript:history.back();"><img class="back-arrow" src="../images/merchant/back-arrow.png" /></a>
    <div class="header-title">商品详情</div>
</header>

<section class="sectionBox">
    <!-- Swiper -->
    <div class="swiper-container pos-relative">
    	<div class="item-status-text bgorange">商品正在审核</div>
        <div class="swiper-wrapper bgdarkgrey my-gallery">
        	<c:forEach var="prodPic" items="${prodDetail.prodPicList}" >
        		<figure class="swiper-slide" itemprop="associatedMedia" itemscope>
	              <a href="${prodPic.url}<c:choose><c:when test="${fn:contains(prodPic.url, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/format,jpg/interlace,1/quality,q_80" itemprop="contentUrl" data-size="640x540">
	                  <img src="${prodPic.url}<c:choose><c:when test="${fn:contains(prodPic.url, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,limit_0,w_640,h_467/format,jpg/interlace,1/quality,q_80" itemprop="thumbnail" alt="Image description" />
	              </a>
	            </figure>
        	</c:forEach>
        	<c:if test="${empty prodDetail.prodPicList}">
        		<figure class="swiper-slide" itemprop="associatedMedia" itemscope>
	              <a href="${prodDetail.picBase}" itemprop="contentUrl" data-size="640x540">
	                  <img src="${prodDetail.picBase}" itemprop="thumbnail" alt="Image description" />
	              </a>
	            </figure>
        	</c:if>
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

<section class="sectionBox padding-menu pos-relative">
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list">
        <li class="borderbottomgrey">
        	<div class="f16 height36">${prodDetail.prodName}</div>
        </li>
        <li>
        	<div class="f16 height36"><c:forEach var="prodType" items="${prodTypeList}"><c:if test="${prodDetail.productTypeId == prodType.id}">${prodType.typeName}</c:if></c:forEach></div>
        </li>
    </ul>
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list">
        <li class="borderbottomgrey displaybox">
            <div class="item-standard-name f16 boxflex01">价格</div>
            <div class="boxflex01"><div class="f16 height36 t-right">¥${prodDetail.priceDiscount}</div></div>
        </li>
        <li class="displaybox">
            <div class="item-standard-name f16 boxflex01">库存</div>
            <div class="boxflex01"><div class="f16 height36 t-right">${prodDetail.leftCount}</div></div>
        </li>
        <%--
        <li class="displaybox">
            <div class="item-standard-name f16 boxflex01">规格</div>
            <div class="boxflex01"><div class="f16 height36 t-right">1L/桶</div></div>
        </li>
         --%>
    </ul>
    <section class="sectionBox password-mind-box bordertbgrey">
        <div class="mleft15 grey">选填</div>
    </section>
    <ul class="register-list">
        <li class="displaybox">
            <div class="item-standard-name f16 boxflex01">市场参考价</div>
            <div class="boxflex01"><div class="f16 height36 t-right">¥${prodDetail.price}</div></div>
        </li>
    </ul>
    <section class="sectionBox password-mind-box bordertbgrey">
        <div class="mleft15 grey">商品参数</div>
    </section>
    <ul class="register-list">
    	<c:forEach var="prodParam" items="${prodDetail.prodParamList}" varStatus="status">
    		<li class="borderbottomgrey displaybox">
	            <div class="item-standard-name f16 boxflex01">${prodParam.propName}</div>
	            <div class="boxflex01"><div class="f16 height36 t-right">${prodParam.propValue}</div></div>
	        </li>
 		</c:forEach>
 		<c:if test="${empty prodDetail.prodParamList}">
	        <li>
	        	<div class="height36 f16">暂无商品参数</div>
	        </li> 			
 		</c:if>
    <%--
        <li class="borderbottomgrey displaybox">
            <div class="item-standard-name f16 boxflex01">颜色</div>
            <div class="boxflex01"><div class="f16 height36 t-right">蓝色</div></div>
        </li>
        <li class="displaybox">
            <div class="item-standard-name f16 boxflex01">尺寸</div>
            <div class="boxflex01"><div class="f16 height36 t-right">20mm*30mm*50mm</div></div>
        </li>
         --%>
    </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
        <div class="mleft15 grey">商品详情图片</div>
    </section>
   
    <section class="list-box bgwhite image-view">
        <div class="my-gallery desc-img" itemscope>
        	<c:forEach var="introducePic" items="${introducePicList}" varStatus="status">
        		<figure itemprop="associatedMedia" itemscope>
		              <a href="<%=OmsSysParamManager.getImageServerUrl("/productPic/") %>/productPic/${introducePic.urlBig}" data-size="640x540">
		                  <img src="<%=OmsSysParamManager.getImageServerUrl("/productPic/") %>/productPic/${introducePic.urlBig}<c:choose><c:when test="${fn:contains(introducePic.urlBig, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_140,h_140/format,jpg/interlace,1/quality,q_80" itemprop="thumbnail" alt="Image description" />
		              </a>
             	<figcaption itemprop="caption description">${introducePic.textDesc}</figcaption>
            </figure>
        	</c:forEach>
        	<c:if test="${empty introducePicList}">
	        	<li>
		        	<div class="height36 f16">暂无商品图片描述</div>
		        </li> 	
        	</c:if>
        </div>
    </section>
    
    <section class="divide-box bordertbgrey"></section>
</section>

<div class="shape-box dsn"></div>

<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/swiper.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script src="../js/merchant/resetSwaperSize.js"></script>

<script>
	$(function(){
		new FastClick(document.body);
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
<script src="../js/merchant/shopping.common.js"></script>
<script src="../dist/photoswipe.min.js"></script>
<script src="../dist/photoswipe-ui-default.min.js"></script>
<script src="../dist/index.js"></script>
</body>
</html>