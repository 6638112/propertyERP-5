<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>更换手机号码</title>
<link rel="stylesheet" href="css/recommendaward.css">
</head>

<body class="bggrey">
<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100">
	<form class="inputform">
			<div class="t-center ptb20 borderbottomgrey bggrey">
				<div class="f18 mtop10 pb10">验证手机号码</div>
			</div>
		    <ul class="register-list borderbottomgrey binding-phone-num-box pos-relative">
		        <li class="borderbottomgrey">
		            <div class="displaybox checkPhoneNumBox">
		                <div class="item-standard-name height36 f16">手机号</div>
		                <div class="boxflex01 f16 grey"><input id="validPhoneNum" class="input-text wp100" type="text" placeholder="输入手机号码" value="${sessionScope.regist3rdResponse.mobile }" readonly="readonly" maxlength="11" datatype="m" nullmsg="请输入手机号码" errormsg="请输入正确的手机号码格式" /></div>
		                <input id="getValidCode" class="input-btn get-code-btn on" type="button" name="button" value="获取验证码"  />
		            </div>
		        </li>
		        <li>
		            <div class="displaybox">
		                <div class="item-standard-name height36 f16">验证码</div>
		                <div class="boxflex01 f16 grey"><input id="verifyCode" class="input-text wp100" type="text" placeholder="输入短信验证码" value="" maxlength="4" datatype="n" nullmsg="请输入短信验证码" errormsg="请输入正确的验证码" /></div>
		            </div>
		        </li>
	       </ul>
			<section class="sectionBox wp100 ptb20 border-radius-bottom bggrey">
			    <div class="m010"><input class="btn-submit btnSubmit btn-next" type="button" value="确认" /></div>
			</section>
	</form>
	<div class="tips-box tips-done bounceInDown animated1s dsn">
		<img class="rotateZoomIn animated1s delay" src="images/icon-tips-done.png"/><br>解除绑定成功
	</div>
</section>
<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/Validform_v5.3.2.js"></script>
<script>
$(function(){
	//表单验证
	$(".inputform").Validform({
		tiptype:1,
		btnSubmit:".btnSubmit",
		postonce:true,
		ajaxPost:true,
		beforeSubmit:function(){
			
			jQuery.ajax({//绑定手机，该接口有校验验证码功能
				  url: "../common/toUrl.do",
				  cache: false,
				  dataType:"json",
				  data:{"detailUrl":"/user/bindPhoneCheckOldVerify.json", "oldValicode":$('#verifyCode').val(), "isNeedLogin":"1"},
				  success:function(data){
					if(data.status =="0000"){
						// 成功 
						$('.tips-box').show();
						setTimeout(function(){window.location.href = 'bindingPhoneNum_to_personalCenter.jsp'},3000);
						history.pushState({}, '个人中心', 'personalCenter.do');
					}else{
						//失败 
						$.Showmsg(data.message);
					}
				  },  
		          error: function(){  
		          	$.Showmsg('网络不给力，请稍后重试'); 
		          } 
			});
			
			return false;
		}
	});
		
	//获取验证码，验证手机号码
	$(".checkPhoneNumBox").Validform({
		tiptype:1,
		btnSubmit:".get-code-btn",
		dragonfly:true,
		postonce:true,
		beforeSubmit:function(curform){
			//验证手机号码通过，不提交
			$('.get-code-btn').removeClass('on');
			
			getValidCode();
			
			settime('.get-code-btn');
			
			return false;
		}
	});
			
	//检查触发按钮状态
	$('input').keyup(function(){
		countValNum();
		
	});
	$('select').change(function(){
		countValNum();
	});
	
});

//获取验证码
function getValidCode(){
	jQuery.ajax({
		  url: "../common/toUrl.do",
		  cache: false,
		  dataType:"json",
		  data:{"detailUrl":"/login/getValidateCode.json", "mobile":$('#validPhoneNum').val(), "type":4, "isNeedLogin":"1"},
		  success:function(data){
			  console.log("1111");
			if(data.status =="0000"){
				// 成功
				$.Showmsg('验证码已成功发送！');
			}else{
				//失败 
				$.Showmsg(data.message);
			}
		  },  
          error: function(){  
          	$.Showmsg('网络不给力，请稍后重试'); 
          } 
	});
}
//触发按钮状态
function countValNum(){
	var num = 0;
	$('.register-list li').each(function(){
	var thisInputVal = $(this).find('input').val();
	var thisSelectVal = $(this).find('select').val();
	if(thisInputVal == '' || thisSelectVal == ''){
			num += 1;
		}
	});
	if(num == 0){
		$('.btnSubmit').addClass('bgred noborder white');
	}else{
		$('.btnSubmit').removeClass('bgred noborder white');
}
}

//60秒倒计时，重新获取验证码
var countdown = 60;
function settime(obj) {
	if (countdown == 0) {
		$(obj).attr("disabled",false).val("获取验证码").addClass('on'); 
		countdown = 60;
	} else {
		$(obj).attr("disabled", true).val("重新发送(" + countdown + ")");
		countdown--;
		setTimeout(function() {
			settime(obj);
		}, 1000);
	}
}
</script>
</body>
</html>