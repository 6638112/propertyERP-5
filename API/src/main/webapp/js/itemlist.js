$(function(){
	new FastClick(document.body);
	
    //lazyload
    $("img.bgloading").lazyload(
    	{effect: "fadeIn"}
    );
    
	//购物车动画
	var $shoppingCart = $('.shopping-cart-index');
	var $itemNum = $('.item-num');
	var itemNumText = $itemNum.text()*1;
	$('.join-btn').click(function(event){
		var $itemListImg = $(this).parents('.item-list-small').find('.item-list-img');
		var $itemListImgPic = $itemListImg.find('img').clone().css({width:'40px', height:'40px'});
		var itemListImgPicWidth = $itemListImgPic.width();
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop;
		
		var offset = $('.shopping-cart-index').offset();
		var $flyer = $itemListImgPic;
		
		$itemNum.removeClass('dsn');
		
		$flyer.addClass('item-flyer');
		$flyer.fly({
			start: {
				left: event.pageX - 50,
				top: event.pageY - scrollt -60
			},
			end: {
				left: offset.left + 20,
				top: offset.top + 20 - scrollt,
				width: 0,
				height: 0
			}
		});
		setTimeout(function(){
			$flyer.remove();
		},1000);
		
		itemNumText += 1;
		$itemNum.text(itemNumText).addClass('animated pulse');
		setTimeout(function(){
			$itemNum.removeClass('animated pulse')
		}, 500);
		
	});
	
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    
	//不再调用该接口，接口获取不到版本时默认返回信息为50106
	/*$.ajax({
		url:"/API/performPro/getVersion.json",
		success:function(data){
			if(data.status === '0000'){
				if(data.dataValue && data.dataValue <= 50007 && isAndroid){
					$('#shoppingCart').addClass('dsn');
				}
			}
		}
	});*/
	
	//安卓
	if(isAndroid){
		//商品详情按钮
		$('.single-item-btn').click(function(){
			var itemId = $(this).attr('data-id');
			window.itemList.singleItemBtn(itemId);
		});
		
		//加入购物车按钮
		$('.join-shoppingCart-btn').click(function(){
			var itemId = $(this).attr('data-id');
			window.itemList.joinShoppingCartBtn(itemId);
		});
	}
	
	//ios
	if(isiOS){
		//商品详情按钮		
		$('.single-item-btn').click(function(){
			var itemId = $(this).attr('data-id');
			document.location="jfq://"+"singleItemBtn"+":/"+itemId;
		});
		
		//加入购物车按钮		
		$('.join-shoppingCart-btn').click(function(){
			var itemId = $(this).attr('data-id');
			document.location="jfq://"+"joinShoppingCart"+":/"+itemId;
		});
	}
	
	//购物车跳转
	$("#shoppingCart").click(function(){
		
		if(isAndroid){
			window.itemDetails.shoppingCartBtn('param');
		}
		if(isiOS){
			//OpenAppBySchema("jiefangqu://jiefangqu.com?params={'type':'1','controll':'ShopCarOrderingViewController'}");

			setupWebViewJavascriptBridge(function(bridge) {
				bridge.callHandler('clientDefineAction', {'type':'1','controll':'ShopCarOrderingViewController'}, function(response) {});
			});
		}
		
	})

});

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
