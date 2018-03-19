<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/head.jsp"%>
<%@page import="com.cnfantasia.server.business.pub.utils.CookieUtil"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">

<title>抽奖</title>
<link rel="stylesheet" href="css/prize.common.css?v28">
</head>

<body>
<section class="sectionBox main-info">
    <div class="prize-box">
	<% 
		String userId = (String)request.getSession().getAttribute("userId");
		String userIdAndDate = userId + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			if(CookieUtil.getCookieByName(request, userIdAndDate)!=null
					&& "1".equals(CookieUtil.getCookieByName(request, userIdAndDate).getValue())){ //已参与活动
				out.write("<div id='hasPrize' class='prize-btn'><img src='images/lingquan-btn2.png' /></div>");
			}else{//未参与活动
				out.write("<div id='getPrize' class='prize-btn'><img src='images/lingquan-btn.png' /></div>");
			}
		%>
       
        <div class="quan-btn"><a href="../prize/list.do" target="_blank"><img src="images/quan-btn.png" /></a></div>
        
    </div>
    <section class="pop-box hide">
    	<div class="step01 hide">
        	<ul class="red-packet">
	            <li class="animated1s bounceInDown"><img src="images/red-packet.png" /></li>
        		<li class="animated2s bounceInDown"><img src="images/red-packet.png" /></li>
            	<li class="animated3s bounceInDown"><img src="images/red-packet.png" /></li> 
            </ul>
        </div>
        
        <input type="hidden" id="hbAmount" value="${hbAmount}" />
      
	    	<div class="step02 pos-relative hide">
	        	<ul class="step-box">
	            	<li class="txt animated4s slideInDown"><div class="f01">恭喜！运势牛牛滴！</div><div class="f02"><span class="white">恭喜获得</span><span id="hbAmount1">${hbAmount}</span>元消费券</div></li>
	            	<li class="xiaofeiquan"><div class="f03">￥<span id="hbAmount2">${hbAmount}</span></div><div class="f04">解放区消费券</div><div class="xiaofeiquan-bg"><img src="images/xiaofeiquan.png" /></div></li>
	            	<li id="getBtn" class="animated4s slideInUp"><input class="get-btn" type="button" value="领取" /></li>
	            	<li class="step-end hide animated4s slideInUp"><input class="get-btn bgorange" type="button" value="已领，嫌不够？下载解放区拿更多" onclick="window.open('http://app.jiefangqu.com/')" /></li>
	            	<li class="step-end hide tips animated4s slideInUp"><div class="arrow"></div><input class="get-btn bggreen" type="button" value="使用微信号登录APP即可保存券" /></li>
	            </ul>
	        </div>
	   
	    	<div class="step03 pos-relative hide">
	        	<ul class="step-box">
	            	<li class="txt animated4s slideInDown"><div class="f01">好可惜阿，就差一点</div><div class="f02"><span class="white">一定是抽奖姿势不对，换个姿势明天再战！</span></div></li>
	            	<li class="xiaofeiquan"><div class="xiaofeiquan-bg"><img src="images/red-packet01.png" /></div></li>
	            	<li class="animated4s slideInUp download-btn"><input class="get-btn" type="button" value="不甘心？下载解放区1天3次抽低折" onclick="window.open('http://app.jiefangqu.com/')" /></li>
	            	<li class="tips animated4s slideInUp"><div class="arrow"></div><input class="get-btn bggreen" type="button" value="使用微信号登录APP即可保存券" /></li>
	            </ul>
	            <div class="close-btn">关闭</div>
	        </div>
      
    </section>
</section>

<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
<script src="js/prize.common.js?v0630"></script>
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