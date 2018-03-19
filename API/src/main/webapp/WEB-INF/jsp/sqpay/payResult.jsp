<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String currPath = basePath+"/signalStyle/sqpay/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付结果页面</title>
<base href="<%=currPath%>" target="_blank">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<link rel="stylesheet" href="css/pay.common.css">
</head>

<body class="bggrey">
<section class="sectionBox welcome-img p00">
    <div class="change-password-box">
    	<%if("0".equals(request.getAttribute("status"))){
    		%><span><img class="register-done-img" src="images/register-done.png" /><br/>${resDesc}</span><%
    	}else{
    		%><span><img class="register-done-img" src="images/paying-icon.png" /><br>${resDesc}</span><%
    	}%>
        <input class="btn-submit bgred mtop40 pay-done-btn" type="button" name="button" value="确 定"  />
    </div>
</section>
<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
		
		//判断安卓ios系统
		var u = navigator.userAgent, app = navigator.appVersion;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		
		//安卓
		if(isAndroid){
			$('.pay-done-btn').click(function(param01,param02){
				window.payDone.payDoneBtn('${status}','${resDesc}');
			});
		}
		
		//ios
		if(isiOS){
			function payDoneBtn(param01,param02,param03){
				document.location="jfq://"+param01+"/"+param02+"/"+param03;
			};
			
			$('.pay-done-btn').click(function(){
				payDoneBtn('payDoneBtn','${status}','${resDesc}');
			});
		}

	});
</script>
</body>
</html>
