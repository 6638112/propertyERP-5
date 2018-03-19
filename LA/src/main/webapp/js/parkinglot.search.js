/* *
 * *weijc
 * *20171207
 * */
var $itemWrapBox = $('.item-list-box');
var $storeItemSingle = $('.lot-item.dsn');
var $searchInput = $('#storeSearch');
var $loading = $('.loading.hide');

$(function(){
	new FastClick(document.body);

	//已定位过城市，并且没有停车场缓存信息，直接查询该城市下所有停车场
	if(localStorage.currentCity && !sessionStorage.searchData){
		inputSearch('', localStorage.currentCity);
	//未定位过城市，通过定位获取用户当前城市
	}else if(!localStorage.currentCity){
		$.getScript('https://api.map.baidu.com/api?v=2.0&ak=BF512d9c46c962e21adf4bd17340b6cc&s=1&services=&t=20170608143204', function(){
			$.getScript('https://api.map.baidu.com/getscript?v=2.0&ak=BF512d9c46c962e21adf4bd17340b6cc&s=1&services=&t=20170608143204', function(){
				//自动定位
				autoLocation();
			});
		});
	}
	//已缓存停车场，读取缓存数据，恢复数据，恢复滚动条位置
	if(sessionStorage.searchData){
		
		$searchInput.val(sessionStorage.searchKey);
		$itemWrapBox.find('.lot-item').remove();
		if($('.newLoading').length == 0){
			$loading.clone(true).addClass('newLoading').removeClass('hide').prependTo($itemWrapBox);
		}

		parseItemData(JSON.parse(sessionStorage.searchData));
		$('.newLoading').remove();
		
		document.body.scrollTop = sessionStorage.scrollTop;
		
	}
	
	//获取签名信息
	var wxInfo = {
			signInfo: null,
			ajax: function(){
				var me = this;
				$.ajax({
					url:"../share/getShareParam.do",
					data:{currentURL:location.href.split('#')[0]},
					dataType:"json",
					async:false,
					success:function(data){
						me.signInfo = data.dataValue.signInfo;
						//统一微信分享风格 
						wx.config({
							debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
							appId: me.signInfo.appId, // 必填，公众号的唯一标识
							timestamp: me.signInfo.timestamp, // 必填，生成签名的时间戳
							nonceStr: me.signInfo.nonceStr, // 必填，生成签名的随机串
							signature: me.signInfo.signature,// 必填，签名，见附录1
							jsApiList: ['openLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
						});
					}
				});
			}
	};
	wxInfo.ajax();
	
	
	//打开微信地图
	$(document).on('click', '.lot-item', function(){
		var $this = $(this);
		sessionStorage.scrollTop = document.body.scrollTop;

		wx.ready(function(){
			// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
			wx.openLocation({
				//经纬度必须传浮点数，不能是字符串，否则ios下点击无法调起地图
			    latitude: $this.attr('latitude')*1, // 纬度，浮点数，范围为90 ~ -90
			    longitude: $this.attr('longitude')*1, // 经度，浮点数，范围为180 ~ -180。
			    name: $this.find('.lot-item-name').text(), // 位置名
			    address: $this.find('.item-desc').text(), // 地址详情说明
			    scale: 14, // 地图缩放级别,整形值,范围从1~28。默认为最大
			    infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
			});
		});
		
	});

	//苹果手机输入法，选中中文时无法触发keyup事件，因此换成点击按钮触发搜索
	$('.btn-search').click(function(event){
		inputSearch($.trim($searchInput.val()), localStorage.currentCity);
	});
	//键盘回车或输入法搜索按键触发
	$searchInput.on('keyup', function(event){
		var myEvent = event || window.event;
		currentVal = $.trim($searchInput.val());
		
		if(myEvent.keyCode === 13){
			inputSearch(currentVal, localStorage.currentCity);
		}
	});
	
	//取消搜索
	$(document).on('click', '#goBackBtn', function(){
		window.history.back(-1);
	});
	
});

//查询
function inputSearch(searchKey, currentCity){
	$.ajax({
		type:"get",
		url:"../temporaryParkingFee/getPlots.json",
		data:{city: currentCity, plotName: searchKey},
		dataType:"json",
		beforeSend:function(data){
			if($('.newLoading').length == 0){
				$loading.clone(true).addClass('newLoading').removeClass('hide').prependTo($itemWrapBox);
			}else{
				$('.newLoading').remove();
				$loading.clone(true).addClass('newLoading').removeClass('hide').prependTo($itemWrapBox);
			}
		},
		success:function(data){
			
			$itemWrapBox.find('.lot-item').remove();
			
			if(data.dataValue.length === 0){ 
				if(searchKey === '' && currentCity !== ''){
					$('.newLoading').html('当前城市暂无合作停车场信息');
				}else{
					$('.newLoading').html('找不到该停车场信息，换一个试试？');
				}
				return;
			}

			sessionStorage.searchKey = searchKey;
			sessionStorage.searchData = JSON.stringify(data.dataValue);
			
			parseItemData(data.dataValue);
			
			$('.newLoading').remove();
		}
	});
}

//解析数据
function parseItemData(itemData){
	
	$.each(itemData,function(iPro,dataPro){
		
		var $storeItemSingleClone = $storeItemSingle.clone(true);
		
		$storeItemSingleClone.find('.lot-item-name').text(dataPro.name);
		$storeItemSingleClone.find('.item-desc').text(dataPro.address);
		$storeItemSingleClone.attr('latitude', dataPro.latitude);
		$storeItemSingleClone.attr('longitude', dataPro.longitude);
		
		$storeItemSingleClone.removeClass('dsn').insertBefore($('.newLoading'));
		
	});
	
}

//自动定位
function autoLocation() {
	//第三方接口(百度)获取经纬度，有点偏差
	var geolocation = new BMap.Geolocation({
        enableHighAccuracy: true,//是否使用高精度定位，默认:true
        timeout: 10000
	}); 
	geolocation.getCurrentPosition(function (position) {  
        if (this.getStatus() === 0) {  
            //保存定位到的城市
            localStorage.currentCity = position.address.city;
        }else{
        	//定位失败，城市参数为空，显示所有城市停车场
        	localStorage.currentCity = '';
        }
        
        inputSearch($.trim($searchInput.val()), localStorage.currentCity);
    });
}