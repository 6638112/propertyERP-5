<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>解放区App下载</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/webapp.common.css?v20170524">
	</head>
	<body onload="autoJump();">
	<section class="sectionBox pos-relative minheight100">
		<div id="tipsBox" class="pos-absolute heightp100 minheightp100 zindex9999 tips-bg" style="display: none;"><img src="${pageContext.request.contextPath}/images/tips-info.png"/></div>
		<section class="sectionBox">
			<div class="lineheight0"><img src="${pageContext.request.contextPath}/images/download_banner.png" /></div>
		    <div class="list-box mtop40 p00">
		        <div class="item-standard-name f14 displaybox t-center">
		        	<div id="btnAndroid" class="boxflex01" onclick="androidDown();">
	        			<img style="width:32px;" src="${pageContext.request.contextPath}/images/download_android.png" />
	        			<div class="black">Android<br>下载</div>
		        	</div>
		        	<div class="boxflex01 borderleft" onclick="iosDown();">
	        			<img style="width:27px;" src="${pageContext.request.contextPath}/images/download_ios.png" />
	        			<div class="black">App Store<br>下载</div>
		        	</div>
		        </div>
		    </div>
		    <section class="divide-box10 pb48 bgwhite"></section>
		    <ul class="bottom-menu-box displaybox t-center height48 bgwhite">
		        <li class="p00 boxflex01 t-center f12 lineheight140">Copyright © 2014 深圳前海邻里乐科技服务有限公司<br>All rights reserved.</li>
		    </ul>
		</section>
	</section>
		<script>
		//判断安卓ios系统
		var u = navigator.userAgent, app = navigator.appVersion;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

			function androidDown(){
				if (isWeiXin()) {
					//window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
				}else{
					<%
					if(request.getAttribute("androidPath")!=null){
						%>window.location="<%=request.getAttribute("androidPath")%>";<%
					}
					%>
				}
			}
			function iosDown(){
				if (isWeiXin()) {
					window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
				}else{
					<%
					if(request.getAttribute("iosPath")!=null){
						%>window.location="<%=request.getAttribute("iosPath")%>";<%
					}
					%>
				}
			}
			function autoJump(){
				if (isWeiXin()) {
					if(isAndroid){
						var tipsBox = document.getElementById("tipsBox");
						tipsBox.style.display = "block";
						
						tipsBox.onclick = function(){
							this.style.display = "none";
						}
						
						document.getElementById("btnAndroid").onclick = function(){
							tipsBox.style.display = "block";
						}
					}else{
						setTimeout(function(){
							window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
						},2000);
					}
				//在浏览器里自动下载
				}else{
					if(isiOS){
						setTimeout(function(){
							window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
						},2000);
					}else{
						setTimeout(function(){
							//window.location="<%=request.getAttribute("androidPath")%>";
						},2000);
					}
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
		<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
	</body>
</html>