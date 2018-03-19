<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>银行卡</title>
<link rel="stylesheet" href="css/recommendaward.css">
</head>

<body class="bggrey">
<section id="wrapBox" class="sectionBox pos-relative minheight100 bggrey">
	<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
		<section class="sectionBox">
		    <section class="divide-box bordertbgrey"></section>
		    <c:forEach items="${dataValue.list }" var="item">
			    <div class="list-box bgwhite borderbottomgrey ptb5">
			        <a class="displaybox" href="javascript:void(0)">
			            <div class="item-standard-name f16 boxflex01 list-icon list-icon-bankcard">${item.bankName }<div class="f14 grey ptop5">${item.cardNumber }</div></div>
			            <!-- <div class="item-standard-name f16 boxflex01 blue t-right lineheight65">更换银行卡</div> -->
			        </a>
			    </div>
		    </c:forEach>
		</section>
	</section>
	
	<section class="sectionBox wrap-bg pop-box02 dsn">
		<form class="inputform">
			<div class="tips-box">
				<div class="t-center pt20">
					<div class="marb15 f18">提款信息确认</div>
				    <ul class="register-list bordertbgrey binding-phone-num-box mtop20 pos-relative">
				        <li class="borderbottomgrey">
				            <div class="displaybox checkPhoneNumBox">
				                <div class="item-standard-name height36 f16">手机号</div>
				                <div class="boxflex01 f16 grey"><input class="input-text wp100 grey" type="text" value="18966663333" readonly="readonly" /></div>
				                <input id="getValidCode" class="input-btn get-code-btn on" type="button" name="button" value="获取验证码"  />
				            </div>
				        </li>
				        <li>
				            <div class="displaybox">
				                <div class="item-standard-name height36 f16">验证码</div>
				                <div class="boxflex01 f16 grey"><input class="input-text valid-code wp100" type="text" placeholder="输入短信验证码" value="" maxlength="4" datatype="n" nullmsg="请输入短信验证码" errormsg="请输入正确的验证码" /></div>
				            </div>
				        </li>
			       </ul>
				</div>
				<ul class="displaybox">
					<li class="boxflex01 ptb10 t-center back-btn">取消</li>
					<li class="boxflex01 ptb10 t-center red borderleft pay-check-btn"><a class="disblock red" href="javascript:void(0)">确定</a></li>
				</ul>	
			</div>
		</form>
	</section>
</section>

<!-- <script src="js/jquery-1.11.2.min.js"></script>
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
			window.location.href = 'bindingBankCard-待完善.html';
		}
	});
	
	//获取验证码，验证手机号码
	$(".checkPhoneNumBox").Validform({
		tiptype:1,
		btnSubmit:".get-code-btn",
		dragonfly:true,
		beforeSubmit:function(curform){
			//验证手机号码通过，不提交
			$('.get-code-btn').removeClass('on');
			settime('.get-code-btn');
			
			return false;
		}
	});
	
	$('.change-bankcard-btn').click(function(){
		$('#wrapBox').addClass('heightp100');
		$('.pop-box02').removeClass('dsn');
	});

	$('.back-btn').click(function(){
		$('#wrapBox').removeClass('heightp100');
		$(this).parents('.wrap-bg').addClass('dsn').find('input.valid-code').val('');
	});
})

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
			settime(obj)
		}, 1000)
	}
}
</script> -->

</body>
</html>