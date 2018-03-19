/*weijc 20170712*/
var androidAnniversaryShare, iosShareAnniversary;
$(function(){
	
	var shareLink = location.href;
	shareLink = shareLink + ((shareLink.indexOf('?') > -1 ? '&' : '?') + 'jfqsource=weixin');
	//微信分享
	if(isWeixin){
		setTimeout(function(){
			$.getScript('https://res.wx.qq.com/open/js/jweixin-1.0.0.js', function(){
			   $.getScript('../js/wx.share.info.js', function(){
				    //轻应用分享
					setShareInfo({
						title: '社惠主义好 解放区3周年感恩回馈',		// 分享标题
						desc: '我在解放区发现了超多好东西，重点是优惠享不停，绝对有你想要的，速来参与！',		// 分享描述
						link: shareLink,	// 分享链接
						imgIcon: getCurHref('resource') + '/docs/commonimages/jfqlogo_share.png'
					});
				});
			});
		}, 1500);
		
	}else{
	    //app分享交互方法
	    var shareInfo = {
				title: '社惠主义好 解放区3周年感恩回馈',		// 分享标题
				friendTitle: '社惠主义好 解放区3周年感恩回馈',		// 分享标题
				desc: '我在解放区发现了超多好东西，重点是优惠享不停，绝对有你想要的，速来参与！',		// 分享描述
				link: shareLink,	// 分享链接
				imgIcon: getCurHref('resource') + '/docs/commonimages/jfqlogo_share.png'
			};
	    
	    androidAnniversaryShare = function(){
	    	window.anniversaryShare.shareToFriendAnniversary(JSON.stringify(shareInfo));
	    }
	    
	    iosShareAnniversary = function(){
	    	setupWebViewJavascriptBridge(function(bridge) {
				bridge.callHandler('shareToFriendServer', shareInfo, function(response) {});
			});
	    }
	}
	
})

//自动定位
function autoLocation(callback) {
	//第三方接口(百度)获取经纬度，有点偏差
	var geolocation = new BMap.Geolocation({
		enableHighAccuracy: true,//是否使用高精度定位，默认:true
		timeout: 10000
	}); 
	geolocation.getCurrentPosition(function (position) {  
		if (this.getStatus() === 0) {  
			currentLat = position.point.lat;  
			currentLon = position.point.lng;  
			
			//声明地址解析器
			var point = new BMap.Point(currentLon, currentLat);  
			var geoc = new BMap.Geocoder();  
			geoc.getLocation(point, function (res) {  
				var addComp = res.addressComponents; //查询得到的城市、区域
				var building = res.surroundingPois[0].title; //附近楼栋、小区，取第一个。。最接近
				
				//记录新用户，预约提交时需要设置changeDefaultRoom参数值为1
				$.cookie('changeDefaultRoom', '1', { path: '/' });

                //保存位置到cookie
                setLocationName(addComp.city, addComp.district, building, null, null, null, null);

				//校验定位到的区域小区
				isServerCover(addComp.city, addComp.district, building,addComp.province,callback);
				
				//定位到城市区域后，才能获取对应类别
				var blockId = $.cookie('blockId');
				
			})  
		}else{
			showMsg("定位失败，请修改微信定位权限");
			$('#locationTips').removeClass('hide');
		}
	});
}

//保存位置信息到cookie
function setLocationName(cityName, blockName, gbName, provinceId, cityId, blockId, gbId){
	
	//expires单位为天，永久先设置为10年
	$.cookie('cityName', cityName, { path: '/' }); 
	$.cookie('blockName', blockName, { path: '/' }); 
	$.cookie('gbName', gbName, { path: '/' }); 

	if(provinceId){
		$.cookie('provinceId', provinceId, { path: '/' }); 
		$.cookie('cityId', cityId, { path: '/' }); 
		$.cookie('blockId', blockId, { path: '/' }); 
	}
	if(gbId){
		$.cookie('gbId', gbId, { path: '/' });
	}else if(gbId === null){
		$.cookie('gbId', '', { path: '/' });
	}
}

//校验区域小区
function isServerCover(cityName, blockName,gbName,provinceName, callback){
	$.ajax({
		url: "../common/toUrl.do",
		type: 'post',
		dataType:"json",
		async: false,
		// data:{"detailUrl" : "/dredge/qryAddressIds.json", "city": cityName, "block": blockName},
		data:{"detailUrl" : "/groupBuilding/qryGroupBuildingByLocation.json", "blockName": blockName, "cityName": cityName,"gbName":gbName,"provinceName":provinceName},

		success: function(data){ 
			if(data.status === '0000'){
				
				//校验完成，将返回的城市和区域id保存到cookie中，在预约页面中需要用到
				//需要一并返回provinceId，否则预约页面不知道所在省份
				$.cookie('provinceId', data.dataValue.provinceId, { path: '/' }); 
				$.cookie('cityId', data.dataValue.cityId, { path: '/' }); 
				$.cookie('blockId', data.dataValue.blockId, { path: '/' });
                $.cookie('gbId', data.dataValue.gbId, { path: '/' });
                $.cookie('gbName', data.dataValue.gbName, { path: '/' });

                //请求活动接口
    			callback(data.dataValue.gbId);
				
			}else{
				showMsg(data.message);
			}
		},  
		error: function(){  
			showMsg('网络不给力，请稍后重试'); 
		}
	});
}

//获取默认门牌信息
function isHasDefaultRoom(callback){
	$.ajax({
	    url: "../common/toUrl.do",
	    async: false,
	    data:{"detailUrl" : "/plotproperty/qryCurrGroupbuildingInfo.json", "isNeedLogin": "1"}, 
	    success: function(data){
		   if(data.status === '0000'  && JSON.stringify(data.dataValue) !== '{}'){
		    	var dataValue = data.dataValue;
		    	
				//保存默认门牌信息到cookie，在预约信息页面获取默认门牌地址
		    	var defaultRoomAddr01 = dataValue.defaultRoomInfo.province + dataValue.defaultRoomInfo.city + dataValue.defaultRoomInfo.block;
		    	var defaultRoomAddr02 = dataValue.defaultRoomInfo.groupBuilding + dataValue.defaultRoomInfo.building + dataValue.defaultRoomInfo.roomNum;
		    	$.cookie('defaultRoomAddr01', defaultRoomAddr01, { path: '/' });
		    	$.cookie('defaultRoomAddr02', defaultRoomAddr02, { path: '/' });
		    	
				//有默认门牌，则取默认门牌
				//设置门牌展示，保存位置信息到cookie
				setLocationName(dataValue.defaultRoomInfo.city, dataValue.defaultRoomInfo.block, dataValue.name, dataValue.provinceId, dataValue.cityId, dataValue.blockId, dataValue.id);

				//保存默认门牌id及房间号，用来比对是否修改了门牌
				$.cookie('defaultRealRoomId', dataValue.defaultRoomInfo.realRoomId, { path: '/' });
		    	//判断是否新用户，预约提交时老用户需要设置changeDefaultRoom参数值为0
    			$.cookie('changeDefaultRoom', '0', { path: '/' });
    			
				//请求活动接口
    			callback(dataValue.id);
					
		   }else{
			   //没有默认门牌，则通过定位获取用户当前位置 
			   $.getScript('https://api.map.baidu.com/api?v=2.0&ak=BF512d9c46c962e21adf4bd17340b6cc&s=1&services=&t=20170608143204', function(){
				   $.getScript('https://api.map.baidu.com/getscript?v=2.0&ak=BF512d9c46c962e21adf4bd17340b6cc&s=1&services=&t=20170608143204', function(){
						//自动定位
						autoLocation(callback);
					});
				});
		    }
	    },  
	    error: function(){  
	  	   showMsg('网络不给力，请稍后重试');
	    }
	});
}

var anniversaryVM = new Vue({
	el: '#container',
	data: {
		scenes: null,
		couponList: null,
		tipsShow: false,
		tipsMsg: '信息提示',
		couponBoxShow: false,
		isLoading: false,
		marketShowOnly: false,
		serverShowOnly: false,
		userId: ''
	},
	mounted: function(){
		var _self = this;
		
		//微信打开，获取城市id
        // var cityId = $.cookie('cityId');
        var gbId = $.cookie('gbId');

    	if(!isWeixin){
    		_self.getAjax();
    	}else{
    		//先判断cookie里是否保存有门牌信息，如果有则直接取门牌信息，不请求默认门牌
    		if(gbId){
    			_self.getAjax(gbId);
			//如果cookie没有保存门牌信息，则请求默认门牌
    		}else{
    			isHasDefaultRoom(_self.getAjax);
    		}
    	}
		
		//主分会场
		if(location.search.indexOf('marketpart') > -1){
			_self.marketShowOnly = true;
		}
		if($('#container').attr('data-param') === '1'){
			_self.serverShowOnly = true;
		}
		
	},
	methods: {
		getAjax: function(gbId){
			
	        var _self=this;
			var url = "../common/toUrl.do";
			var dataParams = {"detailUrl": "/coupon/qryCanReceiveScene.json"};
			
			if(gbId){
				dataParams.gbId = gbId;
			}
	        
	        axios.get(url, {
	        	params: dataParams
	        })
	        .then(function(response){
	        	
	        	if(response.data.status === '0000'){
		        	_self.scenes = response.data.dataValue.scenes;
	        		_self.userId = response.data.dataValue.userId;
	        	}else{
	        		showMsg(response.data.message);
	        	}
	        	
	        });
		},
		getCoupon: function(scene){
			
			var _self = this;
			var url = "../common/toUrl.do";
			
			if(_self.isLoading){
				return;
			}
			_self.isLoading = true;

			var gbId = $.cookie("gbId");

            var dataParams = {"sceneId": scene,"detailUrl": "/coupon/receiveSceneCoupon.json","isNeedLogin": "1"};
            if(gbId){
                dataParams.gbId = gbId;
            }

	        axios.get(url, {
	        	params: dataParams
	        })
	        .then(function(response){
	        	
	        	//已登录，领券成功
	        	if(response.data.status === '0000'){
	        		_self.couponList = response.data.dataValue.couponList;
	        		_self.userId = response.data.dataValue.userId;
	        		
	        		_self.couponBoxShow = true;
	        		_self.isLoading = false;
	        		
	        		//设置已领取状态
	        		_self.scenes[scene - 2].canReceive = 0;
	        		_self.scenes[scene - 2].hasReceive = 1;
	        		
	        		$('body').toggleClass('overhidden heightp100');
	        		
        		//未登录状态，app跳转到登录界面
	        	}else if(response.data.status === '0003'){
	        		
	    			if(!isWeixin){

		        		_self.isLoading = false;
		        		
	    				if(isAndroid){
	    					window.getAnniversary.getCouponList();
	    				}else if(isiOS){
	    					setupWebViewJavascriptBridge(function(bridge) {
	    						bridge.callHandler('getCouponList', null, function(response) {});
	    					});
	    				}
	    				
	    			}else{
		        		showMsg(response.data.message);
		        		_self.isLoading = false;
	    			}
	    			
	        	}else{
	        		showMsg(response.data.message);
	        		_self.isLoading = false;
	        	}
	        	
	        });
			
		},
		closeCouponBox: function(){
			
			this.couponBoxShow = false;
        	
        	$('body').toggleClass('overhidden heightp100');
			
		},
		gotoItemlist: function(item){
			
			if(isWeixin){
				location.href = item.linkUrl + '&jfqsource=weixin';
			}else{
				
				if(isAndroid){
					var urlScheme = 'jiefangqu://jiefangqu.com/openBannerJumpWebViewAct?url=' + item.linkUrl;
					OpenAppBySchema(urlScheme);
				}else if(isiOS){
					setupWebViewJavascriptBridge(function(bridge) {
						bridge.callHandler('clientDefineAction', {'type':'1','controll':'WebborseViewController','params':{'isGetWebTitle':true, 'isAddHeaderReq':true, 'webUrl': item.linkUrl}}, function(response) {});
					});
				}
			}
			
		},
		gotoCouponlist: function(){
			var _self = this;
			
			if(isWeixin){
				location.href = getCurHref('www') + '/LA/coupon/couponList.do?userId=' + _self.userId;
			}else{
				
				if(isAndroid){
					window.anniversary.jumpCouponList();
				}else if(isiOS){
					setupWebViewJavascriptBridge(function(bridge) {
						bridge.callHandler('couponList', null, function(response) {});
					});
				}
			}
			
		}
	}
})
