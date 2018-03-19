//商品数量增减
$(function(){
	(function(){
		var $itemNumAdd = $(".btnAdd");
		var $itemNumReduce = $(".btnReduce");
		$itemNumAdd.on("click", function(){
			var $thisItemNum = $(this).siblings(".itemNum");
			var currentValue = $thisItemNum.val();
			currentValue++;
			$thisItemNum.val(currentValue);
		});	
		$itemNumReduce.on("click", function(){
			var $thisItemNum = $(this).siblings(".itemNum");
			var currentValue = $thisItemNum.val();
			if(currentValue>1){
				currentValue--;
				$thisItemNum.val(currentValue);
			};
		});
		//仅限输入大于0的整数
		$('.itemNum').blur(function(){
			var thisVal = $(this).val();
			var reg = /^\+?[1-9]\d*$/;
			if(!reg.test(thisVal)){
				$.Showmsg('数量为大于0的整数');
				$(this).val('1');
			}
		});
		
		//读取设置已选择商品数据
		readItemLocalStorage();
	
		//选择反选消费券
		var $itemListBox = $('.order-info-box');
		var $allCheckBox = $('.item-check-box.total-check');
		$allCheckBox.click(function(){
			var $this = $(this);
			
			if( !$this.hasClass('item-checked') ){
				$itemListBox.each(function() {
					var $thisLabel = $(this).find(".item-check-box.single-check");
					
					if( !$thisLabel.hasClass('item-checked') ){
						$thisLabel.addClass('item-checked').siblings('input').prop('checked', true);	
					}
		        });
		        defaultSelectedNum = cBoxLength;
			}else{
				$itemListBox.each(function() {
					var $thisLabel = $(this).find(".item-check-box.single-check");
					
					if( $thisLabel.hasClass('item-checked') ){
						$thisLabel.removeClass('item-checked').siblings('input').prop('checked', false);	
					}
		        });
		        
		        defaultSelectedNum = 0;
				
			}
	        
	        $(this).toggleClass('item-checked');
	        
		});
	
		//选择消费券
		var defaultSelectedNum = 0;
		var cBoxLength = $('.item-check-box.single-check').length;
		$('.item-check-box.single-check').click(function(){
			var $cBox = $(this);
			
			$cBox.toggleClass('item-checked');
			
			//编辑耗材页面，全选框
			if($('.item-check-box.total-check').length){
				if($cBox.hasClass('item-checked')){
					defaultSelectedNum += 1;
				}else{
					defaultSelectedNum -= 1;
				}
				
				if(cBoxLength === defaultSelectedNum){
					$('.item-check-box.total-check').addClass('item-checked');
				}else{
					$('.item-check-box.total-check').removeClass('item-checked');
				}
			}
			
			//判断【选好了】按钮是否可用
			/*var itemSelectedLength = $('.item-check-box.single-check.item-checked').length;
			isItemHasSelected('#itemHasSelectedBtn', $cBox, itemSelectedLength)*/
		});	
	
	}());
});

//点击选择商品时判断【选好了】按钮是否可用
function isItemHasSelected(btn, obj, num){
	if($(btn).length){
		if($(obj).hasClass('item-checked')){
			num += 1;
		}else{
			num -= 1;
		}
		
		if(num > -1){
			$(btn).removeClass('grey').addClass('bgred white').removeAttr('disabled');
		}else{
			$(btn).removeClass('bgred white').addClass('grey').attr('disabled', 'disabled');
		}
	}
}

//页面加载时判断【选好了】按钮是否可用
function checkItemSelectedNum(btn){
	if($(btn).length){
		var itemSelectedNum = $('.item-check-box.single-check.item-checked').length;
		if(itemSelectedNum > 0){
			$(btn).removeClass('grey').addClass('bgred white').removeAttr('disabled');
		}else{
			$(btn).removeClass('bgred white').addClass('grey').attr('disabled', 'disabled');
		}
	}
}

//读取设置已选择商品数据
function readItemLocalStorage(){
	//判断是否有已选商品
	if(location.href.indexOf('hasSelected') > -1){
		
		var dataStorage = ( JSON.parse(decodeURI(location.href.substring(location.href.indexOf('hasSelected=') + 12))) ).totalItemSelectedInfo;
		
		for(var i = 0; i < dataStorage.length; i++){
			$("#" + dataStorage[i].productId).addClass('item-checked');
			$("#" + dataStorage[i].productId).parents('.single-item-wrap').find('.itemNum').val(dataStorage[i].productQty);
		}
		
	}
	
}

//ios不支持localStorage，也不支持cookies:(((
//清除所有cookie函数  
function clearAllCookie() {  
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);  
    if(keys) {  
        for(var i = keys.length; i--;)  
            document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()  
    }  
} 

//获取已选中耗材数量及价格，计算总额
function saveItemSelectedBoxIdAndNum(){
	
	var itemSaveInfo = {};
	
	$('.order-info-box').each(function(){
		
		var $me = $(this);
		
		if($me.find('.item-check-box.single-check').hasClass('item-checked')){
			
			var thisBoxId = $me.find('.item-check-box.single-check').attr('id');
			var thisNum = $me.find('.itemNum').val();
			
			itemSaveInfo[thisBoxId] = thisNum;
		}
	});
	
	$.cookie('itemSaveInfo', JSON.stringify(itemSaveInfo), { expires: 3650 });
}