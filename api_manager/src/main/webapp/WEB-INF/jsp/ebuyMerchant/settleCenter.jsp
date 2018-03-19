<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
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

<title>结算中心</title>
<link rel="stylesheet" href="css/merchant/shopping.common.css">
</head>

<body class="bggrey">
<section id="wrapBox" class="sectionBox pos-relative minheight100">
	<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
		<header class="sectionBox fantasia-header bgred">
		    <div class="header-title white">结算中心</div>
		</header>
		<section class="sectionBox">
			<ul class="p15 bgredgradual white t-center">
				<li class="mtop5 f14 opacity75">可结算金额(元)</li>
				<li class="myAmountPrice">${totalAmount}</li>
				<li class="mtop5 f14 opacity75">共  ${applyTotal} 笔订单</li>
				<li id="cashOutBtn" class="cash-out-btn f18 bordertopgrey" data-ownerAuditStatus="${ownerAuditStatus }">立即结算</li>
			</ul>
		    <div class="bgwhite displaybox t-center f18 borderbottom">
	            <div id="redDotBox" class="boxflex01 item-standard-name ptb20 pos-relative">
	            	<c:if test="${hasRedPoint }"><div id="redDot"></div></c:if>
	            	<a class="disblock heightp100" <c:if test="${ownerAuditStatus == 1}">href="ebuyMerchant/settleCenter/settleRecords.html"</c:if>>结算记录
						<div class="f14 grey mtop10">结算中、已结算记录</div>
					</a>
			    </div>
	            <div <c:if test="${ownerAuditStatus == 1}">id="triggerVerifyCode"</c:if> class="boxflex01 ptb20 borderleft btn-edit">银行卡
					<c:if test="${!hasBankAccount}">
						<div class="f14 grey mtop10">暂无银行卡信息</div>
					</c:if>
					<c:if test="${hasBankAccount}">
						<div class="f14 grey mtop10">${bankAccount}</div>
					</c:if>
				</div>
		    </div>
		    
		    <section class="pbfooter bggrey"></section>
		    <ul class="bottom-menu-box displaybox t-center bordertopgrey">
		    	<li class="boxflex01"><a href="ebuyMerchant/myOrder.html"><span class="menu-icon01"></span>我的订单</a></li>
		    	<li class="boxflex01"><a href="ebuyMerchant/itemManage.html"><span class="menu-icon02"></span>商品管理</a></li>
		    	<li class="boxflex01"><a href="ebuyMerchant/setting.html"><span class="menu-icon04"></span>店铺设置</a></li>
		    	<li class="boxflex01 on"><a href="ebuyMerchant/settleCenter.html"><span class="menu-icon03"></span>结算中心</a></li>
		    </ul>
		
		</section>
	</section>
	
	<section id="popBox" class="sectionBox wrap-bg hide"></section>
</section>
<c:if test="${!hasBankAccount }"><div id="noBankAccount"></div></c:if>
<script src="js/merchant/jquery-1.11.2.min.js"></script>
<script src="js/merchant/Validform_v5.3.2.js"></script>
<script src="js/merchant/fastclick.js"></script>
<script>
	var tipsInfo = {
		tipsShopVerify: {
			icon: 'images/bird.png',
			tipsTxt: '<br>为保障您的提款安全<br>请进行店主认证',
			cancelBtn: '稍后再说',
			nextStepUrl: 'ebuyMerchant/setting.html',
			nextStepTxt: '店主认证'
		},
		//后台没有未提交状态...
		/* tipsShopVerified: {
			icon: 'images/bird.png',
			tipsTxt: '<br>店主认证<br>尚未完成',
			cancelBtn: '稍后再说',
			nextStepUrl: 'ebuyMerchant/setting.html',
			nextStepTxt: '查看'
		}, */
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
		}
	}
</script>
<script src="js/merchant/getTipsBox.js"></script>


</body>
</html>