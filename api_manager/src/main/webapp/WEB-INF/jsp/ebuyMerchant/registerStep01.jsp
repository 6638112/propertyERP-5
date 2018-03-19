<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>注册</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css">
</head>

<body class="bggrey">
<header class="sectionBox fantasia-header order-top-bg">
    <div class="header-title">注册</div>
</header>
<section class="divide-box borderbottomgrey"></section>
<form id="inputform" class="inputform" action="isVarifyCodeCorrect.html" onsubmit="return false;">
<input type="hidden" value="0" name="type" />
<section class="sectionBox borderbottomgrey">
    <ul class="register-list">
    	<li class="borderbottomgrey checkPhoneNum"><input id="phoneNum" class="input-text mtop10 wp50 left" type="text" name="phoneNum" maxlength="11" placeholder="请输入手机号码" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码格式！"/><input id="validCode" class="input-btn right password-btn-off" type="button" name="button" value="获取验证码" /></li>
        <li><input id="verifyCode" class="input-text wp100" type="text" name="validCode" placeholder="请输入短信验证码" datatype="s" nullmsg="请输入短信验证码！" /></li>
    </ul>
</section>
<section class="sectionBox bggrey">
    <div class="m010 mtop15"><input class="btn-submit btn-next" type="button" value="下一步" /></div>
</section>
</form>
</body>
<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
</script>
<script src="../js/merchant/shopping.common.js"></script>
<script src="../js/merchant/Validform_v5.3.2.js"></script>
<script src="../js/jquery.form.js"></script>
<script type="text/javascript">
	$(function(){
		var issubmited = false;
		//表单验证
		$(".inputform").Validform({
			tiptype:1,
			postonce:false,
			btnSubmit:".btn-next",
			beforeCheck:function(){
				setTimeout(function(){
					$('#Validform_msg').hide();
				},2000);
			},
			beforeSubmit:function(){
				$.Showmsg("请稍候…");
				if(issubmited){
					return false;
				}
			},
			callback:function(data){
        		issubmited = true;
				//验证成功，提交表单
				$("#inputform").ajaxSubmit({  
                    success: function(data){
						if (data.status == '0000') {
							window.location.href = "toPage.html?page=registerStep02&mobile=" + $('#phoneNum').val() + "&verifyCode=" + $("#verifyCode").val();
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
			}
		});
		
		//获取验证码，验证手机号码
		$(".checkPhoneNum").Validform({
			tiptype:1,
			btnSubmit:".password-btn-off",
			beforeSubmit:function(curform){
				//验证手机号码通过，不提交
				return false;	
			}
		});
		
		$('#phoneNum').keyup(function(){
			var valLength = $(this).val().length;
			if(valLength == 11){
				$('.password-btn-off').addClass('password-btn-on');
			}else{
				$('.password-btn-off').removeClass('password-btn-on');	
			}
		});
		
		$("#validCode").click(function(){
			$.ajax({
				type:"post",
				url:"getValidCode.html",
				data:{phoneNum : $("#phoneNum").val(), type: 0},
				dataType:"json",
				beforeSend:function(data){
					$.Showmsg("正在获取…");
				},
				success:function(data){
					try {
						if (data.status == '0000') {
							$("#validCode").attr('disabled','disabled');
							$.Showmsg('验证码已经发送到手机！');
							setTimeout(function(){
								$("#validCode").removeAttr('disabled');
							},5000)
						} else {
							$.Showmsg(data.message);
							return;
						}
					} catch (e) {
						$.Showmsg(e);
					}
				}
			});
		});
		
		
	});
	
	
</script>
</html>