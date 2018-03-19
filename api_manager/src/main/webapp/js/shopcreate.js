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
	var $paddingMenu = $('.paddingMenu');
	var areaNum = 1;
	$('.shop-create-check li').click(function(){
		var thisLiLength = $(this).parent('ul').next('ul').find('.area-list').find('li').length;
		var thisCurLiLength = thisLiLength + 1;
		var deleteCurLiLength = thisLiLength - 1;
		if(!$(this).find('.area-check-icon').hasClass('on')){
			var areaInfoName = $(this).find('.area-info-name').text();
			var $newAreaName = $('.newAreaName.dsn');
			var $newAreaInput = $('<input name="areaName" class="dsn" />');
			var $newAreaInputClone = $newAreaInput.clone();
			var newAreaInputClass = 'newAreaInput' + areaNum ;
			
			var $newAreaNameClone = $newAreaName.clone(true);
			var newAreaClassA = 'newAreaA' + areaNum ;
			var newAreaClassB = 'newAreaB' + areaNum ;
			
			$newAreaInputClone.val(areaInfoName).addClass(newAreaInputClass);
			$newAreaNameClone.prepend($newAreaInputClone);
			$(this).attr('data-newclass', newAreaClassA).find('.area-check-icon').addClass('on').addClass(newAreaClassB);
			$newAreaNameClone.addClass(newAreaClassA).attr('data-newclass', newAreaClassB).prepend(areaInfoName);
			$newAreaNameClone.removeClass('dsn').prependTo($areaList);
			$areaList.eq(1).find('input').attr('name','');
			areaNum += 1;
			if(!$areaAddList.is(":visible")){
				$areaAddList.show();
			}
			
			var areaAddListHeight = $areaAddList.height();
			$paddingMenu.css('padding-bottom', 57 + areaAddListHeight);
			$('.shop-area-select').val('已选择' + thisCurLiLength + '个小区');
		}else{
			var areaDeleteClass = $(this).attr('data-newclass');
			$(this).find('.area-check-icon').removeClass('on');
			$('.' + areaDeleteClass).remove();
			
			var areaAddListHeight = $areaAddList.height();
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
		console.log(1);
		var areaDeleteClass = $(this).parent('li').attr('data-newclass');
		var thisIndex = $(this).parent('li').index();
		
		var thisLiLength = $(this).parents('.area-list').find('li').length;
		var thisCurLiLength = thisLiLength - 1;
		var areaDeleteTargetClass = $('.' + areaDeleteClass).parent('li').attr('data-newclass');
				
		if(areaDeleteClass == '' || areaDeleteClass == undefined){
			$('.area-list').each(function(index, element) {
                $(this).find('li').eq(thisIndex).remove();
            });
		}else{
			$('.' + areaDeleteClass).removeClass('on');
			$('.' + areaDeleteTargetClass).remove();
				
		}
		
		var areaAddListHeight = $areaAddList.height();
		$paddingMenu.css('padding-bottom', 57 + areaAddListHeight);
		$('.shop-area-select').val('已选择' + thisCurLiLength + '个小区');
		if(thisLiLength == 1){
			$areaAddList.hide();
			$paddingMenu.css('padding-bottom', 57);
			$('.shop-area-select').val('');
		}
	});
	
	$('.shop-area-select').click(function(){
		$('.shop-part01').hide();
		$('.shop-part02').show();
	});
	$('.area-add-btn').click(function(){
		$('.shop-part02').hide();
		$('.shop-part01').show();
		window.scrollTo(0, 500);
	});
	
});