<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html, charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>登录</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css">
</head>

<body class="bggrey">
<section class="sectionBox welcome-img hide"><img src="../images/merchant/welcome-img.png" /></section>
<header class="sectionBox fantasia-header order-top-bg">
    <div class="header-title">登录</div>
    <a class="disblock p010 right white shop-register-btn" href="register1.html">立即注册</a>
</header>
<section class="divide-box borderbottomgrey"></section>
<form class="inputform" id="inputform" action="doLogin.html" method=post>
<section class="sectionBox borderbottomgrey">
    <ul class="register-list">
        <li class="borderbottomgrey"><input class="input-text wp100 left" type="text" name="phoneNum" maxlength="11" placeholder="请输入手机号码" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码格式！" /></li>
        <li><input class="input-text wp100" type="password" name="password" placeholder="请输入密码" datatype="s" nullmsg="请输入密码！" /></li>
    </ul>
</section>
<section class="sectionBox bggrey">
    <div class="m010 mtop15"><input class="btn-submit bgred" type="button" name="submit" value="登录"/></div>
    <div class="m010 mtop10 right"><a class="blue" href="toPage.html?page=changePassword1&type=1">忘记密码</a></div>
</section>
</form>
</body>
<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/data.encrypt.base64.min.js"></script>
<script src="../js/jquery.form.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
		if("${empty param.initPage}") {
			//$('.welcome-img').fadeOut();
		} else {
			$('.welcome-img').removeClass('hide');
			setTimeout(function(){
				$('.welcome-img').fadeOut();
			},1500);
		}
		//按回车触发登录
		$('input[name=password]').keyup(function(event){
			var myEvent = event || window.event;
			var curVal = $.trim($(this).val());
			if(myEvent.keyCode == 13 && !curVal == ''){
				$('.btn-submit').click();
			}
		});
	})
</script>
<script src="../js/merchant/Validform_v5.3.2.js"></script>
<script type="text/javascript">
	$(function(){
		var issubmited = false;
		//表单验证
		$(".inputform").Validform({
			tiptype:1,
			postonce:false,
			btnSubmit:".btn-submit", 
			beforeSubmit:function(){
				//var password = $('[name=password]').val();
				//var createBase64 = new base64();
				//$('[name=password]').val(createBase64.encode(password));
				$.Showmsg("正在登录…");
				if(issubmited){
					return false;
				}
			},
			callback:function(data){
        		issubmited = true;
				$("#inputform").ajaxSubmit({  
                    success: function(data){  
                    	if (data.status == '0000' ) {
							if(data.dataValue.isMerchant == true){
								if(typeof(data.dataValue.isFirstLogin) != "undefined" && data.dataValue.isFirstLogin == true){
									//更改密码
									window.location.href = "toPage.html?page=changePassword1";
								} else {
									//登录进入首页
									//window.location.href = "toPage.html?page=changePassword1";
									window.location.href = "myOrder.html";
								}
							}else{//还没有店铺，创建店铺
								window.location.href = "toPage.html?page=registerStep03";  
							}
						} else {
							$.Showmsg(data.message);
                    		issubmited = false;
							return;
						}
                    },  
                    error: function(){  
                    	$.Showmsg('网络不给力，请稍后重试'); 
                		issubmited = false;
                    }  
                }); 
				
				return false;
			}
		});
	});
	
	
</script>
</html>