<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html class="heightp100">
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<title>到家服务</title>
<link type="text/css" rel="stylesheet" href="${resourcePathHttps}/commoncss/swiper-3.4.2.min.css">
<link type="text/css" rel="stylesheet" href="css/homeservice.css?20170620">
</head>
<body class="pos-relative heightp100 bgwhite">
<div id="container" class="getlocation-box">
	<div class="reload-box dsn">
		<div id="reloadIcon" class="reload-icon"></div>
		<span id="reloadText">下拉刷新</span>
	</div>
	<div class="main-part01">  
		<header class="sectionBox fantasia-header displaybox dsn">
			<div id="cityOrientation" class="mleft15 boxflex01">
				<span class="mright5 maxw150 word-break1 left">
					<span id="curCity">正在定位…</span> 
					<span id="curDistrict"></span> 
					<span id="curBuilding"></span>
				</span>
				<!-- <div class="get-city-arrow left"></div> -->
				<img class="w14 left mtop12" src="images/icon_city_arrow.png" />
			</div>
			<a class="disblock mright15 t-right" href="myAppointment.do">
				<img class="icon_appointment mright5" src="images/icon_appointment.png" />
				<span>预约订单</span>
			</a>
		</header>
		<section id="slideBox" class="slideBox index-banner mtop0">
		    <div id="slidePic" class="bd">
		        <ul id="slideLi">
		            <!-- <li><a class="disblock progressive app-jump" href="serviceProcess01.html"><img class="preview lazy" data-src="http://public.image.linlile.com.cn/adPicBasePath/20170412142452689.jpg?2017-04-17_16_33_12&x-oss-process=image/resize,m_fill,w_640,h_299/format,jpg/quality,q_90/interlace,1" src="http://public.image.linlile.com.cn/adPicBasePath/20170412142452689.jpg?2017-04-17_16_33_12&x-oss-process=image/resize,m_fill,w_39,h_18/format,jpg/quality,q_90/interlace,1" /></a></li> -->
		            <!-- <li><a class="disblock progressive" href="#"><img class="preview lazy" data-src="http://public.image.linlile.com.cn/adPicBasePath/20170412142452689.jpg?2017-04-17_16_33_12&x-oss-process=image/resize,m_fill,w_640,h_299/format,jpg/quality,q_90/interlace,1" src="http://public.image.linlile.com.cn/adPicBasePath/20170412142452689.jpg?2017-04-17_16_33_12&x-oss-process=image/resize,m_fill,w_39,h_18/format,jpg/quality,q_90/interlace,1" /></a></li> -->
		        </ul>
		    </div>
		    <div id="slideDot" class="hd">
		        <ul></ul>
		    </div>
		</section>
		
		<section class="sectionBox pos-relative">
			<ul class="maintain-menus m010 t-center">
				<li class="servertype_prev_icon"></li>
				<li class="servertype_prev_icon"></li>
				<li class="servertype_prev_icon"></li>
				<li class="servertype_prev_icon"></li>
			</ul>
		</section>
		
		<li class="server-hot swiper-slide t-center dsn">
			<div class="server-hot-img-box"><img src="" /></div>
			<div class="m010">
				<div class="mtop5 f14 bold firstLine">监管到位</div>
				<div class="grey f12 secondLine">人员身份认证</div>
				<div class="mtop3 grey f12 thirdLine lineheight160">全程追踪监督</div>
			</div>
		</li>
		<section class="sectionBox pos-relative pb10 activity-wrap dsn" style="margin-top: -1px;">
			<section class="divide-box"></section>
			<!-- <div class="mtb10 f13"><span class="m010 adDesc">服务保障</span></div> -->
			<div class="m15 f14 t-center"><img class="w20 mtop3 align-middle" src="images/sale_title_left.png" /><span class="m015 adDesc">服务保障</span><img class="w20 mtop3 align-middle" src="images/sale_title_right.png" /></div>
			<div>
				<div class="swiper-container opacity0">
					<ul id="swiperWrapper" class="f14 lineheightnormal activity-box swiper-wrapper">
					<!--<li class="server-hot swiper-slide t-center" data-id="134" data-name="清洗" data-source="限时活动" data-href="maintainTypelist.do?name=清洗&parentTypeId=133&forSale=true&bigTypeName=空调清洗&smallTypeName=挂机*3">
							<div class="server-hot-img-box"><img src="images/img_jianguandaowei.jpg" /></div>
							<div class="m010">
								<div class="mtop5 f14 bold">监管到位</div>
								<div class="grey f14">人员身份认证</div>
								<div class="mtop3 grey f14">全程追踪监督</div>
							</div>
						</li> -->
						
					<!--<li class="server-hot server-big swiper-slide t-left" data-id="135" data-name="清洗" data-source="限时活动" data-href="maintainTypelist.do?name=清洗&parentTypeId=134&forSale=true&bigTypeName=日常保洁&smallTypeName=A1单次">
							<div class="server-hot-img-box"><img src="images/sale_icon_03.jpg" /></div>
							<div class="m010">
								<div class="mtop5 f14 word-break1">等离子电视机 换显示屏全年保修</div>
								<div class="mtop2 f12 grey word-break1">专业师傅上门维修 送全年电视机检查</div>
								<div class="f14"><span class="red">￥ 76.00</span> <span class="grey"><span class="f12 align-top">|</span> <span class="f12">床</span></span></div>
							</div>
						</li> -->
					</ul>
				</div>
			</div>
		</section>
	</div>
	
</div>
	
<div class="main-part02 getlocation-box dsn">    
    <section class="sectionBox item-details-info overscroll getlocation-box pos-relative">
        <section class="sectionBox password-mind-box pt12 borderbottomgrey displaybox">
        	<div id="closeLocationBox" class="w10 p015 lineheight0"><img class="" src="../images/back_black.png"></div>
            <div class="boxflex01 grey">请选择您所在省市</div>
        </section>
        <ul class="register-list">
        	<li class="borderbottomgrey">
  				<select id="province" class="province select_normal input-text wp100 list-arrow" name="province" data-first-title="选择省" title="选择省" >
                   	<option value="">选择省</option>
                   	<c:forEach items="${pcbList}" var="pcb" >
                   		<option value="${pcb.id}">${pcb.name}</option>
                  	</c:forEach>
            	</select> 
        	</li>   
            <li class="borderbottomgrey">
            	<select id="city" name="city" class="input-text wp100 list-arrow">
                     	<option value="">选择城市</option>
                </select> 
            </li>
            <li>
	            <select id="block" name="block" class="input-text wp100 list-arrow">
	                <option value="">选择区县</option>
	            </select>
            </li>
        </ul>
		<div class="modifyAddressBox">
	        <ul id="citySelectNewBox" class="register-list">
	            <li class="pos-relative searchBox bordertopgrey" style="min-height:37px;"><div class="mtop5 displaybox shop-create-header"><input id="shopCreateSearchBtn" class="order-search boxflex01 shop-create-search inputBorder" placeholder="请输入小区名称" type="text" name="search" /><div class="quick-delete hide"></div></div></li>
	        </ul>
		    <section class="sectionBox paddingMenu item-details-info pos-relative">
		        <li class="displaybox shop-create-check-single pointer mleft15 bordertopgrey dsn">
        			<span class="area-map-icon"></span>
		            <div class="area-info-text boxflex01 mleft5">
		                <span class="area-info-name f16" gbId="" ></span>
		                <br><span class="area-info-address grey"></span>
		            </div>
		        </li>
		        <ul class="register-list shop-create-check">
		            
		        </ul>        
		        
			</section>
		</div>
        <div class="item-list-arrow-box p00 bordertbgrey exchang-fixed dsn">
        	<input class="btn-submit btn-next noradius bordertbgrey btnSubmit bgwhite red" type="button" name="button" value="确定">
        </div>
    </section>
</div>

<div class="sectionBox loading grey bordertopgrey dsn"><img src="images/loading01.gif" /> 加载中…</div>
<div class="sectionBox creatInfo grey bordertopgrey t-center pb10 dsn borderbottomgrey">您输入的小区不在服务范围，您可以选择附近区域的服务</div>
<section class="pop-tips hide">
	<div class="pop-tips-text">请选择</div>
</section>

</body>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/TouchSlide.1.1.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.cookie.js"></script>
<script src="${resourcePathHttps}/commonjs/swiper-3.4.2.jquery.min.js"></script>
<script src="js/pulldownReload.js"></script>
<script src="js/method.common.js?v20170921"></script>
<script src="js/method.index.js?v20170921"></script>
<script src="js/method.getlocation.js"></script>
<script type="text/javascript">
	var cnzz_s_tag = document.createElement('script');
	cnzz_s_tag.type = 'text/javascript';
	cnzz_s_tag.async = true;
	cnzz_s_tag.charset = 'utf-8';
	cnzz_s_tag.src = 'https://s11.cnzz.com/z_stat.php?id=1261123817&async=1';
	var root_s = document.getElementsByTagName('script')[0];
	setTimeout(function(){
		root_s.parentNode.insertBefore(cnzz_s_tag, root_s);
	},4000);
</script>
</html>