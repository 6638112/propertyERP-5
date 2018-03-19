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
<form class="inputform">
	<div class="shop-part03">    
	    <section class="sectionBox item-details-info pos-relative">
	        <div class="my-order-item">
				<section class="divide-box bordertopgrey"></section>
	        	<div class="single-item-wrap overhidden hide">
	                <div class="displaybox order-info-box p00 bordertopgrey overhidden">
	                    <div class="item-check-box single-check"></div >
	                    <div class="item-info-img"><img src="images/1ba.jpg"></div>
		                <ul class="order-info-address boxflex01">
		                    <a class="disblock item-url" href="#">
		                        <li>
		                        	<div class="single-item-name">飞利浦节能灯（11瓦）</div>
		                        	<div class="mtop5">￥<span class="single-item-price">13.50</span></div>
		                        </li>
		                    </a>
	                    </ul>
	                	<div class="displaybox mright10 mleft10"><div class="btn-num btnReduce">-</div><input id="itemNum01" class="input-normal w40 itemNum" type="text" name="itemSelectNum" value="1" maxlength="3" /><div class="btn-num btnAdd">+</div></div>
	                </div>
					<section class="divide-box bordertopgrey"></section>
                </div>
           </div>
		    <div class="pbfooter bggrey bordertopgrey"></div>
	        <ul class="bottom-menu-box displaybox t-center">
	            <li class="p00 boxflex01"><input id="itemCancelBtn" class="btn-submit btn-next noradius bordertbgrey red" type="button" name="button" value="取消"></li>
	            <li class="p00 boxflex01 borderleft"><input id="itemHasSelectedBtn" class="btn-submit btn-next noradius bordertbgrey white bgred" type="button" name="button" value="选好了" onclick="sendItemSelectedNumAndPrice();"></li>
	        </ul>
	    </section>
	</div>

</form>
<div class="sectionBox loading grey bordertopgrey mtop15 hide"><img src="../images/loading01.gif" /> 加载中…</div>

<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/Validform_v5.3.2.js"></script>
<script src="../js/getitem.pages.js"></script>
<script src="../js/shoppingcart.fromorder.js"></script>
<script src="../js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
		
		//判断安卓ios系统
		var u = navigator.userAgent, app = navigator.appVersion;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		
		//安卓
		if(isAndroid){
			//耗材选好了按钮
			document.getElementById("itemHasSelectedBtn").onclick = function(){
				window.itemSelect.itemSelectedInfo(JSON.stringify(sendItemSelectedNumAndPrice()));
			};
			
			//取消按钮
			document.getElementById("itemCancelBtn").onclick = function(){
				window.itemSelect.itemCancelEvent();
			};
			
		}
		
		//ios
		if(isiOS){
			setupWebViewJavascriptBridge(function(bridge) {
				//耗材选好了按钮
				document.getElementById("itemHasSelectedBtn").onclick = function(){
					bridge.callHandler('itemSelectedInfo', sendItemSelectedNumAndPrice(), function(response) {});
				}

				//取消按钮
				document.getElementById("itemCancelBtn").onclick = function(){
					bridge.callHandler('itemCancelEvent', null, function(response) {});
				}
			});
		}
		
	});
	
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

	//获取已选中耗材数量及价格，计算总额
	function sendItemSelectedNumAndPrice(){
		var itemInfo = {
			totalItemSelectedInfo : [],
			selectedNum : 0,
			selectedTotalPrice : 0
		}
		
		$('.order-info-box').each(function(){
			
			var singleItemInfo = {};
			var $me = $(this);
			
			if($me.find('.item-check-box.single-check').hasClass('item-checked')){
				
				var thisPrice = $me.find('.single-item-price').text()*1;
				var thisNum = $me.find('.itemNum').val()*1;
				
				var thisId = $me.attr('data-itemid');
				var thisName = $me.find('.single-item-name').text();
				
				itemInfo.selectedNum += thisNum;
				itemInfo.selectedTotalPrice += thisPrice*thisNum;
				
				singleItemInfo.productId = thisId;
				singleItemInfo.productQty = thisNum;
				singleItemInfo.productName = thisName;
				singleItemInfo.productPrice = thisPrice;
				itemInfo.totalItemSelectedInfo.push(singleItemInfo);
			}
		});
		
		return itemInfo;
	}
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>