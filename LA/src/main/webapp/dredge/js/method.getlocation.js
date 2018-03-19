var isWeixin = location.search.indexOf('weixin') > -1;
//搜索小区
var $loading = $('.loading'),
	$creatInfo = $('.creatInfo'),
	$creatInfoClone = $creatInfo.clone(true),
	$searchBox = $('.searchBox'),
	$reSearch = $('.reSearch'),
	$reSearchClone = $('.reSearch').clone(true),
	$shopCreateBox = $('.shop-create-check'),
	$shopCreateSearchInput = $('#shopCreateSearchBtn');
	$addressIcon = $('#addressIcon');

//显示快速清除按钮
var $quickDelete = $('.shop-create-header .quick-delete');

//省市切换
function onSelectChange(obj,toSelId){
	setSelect(obj.value,toSelId);
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
				  cache: false,
				  dataType:"json",
				  async:false,
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
	              	showMsg('网络不给力，请稍后重试'); 
	              }
			});
		}
	}else if(toSelId === "block"){
		$('#block').html('<option value="">选择区县</option>');//清空之前的选项
		if(fromSelVal !== ""){
    		jQuery.ajax({
    			  url: "../common/toUrl.do",
    			  cache: false,
    			  dataType:"json",
    			  async:false,
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
                  	showMsg('网络不给力，请稍后重试'); 
                  }
    		});
		}
  	}	
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
				$loading.clone(true).addClass('newLoading').removeClass('dsn').prependTo($shopCreateBox);
			}
		},
		success:function(data){
			var $shopCreateCheckSingle = $('.shop-create-check-single.dsn');
			
			$shopCreateBox.html('');
			if(data.dataValue.list.length === 0){
				//$creatInfo.removeClass('dsn').appendTo($shopCreateBox);
				$('.item-list-arrow-box').removeClass('dsn');
			}else{
				$('.item-list-arrow-box').addClass('dsn');
				$.each(data.dataValue.list,function(iPro,dataPro){
					
					var $shopCreateCheckSingleClone = $shopCreateCheckSingle.clone(true);
					//将订单数据更新至$itemListInfoClone
					$shopCreateCheckSingleClone.find('.area-info-name').attr('gbid', dataPro.id);
					$shopCreateCheckSingleClone.find('.area-info-name').attr('signStatus', dataPro.signStatus);
					$shopCreateCheckSingleClone.find('.area-info-name').text(dataPro.name);
					$shopCreateCheckSingleClone.find('.area-info-address').text(dataPro.totalName);

					//插入页面
					$shopCreateCheckSingleClone.appendTo($shopCreateBox).removeClass('dsn');

				});
			}
		}
	});
}

$(function () {
	$('#cityOrientation').click(function(){
		//读取cookie中的provinceId，设为选中
	    var cookieProvinceId = getCookie('provinceId');
	    if(cookieProvinceId){
	    	$('#province').val(cookieProvinceId);
	    	setSelect(cookieProvinceId,'city');
	    }
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
					showMsg('请先选择省市区'); 
					$this.val('');
					$quickDelete.hide();
				}else if(currentSearchInputVal !== ""){
					areaListSearch('#shopCreateSearchBtn');
				}
			}
		},800);
	});

	//微信打开，定位城市，更新数据
	if(isWeixin){
		//选择省份
		$('#province').change(function(){
			onSelectChange(this,'city');
		})
		//选择城市
		$('#city').change(function(){
			onSelectChange(this,'block');
		})
	}

});