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
			
			//设置本地存储
			setItemLocalStorage($thisItemNum);
		});	
		$itemNumReduce.on("click", function(){
			var $thisItemNum = $(this).siblings(".itemNum");
			var currentValue = $thisItemNum.val();
			if(currentValue>1){
				currentValue--;
				$thisItemNum.val(currentValue);
			};
			
			//设置本地存储
			setItemLocalStorage($thisItemNum);
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
		//readItemLocalStorage();
	
		//选择反选消费券
		var $itemListBox = $('.order-info-box');
		var $allCheckBox = $('.item-check-box.total-check');
		$allCheckBox.click(function(){
			var cBoxLength = $('.item-check-box.single-check').length;
			var $this = $(this);
			
			if( !$this.hasClass('item-checked') ){
				$itemListBox.each(function() {
					var $thisItem = $(this).find(".item-check-box.single-check");
					
					if( !$thisItem.hasClass('item-checked') ){
						$thisItem.addClass('item-checked');	
					}
		        });
		        defaultSelectedNum = cBoxLength;
			}else{
				$itemListBox.each(function() {
					var $thisItem = $(this).find(".item-check-box.single-check");
					
					if( $thisItem.hasClass('item-checked') ){
						$thisItem.removeClass('item-checked');	
					}
		        });
		        
		        defaultSelectedNum = 0;
				
			}
	        
	        $(this).toggleClass('item-checked');
	        
		});
	
		//选择消费券
		var defaultSelectedNum = 0;
		$('.item-check-box.single-check').click(function(){
			var cBoxLength = $('.item-check-box.single-check').length;
			var $cBox = $(this);
			
			$cBox.toggleClass('item-checked');
			
			//编辑耗材页面，全选框
			if($('.item-check-box.total-check').length){
				defaultSelectedNum = $('.item-check-box.single-check.item-checked').length;
				
				if(cBoxLength === defaultSelectedNum){
					$('.item-check-box.total-check').addClass('item-checked');
				}else{
					$('.item-check-box.total-check').removeClass('item-checked');
				}
			}
			
			//判断【选好了】按钮是否可用
			/*var itemSelectedLength = $('.item-check-box.single-check.item-checked').length;
			isItemHasSelected('#itemHasSelectedBtn', $cBox, itemSelectedLength);*/
		});	
		
		//删除耗材商品
		$("#itemDeleteBtn").click(function(){
			var $selectedItem = $(".item-check-box.single-check.item-checked");
			var selectedItemLength = $selectedItem.length;
			
			if(selectedItemLength === 0){
				$.Showmsg('请先选择要删除的耗材');
				return false;
			}
			
			$selectedItem.parents('.order-info-box').addClass('removed-item');
			
			setTimeout(function(){
				$selectedItem.parents('.single-item-box').remove();
				
				var unSelectedItemLength = $(".item-check-box.single-check").length;
				if(unSelectedItemLength === 0){
					$('#noItemSelected').removeClass('hide');
				}
			}, 800);
			
			//提交删除
			submitItemDelete();
		});
	}());
});


//保存已选择商品数据
function setItemLocalStorage(obj){
	localStorage[$(obj).attr("id")] = $(obj).val();
}

//读取设置已选择商品数据
function readItemLocalStorage(){
	//新预约单，清空现有商品数据记录
	if(location.hash && location.hash.indexOf('stepNum') === -1){
		localStorage.clear();
	}else if(!location.hash){
		localStorage.clear();
	}
	
	if (localStorage) {
		for(var i=0;i<localStorage.length;i++){
			if(localStorage.key(i).indexOf('itemNum') > -1){
		        //key(i)获得相应的键，再用getItem()方法获得对应的值
				$("#" + localStorage.key(i)).val(localStorage.getItem(localStorage.key(i)));
			}else if(localStorage.key(i).indexOf('cbox') > -1){
				$("#" + localStorage.key(i)).prop('checked', localStorage.getItem(localStorage.key(i)));
				$("#" + localStorage.key(i)).siblings('label').addClass('item-checked');
			}
    	}
	}
	
}

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


//是否已提交
var issubmited = false;
//提交删除耗材
function submitItemDelete(){
	//防止二次提交
	if(issubmited){
		return false;
	}

	$.ajax({
        url: "deleteSelfBuyProduct.json",
        data:{dredgeBillId: getUrlParam('dredgeBillId'), shelfIds:sendItemSelectedId()},
        cache: false,
	    dataType:"json",
		async:false,
		beforeSend: function(){
			$.Showmsg('提交中，请稍后…'); 
		},
		success: function(data){
			//已成功提交，设置issubmited为true
			issubmited = true;
			$.Showmsg('耗材删除成功');

			setTimeout(function(){
				//商品删除完成后再置为可再次删除状态
				issubmited = false;
				
				//判断是否全部删除
				var allNum = $('.item-check-box.single-check').length;
				if(allNum === 0){
					location.href = '../dredge/viewSelfBuyProductList.html?' + getOrderInfo();
				}
			},810);
		},  
		error: function(data){
			$.Showmsg('网络不给力，请稍后重试'); 
		}
	});
}

//订单页面选择更多耗材，+耗材编辑页面，提交已选耗材id及数量
function submitItemSelected(url){
	//防止二次提交
	if(issubmited){
		return false;
	}
	
	$.ajax({
      url: url,
      data:{dredgeBillId: getUrlParam('dredgeBillId'), productList:sendItemSelectedIdAndNum()},
      cache: false,
	    dataType:"json",
		async:false,
		beforeSend: function(){
			$.Showmsg('提交中，请稍后…'); 
		},
		success: function(data){
			//已成功提交，设置issubmited为true
			issubmited = true;
			
			setTimeout(function(){
				location.href = '../dredge/viewSelfBuyProductList.html?' + getOrderInfo();
			},800);
		},  
		error: function(){
			$.Showmsg('网络不给力，请稍后重试'); 
		}
	});
}

//编辑耗材页面，删除耗材，点删除按钮，获取已选中耗材id
function sendItemSelectedId(){
	var itemIdGroup = [];
	
	$('.order-info-box').each(function(){
		
		var $me = $(this);
		
		if($me.find('.item-check-box.single-check').hasClass('item-checked')){
			
			var thisId = $me.attr('data-itemid');
			itemIdGroup.push(thisId);
		}
	});
	
	return JSON.stringify(itemIdGroup);
}

//编辑耗材页面，更改耗材，点确定按钮，获取已选中耗材id及数量
function sendItemSelectedIdAndNum(){
	var totalItemSelectedInfo = [];
	//获取已选中耗材id及数量
	$('.order-info-box').each(function(){
		var singleItemInfo = {};
		var $this = $(this);
		if($this.find('.item-check-box.single-check').hasClass('item-checked')){
			var thisId = $this.attr('data-itemid');
			var thisNum = $this.find('.itemNum').val()*1;
			singleItemInfo.productId = thisId;
			singleItemInfo.productQty = thisNum;
			totalItemSelectedInfo.push(singleItemInfo);
		}
	});
	
	return JSON.stringify(totalItemSelectedInfo);
}


//解析location.search值
function getUrlParam(name) {
	//构造一个含有目标参数的正则表达式对象  
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	//匹配目标参数  
	var r = window.location.search.substr(1).match(reg);
	//返回参数值  
	if (r != null)
		return r[2];
	return null;
}

//获取订单id信息，url参数
function getOrderInfo(){
	var  orderParams = 'dredgeBillId=' + getUrlParam('dredgeBillId') + '&dredgeTypeId=' + getUrlParam('dredgeTypeId') + '&subTypeId=' + getUrlParam('subTypeId');
	 return orderParams;
}
