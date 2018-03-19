<%@page import="com.cnfantasia.server.api.pub.header.HeaderConstant"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path2 = request.getContextPath();
	String basePath2 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path2;
	String currPath = basePath2+"/signalStyle/surpriseGift/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>意外惊喜</title>
<base href="<%=currPath%>" target="_blank">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<link type="text/css" rel="stylesheet" href="css/falling.css?v=1.0.3">
</head>
<body>
<div id="container" data-path="${laPath}"> 
    <%-- <div class="gift dsn">
    	<div class="gift-txt opacity0"><span class="orange t01">哇塞！意外惊喜！</span><br/>恭喜获得<span class="orange t02">${hbAmount}元消费券</span>！</div>
    	<div class="gift-img giftBtn"><span class="gift-num dsn">￥${hbAmount}</span><div class="pic"><img class="stars dsn" src="images/stars.png" /><img class="gf" src="images/gift.png" /><img class="lightbg animated0s infinite rotateOut01 dsn" src="images/lightbg.png" /></div></div>
    	<div class="gift-btn dsn"><input class="giftBtn" type="button" id="claimButton" value="领取现金" /></div><!-- onclick="claimMoney();" -->
    </div> --%>
     <div class="gift dsn">
    	<div class="gift-txt opacity0"><span class="orange t01">恭喜！获得额外惊喜！</span><br/><span class="t02">获得</span><span class="orange t02"><fmt:formatNumber type="number" maxFractionDigits="0" value="${hbAmount}" />元现金粮票</span></div>
    	<div class="gift-img giftBtn"><span class="gift-num dsn">￥<fmt:formatNumber type="number" maxFractionDigits="0" value="${hbAmount}" /><br><span class="f01">解放区现金粮票</span></span><div class="pic"><img class="gf" src="images/gift.png" /><img class="lightbg animated5s infinite rotateOut01 dsn" src="images/lightbg.png" /></div></div>
    	<div class="gift-btn dsn"><input class="giftBtn" type="button" value="领取现金" onclick="claimMoney();" /></div> 
        <ul class="share-info dsn">
        	<li>获得<fmt:formatNumber type="number" maxFractionDigits="0" value="${hbAmount}" />元现金粮票<br>把好运分享到</li>
        	<li class="share-btn">
        		<a id="shareToFriend" href="javascript:void(0)" target="_blank"><img src="images/sharettofriend.png" /><br>微信好友</a>
        		<a id="shareToQuan" href="javascript:void(0)" target="_blank"><img src="images/sharettoquan.png" /><br>微信朋友圈</a>
        	</li>
        </ul>
        <div class="close-btn dsn">关闭</div>
    </div>
    
    <ul class="eggs">
    	<li class="animated1s bounceInDown"><img src="images/egg-big.png" /></li>
    	<li class="animated2s bounceInDown"><img src="images/egg-big.png" /></li>
    	<li class="animated3s bounceInDown"><img src="images/egg-big.png" /></li>
    </ul>
    <div id="fallingContainer">
    </div>
</div>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script src="js/fastclick.js"></script>
<script type="text/javascript">
	$(function(){
		new FastClick(document.body);
	});
	
</script>
<script type="text/javascript" src="js/falling.js"></script>
<script type="text/javascript" src="js/jquery.eggs.js?v=1.0.8a"></script>
<script type="text/javascript">
	function claimMoney(){
		//alert('领取现金~');
		$.ajax({
	        type: "GET",
	        url: "<%=basePath2%>/surpriseGift/markSurpriseGiftAsFetched.json?surpriseGiftId=<%=request.getAttribute("giftId")%>",
	        beforeSend: function(request) {
	            request.setRequestHeader("channelId", "1");
	            request.setRequestHeader("subChannelId", "6");
	            request.setRequestHeader("deviceId", "0");
	            request.setRequestHeader("sessionKey", "<%=request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY)%>");
	        },
	        success: function(result) {
	        	//alert(result);
	        } 
   		});
	}
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
</script>
</html>