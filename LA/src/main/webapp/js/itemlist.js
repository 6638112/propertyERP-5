//判断安卓ios系统
var u = navigator.userAgent, app = navigator.appVersion;
var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

$(function(){
	new FastClick(document.body);
	
    //lazyload
    $("img.bgloading").lazyload(
    	{effect: "fadeIn"}
    );
    
    var jfqUserId = null;
	//购物车动画
	var $shoppingCart = $('.shopping-cart-index');
	var $itemNum = $('.item-num');
	var itemNumText = $itemNum.text()*1;
    $(document).on('click','.join-btn',function(event){
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
    
	//微信打开
	if(location.search.indexOf('weixin') > -1){
		
		 jfqUserId = getUrlParam('userId');
		
		 //商品详情按钮
		 $(document).on('click','.single-item-btn.ebuyProductList',function(event){
			 var itemId = $(this).attr('data-id');
			 var userIdParam = jfqUserId === null ? '' : '&userId=' + jfqUserId;
			 location.href = getCurHref('www') + '/LA/product/productDetail.do?ptId=' + itemId + userIdParam;
		 });
		 
		 //服务详情按钮
		 $(document).on('click','.single-item-btn.dredgeProductList',function(event){
			 var itemId = $(this).attr('data-id');
			 location.href = getCurHref('www') + '/LA/dredge/productDetail.html?productId=' + itemId + '&jfqsource=weixin';
		 });
		 
		 //加入购物车按钮
         $(document).on('click','.join-shoppingCart-btn',function(event){
			 var itemId = $(this).attr('data-id');

			//加入购物车
			$.post("../cart/add2BuyCar.do", "ptId=" + itemId + "&productQty="+ 1).success(function(response){
				 
				 if(response.status != '0000') {
					 if(response.message.indexOf("库存不足") > -1){
						 alert("该商品库存不足");
					 }else {
						 alert(response.message);
					 }
					 return;
				 }
				 
			});
		 });

 		//购物车跳转
 	    $(document).on('click','#shoppingCart',function(event){
 			location.href = '../cart/qryBuyCar.do';
 		})
		
	//app打开	
	}else{
	
		//安卓
		if(isAndroid){
			//商品详情按钮
	        $(document).on('click','.single-item-btn.ebuyProductList',function(event){
				var itemId = $(this).attr('data-id');
				window.itemList.singleItemBtn(itemId);
			});
			
			//加入购物车按钮
	        $(document).on('click','.join-shoppingCart-btn',function(event){
				var itemId = $(this).attr('data-id');
				window.itemList.joinShoppingCartBtn(itemId);
			});
	        
	        //服务详情按钮
	        $(document).on('click','.single-item-btn.dredgeProductList',function(event){
	        	var itemId = $(this).attr('data-id');
	        	var urlScheme = 'jiefangqu://jiefangqu.com/openHomeServiceProductDetailAct?url=' + getCurHref('www') + '/LA/dredge/productDetail.html?productId=' + itemId;
				OpenAppBySchema(urlScheme);
	        });
		}

		//ios
		if(isiOS){
			//商品详情按钮
	        $(document).on('click','.single-item-btn.ebuyProductList',function(event){
				var itemId = $(this).attr('data-id');
				document.location="jfq://"+"singleItemBtn"+":/"+itemId;
			});
			
			//加入购物车按钮		
	        $(document).on('click','.join-shoppingCart-btn',function(event){
				var itemId = $(this).attr('data-id');
				document.location="jfq://"+"joinShoppingCart"+":/"+itemId;
			});
	        
	        //服务详情按钮
	        $(document).on('click','.single-item-btn.dredgeProductList',function(event){
	        	var itemId = $(this).attr('data-id');
	    		//跳服务详情只需id，webUrl可不传
	    		setupWebViewJavascriptBridge(function(bridge) {
	    			bridge.callHandler('clientDefineAction', {'type':'1','controll':'HSProductInfoViewController','params':{'productId': itemId}}, function(response) {});
	    		});
	        });
		}
		
		//购物车跳转
	    $(document).on('click','#shoppingCart',function(event){
		// $("#shoppingCart").click(function(){
			
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
	}

});

function getUrlParam(name) {
    //构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //匹配目标参数
    var r = window.location.search.substr(1).match(reg);
    //返回参数值
    if (r != null)
        return unescape(r[2]);
    return null;
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
