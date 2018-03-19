
var $menuUlHide = $('.menu_01.dsn');
var $menuLiHide = $('.side-bar-memu03.dsn');
var $gbTr = $('.gb-tr.dsn');
var $buildingTr = $('.building-tr.dsn');
var $realRoomTr = $('.realRoom-tr.dsn');
var $unitTr;
var $menuUlClone, $menuLiClone, $gbTrClone, $buildingTrClone, $realRoomTrClone;

var hasGotTree = false;
var itemFeesHascalculateRules = null;	//缓存房号收费项及收费标准
var regNumberFixedTwo = /^[0-9]+(\.[0-9]{0,2})?$/;

//获取树形菜单
function getRoomTree(){
	$.ajax({
		url: '../propertyPayConfig/getRoomTree.json',
		data: {'gbId': $(".info").attr("gbId")},
		success: function(data){
			if(data.status === '0000'){
				var gbTreeData = data.dataValue[0];
			
				$('.side-bar-memu01').text(gbTreeData.gbName).attr('data-id', gbTreeData.gbId);
				$.each(gbTreeData.buildingRooms, function(index, item){
					$menuUlClone = $menuUlHide.clone(true);
					$menuUlClone.find('.side-bar-memu02').text(item.buildingName).attr('data-id', item.buildingId);
					
					$.each(item.realRooms, function(roomIndex, roomItem){
						$menuLiClone = $menuLiHide.clone(true);
						$menuLiClone.text(roomItem.realRoomName).attr('data-id', roomItem.realRoomId).removeClass('dsn').appendTo($menuUlClone);
					});
					
					$menuUlClone.removeClass('dsn').appendTo('.menu_02');
				});
				
				hasGotTree = true;
			}else{
				alert(data.message);
			}
		}
	})
}

//获取小区配置
function getGroupBuildingList(gbId){
	$.ajax({
		url: '../propertyPayConfig/mrStandardList.json',
		data: {'gbId': gbId},
		success: function(data){
			if(data.status === '0000'){
				
				$('.wrap-box01').find('.gb-tr.new-add').remove();

				$.each(data.dataValue, function(indexdata, gbData){
					$.each(gbData.mrFeeItemForStandars, function(index, item){
					
					$gbTrClone = $gbTr.clone(true);
					$gbTrClone.find('.gb-name').text(gbData.gbName);
					$gbTrClone.find('.fee-item-name').text(item.feeItemName);
					$gbTrClone.find('.gb-save-btn').attr('data-id', gbData.id);
					
					$.each(item.calculateRuleForStandars, function(standardIndex, standardItem){
						if(item.mrCalculateRuleCfgId === standardItem.calculateRuleId){
							var $newOption = '<option value="' + standardItem.calculateRuleId + '" selected>' + standardItem.calculateRuleName + '</option>';
						}else{
							var $newOption = '<option value="' + standardItem.calculateRuleId + '">' + standardItem.calculateRuleName + '</option>';
						}
						$gbTrClone.find('.fee-standard').append($newOption);
					});

					$gbTrClone.removeClass('dsn').addClass('new-add').appendTo('.wrap-box01 table tbody');
					})
				})
			}else{
				alert(data.message);
			}
		}
	})
}

//获取楼栋配置
function getBuildingList(buildingId){
	$.ajax({
		url: '../propertyPayConfig/mrStandardList.json',
		data: {'buildingId': buildingId},
		success: function(data){
			if(data.status === '0000'){

				$('.wrap-box02').find('.building-tr.new-add').remove();

				$.each(data.dataValue, function(builderIndex, buildingData){
					$.each(buildingData.mrFeeItemForStandars, function(index, item) {
						$buildingTrClone = $buildingTr.clone(true);
						$buildingTrClone.find('.building-name').text(buildingData.buildingName);
						$buildingTrClone.find('.fee-item-name').text(item.feeItemName);
						$buildingTrClone.find('.building-save-btn').attr('data-id', buildingData.id);
						$.each(item.calculateRuleForStandars, function (standardIndex, standardItem) {
							if(item.mrCalculateRuleCfgId === standardItem.calculateRuleId){
								var $newOption = '<option value="' + standardItem.calculateRuleId + '" selected>' + standardItem.calculateRuleName + '</option>';
							}else{
								var $newOption = '<option value="' + standardItem.calculateRuleId + '">' + standardItem.calculateRuleName + '</option>';
							}

							$buildingTrClone.find('.fee-standard').append($newOption);
						});

						$buildingTrClone.removeClass('dsn').addClass('new-add').appendTo('.wrap-box02 table tbody');
					});
				})
			}else{
				alert(data.message);
			}
		}
	})
}

//获取房号配置
function getRealRoomList(realRoomId){
	$.ajax({
		url: '../propertyPayConfig/mrStandardList.json',
		data: {'realRoomId': realRoomId},
		success: function(data){
			if(data.status === '0000'){
				var realRoomData = data.dataValue;
				itemFeesHascalculateRules = realRoomData.itemFeesHascalculateRules;
				
				$('.wrap-box03').find('.realRoom-tr.new-add').remove();
				
				$.each(realRoomData.mrStandardRooms, function(index, item){
					
					$realRoomTrClone = $realRoomTr.clone(true);
					$realRoomTrClone.find('.building-name').text(item.buildingName);
					$realRoomTrClone.find('.unit-name').text(item.unitName);
					$realRoomTrClone.find('.room-name').text(item.roomNo);
					$realRoomTrClone.find('.user-name').text(item.ppName);
					$realRoomTrClone.find('[name=mrName]').val(item.mrName);
					$realRoomTrClone.find('[name=multiplierTimes]').val(item.multiplierTimes);
					$realRoomTrClone.find('.realRoom-save-btn').attr('data-id', item.id);
					
					$.each(itemFeesHascalculateRules, function(feeIndex, feeItem){
						var $newFeeOption;
						//已选中该项
						if(item.tMrFeeItemId === feeItem.feeItemId){
							$newFeeOption = '<option value="' + feeItem.feeItemId + '" selected>' + feeItem.feeItemName + '</option>';
							$.each(feeItem.calculateRuleForStandars, function(standardIndex, standardItem){//已选中该项
								if(item.tMrCalculateRuleCfgId === standardItem.calculateRuleId){
									var $newStandardOption = '<option value="' + standardItem.calculateRuleId + '" selected>' + standardItem.calculateRuleName + '</option>';
								}else{
									var $newStandardOption = '<option value="' + standardItem.calculateRuleId + '">' + standardItem.calculateRuleName + '</option>';
								}
								$realRoomTrClone.find('.fee-standard').append($newStandardOption);
							})
						}else{
							$newFeeOption = '<option value="' + feeItem.feeItemId + '">' + feeItem.feeItemName + '</option>';
						}

						$realRoomTrClone.find('.fee-item-name').append($newFeeOption);
					});

					$realRoomTrClone.removeClass('dsn').addClass('new-add').appendTo('.wrap-box03 table tbody');
				})
			}else{
				alert(data.message);
			}
		}
	})
}

//保存配置
var isPost = false;
function saveConfig(id, standardType, mrCalculateRuleCfgId, mrFeeItemId, mrName, multiplierTimes, gbId, buildingId, realRoomId){
	var dataParams = {'id': id, 'standardType': standardType, 'mrCalculateRuleCfgId': mrCalculateRuleCfgId};
	if(standardType === '3'){
		dataParams.mrFeeItemId = mrFeeItemId;
		dataParams.mrName = mrName;
		dataParams.multiplierTimes = multiplierTimes;
	}
	//新增房号配置，须传gbId, buildingId, realRoomId
	if(gbId){
		dataParams.gbId = gbId;
		dataParams.buildingId = buildingId;
		dataParams.realRoomId = realRoomId;
	}
	if(isPost){
		return false;
	}
	isPost = true;

	//增加菊花
	var layermsg = null;
	layermsg = layer.msg('正在处理，请稍候', {
		icon: 16,
		time: 0,	//指定time为0，默认该消息不关闭；若2秒后，再弹出layer.alert('加载完成', 1);该消息同时关闭。
		shade: 0.5
	});

	$.ajax({
		url: '../propertyPayConfig/saveMrStandard.json',
		data: dataParams,
		type: 'post',
		success: function(data){
			if(data.status === '0000'){
				alert(data.message);
				//更新新添加项的id
				if(gbId){
					$('.side-bar-memu03.orange').click();
				}
			}else{
				alert(data.message);
			}
			isPost = false;
			layer.closeAll();
		},
		error: function(){
			isPost = false;
		}
	})
}

//删除配置
function deleteConfig(id, $obj){
	var dataParams = {'id': id};
	if(isPost){
		return false;
	}
	isPost = true;
	$.ajax({
		url: '../propertyPayConfig/delMrStandard.json',
		data: dataParams,
		type: 'post',
		success: function(data){
			if(data.status === '0000'){
				alert('操作成功！');
				$obj.parents('tr').remove();
			}else{
				alert(data.message);
			}
			isPost = false;
		},
		error: function(){
			isPost = false;
		}
	})
}

$(function(){
	//获取树形菜单
	getRoomTree();
	
    //表单验证
	$(".inputform").Validform({
		btnSubmit:"#sumPopAd", 
		tiptype:3,
		beforeSubmit: function(){
		},
		callback:function(data){
			return false;
		}
	});
	
	//小区配置
	$(document).on('click', '.side-bar-memu01', function(){
		var gbId = $(this).attr('data-id');
		$(".wrap-box01").removeClass('dsn').siblings().addClass('dsn');
		getGroupBuildingList(gbId);
	});
	
	//楼栋配置
	$(document).on('click', '.side-bar-memu02', function(){
		var buildingId = $(this).attr('data-id');
		$(".wrap-box02").removeClass('dsn').siblings().addClass('dsn');
		getBuildingList(buildingId);
	});
	
	//房号配置
	$(document).on('click', '.side-bar-memu03', function(){
		var realRoomId = $(this).attr('data-id');
		$(".wrap-box03").removeClass('dsn').siblings().addClass('dsn');
		$('.side-bar-memu03').removeClass('orange');
		$(this).addClass('orange');
		$(this).siblings('span').addClass('orange');
		
		getRealRoomList(realRoomId);
	});
	//房号添加
	$(document).on('click', '.realRoom-add-btn', function(){
		if($('.realRoom-tr').length > 1){
			$unitTr = $('.realRoom-tr:last-child');
		}
		$unitTr.clone(true).appendTo('.wrap-box03 .info-list-02 tbody').find('.realRoom-save-btn').attr('data-id', '');
		window.parent.TuneHeight();
	});
	//房号删除
	$(document).on('click', '.realRoom-delete-btn', function(){
		if(confirm('确认删除该项？')){
			var id = $(this).parent('td').prev('td').find('a').attr('data-id');
			if(id){
				deleteConfig(id, $(this));
			}else{
				$(this).parents('tr').remove();
			}
			if($('.realRoom-tr:visible').length === 1){
				$unitTr = $(this).parents('tr');
			}
		}
	});
	
	//保存小区、楼栋配置
	$(document).on('click', '.gb-save-btn, .building-save-btn', function(){
		var id = $(this).attr('data-id');
		var standardType = $(this).parents('table').attr('data-type');
		var mrCalculateRuleCfgId = $(this).parents('tr').find('.fee-standard').val();
		if(mrCalculateRuleCfgId == '' || mrCalculateRuleCfgId == null) {
			alert("请选择收费标准！");
			return false;
		}
		saveConfig(id, standardType, mrCalculateRuleCfgId, null, null, null, null);
	});
	
	//保存房号配置
	$(document).on('click', '.realRoom-save-btn', function(){
		var id = $(this).attr('data-id');
		var standardType = $(this).parents('table').attr('data-type');
		var mrCalculateRuleCfgId = $(this).parents('tr').find('.fee-standard').val();
		var mrFeeItemId = $(this).parents('tr').find('.fee-item-name').val();
		var mrName = $(this).parents('tr').find('[name=mrName]').val();
		var multiplierTimes = $(this).parents('tr').find('[name=multiplierTimes]').val();
		if(mrName === ''){
			alert('请填写收费表名称');
			return false;
		}
		if(multiplierTimes === ''){
			alert('请填写计量倍率');
			return false;
		}
		if(!regNumberFixedTwo.test(multiplierTimes)){
			alert('计量倍率为数字');
			return false;
		}
		if(mrCalculateRuleCfgId == '' || mrCalculateRuleCfgId == null) {
			alert("请选择收费标准！");
			return false;
		}
		//新添加表配置，无表id，须传对应小区/楼栋/房号id
		if(!id){
			var gbId = $('.side-bar-memu01.orange').attr('data-id');
			var buildingId = $('.side-bar-memu02.orange').attr('data-id');
			var realRoomId = $('.side-bar-memu03.orange').attr('data-id');
			saveConfig(id, standardType, mrCalculateRuleCfgId, mrFeeItemId, mrName, multiplierTimes, gbId, buildingId, realRoomId);
		}else{
			saveConfig(id, standardType, mrCalculateRuleCfgId, mrFeeItemId, mrName, multiplierTimes, null, null, null);
		}
	});
	
	//切换收费项
	$(document).on('change', '.fee-item-name', function(){
		var curVal = $(this).val();
		var $curStandardSelect = $(this).parents('tr').find('.fee-standard');
		$.each(itemFeesHascalculateRules, function(index, item){
			if(+curVal === item.feeItemId){
				$curStandardSelect.html('');
				$.each(item.calculateRuleForStandars, function(standardIndex, standardItem){
					var $changeOption = '<option value="' + standardItem.calculateRuleId + '">' + standardItem.calculateRuleName + '</option>';
					$curStandardSelect.append($changeOption);
				})
			}
		})
	});
	
	//树形菜单展开收起，可扩展、无限级菜单。
    $(document).on('click', '.menu_01 span', function(){
        var $menu=$(this);

        if($menu.hasClass('orange')){
            $menu.removeClass('orange menu_ar_down');
            $menu.siblings('li').slideUp(300);
        }else{
            $menu.parent('ul').siblings().find('li').slideUp(300);	//find()获取的是元素的所有下级元素
            $menu.parent('ul').siblings().find("span").removeClass('orange menu_ar_down').addClass('menu_ar');
            $menu.siblings().removeClass('orange').slideDown(300);
            $menu.siblings().find('span.orange, li.orange').removeClass('orange');
            $menu.addClass('orange menu_ar_down');
        }
    });


    //默认进入小区配置
	var sideBarClick = setInterval(function(){
		if(hasGotTree){
			$('.side-bar-memu01').click();
			hasGotTree = false;
			clearInterval(sideBarClick);
		}
	}, 100);
	
	$(window.parent.document).find(".main-right").scrollTop('0px')
});