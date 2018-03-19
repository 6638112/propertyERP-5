$(function(){
	//立即结算，判断是否已认证
	$("#cashOutBtn").click(function(){
		var thisStatus = $(this).attr('data-ownerAuditStatus');
		
		//已认证
		if(thisStatus === '1'){
			location.href = 'ebuyMerchant/settleCenter/orderNotSettle.html';	

		//已提交认证，待审核
		/*}else if(thisStatus === '0'){
			insertTipsBox(tipsInfo.tipsShopVerified);
			showTipsBox();*/
			
		//已提交认证，待审核或审核失败。后台没有未提交认证的状态
		}else{
			insertTipsBox(tipsInfo.tipsShopVerify);
			showTipsBox();
		}
	})
	//取消按钮	
	$(document).on('click', '#cancelBtn', function(){
		$('#wrapBox').removeClass('heightp100');
		$('#popBox').addClass('hide');
	});
	
	//触发发送验证码提示框
	$('#triggerVerifyCode, #changeSettlePassword').click(function(){
		//结算中心页面，已绑定银行卡，进入银行卡页面
		if(location.pathname.indexOf('settleCenter.html') > -1 && $('#noBankAccount').length === 0){
			location.href = 'ebuyMerchant/settleCenter/bankAccount.html';
		
		//待结算订单页面，已绑定银行卡，点击结算按钮
		}else if(location.pathname.indexOf('orderNotSettle.html') > -1 && $('#hasBankAccount').length > 0){
			$('#popBox').load('tipsBox.jsp #checkSettleInfo', function(){
				updateBankInfo(tipsInfo.bankInfo);
			});
			showTipsBox();
			
		//银行卡页面，已绑定银行卡，点击更换银行卡或点击修改结算密码
		}else if(location.href.indexOf('bankAccount.html') > -1){
			//设置将要跳转的页面url
			if($('#urlTogo').length > 0){
				$('#urlTogo').attr('data-url', $(this).attr('data-url'));
			}
			
			insertTipsBox(tipsInfo.triggerVerifyCode, 'getCodeBtn');
			showTipsBox();
		
		//结算中心或	待结算订单页面，未绑定银行卡
		}else if($('#noBankAccount').length > 0){
			insertTipsBox(tipsInfo.tipsbankcardBinding);
			showTipsBox();
		}
	});
	$(document).on('click', '#forgetPassword', function(){
		//银行卡页面，已绑定银行卡，点击更换银行卡或点击修改结算密码
		insertTipsBox(tipsInfo.triggerVerifyCode, 'getCodeBtn');
	});
	$(document).on('click', '#nextStepBtn', function(){
		if($(this).find('a').attr('href').indexOf('setting.html') === -1 && !$(this).hasClass('getCodeBtn')){
			//未绑定银行卡
			insertTipsBox(tipsInfo.triggerVerifyCode, 'getCodeBtn');
			$('#nextStepBtn').removeAttr('id');
		}
	});
	
	
	//同意发送验证码
	$(document).on('click', '.getCodeBtn', function(){
		getValidCode();
		$('#popBox').load('tipsBox.jsp #tipsEnterCode');
	});
	//验证码校验通过
	$(document).on('click', '#checkCodeBtn', function(){
		var reg = /^\d{4}$/;
		
		if(!reg.test($('#validCode').val())){
			$.Showmsg('验证码为4位阿拉伯数字');
			return false;
		}else{
			//校验验证码
			checkValidCode();
		}
	});
	//确认提款信息明细
	$(document).on('click', '#checkSettleInfoBtn', function(){
		$('#popBox').load('tipsBox.jsp #tipsEnterPassword');
	});
	//密码校验通过
	$(document).on('click', '#checkPasswordBtn', function(){
		settle();
	});

	//忘记密码按钮
	$(document).on('click', '#forgetPassword', function(){
		$('#urlTogo').addClass('from-forget-password');
	});
	
	//店主通过认证后，设置页面弹出提示信息，//后台没有做这个状态。。。
	/*if($('#wrapBox').attr('data-ownerAuditStatus') === '1'){
		$('#popBox').load('tipsBox.jsp #tipsVerified');
		showTipsBox();
	}*/
	
})

//验证码校验通过，跳转页面
function jumpToPage(){
	var thisHref = location.href;
	//当前位置：结算中心页面，进入绑定银行卡；或银行卡页面，进入更换银行卡或进入修改结算密码
	if(thisHref.indexOf('settleCenter.html') > -1 || thisHref.indexOf('bankAccount.html') > -1){
		if($('#urlTogo').length > 0){
			var urlTogo = $('#urlTogo').attr('data-url');
		}else if($('#noBankAccount').length > 0){
			var urlTogo = 'ebuyMerchant/settleCenter/bindBankAccount.html?noBankAccount=true';
		}else{
			var urlTogo = 'ebuyMerchant/settleCenter/bindBankAccount.html';
		}
		location.href = urlTogo;
		
	//当前位置：待结算订单页面，进入确认结算信息弹窗-输入提款密码	或忘记结算密码
	}else if(thisHref.indexOf('orderNotSettle.html') > -1){
		//已绑定银行卡
		if($('#hasBankAccount').length > 0){
			if($('.from-forget-password').length > 0){
				var urlTogo = $('#urlTogo').attr('data-url');
				location.href = urlTogo;
			}else{
				$('#popBox').load('tipsBox.jsp #checkSettleInfo', function(){
					updateBankInfo(tipsInfo.bankInfo);
				});
			}
		
		//未绑定银行卡
		}else if($('#noBankAccount').length > 0){
			var urlTogo = 'ebuyMerchant/settleCenter/bindBankAccount.html?noBankAccount=true&from=orderNotSettle';
			location.href = urlTogo;
		}
	}
} 

//插入tipsBox信息
function updateTipsBox(infoObj, newClass){
	$('#tipsTxt').find('img').attr('src', infoObj.icon);
	$('#tipsTxt').find('span').html(infoObj.tipsTxt);
	$('#cancelBtn').text(infoObj.cancelBtn);
	$('#nextStepBtn').find('a').attr('href', infoObj.nextStepUrl);
	$('#nextStepBtn').find('a').text(infoObj.nextStepTxt);
	
	//增加新class标记
	if(newClass !== '' && newClass !== null){
		$("#nextStepBtn").addClass(newClass);
	}
}
//插入tipsBox到页面
function insertTipsBox(infoObj, newClass){
	$('#popBox').load('tipsBox.jsp #tipsComponent', function(){
		updateTipsBox(infoObj, newClass);
	});
}
//显示tipsBox
function showTipsBox(){
	$('#wrapBox').addClass('heightp100');
	$('#popBox').removeClass('hide');
}

//插入tipsBox银行卡信息
function updateBankInfo(infoObj){
	$('.settleAcount').text(infoObj.settleAcount);
	$('.bankCardName').text(infoObj.bankCardName);
	$('.bankCardNum').text(infoObj.bankCardNum);
}

//获取验证码
function getValidCode(){
	$.ajax({
		  url: "ebuyMerchant/getValidateCode.json",
		  dataType:"json",
		  async:true,
		  data:{"mobile":tipsInfo.triggerVerifyCode.phoneNum, "type":8},
		  success:function(data){
			if(data.status =="0000"){
				$.Showmsg('验证码已成功发送！');
			}else{
				$.Showmsg(data.message);
			}
		  },  
          error: function(){  
          	$.Showmsg('网络不给力，请稍后重试'); 
          } 
	});
}

//校验验证码
function checkValidCode(){
	$.ajax({
		url: "ebuyMerchant/isVarifyCodeCorrect.html",
		cache: false,
		dataType:"json",
		data:{"phoneNum":tipsInfo.triggerVerifyCode.phoneNum, "validCode":$('#validCode').val(), "type":8},
		success:function(data){
			if(data.status =="0000"){
				jumpToPage();
			}else{
				$.Showmsg(data.message);
				return false;
			}
		},  
		error: function(){  
			$.Showmsg('网络不给力，请稍后重试'); 
			return false;
		} 
	});
}

//结算
function settle(){
	$.ajax({
		url: "ebuyMerchant/settleCenter/settle.json",
		cache: false,
		dataType:"json",
		method: 'post',
		data:{"password":$('#password').val()},
		success:function(data){
			if(data.status =="0000"){
				window.location.href = "ebuyMerchant/settleCenter/settleRecordInfo.html?applyId=" + data.dataValue.applyId;
			}else{
				$.Showmsg(data.message);
				return false;
			}
		},  
		error: function(){  
			$.Showmsg('网络不给力，请稍后重试'); 
			return false;
		} 
	});
}