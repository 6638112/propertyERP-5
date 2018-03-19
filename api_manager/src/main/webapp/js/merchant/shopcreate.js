$(function(){
	//显示快速清除按钮
	$('.shop-create-search').on('keyup' ,function(){
		var curVal = $.trim($(this).val());
		if(!curVal == ''){
			$('.shop-create-header .quick-delete').show();
		}
	});
	
	//快速清除搜索框
	$('.shop-create-header .quick-delete').click(function(){
		$('.shop-create-search').val('');
		$(this).hide();
	});
	
	//选择与反选小区
	var $areaList = $('.area-list');
	var $areaAddList = $('.area-add-list');
	var $areaAddListPart02 = $('.shop-part02 .area-add-list');
	var $paddingMenu = $('.paddingMenu');
	var areaNum = 1;
	$(document).on('click', '.shop-create-check li', function(){
		var hasName = 'false';
		var thisLiLength = $(this).parent('ul').next('ul').find('.area-list').find('li').length;
		var thisCurLiLength = thisLiLength + 1;
		var deleteCurLiLength = thisLiLength - 1;
		
		//判断小区是否重复选择
		var areaInfoName = $(this).find('.area-info-name').text();
		var $thisCheckIcon = $(this).find('.area-check-icon');
		
		$areaList.find('li').each(function(){
			var thisLiText = $(this).text();
			if(areaInfoName == thisLiText && !$thisCheckIcon.hasClass('on')){
				alert('该小区已选择！');
				hasName = 'true';
			}
			if(hasName == 'true'){
				return false;
			}
		});
		//未选择小区
		if(!$thisCheckIcon.hasClass('on') && hasName == 'false'){
			var gbId = $(this).find('.area-info-name').attr('gbId');
			var $newAreaName = $('.newAreaName.dsn');
			var $newAreaInput = $('<input name="groupBuildingId" class="dsn" />');
			var $newAreaInputClone = $newAreaInput.clone();
			var newAreaInputClass = 'newAreaInput' + areaNum ;
			
			var $newAreaNameClone = $newAreaName.clone(true);
			var newAreaClassA = 'newAreaA' + areaNum ;
			var newAreaClassB = 'newAreaB' + areaNum ;
			var newAreaClassC = 'newAreaC' + areaNum ;
			
			$newAreaInputClone.val(gbId).addClass(newAreaInputClass);
			$newAreaNameClone.prepend($newAreaInputClone);
			$(this).attr('data-newclass', newAreaClassA).find('.area-check-icon').addClass('on').addClass(newAreaClassB);
			$newAreaNameClone.addClass(newAreaClassA + ' ' + newAreaClassC).attr('data-newclass', newAreaClassB).attr('data-newclass-this', newAreaClassC).prepend(areaInfoName);
			$newAreaNameClone.removeClass('dsn').prependTo($areaList);
			$areaList.eq(1).find('input').attr('name','');
			areaNum += 1;
			if(!$areaAddList.is(":visible")){
				$areaAddList.show();
			}
			
			var areaAddListHeight = $areaAddListPart02.height();
			//console.log(areaAddListHeight);
			$paddingMenu.css('padding-bottom', 57 + areaAddListHeight);
			$('.shop-area-select').val('已选择' + thisCurLiLength + '个小区');
		//已选择小区
		}else if($thisCheckIcon.hasClass('on') && hasName == 'false'){
			var areaDeleteClass = $(this).attr('data-newclass');
			$(this).find('.area-check-icon').removeClass('on');
			$('.' + areaDeleteClass).remove();
			
			var areaAddListHeight = $areaAddListPart02.height();
			$paddingMenu.css('padding-bottom', 57 + areaAddListHeight);
			$('.shop-area-select').val('已选择' + deleteCurLiLength + '个小区');
			if(thisLiLength == 1){
				$areaAddList.hide();
				$paddingMenu.css('padding-bottom', 57);
				$('.shop-area-select').val('');
			}
		}
	});
	
	//删除已选小区
	$(document).on('click', '.area-list .quick-delete',function(){
		var areaDeleteClass = $(this).parent('li').attr('data-newclass');
		var thisAreaDeleteClass = $(this).parent('li').attr('data-newclass-this');
		var thisIndex = $(this).parent('li').index();
		
		var thisLiLength = $(this).parents('.area-list').find('li').length;
		var thisCurLiLength = thisLiLength - 1;
		var areaDeleteTargetClass = $('.' + areaDeleteClass).parent('li').attr('data-newclass');
				
		if(areaDeleteClass == '' || areaDeleteClass == undefined){
			$('.area-list').each(function(index, element) {
                $(this).find('li').eq(thisIndex).remove();
            });
		}else{
			if($('.' + areaDeleteClass)){
				//console.log(areaDeleteClass);
				$('.' + areaDeleteClass).removeClass('on');
			}
			
			$('.' + thisAreaDeleteClass).remove();
				
		}
		
		var areaAddListHeight = $areaAddListPart02.height();
		$paddingMenu.css('padding-bottom', 57 + areaAddListHeight);
		$('.shop-area-select').val('已选择' + thisCurLiLength + '个小区');
		if(thisLiLength == 1){
			$areaAddList.hide();
			$paddingMenu.css('padding-bottom', 57);
			$('.shop-area-select').val('');
		}
	});
	
	//选择小区
	var $loading = $('.loading');
	var $shopPart02 = $('.shop-part02');
	var $shopCreateBox = $shopPart02.find('.shop-create-check');
	$('.shop-area-select').click(function(){
		//隐藏第一部分
		$('.shop-part01').hide();

		//TODO 需要调用  getGroupBuildingPageByBlockId.json接口获取到小区列表，传入参数为 blockId，page=1， pageNum=20
		//Demo: http://localhost:8080/api_manager/ebuyMerchant/getGroupBuildingPageByBlockId.json?blockId=2150&page=1&pageNum=20
		
		//获取小区列表
		getAreaLists();
		
		//显示小区列表部分
		$shopPart02.show();
	});
	
	//获取小区列表
	function getAreaLists(){
		$.ajax({
			type:"get",
			url:"getGroupBuildingPageByBlockId.json",
			data:{blockId:$('#block').val(), page:1, pageNum:20},
			dataType:"json",
			beforeSend:function(data){
				if($('.newLoading').length == 0){
					$loading.clone(true).addClass('newLoading').removeClass('hide').prependTo($shopCreateBox);
				}
			},
			success:function(data){
				$('.newLoading').remove();
				
				var $shopCreateCheckSingle = $('.shop-create-check-single.hide');
				
				if(data.dataValue.list == ''){
					var noItemTips = '<div id="noItemTips" class="list-box t-center">该城市区域暂无小区！</div>';
					$shopCreateBox.html(noItemTips);
				}else{
					$shopCreateBox.html('');
					$.each(data.dataValue.list,function(iPro,dataPro){
						
						var $shopCreateCheckSingleClone = $shopCreateCheckSingle.clone(true);
						//将订单数据更新至$itemListInfoClone
						$shopCreateCheckSingleClone.find('.area-info-name').attr('gbId', dataPro.id);
						$shopCreateCheckSingleClone.find('.area-info-name').text(dataPro.name);
						if(dataPro.addressDesc!=null && dataPro.addressDesc!='null'){
							$shopCreateCheckSingleClone.find('.area-info-address').text(dataPro.addressDesc);
						}else{
							$shopCreateCheckSingleClone.find('.area-info-address').text('');
						}
	
						//插入页面
						$shopCreateCheckSingleClone.removeClass('hide').appendTo($shopCreateBox);

					});
				}
				
			}
			
		});
	}
	
	//搜索小区
	var $shopCreateSearchInput = $('#shopCreateSearchBtn');
	$shopCreateSearchInput.keyup(function(){
		currentSearchInputVal = $.trim($shopCreateSearchInput.val());
		//查询小区
		if(currentSearchInputVal !== ""){
			areaListSearch();
		}else{
			getAreaLists();
		}
		
	});
	
	//查询小区
	function areaListSearch(){
		$.ajax({
			type:"get",
			url:"getGroupBuildingByBlockIdWithName.json",
			data:{blockId:$('#block').val(), gbName:currentSearchInputVal},
			dataType:"json",
			beforeSend:function(data){
				if($('.newLoading').length == 0){
					$loading.clone(true).addClass('newLoading').removeClass('hide').prependTo($shopCreateBox);
				}
			},
			success:function(data){
				$('.newLoading').remove();
				var $shopCreateCheckSingle = $('.shop-create-check-single.hide');
				
				if(data.dataValue == ''){
					var noItemTips = '<div id="noItemTips" class="list-box t-center">找不到符合条件的小区！</div>';
					$shopCreateBox.html(noItemTips);
				}else{
					$shopCreateBox.html('');
					$.each(data.dataValue,function(iPro,dataPro){
						
						var $shopCreateCheckSingleClone = $shopCreateCheckSingle.clone(true);
						//将订单数据更新至$itemListInfoClone
						$shopCreateCheckSingleClone.find('.area-info-name').attr('gbId', dataPro.id);
						$shopCreateCheckSingleClone.find('.area-info-name').text(dataPro.name);
						if(dataPro.addressDesc!=null && dataPro.addressDesc!='null'){
							$shopCreateCheckSingleClone.find('.area-info-address').text(dataPro.addressDesc);
						}else{
							$shopCreateCheckSingleClone.find('.area-info-address').text('');
						}
	
						//插入页面
						$shopCreateCheckSingleClone.removeClass('hide').appendTo($shopCreateBox);

					});
				}
				
			}
			
		});
	}
	
	//选择小区确定按钮
	$('.area-add-btn').click(function(){
		$('.shop-part02').hide();
		$('.shop-part01').show();
		$shopCreateSearchInput.val('');
		window.scrollTo(0, 500);
	});
	
});