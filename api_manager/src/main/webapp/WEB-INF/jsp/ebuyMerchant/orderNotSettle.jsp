<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<base href="<%=basePath%>/"/>
	<meta http-equiv="cleartype" content="on">
	<meta name="description" content="">
	<meta name="HandheldFriendly" content="True">
	<meta name="MobileOptimized" content="320">
	<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
	<link rel="dns-prefetch" href="//jiefangqu.com">

	<title>待结算订单</title>
	<link rel="stylesheet" href="css/merchant/shopping.common.css">
</head>

<body class="bggrey pos-relative">
<section id="wrapBox" class="sectionBox pos-relative minheight100">
	<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
		<header class="sectionBox fantasia-header order-top-bg">
			<a class="disblock mleft15 left white w80" href="ebuyMerchant/settleCenter.html"><img class="back-arrow left" src="images/back-arrow.png" /> <span class="mleft10">返回</span></a>
			<div class="header-title">待结算订单</div>
		</header>
		<section class="sectionBox">

			<!--有订单状态，显示内容-->
			<div class="cash-out-order-list-box borderbottomgrey" data-hasnext="true">
				<a class="cash-out-order-single hide" href="javascript:void(0)">
					<ul class="cash-out-order-list mleft15 borderbottomgrey">
						<li class="mtop5 f14 icon-order-list"><span class="left"><span class="deliveryAddress">星海名城1栋105室</span></span><span class="right red f20">￥<span class="deliveryOrderTotalAmount">60.00</span></span></li>
						<li class="f12 grey"><span class="left">共<span class="productQty">4</span>件商品</span><span class="right"></span></li>
						<li class="f12 grey">下单时间：<span class="deliveryOrderAddTime">2015-12-23</span></li>
					</ul>
				</a>
			</div>

			<section class="divide-box pb56 noOrderList hide"></section>
			<ul id="triggerVerifyCode" class="bottom-menu-box displaybox t-center noOrderList hide">
				<li class="boxflex01 p00"><input class="btn-submit btn-next noradius bordertbgrey btnSubmit red" type="button" name="button" value="结算"></li>
			</ul>

		</section>
	</section>

	<section id="popBox" class="sectionBox wrap-bg hide"></section>
</section>
<div class="sectionBox loading grey hide"><img src="images/merchant/loading01.gif" /> 加载中…</div>
<section class="sectionBox bggrey big-text-tips-box toppercent40 hide">
	<div class="big-text-tips grey t-center">暂无可结算订单</div>
</section>
<div id="urlTogo" class="hide" data-url="ebuyMerchant/toPage.html?page=settlePassword&from=orderNotSettle"></div>
<c:if test="${hasBankAccount }"><div id="hasBankAccount"></div></c:if>
<c:if test="${!hasBankAccount }"><div id="noBankAccount"></div></c:if>
<script src="js/merchant/jquery-1.11.2.min.js"></script>
<script src="js/merchant/fastclick.js"></script>
<script src="js/merchant/Validform_v5.3.2.js"></script>
<script src="js/merchant/getorder.pages.js?v20161009"></script>
<script>
	var tipsInfo = {
		tipsbankcardBinding: {
			icon: 'images/icon-bankcard01.png',
			tipsTxt: '<br>提款前需要绑定一张<br>您本人的银行卡',
			cancelBtn: '稍后再说',
			nextStepUrl: 'javascript:void(0)',
			nextStepTxt: '绑定银行卡'
		},
		triggerVerifyCode: {
			icon: 'images/star.png',
			tipsTxt: '<br>为保障您的账户安全，解放区将<br>向您尾号' + '${ownerPhone}'.substring(7) + '的手机号<br>发送验证码以确认是您本人操作',
			cancelBtn: '取消',
			nextStepUrl: 'javascript:void(0)',
			nextStepTxt: '同意',
			phoneNum: '${ownerPhone}'
		},
		bankInfo: {
			settleAcount: '${totalAmount}',
			bankCardName: '${bankName}',
			bankCardNum: '${bankAccount}'
		}
	}
</script>
<script src="js/merchant/getTipsBox.js"></script>
</body>
</html>