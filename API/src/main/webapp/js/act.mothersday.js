$(function(){
	if(location.search.indexOf('hometab') > -1){
		$(".hometab").removeClass('hide');
	}
	if(location.search.indexOf('shopstab') > -1){
		$(".shopstab").removeClass('hide');
	}
	if(location.search.indexOf('homeservicetab') > -1){
		$(".homeservicetab").removeClass('hide');
	}
	
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	//安卓
	if(isAndroid){
		//商品立即购买跳转
		$(".buynow-btn").click(function(){
			var thisItemId = $(this).attr("data-id");
			jumpToCart(thisItemId, "jiefangqu://jiefangqu.com/openMainActGroup?DLJ=1&tab=2&clazz=com.jiefangqu.living.act.buy.ShopCarAct")
		});
		
		//到家服务跳转
		$(".booknow-btn").click(function(){
			var serverInfo = {};
			serverInfo.id = this.getAttribute('data-id');
			serverInfo.name = this.getAttribute('data-name');
			serverInfo.source = this.getAttribute('data-source');
//			window.homeServer.serverSelected(JSON.stringify(serverInfo));
//			OpenAppBySchema("jiefangqu://jiefangqu.com/openMainActGroup?DLJ=1&tab=3&clazz=com.jiefangqu.living.act.dredge.HomeServiceReserveTypeOneActivity&paramsStr=Parcel.Parcelable-com.jiefangqu.living.entity.dredge.HomeServiceItemEntity@[id=" + serverInfo.id + ", name=" + serverInfo.name + ", type=1]");
			OpenAppBySchema("jiefangqu://jiefangqu.com/openMainActGroup?DLJ=1&tab=3");
		});
	}
	
	//ios
	if(isiOS){
		//商品立即购买跳转
		$(".buynow-btn").click(function(){
			var thisItemId = $(this).attr("data-id");
			//ios9下wkwebview旧版本兼容，须用bridge交互
			jumpToCart(thisItemId, "jiefangqu://jiefangqu.com?params={'type':'1','controll':'ShopCarOrderingViewController'}")
		});
		
		//到家服务跳转
		//setupWebViewJavascriptBridge(function(bridge) {
		$(".booknow-btn").click(function(){
			var serverInfo = {};
			serverInfo.id = this.getAttribute('data-id');
			serverInfo.name = this.getAttribute('data-name');
			serverInfo.source = this.getAttribute('data-source');
			//bridge.callHandler('clientDefineAction', serverInfo, function(response) {});
			//ios9下wkwebview旧版本兼容，须用bridge交互
			OpenAppBySchema("jiefangqu://jiefangqu.com?params={'type':'1','controll':'SelectRepairItemController','params':{'title':'" + serverInfo.name + "','parentId':'" + serverInfo.id + "'}}");
		});
		//});
	}
		
    //lazyload
    $("img.bgloading").lazyload(
    	{effect: "fadeIn"},
    	setImgBlock
    );
});

//图片外边框有1像素空白，须设置图片为块级元素
function setImgBlock(){
	$("img.bgloading").css('display', 'block');
}

//加入购物车，然后跳转至购物车页面
function jumpToCart(thisItemId, schemaUrl){
	showMsg('请稍候');
	$.ajax({
		url:getCurHref('api') + "/API/ebuyNew/add2BuyCarBatch.json",
		data:{productList:'[{productId:"' + thisItemId + '", num:1}]'},
        beforeSend: function(xhr) {
            xhr.setRequestHeader("subChannelId", "3");
        },
		success:function(data){
			if(data.status === '0000'){
				
				//跳转订单页面
				//OpenAppBySchema('jiefangqu://jiefangqu.com/openMainActGroup?DLJ=1&tab=2&clazz=com.jiefangqu.living.act.buy.OrderPayConfirmAct&paramsStr=S.shops-[{"productId":"10266479","productQty":1}]');
				
				//跳转购物车页面
				hideMsg('正在跳转');
				setTimeout(function(){
					OpenAppBySchema(schemaUrl);
				},600);
			}
		},
		error: function(data){
			hideMsg('网络不给力，请重试');
		}
	});
}

function OpenAppBySchema(scheme) {
    var ua = navigator.userAgent.toLowerCase();    
    if (ua.indexOf('safari') > -1 && (ua.indexOf('os 8') == -1
        || ua.indexOf('os 7') == -1
        || ua.indexOf('os 6') == -1
        || ua.indexOf('os 5') == -1)) {
        var schemeLinkOpen = document.getElementById('schemeLinkOpen');
        if (!schemeLinkOpen) {
            schemeLinkOpen = document.createElement('a');
            schemeLinkOpen.id = 'schemeLinkOpen';
            schemeLinkOpen.style.display = 'none';
            document.body.appendChild(schemeLinkOpen);
        }
        schemeLinkOpen.href = scheme;
        // 执行click
        schemeLinkOpen.dispatchEvent(customClickEvent());
    }
    var iframeObj = document.createElement("iframe");
    var startTime = (new Date()).getTime();
    if (iframeObj != null) {
        iframeObj.setAttribute("style", "height:0px;width:0px;display:none;")
        iframeObj.setAttribute("src", scheme);
        document.body.appendChild(iframeObj);
        document.body.removeChild(iframeObj);
    }
}

function customClickEvent() {
    var clickEvent;
    if (window.CustomEvent) {
        clickEvent = new window.CustomEvent("click", {
            canBubble: true,
            cancelable: true
        }
        );
    } else {
        clickEvent = document.createEvent('Event');
        clickEvent.initEvent('click', true, true);
    }
    return clickEvent;
}

//判断获取当前域名
function getCurHref(project){
	var curHref = '';
	var curOrigin = location.origin;
	var curProtocol = location.protocol;
	if(curOrigin.indexOf('linlile.com.cn') > -1){
		curHref = curProtocol + '//'+project+'.linlile.com.cn';
	}else if(curOrigin.indexOf('linlile.cn') > -1){
		curHref = curProtocol + '//'+project+'.linlile.cn';
	}else if(curOrigin.indexOf('jiefangqu') > -1){
		curHref = curProtocol + '//'+project+'.jiefangqu.com';
	}else{
		curHref = curProtocol + '//localhost';
	}
	return curHref;
}
	
function setupWebViewJavascriptBridge(callback) {
    if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
    if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
    window.WVJBCallbacks = [callback];
    var WVJBIframe = document.createElement('iframe');
    WVJBIframe.style.display = 'none';
    WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
    document.documentElement.appendChild(WVJBIframe);
    setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0);
}
	
function showMsg(txt, hidesoon){
	$(".bg-wrap").removeClass('hide');
	$(".pop-tips-text").text(txt);
	$(".pop-tips").fadeIn();
	if(hidesoon === 'hidesoon'){
		setTimeout(function(){
			$(".pop-tips").fadeOut();
			$(".bg-wrap").addClass('hide');
		}, 2500)
	}
}
	
function hideMsg(txt){
	$(".pop-tips-text").text(txt);
	setTimeout(function(){
		$(".pop-tips").fadeOut();
		$(".bg-wrap").addClass('hide');
	}, 2500)
}
