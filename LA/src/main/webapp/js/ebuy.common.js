
//获取可用粮票金额
var balance = 0.00;
function getTicketAmount(){
	var merchantIds = [];
	if(location.search.indexOf('limitbuy') > -1){
		merchantIds.push($('.merchant-price').attr('data-storeid'));
	}else{
		$('.item-no-deliver').each(function(){
			merchantIds.push($(this).attr('data-merchantid'));
		})
	}
	$.ajax({
		type: 'post',
		url:'../common/toUrl.do',
		data:{'toPayAmount': $('.items-total').text().trim(), 'merchantIds': JSON.stringify(merchantIds), 'detailUrl': '/redenvelope/qryBalanceWithPercent.json'},
		success: function(data){
			if(data.status === '0000'){
				balance = (data.dataValue.ext_balancePercent).toFixed(2);
				$('#ticketAmount').val(balance);
				$('.ticket-amount').text(balance);
				
				if(balance > 0){
					$('.has-ticket').removeClass('dsn');
					$('.ticket-text').removeClass('mleft10');
					$('#ticketCheck').click();
				}
			}
		}
	})
}

function showMsg(txt){
	$(".pop-tips-text").text(txt);
	if($(".pop-tips").is(':visible')){
		return;
	}
	$(".pop-tips").fadeIn();
	setTimeout(function(){
		$(".pop-tips").fadeOut();
	}, 2000)
}
//仅限输入大于0的数字，可带两位小数
function numberFixedTwo(obj){
    var partten = /^[0-9]+(.[0-9]{0,2})?$/;
    var objVal = $(obj).val().trim();
    if(objVal !== '' && !partten.test(objVal)){
        $(obj).val('');
        showMsg('请输入数字，可带两位小数');
        return;
    }
}
//仅限输入大于0的整数
function onlyNumber(obj){
	var partten = /^[1-9]\d*$/;
	if(!partten.test($(obj).val().trim())){
		$(obj).val('1');
	}
}
//计算商品数量、价格
function ShoppingCartEditGetCount() {
	
	var $itemSectionBox = $('.sectionBox');
	
	$itemSectionBox.each(function() {
		var thisTotalNum = 0;
		var thisConts = 0;
		$('.info', this).each(function () {
			var thisNum = $(this).find('input.itemNum').val()*1;
			var thisPrice = $(this).find('.singleItemPrice').text()*1;
			thisTotalNum += thisNum;
			thisConts += thisNum*thisPrice; //乘1将值转换
		});
		$('.subTotalNum', this).text(thisTotalNum);
		$('.subTotalPrice', this).text(thisConts.toFixed(2));
	});

}

//计算购物车已勾选商品的价格合计
function GetCount() {
	var $itemSectionBox = $('.sectionBox');
	$itemSectionBox.each(function() {
		var conts = 0;
		$('input[name=ptId]', this).each(function () {
			if ($(this).is(":checked")) {
				conts += $(this).prop("alt")*1;
			}
		});
		$('.sub-total', this).text((conts).toFixed(2));
	});
	
	var allConts = 0;
	$('.sub-total').each(function() {
		allConts += $(this).text()*1;
	});
	$('.all-total').text((allConts).toFixed(2));
}

//购物车页面商品数量计算，分别计算每个店铺
function shopItemNumCount() {
	
	var $itemSectionBox = $('.sectionBox');
	
	$itemSectionBox.each(function() {
		var num = 0;
		$('input[name=ptId]', this).each(function () {
			if ($(this).is(":checked")) {
				num += $(this).parents('.info').find('.shopSingleNum').text()*1; //乘1将值转换
			}
		});
		$('.ShopTotalNum', this).text(num);
	});
	
	var allNums = 0;
	$('.ShopTotalNum').each(function() {
		allNums += $(this).text()*1;
	});
	$('.AllTotalNum').text(allNums);

}


//遍历选择为自提的货单
function getNoDeliveryList(){
	var merchantIdDeliveryTypeList = [];
	$('.item-no-deliver').each(function(){
		var noDeliveryItem = {'deliveryType': 1};
		if($(this).hasClass('item-checked')){
			noDeliveryItem.merchantId = $(this).attr('data-merchantid');
			merchantIdDeliveryTypeList.push(noDeliveryItem);
			if(location.search.indexOf('limitbuy') > -1){
				$('[name=isSelfGet]').val(1);
			}
		}else{
			if(location.search.indexOf('limitbuy') > -1){
				$('[name=isSelfGet]').val(0);
			}
		}
	})
	$('[name=merchantIdDeliveryTypeList]').val(JSON.stringify(merchantIdDeliveryTypeList));
}

//消费券叠加计算
function prizeCount() {
	var $prizeListBox = $('.prizeListBox');
	var conts = 0;	//已选中优惠额度(消费券或粮票)
	var product_total = parseFloat($("#product-total").text().substr(1)); //商品总价
	var transport_total =  parseFloat($("#transport-total").text()); //运费合计
	var thisUseType, thisMerchantId;
	var discountidlist, selectedId;
	var maxCouponAmount;	//最大抵扣额度
	
	//粮票没有勾选，消费券已勾选
	if(!$('#ticketCheck').hasClass('item-checked') && $allCheckBox.hasClass('item-checked')){
		//获取选中消费券
		var $couponChecked = $('[name=cpId]:checked');
		
		if($couponChecked.length > 0){
			conts += $couponChecked.attr("hbAmout")*1; //乘1将值转换
			thisMerchantId = $couponChecked.attr('data-merchantId');
			thisUseType = $couponChecked.attr('data-useType');
			
			if($couponChecked.attr('data-discountidlist')){
				discountidlist = JSON.parse($couponChecked.attr('data-discountidlist'));
			}
		}
			
		//已勾选券为定向店铺券，最大抵扣额度取该店铺下的商品小计
		if(thisUseType === '5'){
			//获取定向券对应的供应商价格，用于和券面额对比，取小者
			maxCouponAmount = $('#merchant-id-'+thisMerchantId).text()*1;
		
		//已勾选券为定向商品券20170719，最大抵扣额度取所有该类商品小计
		}else if(thisUseType === '6'){
			
			var discountPtTotal = 0;
				
			$('[name=productIdQty]').each(function(){
				var curPtId = $(this).attr('data-ptId');
				var curPtTotal = $(this).attr('data-ptTotal')*1;
				
				discountidlist.forEach(function(val){
					
					//包含在选中消费券中的商品，方可抵扣
					if(val == curPtId){
						discountPtTotal += curPtTotal;
					}
					
				})
				
			})
			
			maxCouponAmount = discountPtTotal;
			
		}else{
			var getTotalPrice = setInterval(function(){
				if($('.items-total').text() !== ''){
					maxCouponAmount = $('.items-total').text()*1;	//maxCouponAmount为之前预留百分比金额，目前所有券按面额抵扣，不再使用该字段
					clearInterval(getTotalPrice);
				}
			},100);
		}
		
	//粮票没有勾选，消费券也没有勾选
	}else if(!$('#ticketCheck').hasClass('item-checked') && !$allCheckBox.hasClass('item-checked')){
		conts = 0;
		maxCouponAmount = $('.items-total').text()*1;
		
	//已勾选粮票，消费券没有勾选
	}else{
		var ticketVal = $('#ticketAmount').val()*1 || 0;
		conts = ticketVal;
		maxCouponAmount = $('.items-total').text()*1;
	}
	
	var setPrice = setInterval(function(){
		if(maxCouponAmount !== undefined){
			clearInterval(setPrice);
			$('.prize-total').text(Math.min(conts, maxCouponAmount).toFixed(2));
			$('#coupon-total').text("-￥"+ Math.min(conts, maxCouponAmount).toFixed(2));
			$("#shouldPay").text(Math.max(product_total+ transport_total -Math.min(conts, maxCouponAmount), 0).toFixed(2));//实付
		}
	},100);
}

//jQuery plugin to prevent double submission of forms
jQuery.fn.preventDoubleSubmission = function() {
$(this).on('submit',function(e){
var $form = $(this);

if ($form.data('submitted') === true) {
  // Previously submitted - don't submit again
  e.preventDefault();
} else {
  // Mark it so that the next submit can be ignored
  $form.data('submitted', true);
}
});

//Keep chainability
return this;
};

$('form').preventDoubleSubmission();

//购物车编辑页面加载时计算商品数量、价格
ShoppingCartEditGetCount();

//计算购物车已勾选商品的价格合计
GetCount();

//页面加载时计算商品数量
shopItemNumCount();

//获取粮票金额
getTicketAmount();

$(function(){
	//商品数量增减
	var $itemNumAdd = $(".btnAdd");
	var $itemNumReduce = $(".btnReduce");
	$itemNumAdd.on("click", function(){
		var currentValue = $(this).siblings(".itemNum").val();
		currentValue++;
		$(this).siblings(".itemNum").val(currentValue);
		ShoppingCartEditGetCount();
	});	
	$itemNumReduce.on("click", function(){
		var currentValue = $(this).siblings(".itemNum").val();
		if(currentValue>1){
			currentValue--;
			$(this).siblings(".itemNum").val(currentValue);
			ShoppingCartEditGetCount();
		};
	});
	
	//商品详情页仅限输入大于0的整数
    $('.itemNum').on('keyup blur',function(){
    	onlyNumber('.itemNum');
    });
    
    //确认支付页面输入粮票金额
    $('#ticketAmount').on('keyup blur',function(){
    	var maxAmount = $('.ticket-amount').text()*1;
    	numberFixedTwo('#ticketAmount');
    	if($(this).val() > maxAmount){
    		showMsg('输入金额不能大于可用金额');
    		$(this).val('');
    		return;
    	}
    	if($('#ticketCheck').hasClass('item-checked')){
    		prizeCount();
    	}
    });

    
	//购物车选择商品
	var $allCheck = $('.item-sort').find('input[type=checkbox]');
	var $allItemCheck = $('#allCheck');
	$allCheck.change(function(){
		var $allBox = $(this).parent().siblings().find('input[type=checkbox]');
		if($(this).is(":checked")){
			$allBox.prop("checked",true).next("label").addClass("item-checked");
			GetCount();
			shopItemNumCount();
		}else{
			$allBox.prop("checked",false).next("label").removeClass("item-checked");
			GetCount();
			shopItemNumCount();
		}
		var allCheckLength = $allCheck.length;
		var allCheckedLength = $('.item-sort').find('input[type=checkbox]:checked').length;
		if(allCheckLength == allCheckedLength){
			$allItemCheck.prop("checked",true).next("label").addClass("item-checked");
		}else{
			$allItemCheck.prop("checked",false).next("label").removeClass("item-checked");
		}
	})
	
	var $secBox = $('.sectionBox');
	$secBox.each(function() {
		var $allBox = $(this).children('.info').find('input[type=checkbox]');
		$allBox.change(function(){
			var allBoxLength = $allBox.length;
			var allCheckLength = $(this).parents('.sectionBox').children('.info').find('input[type=checkbox]:checked').length;
			var $allSelect = $(this).parents('.info').siblings('.item-sort').find('input[type=checkbox]');
			if(allBoxLength == allCheckLength) {
				$allSelect.prop("checked",true).next("label").addClass("item-checked");
				var allCheckLength = $allCheck.length;
				var allCheckedLength = $('.item-sort').find('input[type=checkbox]:checked').length;
				if(allCheckLength == allCheckedLength){
					$allItemCheck.prop("checked",true).next("label").addClass("item-checked");
				}
			}else{
				$allSelect.prop("checked",false).next("label").removeClass("item-checked");
				$allItemCheck.prop("checked",false).next("label").removeClass("item-checked");
			}
		});
	});
	
	var $cBox = $('body').find('input[type=checkbox]');
	//$cBox.hide();
	$cBox.change(function() {
		if($(this).is(":checked")){
			$(this).next("label").addClass("item-checked");
		}else{
			$(this).next("label").removeClass("item-checked");
		}
	});
	
	$allItemCheck.change(function(){
		if($(this).is(":checked")){
			$secBox.find('input[type=checkbox]').prop("checked",true).next("label").addClass("item-checked");
			$("#cbox01").prop("checked",true);
			GetCount();
			shopItemNumCount();
		}else{
			$secBox.find('input[type=checkbox]').prop("checked",false).next("label").removeClass("item-checked");
			$("#cbox01").prop("checked",false);
			GetCount();
			shopItemNumCount();
		}
	});
	
	var $itemBox = $('.sectionBox').children('.info').find('input[type=checkbox]');
	$itemBox.change(function(){
		if($(this).is(":checked")){
			$(this).next("label").addClass("item-checked");
			GetCount();
			shopItemNumCount();
		}else{
			$(this).next("label").removeClass("item-checked");
			GetCount();
			shopItemNumCount();
		}
	});
	
}); 

var $prizeListBox = $('.prizeListBox');
var $allCheckBox = $('#allCheckBox');
//返回顶部
$(function(){
	$("#gotop").hide();
	$(window).scroll(function(){
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop;
		if( scrollt > 100 ){
			$("#gotop").show();
		}else{
			$("#gotop").hide();
		}
	});
	$("#gotop").click(function(){
		$("html,body").scrollTop('0px');
	});
	
	//弹出消费券
	var $prizeBox = $('.prize-box');
	var $checkPayBox = $('.check-pay-box');
	var $doc = $(document);
	$('.prize-list').click(function(){
		if($('#allCheckBox').length > 0){
			$checkPayBox.hide();
			$prizeBox.fadeIn(100);
			$doc.scrollTop('0px');
		}
	});
	$('#prizeCancel, #prizeCheck').click(function(){
		$prizeBox.fadeOut();
		$checkPayBox.show();
	});
	
	//勾选粮票结算
	$('#ticketCheck').click(function(){
		if($(this).hasClass('item-checked')){
			$(this).removeClass('item-checked');
		}else if($allCheckBox.hasClass('item-checked')){
			$allCheckBox.click();
			$(this).addClass('item-checked');
			$('#discountName').text('粮票抵扣');
		}else{
			$(this).addClass('item-checked');
			$('#discountName').text('粮票抵扣');
		}
		prizeCount();
	})
	
	//全选按钮，选择反选消费券
	$allCheckBox.click(function(){
		if($('#ticketCheck').hasClass('item-checked')){
			$('#ticketCheck').removeClass('item-checked');
		}
		$(this).toggleClass('item-checked');
		$('#discountName').text('消费券抵扣');
		$prizeListBox.each(function() {
			var $thisLabel = $(this).find('label');
			if($thisLabel.hasClass('item-checked')){
				$thisLabel.addClass('hadSelected').removeClass('item-checked');
				$thisLabel.siblings('input').prop('checked', false);
			}else if($thisLabel.hasClass('hadSelected')){
				$thisLabel.removeClass('hadSelected').addClass('item-checked');
				$thisLabel.siblings('input').prop('checked', true);
			}
        });
		prizeCount();
		//设置已选用消费券金额
		var thisHbamout = $('.prize-total').text();
		thisHbamout = thisHbamout == undefined ? 0:thisHbamout;
		
		//促销商品，获取已选中消费券金额
		if($('.prizeListBox').length){
			$('.prizeListBox').each(function(){
				var $thisChecked = $(this).find('.item-check-box.single-check.item-checked');
				if($thisChecked.length){
					thisHbamout = $thisChecked.siblings('input').attr('hbamout');
					$('[name=couponId]').val($thisChecked.siblings('input').attr('id'));
				}
			})
		}
		
		$('#couponSelectedVal').text((+thisHbamout).toFixed(2));
	});
	
	//按确定按钮
	$('#prizeCheck').click(function(){
		var hasLength = 0;
		$prizeListBox.each(function() {
			if($(this).find('label').hasClass('item-checked')){
				hasLength += 1;
			}
		});
		if ( hasLength > 0 ){
			$allCheckBox.addClass('item-checked');	
		}else{
			$allCheckBox.removeClass('item-checked');
			//设置已选用消费券金额为0
			$('#couponSelectedVal').text('0.00');
		}
	});

	//选择消费券
	$prizeListBox.click(function(event){
		event.stopPropagation();
		var $cBox = $(this).find('.item-check-box');
		var priceTotal = $('.items-total').text()*1;
		
		if( event.target.tagName != 'LABEL' && event.target.tagName != 'INPUT'){
			
			//已勾选粮票，先反选粮票
			if($('#ticketCheck').hasClass('item-checked')){
				$('#ticketCheck').click();
			}
			
			if($cBox.siblings('input').prop('checked')){
				
				$cBox.siblings('input').prop('checked', false);
			} else {
				//已有选中的消费券，才弹出提示
				$prizeListBox.each(function() {
					$('input[name=cpId]', this).each(function () {
						if ($(this).is(":checked")) {
							showMsg('每笔订单限用一张消费券！');
						}
					});
				});
				
				$cBox.siblings('input').prop('checked', true);
			}
			
			if($(this).find('label').hasClass('item-checked')){
				$(this).find('label').removeClass('item-checked').addClass('hadSelected');
			}else{
				$prizeListBox.find('label').removeClass('item-checked hadSelected');
				$(this).find('label').addClass('item-checked hadSelected');
				//设置全选
				$allCheckBox.addClass('item-checked');
			}
			
			prizeCount();
			
			//设置已选用消费券金额
			var thisHbamout = $('.prize-total').text();
			
			//促销商品，获取已选中消费券金额
			if($('.prizeListBox').length){
				$('.prizeListBox').each(function(){
					var $thisChecked = $(this).find('.item-check-box.single-check.item-checked');
					if($thisChecked.length){
						thisHbamout = $thisChecked.siblings('input').attr('hbamout');
						$('[name=couponId]').val($thisChecked.siblings('input').attr('id'));
					}
				})
			}
			
			//$('#couponSelectedVal').text(Math.min((+thisHbamout),priceTotal).toFixed(2));
			$('#couponSelectedVal').text((+thisHbamout).toFixed(2));
			
		}
	});	
	
	prizeCount();
	//设置默认已选用消费券金额
	//var defaultHbamout = $prizeListBox.eq(0).find('input[name=cpId]').attr('hbamout');
	var defaultHbamout = $('.prize-total').text();
	
	//促销商品，获取已选中消费券金额
	if($('.prizeListBox').length){
		$('.prizeListBox').each(function(){
			var $thisChecked = $(this).find('.item-check-box.single-check.item-checked');
			if($thisChecked.length){
				defaultHbamout = $thisChecked.siblings('input').attr('hbamout');
				$('[name=couponId]').val($thisChecked.siblings('input').attr('id'));
			}
		})
	}
	
	$('#couponSelectedVal').text((+defaultHbamout).toFixed(2));
	
	//选择快递或自提20170807
	$('.deliver-box').click(function(){
		if(!$(this).find('.item-check-box').hasClass('item-checked')){
			var $this = $(this);
			//应付、运费合计、实付、总金额
			var itemSubTotal = $this.siblings('.item-sub-total').find('.merchant-price').text()*1;
			var transportTotal = $('#transport-total').text()*1;
			var shouldPayTotal = $('#shouldPay').text()*1;
			var itemsCurTotal = $('.items-total').text()*1;
			
			//获取当前货单运费
			var deliverFee = 0;
			
			//需要运费
			if($this.find('.item-no-deliver').length === 0){
				deliverFee = $this.find('.item-deliver-fee').text()*1;
				
				//计算应付、运费合计、实付、总金额
				$this.siblings('.item-sub-total').find('.merchant-price').text((itemSubTotal + deliverFee).toFixed(2));
				$('.items-total').text((itemsCurTotal + deliverFee).toFixed(2));
				$('#transport-total').text((transportTotal + deliverFee).toFixed(2));
				if(shouldPayTotal !== 0){
					$('#shouldPay').text((shouldPayTotal + deliverFee).toFixed(2));
				}
				
				//粮票及订单合计对比，取小者
				$('#ticketAmount').val( Math.min((itemsCurTotal + deliverFee).toFixed(2),balance) );
				$('.ticket-amount').text( Math.min((itemsCurTotal + deliverFee).toFixed(2),balance) );
				
			//自提，不需要运费
			}else{
				deliverFee = $this.siblings('.deliver-box').find('.item-deliver-fee').text()*1;
				
				//计算应付、运费合计、实付、总金额
				$this.siblings('.item-sub-total').find('.merchant-price').text((itemSubTotal - deliverFee).toFixed(2));
				$('.items-total').text((itemsCurTotal - deliverFee).toFixed(2));
				$('#transport-total').text((transportTotal - deliverFee).toFixed(2));
				$('#shouldPay').text(((shouldPayTotal - deliverFee) < 0 ? 0 : (shouldPayTotal - deliverFee)).toFixed(2));
				
				//粮票及订单合计对比，取小者
				$('#ticketAmount').val( Math.min((itemsCurTotal - deliverFee).toFixed(2),balance) );
				$('.ticket-amount').text( Math.min((itemsCurTotal - deliverFee).toFixed(2),balance) );
			}
			
			//勾选
			$this.find('.item-check-box').addClass('item-checked');
			$this.siblings('.deliver-box').find('.item-check-box').removeClass('item-checked');
			
			//重新计算券抵扣
			prizeCount();
		}
	});
	
});
