<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>解放区App下载</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/appDownload/css/webapp.common.css">
	</head>
	<body onload="autoJump();">
		<section class="sectionBox">
			<div class="lineheight0"><img src="${pageContext.request.contextPath}/appDownload/images/download_banner.png" /></div>
		    <div class="list-box mtop40 p00">
		        <div class="item-standard-name f14 displaybox t-center">
		        	<div class="boxflex01" onclick="androidDown();">
	        			<img class="wp30" src="${pageContext.request.contextPath}/appDownload/images/download_android.png" />
	        			<div class="black">Android<br>下载</div>
		        	</div>
		        	<div class="boxflex01 borderleft" onclick="iosDown();">
	        			<img class="wp30" src="${pageContext.request.contextPath}/appDownload/images/download_ios.png" />
	        			<div class="black">App Store<br>下载</div>
		        	</div>
		        </div>
		    </div>
		    <section class="divide-box10 pb48 bgwhite"></section>
		    <ul class="bottom-menu-box displaybox t-center height48 bgwhite">
		        <li class="p00 boxflex01 t-center f12 lineheight140">Copyright © 2014 深圳前海邻里乐科技服务有限公司<br>All rights reserved.</li>
		    </ul>
		</section>
		<script>
			function androidDown(){
				if (isWeiXin()) {
					window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
				}else{
					window.location="http://www.jiefangqu.com/download/HynLiving_3.2.0_HYN.apk";
				}
			}
			function iosDown(){
				if (isWeiXin()) {
					window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
				}else{
					window.location="https://itunes.apple.com/cn/app/jie-fang-qu/id903769677?mt=8";
				}
			}
			function autoJump(){
				if (isWeiXin()) {
					window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
				}else{
					window.location="http://www.jiefangqu.com/download/HynLiving_3.2.0_HYN.apk";
				}
			}
		
			function isWeiXin(){ 
				var ua = window.navigator.userAgent.toLowerCase(); 
				if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
					return true; 
				}else{ 
					return false; 
				} 
			} 
		
		</script>
	</body>
</html>