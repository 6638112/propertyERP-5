<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<title>到家服务</title>
<!--<script src="../commonjs/pace.min.js"></script>
<link type="text/css" rel="stylesheet" href="../commoncss/pace-theme-flash.css">-->
<link type="text/css" rel="stylesheet" href="${resourcePath}/commoncss/swiper.css">
<link type="text/css" rel="stylesheet" href="css/homeservice.css">
<link type="text/css" rel="stylesheet" href="css/progressive.img.css">
</head>
<body class="pos-relative">
<div id="container" class="reload-container" style=" transform: translate(0px, -80px); -webkit-transform: translate(0px, -80px);">
	<div class="reload-box">
		<div id="reloadIcon" class="reload-icon"></div>
		<span id="reloadText">下拉刷新</span>
	</div>
	<div class="main-part01">  
		<header class="sectionBox fantasia-header displaybox">
			<div id="cityOrientation" class="mleft10 boxflex01">
				<span class="mright5"><span id="curCity">正在定位…</span></span>
				<div class="get-city-arrow disinline"></div>
			</div>
			<a class="disblock p010 boxflex01 t-right" href="myAppointment.html">
				<img class="icon_appointment mright5" src="images/icon_appointment.png" />
				<span>预约订单</span>
			</a>
		</header>
		<section id="slideBox" class="slideBox mtop0" style="height: calc((190.47 / 412) * 100vw);">
		    <div id="slidePic" class="bd">
		        <ul id="slideLi">
		            <li><a class="disblock progressive app-jump" href="serviceProcess01.html"><img class="preview lazy" data-src="http://public.image.linlile.com.cn/adPicBasePath/20170412142452689.jpg?2017-04-17_16_33_12&x-oss-process=image/resize,m_fill,w_640,h_299/format,jpg/quality,q_90/interlace,1" src="http://public.image.linlile.com.cn/adPicBasePath/20170412142452689.jpg?2017-04-17_16_33_12&x-oss-process=image/resize,m_fill,w_39,h_18/format,jpg/quality,q_90/interlace,1" /></a></li>
		            <li><a class="disblock progressive" href="#"><img class="preview lazy" data-src="http://public.image.linlile.com.cn/adPicBasePath/20170412142452689.jpg?2017-04-17_16_33_12&x-oss-process=image/resize,m_fill,w_640,h_299/format,jpg/quality,q_90/interlace,1" src="http://public.image.linlile.com.cn/adPicBasePath/20170412142452689.jpg?2017-04-17_16_33_12&x-oss-process=image/resize,m_fill,w_39,h_18/format,jpg/quality,q_90/interlace,1" /></a></li>
		        </ul>
		    </div>
		    <div id="slideDot" class="hd">
		        <ul></ul>
		    </div>
		</section>
		
		<section class="sectionBox pos-relative">
			<ul class="maintain-menus displaybox m010 t-center">
				<li class="server-big boxflex01" data-id="111" data-name="维修"><img src="images/icon_appliance_repair.png"/><div class="mtop5">维修服务</div></li>
				<li class="server-big boxflex01" data-id="113" data-name="清洗"><img src="images/icon_appliance_cleaning1.png"/><div class="mtop5">清洗服务</div></li>
				<li class="server-big boxflex01" data-id="113" data-name="清洗"><img src="images/icon_anzh1.png"/><div class="mtop5">清洗服务</div></li>
				<li class="server-big boxflex01" data-id="113" data-name="清洗"><img src="images/icon_appliance_cleaning.png"/><div class="mtop5">清洗服务</div></li>
			</ul>
		</section>
		
		<section class="sectionBox pos-relative pb20 activity-wrap" style="margin-top: -1px;">
			<section class="divide-box"></section>
			<div class="m15 f16 t-center"><img class="w20 mtop3 align-middle" src="images/sale_title_left.png" /><span class="m015">限时活动</span><img class="w20 mtop3 align-middle" src="images/sale_title_right.png" /></div>
			<div class="m010">
				<div class="swiper-container">
					<ul id="swiperWrapper" class="f14 lineheightnormal activity-box swiper-wrapper">
						<li class="server-hot swiper-slide t-left">
							<a href="serviceProcess.html?dredgeTypeId=4&subTypeId=">
								<!--<div class="progressive"><img class="preview lazy" src="images/sale_icon_ktqx.jpg" data-src="images/sale_icon_ktqx.jpg" /></div>-->
								<div class="progressive server-hot-img-box" style=""><img src="images/sale_icon_ktqx.jpg" /></div>
								<div class="m010">
									<div class="mtop5 f14 word-break1">等离子电视机 换显示屏全年保修</div>
									<div class="mtop2 f12 grey word-break1">专业师傅上门维修 送全年电视机检查</div>
									<div class="f14"><span class="red">￥ 76.00</span> <span class="grey"><span class="f12 align-top">|</span> <span class="f12">床</span></span></div>
								</div>
							</a>
						</li>
						<li class="server-hot swiper-slide t-left">
							<a href="serviceProcess.html?dredgeTypeId=3&subTypeId=1001">
								<div class="progressive server-hot-img-box"><img src="images/sale_icon_cdcm.jpg" /></div>
								<div class="m010">
									<div class="mtop5 f14 word-break1">等离子电视机 换显示屏全年保修</div>
									<div class="mtop2 f12 grey word-break1">专业师傅上门维修 送全年电视机检查</div>
									<div class="f14"><span class="red">￥ 76.00</span> <span class="grey"><span class="f12 align-top">|</span> <span class="f12">床</span></span></div>
								</div>
							</a>
						</li>
						<li class="server-hot server-big swiper-slide t-left" data-id="113" data-name="清洗">
							<div>
								<div class="progressive server-hot-img-box"><img src="images/sale_icon_jzfw.jpg" /></div>
								<div class="m010">
									<div class="mtop5 f14 word-break1">等离子电视机 换显示屏全年保修</div>
									<div class="mtop2 f12 grey word-break1">专业师傅上门维修 送全年电视机检查</div>
									<div class="f14"><span class="red">￥ 76.00</span> <span class="grey"><span class="f12 align-top">|</span> <span class="f12">床</span></span></div>
								</div>
							</div>
						</li>
						<li class="server-hot server-big swiper-slide t-left" data-id="113" data-name="清洗">
							<div>
								<div class="progressive server-hot-img-box"><img src="images/sale_icon_jzfw.jpg" /></div>
								<div class="m010">
									<div class="mtop5 f14 word-break1">等离子电视机 换显示屏全年保修</div>
									<div class="mtop2 f12 grey word-break1">专业师傅上门维修 送全年电视机检查</div>
									<div class="f14"><span class="red">￥ 76.00</span> <span class="grey"><span class="f12 align-top">|</span> <span class="f12">床</span></span></div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</section>
	</div>
	
	<div class="main-part02 dsn">    
	    <section class="sectionBox item-details-info pos-relative">
	        <section class="sectionBox password-mind-box bordertbgrey">
	            <div class="mleft15 grey">请选择您所在省市</div>
	        </section>
	        <ul class="register-list">
	            <li class="borderbottomgrey">
	                <select class="input-text wp100 list-arrow" datatype="*" nullmsg="请完善上门地址！" >
	                    <option style="color: #8e8e93;"  value="">选择省份</option>
	                    <option value="1">广东省</option>
	                    <option value="2">广西省</option>
	                </select>
	            </li>
	            <li>
	                <select id="city" class="input-text wp100 list-arrow" datatype="*" nullmsg="请完善上门地址！" >
	                    <option value="">选择城市</option>
	                    <option value="1">南宁</option>
	                    <option value="2">深圳</option>
	                </select>
	            </li>
	        </ul>
	        <div class="item-list-arrow-box p00 borderbottomgrey"><input class="btn-submit btn-next noradius bordertbgrey btnSubmit bgwhite red" type="button" name="button" value="确定"></div>
	    </section>
	    
	</div>
	
</div>

<section class="pop-tips hide">
	<div class="pop-tips-text">请选择</div>
</section>

</body>
<script src="${resourcePath}/commonjs/jquery-1.11.2.min.js"></script>
<script src="js/vue.2.3.0.js"></script>
<script src="js/progressive.img.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/TouchSlide.1.1.js"></script>
<script src="js/pulldownReload.js"></script>
<script src="js/method.common.js"></script>
<script src="js/method.index.js"></script>
<script src="js/vue.index.js"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=BF512d9c46c962e21adf4bd17340b6cc"></script>

<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</html>