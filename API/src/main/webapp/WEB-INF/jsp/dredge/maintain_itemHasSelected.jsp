<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<title>解放区到家服务</title>
<link rel="stylesheet" href="../css/shopping.common.css" type="text/css">
</head>

<body class="pos-relative bggrey">
<form class="inputform" action="myAppointment.html">
	<div class="shop-part03">    
	    <section class="sectionBox item-details-info pos-relative">
	        <div class="my-order-item single-item-wrap"> 
			    <c:if test="${empty resList}">
				    <div class="list-box displaybox bordertopgrey bggrey ptb20">
			            <div class="item-standard-name f14 boxflex01 t-center">没有选择耗材</div>
				    </div>
			    </c:if>	
			    <c:forEach items="${resList}" var="item">
			    	<section class="divide-box bordertopgrey"></section>
                    <a class="disblock" href="../ebuyNew/newEbuyproductDetail.html?readonly=true&productId=${item.id}">
		                <div class="displaybox order-info-box p00 bordertopgrey">
		                    <div class="item-info-img mleft10"><img src="${item.pic}<c:choose><c:when test="${fn:contains(item.pic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,w_46/format,jpg/interlace,1"></div>
			                <ul class="order-info-address boxflex01">
		                        <li>
		                        	<div class="icon-person master-info-name">${item.name}</div>
		                        </li>
		                    </ul>
			                <ul class="order-info-address t-right mright10">
		                        <%-- <li>
		                        	<div class="icon-person master-info-name">￥${item.price}</div>
		                        	<div class="icon-person master-info-name grey f12">x${item.quantity}</div>
		                        </li> --%>
		                        <li class="right mleft10">￥<span class="subPrice">${item.price * item.quantity}</span></li>
		                        <li class="right">
		                        	<div class="icon-person master-info-name grey f12">￥${item.price} x${item.quantity}</div>
		                        </li>
		                    </ul>
		                </div>
                    </a>
			    </c:forEach>
			    <c:if test="${!empty resList}">
					<section class="divide-box bordertopgrey"></section>
	                <div class="displaybox order-info-box p00 bordertopgrey">
		                <ul class="order-info-address boxflex01 mleft10">
	                        <li>
	                        	<div class="icon-person master-info-name">总金额</div>
	                        </li>
	                    </ul>
		                <ul class="order-info-address boxflex01 t-right mright10">
	                        <li>
	                        	<div class="icon-person master-info-name">￥<span id="totalItemPrice"></span></div>
	                        </li>
	                    </ul>
	                </div>
                </c:if>
           </div>
		    <div class="pbfooter bggrey bordertopgrey"></div>

			<c:if test="${canEdit}">
				<ul class="bottom-menu-box displaybox t-center order-to-pay">
					<li class="p00 wp50">
						<a id="moreItemsBtn" class="displaybox disblock" href="#">
							<input class="btn-submit btn-next noradius bordertbgrey red" type="button" name="button" value="选择更多耗材">
						</a>
					</li>
					<li class="p00 boxflex01 borderleft">
						<a id="editItemsBtn" class="displaybox disblock" href="#">
							<input class="btn-submit btn-next noradius bordertbgrey red" type="button" name="button" value="编辑">
						</a>
					</li>
				</ul>
			</c:if>
	    </section>
	</div>

</form>

<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/shoppingcart.common.js"></script>
<script>
$(function(){

	//计算商品总金额
	totalPriceCount();
	
	function totalPriceCount(){
		var totalItemPrice = 0;
		$(".order-info-box").each(function(){
			var subPrice = $(this).find('.subPrice').text();
			
			if(subPrice != undefined && subPrice != ''){
				totalItemPrice += subPrice*1;
			}
		})
		$("#totalItemPrice").text(totalItemPrice.toFixed(2));
	}
	
	//确认付款时将页面设置为只读
	if(location.search.indexOf('canEdit') > -1){
		$('.order-to-pay').hide();
	}

	//设置更多耗材链接
	document.getElementById("moreItemsBtn").setAttribute('href', '../dredge/toPage.html?page=maintain_itemSelect_fromindent&' + getOrderInfo());
	document.getElementById("editItemsBtn").setAttribute('href', '../dredge/viewSelfBuyProductList.html?operType=edit&' + getOrderInfo());
		
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

	//安卓
	if(isAndroid){
		//编辑耗材，完成按钮
		document.getElementById("itemHasSelectedBtn").onclick = function(){
			window.itemSelect.selectCompletedEvent();
		};
	}
	
	//ios
	if(isiOS){
		if(location.search.indexOf('killErrors') === -1){
			setupWebViewJavascriptBridge(function(bridge) {
				//编辑耗材，完成按钮
				document.getElementById("itemHasSelectedBtn").onclick = function(){
					bridge.callHandler('selectCompletedEvent', null, function(response) {});
				}
			});
		}
	}
	
    function setupWebViewJavascriptBridge(callback) {
        if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
        if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
        window.WVJBCallbacks = [callback];
        var WVJBIframe = document.createElement('iframe');
        WVJBIframe.style.display = 'none';
        WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
        document.documentElement.appendChild(WVJBIframe);
        setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0);
    }
	
});
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>