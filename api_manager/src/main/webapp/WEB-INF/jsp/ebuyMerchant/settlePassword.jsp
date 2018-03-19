<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>/"/>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>结算密码</title>
<link rel="stylesheet" href="css/merchant/shopping.common.css">
</head>

<body class="bggrey">
<section id="wrapBox" class="sectionBox pos-relative minheight100 bggrey">
	<header class="sectionBox fantasia-header order-top-bg">
		<a class="disblock mleft15 left" href="javascript:void(0)" onclick="history.back()"><img class="back-arrow" src="images/back-arrow.png" /></a>
	    <div class="header-title">结算密码</div>
	</header>
	<section class="sectionBox password-mind-box borderbottomgrey">
	    <div class="mleft15">密码由6-14位，建议数字、字母任意组成</div>
	</section>
	<form class="inputform">
		<section class="sectionBox borderbottomgrey">
		    <ul class="register-list">
		        <li><input id="passwordInput" class="input-text password-box wp100 left" type="text" name="password" placeholder="设置密码" datatype="EnglishAndNumber6-14" nullmsg="请输入新密码！" errormsg="密码由6-14位数字或字母组成！"  /></li>
		    </ul>
		</section>
		<section class="sectionBox bggrey">
		    <div class="m010 mtop15"><input id="setPasswordBtn" class="btn-submit btnSubmit btn-next" type="submit" name="submit" value="确定" /></div>
		</section>
	</form>
	<div id="setPasswordTips" class="tips-box tips-done bounceInDown animated1s hide">
		<img class="rotateZoomIn animated1s delay" src="images/icon-tips-done.png"/><br>密码设置成功
	</div>
</section>
</body>
<script src="js/merchant/jquery-1.11.2.min.js"></script>
<script src="js/merchant/fastclick.js"></script>
<script src="js/merchant/Validform_v5.3.2.js"></script>
<script src="js/merchant/fn.common.js"></script>
<script type="text/javascript">
	$(function(){
		//表单验证
		$(".inputform").Validform({
			tiptype:1,
			btnSubmit:"#setPasswordBtn",
			ajaxPost:true,
			beforeSubmit:function(){
				checkPassword();
				
				setTimeout(function(){
					if(location.search.indexOf('orderNotSettle') > -1){
						location.href = 'ebuyMerchant/settleCenter/orderNotSettle.html';
					}else{
						location.href = 'ebuyMerchant/settleCenter/bankAccount.html';
					}
				},3000);
				
				return false;
			}
		});
		
		//检查触发按钮状态
		$('input').keyup(function(){
			countValNum();
		});
	});
</script>
</html>