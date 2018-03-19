
var $mainPart01 = $('.main-part01');
var $mainPart02 = $('.main-part02');

//显示快速清除按钮
var $quickDelete = $('.shop-create-header .quick-delete');
var $shopCreateCheckSingle = $('.shop-create-check-single.hide');

//首次进入编辑页面，设置城市、区域
var unSetCity = true;
var unSetBlock = true;

//搜索小区
var $loading = $('.loading'),
	$creatInfo = $('.creatInfo'),
	$creatInfoClone = $creatInfo.clone(true),
	$searchBox = $('.searchBox'),
	$reSearch = $('.reSearch'),
	$reSearchClone = $('.reSearch').clone(true),
	$shopCreateBox = $('.shop-create-check'),
	$shopCreateSearchInput = $('#shopCreateSearchBtn');

$(function(){

	new FastClick(document.body);
	
	//表单验证
	$(".inputform").Validform({
		tiptype:1,
		btnSubmit:".btnSubmit",
		ajaxPost:false,
		postonce:true,
		beforeSubmit:function(curform){
			//拼接收货地址
			var detailAddress = $('#province option:selected').text() + $('#city option:selected').text() + $('#block option:selected').text() + $('#shopCreateSearchBox').val() + $('#userRoomNum').val();
			$('[name=addressDetail]').val(detailAddress);
		},
		callback:function(data){
		}
	});
	
	//检查触发按钮状态
	$('input,textarea').keyup(function(){
		countValNum();
		
	});
	$('select').change(function(){
		countValNum();
	});

	//搜索小区
	$('#searchBtn').click(function(){
		var currentSearchInputVal = $.trim($shopCreateSearchInput.val());
		//查询小区
		if(currentSearchInputVal !== ""){
			areaListSearch('#shopCreateSearchBtn');
		}
	});
	
	//获取键盘搜索按钮事件
    $("#shopCreateSearchBtn").on('keypress', function(e) {
        var keycode = e.keyCode;
        //获取搜索框的值
		var currentSearchInputVal = $.trim($shopCreateSearchInput.val());
        if (keycode == '13' && currentSearchInputVal !== "") {
            e.preventDefault();
    		//查询小区
			areaListSearch('#shopCreateSearchBtn');
        }
    });
	
	//取消搜索小区
	$('#goBackBtn').click(function(){
		$('.shop-part02').hide();
		$('.shop-part01').show();
	});
	
    //显示真正搜索框
    $shopCreateSearchBox = $('#shopCreateSearchBox');
    $shopCreateSearchBox.click(function(){
    	if($('#block').val() == ''){
    		$.Showmsg('请先选择区');
    		return false;
    	}
    	$('[name=areaSearchValid]').val('1');
		$('.shop-part01').hide();
		$('.shop-part02').show();
		$shopCreateSearchInput.focus();
	});

	$('.shop-create-search').on('keyup' ,function(){
		var curVal = $.trim($(this).val());
		if(!curVal == ''){
			$quickDelete.show();
		}else{
			$quickDelete.hide();
		}
	});
	
	//快速清除搜索框
	$quickDelete.click(function(){
		$('.shop-create-search').val('');
		$(this).hide();
	});
	
	/*$shopCreateSearchInput.keyup(function(){
		//触发搜索，将搜索框滚动至顶部
		window.location.hash = '#shopCreateSearchBtn';
		var currentSearchInputVal = $.trim($shopCreateSearchInput.val());
		//查询小区
		if(currentSearchInputVal !== ""){
			areaListSearch('#shopCreateSearchBtn');
		}
	});*/
	
	//更改省市区时，重置已选小区
	$('#province, #city, #block').change(function(){
		$("#shopCreateSearchBox, #shopCreateSearchBtn, #userRoomNum, [name=areaSearchValid]").val('');
		$shopCreateBox.html('');
	});
	
	//选中小区，填写小区名称
	$(document).on('click', '.shop-create-check-single', function(){
		var signstatus = $(this).find('.area-info-name').attr('signstatus');
		var gbId = $(this).find('.area-info-name').attr('gbid');
		var buildingName = $(this).find('.area-info-name').text();
		
		$("#shopCreateSearchBox").val(buildingName);
		$("[name=groupBuildingId]").val(gbId); 
		
		countValNum();
		
		$('.shop-part02').hide();
		$('.shop-part01').show();
	});
	
	//添加自定义小区
	$(document).on('click', '.creatInfo', function(){
		$('#shopCreateSearchBox').val($('#shopCreateSearchBtn').val());
		$("[name=groupBuildingId]").val('-1');
		
		$('.shop-part02').hide();
		$('.shop-part01').show();
	});
	
	//重新搜索
	$(document).on('click', '.reSearch', function(event){
		$('[name=gbSignStatus]').remove();
		$(this).addClass('dsn').appendTo('body');
		$searchBox.show().find('input').val('');
		$('#writeRoomNum:visible').appendTo('body').hide();
		$('#selectCheckedRoomNum:visible').appendTo('body').hide().find('select').val('');
		$('#selectUncheckedRoomNum:visible').appendTo('body').hide().find('input').val('');

		$('.shop-part01').hide();
		$('.shop-part02').show();
		$shopCreateSearchInput.focus();
	});

	//修改当前门牌地址，仅能修改，不能选
	$(document).on('click', '.modifyAddress', function(){
		if($(this).siblings('input').attr('readonly') == 'readonly'){
			$(this).siblings('input').removeAttr("readonly").focus();
			$(this).text('保存');
		}else{
			$(this).siblings('input').attr('readonly', 'readonly');
			$(this).text('修改');
		}
	});
	
	//初始化省份
	if(location.href.indexOf('updDeliveryAddress') > -1){
		onSelectChange($('#province').val(),'city');
		countValNum();
	}
	
	//查询小区
	function areaListSearch(searchInput){
		$.ajax({
			type:"get",
			url:"../common/toUrl.do",
			data:{detailUrl: "/groupBuilding/qryGroupBuildingDim.json", searchKey:$(searchInput).val(), page:1, pageNum:10, cityId:$('#city').val(), blockId:$('#block').val()},
			dataType:"json",
			beforeSend:function(data){
				if($('.newLoading').length == 0){
					$loading.clone(true).addClass('newLoading').show().prependTo($shopCreateBox);
				}
			},
			success:function(data){
				$('.newLoading').remove();

				$shopCreateBox.html('');
				
				if(data.dataValue.list.length === 0){
					$creatInfo.removeClass('dsn').appendTo($shopCreateBox);
				}else{
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
			}
		});
	}
	    
});

//触发按钮状态
function countValNum(){
	var selectCheckedRoomNum = $("#selectCheckedRoomNum").is(":visible");
	var selectUncheckedRoomNum = $("#selectUncheckedRoomNum").is(":visible");
	var writeRoomNum = $("#writeRoomNum").is(":visible");
	
	var userName = $.trim($("#userName").val());
	var userPhone = $.trim($("#userPhone").val());
	var province = $.trim($("#province").val());
	var city = $.trim($("#city").val());
	var block = $.trim($("#block").val());
	var building = $.trim($("#shopCreateSearchBox").val());
	var userRoomNum = $.trim($("#userRoomNum").val());
	var isPass = true;

	if(userName=="" || userPhone=="" || province=="" || city=="" || block=="" || building=="" || userRoomNum==""){
		isPass = false;
	}
	
	if(isPass){
		$('.btnSubmit').addClass('bgred noborder white');
	} else {
		$('.btnSubmit').removeClass('bgred noborder white');
	}
}

//省市切换
function onSelectChange(fromSelVal,toSelId){
	//手动选择省份后，将localStorage的block置为空，避免没有选择城市时，下次重新进入该页面无法设置block的value
	localStorage['block'] = '';
	setSelect(fromSelVal,toSelId);
}

//省市切换详情逻辑处理
function setSelect(fromSelVal,toSelId){
	if(toSelId === "city"){//选择省，更新市
  	$('#' + toSelId + ',#block').html('<option value="">请选择</option>');//清空之前的选项
		jQuery.ajax({
		  url: "../common/toUrl.do",
		  cache: false,
		  dataType:"json",
		  async:false,
		  data:{"detailUrl" : "/addressCity/getAddressCityListById.json", "provId":fromSelVal, "isNeedLogin":"0"}, //"provId="+fromSelVal,
		  success: function(data){
		    $.each(data.dataValue.list, function(i, item) {
		    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
		    });
		    //编辑页面，初始化城市
		    if(location.href.indexOf('updDeliveryAddress') > -1 && unSetCity === true){
		    	$('#city').val($('#city').attr('data-id'));
		    	onSelectChange($('#city').val(),'block');
		    	unSetCity = false;
			}
		  },  
          error: function(){  
        	$.Showmsg('网络不给力，请稍后重试'); 
          }
		});
		//$("#city").prepend('<option value="">选择城市</option>');
	}else if(toSelId === "block"){
		$('#' + toSelId).html('<option value="">请选择</option>');//清空之前的选项
		jQuery.ajax({
		  url: "../common/toUrl.do",
		  cache: false,
		  dataType:"json",
		  async:false,
		  data:{"detailUrl" : "/addressBlock/getAddressBlockListById.json", "cityId":fromSelVal, "isNeedLogin":"0"}, //"provId="+fromSelVal,
		  success: function(data){
		    $.each(data.dataValue.list, function(i, item) {
		    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
		    });
		    //编辑页面，初始化区域
		    if(location.href.indexOf('updDeliveryAddress') > -1 && unSetBlock === true){
		    	$('#block').val($('#block').attr('data-id'));
		    	unSetBlock = false;
			}
		  },  
          error: function(){  
          	$.Showmsg('网络不给力，请稍后重试'); 
          }
		});
	}
}
