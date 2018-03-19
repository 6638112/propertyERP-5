<%@page import="com.cnfantasia.jfq.share.constant.ShareConstant"%>
<%@page import="com.cnfantasia.jfq.share.entity.ShareActiveEntity"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.cnfantasia.server.business.pub.utils.CookieUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.cnfantasia.pub.constant.CookieConstant"%>
<%@page import="com.cnfantasia.pub.util.WeChatConfig" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<htmlang = "zh-cn">
<head>
<meta charset = "utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0; user-scalable=no">
<title>解放区</title>
<link href="css/main.css?v0621" rel="stylesheet" type="text/css">
<link href="css/falling.css?V0621" rel="stylesheet" type="text/css">
</head>
<body>
<section class="main-box">
    <!-- <div id="fallingContainer"></div> -->
    <img class="main-box-bg" src="images/greyBar.png" />
    <section class="part01">
        <ul class="disbox section01">
            <li class="disbox-a01">
            <div class="head-img">
            	<c:choose>
            		<c:when test="${fn:contains(user.imgprofile, 'null') }">
		            	<img src="images/defaultHeadImg-new.png" /> 
            		</c:when>
            		<c:otherwise>
            			<img src="${user.imgprofile}" />
            		</c:otherwise>
            	</c:choose>            	
            </div></li>
            <li class="disbox-a02"><div class="text01">我是<span class='blue'>${user.nickName}</span>：解放区，全球首款物业费打折神器送粮票啦，我得了 <span class="red">${cash }元</span> 粮票哦~快来下载解放区沾沾喜气吧</div></li>
        </ul>
		<figure class="pleftright10" style="line-height: 0;">
	        <img class="wp100" src="images/main_01.jpg" />
	    </figure>
	    <figure class="pleftright10">
	    	<ul class="dl-btn">
	            <li><a id="btnApple" href="https://itunes.apple.com/cn/app/jie-fang-qu/id903769677?mt=8&from='sm'"><div><img src="images/main_02.jpg" /></div></a></li>
	            <li><div class="line"></div><a id="btnAndroid" href="http://www.jiefangqu.com/download/HynLiving_3.1.5_HYN.apk"><div><img src="images/main_03.jpg" /></div></a></li>
	        </ul>
	    </figure>
</section>
</section>
<section class="foot pt0">
    <div id="erweima" class="text02"><img src="images/erweima3.jpg?v0615" /></div>
    <div class="foot-txt grey">深圳市前海邻里乐科技服务有限公司版权所有</div>
</section>
</body>
<!-- <script type="text/javascript" src="js/falling.js"></script> -->
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script>
function isWeiXin(){ 
	var ua = window.navigator.userAgent.toLowerCase(); 
	if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
			return true; 
		}else{ 
			return false; 
	} 
}
if(isWeiXin()){ 
	document.getElementById("btnAndroid").href="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
	document.getElementById("btnApple").href="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
} 
</script>
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