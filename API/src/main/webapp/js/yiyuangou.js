//商品数量增减
$(function(){

		//设置预览图片为原始尺寸
	$('.my-gallery figure').each(function(){
		var $this = $(this);
		var $swiperImg = $(this).find('img');
		var thisSrc = $swiperImg.attr('src');
		getImageWidth(thisSrc,function(swiperImgWidth,swiperImgHeight){
			$this.find('a').attr('data-size', swiperImgWidth + 'x' + swiperImgHeight);
		});
		
	});

	//获取原始图片尺寸
	function getImageWidth(url,callback){
		var img = new Image();
		img.src = url;
		
		// 如果图片被缓存，则直接返回缓存数据
		if(img.complete){
			callback(img.width, img.height);
		}else{
			// 完全加载完毕的事件
			img.onload = function(){
				callback(img.width, img.height);
			}
		}
	}
	
	
	var t_img; // 定时器
	var isLoad = true; // 控制变量
	
	// 判断图片加载状况，加载完成后回调
	isImgLoad(function(){
	    // 加载完成，设置轮播图片尺寸
		$('.my-gallery figure').each(function(){
			var thisImgHeight = Math.round($(this).find('img').height());
			var thisImgWidth = Math.round($(this).find('img').width());
			var thisImgScale = thisImgWidth/thisImgHeight;
			
			var thisBoxHeight = Math.round($(this).height());
			var thisBoxWidth = Math.round($(this).width());
			var thisBoxScale = thisBoxWidth/thisBoxHeight;
			
			//宽度小于外框
			if(thisImgScale < thisBoxScale){
				$(this).find('img').width(thisBoxWidth);
				var curImgHeight = Math.round(thisBoxWidth/thisImgScale);
				$(this).find('img').height(curImgHeight);
				$(this).find('img').css('margin-top',(thisBoxHeight - curImgHeight)/2);
			//高度小于外框
			}else if(thisImgScale > thisBoxScale){
				$(this).find('img').height(thisBoxHeight);
				var curImgWidth = Math.round(thisBoxHeight*thisImgScale);
				$(this).find('img').width(curImgWidth);
				$(this).find('img').css('margin-left',(thisBoxWidth - curImgWidth)/2);
			}
		});
	});
	
	// 判断图片加载的函数
	function isImgLoad(callback){
	    // 注意我的图片类名都是cover，因为我只需要处理cover。其它图片可以不管。
	    // 查找所有封面图，迭代处理
	    $('.my-gallery figure').each(function(){
	    	var thisImgHeight = $(this).find('img').height();
	        // 找到为0就将isLoad设为false，并退出each
	        if(thisImgHeight === 0){
	            isLoad = false;
	            return false;
	        }
	    });
	    // 为true，没有发现为0的。加载完毕
	    if(isLoad){
	        clearTimeout(t_img); // 清除定时器
	        // 回调函数
	        callback();
	    // 为false，因为找到了没有加载完成的图，将调用定时器递归
	    }else{
	        isLoad = true;
	        t_img = setTimeout(function(){
	            isImgLoad(callback); // 递归扫描
	        },500); // 我这里设置的是500毫秒就扫描一次，可以自己调整
	    }
	}
	
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	//安卓
	if(isAndroid){
		//跳转商品详情
		$("#jumpToSinglePage").click(function(){
			var itemInfo = {
				itemId: $('#itemOnsell').attr('data-itemid'),
				zitiAddr: $('#zitidian').text()
			};
			window.itemSelect.jumpToSinglePage(JSON.stringify(itemInfo));
		});
		//立即抢购按钮
		$("#buyNowBtn").click(function(){
			window.itemSelect.itemBuyingNow(JSON.stringify(itemInfo));
		});
	}
	
	//ios
	if(isiOS){
		setupWebViewJavascriptBridge(function(bridge) {
			//跳转商品详情
			$("#jumpToSinglePage").click(function(){
				var itemInfo = {
					itemId: $('#itemOnsell').attr('data-itemid'),
					zitiAddr: $('#zitidian').text()
				};
				bridge.callHandler('jumpToSinglePage', itemInfo, function(response) {});
			});
			//立即抢购按钮
			$("#buyNowBtn").click(function(){
				bridge.callHandler('itemBuyingNow', itemInfo, function(response) {});
			});
		});
	}
	
	var itemInfo = {
		itemId: $('#itemOnsell').attr('data-id'),
		zitiAddr: $('#zitidian').text()
	}
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