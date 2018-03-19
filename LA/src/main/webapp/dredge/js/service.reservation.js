//搜索小区
var $loading = $('.loading'),
	$creatInfo = $('.creatInfo'),
	$creatInfoClone = $creatInfo.clone(true),
	$searchBox = $('.searchBox'),
	$reSearch = $('.reSearch'),
	$reSearchClone = $('.reSearch').clone(true),
	$shopCreateBox = $('.shop-create-check'),
	$shopCreateSearchInput = $('#shopCreateSearchInput'),
	$addressIcon = $('#addressIcon'),
	$updAddrLi = null,
	dredgeAddressId = '',
	dredgeSelectedAddressId = '',
	isupdAddr = false;
var dredgeAddress = '';
//显示快速清除按钮
var $quickDelete = $('.shop-create-header .quick-delete');
var isloading = false;

//省市切换
function onSelectChange(obj,toSelId){
	setSelect(obj.value,toSelId);
	
	//重新选择小区
	$('.reSearch').click();
}

//省市切换详情逻辑处理
function setSelect(fromSelVal,toSelId){
	
	//清除输入小区
	$shopCreateSearchInput.val('');
	$shopCreateBox.html('');
	
	if(toSelId === "city"){//选择省，更新市
		$('#city').html('<option value="">选择城市</option>');//清空之前的选项
		$('#block').html('<option value="">选择区县</option>');//清空之前的选项
		if(fromSelVal !== ""){
			jQuery.ajax({
				  url: "../common/toUrl.do",
				  dataType:"json",
				  data:{"detailUrl" : "/addressCity/getAddressCityListById.json", "provId":fromSelVal, "isNeedLogin":"0"}, //"provId="+fromSelVal,
				  success: function(data){
				    var cookieCityId = getCookie('cityId');
  				    var hasCookieCity = false;
					    
				    $.each(data.dataValue.list, function(i, item) {
				    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
				    	
				    	//对比是否存在该id，不存在则不设置
				    	if(cookieCityId == item.id){
				    		hasCookieCity = true;
				    	}
				    });
				    
				    //读取cookie中的cityId，设为选中
				    if(cookieCityId && hasCookieCity){
				    	$('#city').val(cookieCityId);
				    	setSelect(cookieCityId,'block');
				    }
				    
				  },  
	              error: function(){  
	              	$.Showmsg('网络不给力，请稍后重试'); 
	              }
			});
		}
	}else if(toSelId === "block"){
		$('#block').html('<option value="">选择区县</option>');//清空之前的选项
		if(fromSelVal !== ""){
    		jQuery.ajax({
    			  url: "../common/toUrl.do",
    			  dataType:"json",
    			  data:{"detailUrl" : "/addressBlock/getAddressBlockListById.json", "cityId":fromSelVal, "isNeedLogin":"0"}, //"provId="+fromSelVal,
    			  success: function(data){
  				    var cookieBlockId = getCookie('blockId');
  				    var hasCookieBlock = false;
  				    
    			    $.each(data.dataValue.list, function(i, item) {
    			    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
    			    	if(cookieBlockId == item.id){
    			    		hasCookieBlock = true;
    			    	}
    			    });
				    
				    //读取cookie中的blockId，设为选中
				    if(cookieBlockId && hasCookieBlock){
				    	$('#block').val(cookieBlockId);
				    }
    			    
    			  },  
                  error: function(){  
                  	$.Showmsg('网络不给力，请稍后重试'); 
                  }
    		});
		}
  	}	
}

//查询小区
function areaListSearch(searchInput){
	isloading = true;
	$.ajax({
		type:"get",
		url:"../common/toUrl.do",
		data:{detailUrl: "/groupBuilding/qryGroupBuildingDim.json", searchKey:$(searchInput).val(), page:1, pageNum:10, cityId:$('#city').val(), blockId:$('#block').val()},
		dataType:"json",
		beforeSend:function(data){
			if($('.newLoading').length == 0){
				$loading.clone(true).addClass('newLoading').removeClass('dsn').prependTo($shopCreateBox);
			}
		},
		success:function(data){
			var $shopCreateCheckSingle = $('.shop-create-check-single.hide');
			
			$shopCreateBox.html('');
			if(data.dataValue.list.length === 0){
				//$creatInfo.removeClass('dsn').appendTo($shopCreateBox);
				//$('.item-list-arrow-box').removeClass('dsn');
				$creatInfo.removeClass('dsn').appendTo($shopCreateBox);
			}else{
				$('.item-list-arrow-box').addClass('dsn');
				$('.paddingbottom').addClass('dsn');
				
				$.each(data.dataValue.list,function(iPro,dataPro){
					
					var $shopCreateCheckSingleClone = $shopCreateCheckSingle.clone(true);
					//将订单数据更新至$itemListInfoClone
					$shopCreateCheckSingleClone.find('.area-info-name').attr('gbid', dataPro.id);
					$shopCreateCheckSingleClone.find('.area-info-name').attr('signStatus', dataPro.signStatus);
					$shopCreateCheckSingleClone.find('.area-info-name').text(dataPro.name);
					$shopCreateCheckSingleClone.find('.area-info-address').text(dataPro.totalName);

					//插入页面
					$shopCreateCheckSingleClone.appendTo($shopCreateBox).removeClass('hide');

				});
			}
			
			isloading = false;
		}
	});
}

//校验所选区域或小区是否在服务范围
function isServerCover(url, blockId, gbId, dredgeProductId){
	var isCover = true;
	$.ajax({
		  url: "../common/toUrl.do",
		  type: 'post',
		  dataType:"json",
		  async: false,
		  data:{"detailUrl" : url, "blockId": blockId, "gbId": gbId, "dredgeProductId": dredgeProductId}, 
		  success: function(data){
			  if(data.status === '0000'){
				  if(!data.dataValue.ok){
					  $.Showmsg('该项服务暂未覆盖当前地址，请重新选择');
					  isCover = false;
				  }
			  }else{
				  $.Showmsg(data.message);
				  isCover = false;
			  }
		  },  
          error: function(){  
          	 $.Showmsg('网络不给力，请稍后重试');
			  isCover = false;
          }
	});
	return isCover;
}

function getCookie(name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg)){
		return unescape(decodeURI(arr[2]));
	}else{
		return null;
	}
}

function cleanSearch() {
    $searchBox.show().find('#shopCreateSearchInput').val('');
    $('#writeRoomNum:visible').appendTo('body').hide();
    $('#selectCheckedRoomNum:visible').appendTo('body').hide().find('select').val('');
    $('#selectUncheckedRoomNum:visible').appendTo('body').hide().find('input').val('');

    $('.shop-create-check').html('');

    //隐藏确定按钮，需选择小区后才能点确定按钮
    $('.item-list-arrow-box').addClass('dsn');
    $('.paddingbottom').addClass('dsn');
}

$(function () {
    
    //读取cookie中的门牌信息，如果已有默认门牌，直接显示默认门牌信息，并且设置小区id，签约状态
    var defaultRoomAddr01 = getCookie('defaultRoomAddr01');
    var defaultRoomAddr02 = getCookie('defaultRoomAddr02');
    var defaultRoomGbId = getCookie('gbId');
    var changeDefaultRoomVal = getCookie('changeDefaultRoom');

    if(defaultRoomAddr02){
    	//如果是已签约小区，则只需传dredgeAddress（不带省份），不需传blockId和gbId
    	var defaultCityName = getCookie('cityName');
    	var defaultBlockName = getCookie('blockName');
    	var defaultGbName = getCookie('gbName');
    	dredgeAddress = defaultRoomAddr01 + defaultRoomAddr02;

		$('#addressTxt').text(defaultRoomAddr02).removeClass('dsn');
		$('#getAddressBtn').addClass('dsn');
		
		$('<input name="dredgeAddress" type="text" hidden="hidden" value="' + dredgeAddress + '" />').prependTo('#wrapBox');
		//$("<input class='dsn' name='gbSignStatus' type='hidden' value='" + signstatus + "'/>").insertBefore('#citySelectBox');
    }
    
  //判断是否新用户，预约提交时需要设置changeDefaultRoom参数值为1
    if(changeDefaultRoomVal === '1'){
    	$('[name=changeDefaultRoom]').val(changeDefaultRoomVal);
    //如果是老用户，无需变更默认门牌，将地址校验设置通过
    }else if(changeDefaultRoomVal === '0'){
    	$('#addressValid').val('1');
    }
	
	$('#shopCreateSearchInput').on('keyup' ,function(){
		var curVal = $.trim($(this).val());
		if(!curVal == ''){
			$quickDelete.removeClass('hide');
		}else{
			$quickDelete.addClass('hide');
		}
	});
	
	//快速清除搜索框
	$quickDelete.click(function(){
		$('.shop-create-search').val('');
		$(this).hide();
	});

	//时间戳+定时器，避免搜索时频繁触发请求
	var lastTimeStamp = 0;
	$shopCreateSearchInput.keyup(function(event){
		var $this = $(this);
		
		//标记当前事件函数的时间戳
		lastTimeStamp = event.timeStamp;
		
		//触发搜索，将搜索框滚动至顶部
		currentSearchInputVal = $.trim($shopCreateSearchInput.val());
		
		////800ms后比较二者是否还相同（因为只要还有事件触发，lastTimeStamp就会被改写，不再是当前事件函数的时间戳）
		setTimeout(function(){
			if(lastTimeStamp == event.timeStamp){
				//查询小区
				if($('#block').val() == ""){
					$.Showmsg('请先选择省市区'); 
					$this.val('');
					$quickDelete.hide();
				}else if(currentSearchInputVal !== ""){
					areaListSearch('#shopCreateSearchInput');
				}
			}
		},800);
	});
	
	//选择小区，判断选中小区是否已验证：已签约则显示$('#selectCheckedRoomNum');未签约则显示$('#selectUncheckedRoomNum')
	$('.shop-create-check-single').click(function(){
		
		//小区加载尚未完成，防止此时点击，会造成页面小区列表没有清空
		if(isloading){
			return;
		}
		
		var signstatus = $(this).find('.area-info-name').attr('signstatus');
		var gbId = $(this).find('.area-info-name').attr('gbid');
		var areaName = $(this).find('.area-info-name').text();
		
		$searchBox.hide();
		$('.shop-create-check').html('');
		
		$reSearch.insertAfter('#citySelectNewBox').removeClass('dsn');
		
		$("input[name=groupBuildingName]").val(areaName);
		
		if($('[name=gbId]').length){
			$('[name=gbId]').val(gbId);
		}else{
			$("<input class='dsn' name='gbId' type='hidden' value='" + gbId + "'/>").insertBefore('#citySelectBox');
		}
		
		if($('input[name=groupBuildingId]').length){
			$('input[name=groupBuildingId]').val(gbId);
		}else{
			$("<input class='dsn' name='groupBuildingId' type='hidden' value='" + gbId + "'/>").insertBefore('#citySelectBox');
		}
		
        $addressIcon.removeClass('icon-margin-top');

		if(signstatus == 1){
			
			//已有门牌，选择小区后，选择楼栋单元房间号
			$('#selectCheckedRoomNum').insertAfter('#citySelectNewBox').show().find('.areaNameBox option').text(areaName).val(gbId);
			$('#selectCheckedRoomNum').find('.areaNameBox').val(gbId);
			setBuildingSelect('buildingNum',gbId);
			
			//确定按钮
			$('.item-list-arrow-box').removeClass('dsn');
			$('.paddingbottom').removeClass('dsn');
		}else if(signstatus == 0){
			
			//没有门牌，创建新门牌，输入小区、楼栋单元房间号
			$('#selectUncheckedRoomNum').insertAfter('#citySelectNewBox').show().find('.areaNameBox option').text(areaName).val(gbId);

			//确定按钮
			$('.item-list-arrow-box').removeClass('dsn');
			$('.paddingbottom').removeClass('dsn');
		}
		//$('[name=gbSignStatus]').val(signstatus);

		if($('[name=gbSignStatus]').length){
			$('[name=gbSignStatus]').val(signstatus);
		}else{
			$("<input class='dsn' name='gbSignStatus' type='hidden' value='" + signstatus + "'/>").insertBefore('#citySelectBox');
		}
	});
	
	//添加小区门牌号
	$(document).on('click', '.creatInfo', function(){

		//小区加载尚未完成，防止此时点击，会造成页面该按钮没有移除
		if(isloading){
			return;
		}
		
		//创建新门牌，清空小区id
		if($('[name=gbId]').length){
			$('[name=gbId]').remove();
		}
		//创建新门牌，移除小区签约状态
		if($('[name=gbSignStatus]').length){
			$('[name=gbSignStatus]').remove();
		}
		//创建新门牌，插入addressBlockId
		if($('[name=addressBlockId]').length){
			$('[name=addressBlockId]').val($('#block').val());
		}/*else{
			$("<input class='dsn' name='addressBlockId' type='hidden' value='" + $('#block').val() + "'/>").insertBefore('#citySelectBox');
		}*/

		//确定按钮
		$('.item-list-arrow-box').removeClass('dsn');
		$('.paddingbottom').removeClass('dsn');
		
		$(this).addClass('dsn').appendTo('body');
		$searchBox.hide();
		$('.shop-create-check').html('');
		$reSearch.insertAfter('#citySelectNewBox').removeClass('dsn');
		//写入已搜索小区名称
		$('#writeRoomNum').find('[name=groupBuildingName]').val($('#shopCreateSearchInput').val());
		$('#writeRoomNum').insertAfter('#citySelectNewBox').show();
		
	});
	
	//重新搜索
	$(document).on('click', '.reSearch', function(event){
		
		$(this).addClass('dsn').appendTo('body');
        $shopCreateSearchInput.focus();

        cleanSearch();
	});
	
	//修改当前门牌地址，仅能修改，不能选
	$('.modifyAddress').click(function(){
		if($(this).siblings('input').attr('readonly') == 'readonly'){
			$(this).siblings('input').removeAttr("readonly").focus();
			$(this).text('保存');
		}else{
			$(this).siblings('input').attr('readonly', 'readonly');
			$(this).text('修改');
		}
	});
	
	//预约地址门牌选择
	$('#serverAddressBox').click(function(){
		if($('.inputform').find('.shop-part02').length > 0){
			$('.shop-part02').appendTo('.inputform-location-box');
		}
		
		$('.shop-part01').addClass('dsn');
        var changeDefaultRoomVal = getCookie('changeDefaultRoom');
        if(changeDefaultRoomVal === '1'){
        	//新用户 直接跳添加地址
            $('.shop-part02').removeClass('dsn');
		}else {
        	//老用户 读取cookies门牌信息
			var  defaultRoomAddr01 = getCookie('defaultRoomAddr01');
            var  defaultRoomAddr02 = getCookie('defaultRoomAddr02');
            var dredgeAddrId = $('[name=dredgeAddrId]').val();//用户地址id
            if(defaultRoomAddr02 != null && defaultRoomAddr02 != undefined){
                $('#currAddrText').text(defaultRoomAddr02);
                $('#currBlockAddrText').text(defaultRoomAddr01);
            }else{
                dredgeAddress = $('#currAddrText').text() + $('#currBlockAddrText').text();
			}

            $('#serverAddressList').removeClass('dsn');
            var dredgeProductId = $('[name=dredgeProductId]').val();
            var $addrUl = $('#otherDregedAddr');
            var $otherAddrDesc = $('.otherAddrDesc');
            var isAddNewAddr = $('[name=isAddNewAddr]').val();
            if(isAddNewAddr === '1'){
            	//新增了或者一开始需要加载页面
				if($addrUl.find('.otherAddrLi').length > 0){
                    $addrUl.find('.otherAddrLi').remove();
                    $otherAddrDesc.addClass('dsn');
				}
                jQuery.ajax({
                    url: "../common/toUrl.do",
                    cache: false,
                    dataType:"json",
                    async:false,
                    data:{"detailUrl" : "/dredgeAddress/dredgeAddressList.json", "dredgeProductId":dredgeProductId},
                    success: function(data){
                        if(data.dataValue.list.length > 0){
                            $addrUl.removeClass('dsn');
                            $otherAddrDesc.removeClass('dsn');
                            var $addrLi = $('#serverAddressList').find('.otherAddrLi.dsn');
                            $.each(data.dataValue.list, function(i, item) {
                                var $addrLisClone = $addrLi.clone(true).removeClass('dsn');
                                $addrLisClone.find('.addrText').text(item.address);
                                $addrLisClone.find('.blockAddrText').text(item.city+item.block);
                                $addrLisClone.find('.orther-address-info').attr('data-addrid',item.addressId).attr('data-addrblockid',item.blockId);
                                $addrLisClone.find('.updAddr').attr('data-addrid',item.addressId);
                                $addrLisClone.find('.delAddr').attr('data-addrid',item.addressId);
                                if(dredgeSelectedAddressId === (item.addressId + '')){
                                    $addrLisClone.find('.orther-address-info').addClass('hasSel').addClass('list-icon-check');
								}
								if(!item.valid){
                                    $addrLisClone.find('.addrText').addClass('grey');
                                    $addrLisClone.find('.validText').removeClass('dsn');
								}
                                $addrUl.append($addrLisClone);
                            });
                        }

                        $('[name=isAddNewAddr]').val('0');
                    },
                    error: function(){
                        $.Showmsg('网络不给力，请稍后重试');
                    }
                });
			}else {
                if($addrUl.find('.otherAddrLi').length > 0){
                    $addrUl.removeClass('dsn');
                }
			}
		}
	});
	//地址选择
	$(document).on('click','.orther-address-info',function () {
		//地址不在服务范围，不可选
		if(!$(this).find('.validText').hasClass('dsn')){
			$.Showmsg('该地址不支持此项服务');
			return false;
		}
		dredgeSelectedAddressId = $(this).attr('data-addrid');
		
        $('#otherDregedAddr').find('.otherAddrLi').find('.hasSel').removeClass('list-icon-check');
        $(this).addClass('list-icon-check').addClass('hasSel');
        $('#currAddr').removeClass('list-icon-check');
        var addrtext = $(this).find('.addrText').text();
        var blockAddrText = $(this).find('.blockAddrText').text();

        dredgeAddress = blockAddrText+addrtext;
        $('#addressTxt').text(dredgeAddress);
        if($('[name=dredgeAddress]').length === 0){
            $('<input name="dredgeAddress" type="text" hidden="hidden" value="' + dredgeAddress + '" />').prependTo('#wrapBox');
        }else{
            $('[name=dredgeAddress]').val(dredgeAddress);
        }
        
        //更新addressBlockId
		if($('[name=addressBlockId]').length){
			$('[name=addressBlockId]').val($('.orther-address-info').attr('data-addrblockid'));
		}

        $('.shop-part01').removeClass('dsn');
        $('#serverAddressList').addClass('dsn');
    });

	$('#currAddr').click(function () {
        $('#otherDregedAddr').find('.otherAddrLi').find('.hasSel').removeClass('list-icon-check');
		$(this).addClass('list-icon-check');
        dredgeAddressId = '';
        var currAddrtext = $('#currAddrText').text();
        var currBlockAddrText = $('#currBlockAddrText').text();

        dredgeAddress = currBlockAddrText+currAddrtext;
        $('#addressTxt').text(dredgeAddress);
        if($('[name=dredgeAddress]').length === 0){
            $('<input name="dredgeAddress" type="text" hidden="hidden" value="' + dredgeAddress + '" />').prependTo('#wrapBox');
        }else{
            $('[name=dredgeAddress]').val(dredgeAddress);
        }
        $('.shop-part01').removeClass('dsn');
        $('#serverAddressList').addClass('dsn');
    });
	//新增地址
	$('#addNewAddrBtn').click(function () {
        $('.shop-part02').removeClass('dsn');
        $('#serverAddressList').addClass('dsn');
        isupdAddr = false;
        dredgeAddressId = '';
    });
	$(document).on('click','.updAddr',function () {
    	$updAddrLi = $(this).parents('.otherAddrLi');//记录要修改的地址li
    	dredgeAddressId = $(this).attr('data-addrid'); 
        isupdAddr = true;
        $('.shop-part02').removeClass('dsn');
        $('#serverAddressList').addClass('dsn');
    });
    $(document).on('click','.delAddr',function () {
    	if(window.confirm('确定要删除该地址?')){
            dredgeAddressId = $(this).attr('data-addrid');
            var $that = $(this);
            //删除地址
            jQuery.ajax({
                url: "../common/toUrl.do",
                cache: false,
                dataType:"json",
                type:"post",
                async:false,
                data:{"detailUrl" : "/dredgeAddress/delDredgeAddress.json", "dredgeAddressId":dredgeAddressId},
                success: function(data){
                    $that.parents('.otherAddrLi').remove();
                    if($('#otherDregedAddr').find('.otherAddrLi').length===0){
                        $('#otherDregedAddr').find('.otherAddrDesc').addClass('dsn');
                    }
                },
                error: function(){
                    // $.Showmsg('网络不给力，请稍后重试');
                }
            });
		}
    });
	//关闭地址门牌选择
	$('#closeLocationBox').click(function(){
        var changeDefaultRoomVal = getCookie('changeDefaultRoom');
        if(changeDefaultRoomVal === '1'){
            $('.shop-part02').addClass('dsn');
            $('.shop-part01').removeClass('dsn');
		}else {
            $('.shop-part02').addClass('dsn');
            $('#serverAddressList').removeClass('dsn');
		}
	})
    //关闭地址列表选择
    $('#closeAddrListBox').click(function(){
        $('#serverAddressList').addClass('dsn');
        $('.shop-part01').removeClass('dsn');
    })
	//选择省份
	$('#province').change(function(){
		onSelectChange(this,'city');
	})
	//选择城市
	$('#city').change(function(){
		onSelectChange(this,'block');
	})
	//选择区县
	//重新选择小区
	$('#block').change(function(){
		$('.reSearch').click();
	})
	
	//确定门牌信息，组装完整地址到页面
	/*$('.item-list-arrow-box').click(function(){
		//获取省市区
		var curProvince = $('#province option:selected').text();
		var curCity = $('#city option:selected').text();
		var curBlock = $('#block option:selected').text();
		var addressTxt01 = curProvince + curCity + curBlock;
		
		//获取小区楼栋房间
		if($('#selectCheckedRoomNum').is(':visible')){
			var curAreaName = $('#areaNameSelected option:selected').text();
			var curAreaName = $('#areaNameSelected option:selected').text();
			var curAreaName = $('#areaNameSelected option:selected').text();
			var curAreaName = $('#areaNameSelected option:selected').text();
		}
		
	})*/
	
	//填写地址表单验证
	//确定门牌信息，组装完整地址到页面
	$(".inputform-location-box").Validform({
		tiptype:1,
		btnSubmit:".btnSubmit",
		postonce:false,
		beforeSubmit:function(){
			var blockId = $('#block').val();
			var gbId = $('[name=gbId]').val();
			var dredgeProductId = $('[name=dredgeProductId]').val();
			var realRoomId = $('[name=realRoomId]').val();
			var defaultRealRoomId = getCookie('defaultRealRoomId');
            var changeDefaultRoomVal = getCookie('changeDefaultRoom');

			//校验所选区域或小区是否在服务范围，不在则提示用户重新选择服务区县
			if(!isServerCover('/dredge/validateDredgeAddress.json', blockId, gbId, dredgeProductId)){
				return false;
			}
			
			//获取省市区
			var curProvince = $('#province option:selected').text();
			var curCity = $('#city option:selected').text();
			var curBlock = $('#block option:selected').text();
			var addressTxt01 = curCity + curBlock;
			//获取小区、楼栋、单元、房间
			//已签约小区
			if($('#selectCheckedRoomNum').is(':visible')){
				var curAreaName = $('#areaNameSelected option:selected').text();
				var curBuildingNum = $('#buildingNum option:selected').text();
				var curUnitNum = $('#unitNum option:selected').text();
				var curRoomNum = $('#roomNum option:selected').text();
                var addressTxt02 = curAreaName + curBuildingNum + '-' + curUnitNum + '-' + curRoomNum;

				if(curUnitNum === '请选择单元号'||curUnitNum === '无单元号'){
                    var addressTxt02 = curAreaName + curBuildingNum + '-' + curRoomNum;
				}
			}else{
				//未签约小区
				if($('#selectUncheckedRoomNum').is(':visible')){
					var curAreaName = $('#selectUncheckedRoomNum').find('[name=groupBuildingName]').val();
				
				//新创建小区
				}else if($('#writeRoomNum').is(':visible')){
					var curAreaName = $('#writeRoomNum').find('[name=groupBuildingName]').val();
				}
				
				var curBuildingNum = $('[name=buildingName]:visible').val();
				var curUnitNum = $('[name=unitName]:visible').val();
				var curRoomNum = $('[name=roomNum]:visible').val();

                var addressTxt02 = curAreaName + curBuildingNum + '-' + curRoomNum;
				if(curUnitNum.length > 0){
                    addressTxt02 = curAreaName + curBuildingNum + '-' + curUnitNum + '-' + curRoomNum;
                }
			}
			//关闭浮层
			$('#getAddressBtn').addClass('dsn');
			//设置地址校验为通过
			$('#addressValid').val('1');
            $('[name=isAddNewAddr]').val('1');
            
            //更新addressBlockId
    		if($('[name=addressBlockId]').length){
    			$('[name=addressBlockId]').val($('#block').val());
    		}
            
            //更新需要提交的地址信息
            dredgeAddress = addressTxt01 + addressTxt02;
            if($('[name=dredgeAddress]').length === 0){
                $('<input name="dredgeAddress" type="text" hidden="hidden" value="' + dredgeAddress + '" />').prependTo('#wrapBox');
            }else{
                $('[name=dredgeAddress]').val(dredgeAddress);
            }
            
            if(isupdAddr){
				//修改地址
                jQuery.ajax({
                    url: "../common/toUrl.do",
                    cache: false,
                    dataType:"json",
                    type:"post",
                    async:false,
                    data:{"detailUrl" : "/dredgeAddress/updDredgeAddress.json", "addressId":dredgeAddressId,"address":addressTxt02,"gbId":gbId,"blockId":blockId},
                    success: function(data){
                        // $('.reSearch').click();
                        cleanSearch();
                        $reSearch.addClass('dsn').appendTo('body');
                    },
                    error: function(){
                        // $.Showmsg('网络不给力，请稍后重试');
                    }
                });

                $updAddrLi.find('.addrText').text(addressTxt02);
                $updAddrLi.find('.blockAddrText').text(addressTxt01);
                
                //判断该地址是否选中，已选中，则同时修改预约页面上的地址
                if($updAddrLi.find('.hasSel').length>0){
                    $('#addressTxt').text(dredgeAddress).removeClass('dsn');
				}

                $('#serverAddressList').removeClass('dsn');
                
                //修改地址后更新地址列表
                var dredgeProductId = $('[name=dredgeProductId]').val();
                var $addrUl = $('#otherDregedAddr');
                var $otherAddrDesc = $('.otherAddrDesc');
                
				if($addrUl.find('.otherAddrLi').length > 0){
                    $addrUl.find('.otherAddrLi').remove();
                    $otherAddrDesc.addClass('dsn');
				}
				
                jQuery.ajax({
                    url: "../common/toUrl.do",
                    cache: false,
                    dataType:"json",
                    async:false,
                    data:{"detailUrl" : "/dredgeAddress/dredgeAddressList.json", "dredgeProductId":dredgeProductId},
                    success: function(data){
                        if(data.dataValue.list.length > 0){
                            $addrUl.removeClass('dsn');
                            $otherAddrDesc.removeClass('dsn');
                            var $addrLi = $('#serverAddressList').find('.otherAddrLi.dsn');
                            $.each(data.dataValue.list, function(i, item) {
                                var $addrLisClone = $addrLi.clone(true).removeClass('dsn');
                                $addrLisClone.find('.addrText').text(item.address);
                                $addrLisClone.find('.blockAddrText').text(item.city+item.block);
                                $addrLisClone.find('.orther-address-info').attr('data-addrid',item.addressId).attr('data-addrblockid',item.blockId);
                                $addrLisClone.find('.updAddr').attr('data-addrid',item.addressId);
                                $addrLisClone.find('.delAddr').attr('data-addrid',item.addressId);
                                if(dredgeSelectedAddressId === (item.addressId + '')){
                                    $addrLisClone.find('.orther-address-info').addClass('hasSel').addClass('list-icon-check');
								}
								if(!item.valid){
                                    $addrLisClone.find('.addrText').addClass('grey');
                                    $addrLisClone.find('.validText').removeClass('dsn');
								}
                                $addrUl.append($addrLisClone);
                            });
                        }

                        $('[name=isAddNewAddr]').val('0');
                    },
                    error: function(){
                        $.Showmsg('网络不给力，请稍后重试');
                    }
                });
			}else {
                //插入到inputform中一并提交
                //如果没有门牌则回写门牌
                if(changeDefaultRoomVal === '0'){

                    //新增预约地址
                    jQuery.ajax({
                        url: "../common/toUrl.do",
                        cache: false,
                        dataType:"json",
                        type:"post",
                        async:false,
                        data:{"detailUrl" : "/dredgeAddress/addDredgeAddress.json", "address":addressTxt02,"gbId":gbId,"blockId":blockId},
                        success: function(data){
                        	dredgeSelectedAddressId = data.dataValue.addressId + '';//记录选择的地址id
                            $('#currAddr').removeClass('list-icon-check');
                            // $('.reSearch').click();
                            cleanSearch();
                            $reSearch.addClass('dsn').appendTo('body');
                        },

                        error: function(){
                            // $.Showmsg('网络不给力，请稍后重试');
                        }
                    });
                }else{
                    if($('[name=dredgeAddress]').length){
                        $('[name=dredgeAddress]').remove();
                    }
                    $(".shop-part02").appendTo('.inputform');
                }

				//插入地址信息
                $('#addressTxt').text(dredgeAddress).removeClass('dsn');
                $('.shop-part01').removeClass('dsn');
			}
			
			$('.shop-part02').addClass('dsn');
			
		},
		callback:function(data){
			return;
		}
	});
	
	//读取cookie中的provinceId，设为选中
    var cookieProvinceId = getCookie('provinceId');
    if(cookieProvinceId){
    	$('#province').val(cookieProvinceId);
    	setSelect(cookieProvinceId,'city');
    }
    
});

//设置楼栋
function setBuildingSelect(obj,gbId){
	$('#' + obj).html('');//清空之前的选项
	$("#buildingNum").prepend('<option value="">请选择楼栋号</option>');
	jQuery.ajax({
		  url: "../common/toUrl.do",
		  cache: false,
		  dataType:"json",
		  async:false,
		  data:{"detailUrl" : "/room/qryBuildingInfoByGroupBuildingId.json", "groupBuildingId":gbId}, 
		  success: function(data){
		    $.each(data.dataValue.list, function(i, item) {
		    	$("<option value='" + item.name + "' data-bId='"+ item.id +"' data-value='" + item.unitList + "'>" + item.name + "</option>").appendTo($("#"+obj));
                // if(i===0){
		    		// //是否存在单元
				// 	if(item.unitList.length>0){
                 //        $('.unitLi').removeClass('dsn');
				// 	}else {
                 //        $('.unitLi').addClass('dsn');
				// 	}
				// }
		    });
		  },  
          error: function(){  
          	$.Showmsg('网络不给力，请稍后重试'); 
          }
	});
}

//设置单元号
function setUnitSelect(fromSelId, toSelId){
	var unitNamesValue = $(fromSelId).find('option:selected').attr('data-value');
	if(unitNamesValue !== '请选择楼栋号'){
        var unitNames = unitNamesValue.split(',');
        $("#"+toSelId).html('<option value="">请选择单元号</option>');
        $('#roomNum').html('<option value="">请选择房间号</option>');
        if(unitNamesValue == ''){
            $("<option value='" + "" + "'>" + "无单元号" + "</option>").appendTo($("#"+toSelId));
            // $('.unitLi').addClass('dsn');
            // setRoomNumSelect('roomNum');
        }else{
            // $('.unitLi').removeClass('dsn');
            $.each(unitNames, function(i, item) {
                $("<option value='" + item + "'>" + item + "</option>").appendTo($("#"+toSelId));
            });
        }
	}

}

//设置房间号
function setRoomNumSelect(toSelId){
	//清空之前的选项
	$('#' + toSelId).html('<option value="">请选择房间号</option>');
	var buildingId  = ($('#buildingNum').find('option:selected').attr("data-bId"));
	jQuery.ajax({//选择楼栋号，更新单元号
		  url: "../common/toUrl.do",
		  cache: false,
		  dataType:"json",
		  data:{"detailUrl" : "/room/qryRoomListByBuildingId.json", "buildingId":buildingId, "unitName":$('#unitNum').val()}, 
		  success: function(data){
			//alert(JSON.stringify(data));
		    $.each(data.dataValue.list, function(i, item) {
		    	$("<option value='" + item.id + "'>" + item.roomNum + "</option>").appendTo($('#' + toSelId));
		    });
		  },   
          error: function(){  
          	$.Showmsg('网络不给力，请稍后重试'); 
          } 
	});

}
