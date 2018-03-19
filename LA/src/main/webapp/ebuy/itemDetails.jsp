<%@page import="com.cnfantasia.wl.wechat.util.product.ProductSpecUniqueUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">

<title>商品详情</title>
<link rel="stylesheet" href="../css/common.css?V20170907b">
</head>

<body>
<section id="slideBox" data-id="${product.id }" class="slideBox" style="overflow:hidden">
    <div class="bd noborder itemDetails-img">
        <ul>
        	<c:forEach var="ebuyProductPic" items="${product.picBigDescs}">
	            <li class="bgloading">
	            	<img class="disblock" src="${ebuyProductPic }<c:choose><c:when test="${fn:contains(ebuyProductPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_560,h_473,limit_0/format,jpg/interlace,1/quality,q_95" />
	            </li>
   			</c:forEach>
            <%-- <li class="bgloading"><img class="disblock" src="${product.picBase }<c:choose><c:when test="${fn:contains(product.picBase, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_560,h_473,limit_0/format,jpg/interlace,1/quality,q_90" /></li> --%>
        </ul>
    </div>
    <div class="hd">
        <ul></ul>
    </div>
</section>
<section class="sectionBox">
   <div class="info pb0">
        <div class="f16 bold mtop10 p010" style="line-height:20px; margin-top:10px">${product.name }</div></a>
        <div class="message p010">
            <span>${product.desc}</span>
        </div>
        <div class="special bordertopgrey mtop10">
        	<div class="market-price p010 f12">原价：￥${product.price}</div>
            <div class="hot-price f16 p010 mtop5">精选价：<span class="red bold">￥${product.priceDiscount }</span></div>
            <div class="p010 grey mtop5">${product.defaultDeliveryName }</div>
            <c:if test="${not empty product.sosName }">
            	<div class="p010 grey mtop5">来自：
            		<a <c:if test="${not empty product.sosIntroduceUrl }"> class="blue" href="${product.sosIntroduceUrl }"</c:if>
            	    	<c:if test="${empty product.sosIntroduceUrl }"> class="grey" href="javascript:void(0)"</c:if> >${product.sosName }</a></div>
            </c:if>
            <div class="p010 grey mtop5">${themeAdDesc }</div>
        </div>
        <div class="item-standard readonly-box">
            <div>
	            <div class="number disbox p010">
		            <div class="name grey">数量</div>
		            <div class="btn-num btnReduce border-left-radius">-</div>
		            <input class="input-normal w80 itemNum border-black" maxlength="3" type="text" value="1" />
		            <div class="btn-num btnAdd border-right-radius">+</div>
	            </div>
        	</div>
        </div>
        
        <c:if test="${product.filmStatus eq 1}">
			<section class="divide-box borderbottomgrey"></section>
		    <div class="list-box bgwhite">
		        <a class="displaybox" href="${product.filmLookUrl}">
		            <div class="item-standard-name height36 f16 boxflex01">支持在线订座的影院</div>
		            <div class="box-arrow t-right grey"></div>
		        </a>
		    </div>
			<section class="divide-box bordertbgrey"></section>
		    <div class="list-box bgwhite borderbottomgrey">
		        <a class="displaybox" href="${product.filmPayUrl}">
		            <div class="item-standard-name height36 f16 boxflex01">在线订座<span class="bgred icon-new">new</span></div>
		            <div class="box-arrow t-right grey"></div>
		        </a>
		    </div>
	    </c:if>
		<section class="divide-box"></section>
        <ul class="item-price f16 borderbottom" style="padding:12px 10px;">
            <li class="left"><img style="width:15px; vertical-align:middle; margin-top:-2px;" src="../images/phone-icon.png" /> 客服电话</li>
            <li class="right"><a href="tel:0755-86713324">0755-86713324</a></li>
        </ul>
    </div>
</section>           
<section class="divide-box"></section>
<section id="tabBox" class="tabBox">
    <div class="hd tab-head borderbottomgrey">
        <ul>
            <li class="wp50 on"><a href="javascript:void(0)">介绍</a></li>
            <li class="wp50"><a href="javascript:void(0)">参数</a></li>
        </ul>
    </div>
    <div class="bd" id="tabBox-bd">  
        <div class="con bgwhite">
            <div id="tabOne" class="p010">
				<div class="info pt10 lineheight0" >
               	   <c:choose>
               			<c:when test="${not empty product.ebuyProductIntroducePicBigList}">
               				<c:forEach items="${product.ebuyProductIntroducePicBigList}" var="tmpPic">
			                   <img class="item-pic-big" src="${tmpPic}<c:choose><c:when test="${fn:contains(tmpPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,w_560/format,jpg/quality,q_95/interlace,1" />
			               </c:forEach>
               			</c:when>
               			<c:otherwise>
               				<div class="img-desc-text-box t-center">该商品暂无图文介绍</div>
               			</c:otherwise>
               		</c:choose>
                </div>
            </div>
        </div>
        <div class="con bgwhite" style="display:none">
            <div id="tabTwo" class="p010">
               <div class="info pt10">
               		<c:choose>
               			<c:when test="${not empty ppList}">
               				<table class="bordered grey">
		                    	<c:forEach items="${ppList}" var="pp"> 
				                     <tr>
				                        <td>${pp.tPropName }</td>
				                        <td>${pp.tPropValue }</td>
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
<div id="gotop" class="go-top" style="display:none;"><img src="../images/icon-gotop.png" /><br/>顶部</div>
<c:if test="${subscribe==0 }">
	<section class="sectionBox" id="erweima"><img src="../images/erweima2.jpg" /></section>
</c:if>

<section class="sectionBox readonly-box">
	<section class="pbfooter"></section>
	<div class="exchang-fixed displaybox">
	    <div class="shopping-cart bggradient">
		    <a href="../cart/qryBuyCar.do">
			    <div id="cartNum" class="item-num bgred">${productCount.productCount }</div>
			    <img src="../images/shoppingcart-black.png" />
		    </a>
	    </div>
	    <div class="btn-fixed boxflex01">
	        <div id="btnCart" class="btn-cart wp50 left"><a class="bgred" href="javascript:void(0)">加入购物车</a></div>
	        <div id="fromStoreBtn" class="btn-cart wp50 left"><a id="buyNowBtn" class="bgorange" href="../cart/buyRightNow.do?ptId=${product.id }&jfqsource=buyRightNow">立即购买</a></div>
	    </div>
	</div>
</section>
<section class="pop-tips dsn">
	<div class="pop-tips-text">请选择</div>
</section>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script> 
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="${resourcePathHttps}/commonjs/requestAnimationFrame.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.flying.js"></script>
<script src="${resourcePathHttps}/commonjs/TouchSlide.1.1.js"></script>
<script src="../js/ebuy.common.js"></script>
<script src="../js/itemdetail.page.js?20170712"></script>
<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="../js/wx.share.info.js"></script>
<script type="text/javascript">
$(function(){

    //轻应用分享
	setShareInfo({
		title: '${product.name }',		// 分享标题
		desc: '抢购价：￥${product.priceDiscount }',		// 分享描述
		imgIcon: '${miniPicBase }', // 分享图标
	});
    
    //体验店进来时隐藏立即抢购按钮
    /*if(location.search.indexOf('jfqstore') === -1){
    	$('#fromStoreBtn').removeClass('dsn');
    	$('#btnCart').addClass('wp50');
    } */
    
});

</script>
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