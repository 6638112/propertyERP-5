//请求消费券接口

var $prizeListBox = $('.coupon-box');

function getCoupon(){
	
	$.ajax({
		url: "../temporaryParkingFee/qryCarCouponWithUserId.json",
		dataType:"json",
		success: function(data){
			
			if(data.status === '0000'){
				var dataCoupon = data.dataValue;
				
				if(JSON.stringify(dataCoupon) !== '{}'){
					$prizeListBox.find('[name=cpId]').val(dataCoupon.ucId).attr('hbAmout', dataCoupon.discountMoney);
					$prizeListBox.find('.coupon-name').text(dataCoupon.couponName);
					$prizeListBox.find('.coupon-useEndDate').text(dataCoupon.useEndDate);
					$prizeListBox.removeClass('dsn');
					
					//计算优惠后费用
					var realFee = $('.realFee').text();
					realFee = realFee*1 - dataCoupon.discountMoney;
					realFee = realFee < 0 ? 0 : realFee;
					$('.discountRealFee').text(realFee.toFixed(2)).removeClass('dsn');
					$('#totalFeeBox').removeClass('dsn');
					
				}else{
					$('.realFee').removeClass('dsn');
				}
			}
			
		},  
		error: function(){  
			alert('网络不给力，请稍后重试'); 
		}
	});
}

function prizeCount(discountMoney){
	
	var discountMoney = 0;
	if($prizeListBox.find('label').hasClass('item-checked')){
		discountMoney = $prizeListBox.find('[name=cpId]').attr('hbAmout')*1;
	}
	
	var realFee = $('.realFee').text();
	var payFee =  realFee*1 - discountMoney;
	
	payFee = payFee < 0 ? 0 : payFee;
	
	$('.discountRealFee').text(payFee.toFixed(2));
}
$(function(){
	
	//获取消费券
	getCoupon();
	
	//选择消费券
	$prizeListBox.click(function(event){
		event.stopPropagation();
		var $cBox = $(this).find('.item-check-box');
		
		if($(this).find('label').hasClass('item-checked')){
			$(this).find('label').removeClass('item-checked');
		}else{
			$(this).find('label').addClass('item-checked');
		}
		prizeCount();
		
	});	
	
}); 
