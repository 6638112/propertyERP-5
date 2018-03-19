<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>解放区消费券</title>
<link rel="stylesheet" href="../css/prize.common.css">
</head>

<body>
<section class="sectionBox">
	<ul class="jfq-quan">
    	<li class="quan-money">￥<%=request.getParameter("hbAmount").substring(0, request.getParameter("hbAmount").indexOf(".")) %></li>
        <li class="quan-date">有效期至：
          <%
            String expiryTimeLong = request.getParameter("expiryTimeLong");
			Date expiryTime = new java.util.Date(Long.parseLong(expiryTimeLong));
			out.write(new SimpleDateFormat("yyyy-MM-dd").format(expiryTime)); 
		  %>
        </li>
    </ul>
</section>
<section class="sectionBox">
	<div class="common-title f14 bordertopgrey borderbottomgrey">使用说明</div>
</section>
<section class="sectionBox">
	<div class="common-info f14">1. 消费券可在解放区超市购物；<br/>2. 每次购物限使用一张消费券；<br/>3. 消费券必须在有效期内使用，过期作废；<br/>4. 消费券以一定比例抵扣订单金额，不设找零；</div>
</section>
<section class="sectionBox">
	<div class="common-title f14 bordertopgrey borderbottomgrey">联系我们</div>
</section>
<section class="sectionBox">
    <ul class="use-date f16 disbox">
        <li>客服电话</li>
        <li class="t-right"><a class="darkblue" href="tel:400-960-2228">400-960-2228</a></li>
    </ul>
    <ul class="use-date f16 disbox user-qq bordertopgrey">
        <li>用户Q群</li>
        <li class="t-right darkblue">384432710</li>
    </ul>
</section>
<section class="divide-box bordertopgrey borderbottomgrey" style="padding-bottom:56px;"></section>
<section class="sectionBox fixed-info">
    <a class="fixed-text bordertopgrey" href="#" onclick="toShopping();">使用消费券购物</a>
</section>

<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
	
	function toShopping() {

		//判断安卓ios系统
		var u = navigator.userAgent;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

		//安卓
		if (isAndroid) {
			window.shopping.toShoppingBtn('param1');
		}

		//ios
		if (isiOS) {
			function toShoppingBtn(param01, param02) {
				document.location = "jfq://" + param01 + ":/" + param02;
			}

			toShoppingBtn('toShopping', 'param');
		}
	}
</script>
<script src="../js/prize.common.js?0706"></script>
</body>
</html>