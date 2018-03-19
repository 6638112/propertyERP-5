//商品数量增减
$(function(){
	var $itemNumAdd = $(".btnAdd");
	var $itemNumReduce = $(".btnReduce");
	$itemNumAdd.on("click", function(){
		var currentValue = $(this).siblings(".itemNum").val();
		currentValue++;
		$(this).siblings(".itemNum").val(currentValue);
	});	
	$itemNumReduce.on("click", function(){
		var currentValue = $(this).siblings(".itemNum").val();
		if(currentValue>1){
			currentValue--;
			$(this).siblings(".itemNum").val(currentValue);
		};
	});
}); 


//选择商品
$(function(){
	
	var $allCheck = $('.item-sort').find('input[type=checkbox]');
	var $allItemCheck = $('#allCheck');
	$allCheck.change(function(){
		var $allBox = $(this).parent().siblings().find('input[type=checkbox]');
		if($(this).is(":checked")){
			$allBox.prop("checked",true).next("label").addClass("item-checked");
			GetCount();
		}else{
			$allBox.prop("checked",false).next("label").removeClass("item-checked");
			GetCount();
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
			GetCount();
		}else{
			$secBox.find('input[type=checkbox]').prop("checked",false).next("label").removeClass("item-checked");
			GetCount();
		}
	});
	
	var $itemBox = $('.sectionBox').children('.info').find('input[type=checkbox]');
	$itemBox.change(function(){
		if($(this).is(":checked")){
			$(this).next("label").addClass("item-checked");
			GetCount();
		}else{
			$(this).next("label").removeClass("item-checked");
			GetCount();
		}
	});
	
}); 
//商品价格计算
function GetCount() {
	
	var $itemSectionBox = $('.sectionBox');
	
	$itemSectionBox.each(function() {
		var conts = 0;
		$('input[name=checkbox]', this).each(function () {
			if ($(this).is(":checked")) {
				conts += $(this).val()*1; //乘1将值转换
			}
		});
		$('.sub-total', this).text((conts));
	});
	
	var allConts = 0;
	$('.sub-total').each(function() {
		allConts += $(this).text()*1;
	});
	$('.all-total').text((allConts));

}

//修改商品规格
$(function(){
	var $standardsModify = $('.standardsModify');
	var $popupBox = $('.popupBox');
	$standardsModify.click(function(){
		var cHeight = document.body.clientHeight;
		var index = $standardsModify.index(this);
		$('body').height(cHeight).css({overflow:'hidden'});
		$popupBox.eq(index).show();
		setTimeout(function() { 
			$popupBox.eq(index).find('.pop-main').addClass('pop-main-click');
			$popupBox.eq(index).find('.item-title').addClass('item-title-click');
		}, 200);
		
	});
	$('.pop-up-btn-check').click(function(){
		$(this).parents('.popupBox').hide();
		$(this).parents('.popupBox').find('.pop-main').removeClass('pop-main-click');
		$(this).parents('.popupBox').find('.item-title').removeClass('item-title-click');
		$('body').height('auto').css({overflow:'auto'});
	});
});

//表单hover
$('.input-text[name=text]').each(function(){
	$(this).focus(function(){
		if (this.value == this.defaultValue){
			this.value = "";
		}
	});
	$(this).blur(function(){
		if (this.value == ""){
			this.value = this.defaultValue;	
		}
	});
});

//规则选择
$(function(){
	var $itemStandard = $(".item-standard .title");
	var $standards = $(".standards span");
	$itemStandard.click(function(){
		$(this).find('.item-arrow-show').swapAddClass('rotateIn', 'rotateOut');
		$(this).siblings().stop(true, false).animate({height: 'toggle'}, 300);
	});	
	$standards.click(function(){
		$(this).addClass('on').siblings().removeClass();
	});	
	
	$.fn.swapAddClass = function(class1, class2){
		return this.each(function(){
			var $elem = $(this);
			if($elem.hasClass(class1)){
				$elem.removeClass(class1).addClass(class2);
			}else if($elem.hasClass(class2)){
				$elem.removeClass(class2).addClass(class1);
			}else{
				$elem.addClass(class1);
			}
		});
	};
}); 
//联系客服、商品分享
$(function(){
	var $popupserver = $('.popupserver');
	$('#servers').click(function(){
		var cHeight = document.body.clientHeight;
		$('body').height(cHeight).css({overflow:'hidden'});
		$popupserver.show();
		setTimeout(function() { 
			$popupserver.find('.pop-server').show().addClass('pop-server-click');
		}, 50);
		return false;
	});
	
	$('#returnServer, .share-close').click(function(){
		$('.pop-code').hide().removeClass('pop-code-click');
		$(this).parents('.popupserver').hide();
		$(this).parents('.pop-server').removeClass('pop-server-click');
		$('body').height('auto').css({overflow:'auto'});
	});

	$('#codeShare').click(function(){
		$(this).parents('.pop-server').hide().removeClass('pop-server-click');
		$('.pop-code').show();
		setTimeout(function() { 
			$('.pop-code').addClass('pop-code-click');
		}, 50);
	});
	
	$('.share-code-close').click(function(){
		$('.pop-code').hide().removeClass('pop-code-click');
		$(this).parents('.popupserver').hide();
		$(this).parents('.pop-server').removeClass('pop-server-click');
		$('body').height('auto').css({overflow:'auto'});
	});
	
});
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
		$("html,body").animate({scrollTop:"0px"},200);
	});
});
//加入购物车
/*$(function(){
	var $joinCartTips = $('.join-cart');
	$joinCartTips.hide();
	$('#btnCart').click(function(){
		var cartNum = $('#cartNum').text();
		cartNum++;
		$joinCartTips.show();
		$('#cartNum').text(cartNum)
		setTimeout(function(){
			$joinCartTips.hide();
		}, 1200);
	});	
});*/
//会员信息编辑
$(function(){
	$('#userInfoEdit').click(function(){
		if($(this).children('span').text() == '确定'){
			$('.user-info-text').find('input').removeClass('black').addClass('grey').prop('readonly',true);
			$(this).children('span').text('编辑');
		}else{
			$('.user-info-text').find('input').removeClass('grey').addClass('black').prop('readonly',false);
			$('.user-info-text').find('input').eq(0).focus();
			$(this).children('span').html('确定')
		}
	});	
});
//分享到微信朋友圈
$(function(){	
	$('#shareTofriends').click(function(){
		var localLink = window.location.href;
		var imgUrl = $('#slideBox').find('li').eq(0).children('img').attr('src');
		var imgLink = window.location.host + '/webapp/' + imgUrl;
		if (typeof WeixinJSBridge == "undefined") {
			alert(" 请先通过微信搜索 【花样年文旅】 添加为好友，通过微信分享宝贝 :) ");
			} else {
			WeixinJSBridge.invoke('shareTimeline', {
			"title": "花样年文旅",
			"link": localLink,
			"desc": " 商品宝贝 ",
			"img_url": imgLink
			});
		}
	});
});
//弹出消费券
$(function(){
	var $prizeBox = $('.prize-box');
	var $checkPayBox = $('.check-pay-box');
	var $doc = $(document);
	$('.prize-list').click(function(){
		$checkPayBox.hide();
		$prizeBox.fadeIn(100);
		$doc.scrollTop('0px');
	});
	$('#prizeCancel, #prizeCheck').click(function(){
		$prizeBox.fadeOut();
		$checkPayBox.show();
	});
	
});

//选择反选消费券
$(function(){
	var $prizeListBox = $('.prizeListBox');
	var $allCheckBox = $('#allCheckBox');
	$allCheckBox.click(function(){
		$(this).toggleClass('item-checked');
		$prizeListBox.each(function() {
            $('input[name=checkbox]', this).each(function () {
				if( $(this).hasClass('toggleCheck') ){
					$(this).prop('checked', true).removeClass('toggleCheck').siblings('label').addClass('item-checked');	
				}else if( $(this).is(":checked") ){
					$(this).addClass('toggleCheck').prop('checked', false).siblings('label').removeClass('item-checked');	
				}
			});
        });
		prizeCount();
	});
	
	//按确定按钮
	$('#prizeCheck').click(function(){
		$prizeListBox.each(function() {
			$('input[name=checkbox]', this).each(function () {
				if ( $('input[name=checkbox]:checked').length ){
					$$allCheckBox.addClass('item-checked');	
					$(this).removeClass('toggleCheck');
				}
			});
		});
	});
});

//选择消费券
$(function(){
	$('.prizeListBox').click(function(){
		var $cBox = $(this).find('.item-check-box');
		$cBox.toggleClass('item-checked');
		if($cBox.hasClass('item-checked')){
			$cBox.siblings('input').prop('checked', true);	
		}else{
			$cBox.siblings('input').prop('checked', false);	
		}
		prizeCount();
	});	
});
//消费券叠加计算
function prizeCount() {
	var $prizeListBox = $('.prizeListBox');
	var conts = 0;
	$prizeListBox.each(function() {
		$('input[name=checkbox]', this).each(function () {
			if ($(this).is(":checked")) {
				conts += $(this).val()*1; //乘1将值转换
			}
		});
		$('.prize-total').text(conts);
	});
}
$(function(){
	prizeCount();
});