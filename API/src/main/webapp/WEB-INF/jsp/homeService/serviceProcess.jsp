<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>清洗服务流程</title>
<link rel="stylesheet" href="css/homeservice.css">
</head>
<body>
<section class="sectionBox f14 pos-relative" style="padding-bottom: 58px;">
    <div><img class="disblock" src="images/flow_desc_banner.jpg"/></div>
    <section class="mtop30 bgwhite t-center grey pos-relative">
    	<span class="title-text bgwhite p010 pos-relative">服务介绍</span>
    	<div class="title-line borderbottomgrey"></div>
    </section>
    <div class="list-box">解放区师傅都是经过身份登记，专业技能证审核，上岗测试，培训等环节，最终顺利通过才能在开始上门服务，如若业主对师傅服务有任何不满意的意见或建议，可以随时联系客服人员。</div>
    <section class="mtop15 bgwhite t-center grey pos-relative">
    	<span class="title-text bgwhite p010 pos-relative">服务流程</span>
    	<div class="title-line borderbottomgrey"></div>
    </section>
    <div class="list-box">
        <ul class="displaybox mtop10 t-center">
            <li class="boxflex01">
            	<img class="wp80" src="images/flow_steps_01.jpg"/>
            	<div class="mtop5">1.专业检验<br>制定科学清洗计划</div>
            </li>
            <li class="boxflex01">
            	<img class="wp80" src="images/flow_steps_02.jpg"/>
            	<div class="mtop5">2.家庭环境防护<br>细节突显专业</div>
            </li>
        </ul>
        <ul class="displaybox mtop30 t-center">
            <li class="boxflex01">
            	<img class="wp80" src="images/flow_steps_03.jpg"/>
            	<div class="mtop5">3.专业喷洒<br>药效更均匀</div>
            </li>
            <li class="boxflex01">
            	<img class="wp80" src="images/flow_steps_04.jpg"/>
            	<div class="mtop5">4.设备冲洗<br>去污不留死角</div>
            </li>
        </ul>
        <ul class="displaybox mtop30 t-center">
            <li class="boxflex01">
            	<img class="wp80" src="images/flow_steps_05.jpg"/>
            	<div class="mtop5">5.设备零部件<br>拆卸清洗</div>
            </li>
            <li class="boxflex01">
            	<img class="wp80" src="images/flow_steps_06.jpg"/>
            	<div class="mtop5">6.擦拭外机<br>安装检验</div>
            </li>
        </ul>
    </div>
    <section class="mtop15 bgwhite t-center grey pos-relative">
    	<span class="title-text bgwhite p010 pos-relative">服务保障</span>
    	<div class="title-line borderbottomgrey"></div>
    </section>
    <div class="list-box">客服一对一跟踪，如有不满意您可在公众号和App里联系客服，直接反馈，保证每一例不满意投诉24小时内响应，保障您的权益</div>
    <div class="divide-box bgwhite"></div>
    <ul class="bottom-menu-box displaybox t-left bordertopgrey">
    	<li id="orderNowBtn" class="p00 boxflex01"><input class="btn-submit bgred noradius btnSubmit" type="submit" name="submit" value="立即预约"></li>
    </ul>
</section>
<script type="text/javascript">
	
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	var serverInfo = {};
	
	var serverOrderBtn = document.querySelector('#orderNowBtn');
	serverOrderBtn.onclick = function(){
		serverInfo.dredgeTypeId = getUrlParam('dredgeTypeId');
		serverInfo.subTypeId = getUrlParam('subTypeId');
		console.log(serverInfo);
	}
	
	//安卓
	if(isAndroid){
		serverOrderBtn.onclick = function(){
			serverInfo.dredgeTypeId = getUrlParam('dredgeTypeId');
			serverInfo.subTypeId = getUrlParam('subTypeId');
			window.homeServer.serverOrder(JSON.stringify(serverInfo));
		}
	}
	
	//ios
	if(isiOS){
		setupWebViewJavascriptBridge(function(bridge) {
			serverOrderBtn.onclick = function(){
				serverInfo.dredgeTypeId = getUrlParam('dredgeTypeId');
				serverInfo.subTypeId = getUrlParam('subTypeId');
				bridge.callHandler('serverOrder', serverInfo, function(response) {});
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
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>