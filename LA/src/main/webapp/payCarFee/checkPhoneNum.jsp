<%-- 
    Document   : 手机号验证
    Created on : 2016-3-16, 13:20:24
    Author     : Liyl lyl010991@126.com
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>手机号验证</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopping.common.css">
	</head>
	<body class="bggrey">
		<section id="wrapBox" class="sectionBox pos-relative minheight100 bggrey">
			<form class="inputform">
			    <ul class="register-list bordertbgrey binding-phone-num-box mtop20 pos-relative">
			        <li class="borderbottomgrey">
			            <div class="displaybox checkPhoneNumBox">
			                <div class="item-standard-name height36 f16">手机号</div>
			                <div class="boxflex01 f16 grey"><input class="input-text wp100" type="text" placeholder="输入手机号码" value="" maxlength="11" datatype="m" nullmsg="请输入手机号码" errormsg="请输入正确的手机号码格式" /></div>
			                <input id="getValidCode" class="input-btn get-code-btn on" type="button" name="button" value="获取验证码"  />
			            </div>
			        </li>
			        <li>
			            <div class="displaybox">
			                <div class="item-standard-name height36 f16">验证码</div>
			                <div class="boxflex01 f16 grey"><input class="input-text wp100" type="text" placeholder="输入短信验证码" value="" maxlength="4" datatype="n" nullmsg="请输入短信验证码" errormsg="请输入正确的验证码" /></div>
			            </div>
			        </li>
		        </ul>
				<section class="sectionBox wp100 ptb10 border-radius-bottom bggrey">
		       		<div class="f14 grey m010">解放区与全国多家物业公司合作，开通在线物业服务，请验证手机号开通服务。</div>
				    <div class="m010 mtop10"><input class="btn-submit btnSubmit btn-next" type="button" value="确认" /></div>
				</section>
			</form>
			<div class="tips-box tips-done bounceInDown animated1s dsn">
				<img class="rotateZoomIn animated1s delay" src="${pageContext.request.contextPath}/images/icon-tips-done.png"/><br>绑定成功
			</div>
		</section>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
		<script src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
		<script>
			$(function(){
				//表单验证
				$(".inputform").Validform({
					tiptype:1,
					btnSubmit:".btnSubmit",
					postonce:true,
					ajaxPost:true,
					callback:function(data){
						$('.tips-box').show();
						setTimeout(function(){
							//跳转
							window.location.href = '${pageContext.request.contextPath}/payCarFee/payPropertyList.jsp';
						},3000);
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
						
				new FastClick(document.body);//检查触发按钮状态
				$('input').keyup(function(){
					countValNum();
					
				});
				$('select').change(function(){
					countValNum();
				});
			});
			
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
						settime(obj)
					}, 1000)
				}
			}
		</script>
	</body>
</html>