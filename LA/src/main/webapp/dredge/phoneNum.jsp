<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>手机号</title>
<link rel="stylesheet" href="css/recommendaward.css">
</head>

<body class="bggrey">
<section class="sectionBox t_center bggrey">
    <div class="wp20 phone-img"><img src="images/phone-img.png" /></div>
	<div class="t-center ptb10">
		<div class="f16 mtop10">您的手机号：${sessionScope.regist3rdResponse.mobile }</div>
		<div class="mtop10 f14 grey">您可用当前手机号登录到解放区App</div>
	</div>
	<!-- <section class="sectionBox wp100 ptb20 border-radius-bottom bggrey">
	    <div class="m010"><a href="changePhoneNum.jsp"><input class="btn-submit btnSubmit btn-next change-phoneNum-btn" type="button" value="更换手机号" /></a></div>
	</section> -->
</section>

<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/Validform_v5.3.2.js"></script>

<script>
	$(function(){
		//表单验证
		$(".inputform").Validform({
			tiptype:1,
			btnSubmit:".pay-check-btn",
			postonce:true,
			ajaxPost:true,
			callback:function(data){
				//跳转
				window.location.href = 'bindingPhoneNum.do';
			}
		});
	})
</script>

</body>
</html>