<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">

<title>我的信息</title>
<link rel="stylesheet" href="../css/common.css?v=20150321">
</head>

<body class="bggrey pb200">
<section class="user-center sectionBox">
	<ul class="user-info borderbottomgrey">
    	<li class="user-img"><a class="disblock" href="../user/qryPersonInfo.do"><img class="left" src="${headimgurl }" /><span><span class="user-name">${nickName}
    		<br><!-- <span class="f12 grey">会员等级：<span class="rank mleft10">V3</span></span> -->
    		</span></span><img class="right" src="../images/arrow-right.png" /></a></li>
    </ul>
	<ul class="user-center-menu userCenter01">
    	<li class="wp50 h70 tap-nobg"><a class="disblock" href="../cart/qryBuyCar.do"><label class="order-icon04 on"><div class="item-num bgred">${buyCarProductCount}</div></label><br>购物车</a></li>
    	<li class="wp50 h70 tap-nobg"><a class="disblock" href="../order/qryOrderList.do"><label class="order-icon03"></label><br>我的订单</a></li>
    </ul>
</section>
<section class="divide-box bordertopgrey borderbottom"></section>
<section class="sectionBox borderbottom">
	<ul class="user-menu-list">
    	<li><a class="disblock" href="../address/qryDeliveryAddressList.do"><span class="right"><img src="../images/arrow-right.png" /></span>收货信息</a></li>
    	<li><a id="servers" class="disblock" href="#"><span class="right"><img src="../images/arrow-right.png" /></span>联系客服</a></li>
    </ul>
</section>
<section class="popup-box-main popupserver">
    <div class="sectionBox pop-server">
        <ul class="user-menu-list m0">
            <li><a class="disblock t-center blue" href="#">400-960-2228</a></li>
            <li><a class="disblock t-center blue" href="#">周一至周五 8:30-18:00（法定节假日除外）</a></li>
            <li><a id="returnServer" class="disblock t-center" href="#">取消</a></li>
        </ul>
    </div>
    <div class="bgblack"></div>
</section>
<script src="${resourcePathHttps}/commonjs/zepto.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
</script>
<script src="${resourcePathHttps}/commonjs/Touch.js"></script>
<script src="${resourcePathHttps}/commonjs/TouchSlide.1.1.js"></script>
<script src="../js/ebuy.common.js"></script>
</body>
</html>