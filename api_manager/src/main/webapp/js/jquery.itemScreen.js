/**!
/* 搜索、选择、反选与删除元素
 * @author	Weijc
 * @date	2016-06
 */

;(function($){
    var itemScreen = {
		defaultOptions: {
			
			//下拉框配置项
			selectChangeObj: '.addressAreaSelect',		//下拉框对象
			
			//选择配置项
			selectObj: '.demo-box.search-box li:visible',	//要选择的对象
			deleteObj: '.demo-box.selected-box li .icon-delete',	//要删除的对象
			itemsSelected: '.demo-box.selected-box:visible li:visible',		//已选对象
			itemDemo: '.address-selected.dsn',	//用来克隆的对象		
			itemDemoBox: '.demo-box.selected-box:visible',	//用来插入克隆的容器对象	
			validInput: '.select-input',	//校验已选数量
			
			itemClickCountNum: 0,	//点击记数，每次点击选择+1，用作addressSelectedClass-后缀，添加一个按序号增加的data-class，用作反选定位
			itemSelectedCountNum: 0,		//选中记数，每选中一个+1，反选一个-1
			
			//多选or单选，默认为多选
			multiple: true,
			multipleTipsmsg: '该小区已选择！',
			singleTipsMsg: '只能选择一个自提点！',
			
			searchType: 0	//搜索类型。0表示城市和小区搜索，1表示商品搜索，默认为0
			
		},
		changeUserRange: function(){
			var me = this;
			//选择用户范围
			$(me.defaultOptions.selectChangeObj).change(function(){
				var curVal = $(this).val(),
					$swapCon = $(this).parents('tr').nextAll('.swap-con'),
					$swapBoxCommon = $(this).parents('tr').nextAll('.swap-box-common');
				
				//隐藏所有内容
				$swapCon.hide().find('input.input_text, select.select_normal').attr('ignore', 'ignore');
				$swapBoxCommon.hide();
				
				//显示对应的swap-box-
				var $curSwapVal = $(this).parents('tr').nextAll('.swap-box-' + curVal);
				
				if($curSwapVal.length > 0){	
					$swapBoxCommon.show();
					
					$curSwapVal.show().find('input.input_text, select.select_normal').attr('ignore', '');
					
					//获取已选城市个数
					var curNum = $(me.defaultOptions.itemsSelected).length;
					if( curNum == ''){
						me.defaultOptions.itemSelectedCountNum = 0;
					}else{
						me.defaultOptions.itemSelectedCountNum = curNum;
					}
				}else{
					me.defaultOptions.itemSelectedCountNum = 0;
				}
				
				//重置页面高度
				window.parent.TuneHeight();
			});
		},
		itemSelectAndDelete: function(){
			var me = this;
			
			//选择城市/小区
			$(document).on("click", me.defaultOptions.selectObj, function(){ 
				var $this = $(this);
				//选择
				if(!$this.hasClass('on')){
					var addressSelectedClass = 'address-selected-';		//每选择一个，添加一个按序号增加的data-class，用作反选定位
					var $addressSelectedLiClone = $(me.defaultOptions.itemDemo).clone(true);
					var thisName = $this.find('.address-name').text();
					var thisObjName = $this.find('.data-obj-name').text();
					var thisItemId = $this.find('.address-name').attr('data-itemid');
					
					//单选or多选
					switch(me.defaultOptions.multiple){
						case false:
						
							//只能选择一个自提点
							if(me.defaultOptions.itemSelectedCountNum == 1){
								alert(me.defaultOptions.singleTipsMsg);
								return false;
							}
							break;
						default:
						
							//防止重复选择
							var areaSelectedNum = 0;
							$(me.defaultOptions.itemsSelected).each(function(){
								var thisAreaName = $(this).find('.address-name').text();
								if(thisAreaName === thisName){
									areaSelectedNum += 1;
								}
							})
							if(areaSelectedNum > 0){
								alert(me.defaultOptions.multipleTipsmsg);
								return false;
							}
							break;
					}
					
					me.defaultOptions.itemClickCountNum += 1;
					me.defaultOptions.itemSelectedCountNum += 1;
					
					//已选城市/小区，校验通过
					if(me.defaultOptions.itemSelectedCountNum > 0){
						$(me.defaultOptions.itemDemoBox).siblings(me.defaultOptions.validInput).val(me.defaultOptions.itemSelectedCountNum);
						$(".inputform").Validform({}).check(false, $(me.defaultOptions.itemDemoBox).siblings(me.defaultOptions.validInput));
					}
					
					addressSelectedClass = addressSelectedClass + me.defaultOptions.itemClickCountNum;
					
					
					$this.addClass('on ' + addressSelectedClass).attr('data-class',addressSelectedClass);
					$addressSelectedLiClone.find('.address-name').text(thisName);
					$addressSelectedLiClone.find('.data-obj-name').text(thisObjName);
					$addressSelectedLiClone.find('.data-obj-id').text(thisItemId);
					$addressSelectedLiClone.find('input').val(thisItemId);
					$addressSelectedLiClone.removeClass('dsn').addClass(addressSelectedClass).attr('data-class',addressSelectedClass).appendTo(me.defaultOptions.itemDemoBox);
				//反选
				}else{
					var thisUnSelectedClass = $this.attr('data-class');
					
					$this.removeClass();
					$(me.defaultOptions.itemDemoBox).find('.' + thisUnSelectedClass).remove();
					me.defaultOptions.itemSelectedCountNum -= 1;
					
					//没有选择城市/小区，校验不通过
					if(me.defaultOptions.itemSelectedCountNum == 0){
						$(me.defaultOptions.itemDemoBox).siblings(me.defaultOptions.validInput).val('');
						$(".inputform").Validform({}).check(false, $(me.defaultOptions.itemDemoBox).siblings(me.defaultOptions.validInput));
					}
				}
				//重置页面高度
				window.parent.TuneHeight();
			});
			
			//删除城市/小区
			$(document).on("click", me.defaultOptions.deleteObj, function(){ 
				var $this = $(this);
				var thisDeleteClass = $this.parent('li').attr('data-class');
				
				if($('.' + thisDeleteClass)){
					$('.' + thisDeleteClass).removeClass('on');
				}
				$this.parent('li').remove();
				
				me.defaultOptions.itemSelectedCountNum -= 1;
				
				//没有选择城市/小区，校验不通过
				if(me.defaultOptions.itemSelectedCountNum == 0){
					$(me.defaultOptions.itemDemoBox).siblings(me.defaultOptions.validInput).val('');
					$(".inputform").Validform({}).check(false, $(me.defaultOptions.itemDemoBox).siblings(me.defaultOptions.validInput));
				}
				//重置页面高度
				window.parent.TuneHeight();
			});
		},
		ajaxSearch: function(url, objBox){
			var me = this;
			
			$.getJSON(url, function(data){
				
				var $objBox = $(objBox);
				var searchList = '';
				
				$.each(data, function(i, ssList){	
					
					switch(me.defaultOptions.searchType){
						case 0:
							//小区搜索，带详细地址
							if(ssList.addressDesc){
								if(ssList.name.indexOf(currentVal) > -1 || ssList.addressDesc.indexOf(currentVal) > -1){
									searchList += '<li><span class="address-name" data-itemid="' + ssList.id + '">' + ssList.name + '</span><br><span class="grey">地址：' + ssList.addressDesc + '</span></li>';
								}
								
							//城市搜索，不带详细地址
							}else if(ssList.name.indexOf(currentVal) > -1){
								searchList += '<li><span class="address-name" data-itemid="' + ssList.id + '">' + ssList.name + '</span></li>';
							}
							break;
							
						case 1:
			                //商品搜索，带ID、供应商
			                if(ssList.name.indexOf(currentVal) > -1){
			                    searchList += '<li><span class="address-name" data-itemid="'+ssList.id+'">' + ssList.name + '</span><br><span class="grey">ID：' + ssList.addressDesc + '</span><span class="grey mleft10">供应商：海吉星</span></li>';
			                }
			                break;
	               }
				});
				
				if(searchList == ''){
					searchList = '<span>' + '没有找到相关内容' + '</span>';	
				}
				
				$objBox.html(searchList);
				
				//重置页面高度
				window.parent.TuneHeight();
			});	
		}
	};
	
	$.itemScreenStart = function(newOptions){
		var newItemScreen = Object.create(itemScreen);
		//重设配置项
		newItemScreen.defaultOptions = $.extend({}, newItemScreen.defaultOptions, newOptions);
		return newItemScreen;
	}
	
})(jQuery);

