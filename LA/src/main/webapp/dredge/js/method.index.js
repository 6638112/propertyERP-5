$(function () {
	
	//请求数据
	var isWeixin = location.search.indexOf('weixin') > -1;
    //h5跳转app
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	var serverBig = document.querySelectorAll('.server-big');
	var serverInfo = {};
	
	if(isWeixin || isAndroid){

		$(".reload-box").removeClass('dsn');
		$("#container").addClass('reload-container');
		
		//下拉刷新
	    pulldown('container', 80, '',  function (e) {
	        var that = this;
	        setTimeout(function () {
	            that.back.call();
	        }, 2000);
	    });
	}
	
	$(document).on('click', '.server-big', function(){
		var $this = $(this);
		if(isWeixin){
			//无需查询该城市是否开通
			//toDredageTypeList(this);
			window.location.href = $this.attr("data-href")+"&city="+$('#curCity').text();
		}else{
			serverInfo.id = $this.attr('data-id');
			serverInfo.name = $this.attr('data-name');
			if(isAndroid){
				//分类跳转
				window.homeServer.serverSelected(JSON.stringify(serverInfo));
			}
			if(isiOS){
				//分类跳转
				setupWebViewJavascriptBridge(function(bridge) {
					bridge.callHandler('serverSelected', serverInfo, function(response) {});
				});
			}
		}
	});
	
	//微信打开，定位城市，更新数据
	if(isWeixin){
		var $reloadContainer = $('#container');
		var $mainPart01 = $('.main-part01');
		var $mainPart02 = $('.main-part02');
		var curCityName = getUrlParam('curCity');
		var cookie_cityName = $.cookie('cityName');
		var cookie_blockName = $.cookie('blockName');
		var cookie_gbName = $.cookie('gbName');
		
		$('.fantasia-header').removeClass('dsn');
		
		//参数‘shenzhen’,仅针对深圳地区进行的活动，由页面控制是否显示的静态内容
		if(curCityName === 'shenzhen'){
			$.cookie('cityName', '深圳市', { path: '/' });
		}
		
		//先判断cookie里是否保存有门牌信息，如果有则直接取门牌信息，不请求默认门牌
		if(cookie_cityName && cookie_blockName && cookie_gbName){
			$('#curCity').html(cookie_cityName);
			$('#curDistrict').html(cookie_blockName);
			$('#curBuilding').html(cookie_gbName);
			
			//获取服务分类
	    	var blockId = $.cookie('blockId');
	    	var gbId = $.cookie('gbId');
			updateCommunitySupplyTypeAfterInsertCity('blockId', blockId);
			//获取底部促销商品列表
			updateProductListAfterInsertCity(blockId, gbId)
			
			//获取banner广告
			updateADAfterInsertCity($("#curCity").text());
			
		//如果cookie没有保存门牌信息，则请求默认门牌
		}else{
			
			//获取默认门牌信息
			isHasDefaultRoom();
			
		}

		//设置定位城市
		$('#cityOrientation').click(function(){
			$reloadContainer.addClass('dsn');
			$mainPart02.removeClass('dsn');
			
			//设置弹框高度
			$('.getlocation-box').addClass('heightp100');
			$reloadContainer.addClass('dsn');
		});
		
		//关闭城市定位box
		$('#closeLocationBox').click(function(){
			$reloadContainer.removeClass('dsn');
			$mainPart02.addClass('dsn');
			
			//设置弹框高度
			$('.getlocation-box').removeClass('heightp100');
			$reloadContainer.removeClass('dsn');
		});
		
		//用户输入小区不在服务范围，点击确定按钮，开始请求服务类别，发送区县id
		$('.item-list-arrow-box').click(function(){
			var provinceId = $('#province').val();
			var cityId = $('#city').val();
			var blockId = $('#block').val();
			var gbName = $('#shopCreateSearchBtn').val();
			if(gbName !== ''){
				var cityName = $('#city').find('option:selected').text();
				var blockName = $('#block').find('option:selected').text();

    			//保存位置到cookie
				setLocationName(cityName, blockName, gbName, provinceId, cityId, blockId, null);
				
				$mainPart02.addClass('dsn');
				$reloadContainer.removeClass('dsn');
				
				updateCommunitySupplyTypeAfterInsertCity('blockId', blockId);
				//获取底部促销商品列表
				updateProductListAfterInsertCity(blockId, null)
				
				updateADAfterInsertCity($("#curCity").text());
				
			}else if(gbName == ''){
				showMsg('请输入您所在小区');
			}
		});
		
		//用户输入小区，有结果列表，点击小区选中，开始请求服务类别，发送小区id
		$(document).on('click', '.shop-create-check-single', function(){
			var gbId = $(this).find('.area-info-name').attr('gbid');
			
			var cityName = $('#city').find('option:selected').text();
			var blockName = $('#block').find('option:selected').text();
			var gbName = $(this).find('.area-info-name').text();

			var provinceId = $('#province').val();
			var cityId = $('#city').val();
			var blockId = $('#block').val();
			var gbId = $(this).find('.area-info-name').attr('gbid');

			//保存位置到cookie
	    	setLocationName(cityName, blockName, gbName, provinceId, cityId, blockId, gbId);
			
			$mainPart02.addClass('dsn');
			$reloadContainer.removeClass('dsn');

			updateCommunitySupplyTypeAfterInsertCity('gbId', gbId);
			//获取底部促销商品列表
			updateProductListAfterInsertCity(blockId, gbId)
			
			//updateCommunitySupplyTypeAfterInsertCity($("#curCity").text());
			updateADAfterInsertCity($("#curCity").text());
		});
		
	}else{
		updateCommunitySupplyTypeAfterInsertCity('gbId', getUrlParam('gbId'));
		//获取底部促销商品列表
		updateProductListAfterInsertCity(null, null)
		updateADAfterInsertCity(null);

		//刷新完成回调
		if(isAndroid){
			window.itemList.pageLoadDone();
		}
	}

	//更新城市后要更新维修类别
	function updateCommunitySupplyTypeAfterInsertCity(paramKey, paramName){
		var $ul = $("ul.maintain-menus");
		var dataParams = {"detailUrl":"/dredge/qryCommunitySupplyType.json",};
		if(paramKey !== null){
			dataParams[paramKey] = paramName;
		}
		
		$.ajax({
			 url: "../common/toUrl.do",
			  async: true,
			  dataType:"json",
			  data:dataParams,
			  success: function(data){
				  if(data.status!="0000"){
					  showMsg(data.message);
				  }else{
					  $ul.html("");
					  
					  if(data.dataValue.dredgeList.length === 0){
						  $ul.hide();
					  }
					  
					  $.each(data.dataValue.dredgeList, function(i,item){
						 //var li_item = '<li class="borderright"><a class="dgTypeItem" href="javaScript:void(0)" data-href="maintainTypelist.do?name='+ item.name +'&amp;parentTypeId='+ item.supplyTypeId +'" data-value="'+ item.supplyTypeId +'" onclick="return toDredageTypeList(this);"><img class="wp18" style=" min-width: 27px !important;" src="'+ item.iconUrl +'"><div>'+ item.name +'</div></a></li>';
						 var li_item = '<li class="server-big pointer" data-href="maintainTypelist.do?name='+ item.name +'&parentTypeId='+ item.id +'" data-id="'+ item.id +'" data-name="'+ item.name +'"><img src="'+ item.picUrl +'"/><div class="mtop5">'+ item.name +'</div></li>';
						 $ul.append(li_item);
					  });
	
					  //插入空白补齐li
					  var liLength = $ul.find('li').length;
					  if(liLength > 0 && liLength < 4){
						  for(var j = 0; j < 4 - liLength; j++){
							  $ul.append('<li class="boxflex01"></li>');
						  }
					  }
					  
				  }
			  },
			  error: function(){  
	           	showMsg('网络不给力，请稍后重试'); 
	         }
		});
	}
	
	function toDredageTypeList(dgTypeName){
		var cityName = $('#curCity').text();
		var cstId = $(dgTypeName).attr("data-id");
		var isOpenDredgeService = 0;
		$.ajax({
			 url: "qryOpenDredgeService.html",
			  async: false,
			  dataType:"json",
			  data:{"cstId" : cstId, "cityName":cityName,},
			  success: function(data){
				  isOpenDredgeService = data.dataValue.isOpenDredgeService;
			  }
		});
		
		if(isOpenDredgeService==0){
			window.location.href = "maintainRecruit.jsp";
		}else{
			window.location.href = $(dgTypeName).attr("data-href")+"&city="+$('#curCity').text();//+"&myNumber=50003&myPassword=123456"
		}
		return false;
	};
	
	
	//更新城市后更新广告图
	function updateADAfterInsertCity(cityName){
		var $ul = $("#slideBox ul");
		var dataParams = cityName != null ? {"code":"LA_WX", "city":cityName, "detailUrl":"/homeService/qryAds.json"} : {"gbId":getUrlParam("gbId"), "detailUrl":"/homeService/qryAds.json"};    	
		$.ajax({
			  url: "../common/toUrl.do",
			  async: true,
			  dataType:"json",
			  data:dataParams,
			  success: function(data){
				  if(data.status!="0000"){
					  showMsg(data.message);
				  }else{
					  if(data.dataValue.list.length != 0){
						  var li_item = '';
						  $.each(data.dataValue.list, function(i,item){
							  //li_item += '<li><a href="' + item.linkUrl + '"><img src="' + item.picUrl + '?x-oss-process=image/resize,w_640/format,jpg/quality,q_90/interlace,1" /></a></li>';

							  var curUrl = item.picUrl;
							  var newImgUrl = curUrl + (curUrl.indexOf('?') > -1 ? '&':'?') + 'x-oss-process=image/resize,m_fill,w_640,h_299/format,jpg/quality,q_95/interlace,1';
								
							  //app banner跳转类型：
							  //1、跳转到商品详情：默认type为1，且链接不为#，不带参数toh5page(直接配置商品链接如：https://www.linlile.com.cn/LA/dredge/productDetail.html?productId=1022)	//去除version参数.replace(/(&version=\d*)|(version=\d*&)/g, '')
							  //2、不跳转：默认type为1，且链接为#，不带参数toh5page
							  //3、跳转到h5页面：默认type为1，且链接不为#，需带参数toh5page
							  //4、跳转到商品分类：type不为1，oos直接配置跳转分类
							  if(item.type == 1 && item.linkUrl.indexOf('#') === -1 && item.linkUrl.indexOf('toh5page') === -1) {
								  li_item += '<li><a class="app-jump" data-title="' + item.tittle+ '" href="' + item.linkUrl+ '"><img src="' + newImgUrl + '" /></a></li>';
							  }else if(item.type == 1 && item.linkUrl.indexOf('#') > -1 && item.linkUrl.indexOf('toh5page') === -1) {
								  li_item += '<li><a href="javascript:void(0)"><img src="' + newImgUrl + '" /></a></li>';
							  }else if(item.type == 1 && item.linkUrl.indexOf('#') === -1 && item.linkUrl.indexOf('toh5page') > -1){
								  li_item += '<li><a class="h5-jump" data-title="' + item.tittle+ '" data-id="' + item.id + '" href="' + item.linkUrl+ '"><img src="' + newImgUrl + '" /></a></li>';
							  }else {
								  var i = item.linkUrl.lastIndexOf('/');
								  // item.linkUrl = http://api.linlile.com.cn:8080/API/116,  只取出116作为参数放在data-id中
								  li_item += '<li><a href="javascript:void(0)" class="server-big" ' + 'data-id="' + item.linkUrl.substring(i+1) + '" data-name="' + item.tittle + '"' + ' data-source="拦腰广告"' + '><img src="' + newImgUrl + '" /></a></li>';
							  }
						  
						  }); 
						  
						  $ul.html(li_item);
					  }else{
						  var li_item = '<li><a href="javaScript:void(0)"><img src="images/main_banner.jpg" /></a></li>';
						  $ul.html(li_item);
					  }
					  
					  TouchSlide({ 
						slideCell:"#slideBox",
						titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
						mainCell:".bd ul", 
						effect:"left", 
						autoPage:true,//自动分页
						autoPlay:true //自动播放
					  });
					  
						var $li = $('#slideBox .hd ul li');
						if($li.length == 1){
							$li.addClass('dsn');
						}
						
						if(!isWeixin){
							var appJump = document.querySelectorAll('.app-jump');
							var h5Jump = document.querySelectorAll('.h5-jump');
							if(isAndroid){
								//banner跳转到详情页
								for(var i = 0; i < appJump.length; i++){
									appJump[i].onclick = function(e){
										e.preventDefault();
										var thisLink = this.href; 
										var thisSearch = thisLink.substring(thisLink.indexOf('?') + 1);
										var productId = getStrParam(thisSearch, 'productId');
										var urlScheme = 'jiefangqu://jiefangqu.com/openMainActGroup?DLJ=1&tab=3&clazz=com.jiefangqu.living.act.dredge.HomeServiceProductDetailAct&paramsStr=SA.string_array_url_params-' + thisLink + '+S.productId-' + productId;
										OpenAppBySchema(urlScheme);
									}
								}

								//banner跳转到h5活动页
								for(var i = 0; i < h5Jump.length; i++){
									h5Jump[i].onclick = function(e){
										e.preventDefault();
										var thisLink = this.href; 
										thisLink = thisLink.split('?')[0];
										var thisTitle = this.getAttribute('data-title'); 
										if(thisLink.indexOf('anniversary') > -1){
											var urlScheme = 'jiefangqu://jiefangqu.com/openJfqAnniversaryAct?url=' + thisLink + '&title=' + thisTitle;
										}else{
											var urlScheme = 'jiefangqu://jiefangqu.com/openMainActGroup?DLJ=1&tab=3&clazz=com.jiefangqu.living.act.buy.BannerJumpWebViewAct&paramsStr=SA.string_array_url_params-' + thisLink + '+S.title-' + thisTitle;
										}
										OpenAppBySchema(urlScheme);
									}
								}
							}
							if(isiOS){
								//banner跳转详情页
								for(var i = 0; i < appJump.length; i++){
									appJump[i].onclick = function(e){
										e.preventDefault();
										var thisLink = this.href; 
										var reg = new RegExp("(^|&)" + "productId" + "=([^&]*)(&|$)");
										var arr = thisLink.split('?');
										var param = arr[1].match(reg);
										var productId = unescape(param[2]);
										
										//var urlScheme = "jiefangqu://jiefangqu.com?params={'type':'1','controll':'HSProductInfoViewController','params':{'productId':'" + productId + "','webUrl':'" + thisLink + "'}}";
										//OpenAppBySchema(urlScheme);

										setupWebViewJavascriptBridge(function(bridge) {
											bridge.callHandler('clientDefineAction', {'type':'1','controll':'HSProductInfoViewController','params':{'productId': productId, 'webUrl': thisLink}}, function(response) {});
										});
									}
								}

								//banner跳转h5活动页
								for(var i = 0; i < h5Jump.length; i++){
									h5Jump[i].onclick = function(e){
										e.preventDefault();
										var thisLink = this.href;
										thisLink = thisLink.split('?')[0];
										var thisTitle = this.getAttribute('data-title'); 
										
										//var urlScheme = "jiefangqu://jiefangqu.com?params={'type':'1','controll':'WebborseViewController','params':{'title':'" + thisTitle + "','isAddHeaderReq':true,'webUrl':'" + thisLink + "'}}"	;
										//OpenAppBySchema(urlScheme);

										setupWebViewJavascriptBridge(function(bridge) {
											if(thisLink.indexOf('anniversary') > -1){
												bridge.callHandler('clientDefineAction', {'type':'1','controll':'ThematicActivitiesViewController','params':{'title': thisTitle, 'isAddHeaderReq': true, 'webUrl': thisLink}}, function(response) {});
											}else{
												bridge.callHandler('clientDefineAction', {'type':'1','controll':'WebborseViewController','params':{'title': thisTitle, 'isAddHeaderReq': true, 'webUrl': thisLink}}, function(response) {});
											}
										});
									}
								}
							}
						}
						
				  }
				  var cityName = $('#curCity').text();
			  },
			  error: function(){  
	           	showMsg('网络不给力，请稍后重试'); 
	         }
		});
	}
	
	
	//更新城市后更新底部促销商品列表
	function updateProductListAfterInsertCity(blockId, gbId){
		var dataParams = blockId != null ? {"blockId":blockId, "gbId":gbId, "detailUrl":"/dredge/qryDredgeBottomAd.json"} : {"gbId":getUrlParam("gbId"), "detailUrl":"/dredge/qryDredgeBottomAd.json"};    	
		$.ajax({
			  url: "../common/toUrl.do",
			  async: true,
			  dataType:"json",
			  data:dataParams,
			  success: function(data){
				  if(data.status!="0000"){
					  showMsg(data.message);
				  }else{
					  if(data.dataValue.ads.length != 0){
						  $('.activity-wrap:visible').remove();
						  var li_item = '';
						  var $productLi = $('.server-hot.swiper-slide.dsn');
						  var $activityWrap = $('.activity-wrap.dsn');
						  $.each(data.dataValue.ads, function(i,dataList){
							  var $activityWrapClone = $activityWrap.clone(true);
							  
							  $activityWrapClone.find('.adDesc').text(dataList.adDesc);
							  
							  if(dataList.adList){
								  $.each(dataList.adList, function(i,item){
								  
									  var $productLiClone = $productLi.clone(true);
									  var curUrl = item.picUrl;
									  var newImgUrl = curUrl + (curUrl.indexOf('?') > -1 ? '&':'?') + 'x-oss-process=image/resize,m_fill,w_210,h_210/format,jpg/quality,q_95/interlace,1';
									  
									  $productLiClone.find('.server-hot-img-box img').attr('src', newImgUrl);
									  $productLiClone.find('.firstLine').text(item.firstLine);
									  $productLiClone.find('.secondLine').text(item.secondLine);
									  $productLiClone.find('.thirdLine').text(item.thirdLine);
									  
									  //有item.linkUrl，可跳转
									  if(item.linkUrl){
										  var idIndex = item.linkUrl.indexOf('productId=') + 10;
										  var productId = item.linkUrl.substring(idIndex);
										  
										  $productLiClone.attr('data-href', item.linkUrl);
										  $productLiClone.attr('data-id', productId);
									  }
									  
									  $productLiClone.removeClass('dsn').appendTo($activityWrapClone.find('.swiper-wrapper'));
								  });
							  
								  $activityWrapClone.removeClass('dsn').appendTo('.main-part01');
							  }
							  
						  }); 

							//限时活动拖动效果
							if($(".swiper-wrapper").length){
							    //setSlideWidth('.swiper-wrapper');
								var swiper = new Swiper('.swiper-container', {
							        pagination: '.swiper-pagination',
							        slidesPerView: 2.5,
							        spaceBetween: 5,
									freeMode: true,
							        
							        //3.4.2版本才有效
							        slidesOffsetBefore: 10,
							        slidesOffsetAfter: 10,
							        
							        onInit: function(swiper){
							        	//初始化前加上pleft10，防止初始化时布局抖动，完成后移除class
							        	$(".swiper-container").removeClass('opacity0');
							        }
							    });
							}
						  
					  }
				 
					  var serverHot = document.querySelectorAll('.server-hot');
						if(!isWeixin){
							if(isAndroid){
								//底部促销商品跳转详情页
								for(var i = 0; i < serverHot.length; i++){
									serverHot[i].onclick = function(e){
										e.preventDefault();
										var thisLink = this.getAttribute('data-href'); 
										var productId = this.getAttribute('data-id'); 
										if(thisLink){
											var urlScheme = 'jiefangqu://jiefangqu.com/openMainActGroup?DLJ=1&tab=3&clazz=com.jiefangqu.living.act.dredge.HomeServiceProductDetailAct&paramsStr=SA.string_array_url_params-' + thisLink + '+S.productId-' + productId + '+S.title-解放区';
											OpenAppBySchema(urlScheme);
										}
									}
								}
	
							}
							if(isiOS){
								//底部促销商品跳转详情页
								for(var i = 0; i < serverHot.length; i++){
									serverHot[i].onclick = function(e){
										e.preventDefault();
										var thisLink = this.getAttribute('data-href'); 
										var thisProductId = this.getAttribute('data-id'); 
										if(thisProductId){
											//var urlScheme = "jiefangqu://jiefangqu.com?params={'type':'1','controll':'HSProductInfoViewController','params':{'productId':'" + thisProductId + "','webUrl':'" + thisLink + "'}}";
											//OpenAppBySchema(urlScheme);

											//跳服务详情只需id，webUrl可不传
											setupWebViewJavascriptBridge(function(bridge) {
												bridge.callHandler('clientDefineAction', {'type':'1','controll':'HSProductInfoViewController','params':{'productId': thisProductId, 'webUrl': thisLink}}, function(response) {});
											});
										}
									}
								}
	
							}
						}else if(isWeixin){
							for(var i = 0; i < serverHot.length; i++){
								serverHot[i].onclick = function(e){
									e.preventDefault();
									var thisLink = this.getAttribute('data-href'); 
									if(thisLink){
										location.href = thisLink + '&jfqsource=weixin';
									}
								}
							}
						}
						
				  }
				  var cityName = $('#curCity').text();
			  },
			  error: function(){  
	           	showMsg('网络不给力，请稍后重试'); 
	         }
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
        			
        			//校验定位到的区域小区
        			isServerCover(addComp.city, addComp.district);
        			
        			//保存位置到cookie
        	    	setLocationName(addComp.city, addComp.district, building, null, null, null, null);
        			
        			//定位到城市区域后，才能获取对应类别
        	    	var blockId = $.cookie('blockId');
        			updateCommunitySupplyTypeAfterInsertCity('blockId', blockId);
        			//获取底部促销商品列表
        			updateProductListAfterInsertCity(blockId, null)
        			
        			updateADAfterInsertCity($("#curCity").text());
                })  
            }else{
            	showMsg("自动定位失败，请手动选择所在位置");
    			$reloadContainer.addClass('dsn');
    			$mainPart02.removeClass('dsn');
            }
        });
	}
	
	//保存位置信息到cookie
	function setLocationName(cityName, blockName, gbName, provinceId, cityId, blockId, gbId){
		$('#curCity').html(cityName);
		$('#curDistrict').html(blockName);
		$('#curBuilding').html(gbName);
		
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
	function isServerCover(cityName, blockName){
		$.ajax({
			  url: "../common/toUrl.do",
			  type: 'post',
			  dataType:"json",
			  async: false,
			  data:{"detailUrl" : "/dredge/qryAddressIds.json", "city": cityName, "block": blockName}, 
			  success: function(data){
				  if(data.status === '0000'){
					  
					  //校验完成，将返回的城市和区域id保存到cookie中，在预约页面中需要用到
					  //需要一并返回provinceId，否则预约页面不知道所在省份
					  $.cookie('provinceId', data.dataValue.provinceId, { path: '/' }); 
					  $.cookie('cityId', data.dataValue.cityId, { path: '/' }); 
					  $.cookie('blockId', data.dataValue.blockId, { path: '/' }); 
					  
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
	function isHasDefaultRoom(){
		$.ajax({
		    url: "../common/toUrl.do",
		    async: false,
		    data:{"detailUrl" : "/plotproperty/qryCurrGroupbuildingInfo.json", "isNeedLogin": "1"}, 
		    success: function(data){
			   if(data.status === '0000'){
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

					//获取服务分类
					updateCommunitySupplyTypeAfterInsertCity('gbId', dataValue.id);
					//获取底部促销商品列表
					updateProductListAfterInsertCity(dataValue.blockId, dataValue.id)

					//获取banner广告
					updateADAfterInsertCity($("#curCity").text());
						
			   }else{
				   //没有默认门牌，则通过定位获取用户当前位置 
				   $.getScript('https://api.map.baidu.com/api?v=2.0&ak=BF512d9c46c962e21adf4bd17340b6cc&s=1&services=&t=20170608143204', function(){
					   $.getScript('https://api.map.baidu.com/getscript?v=2.0&ak=BF512d9c46c962e21adf4bd17340b6cc&s=1&services=&t=20170608143204', function(){
							//自动定位
							autoLocation();
						});
					});
			    }
		    },  
		    error: function(){  
		  	   showMsg('网络不给力，请稍后重试');
		    }
		});
	}
	
	//广告点击统计
	$(document).on('click', '.h5-jump', function(){
		var $this = $(this);
		var advLink = $this.attr('href');
		advLink = advLink.split('?')[0];
		var advTitle = $this.attr('data-title'); 
		var advId = $this.attr('data-id'); 
		
		getNumberOfClicks(advId,advTitle,advLink,3)
	})
	
});