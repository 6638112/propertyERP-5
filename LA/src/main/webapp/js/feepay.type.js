//触发按钮状态
function countValNum() {
	var num = 0;
	$('.fee-pay-list li').each(function() {
		if($(this).find('input').length > 0) {
			var thisInputVal = $(this).find('input').val();
		} else {
			var thisInputVal = '1';
		}
		if($(this).find('select').length > 0) {
			var thisSelectVal = $(this).find('select').val();
		} else {
			var thisSelectVal = '1';
		}

		//thisSelectVal === '';	若thisSelectVal值为第一个选项的value，则没有选中
		if($.trim(thisInputVal) === '' || thisSelectVal === '-1') {
			num += 1;
		}
	});
	if(num == 0) {
		$('.btnSubmit').addClass('bgred noborder white box-shadow-red');
	} else {
		$('.btnSubmit').removeClass('bgred noborder white box-shadow-red').addClass('box-shadow');
	}
}

//序列化表单信息为json对象
$.fn.parseForm=function(){
    var serializeObj={};
    var array=this.serializeArray();
    var str=this.serialize();
    $(array).each(function(){
        if(serializeObj[this.name]){
            if($.isArray(serializeObj[this.name])){
                serializeObj[this.name].push(this.value);
            }else{
                serializeObj[this.name]=[serializeObj[this.name],this.value];
            }
        }else{
            serializeObj[this.name]=this.value; 
        }
    });
    return serializeObj;
};

//是否已提交付款
var issubmited = false;
var dataParams;
//费用支付
function toPay() {

	var shouldPayAmt = parseFloat($("#shouldPay").text().trim());

	var ajaxURL = "../livingPay/submitOrder.do";
	dataParams = $('.inputform').serialize();

	if(!isWeixin){
		dataParams = dataParams + '&userId=' + getUrlParam('userId');
	}
	
	if(issubmited) { //防止二次提交付款
		return false;
	}

	issubmited = true;

	$.ajax({
		url: ajaxURL, //ajax调用微信统一接口获取prepayId
		type: 'post',
		data: dataParams,
		success: function(data) {
			var orderId;
			
			if(typeof data === 'string'){
				data = JSON.parse(data);
			}
			
			if(data.status !== '0000') { //提交订单失败
				issubmited = false;
				$.Showmsg(data.message);
				return false;
			}else{
				orderId = data.dataValue.orderId;
			}

			if(isWeixin) {
				//充值金额为0的情况，不再调用微信支付
				if($('[name=feeTypeId]').val() == 6 && $('[name=amount]').val() == 0){
					window.location.href = "../livingPay/viewLivingPayHistoryList.do";
					return;
				}
				
				$.ajax({
					url: '../order/getWXPayParams.do',
					data: {'orderId': orderId},
           			dataType:"json",	//必须加上类型，否则到测试环境(linux)返回的数据类型会导致无法支付
					success: function(signData){
						var signInfo = JSON.parse(signData);
						WeixinJSBridge.invoke('getBrandWCPayRequest', {
							"appId": signInfo.appId, //公众号名称，由商户传入  
							"timeStamp": signInfo.timeStamp, //时间戳，自 1970 年以来的秒数  
							"nonceStr": signInfo.nonceStr, //随机串  
							"package": signInfo.package, //prepay_id=*** 
							"signType": signInfo.signType, //微信签名方式:MD5  
							"paySign": signInfo.paySign //微信签名  
						}, function(res) {
							//alert(res.err_msg); // get_brand_wcpay_request:ok
							if(res.err_msg == "get_brand_wcpay_request:ok") {
								window.location.href = "../livingPay/viewLivingPayHistoryList.do";
							} else {
								// $.Showmsg(res.err_msg);
								$.Showmsg("付款失败");
							}
						});
						issubmited = false;
					}
				})
			} else {
				dataParams = $('.inputform').parseForm();
				dataParams.orderId = orderId;
				issubmited = false;
				
				if(isAndroid) {
					window.livingFee.feePay(JSON.stringify(dataParams));
				}
				if(isiOS) {
					setupWebViewJavascriptBridge(function(bridge) {
						bridge.callHandler('feePay', dataParams, function(response) {});
					});
				}
			}
			
		},
		error: function(data) {
			$.Showmsg("网络不给力，请稍后再试");
			issubmited = false;
		}
	});
}

$(function() {
	//校验表单信息
	countValNum();
	
	//表单验证
	$(".inputform").Validform({
		tiptype: 1,
		btnSubmit: ".btnSubmit",
		beforeSubmit: function() {
			if(!$('.fee-check').hasClass('has-checked')){
				$.Showmsg('须同意《解放区代收费协议》');
				return false;
			}
			//立即支付费用
			toPay();
			return false;
		},
		callback: function() {}
	});

	//检查触发按钮状态
	$('input').keyup(function() {
		countValNum();
	});
	$('select').change(function() {
		countValNum();
	});

	//后台做记录，前端不做保存
	//保存表单数据 
	/*$("input[type=text],select").change(function() {
		$this = $(this);
		localStorage[$this.attr("name")] = $this.val();
	});*/

	//读取本地存储的数据
/*	if(localStorage) {

		$(".fee-pay-list li").each(function() {
			var thisKey = $(this).find('input').attr('name') || $(this).find('select').attr('name');

			if(localStorage[thisKey]) {
				$('[name=' + thisKey + ']').val(localStorage[thisKey]);
			}
		});

		countValNum();

	}*/
	
	//勾选协议
	$('.fee-check').click(function(){
		$(this).toggleClass('has-checked');
		if($(this).hasClass('has-checked')){
			$(this).attr('src', '../images/fee_icon_checked.png');
		}else{
			$(this).attr('src', '../images/fee_icon_unchecked.png');
		}
	})
	
	//20180105增加宽带缴费
	//选择运营商
	$('#operaters').change(function(){
		var curValue = $(this).val();
		var curDesc = $(this).find('option:selected').attr('data-desc');
		var curName = $(this).find('option:selected').text();
		var curClass = 'text' + curValue;
		
		$('.operater-desc, .operater-text').addClass('dsn');
		if(curValue){
			$('.operater-text span').text(curDesc);
			$('.operater-desc, .operater-text').removeClass('dsn');
		}
		
		$('[name=chargeObject]').val(curName);
	})
	//初始化选中运营商说明文案
	if($('#operaters').val() !== ''){
		$('#operaters').change();
	}
})