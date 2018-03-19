<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path2 = request.getContextPath();
	String basePath2 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path2;
%>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta charset = "utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<meta name="description" content="解放区“师傅端”隆重登场！工作环境好：都是小区业主下单，不怕跑到山旮旯！接单够任性：接单抛弃纸和笔，一键接单应有尽有！兼容多手艺：维修清洗、管道疏通等等手艺都兼容！最重要的是，百万解放区业主用户的需求等着你！" />
<meta name="keywords" content="解放区,社区O2O,社区O2O,社区服务O2O,社区O2O平台,小区服务O2O,生活软件,生活软件,手机生活软件,生活消费手机软件,小区app,解放区小区app,小区手机app,小区应用,小区手机应用,居家服务,物业费,物业费打折,物业费减免,不交物业费,物业费免费,免物业费,0元物业费,物业费全免,物业费收取标准,网上缴/交物业费,水电煤,水电煤账单,网上缴/交水电煤,物业通知,小区物业通知,物业停水通知,投诉报修,物业投诉报修,小区周边,邻里,邻里纠纷,邻里关系,社区邻里,社区,和谐社区,邻居,街坊邻居,认识邻居,搭讪邻居,楼上楼下,认识楼上楼下,楼上楼下,认识楼上楼下邻居,业主,业主qq群,业主交流群,业主群,业主论坛,小区业主交流,业主维权,二手,二手交换/置换/交易,交换二手物品,闲置物品,闲置物品交换/交易,拼单,社区拼单,拼车,春运拼车,上下班拼车,拼车回家,菜谱,家常菜谱,家庭菜谱,家常菜谱大全,实用家常菜谱大全,家常菜谱家常菜做法,粤菜家常菜菜谱,川菜家常菜菜谱,冬季进补家常菜谱,美食,居家美食,厨房,家庭厨房,超市,私家超市,购物,家庭购物,网购,家庭网购," />
<title>解放区师傅端下载</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/webapp.common.css?v20160608">
</head>
<body>
	<section class="sectionBox pos-relative minheight100">
		<section class="sectionBox">
			<div class="lineheight0"><img src="${pageContext.request.contextPath}/images/download_banner_master.png" /></div>
		    <div class="list-box mtop40 p00">
		        <div class="item-standard-name f14 displaybox t-center">
		        	<div id="btnAndroid" class="boxflex01" onclick="androidDown();">
	        			<img style="width:32px;" src="${pageContext.request.contextPath}/images/download_android.png" />
	        			<div class="black">Android<br>下载</div>
		        	</div>
		        	<div id="btnApple" class="boxflex01 borderleft" onclick="iosDown();">
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
</body>
<script type="text/javascript">
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	function androidDown(){
		//微信里跳转到应用宝
		if(isWeiXin()){
			window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living.dredge";
		}else{
			<%
			if(request.getAttribute("androidPath")!=null){
				%>window.location="<%=request.getAttribute("androidPath")%>";<%
			}
			%>
		}
	}
	
	function iosDown(){
		//微信里跳转到应用宝
		if(isWeiXin()){
			window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living.dredge";
		}else{
			<%
			if(request.getAttribute("iosPath")!=null){
				%>window.location="<%=request.getAttribute("iosPath")%>";<%
			}
			%>
		}
	}
	
	function autoJump(){
		if (!isWeiXin()) {
			if(isiOS){
				setTimeout(function(){
					window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living.dredge";
				},2000);
			}else{
				setTimeout(function(){
					window.location="<%=request.getAttribute("androidPath")%>";
				},2000);
			}
		}
	}
	autoJump();
	
	function isWeiXin(){ 
		var ua = window.navigator.userAgent.toLowerCase(); 
		if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
			return true; 
		}else{ 
			return false; 
		} 
	} 
</script>
<script>
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F71a32d0a717889a15a9935bd0ee1c1bb' type='text/javascript'%3E%3C/script%3E"));
</script>
</html> 