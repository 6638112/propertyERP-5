<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">
<script src="../commonjs/pace.min.js"></script>
<link type="text/css" rel="stylesheet" href="../commoncss/pace-theme-flash.css">
<title>到家服务</title>
<style type="text/css">
section { display: block; }
html { width:100%; height:100%; min-height: 100%; font-family: sans-serif; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100% }
body { position: relative; margin: 0; height:100%; min-height: 100%; width: 100%; background: #fff; color: #232736; font: 12px/180% "Microsoft Yahei", sans-serif; -webkit-tap-highlight-color:rgba(0,0,0,0);/*去掉点击蓝色焦点*/ }
a { background: transparent }
a:focus { outline: thin dotted }
a:active, a:hover { outline: 0 }

img { border: 0; width:100%; }
ul, li { list-style: none; }
div, ul, li, br { margin: 0; padding: 0 }
br{ line-height: 0;}
a { text-decoration: none; color: #232736; cursor: pointer }
a:focus { outline: none; }
img { max-width: 100% }
.align-top{ vertical-align:top;}
.disblock { display: block !important; }
.pos-relative { position: relative; }
.t-center { text-align: center; }
.p010 { padding: 0 10px !important; }
.pb20 { padding-bottom: 20px !important }
.m010{ margin:0 10px;}
.m015{ margin:0 15px;}
.m20 { margin: 20px }
.mtop0 { margin:0 auto !important; }
.mtop2 { margin-top: -2px; }
.mtop3 { margin-top: -3px; }
.mtop5 { margin-top: 5px !important; }
.mtop10 { margin-top: 10px !important; }
.mtop15 { margin-top: 15px; }
.mtop20 { margin-top: 20px; }
.f12 { font-size: 12px !important; }
.f13 { font-size: 13px !important; }
.f14 { font-size: 14px !important; }
.f15 { font-size: 15px !important; }
.f16 { font-size: 16px !important; }
.red { color: #e4393c !important; }
.grey{ color:#8e8e93 !important;}
.bold{ font-weight: bold;}
.divide-box{ margin: 0 auto; height:10px; background: #ebebf3;}
.borderbottomgrey{ border-bottom: 1px solid #ecebef; border-width:0 0 1px 0; border-image:url(images/border.gif) 2 0 stretch;}
.bordertbgrey{ border-top:1px solid #ecebef; border-bottom: 1px solid #ecebef; border-width:1px 0 1px 0; border-image:url(images/border.gif) 2 0 stretch;}

.displaybox{/*老规范*/
			display:-moz-box; 
			display:-webkit-box;
			display:box;
	   -moz-box-align: center;
  	-webkit-box-align: center;
  	 		box-align: center; 
  	 		/*新规范*/
			display:-moz-flex;
			display:-webkit-flex;
			display:flex;
	   -moz-align-items: center;
	-webkit-align-items: center;
	        align-items: center; 
	       }
.boxflex01{ -moz-box-flex:1; -webkit-box-flex:1; box-flex:1;/*老规范*/ -moz-flex:1; -webkit-flex:1; flex:1;/*新规范*/ display: block;}

/* 焦点图 */
.slideBox { position: relative; overflow: hidden; margin: 10px auto 0; background: #fff; max-width: 640px;/* 设置焦点图最大宽度 */ }
.slideBox .hd { position: absolute; height: 28px; line-height: 28px; bottom: 0; left:50%; margin-left:-24px; z-index: 1; }
.slideBox .hd li { display: inline-block; width: 6px; height: 6px; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; background: #e6e6e6; text-indent: -9999px; overflow: hidden; margin: 0 3px; }
.slideBox .hd li.on { background: #d83926; }
.slideBox .bd { position: relative; z-index: 0;}
.slideBox .bd li { position: relative; text-align: center; }
.slideBox .bd li img { vertical-align: top; width: 100%;}
.slideBox .bd li a { -webkit-tap-highlight-color: rgba(0,0,0,0); font-size: 14px } /* 去掉链接触摸高亮 */

.sectionBox { margin: 0 auto; background: #fff; overflow:hidden;}

.wp80{ width: 76%;}
.bgwhite{ background:#fff !important;}
.list-box{ padding:10px 15px; overflow:hidden;}
.opacity0{ opacity:0;}

 @media only screen and (min-width:640px) {
.sectionBox, .divide-box { width: 640px; }
}
 @media only screen and (max-width:639px) {
.sectionBox, .divide-box { width: 100%; }
}
.p010{ padding:0 10px;}
.mtop0{ margin-top:0;}
.mtop30{ margin-top:30px;}

.bottom-menu-box{ position:fixed; width:inherit; bottom:0; margin:0 auto; background:-webkit-gradient(linear, 0 0, 0 100%, from(#fdfdfd), to(#e2e1e4)); z-index:999;}
.bottom-menu-box li{ line-height: normal;}
.btn-submit{ width:100%; color: #fff; text-align:center; border-radius: 4px; height: 48px; line-height: 48px; font-size: 16px; border:0; outline:none;}
.bgred { background: #da3d31; }
.noradius{ border-radius:0; height:58px;}

.maintain-menus{ overflow: hidden;}
.maintain-menus li{float: left; width: 25%; line-height: 28px; padding: 19px 0 16px; font-size: 14px; overflow: hidden; border-bottom: 1px solid #ecebef; border-width:0 0 1px 0; border-image:url(images/border.gif) 2 0 stretch;}
.maintain-menus li > div{ margin-top: 5px;}
.maintain-menus li a{ display: block;}
.maintain-menus li img{ width: auto; height: 26px; margin: 0 auto; display: block;}
.lineheightnormal{ line-height: 180% !important;}

.title-text{ z-index: 2;}
.title-line{ position: absolute; top: 0; left: 15%; width: 70%; height: 8px; z-index: 1;}

.w24{ width: 20px}
.align-middle{ vertical-align:middle;}

a[href*="cnzz"]{ display: none;}
</style>
</head>
<body>
<div id="container" style=" transform: translate(0px, -80px); -webkit-transform: translate(0px, -80px);">
	<div style="font-size: 14px; color: #232736; text-align: center; height: 80px; line-height: 80px;">
		<div id="reloadIcon" style="width: 14px; height: 27px; display: inline-block; vertical-align: middle; background: url(images/reload.png); background-size: auto 27px; background-position: 0 0;"></div>
		<span id="reloadText">下拉刷新</span>
	</div>
	<div class="main-part01">  
		<section id="slideBox" class="slideBox mtop0" style="height: 0;">
		    <div id="slidePic" class="bd">
		        <ul id="slideLi">
		        </ul>
		    </div>
		    <div id="slideDot" class="hd">
		        <ul></ul>
		    </div>
		</section>
		<section id="topBg" class="sectionBox" style="background: #c3503b; display: none;"></section>
		<section class="sectionBox pos-relative">
			<ul class="maintain-menus m010 t-center">
				<!-- <li class="server-big" data-id="111" data-name="维修"><img src="images/icon_appliance_repair.png"/><div>维修</div></li>
				<li class="server-big" data-id="113" data-name="清洗"><img src="images/icon_appliance_cleaning.png"/><div>清洗</div></li>-->
			</ul>
		</section>
		
		<section id="szSaleInfo" class="sectionBox pos-relative pb20" style="margin-top: -1px;">
			<section class="divide-box"></section>
			<div class="m20 f16 t-center"><img class="w24 mtop3 align-middle" src="images/sale_title_left.png" /><span class="m015">服务保障</span><img class="w24 mtop3 align-middle" src="images/sale_title_right.png" /></div>
			<ul class="t-center displaybox f14 lineheightnormal">
				<li class="boxflex01 server-hot" data-id="115" data-name="家政保洁" data-source="特惠精选">
					<img src="images/img_fangxinkekao.jpg" />
					<br><span class="bold">放心可靠</span>
					<br><span class="grey">社区物业合作</span>
					<div class="mtop2 grey"><span>平台收管款项</span></div>
				</li>
				<li class="boxflex01 server-hot" data-id="113" data-name="家电清洗" data-source="特惠精选" style="margin: 0 2px;">
					<img src="images/img_fuwuzhuanye.jpg" />
					<br><span class="bold">服务专业</span>
					<br><span class="grey">技能培训上岗</span>
					<div class="mtop2 grey"><span>操作流程规范</span></div>
					<!-- <a href="serviceProcess.html?dredgeTypeId=4&subTypeId=">
						下一期用这个，即一键预约，要传入是否显示数量、是否要显示自选耗材、维修价格，维修名称信息
					</a> -->
				</li>
				<li class="boxflex01 server-hot" data-id="113" data-name="家电清洗" data-source="特惠精选">
					<img src="images/img_jianguandaowei.jpg" />
					<br><span class="bold">监管到位</span>
					<br><span class="grey">人员身份认证</span>
					<div class="mtop2 grey"><span>全程追踪监督</span></div>
					<!-- <a href="serviceProcess.html?dredgeTypeId=3&subTypeId=1001">
					</a> -->
				</li>
			</ul>
			<!-- <ul class="t-center displaybox f14 lineheightnormal mtop20">
				<li class="boxflex01 server-hot server-big" data-id="115" data-name="家政">
					<img src="images/sale_icon_zdg.jpg" /><br>钟点工保洁<br><span class="red">35元</span> <span class="grey"><span class="f12 align-top">|</span> 2小时</span>
				</li>
				<li class="boxflex01 server-hot server-big" data-id="111" data-name="维修" style="margin: 0 2px;">
					<img src="images/sale_icon_wx.jpg" /><br>热水器维修<br><span class="red">50元</span> <span class="grey">起</span>
				</li>
				<li class="boxflex01 opacity0">维修列表</li>
			</ul> -->
		</section>
	</div>
</div>
<script src="../commonjs/jquery-1.11.2.min.js"></script>
<script src="js/TouchSlide.1.1.js"></script>
<script src="js/pulldownReload.js"></script>
<script>
$(function(){
	var bannerData,typeData;

    slide("#container", 80, function (e) {

        var that = this;

        setTimeout(function () {

            that.back.call();

        }, 2000);

    });
    
	//精选列表图片尺寸设置
	var serverHot = document.querySelectorAll('.server-hot');
	var serverHotWidth = serverHot[0].offsetWidth;
	var serverHotHeight = serverHotWidth*210/248;
	
	for(var j = 0; j < serverHot.length; j++){
		var serverHotImg = serverHot[j].getElementsByTagName('img');
		serverHotImg[0].style.width = serverHotWidth + 'px';
		serverHotImg[0].style.height = serverHotHeight + 'px';
	}
    
	updateDredgeType();
	updateBanner();
})
	
	//更新维修类型
	function updateDredgeType(){
		var $ul = $("ul.maintain-menus");
    	$.ajax({
    		  url: "../dredge/qryCommunitySupplyType.json",
			  dataType:"json",
			  async:false,
			  success: function(data){
				  if(data.status!="0000"){
					  alert(data.message);
				  }else{
					  if(data.dataValue.dredgeList != null){
						  var li_item = "";
						  $.each(data.dataValue.dredgeList, function(i,item){
							  li_item += '<li class="server-big" data-id="'+ item.id + '" data-name="'+ item.name +'"' + ' data-source="分类列表"><img src="' + item.picUrl + '"/><div>' + item.name + '</div></li>';
						  });  
						 $ul.html(li_item);
						 typeData = data.dataValue.dredgeList;
					  }
				  }
			  },
			  error: function(){  
	               	alert('网络不给力，请稍后重试'); 
	          }
    	});
	}
	
	//更新城市后更新广告图
    function updateBanner(){
    	var $ul = $("#slideBox ul");
    	$.ajax({
			  url: "../homeService/qryAds.json",
			  dataType:"json",
			  data:{"code":"WX", "city":"深圳"},
			  success: function(data){
				  if(data.status!="0000"){
					  alert(data.message);
				  }else{
					  if(data.dataValue.list != null){
						  var li_item = "";
						  $.each(data.dataValue.list, function(i,item){
							  var curUrl = item.picUrl;
							  var newImgUrl = curUrl + (curUrl.indexOf('?') > -1 ? '&':'?') + 'x-oss-process=image/resize,m_fill,w_640,h_299/format,jpg/quality,q_90/interlace,1';
								
							  if(item.type == 1 && item.linkUrl != '#') {
								  li_item += '<li><a class="app-jump" href="' + item.linkUrl+ '"><img src="' + newImgUrl + '" /></a></li>';
							  }else if(item.type == 1 && item.linkUrl == '#') {
								  li_item += '<li><a href="javascript:void(0)"><img src="' + newImgUrl + '" /></a></li>';
							  } else {
								  var i = item.linkUrl.lastIndexOf('/');
								  // item.linkUrl = http://api.linlile.com.cn:8080/API/116,  只取出116作为参数放在data-id中
								  li_item += '<li><a href="javascript:void(0)" class="server-big" ' + 'data-id="' + item.linkUrl.substring(i+1) + '" data-name="' + item.tittle + '"' + ' data-source="拦腰广告"' + '><img src="' + newImgUrl + '" /></a></li>';
							  }
						  });  
						  $ul.html(li_item);
						  
						  bannerData = data.dataValue.list;
					  }
						
					  var liLength = document.getElementById("slideLi").children.length;
					  if(liLength === 1){
						  document.getElementById("slideDot").style.display = 'none';
					  }
						
					  var windowWidth = document.getElementById("slideBox").offsetWidth;
					  var hdWidth = document.getElementById("slideDot").offsetWidth;
					  if(liLength > 0){
						  document.getElementById("slideBox").style.height = 299*windowWidth/640 + 'px';
						  document.getElementById("slideDot").style.marginLeft = -hdWidth/2 + 'px';
						  if(liLength > 1){
							  TouchSlide({ 
						          slideCell:"#slideBox",
						          titCell:"#slideDot ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
						          mainCell:"#slidePic ul", 
						          effect:"leftLoop", 
						          autoPage:true,//自动分页
						          autoPlay:true //自动播放
							  });  
						  }
					  }else if(liLength === 0){
						  if(isiOS){
    				  	      var topBgHeight = 64;
						  }else{
							  var topBgHeight = windowWidth*140/1152;
						  }
    				  	  $('#topBg').height(topBgHeight).show();
    				  }
				  }
				  

					var appJump = document.querySelectorAll('.app-jump');
					
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

						for(var i = 0; i < appJump.length; i++){
							appJump[i].onclick = function(e){
								e.preventDefault();
								var thisLink = this.href; 
								var urlScheme = 'jiefangqu://jiefangqu.com/openMainActGroup?DLJ=1&tab=3&clazz=com.jiefangqu.living.act.buy.BannerJumpWebViewAct&paramsStr=SA.string_array_url_params-' + thisLink + '+S.title-解放区活动';
								OpenAppBySchema(urlScheme);
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
						
						for(var i = 0; i < appJump.length; i++){
							appJump[i].onclick = function(e){
								e.preventDefault();
								var thisLink = this.href; 
								var urlScheme = "jiefangqu://jiefangqu.com?params={'type':'1','controll':'WebborseViewController','params':{'title':'解放区活动','webUrl':'" + thisLink + "'}}";
								OpenAppBySchema(urlScheme);
							}
						}
					}
			  },
			  error: function(){  
               	alert('网络不给力，请稍后重试'); 
             }
		});
    }
	
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
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

</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>