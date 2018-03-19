<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

<title>店主认证</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css">
<link rel="stylesheet" href="../dist/default-skin/default-skin.css">
<link rel="stylesheet" href="../dist/photoswipe.css">  
<style>
.my-gallery {   width: 100%; float: left; }
.my-gallery img { max-width: 100%; width: 90px; height:90px; }
.my-gallery figure { display: block; float: left; margin: 0 10px 0 0; width: 90px; height:90px; }
.my-gallery figcaption { display: none; }
</style>
</head>

<body class="bggrey">
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock mleft15 left" href="setting.html"><img class="back-arrow" src="../images/merchant/back-arrow.png" /></a>
    <div class="header-title">店主认证</div>
</header>
<c:if test="${merchantSupply.ownerAuditStatus != 1}">
	<section class="sectionBox item-status-desc item-details-info bgorange">
			<c:if test="${merchantSupply.ownerAuditStatus == 0}">
				审核中
			</c:if>
			<c:if test="${merchantSupply.ownerAuditStatus == 2}">
				审核失败原因：${merchantSupply.ownerAuditDesc}
			</c:if>
	</section>
</c:if>
<section class="sectionBox">
    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <div class="displaybox">
            <div class="item-standard-name height36 f16 boxflex01">店主姓名</div>
            <div class="boxflex01 f16 t-right grey">${merchantSupply.userName}</div>
        </div>
    </div>
    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <div class="displaybox">
            <div class="item-standard-name height36 f16 boxflex01">联系方式</div>
            <div class="boxflex01 f16 t-right grey">${merchantSupply.ownerPhone}</div>
        </div>
    </div>
    <section class="sectionBox password-mind-box bordertbgrey">
        <div class="mleft15">身份证件</div>
    </section>
    <section class="list-box borderbottomgrey bgwhite image-view">
        <div class="my-gallery" data-pswp-uid="1">
        	<c:forEach var="licensePic" items="${picList}">
	              <figure itemprop="associatedMedia" itemscope="">
		              <a data-size="640x540" href="<%=OmsSysParamManager.getImageServerUrl("/ebuyStorePic/") %>/ebuyStorePic/${licensePic}"  >
		 	                 <img alt="Image description" itemprop="thumbnail" src="<%=OmsSysParamManager.getImageServerUrl("/ebuyStorePic/") %>/ebuyStorePic/${licensePic}" />
		              </a>
	              </figure>
        	</c:forEach>
        </div>
    </section>
	<c:if test="${merchantSupply.ownerAuditStatus !=1}">
	    <section class="divide-box pb56"></section>
	    <ul class="bottom-menu-box displaybox t-center">
	        <li class="p00"><input class="btn-submit btn-next noradius bordertbgrey btnSubmit red" type="button" name="button" value="修改" onclick="settingNew();"></li>
	    </ul>
		</section>
	</c:if>
    <!-- Root element of PhotoSwipe. Must have class pswp. -->
    <div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
    
        <!-- Background of PhotoSwipe. 
             It's a separate element, as animating opacity is faster than rgba(). -->
        <div class="pswp__bg"></div>
    
        <!-- Slides wrapper with overflow:hidden. -->
        <div class="pswp__scroll-wrap">
    
            <!-- Container that holds slides. PhotoSwipe keeps only 3 slides in DOM to save memory. -->
            <!-- don't modify these 3 pswp__item elements, data is added later on. -->
            <div class="pswp__container">
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
            </div>
    
            <!-- Default (PhotoSwipeUI_Default) interface on top of sliding area. Can be changed. -->
            <div class="pswp__ui pswp__ui--hidden">
    
                    <div class="pswp__top-bar">
                    
                        <!--  Controls are self-explanatory. Order can be changed. -->
                    
                        <div class="pswp__counter"></div>
                    
                        <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
                    
                        <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
                    
                        <!-- Preloader demo http://codepen.io/dimsemenov/pen/yyBWoR -->
                        <!-- element will get class pswp__preloader--active when preloader is running -->
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
                    
                    <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)">
                    </button>
                    
                    <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)">
                    </button>
                    
                    <div class="pswp__caption">
                        <div class="pswp__caption__center"></div>
                    </div>
    
              </div>
    
        </div>
    
    </div>

</section>
<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/photoswipe.min.js"></script>
<script src="../dist/photoswipe-ui-default.min.js"></script>  
<script src="../js/merchant/shopping.common.js"></script>
<script src="../dist/index.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script>
$(function(){
	new FastClick(document.body);
})

function settingNew(){
	location.href="<%=basePath%>/ebuyMerchant/settingChecknew.html"; 
}
</script>


</body>
</html>