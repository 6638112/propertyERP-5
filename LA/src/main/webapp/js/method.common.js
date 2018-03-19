//判断安卓ios系统
var u = navigator.userAgent, app = navigator.appVersion;
var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

var isWeixin = location.search.indexOf('weixin') > -1;
//var appShareFuncNew = appShareFn();

//app分享交互方法
function appShareFn(newOptions){
    var appShare = {
    	options: {},
		//ios
		iosShare: function(){
			var me = this;
			//document.location='jfq://' + 'shareToFriend' + '/' + me.options.shareUrl + ',' + me.options.shareImg + ',' + me.options.shareCycleTitle + ',' + me.options.shareFriendTitle + ',' + me.options.shareDesc;
			setupWebViewJavascriptBridge(function(bridge) {
				bridge.callHandler('shareToFriendServer', me.options, function(response) {});
			});
		},
	    //安卓
		androidShareNew: function(){
			var me = this;
			window.homeServer.shareToFriendServer(JSON.stringify(me.options));
		}
	}
    
    return function newAppShareFn(){
    	var newAppShare = Object.create(appShare);
    	newAppShare.options = $.extend({}, newAppShare.options, newOptions);
    	return newAppShare;
    }
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

function getStrParam(string,name) {
	//构造一个含有目标参数的正则表达式对象  
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	//匹配目标参数  
	var r = string.match(reg);
	//返回参数值  
	if (r != null)
		return unescape(r[2]);
	return null;
}

function getCookie(name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg)){
		return unescape(arr[2]);
	}else{
		return null;
	}
}

function showMsg(txt){
	$(".pop-tips-text").text(txt);
	if($(".pop-tips").is(':visible')){
		return;
	}
	$(".pop-tips").fadeIn();
	setTimeout(function(){
		$(".pop-tips").fadeOut();
	}, 2000)
}

//获取slide宽度
function setSlideWidth(obj){
	var totalWidth = $(obj).find('li').width()*$(obj).find('li').length;
	$(obj).width(totalWidth);
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

/*url scheme 跳转app*************************************************************/
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
