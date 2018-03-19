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

<title>绑定银行卡</title>
<link rel="stylesheet" href="css/merchant/shopping.common.css">
</head>

<body class="bggrey">
<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100"> 
	<form class="inputform">
	<section id="mainPart01" class="sectionBox">
		<header class="sectionBox fantasia-header order-top-bg">
			<a class="disblock mleft15 left white w80" href="javascript:void(0)" onclick="history.back()"><img class="back-arrow left" src="images/back-arrow.png" /> <span class="mleft10">返回</span></a>
		    <div class="header-title">绑定银行卡</div>
		</header>
	    <section class="divide-box bordertbgrey"></section>
	    <ul class="register-list borderbottomgrey">
	        <li class="borderbottomgrey">
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 w50">持卡人</div>
					<div class="boxflex01 f16 t-right grey">
						<input id="accountName" class="input-text wp100 t-right <c:if test="${!empty ownerName}">grey</c:if>" name="accountName" type="text" placeholder="持卡人姓名" <c:if test="${!empty ownerName}">readonly="readonly" value="${ownerName}"</c:if> maxlength="20" datatype="zhOrEnglish" nullmsg="请输入持卡人姓名！" />
					</div>
	            </div>
	        </li>
	        <li class="borderbottomgrey">
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 w50">卡号</div>
	                <div class="boxflex01 f16 t-right grey"><input id="accountBank" class="input-text wp100 t-right" name="accountBank" type="text" placeholder="银行卡号" value="" maxlength="24" datatype="*19-24" nullmsg="请输入银行卡号！" errormsg="银行卡号由16-20位数字组成！" /></div>
	            </div>
	        </li>
	        <li class="borderbottomgrey">
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 boxflex01 pleft30">开户行</div>
	                <div class="boxflex01 f16 lineheight140">
			            <select id="bankName" name="bankName" class="input-text wp100 list-arrow directionRtl pright15 mtop3" datatype="*" nullmsg="请选择开户行！" >
			                <option value="">请选择</option>
							<c:forEach items="${bankList}" var="bank" >
								<option value="${bank.bankname}">${bank.bankname}</option>
							</c:forEach>
			            </select>
	                </div>
	            </div>
	        </li>
	        <li class="borderbottomgrey">
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 boxflex01 pleft30">开户行所在省</div>
	                <div class="boxflex01 f16 lineheight140">
			            <select id="bankProvince" onchange="onSelectChange(this,'bankCity');" name="bankProvince" class="input-text wp100 list-arrow directionRtl pright15 mtop3" datatype="*" nullmsg="请选择开户行所在省！" >
			                <option value="-1">请选择省</option>
							<c:forEach items="${pcbList}" var="p">
								<option value="${p.name}" data-id="${p.id}">${p.name}</option>
							</c:forEach>
			            </select>
	                </div>
	            </div>
	        </li>
	        <li class="borderbottomgrey">
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 boxflex01 pleft30">开户行所在市</div>
	                <div class="boxflex01 f16 lineheight140">
			            <select id="bankCity" name="bankCity" class="input-text wp100 list-arrow directionRtl pright15 mtop3" datatype="*" nullmsg="请选择开户行所在市！" >
			                <option value="-1" selected="selected">请选择市</option>
			            </select>
	                </div>
	            </div>
	        </li>
	        <li>
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 w50">开户行支行</div>
	                <div class="boxflex01 f16 t-right grey"><input id="bankBranch" class="input-text wp100 t-right" name="bankBranch" type="text" placeholder="开户行支行名称" value="" maxlength="20" datatype="*" nullmsg="请输入开户行支行名称！" /></div>
	            </div>
	        </li>
	    </ul>
	    
		<section class="sectionBox bggrey">
		    <div class="m010 mtop15"><input id="setBankAccountBtn" class="btn-submit btnSubmit btn-next" type="button" value="完成" /></div>
		</section>
	    <section class="divide-box"></section>
	
	</section>
	<section id="mainPart02" class="hide">
		<header class="sectionBox fantasia-header order-top-bg">
			<a class="disblock mleft15 left" href="javascript:void(0)" onclick="history.back()"><img class="back-arrow" src="images/back-arrow.png" /></a>
		    <div class="header-title">结算密码</div>
		</header>
		<section class="sectionBox password-mind-box borderbottomgrey">
		    <div class="mleft15">密码由6-14位，建议数字、字母任意组成</div>
		</section>
		<section class="sectionBox borderbottomgrey">
		    <ul class="register-list">
		        <li><input id="passwordInput" class="input-text password-box wp100 left" type="text" name="withdrawPassword" placeholder="设置密码" datatype="EnglishAndNumber6-14" nullmsg="请输入密码！" errormsg="密码由6-14位数字或字母组成！"  /></li>
		    </ul>
		</section>
		<section class="sectionBox bggrey">
		    <div class="m010 mtop15"><input id="setPasswordBtn" class="btn-submit btnSubmit btn-next" type="button" name="submit" value="确定" /></div>
		</section>
	</section>
	</form>
	
	<section class="sectionBox wrap-bg hide">
		<div class="tips-box">
			<div class="t-center ptb20 borderbottomgrey">
				<div class="marb15 f18">请确认银行卡信息</div>
				<div class="wp90 t-left margin_auto"><span class="grey">持卡人</span><span class="bankCardPerson right"></span></div>
				<div class="wp90 t-left margin_auto"><span class="grey">收款银行</span><span class="bankCardName right"></span></div>
				<div class="wp90 t-left margin_auto"><span class="grey">收款账户</span><span class="bankCardNum right"></span></div>
			</div>
			<ul class="displaybox">
				<li id="cancelBtn" class="boxflex01 ptb10 t-center">取消</li>
				<li id="checkBindingBtn" class="boxflex01 ptb10 t-center red borderleft"><a class="disblock red" href="javascript:void(0)">确认绑定</a></li>
			</ul>	
		</div>
	</section>
	<div id="setPasswordTips" class="tips-box tips-done bounceInDown animated1s hide">
		<img class="rotateZoomIn animated1s delay" src="images/icon-tips-done.png"/><br>密码设置成功
	</div>
</section>

<script src="js/merchant/jquery-1.11.2.min.js"></script>
<script src="js/merchant/fastclick.js"></script>
<script src="js/merchant/Validform_v5.3.2.js"></script>
<script src="js/merchant/bankinput.js"></script>
<script src="js/merchant/fn.common.js"></script>
<script>
$(function(){
	//表单验证
	$(".inputform").Validform({
		tiptype:1,
		btnSubmit:".btnSubmit",
		ajaxPost:true,
		ignoreHidden:true,
		beforeSubmit:function(){
			//银行卡信息表单验证
			if($('#mainPart01').is(':visible')){
				$('#wrapBox').addClass('heightp100');
				//确认银行卡信息
				$('.bankCardPerson').text($('#accountName').val());
				$('.bankCardNum').text($('#accountBank').val());
				$('.bankCardName').text($('#bankName option:selected').text());
				$('.wrap-bg').removeClass('hide');
			
			//设置结算密码信息表单验证
			}else{
				function showTips(){
					$("#wrapBox").addClass('heightp100');
					$("#setPasswordTips").removeClass('hide');
				}
				
				setTimeout(function(){
					if(location.href.indexOf('orderNotSettle') > -1){
						var urlTogo = 'ebuyMerchant/settleCenter/orderNotSettle.html';
					}else{
						var urlTogo = 'ebuyMerchant/settleCenter.html';
					}
					submitBankAccount(urlTogo, showTips);
				},2500);
			}
			
			return false;
		}
	});

	$('#cancelBtn').click(function(){
		$('#wrapBox').removeClass('heightp100');
		$('.wrap-bg').addClass('hide');
	});
	$('#checkBindingBtn').click(function(){
		//首次绑定银行卡，需设置结算密码
		if(location.search.indexOf('noBankAccount') > -1){
			$('.wrap-bg').addClass('hide');
			$('#mainPart01').addClass('hide');
			$('#mainPart02').removeClass('hide');
			
		//修改银行卡，无需设置结算密码
		}else{
			var urlTogo = 'ebuyMerchant/settleCenter/bankAccount.html';
			submitBankAccount(urlTogo, null);
		}
	});
	
	//检查触发按钮状态
	$('input').keyup(function(){
		countValNum();
	});
	$('select').change(function(){
		countValNum();
	});
	
	$('#accountBank').bankInput();
	
	//保存表单数据  
	$("input[type=text],select").change(function(){
	    $this = $(this);
	    localStorage[$this.attr("id")] = $this.val();
	});

	//读取本地存储的数据
	if (localStorage) {
		
		$(".register-list li").each(function(){
			var thisKey = $(this).find('input').attr('id') || $(this).find('select').attr('id');
			
			if(localStorage[thisKey]){
				$('#' + thisKey).val(localStorage[thisKey]);
				
				if(thisKey === 'bankProvince'){
					onSelectChange(document.getElementById('bankProvince'), 'bankCity');
				}
			}
		});
		
		countValNum();

	}
})

function submitBankAccount(urlTogo, callback){
	$.ajax({
		type:"post",
		url:"ebuyMerchant/settleCenter/bindBankAccount.json",
		data:$(".inputform").serialize(),
		async:false,
		dataType:"json",
		success:function(data){
			if(data.status=="0000"){
				if(callback !== null){
					callback();
				}
				//跳转回银行卡页面
				location.href = urlTogo;
			}
		},
	});
}

</script>


</body>
</html>