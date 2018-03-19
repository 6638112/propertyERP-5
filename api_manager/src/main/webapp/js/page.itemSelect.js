/**!
/* 弹出框-搜索、选择、反选与删除元素；
 * @author	Weijc
 * @date	2017-12
 */

$(function(){
	$('input.h5LinkUrl').blur(function(){
		var $this = $(this);
		$this.parents("table").find("input[name=jumpParam]").val($this.val());
	})
	
    //表单验证-活动配置
	$('#activityForm').Validform({
		btnSubmit:'#sumActivity', 
		tiptype:3,
        beforeSubmit: function (curform) {
            $(".inputform").attr('onsubmit', 'return false;');
        },
		callback:function(data){
            $(".inputform").ajaxSubmit(function (data) {
                if (data.status == '0000') {
                    alert("操作成功！");
                    window.location.href = "../selfActivity/list.html";
                } else {
                    alert(data.message);
                    $(".inputform").Validform().resetStatus();
                }
            });
		}
	});
	
	//弹出层、选择商品
	$('.inputform.layerform').Validform({
		btnSubmit:'#checkLayerClose', 
		tiptype:3,
		beforeSubmit: function(){
			
			//插入选中商品到当前图片层
			var curIndex = $("#checkLayerClose").attr('data-index');
			var $curItemSearchBtn = $('.' + curIndex);
			var $curCheckInput = $curItemSearchBtn.siblings('.input_text');
			var $itemClone = $('.layerform').find('.address-list.selected-box01').clone(true);
			
			if($curItemSearchBtn.siblings('.address-list').length){
				$curItemSearchBtn.siblings('.address-list').remove();
			}
			
			$itemClone.find('.address-selected01.dsn').remove().end().find('.icon-delete').hide();
			$itemClone.removeClass('selected-box01');
			$itemClone.insertBefore($curItemSearchBtn);
			$curItemSearchBtn.parent('td').addClass('height48');
			$curItemSearchBtn.val('重新选择商品');
			$curCheckInput.val('1');
			$('.inputform').Validform().check(false,$curCheckInput);
			
			var itemId = $('.layerform').find('.address-list.search-box01 li.on').find("span.address-name").attr("data-itemid");
			$curItemSearchBtn.parents("table").find("input[name='jumpParam']").val(itemId);
			
			layer.close(itemSearchLayer);
			
			return false;
		}
	});
	
	//选择链接类型
	selectOptionChange('.select_normal');
	
	//商品搜索弹出层
	var itemSearchLayer;
	$(document).on('click', '.btn-item-search', function(){
		//获取当前图片层序号
		var curIndex = $(this).attr('data-index');
		//获取当前搜索商品类型
		var curItemType = $(this).parents('tr').prevAll('tr').find('.select_normal').val();
		var $addressList = $(".address-list.selected-box01");
		//如果不是当前图片层，清空搜索结果，并标记当前层
		if(curIndex !== $("#checkLayerClose").attr('data-index')){
			
			$("#itemSearchInput").val('');
			$(".address-list.search-box01").html('');
			$addressList.find('.address-selected01').not('.address-selected01.dsn').find('.icon-delete').click();
			$addressList.siblings('.Validform_checktip').removeClass('Validform_right');
			$addressList.siblings('.select-input01').val('');
			
			////标记当前层、当前搜索商品类型
			$("#checkLayerClose").attr({'data-index':curIndex, 'data-itemtype':curItemType});
		}
		
		itemSearchLayer = layer.open({
		    type: 1,
		    title: false,
		    area: ['auto', 'auto'],
		    content: $('.layer-bill') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		});
	});
	
	//新增一行
	var itemCounter = 0;
	$('.btn-add-row').click(function(){ 
		itemCounter += 1;
		var $actInfoClone = $('.act-info-box.dsn').clone(true);
		
		$actInfoClone.find('.btn-item-search').eq(0).addClass('item-class-' + itemCounter).attr('data-index', 'item-class-' + itemCounter);
		$actInfoClone.find('.btn-item-search').eq(1).addClass('server-class-' + itemCounter).attr('data-index', 'server-class-' + itemCounter);
		$actInfoClone.removeClass('dsn').insertBefore('.act-info-bottom').end()
			.find('.img-upload-val').removeAttr('ignore').end()
			.find('.Validform_checktip').removeClass('Validform_right');
		window.parent.TuneHeight();
	})
	//删除该行
	$(document).on('click', '.btn-delete-row', function(){
		var curSadId = $(this).parents('table').find('input[name=sadId]').val();
		if(confirm('确定删除该行？')){
			$(this).parents('.info-list-02').remove();
			if(curSadId){
				$('<input type="hidden" name="deleteId" value="' + curSadId + '" />').prependTo('#activityForm');
			}
		}
	})
	
	$(document).on('change', 'input[name=photoimage]', function(){
		var curSadId = $(this).parents('table').find('input[name=sadId]').val();
		if(curSadId){
			$('<input type="hidden" name="updImgId" value="' + curSadId + '" />').insertBefore($(this));
		}
	})
	
	//选择商品
	var itemScreen01 = $.itemScreenStart({
		//选择配置项
		selectObj: '.address-list.search-box01 li:visible',	//要选择的对象
		deleteObj: '.address-list.selected-box01 li .icon-delete',	//要删除的对象
		itemsSelected: '.address-list.selected-box01:visible li:visible',		//已选对象
		itemDemo: '.address-selected01.dsn',	//用来克隆的对象	
		itemDemoBox: '.address-list.selected-box01',	//用来插入克隆的容器对象	
		validInput: '.select-input01',		//校验已选数量
		searchType: 1,	//搜索类型，商品搜索
		//重设参数
		multiple: false,		//单选
		singleTipsMsg: '每张图片只能对应一个商品',
	});
	
	//选择、反选、删除
	itemScreen01.itemSelectAndDelete();
	
	//开始搜索
	$('.item-search-btn').click(function(){
		var itemSearchType = $("#checkLayerClose").attr('data-itemtype');
		var thisSearchBox = $('.search-box01:visible');
		var currentVal = $.trim($('.search-input01 input.input_text').val());
		
		//searchType=2 搜索超市商品
		if(itemSearchType === '1'){
			
			var url = '../adv/getShelfProductForAdv.json?qryStr=' + currentVal + '&appType=1';
			if(!currentVal == ''){
				
				$.getJSON(url, function(data){
					
					var $objBox = $('.address-list.search-box01');
					var searchList = '';
					
					$.each(data, function(i, ssList){	
						
						//商品搜索，带ID、供应商
		                if(ssList.productName.indexOf(currentVal) > -1){
		                    searchList += '<li><span class="address-name" data-itemid="'+ssList.productId+'">' + ssList.productName + '</span><br><span class="grey">ID：<span class="data-obj-id">'+ssList.productId+'</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">' + ssList.merchantName + '</span></span></li>';
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
			
		//itemSearchType=3 搜索到家服务商品
		}else if(itemSearchType === '2'){

			var url = '../dredge/productList.json?upShelfState=1&dpName=' + currentVal + '&appType=1';
			if(!currentVal == ''){
				
				$.getJSON(url, function(data){
					
					var $objBox = $('.address-list.search-box01');
					var searchList = '';
					
					$.each(data.dataValue.list, function(i, ssList){	
						
						//商品搜索，带ID、供应商
		                if(ssList.prdtName.indexOf(currentVal) > -1){
		                    searchList += '<li><span class="address-name" data-itemid="'+ssList.dpId+'">' + ssList.prdtName + '</span><br><span class="grey">ID：<span class="data-obj-id">'+ssList.dpId+'</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">' + ssList.ssName + '</span></span></li>';
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
			
		}
	});
	
});