<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">
<title>空调开机清洗季</title>
<link rel="stylesheet" href="css/shopping.common.css">
</head>
<body>
<section class="sectionBox f14 pos-relative">
    <div><img class="disblock" src="images/serverInfo01/main_01.jpg"/></div>
    <div><img class="disblock" src="images/serverInfo01/main_02.jpg"/></div>
    <div><img class="disblock" src="images/serverInfo01/main_03.jpg"/></div>
    <div><img class="disblock" src="images/serverInfo01/main_04.jpg"/></div>
    <div><img class="disblock" src="images/serverInfo01/main_05.jpg"/></div>
    <div><img class="disblock" src="images/serverInfo01/main_06.jpg"/></div>
    <div><img class="disblock" src="images/serverInfo01/main_07.jpg"/></div>
    <div><img class="disblock" src="images/serverInfo01/main_08.jpg"/></div>
    <div id="orderNowBtnWx" style="display: none;"><a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx766bad973e68e42e&redirect_uri=http%3a%2f%2fwww.jiefangqu.com%2fLA%2fdredge%2fmaintainTypelist.do%3fname%3d%E6%B8%85%E6%B4%97%26parentTypeId%3d113%26forSale%3dtrue&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect"><img class="disblock" src="images/serverInfo01/main_09.jpg"/></a></div>
    <div id="orderNowBtnApp" class="server-big" style="display: none;" data-id="113" data-name="家电清洗" data-source="首页banner"><img class="disblock" src="images/serverInfo01/main_09.jpg"/></div>
    <div><img class="disblock" src="images/serverInfo01/main_10.jpg"/></div>
</section>
<script type="text/javascript">
	if(location.search && location.search.indexOf('fromwx') > -1){
		document.getElementById("orderNowBtnWx").style.display = "block";
	}else{
		document.getElementById("orderNowBtnApp").style.display = "block";
	}
	
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

    var serverInfo = {};
	var serverBig = document.querySelectorAll('.server-big');
	
	//安卓
	if(isAndroid){
		for(var i = 0; i < serverBig.length; i++){
			serverBig[i].onclick = function(){
				serverInfo.id = this.getAttribute('data-id');
				serverInfo.name = this.getAttribute('data-name');
				serverInfo.source = this.getAttribute('data-source');
				window.homeServer.serverSelected(JSON.stringify(serverInfo));
			}
		}
	}
	
	//ios
	if(isiOS){
		setupWebViewJavascriptBridge(function(bridge) {
			for(var i = 0; i < serverBig.length; i++){
				serverBig[i].onclick = function(){
					serverInfo.id = this.getAttribute('data-id');
					serverInfo.name = this.getAttribute('data-name');
					serverInfo.source = this.getAttribute('data-source');
					bridge.callHandler('serverSelected', serverInfo, function(response) {});
				}
			}
		});
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
	
</script>
</body>
</html>