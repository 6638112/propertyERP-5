<%@page import="com.cnfantasia.jfq.share.constant.ShareConstant"%>
<%@page import="com.cnfantasia.pub.constant.CookieConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.cnfantasia.server.business.pub.utils.CookieUtil" %> 
<%@ page import="com.cnfantasia.pub.util.WeChatConfig" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>意外惊喜</title>
<base href="." target="_blank">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0; user-scalable=no">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/amazing/css/falling.css?v0506">

</head>
<body>
<div id="container"> 
    <div class="no-gift">
    	<div class="no-gift-txt "><img src="<%=request.getContextPath()%>/amazing/images/cryman.png" /></div>
        <div class="erweima"><img src="<%=request.getContextPath()%>/amazing/images/erweima2.jpg" /></div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/amazing/js/jquery-1.11.2.min.js"></script>
<script src="<%=request.getContextPath()%>/amazing/js/xback.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
	window.onload = function(){
		$('body, #container').height($('.no-gift').height() + 40 +'px');
	};
	
	$(function(){
		XBack.listen(function(){
			window.location = 'http://www.jiefangqu.com/';
		});
	});
</script>
</body>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?10613c5cec2e7e169835579b1c82fd77";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();


var shareTitle = '<%=ShareConstant.shareTitle%>';
var shareUrl = '<%=request.getSession().getAttribute(CookieConstant.ShareUrl)%>';

wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: '<%=com.cnfantasia.pub.util.WeChatConfig.APPID%>', // 必填，公众号的唯一标识
    timestamp: '${signInfo.timestamp}', // 必填，生成签名的时间戳
    nonceStr: '${signInfo.nonceStr}', // 必填，生成签名的随机串
    signature: '${signInfo.signature}',// 必填，签名，见附录1
    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});

wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
    
	//分享朋友圈
	wx.onMenuShareTimeline({
	    title: shareTitle, // 分享标题
	    link: shareUrl, // 分享链接
	    imgUrl: '<%=ShareConstant.shareIcon%>', // 分享图标
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	
	//发送给朋友
	wx.onMenuShareAppMessage({
	    title: shareTitle, // 分享标题
	    desc: '<%=ShareConstant.getShareDesc()%>', // 分享描述
	    link: shareUrl, // 分享链接
	    imgUrl: '<%=ShareConstant.shareIcon%>', // 分享图标
	    type: 'link', // 分享类型,music、video或link，不填默认为link
	    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
});

</script>
</html>