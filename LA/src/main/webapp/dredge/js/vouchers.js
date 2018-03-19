
//弹出消费券
$(function(){
	var $prizeBox = $('.prize-box');
	var $checkPayBox = $('.check-pay-box');
	var $doc = $(document);
	$('.prize-list').click(function(){
		if(!$prizeBox.length){
			return false;
		}
		$checkPayBox.hide();
		$prizeBox.fadeIn(100);
		$doc.scrollTop('0px');
	});
	$('#prizeCancel, #prizeCheck').click(function(){
		$prizeBox.hide();
		$checkPayBox.show();
	});
	
});

//维修消费券新算法 20160728
var $prizeListBox = $('.prizeListBox');
var $allCheckBox = $('#allCheckBox');
$(function(){
	//全选按钮，选择反选消费券
	$allCheckBox.click(function(){
		$(this).toggleClass('item-checked');
		$prizeListBox.each(function() {
			var $thisLabel = $(this).find('.item-check-box');
			if($thisLabel.hasClass('item-checked')){
				$thisLabel.addClass('hadSelected').removeClass('item-checked');
			}else if($thisLabel.hasClass('hadSelected')){
				$thisLabel.removeClass('hadSelected').addClass('item-checked');
			}
        });
		prizeCount();
		//设置已选用消费券金额
		var thisHbamout = $('.prize-total').text();
		thisHbamout = thisHbamout == undefined ? 0:thisHbamout;
		$('#couponSelectedVal, #discountPrice').text(thisHbamout);
		$('#orderShouldPrice').text(($('#orderAmountTotalPrice').text() - thisHbamout).toFixed(2));
	});
	
	//按确定按钮
	$('#prizeCheck').click(function(){
		var hasLength = 0;
		$prizeListBox.each(function() {
			if($(this).find('.item-check-box').hasClass('item-checked')){
				hasLength += 1;
			}
		});
		if ( hasLength > 0 ){
			$allCheckBox.addClass('item-checked');	
		}else{
			$allCheckBox.removeClass('item-checked');
			//设置已选用消费券金额为0
			$('#couponSelectedVal, #discountPrice').text('0');
			$('#orderShouldPrice').text($('#orderAmountTotalPrice').text());
		}
	});

	//选择消费券
	$prizeListBox.click(function(){
		var $cBox = $(this).find('.item-check-box');
		
		if($cBox.hasClass('item-checked')){
			$cBox.removeClass('item-checked').addClass('hadSelected');
		}else{

			//已有选中的消费券，才弹出提示
			$prizeListBox.each(function() {
				$('.item-check-box', this).each(function () {
					if ($(this).hasClass("item-checked")) {
						alert('每笔订单限用一张消费券！');
					}
				});
			});
			
			$prizeListBox.find('.item-check-box').removeClass('item-checked hadSelected');
			$cBox.addClass('item-checked hadSelected');
		}
		
		prizeCount();
		//设置已选用消费券金额
		var thisHbamout = $('.prize-total').text();
		$('#couponSelectedVal, #discountPrice').text(thisHbamout);
		$('#orderShouldPrice').text(($('#orderAmountTotalPrice').text() - thisHbamout).toFixed(2));
	});	
	
	prizeCount();
	//设置默认已选用消费券金额
	var defaultHbamout = $('.prize-total').text();
	defaultHbamout = defaultHbamout || 0;
	$('#couponSelectedVal, #discountPrice').text(defaultHbamout);
	$('#orderShouldPrice').text(($('#orderAmountTotalPrice').text() - defaultHbamout).toFixed(2));
});
//消费券叠加计算
function prizeCount() {
	var $prizeListBox = $('.prizeListBox');
	var conts = 0;
	var product_total = parseFloat($("#orderAmountTotalPrice").text()); //商品总价
	var thisUseType;
	
	$prizeListBox.each(function() {
		$('.item-check-box', this).each(function () {
			if ($(this).hasClass("item-checked")) {
				conts += $(this).attr("hbAmout")*1; //乘1将值转换
				thisUseType = $(this).attr('data-useType');
			}
		});
	});
	
	//获取要抵扣的金额（人工费或者耗材费金额）
	if(thisUseType === '3'){
		var maxCouponAmount = $('#costOfLabor').text()*1;
		
	//抵扣订单总金额
	}else if(thisUseType === '7'){
		var maxCouponAmount = $('.orderAmountPrice').text()*1;
		
	}else{
		var maxCouponAmount = $('#costOfItems').text()*1;
	}
	
	$('.prize-total').text(Math.min(conts, maxCouponAmount));
	$('#coupon-total').text("-"+ Math.min(conts, maxCouponAmount));
	$("#shouldPay").text("￥"+ Math.max(product_total -Math.min(conts, maxCouponAmount), 0).toFixed(2));//实付
}

//星级评价
function setMasterStars(obj, widthPercent){
	$(obj).animate({'width':widthPercent}, 1200);
}
