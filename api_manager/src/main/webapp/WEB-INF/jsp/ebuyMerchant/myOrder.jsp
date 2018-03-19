<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>我的订单</title>
<link rel="stylesheet" href="../css/merchant/animate.css">
<link rel="stylesheet" href="../css/merchant/shopping.common.css?20170622">
</head>

<body class="pos-relative">
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock p010 right" href="myOrderSearch.html"><img class="search-icon" src="../images/merchant/icons-shop-search-small.png" /></a>
    <div class="header-title">订单管理</div>
</header>
<div class="my-order-item hide">
    <div class="displaybox order-title-info">
         <div class="order-item-check-box freight-check-icon mright0 mleft10 hide"></div>
         <%--<div class="order-title-num">02</div> --%>
         <div class="order-title-time boxflex01 grey mleft15"><span class="icon-orderNo-text">2015080111112220</span><br><span class="icon-time-text">2分钟前</span></div>
         <div class="order-title-buff mright15 orange"><img class="order-title-buff-icon" src="../images/merchant/icons-shop-clock.png" /> <span class="order-title-buff-text">等待处理</span></div>
    </div>
    <div class="displaybox ptb10 bordertopgrey">
         <div class="boxflex01 f14 pleft20 lineheight140">解放号：<span class="app-number"></span><br>手机号：<span class="phone-number"></span></div>
    </div>
    <div class="displaybox order-info-box bordertopgrey">
         <ul class="order-info-address boxflex01">
             <li class="icon-address"><span class="icon-address-text">星海名城1期A栋5008室</span><br><%-- <span class="orange f12">距您 1.2km</span>--%></li>
             <li class="icon-person">熊猫爸爸</li>
         </ul>
         <div class="order-info-phone-call"><a class="icon-tel-text" href="tel:400-960-2228"><img src="../images/merchant/icon-phone-call.png" /></a></div>
    </div>
    
    <div class="item-list-box bggrey bordertopgrey">
         <ul class="item-price f14 borderbottomgrey">
             <li class="left">共 <span class="total-item-num">2</span> 件商品</li>
             <li class="right"><span>合计: ¥<span class="total-item-price">30.5</span>(含运费: ¥<span class="deliv-item-price">5.0</span>)</span></li>
         </ul>
         <div class="item-list-check-btn-box bgwhite displaybox"><input class="item-list-check-btn bgred boxflex01" type="button" value="确认发货" /></div>
    </div>
    <%--<div class="item-list-arrow-box bordertbgrey"><div class="item-list-arrow animated rotateIn"></div></div>--%>
    <section class="divide-box bordertbgrey divideBox01"></section>
</div>
<section id="tabBox" class="tabBox pos-relative">
	<section id="copyTipsBox" class="sectionBox wrap-bg dsn">
		<div class="tips-box" style="top: 50%; margin-top: -75px;">
			<div class="t-center ptb20 borderbottomgrey t-center">
				<div class="wp90 margin_auto copy-title">订单信息已复制</div>
				<div class="wp90 margin_auto mtop10 copy-info dsn" style="word-break: break-all;"></div>
				<div class="wp90 margin_auto mtop10">您可选择微信好友进行发送</div>
			</div>
			<ul class="displaybox">
				<li id="checkCopyBtn" class="boxflex01 ptb15 t-center red"><a class="disblock red" href="#">确认</a></li>
			</ul>	
		</div>
	</section>
    <div class="div-copy-order">
	    <div class="list-box freight-setting-list copy-order-info-fixed">
	        <div>
	        	<div class="copy-order-info displaybox bgwhiteopacity blue height28 p05 border-radius2 box-shadow">
		            <div class="order-all-check freight-check-icon"></div>
		            <div class="mright5">选择订单</div>
	            </div>
	            <div id="oncopy" class="btn-share-order-info height28 t-center bggreenopacity white mtop5 p05 border-radius2 box-shadow pointer" data-clipboard-text="test">分享到微信</div>
	        </div>
	    </div>
    </div>
    <div class="hd tab-head">
        <ul class="order-tab">
            <li id="orderTab01" class="wp50 tap-nobg order-tab-title on" data-page="1" data-next="true" status="1"><a href="javascript:void(0)">待处理</a></li>
            <li id="orderTab02" class="wp50 tap-nobg order-tab-title off" data-page="1" data-next="true" status="2"><a href="javascript:void(0)">已处理</a></li>
        </ul>
    </div>
    <section class="divide-box bordertbgrey"></section>
    <div class="bd my-order" id="tabBox-bd">
        <div class="con">
            <div id="tabOne">
                <div class="myOrderList bgwhite">
                </div>
            </div>
        </div>
        <div class="con">
            <div id="tabTwo">
                <div class="myOrderList bgwhite">
                   
                </div>
            </div>
        </div>
    </div>
    <ul class="bottom-menu-box displaybox t-center bordertopgrey">
    	<li class="on"><a href="myOrder.html"><span class="menu-icon01"></span>我的订单</a></li>
    	<li><a href="itemManage.html"><span class="menu-icon02"></span>商品管理</a></li>
    	<!--<li><a href="#"><span class="menu-icon03"></span>账务中心</a></li>-->
    	<li><a href="setting.html"><span class="menu-icon04"></span>店铺设置</a></li>
		<li class="boxflex01"><a href="settleCenter.html"><span class="menu-icon03"></span>结算中心</a></li>
    </ul>
</section>

<div class="displaybox item-list-info borderbottomgrey hide">
    <div class="item-list-img mright10"><img class="single-item-image" src="../images/merchant/good-small-img001.jpg" /></div>
    <div class="item-list-title boxflex01 word-break f16">花生油 农家自炸非转基因食用新鲜花生油 农家自炸非转基因食用新鲜花生油</div>
    <div class="item-list-price mright15"><span class="f14">￥<span class="single-item-price">349.00</span></span><br/><span class="grey">x<span class="single-item-num">2</span></span></div>
</div>
<div class="sectionBox loading grey hide"><img src="../images/merchant/loading01.gif" /> 加载中…</div>
<section class="pop-tips dsn">
	<div class="pop-tips-text">请选择</div>
</section>

<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script src="../js/clipboard.min.js"></script>
<script src="../js/merchant/TouchSlide.1.1.js"></script>
<script src="../js/merchant/Validform_v5.3.2.js"></script>
<script src="../js/merchant/shopping.common.js"></script>
<script src="../js/merchant/page.orderlist.js"></script>
</body>
</html>