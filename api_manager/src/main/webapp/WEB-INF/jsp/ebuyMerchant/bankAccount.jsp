<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>/"/>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>银行卡</title>
<link rel="stylesheet" href="css/merchant/shopping.common.css">
</head>

<body class="bggrey pos-relative">
	
<section id="wrapBox" class="sectionBox pos-relative minheight100">
	<header class="sectionBox fantasia-header order-top-bg">
		<a class="disblock mleft15 left white w80" href="ebuyMerchant/settleCenter.html"><img class="back-arrow left" src="images/back-arrow.png" /> <span class="mleft10">返回</span></a>
	    <div class="header-title">银行卡</div>
		<div id="triggerVerifyCode" class="disblock p010 right white" data-url="ebuyMerchant/settleCenter/bindBankAccount.html">更换</div>
	</header>
	
	<section class="sectionBox">
	    <section class="divide-box bordertbgrey"></section>
	    <div class="list-box bgwhite borderbottomgrey ptb5">
            <div class="item-standard-name f16 list-icon list-icon-bankcard">${bankName}<div class="f14 grey ptop5">${cardNo}</div></div>
	    </div>
	    <ul id="changeSettlePassword" class="bottom-menu-box displaybox t-center bggrey" data-url="ebuyMerchant/toPage.html?page=settlePassword">
	        <li class="boxflex01 pb20"><a class="blue disblock f14" href="javascript:void(0)">修改结算密码</a></li>
	    </ul>
	</section>

	<section id="popBox" class="sectionBox wrap-bg hide"></section>
	<div id="urlTogo" class="hide" data-url=""></div>
</section>
<script src="js/merchant/jquery-1.11.2.min.js"></script>
<script src="js/merchant/Validform_v5.3.2.js"></script>
<script src="js/merchant/fastclick.js"></script>
<script>
	var tipsInfo = {
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