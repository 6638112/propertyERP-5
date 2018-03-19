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

<title>弹出框</title>
</head>

<body class="bggrey">

<!--店主认证、认证未完成、绑定银行卡提示-->
<div id="tipsComponent" class="tips-box">
	<div id="tipsTxt" class="t-center ptb20 borderbottomgrey"><img class="wp30 mtb10 rotateZoomIn animated1s" src="images/bird.png"/><span></span></div>
	<ul class="displaybox f16">
		<li id="cancelBtn" class="boxflex01 ptb10 t-center back-btn"></li>
		<li id="nextStepBtn" class="boxflex01 ptb10 t-center red borderleft"><a class="disblock red" href="javascript:void(0)"></a></li>
	</ul>	
</div>

<!--输入验证码提示-->
<div id="tipsEnterCode" class="tips-box">
	<div class="t-center ptb20 borderbottomgrey">
		<div class="marb15 f18">输入验证码</div>
		<div class="wp80 t-center margin_auto">已向您手机发送验证码</div>
		<div class="wp80 t-center margin_auto">请输入验证码</div>
		<input id="validCode" class="input-normal wp80 ptb5 mtop10 border-radius2" type="text" maxlength="4" />
		<div id="validCodeTips" class="wp80 t-center margin_auto mtop10 red hide">验证码错误，请重新输入</div>
	</div>
	<ul class="displaybox f16">
		<li id="cancelBtn" class="boxflex01 ptb10 t-center">取消</li>
		<li id="checkCodeBtn" class="boxflex01 ptb10 t-center red borderleft"><a class="disblock red" href="javascript:void(0)">确定</a></li>
	</ul>	
</div>

<!--输入密码提示-->
<div id="tipsEnterPassword" class="tips-box">
	<div class="t-center ptb20 borderbottomgrey">
		<div class="marb15 f18">确认提款</div>
		<div class="wp80 t-center margin_auto">请输入您的解放区商户端</div>
		<div class="wp80 t-center margin_auto">结算密码确认提款</div>
		<input class="input-normal wp80 ptb5 mtop10 border-radius2" type="password" id="password" />
		<div class="wp80 t-center margin_auto mtop10 red hide">密码错误，请重新输入</div>
		<div id="forgetPassword" class="wp80 t-center margin_auto mtop10"><a class="blue" href="javascript:void(0)">忘记密码</a></div>
	</div>
	<ul class="displaybox">
		<li id="cancelBtn" class="boxflex01 ptb10 t-center back-btn">取消</li>
		<li id="checkPasswordBtn" class="boxflex01 ptb10 t-center red borderleft pay-check-btn"><a class="disblock red" href="javascript:void(0)">确认</a></li>
	</ul>	
</div>

<!--确认结算信息提示-->
<div id="checkSettleInfo" class="tips-box">
	<div class="t-center ptb20 borderbottomgrey">
		<div class="marb15 pb10 f18 borderbottomgrey">确认明细</div>
		<div class="wp90 t-left margin_auto lineheight180"><span class="grey">结算金额</span><span class="right">￥<span class="settleAcount"></span></span></div>
		<div class="wp90 t-left margin_auto lineheight180"><span class="grey">收款银行</span><span class="bankCardName right"></span></div>
		<div class="wp90 t-left margin_auto lineheight180"><span class="grey">收款账户</span><span class="bankCardNum right"></span></div>
	</div>
	<ul class="displaybox f16">
		<li id="cancelBtn" class="boxflex01 ptb10 t-center back-btn">取消</li>
		<li id="checkSettleInfoBtn" class="boxflex01 ptb10 t-center red borderleft pay-check-btn"><a class="disblock red" href="javascript:void(0)">确定</a></li>
	</ul>	
</div>

<!--店主认证通过提示-->
<div id="tipsVerified" class="tips-box">
	<div id="tipsTxt" class="t-center ptb20 borderbottomgrey"><img class="wp30 mtb10 rotateZoomIn animated1s" src="images/stars.png"/><span><br>您已通过解放区店主认证<br>现在可以结算订单了</span></div>
	<ul class="displaybox f16">
		<li id="cancelBtn" class="boxflex01 ptb10 t-center red"><a class="disblock red" href="javascript:void(0)">我知道了</a></li>
	</ul>	
</div>

</body>
</html>